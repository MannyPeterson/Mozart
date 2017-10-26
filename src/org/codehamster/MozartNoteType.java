package org.codehamster;

/*
 * 
 * Rename this class as MozartPitchType
 * 
 */

public enum MozartNoteType {
	C("C", 0),
	C_SHARP_D_FLAT("C#/Db", 1),
	D("D", 2),
	D_SHARP_E_FLAT("D#/Eb", 3),	
	E("E", 4),
	F("F", 5),
	F_SHARP_G_FLAT("F#/Gb", 6),
	G("G", 7),
	G_SHARP_A_FLAT("G#/Ab", 8),
	A("A", 9),
	A_SHARP_B_FLAT("A#/Bb", 10),
	B("B", 11);

	private String string;
	private int value;

	private MozartNoteType(String string, int value) throws MozartRuntimeException {
		try {
			this.setString(string);
			this.setValue(value);
			return;
		} catch (MozartRuntimeException e) {
			throw new MozartRuntimeException(e);
		}
	}
	
	public String getString() {
		return this.string;
	}

	public int getValue() {
		return this.value;
	}

	private void setString(String note) throws MozartRuntimeException {
		try {
			if (note == null) {
				throw new MozartRuntimeException(this.getClass().getName() + ": string is null.");
			}
			this.string = note;
			return;
		} catch (MozartRuntimeException e) {
			throw new MozartRuntimeException(e);
		}
	}
	
	private void setValue(int value) throws MozartRuntimeException {
		try {
			if (value < 0 | value > 11) {
				throw new MozartRuntimeException(this.getClass().getName() + ": value out of range.");
			}
			this.value = value;
			return;
		} catch (MozartRuntimeException e) {
			throw new MozartRuntimeException(e);
		}
	}

	public String toString() {
		return this.getString();
	}
}
