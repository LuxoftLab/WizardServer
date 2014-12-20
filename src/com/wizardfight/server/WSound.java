package com.wizardfight.server;

import java.io.File;
import java.util.EnumMap;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import com.wizardfight.Shape;

public class WSound {
	private static final EnumMap<Shape, String> SHAPE_SOUNDS;
	
	static {
		SHAPE_SOUNDS = new EnumMap<Shape, String>(Shape.class);
		SHAPE_SOUNDS.put(Shape.TRIANGLE, "sounds\\triangle_sound.wav");
		SHAPE_SOUNDS.put(Shape.CIRCLE, "sounds\\circle_sound.wav");
		SHAPE_SOUNDS.put(Shape.SHIELD, "sounds\\shield_sound.wav");
		SHAPE_SOUNDS.put(Shape.Z, "sounds\\z_sound.wav");
		SHAPE_SOUNDS.put(Shape.V, "sounds\\v_sound.wav");
		SHAPE_SOUNDS.put(Shape.PI, "sounds\\pi_sound.wav");
		SHAPE_SOUNDS.put(Shape.CLOCK, "sounds\\clock_sound.wav");
	}
	
	public static void playShapeSound(Shape s) {
		String path = SHAPE_SOUNDS.get(s);
		if(path == null) return;
		try {
			AudioInputStream ais = AudioSystem.
		            getAudioInputStream(new File(path));
			Clip clip = AudioSystem.getClip();
			clip.open(ais);
	        clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
