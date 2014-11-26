package com.wizardfight.server;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import com.wizardfight.FightMessage;

public class ClientListener extends Thread {
	Socket socket;
	
	public ClientListener(Socket _socket) {
		socket = _socket;
	}

	@Override 
	public void run() {
		Controller.bindSocket(socket);
		byte buffer[] = new byte[8];
		try {
			InputStream in = socket.getInputStream();
			while(in.read(buffer) != -1) {
				FightMessage msg = FightMessage.fromBytes(buffer);
				Controller.onFightMessage(msg, socket);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		Controller.unbindSocket(socket);
	}
	
}
