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

	private void setString(String string) throws MozartRuntimeException {
		try {
			if (string == null) {
				throw new MozartRuntimeException(this.getClass().getName() + ": string is null.");
			}
			this.string = string;
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
