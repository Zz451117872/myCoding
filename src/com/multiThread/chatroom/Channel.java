package com.multiThread.chatroom;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Channel implements Runnable{

	private Socket socket; //与客户端连接的socket
	private Server server;	//服务器
	private BufferedReader reader;
	private boolean isRunning = true;
	private String name ;	//客户端标示
	private BufferedWriter writer;
	
	public Channel(Socket socket , Server server)
	{
		this.server = server;
		this.socket = socket;
		try {
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			name = reader.readLine();
			server.addChannel(name, this);
			System.out.println(name + "连接成功！");
		} catch (Exception e) {
			isRunning = false;
			server.deleteChannel(name);
			IOutils.close(socket,reader,writer);
			e.printStackTrace();
		} 
	}
	
	public void send(String message){
		try {
			this.writer.write(message+"\r\n");
			this.writer.flush();
		} catch (IOException e) {
			isRunning = false;
			server.deleteChannel(name);
			IOutils.close(socket,reader,writer);
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		while( isRunning )
		{
			try {
				server.transmit( reader.readLine() );
			} catch (IOException e) {
				isRunning = false;
				server.deleteChannel(name);
				IOutils.close(socket,reader,writer);
				System.out.println(name + "断开连接！");
				//e.printStackTrace();
			}
		}
	}

}
