package com.wizardfight.server;

import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.wizardfight.Buff;
import com.wizardfight.Shape;
import com.wizardfight.WizardFight;

public class View extends JPanel {
	
	private static HashMap<Shape, ImageIcon> shapes = new HashMap<>();
	private static HashMap<Buff, ImageIcon> buffs = new HashMap<>();
	static {
		shapes.put(Shape.NONE, new ImageIcon("img/nothing.png"));
		shapes.put(Shape.CIRCLE, new ImageIcon("img/"+Shape.CIRCLE+".png"));
		shapes.put(Shape.TRIANGLE, new ImageIcon("img/"+Shape.TRIANGLE+".png"));
		shapes.put(Shape.CLOCK, new ImageIcon("img/"+Shape.CLOCK+".png"));
		shapes.put(Shape.PI, new ImageIcon("img/"+Shape.PI+".png"));
		shapes.put(Shape.SHIELD, new ImageIcon("img/"+Shape.SHIELD+".png"));
		shapes.put(Shape.V, new ImageIcon("img/"+Shape.V+".png"));
		shapes.put(Shape.Z, new ImageIcon("img/"+Shape.Z+".png"));
		
		buffs.put(Buff.BLESSING, new ImageIcon("img/buff_blessing.png"));
		buffs.put(Buff.HOLY_SHIELD, new ImageIcon("img/buff_shield.png"));
		buffs.put(Buff.CONCENTRATION, new ImageIcon("img/buff_concentration.png"));
		buffs.put(Buff.WEAKNESS, new ImageIcon("img/buff_weakness.png"));
	}
	
	JLabel playersLabel[], spells[], spellNames[], playersBuffs[][];
	JIndicator health[], mana[];

    public View() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        playersLabel = new JLabel[2];
        health = new JIndicator[2];
        mana = new JIndicator[2];
        spells = new JLabel[2];
        spellNames=new JLabel[2];
        playersBuffs = new JLabel[2][4];
        JPanel jp=new JPanel();
        add(jp);
        setBackground(Color.BLACK);

        jp.setLayout(new java.awt.GridLayout());
        JPanel jPanels[]=new JPanel[2];
        for(int i=0;i<2;i++) {
            jPanels[i] = new javax.swing.JPanel();
            jPanels[i].setBackground(Color.BLACK);

            jp.add(jPanels[i]);
            jPanels[i].setLayout(new java.awt.GridLayout());
            javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanels[i]);
            jPanels[i].setLayout(jPanel1Layout);

            health[i] = new JIndicator(0, WizardFight.PLAYER_HP, i>0);
            health[i].setValue(WizardFight.PLAYER_HP);
            health[i].backColor=Color.RED;
            health[i].setOpaque(false);
            mana[i] = new JIndicator(0, WizardFight.PLAYER_MANA, i>0);
            mana[i].setMainColor(Color.BLUE);
            mana[i].setOpaque(false);
            mana[i].setValue(WizardFight.PLAYER_MANA);
            spells[i] = new JLabel(shapes.get(Shape.NONE));
            spells[i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

            spellNames[i] = new JLabel("");
            spellNames[i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            spellNames[i].setForeground(Color.WHITE);
            spellNames[i].setText("<html><font size='10'>Shape: </font></html>");
            
            playersBuffs[i][Buff.HOLY_SHIELD.ordinal()] = new JLabel(buffs.get(Buff.HOLY_SHIELD));
            playersBuffs[i][Buff.WEAKNESS.ordinal()] = new JLabel(buffs.get(Buff.WEAKNESS));
            playersBuffs[i][Buff.CONCENTRATION.ordinal()] = new JLabel(buffs.get(Buff.CONCENTRATION));
            playersBuffs[i][Buff.BLESSING.ordinal()] = new JLabel(buffs.get(Buff.BLESSING));

            JPanel buffsPanel = new JPanel();
            for(int j=0; j<4; j++) {
                playersBuffs[i][j].setVisible(false);
                buffsPanel.add(playersBuffs[i][j]);
            }
            buffsPanel.setOpaque(false);
            playersLabel[i] = new JLabel("Not connected");
            playersLabel[i].setForeground(Color.WHITE);
            playersLabel[i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jPanel1Layout.setHorizontalGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(playersLabel[i], javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(spellNames[i], javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(spells[i], javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(buffsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(mana[i], javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(health[i], javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addContainerGap())
            );
            jPanel1Layout.setVerticalGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addContainerGap()
                                            .addComponent(health[i], javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(mana[i], javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(buffsPanel, javax.swing.GroupLayout.PREFERRED_SIZE,75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(spells[i], javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(spellNames[i], javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(playersLabel[i], javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addContainerGap()
                            )
            );
        }
    }
	
	public void update(Player[] players) {
		for(int i=0; i<2; i++) {
			playersLabel[i].setText(players[i].isConnected() ? players[i].getName() : "Not connected");
			health[i].setValue(players[i].getHealth());
			health[i].setString(players[i].getHealth()+"/"+WizardFight.PLAYER_HP);
			mana[i].setValue(players[i].getMana());
			mana[i].setString(players[i].getMana()+"/"+WizardFight.PLAYER_MANA);
			spells[i].setIcon(shapes.get(players[i].getSpell()));
			HashSet<Buff> b = players[i].getBuffs();
			for(int j=0; j<4; j++) {
				playersBuffs[i][j].setVisible(b.contains(Buff.values()[j]));
			}
			spellNames[i].setText("<html><font size='10'>Shape: "+players[i].getSpell().toString()+"</font></html>");
		}
		this.repaint();
	}

}
