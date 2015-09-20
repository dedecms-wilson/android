package com.wl.push;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * ÍÆËÍÆ½Ì¨
 * @author Administrator
 *
 */
public class PushServer {
	
	public static void main(String[] args) {
		PushServer pushServer = new PushServer();
		
		pushServer.start();
	}
	
	
	
	public void start() {
		try {
		ServerSocket server = new ServerSocket(8080);
		System.out.println("server started!!");
		
			while(true){
				
				Socket socket = server.accept();
				System.out.println("server recieved:"  + socket.getInetAddress() + ":" + socket.getPort());
				BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
				bufferedWriter.write("push server");
				bufferedWriter.newLine();
				bufferedWriter.flush();
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
