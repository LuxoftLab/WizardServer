package com.wizardfight.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.wizardfight.remote.WifiService;

public class ConnectionListener extends Thread {
	
	ServerSocket server;
	
	@Override
	public void run() {
		try {
			server = new ServerSocket(WifiService.PORT);
			while(true) {
				Socket client = server.accept();
				new ClientListener(client).start();
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}
