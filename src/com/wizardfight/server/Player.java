package com.wizardfight.server;

import java.util.HashSet;
import com.wizardfight.Buff;
import com.wizardfight.FightMessage;
import com.wizardfight.Shape;
import com.wizardfight.FightActivity;
import com.wizardfight.FightMessage.FightAction;
import com.wizardfight.FightMessage.Target;

public class Player {
	private String name = "Player";
	private boolean isConnected = false;
	private int mana = FightActivity.PLAYER_MANA,
			health = FightActivity.PLAYER_HP;
	private Shape lastSpell = Shape.NONE;
	private HashSet<Buff> buffs = new HashSet<>();

	public Player(String _name) {
		name = _name;
	}

	public void setName(String _name) {
		name = _name;
	}

	public String getName() {
		return name;
	}

	public void fromSelf(FightMessage msg) {
		if (msg.mHealth > health && msg.mAction != FightAction.BUFF_ON
				&& msg.mAction != FightAction.BUFF_TICK) {
			// msg.action = FightAction.HEAL;
		}
		mana = msg.mMana;
		health = msg.mHealth;

		Shape t = FightMessage.getShapeFromMessage(msg);
		if (t != Shape.NONE && FightMessage.isSpellCreatedByEnemy(msg)) {
			System.out.println(name + ": " + t.toString());
			lastSpell = t;
		}
		if (msg.mTarget == Target.SELF) {
			return;
		}
		switch (msg.mAction) {
		case BUFF_OFF:
			buffs.remove(Buff.values()[msg.mParam]);
			break;
		case BUFF_ON:
			buffs.add(Buff.values()[msg.mParam]);
		case HEAL:
			// lastSpell = FightMessage.getShapeFromMessage(msg);
			break;
		case FIGHT_END:
			end();
			break;
		case FIGHT_START:
			start();
			break;
		case DAMAGE:
		case ENEMY_READY:
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
		if (msg.mTarget == Target.ENEMY) {
			return;
		}
		switch (msg.mAction) {
		case BUFF_ON:
			buffs.add(Buff.values()[msg.mParam]);
		case DAMAGE:
		case HIGH_DAMAGE:
			// lastSpell = FightMessage.getShapeFromMessage(msg);
			break;
		case FIGHT_END:
			end();
			break;
		case FIGHT_START:
			start();
			break;
		case ENEMY_READY:
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

	public void end() {
		Controller.endBattle(this);
	}

	public void start() {

	}
}
