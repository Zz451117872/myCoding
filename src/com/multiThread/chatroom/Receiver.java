package com.multiThread.chatroom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

public class Receiver implements Runnable{
	private Socket socket;
	private String name;
	private BufferedReader reader;
	private boolean isRunning = true;
	
	public Receiver(Socket socket ,String name ){
		this.socket = socket;
		this.name = name;
		try {
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
			
		} catch (Exception e) {
			isRunning = false;
			e.printStackTrace();
		} 
	}
	@Override
	public void run() {
		while( isRunning )
		{
			try {
				System.out.println( reader.readLine() );
			} catch (IOException e) {
				isRunning = false;
				e.printStackTrace();
			}
		}
	}

}
