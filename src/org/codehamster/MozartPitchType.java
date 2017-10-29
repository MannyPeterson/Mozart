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

public enum MozartPitchType {
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

	private MozartPitchType(String string, int value) throws MozartRuntimeException {
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
