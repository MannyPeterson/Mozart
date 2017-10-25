package org.codehamster;

public enum MozartStep {
	HALF("HALF", 1),
	WHOLE("WHOLE", 2);

	private String string;
	private int value;

	private MozartStep(String string, int value) throws MozartRuntimeException {
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
			if (value < 0 | value > 2) {
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
