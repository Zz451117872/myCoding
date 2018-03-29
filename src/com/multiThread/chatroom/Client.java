package com.multiThread.chatroom;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

//客户端
public class Client {
	
	private Socket socket;
	private String name; //客户端用户标示
	
	public static void main(String[] str)
	{
		new Client().start();
	}
	
	public void start()
	{
		try {
			socket = new Socket("localhost",7666);
			name = javax.swing.JOptionPane.showInputDialog("请输入用户名：");
			new Thread(new Sender(socket,name)).start();
			new Thread(new Receiver(socket,name)).start();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
		
	public void stop(){
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
