package org.codehamster;

public class MozartNote {
	private MozartLengthType length;
	private MozartNoteType note;
	private MozartOctaveType octave;
	private MozartPitchType pitch;

	public MozartNote(MozartNoteType note, MozartPitchType pitch, MozartOctaveType octave, MozartLengthType length)
			throws MozartRuntimeException {
		try {
			this.setNote(note);
			this.setPitch(pitch);
			this.setOctave(octave);
			this.setLength(length);
			return;
		} catch (MozartRuntimeException e) {
			throw new MozartRuntimeException(e);
		}
	}

	public MozartLengthType getLength() {
		return this.length;
	}
/*
	public int getMIDINote() throws MozartRuntimeException {
		try {
			int retVal;
			retVal = (this.octave.getValue() * 12) + this.pitch.getValue();
			if (retVal < 0 | retVal > 127) {
				retVal = 0;
			}
			return retVal;
		} catch (MozartRuntimeException e) {
			throw new MozartRuntimeException(e);
		}
	}
*/
	public MozartNoteType getNote() {
		return this.note;
	}

	public MozartOctaveType getOctave() {
		return this.octave;
	}
	
	public MozartPitchType getPitch() {
		return this.pitch;
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

	private void setPitch(MozartPitchType pitch) throws MozartRuntimeException {
		try {
			if (pitch == null) {
				throw new MozartRuntimeException(this.getClass().getName() + ": note is null.");
			}
			this.pitch = pitch;
			return;
		} catch (MozartRuntimeException e) {
			throw new MozartRuntimeException(e);
		}
	}

	public String toString() throws MozartRuntimeException {
		try {
			StringBuilder retVal = new StringBuilder();
			if (this.note == MozartNoteType.REST) {
				retVal.append(this.getLength().toString());
				retVal.append(" REST");
			} else {
				retVal.append(this.getPitch().toString());
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
