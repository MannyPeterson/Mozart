package org.codehamster;

public enum MozartOctaveType {
	FIRST("1ST", 0),
	SECOND("2ND", 1),
	THIRD("3RD", 2),
	FOURTH("4TH", 3),
	FIFTH("5TH", 4),
	SIXTH("6TH", 5),
	SEVENTH("7TH", 6),
	EIGHTH("8TH", 7),
	NINTH("9TH", 8),
	TENTH("10TH", 9),
	ELEVENTH("11TH", 10);
	
	private String string;
	private int value;

	private MozartOctaveType(String note, int value) throws MozartRuntimeException {
		try {
			this.setString(note);
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
			if (value < 0 | value > 10) {
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
