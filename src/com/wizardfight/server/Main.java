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
	   // System.out.println("Your Host addr: " + InetAddress.getLocalHost().getHostAddress());  // often returns "127.0.0.1"
	    Enumeration<NetworkInterface> n = NetworkInterface.getNetworkInterfaces();
	    for (int j = 0; n.hasMoreElements(); j++)
	    {
	        NetworkInterface e = n.nextElement();

	        Enumeration<InetAddress> a = e.getInetAddresses();
	        for (int i = 0; a.hasMoreElements(); i++)
	        {
	            InetAddress addr = a.nextElement();
	            if(j == 13 && i == 0)
	            {
	            	//System.out.println(j + "  " +  i + " " + addr.getHostAddress());
	        		frame.setTitle(addr.getHostAddress());
	            }
	        }
	    }
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
