package org.codehamster;

import javax.sound.midi.InvalidMidiDataException;

public class MozartMain {
	public static void main(String[] args) throws InvalidMidiDataException, InterruptedException {
		System.out.println("Mozart Digital Composer v0.1 (C)Copyright 2017 Manny Peterson All Rights Reserved");

		MozartScale scale = new MozartScale(MozartScaleType.MAJOR, MozartPitchType.C_SHARP_D_FLAT);
		
		
		MozartPhrase phrase1 = new MozartPhrase(scale, MozartOctaveType.SIXTH, 100);
		MozartPhrase phrase2 = new MozartPhrase(scale, MozartOctaveType.SEVENTH, 100);
		MozartPhrase phrase3 = new MozartPhrase(scale, MozartOctaveType.SIXTH, 100);
		MozartPhrase phrase4 = new MozartPhrase(scale, MozartOctaveType.SIXTH, 100);


		MozartArrangement arrange = new MozartArrangement(10);
		
		arrange.addPhrase(phrase1);
		arrange.addPhrase(phrase2);
		arrange.addPhrase(phrase3);
		arrange.addPhrase(phrase4);
		arrange.create();
		
		MozartInstrument inst = new MozartInstrument();
		
		for(MozartPhrase playPhrase: arrange.getArrangement()) {
			inst.play(playPhrase);			
		}
	}
}
