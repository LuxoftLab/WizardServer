package com.wizardfight.server;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.wizardfight.Buff;
import com.wizardfight.FightActivity;
import com.wizardfight.FightMessage;

public class Controller {
	private static Logger LOGGER = Logger.getLogger(Controller.class.getName());

	private static View view;
	
	private static Player players[] = {new Player("Player 1"), new Player("Player 2")};
	private static int nextAvailablePlayer = 0;
	
	private static HashMap<InetAddress, Player> map = new HashMap<>();
	
	private static boolean isBotGame;
	private static Player userPlayer = players[0];
	private static Player botPlayer = players[1];
	
	private static JFrame frame;
	
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
			if(nextAvailablePlayer == 2) {
				isBotGame = false;
			}
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
		//LOGGER.info("message from: "+socket.getInetAddress().getHostAddress());
		//LOGGER.info(msg.toString());
		InetAddress addr = socket.getInetAddress();
		if(!isBotGame && msg.mIsBotMessage) {
			isBotGame = true;
			botPlayer.setName("Bot");
			botPlayer.setConnectionStatus(true);
		}
		
		Player player;
		
		if(isBotGame) {
			player = (msg.mIsBotMessage)? botPlayer : userPlayer;
		} else {
			player = map.get(addr);
		}
		
		player.fromSelf(msg);
		getSecondPlayer(player).fromEnemy(msg);
		view.update(players);
	}
	
	public static synchronized void onDeviceName(String name, Socket socket) {
		// TODO change player name here
	}
			
	public static Player getSecondPlayer(Player first) {
		return players[0] == first ? players[1] : players[0];
	}
	
	public static void setView(View _view) {
		view = _view;
	};
	
	public static void endBattle(Player p) {
		p = players[0] == p ? players[1] : players[0];
		frame = new JFrame("End");
		JPanel panel = new JPanel();
		panel.setBackground(Color.black);
		JLabel l = new JLabel("<html><font color='White' size='10'>"+p.getName()+" win!</font></html>");
		l.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
		panel.add(l);
		frame.getContentPane().add(panel);
		frame.pack();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
		
		frame.setVisible(true);
	}
	
	
	public static void onLeaveFight() {
		if(frame != null)
			frame.dispose();
		for(int i=0; i<2; i++) {
			players[i].refresh();
		}
		view.update(players);
	}
}
