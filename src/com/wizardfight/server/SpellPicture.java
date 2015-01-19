package com.wizardfight.server;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.Timer;
import javax.swing.JLabel;

public class SpellPicture extends JLabel {
	public static final long RUNNING_TIME = 1000;
	protected static final Color GP_COLOR = new Color(0, 0, 0, 1f);
	private float alpha = 0f;
	private long startTime = -1;
	private Timer timer;

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(new Color(0, 0, 0, alpha));
		g.fillRect(0, 0, getWidth(), getHeight());
	}

	public void setSpell(Icon i) {
		
		if(timer != null) {
			timer.stop();
			startTime = -1;
			alpha = 0f;
		}
		super.setIcon(i);
		
		timer = new Timer(40, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("action performed!");
				if (startTime < 0) {
					startTime = System.currentTimeMillis();
				} else {

					long time = System.currentTimeMillis();
					long duration = time - startTime;
					if (duration >= RUNNING_TIME) {
						startTime = -1;
						((Timer) e.getSource()).stop();
						alpha = 1f;
					} else {
						alpha = ((float) duration / (float) RUNNING_TIME);
					}
					repaint();
				}
			}
		});
		timer.start();
	}
}
