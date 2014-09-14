package com.wizardfight.server;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

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
	}
}
