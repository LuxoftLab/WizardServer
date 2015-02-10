package com.wizardfight.server;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;

public class JIndicator extends  JProgressBar implements ActionListener {
    boolean right=true;
    Color background=Color.black;
    Color mainColor=Color.green;
    Color backColor;
    int fontSize =24; 
    int margin=0;
    double lvalue=0;
    double AnimStep=0;
    Timer clock = new Timer(20,this);

    JIndicator(int min, int max, boolean right){
        super();
        this.right=right;
        setMaximum(max);
        setMinimum(min);
        clock.start();
    }

    public void setMainColor(Color mainColor) {
        this.mainColor = mainColor;
    }

    public void actionPerformed(ActionEvent e) {
        if(lvalue != getValue())
        {
            if(lvalue > getValue())
                lvalue -= AnimStep;
            else
                lvalue = getValue();
            repaint();
        }
    }
    @Override
    public void	setValue(int n)
    {
        lvalue=getValue();
        super.setValue(n);
        if(n < lvalue) {
            AnimStep = (lvalue - n) * 0.05f;
        } else {
            lvalue=getValue();

        }
    }

    int ShiftNorth(int p, int distance) {
    	   return (p - distance);
    	   }
    	int ShiftSouth(int p, int distance) {
    	   return (p + distance);
    	   }
    	int ShiftEast(int p, int distance) {
    	   return (p + distance);
    	   }
    	int ShiftWest(int p, int distance) {
    	   return (p - distance);
    	   }
    	
    @Override
    public void paint(Graphics g) {
            String str = getValue() + "/" + getMaximum();
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
          /*  g2d.setColor(background);
            g2d.fillRect(0, 0, getWidth() , getHeight());*/
        int t = 0;
        int t1 = 0;
        if (backColor == null) {
            t = getWidth()/4;
            if (!right) {
                t1 =getWidth()/4;
            }
        }
            int v = (int) ((getMaximum() - getValue()) / (getMaximum() * 1.0 - getMinimum()) * (getWidth()- t - margin * 2));
            int v1 = (int) ((getMaximum() - lvalue) / (getMaximum() * 1.0 - getMinimum()) * (getWidth() - margin * 2));

            if (right) {
                if (backColor != null) {
                    g2d.setColor(backColor);
                    g2d.fillRect(margin + t1, margin, getWidth() - t - v1 - margin * 2, getHeight() - margin * 2);
                }
                g2d.setColor(mainColor);
                g2d.fillRect(margin + t1, margin, getWidth() - t - v - margin * 2, getHeight() - margin * 2);
            } else {
                if (backColor != null) {
                    g2d.setColor(backColor);
                    g2d.fillRect(margin + t1 + v1, margin, getWidth() - t - v1 - margin * 2, getHeight() - margin * 2);
                }
                g2d.setColor(mainColor);
                g2d.fillRect(margin + t1 + v, margin, getWidth() - t - v - margin * 2, getHeight() - margin * 2);
            }
            
            Font font = new Font(getFont().getFontName(), Font.PLAIN, fontSize);

            int strX = (getWidth()- t +t1*2 - (g2d.getFontMetrics().stringWidth(str))) / 2;
            int strY = (getHeight() - 5 + fontSize) / 2;
            g2d.setFont(font);
            g2d.setColor(Color.DARK_GRAY);
            g2d.drawString(str, ShiftWest(strX, 1), ShiftNorth(strY, 1));
            g2d.drawString(str, ShiftWest(strX, 1), ShiftSouth(strY, 1));
            g2d.drawString(str, ShiftEast(strX, 1), ShiftNorth(strY, 1));
            g2d.drawString(str, ShiftEast(strX, 1), ShiftSouth(strY, 1));

            g2d.setColor(Color.WHITE);
            g2d.drawString(str, strX, strY);
        }
}
