package com.wizardfight.server;

import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.logging.Logger;

import com.wizardfight.FightMessage;

public class Controller {
	private static Logger LOGGER = Logger.getLogger(Controller.class.getName());

	private static View view;
	
	private static Player players[] = {new Player(), new Player()};
	private static int nextAvailablePlayer = 0;
	
	private static HashMap<InetAddress, Player> map = new HashMap<>();
	
	public static synchronized void bindSocket(Socket socket) {
		LOGGER.info("connection from: "+socket.getInetAddress().getHostAddress());
		InetAddress addr = socket.getInetAddress();
		Player player = map.get(addr);
		if(player == null) {
			if(nextAvailablePlayer == players.length) {
				return;
			}
			map.put(addr, players[nextAvailablePlayer]);
			player = players[nextAvailablePlayer++];
		}
		player.setConnectionStatus(true);
		view.update(players);
	}
	
	public static synchronized void unbindSocket(Socket socket) {
		LOGGER.info("connection closed: "+socket.getInetAddress().getHostAddress());
		InetAddress addr = socket.getInetAddress();
		Player player = map.get(addr);
		if(player != null) {
			player.setConnectionStatus(false);
			view.update(players);
		}
	}
	
	public static synchronized void onFightMessage(FightMessage msg, Socket socket) {
		LOGGER.info("message from: "+socket.getInetAddress().getHostAddress());
		LOGGER.info(msg.toString());
		InetAddress addr = socket.getInetAddress();
		Player player = map.get(addr);
		player.fromSelf(msg);
		getSecondPlayer(player).fromEnemy(msg);
		view.update(players);
	}
	
	public static Player getSecondPlayer(Player first) {
		return players[0] == first ? players[1] : players[0];
	}
	
	public static void setView(View _view) {
		view = _view;
	};
}
