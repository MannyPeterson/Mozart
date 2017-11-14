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

public class MozartInstrumentNote {
	private int MIDINote;
	private boolean stateChange;
	private int ticks;
	private int TTL;

	public MozartInstrumentNote(MozartNote note, int TTL) throws MozartRuntimeException {
		try {
			this.setMIDINote(note);
			this.setTTL(TTL);
			this.setStateChange(false);
			return;
		} catch (MozartRuntimeException e) {
			throw new MozartRuntimeException(e);
		}
	}

	public int getMIDINote() {
		return this.MIDINote;
	}

	public boolean isOn() { /* Bypassing getters and/or setters for performance */
		if (this.ticks > 0) {
			return true;
		}
		return false;
	}

	public void play(MozartNote note) { /* Bypassing getters and/or setters for performance */
		if (this.MIDINote == ((note.getOctave().getValue() * 12) + note.getPitch().getValue())) {
			this.stateChange = true;
			this.ticks = this.TTL;
		}
		return;
	}

	private void setMIDINote(MozartNote note) throws MozartRuntimeException {
		try {
			if (note == null) {
				throw new MozartRuntimeException(this.getClass().getName() + ": note is null.");
			}
			this.MIDINote = (note.getOctave().getValue() * 12) + note.getPitch().getValue();
			return;
		} catch (MozartRuntimeException e) {
			throw new MozartRuntimeException(e);
		}
	}

	private void setStateChange(boolean state) {
		this.stateChange = state;
		return;
	}

	private void setTTL(int TTL) {
		try {
			if (TTL < 0) {
				throw new MozartRuntimeException(this.getClass().getName() + ": TTL is less than zero.");
			}
			this.TTL = TTL;
			return;
		} catch (MozartRuntimeException e) {
			throw new MozartRuntimeException(e);
		}
	}

	public boolean stateChanged() { /* Bypassing getters and/or setters for performance */
		if (this.stateChange) {
			this.stateChange = false;
			return true;
		}
		return false;
	}

	public void tick() { /* Bypassing getters and/or setters for performance */
		if (this.ticks > 0) {
			this.ticks -= 1;
			if (this.ticks == 0) {
				this.stateChange = true;
			}
		}
		return;
	}
}
