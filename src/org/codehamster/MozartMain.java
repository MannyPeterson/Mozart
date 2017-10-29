package org.codehamster;

import javax.sound.midi.InvalidMidiDataException;

public class MozartMain {
	public static void main(String[] args) throws InvalidMidiDataException, InterruptedException {
		System.out.println("Mozart Digital Composer v0.1 (C)Copyright 2017 Manny Peterson All Rights Reserved");

		MozartScale scale = new MozartScale(MozartScaleType.MAJOR, MozartPitchType.C);
		
		
		MozartPhrase phrase1 = new MozartPhrase(scale, MozartOctaveType.SIXTH, 100);
		MozartPhrase phrase2 = new MozartPhrase(scale, MozartOctaveType.SIXTH, 100);
		MozartPhrase phrase3 = new MozartPhrase(scale, MozartOctaveType.SIXTH, 100);
		MozartPhrase phrase4 = new MozartPhrase(scale, MozartOctaveType.SIXTH, 100);
		MozartPhrase phrase5 = new MozartPhrase(scale, MozartOctaveType.SIXTH, 100);
		MozartPhrase phrase6 = new MozartPhrase(scale, MozartOctaveType.SIXTH, 100);

		MozartArrangement arrange = new MozartArrangement(10);
		
		arrange.addPhrase(phrase1);
		arrange.addPhrase(phrase2);
		arrange.addPhrase(phrase3);
		arrange.addPhrase(phrase4);
		arrange.addPhrase(phrase5);
		arrange.addPhrase(phrase6);
		
		System.out.println(arrange.getPhrases()[0].getPhrase().length);
		
		MozartInstrument inst = new MozartInstrument();



	}
}
