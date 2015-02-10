package com.wizardfight.server;

import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.wizardfight.Buff;
import com.wizardfight.Shape;
import com.wizardfight.FightActivity;


public class View extends JPanel {
	
	private static HashMap<Shape, ImageIcon> shapes = new HashMap<>();
	private static HashMap<Buff, ImageIcon> buffs = new HashMap<>();
	private static final int BUFFS_SIZE;
	static {
		shapes.put(Shape.NONE, new ImageIcon("img/nothing.png"));
		shapes.put(Shape.CIRCLE, new ImageIcon("img/"+Shape.CIRCLE+".png"));
		shapes.put(Shape.TRIANGLE, new ImageIcon("img/"+Shape.TRIANGLE+".png"));
		shapes.put(Shape.CLOCK, new ImageIcon("img/"+Shape.CLOCK+".png"));
		shapes.put(Shape.PI, new ImageIcon("img/"+Shape.PI+".png"));
		shapes.put(Shape.SHIELD, new ImageIcon("img/"+Shape.SHIELD+".png"));
		shapes.put(Shape.V, new ImageIcon("img/"+Shape.V+".png"));
		shapes.put(Shape.Z, new ImageIcon("img/"+Shape.Z+".png"));
		shapes.put(Shape.FAIL, new ImageIcon("img/"+Shape.FAIL+".png"));
		
		buffs.put(Buff.BLESSING, new ImageIcon("img/buff_blessing.png"));
		buffs.put(Buff.HOLY_SHIELD, new ImageIcon("img/buff_shield.png"));
		buffs.put(Buff.CONCENTRATION, new ImageIcon("img/buff_concentration.png"));
		buffs.put(Buff.WEAKNESS, new ImageIcon("img/buff_weakness.png"));
		BUFFS_SIZE = buffs.size();
	}
	
	JLabel playersLabel[], spellNames[], playersBuffs[][];
	JIndicator health[], mana[];
	SpellPicture spells[];
    public View() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        playersLabel = new JLabel[2];
        health = new JIndicator[2];
        mana = new JIndicator[2];
        spells = new SpellPicture[2];
        spellNames=new JLabel[2];
        playersBuffs = new JLabel[2][BUFFS_SIZE];
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

            health[i] = new JIndicator(0, FightActivity.PLAYER_HP, i>0);
            health[i].setValue(FightActivity.PLAYER_HP);
            health[i].backColor=Color.RED;
            health[i].setOpaque(false);
            mana[i] = new JIndicator(0, FightActivity.PLAYER_MANA, i>0);
            mana[i].setMainColor(Color.BLUE);
            mana[i].setOpaque(false);
            mana[i].setValue(FightActivity.PLAYER_MANA);
            spells[i] = new SpellPicture();
            spells[i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

            spellNames[i] = new JLabel("");
            spellNames[i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            spellNames[i].setForeground(Color.WHITE);
            spellNames[i].setText("");
            
            for (Map.Entry<Buff, ImageIcon> entry : buffs.entrySet()) {
                playersBuffs[i][ entry.getKey().ordinal() ] = new JLabel( entry.getValue() );
            }

            JPanel buffsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            for(int j=0; j<BUFFS_SIZE; j++) {
                playersBuffs[i][j].setVisible(false);
                buffsPanel.add(playersBuffs[i][j]);
            }
            buffsPanel.setOpaque(false);
            playersLabel[i] = new JLabel(Main.label("Not_connected"));
            playersLabel[i].setFont(new Font("Arial", Font.BOLD, 24));
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
	
    public void showCast(Player p) {
    	int i = Controller.getPlayerIndex(p);
    	Shape s = p.getSpell();
    	if(s == Shape.NONE) return;
    	
    	spells[i].setSpell(shapes.get(s));
    }
    
	public void update(Player[] players) {
		for(int i=0; i<2; i++) {
			String pLabel = players[i].isConnected() ? players[i].getName()
                    : Main.label("Not_connected");
			
            playersLabel[i].setText(pLabel);
            playersLabel[i].setFont(new Font("Arial", Font.BOLD, 24));
			health[i].setValue(players[i].getHealth());
			health[i].setString(players[i].getHealth()+"/"+FightActivity.PLAYER_HP);
			mana[i].setValue(players[i].getMana());
			mana[i].setString(players[i].getMana()+"/"+FightActivity.PLAYER_MANA);
			HashSet<Buff> b = players[i].getBuffs();
			for(int j=0; j<BUFFS_SIZE; j++) {
				playersBuffs[i][j].setVisible(b.contains(Buff.values()[j]));
			}
			String shapeLabel =  Main.spellLabel(players[i].getSpell());
			
            spellNames[i].setText("<html><font size='10'>" + shapeLabel + "</font></html>");
		}
		this.repaint();
	}

}
