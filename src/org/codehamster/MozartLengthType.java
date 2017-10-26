package org.codehamster;

public enum MozartLengthType {
	SIXTEENTH("1/16", 2),
	EIGHTH("1/8", 4),
	QUARTER("1/4", 8),
	HALF("1/2", 16),
	WHOLE("1/1", 32);

	private String string;
	private int value;

	private MozartLengthType(String note, int value) throws MozartRuntimeException {
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
			if (value < 2 | value > 32) {
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
