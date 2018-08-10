package com.multiThread.chatroom;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Collection;
import java.util.HashMap;

//服务端
public class Server {

	private ServerSocket server;
	private boolean isRunning = true;
	//连接中的通道（服务端与客户端建立的一条通道）
	private HashMap<String,Channel> channels = new HashMap<String,Channel>();
	
	public static void main(String[] str)
	{
		new Server().start();
	}
	//开启服务端
	public void start()
	{
		try {
			server = new ServerSocket(7666);
			while( isRunning )
			{
				new Thread(new Channel(server.accept(),this)).start();				
			}
		} catch (IOException e) {
			isRunning = false;
			e.printStackTrace();
		}
	}
	//添加一个通道
	public void addChannel(String name , Channel channel)
	{
		this.channels.put(name, channel);
		transmit(name+"进来了！");
	}
	//删除一个通道
	public void deleteChannel(String name)
	{
		this.channels.remove(name);
		transmit(name+"离开了！");
	}
	//服务端转发消息
	public void transmit(String message){
		if( message.indexOf('@') == -1 )
		{//有‘@’符号，表示 为私聊，反之表示群聊。
			groupChat(message);
		}else{
			privateChat(message);
		}
	}
	//私聊
	private void privateChat(String message) {
		String[] messages = message.split(":");
		String msg = messages[1];
		String name = messages[0].substring(1);
		Channel c = channels.get(name);
		if( c != null )
		{
			c.send(msg);
		}
	}
	//群聊
	private void groupChat(String message) {		
		Collection<Channel> values = channels.values();
		for( Channel c : values )
		{
			c.send(message);
		}
	}

	public void stop(){
		isRunning = false;
		IOutils.close(server);
	}
}
