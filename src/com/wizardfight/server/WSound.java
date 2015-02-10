package com.wizardfight.server;

import java.net.URL;
import java.util.EnumMap;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import com.wizardfight.Shape;

public class WSound {
	private static final EnumMap<Shape, URL> SHAPE_SOUNDS;
	
	private static void putSound(Shape shape, String path) {
		SHAPE_SOUNDS.put(shape, WSound.class.getResource(path));
	}
	static {
		SHAPE_SOUNDS = new EnumMap<Shape, URL>(Shape.class);
		putSound(Shape.TRIANGLE, "sounds/triangle_sound.WAV");
		putSound(Shape.CIRCLE, "sounds/circle_sound.WAV");
		putSound(Shape.SHIELD, "sounds/shield_sound.WAV");
		putSound(Shape.Z, "sounds/z_sound.WAV");
		putSound(Shape.V, "sounds/v_sound.WAV");
		putSound(Shape.PI, "sounds/pi_sound.WAV");
		putSound(Shape.CLOCK, "sounds/clock_sound.WAV");
		putSound(Shape.FAIL, "sounds/fail_sound.WAV");
	}
	
	public static void playShapeSound(Shape s) {
		URL path = SHAPE_SOUNDS.get(s);
		try {
			AudioInputStream ais = AudioSystem.
		            getAudioInputStream(path);
			Clip clip = AudioSystem.getClip();
			clip.open(ais);
	        clip.start();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
