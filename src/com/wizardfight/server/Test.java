package com.wizardfight.server;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import com.wizardfight.FightMessage;
import com.wizardfight.FightMessage.FightAction;
import com.wizardfight.FightMessage.Target;
import com.wizardfight.Shape;
import com.wizardfight.remote.WifiService;

public class Test {
	public static void main(String args[]) throws UnknownHostException, IOException, InterruptedException {
		Socket soket = new Socket("127.0.0.1", WifiService.PORT);
		FightMessage msg = new FightMessage(Shape.SHIELD);
		msg.target = msg.target == Target.SELF ? Target.ENEMY : Target.SELF;
		msg.health = 100;
		msg.mana = 10;
		soket.getOutputStream().write(msg.getBytes());
		msg = new FightMessage(Shape.Z);
		msg.target = msg.target == Target.SELF ? Target.ENEMY : Target.SELF;
		msg.health = 100;
		msg.mana = 10;
		soket.getOutputStream().write(msg.getBytes());
		msg = new FightMessage(Shape.V);
		msg.target = msg.target == Target.SELF ? Target.ENEMY : Target.SELF;
		msg.health = 100;
		msg.mana = 10;
		soket.getOutputStream().write(msg.getBytes());
		msg = new FightMessage(Shape.PI);
		msg.target = msg.target == Target.SELF ? Target.ENEMY : Target.SELF;
		msg.health = 100;
		msg.mana = 10;
		soket.getOutputStream().write(msg.getBytes());
		//Thread.currentThread().sleep(1000*30);
		soket.getOutputStream().close();
		soket.close();
	}
}
