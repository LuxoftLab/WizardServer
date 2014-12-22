package com.wizardfight.server;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.InputStream;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetAddress;
import java.util.Enumeration;

import javax.swing.JFrame;

public class Main {
	public static void main(String args[]) throws IOException {
		new ConnectionListener().start();
		JFrame frame = new JFrame();
		View v = new View();
		Controller.setView(v);
	  
		frame.getContentPane().add(v);
		frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize(800,600);
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
		
	}
}
