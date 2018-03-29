package com.multiThread.chatroom;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Sender implements Runnable{
	
	private Socket socket;
	private String name;
	private boolean isRunning = true;
	private BufferedWriter writer;
	private Scanner scanner = new Scanner(System.in);
	
	public Sender(Socket socket ,String name ){
		this.socket = socket;
		this.name = name;
		try{
			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"));
			send(name);
		}catch(Exception e){
			isRunning = false;
			System.out.println(name + "发送器初始化失败！");
		}		
	}
	
	public void send(String message) throws IOException{		
			this.writer.write(message+"\r\n");
			//this.writer.newLine();
			this.writer.flush();		
	}
	
	public String message()
	{
		return scanner.nextLine();
	}
	@Override
	public void run() {
		while( isRunning )
		{
			try {
				send( message() );
			} catch (IOException e) {
				isRunning = false;
				e.printStackTrace();
			}
		}
	}

}
