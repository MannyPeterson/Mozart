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

	private int getTicks() {
		return this.ticks;
	}

	private int getTTL() {
		return this.TTL;
	}

	public void play(MozartNote note) throws MozartRuntimeException {
		try {
			if (note == null) {
				throw new MozartRuntimeException(this.getClass().getName() + ": note is null.");
			}
			if (note.getNote() == MozartNoteType.PITCH) {
				if (this.getMIDINote() == ((note.getOctave().getValue() * 12) + note.getPitch().getValue())) {
					this.setStateChange(true);
					this.setTicks(this.getTTL());
				}
			}
			return;
		} catch (MozartRuntimeException e) {
			throw new MozartRuntimeException(e);
		}
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

	private void setTicks(int ticks) throws MozartRuntimeException {
		try {
			if (ticks < 0) {
				throw new MozartRuntimeException(this.getClass().getName() + ": ticks is less than zero.");
			}
			this.ticks = ticks;
			return;
		} catch (MozartRuntimeException e) {
			throw new MozartRuntimeException(e);
		}
	}

	private void setTTL(int TTL) {
		try {
			if (ticks < 0) {
				throw new MozartRuntimeException(this.getClass().getName() + ": TTL is less than zero.");
			}
			this.TTL = TTL;
			return;
		} catch (MozartRuntimeException e) {
			throw new MozartRuntimeException(e);
		}
	}

	public boolean stateChanged() {
		if (this.stateChange) {
			this.setStateChange(false);
			return true;
		}
		return false;
	}

	public void tick() {
		if (this.getTicks() > 0) {
			this.setTicks(this.getTicks() - 1);
			if (this.getTicks() == 0) {
				this.setStateChange(true);
			}
		}
		return;
	}
}
