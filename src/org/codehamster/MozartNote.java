package org.codehamster;

public class MozartNote {
	private MozartLengthType length;
	private MozartNoteType note;
	private MozartOctaveType octave;
	private boolean rest;

	public MozartNote(MozartNoteType note, MozartOctaveType octave) throws MozartRuntimeException {
		try {
			this.setNote(note);
			this.setOctave(octave);
			// this.setLength(null); never set length in this constructor.
			this.setRest(false);
			return;
		} catch (MozartRuntimeException e) {
			throw new MozartRuntimeException(e);
		}
	}

	public MozartNote(MozartNoteType note, MozartOctaveType octave, MozartLengthType length)
			throws MozartRuntimeException {
		try {
			this.setNote(note);
			this.setOctave(octave);
			this.setLength(length);
			this.setRest(false);
			return;
		} catch (MozartRuntimeException e) {
			throw new MozartRuntimeException(e);
		}
	}

	public MozartNote(MozartNoteType note, MozartOctaveType octave, MozartLengthType length, boolean rest)
			throws MozartRuntimeException {
		try {
			this.setNote(note);
			this.setOctave(octave);
			this.setLength(length);
			this.setRest(rest);
			return;
		} catch (MozartRuntimeException e) {
			throw new MozartRuntimeException(e);
		}
	}

	public MozartLengthType getLength() {
		return this.length;
	}

	public int getMIDI() throws MozartRuntimeException {
		try {
			int retVal;
			retVal = (this.octave.getValue() * 12) + this.note.getValue();
			if (retVal < 0 | retVal > 127) {
				retVal = 0;
			}
			return retVal;
		} catch (MozartRuntimeException e) {
			throw new MozartRuntimeException(e);
		}
	}

	public MozartNoteType getNote() {
		return this.note;
	}

	public MozartOctaveType getOctave() {
		return this.octave;
	}

	public boolean isRest() {
		return this.rest;
	}

	private void setLength(MozartLengthType length) throws MozartRuntimeException {
		try {
			if (length == null) {
				throw new MozartRuntimeException(this.getClass().getName() + ": length is null.");
			}
			this.length = length;
			return;
		} catch (MozartRuntimeException e) {
			throw new MozartRuntimeException(e);
		}
	}

	private void setNote(MozartNoteType note) throws MozartRuntimeException {
		try {
			if (note == null) {
				throw new MozartRuntimeException(this.getClass().getName() + ": note is null.");
			}
			this.note = note;
			return;
		} catch (MozartRuntimeException e) {
			throw new MozartRuntimeException(e);
		}
	}

	private void setOctave(MozartOctaveType octave) throws MozartRuntimeException {
		try {
			if (octave == null) {
				throw new MozartRuntimeException(this.getClass().getName() + ": octave is null.");
			}
			this.octave = octave;
			return;
		} catch (MozartRuntimeException e) {
			throw new MozartRuntimeException(e);

		}
	}

	private void setRest(boolean rest) {
		this.rest = rest;
	}

	public String toString() throws MozartRuntimeException {
		try {
			StringBuilder retVal = new StringBuilder();
			if (this.isRest()) {
				retVal.append(this.getLength().toString());
				retVal.append(" REST");
			} else {
				retVal.append(this.getNote().toString());
				retVal.append(" ");
				retVal.append(this.getOctave().toString());
				if (this.getLength() != null) {
					retVal.append(" ");
					retVal.append(this.getLength().toString());
				}
			}
			return retVal.toString();
		} catch (MozartRuntimeException e) {
			throw new MozartRuntimeException(e);
		}
	}
}
