package com.multiThread.chatroom;

import java.io.Closeable;
import java.io.IOException;

public class IOutils {
	
	public static void close(Closeable ...closeables )
	{
		for(int i=0; i<closeables.length; i++)
		{
			try {
				closeables[i].close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
