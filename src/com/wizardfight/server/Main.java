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
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JFrame;

import com.wizardfight.Shape;

public class Main {
	private static ResourceBundle labels;

	static HashMap <Shape, String> ShapesSpells = new HashMap <Shape, String>();

	static {
		ShapesSpells.put(Shape.TRIANGLE, "Cone_of_cold");
		ShapesSpells.put(Shape.CIRCLE, "Circle_of_fire");
		ShapesSpells.put(Shape.CLOCK, "Healing");
		ShapesSpells.put(Shape.PI, "Blessing");
		ShapesSpells.put(Shape.V, "Concentration");
		ShapesSpells.put(Shape.SHIELD, "Holy_Shield");
		ShapesSpells.put(Shape.Z, "Weakness");
		ShapesSpells.put(Shape.FAIL, "fail");
		ShapesSpells.put(Shape.NONE, "none");
		
	}
	
    static {
        labels = ResourceBundle
                .getBundle("LabelsBundle", Locale.getDefault());
    }

    public static String label(String key) {
        System.out.println("label(" + key + "): " + labels.getString(key));
        return labels.getString(key);
    }
    
    public static String spellLabel(Shape shape) {
    	        
    	return labels.getString(ShapesSpells.get(shape));
    }
    
	public static void main(String args[]) throws IOException {
		new ConnectionListener().start();
		JFrame frame = new JFrame();
		View v = new View();
		Controller.setView(v);
	  
		frame.getContentPane().add(v);
		frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize(800,600);
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
		
		//show ip 
		boolean flag = false;
		Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces();
        while(e.hasMoreElements()) {
        	if(flag)
        		break;
            NetworkInterface n = (NetworkInterface) e.nextElement();
            Enumeration<InetAddress> ee = n.getInetAddresses();
           
            while (ee.hasMoreElements()) {
                InetAddress i = (InetAddress) ee.nextElement();
                if(i.isSiteLocalAddress()) {
                frame.setTitle(
                		InetAddress.getLocalHost().getCanonicalHostName() 
                		+ " - " + i.getHostAddress());
                	flag = true;
                	break;
                }
            }
        }
        frame.setMinimumSize(new Dimension(800, 700));
		frame.setVisible(true);
	}
}
