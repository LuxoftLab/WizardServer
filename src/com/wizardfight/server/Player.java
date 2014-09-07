package com.wizardfight.server;

import java.util.HashSet;
import java.util.Set;

import com.wizardfight.Buff;
import com.wizardfight.FightMessage;
import com.wizardfight.Shape;
import com.wizardfight.WizardFight;

public class Player {
	private boolean isConnected = false;
	private int mana = WizardFight.PLAYER_MANA, health = WizardFight.PLAYER_HP;
	private Shape lastSpell = Shape.NONE;
	private HashSet<Buff> buffs = new HashSet<>();
	
	public void fromSelf(FightMessage msg) {
		mana = msg.mana;
		health = msg.health;
		if(msg.target == FightMessage.Target.SELF) {
			return;
		}
		switch(msg.action) {
		case BUFF_OFF:
			buffs.remove(Buff.values()[msg.param]);
			break;
		case BUFF_ON:
			buffs.add(Buff.values()[msg.param]);
		case HEAL:
			lastSpell = FightMessage.getShapeFromMessage(msg);
			break;
		case DAMAGE:
		case ENEMY_READY:
		case FIGHT_END:
		case FIGHT_START:
		case BUFF_TICK:
		case HIGH_DAMAGE:
		case NEW_HP_OR_MANA:
		case NONE:
			break;
		default:
			break;
		}
	}
	
	public void fromEnemy(FightMessage msg) {
		if(msg.target == FightMessage.Target.ENEMY) {
			return;
		}
		switch(msg.action) {
		case BUFF_ON:
			buffs.add(Buff.values()[msg.param]);
		case DAMAGE:
		case HIGH_DAMAGE:
			lastSpell = FightMessage.getShapeFromMessage(msg);
			break;
		case ENEMY_READY:
		case FIGHT_END:
		case FIGHT_START:
		case BUFF_TICK:
		case NEW_HP_OR_MANA:
		case HEAL:
		case BUFF_OFF:
		case NONE:
			break;
		default:
			break;
		}
	}
	
	public boolean isConnected() {
		return isConnected;
	}
	
	public int getHealth() {
		return health;
	}
	
	public int getMana() {
		return mana;
	}
	
	public Shape getSpell() {
		return lastSpell;
	}
	
	public HashSet<Buff> getBuffs() {
		return buffs;
	}
	
	public void setConnectionStatus(boolean f) {
		isConnected = f;
	}
}
