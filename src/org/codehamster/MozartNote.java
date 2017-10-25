package org.codehamster;

public enum MozartNote {
	C("C", 0),
	C_SHARP("C#", 1),
	D_FLAT("Db", 1),
	D("D", 2),
	D_SHARP("D#", 3),	
	E_FLAT("Eb", 3),
	E("E", 4),
	F("F", 5),
	F_SHARP("F#", 6),
	G_FLAT("Gb", 6),
	G("G", 7),
	G_SHARP("G#", 8),
	A_FLAT("Ab", 8),
	A("A", 9),
	B_FLAT("Bb", 10),
	A_SHARP("A#", 10),
	B("B", 11);

	private String note;
	private int value;

	private MozartNote(String note, int value) throws MozartRuntimeException {
		try {
			this.setNote(note);
			this.setValue(value);
			return;
		} catch (MozartRuntimeException e) {
			throw new MozartRuntimeException(e);
		}
	}
	
	public String getNote() {
		return this.note;
	}

	public int getValue() {
		return this.value;
	}

	private void setNote(String note) throws MozartRuntimeException {
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
	
	private void setValue(int value) throws MozartRuntimeException {
		try {
			if (value < 0 | value > 11) {
				throw new MozartRuntimeException(this.getClass().getName() + ": note value out of range.");
			}
			this.value = value;
			return;
		} catch (MozartRuntimeException e) {
			throw new MozartRuntimeException(e);
		}
	}
}