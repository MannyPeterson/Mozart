package org.codehamster;

import javax.sound.midi.InvalidMidiDataException;

public class MozartMain {
	public static void main(String[] args) throws InvalidMidiDataException, InterruptedException {
		System.out.println("Mozart Digital Composer v0.1 (C)Copyright 2017 Manny Peterson All Rights Reserved");

		MozartScale scale = new MozartScale(MozartScaleType.MAJOR, MozartPitchType.C);

		MozartPhrase phrase = new MozartPhrase(scale, MozartOctaveType.SIXTH, 10);

		MozartInstrument inst = new MozartInstrument();

		inst.play(phrase);

	}
}
