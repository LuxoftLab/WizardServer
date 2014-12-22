package com.wizardfight.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

import com.wizardfight.FightMessage;
import com.wizardfight.remote.WifiMessage;

public class ClientListener extends Thread {
	Socket socket;
	
	public ClientListener(Socket _socket) {
		socket = _socket;
	}

	@Override 
	public void run() {
		Controller.bindSocket(socket);
		Object message;
		try {
			ObjectInputStream in = 
					new ObjectInputStream( socket.getInputStream() );
			while(true) {
				long t1 = System.currentTimeMillis();
				message = in.readObject();
				long t2 = System.currentTimeMillis();
				System.out.println(t2 - t1);
				
				if(message == null) break;
				
				if(message instanceof FightMessage) {
					Controller.onFightMessage((FightMessage)message, socket);
					
				} else if (message instanceof String) {
					Controller.onDeviceName((String)message, socket);
					
				} else if (message instanceof WifiMessage) {
					WifiMessage msg = (WifiMessage)message;
					if(msg == WifiMessage.LEAVE_FIGHT) {
						Controller.onLeaveFight();
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Controller.unbindSocket(socket);
	}
	
}
