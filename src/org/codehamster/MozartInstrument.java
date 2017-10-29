/*
 * 
 *  Mozart Digital Composer Version 0.1
 *  Copyright (C) 2017 Manny Peterson <me@mannypeterson.com>
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <https://www.gnu.org/licenses/>.
 * 
 */
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
	private MidiDevice midiDevice;
	private Receiver midiReceiver;
	private MozartInstrumentNote[] notes;

	public MozartInstrument() throws MozartRuntimeException {
		try {
			this.init();
			return;
		} catch (MozartRuntimeException e) {
			throw new MozartRuntimeException(e);
		}
	}

	public void close() throws MozartRuntimeException {
		try {
			Thread.sleep(5000);
			this.midiReceiver.close();
			this.midiDevice.close();
			return;
		} catch (InterruptedException e) {
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

	public void open() throws MozartRuntimeException {
		Info[] midiDevicesInfo;
		try {
			midiDevicesInfo = MidiSystem.getMidiDeviceInfo();
			this.midiDevice = MidiSystem.getMidiDevice(midiDevicesInfo[0]);
			this.midiDevice.open();
			this.midiReceiver = midiDevice.getReceiver();
			Thread.sleep(5000);
			return;
		} catch (InterruptedException e) {
			throw new MozartRuntimeException(e);
		} catch (MidiUnavailableException e) {
			throw new MozartRuntimeException(e);
		}
	}

	public void play(MozartPhrase phrase) throws InvalidMidiDataException,
			InterruptedException { /* Bypassing getters and/or setters for performance */
		int length;
		for (MozartNote phraseNote : phrase.getPhrase()) {
			for (MozartInstrumentNote instNote : this.notes) {
				instNote.play(phraseNote);
			}
			for (length = 0; length < phraseNote.getLength().getValue(); length++) {
				for (MozartInstrumentNote instNote : this.notes) {
					if (instNote.stateChanged()) {
						if (instNote.isOn()) {
							this.midiReceiver
									.send(new ShortMessage(ShortMessage.NOTE_ON, 0, instNote.getMIDINote(), 127), -1);
						} else {
							this.midiReceiver
									.send(new ShortMessage(ShortMessage.NOTE_OFF, 0, instNote.getMIDINote(), 127), -1);
						}
					}
					instNote.tick();
				}
				Thread.sleep(25);
			}
		}
		return;
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
