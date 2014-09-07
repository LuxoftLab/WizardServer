package com.wizardfight.server;

import java.util.HashMap;
import java.util.HashSet;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

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
	
	JLabel playersLabel[], spells[], playersBuffs[][];
	JProgressBar health[], mana[];
	
	public View() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		playersLabel = new JLabel[2];
		health = new JProgressBar[2];
		mana = new JProgressBar[2];
		spells = new JLabel[2];
		playersBuffs = new JLabel[2][4];
		for(int i=0; i<2; i++) {
			playersLabel[i] = new JLabel("Not connected");
			playersLabel[i].setAlignmentX(LEFT_ALIGNMENT);
			health[i] = new JProgressBar(0, WizardFight.PLAYER_HP);
			mana[i] = new JProgressBar(0, WizardFight.PLAYER_MANA);
			health[i].setValue(WizardFight.PLAYER_HP);
			mana[i].setValue(WizardFight.PLAYER_MANA);
			health[i].setStringPainted(true);
			mana[i].setStringPainted(true);
			spells[i] = new JLabel(shapes.get(Shape.NONE));
			
			playersBuffs[i][Buff.HOLY_SHIELD.ordinal()] = new JLabel(buffs.get(Buff.HOLY_SHIELD));
			playersBuffs[i][Buff.WEAKNESS.ordinal()] = new JLabel(buffs.get(Buff.WEAKNESS));
			playersBuffs[i][Buff.CONCENTRATION.ordinal()] = new JLabel(buffs.get(Buff.CONCENTRATION));
			playersBuffs[i][Buff.BLESSING.ordinal()] = new JLabel(buffs.get(Buff.BLESSING));
			
			JPanel wrapper = new JPanel();
			wrapper.setAlignmentX(LEFT_ALIGNMENT);
			wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.X_AXIS));
			JPanel hm = new JPanel();
			hm.setLayout(new BoxLayout(hm, BoxLayout.Y_AXIS));
			JPanel spellPanel = new JPanel();
			hm.add(health[i]);
			hm.add(mana[i]);
			spellPanel.add(spells[i]);
			wrapper.add(hm);
			wrapper.add(spellPanel);
			
			JPanel buffsPanel = new JPanel();
			buffsPanel.setAlignmentX(LEFT_ALIGNMENT);
			buffsPanel.setLayout(new BoxLayout(buffsPanel, BoxLayout.X_AXIS));
			for(int j=0; j<4; j++) {
				playersBuffs[i][j].setVisible(false);
				buffsPanel.add(playersBuffs[i][j]);
			}
			add(playersLabel[i]);
			add(wrapper);
			add(buffsPanel);
		}
		
	}
	
	public void update(Player[] players) {
		for(int i=0; i<2; i++) {
			playersLabel[i].setText(players[i].isConnected() ? "Connected" : "Not connected");
			health[i].setValue(players[i].getHealth());
			health[i].setString(players[i].getHealth()+"/"+WizardFight.PLAYER_HP);
			mana[i].setValue(players[i].getMana());
			mana[i].setString(players[i].getMana()+"/"+WizardFight.PLAYER_MANA);
			spells[i].setIcon(shapes.get(players[i].getSpell()));
			HashSet<Buff> b = players[i].getBuffs();
			for(int j=0; j<4; j++) {
				playersBuffs[i][j].setVisible(b.contains(Buff.values()[j]));
			}
		}
		this.repaint();
	}

}
