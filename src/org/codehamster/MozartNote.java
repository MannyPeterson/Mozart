package org.codehamster;

public class MozartNote {
	private MozartLengthType length;
	private MozartNoteType note;
	private MozartOctaveType octave;

	public MozartNote(MozartNoteType note, MozartOctaveType octave, MozartLengthType length) {
		try {
			this.setNote(note);
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

	public MozartNoteType getNote() {
		return this.note;
	}

	public MozartOctaveType getOctave() {
		return this.octave;
	}

	private void setLength(MozartLengthType length) throws MozartRuntimeException {
		try {
			if (length == null) {
				throw new MozartRuntimeException(this.getClass().getName() + ": length is null.");
			}
			this.length = length;
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
		} catch (MozartRuntimeException e) {
			throw new MozartRuntimeException(e);

		}
	}

	public String toString() {
		StringBuilder retVal = new StringBuilder();
		retVal.append(this.getOctave().toString());
		retVal.append(" ");
		retVal.append(this.getNote().toString());
		retVal.append(" ");
		retVal.append(this.getLength().toString());
		return retVal.toString();
	}
}
