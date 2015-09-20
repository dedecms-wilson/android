package com.wl.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class PushClient {
	
	public static void main(String[] args) {
		PushClient PushClient = new PushClient();
		
		PushClient.start();
	}
	
	public void start() {
		try {
			Socket socket = new Socket("127.0.0.1",8080);
			
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
			//start thread,accept message from server
			new Thread(new ReaderThread(bufferedReader)).start();
		
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

class ReaderThread implements Runnable {
	
	private BufferedReader reader;

	public ReaderThread(BufferedReader reader) {
		this.reader = reader;
	}

	@Override
	public void run() {
		String content = null;
		while(true){
			 try {
				if((content = reader.readLine()) != null) {
					 System.out.println("server push message:" + content);
				 }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}
