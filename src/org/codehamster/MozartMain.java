package org.codehamster;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

public class MozartMain {
	public static void main(String[] args) {
		System.out.println("Mozart Digital Composer v0.1 (C)Copyright 2017 Manny Peterson All Rights Reserved");
		
		MozartScale scale = new MozartScale(MozartScaleType.MAJOR, MozartPitchType.C);

		MozartPhrase phrase = new MozartPhrase(scale, MozartOctaveType.SIXTH, 100);

		/*
		int i;
		int j;
		try {
			Synthesizer synth = MidiSystem.getSynthesizer();
			synth.open();
			MidiChannel[] channels = synth.getChannels();
			MidiChannel channel = channels.clone()[0];
			channel.allNotesOff();
			channel.allSoundOff();
			Thread.sleep(5000);
			for (i = 0; i < phrase.getLength(); i++) {
				System.out.println(phrase.getPhrase()[i]);
				channel.noteOn(phrase.getPhrase()[i].getMIDINote(), 127);
				for (j = 0; j < phrase.getPhrase()[i].getLength().getValue(); j++) {
					Thread.sleep(25);
				}
				channel.noteOff(phrase.getPhrase()[i].getMIDINote());
			}
		} catch (MidiUnavailableException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
*/
	}
}
