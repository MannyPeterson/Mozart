package org.codehamster;

import java.util.ArrayList;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiDevice.Info;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;

public class MozartInstrument {
	private Receiver midiReceiver;
	private MozartInstrumentNote[] notes;

	public MozartInstrument() throws MozartRuntimeException {
		try {
			this.init();
			this.midi();
			return;
		} catch (MozartRuntimeException e) {
			throw new MozartRuntimeException(e);
		}
	}

	private MozartInstrumentNote[] getNotes() {
		return this.notes;
	}

	private void init() throws MozartRuntimeException {
		ArrayList<MozartInstrumentNote> notes;
		try {
			notes = new ArrayList<MozartInstrumentNote>();
			for (MozartOctaveType octave : MozartOctaveType.values()) {
				for (MozartPitchType pitch : MozartPitchType.values()) {
					notes.add(new MozartInstrumentNote(
							new MozartNote(MozartNoteType.PITCH, pitch, octave, MozartLengthType.QUARTER), 32));
				}
			}
			this.setNotes(new MozartInstrumentNote[notes.size()]);
			this.setNotes(notes.toArray(this.getNotes()));
			return;
		} catch (MozartRuntimeException e) {
			throw new MozartRuntimeException(e);
		}
	}

	private void midi() throws MozartRuntimeException {
		Info[] midiDevicesInfo;
		MidiDevice midiDevice;
		try {
			midiDevicesInfo = MidiSystem.getMidiDeviceInfo();
			midiDevice = MidiSystem.getMidiDevice(midiDevicesInfo[0]);
			midiDevice.open();
			this.midiReceiver = midiDevice.getReceiver();
			Thread.sleep(5000);
			return;
		} catch (InterruptedException e) {
			throw new MozartRuntimeException(e);
		} catch (MidiUnavailableException e) {
			throw new MozartRuntimeException(e);
		}
	}

	public void play(MozartPhrase phrase) throws MozartRuntimeException {
		int i;
		try {
			if (phrase == null) {
				throw new MozartRuntimeException(this.getClass().getName() + ": phrase is null.");
			}
			for (MozartNote phraseNote : phrase.getPhrase()) {
				for (MozartInstrumentNote instNote : this.getNotes()) {
					instNote.play(phraseNote);
				}
				for (i = 0; i < phraseNote.getLength().getValue(); i++) {
					for (MozartInstrumentNote instNote : this.getNotes()) {
						if (instNote.stateChanged()) {
							if (instNote.isOn()) {
								this.midiReceiver.send(
										new ShortMessage(ShortMessage.NOTE_ON, 0, instNote.getMIDINote(), 127), -1);
							} else {
								this.midiReceiver.send(
										new ShortMessage(ShortMessage.NOTE_OFF, 0, instNote.getMIDINote(), 127), -1);
							}
						}
						instNote.tick();
					}
					Thread.sleep(25);
				}
			}
			return;
		} catch (MozartRuntimeException e) {
			throw new MozartRuntimeException(e);
		} catch (InterruptedException e) {
			throw new MozartRuntimeException(e);
		} catch (InvalidMidiDataException e) {
			throw new MozartRuntimeException(e);
		}
	}

	private void setNotes(MozartInstrumentNote[] notes) throws MozartRuntimeException {
		try {
			if (notes == null) {
				throw new MozartRuntimeException(this.getClass().getName() + ": notes is null.");
			}
			this.notes = notes;
			return;
		} catch (MozartRuntimeException e) {
			throw new MozartRuntimeException(e);
		}
	}
}
