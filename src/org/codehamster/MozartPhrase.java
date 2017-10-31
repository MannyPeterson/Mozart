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

import java.util.ArrayList;
import java.util.Random;

public class MozartPhrase {
	private int length;
	private MozartOctaveType octave;
	private int[][] pattern = { { 6, 2, 5, 1, 4, 0, -6, -4, -2, 0, 2, 4, 6, 2, 5, 1, 4, 0, 0, 0 },
			{ -8, -3, 2, 6, 9, 11, 9, 6, 2, -3, -8, 11, 6, 1, -3, -6, -7, -6, -3, 2 },
			{ 0, -4, -8, -6, -8, -4, -9, -7, -9, -5, -1, 3, 7, 9, 7, 7, 5, 3, 3, 5 },
			{ 6, 2, 5, 1, 4, 0, 3, -1, 2, -2, 1, -3, 0, -4, -1, -5, -2, -6, -4, -2 },
			{ -6, 6, 5, 4, 3, 2, 1, 0, 6, -6, -5, -4, -3, -2, -1, 0, 2, 4, 6, -6 },
			{ 11, 8, 11, 10, 7, 10, 5, 8, 10, 10, 8, 5, 10, 7, 4, 4, 7, 4, 10, 2 },
			{ 5, 5, 0, 0, 4, 4, -1, -1, 3, 3, -2, -2, -5, -3, -1, -3, -1, 1, 3, 3 },
			{ 7, 10, 4, 7, 1, 4, -2, 1, -5, -2, -1, 0, 1, -2, 4, 1, 7, 4, 10, 10 },
			{ -2, 1, 0, 8, -3, -4, 3, 2, 8, 7, 1, -1, -3, -2, -1, -5, 0, -7, 5, 7 },
			{ -3, -3, 6, -3, -3, 6, -6, -4, -2, 0, 2, -5, -5, 5, -5, -5, 5, 2, 0, -2, -1 },
			{ 4, 5, 6, 4, 3, 2, -1, 0, 1, -1, -2, -3, 6, 0, 5, -1, 1, 1, -2, 3 },
			{ 5, 4, 5, 3, -6, 4, 5, 4, 6, -6, 2, 1, 2, 0, -6, 1, 2, 1, 3, -6 },
			{ -3, -6, -8, -8, -6, -3, 1, 5, -1, 8, -1, -1, 10, 10, -1, -1, 7, -1, 4, 1 },
			{ 2, 6, 7, 2, 7, 6, -2, -4, -6, -6, -4, -2, 2, 6, 7, 2, 7, 6, -2, -4 },
			{ 11, 9, 11, 7, 9, 7, 6, 3, -1, -6, 11, 9, 11, 7, 9, 7, 6, 3, -1, -6 },
			{ -1, -3, 5, 6, 3, 0, 2, 4, -1, -3, 5, 6, -6, -3, 0, -2, -5, 6, -6, 0 },
			{ -5, -3, -4, -5, -6, 6, 5, 6, -5, -6, -5, -4, -3, 6, 5, -5, -3, -4, -5, -6 },
			{ 6, 1, -4, -5, -5, -4, 1, 6, -6, -1, 4, 5, 5, 4, -1, -6, 5, 4, -1, -6 },
			{ 4, 5, 6, 6, 0, 4, -1, 3, -2, 2, 6, 6, 5, 4, -6, -2, -5, -1, -4, 0 },
			{ 0, 2, 4, 11, 9, 11, 7, 4, 2, 0, 11, -6, 10, -7, 9, -8, 0, 2, 4, 11 },
			{ 11, 9, 10, 11, 6, 4, 5, 6, 8, 10, 11, 11, 9, 10, 10, 8, 9, 9, 7, 4 },
			{ 11, 9, 10, 11, 10, 9, 8, 6, 8, 10, 11, 11, 10, 8, 6, 8, 9, 10, 11, 10 },
			{ 5, 2, -6, 10, 11, -5, -3, -1, 11, 10, 2, 5, 1, 4, 10, 11, -1, -3, -5, 1 },
			{ -3, 0, 5, 10, -3, 0, 5, 10, -6, -3, 2, 7, -6, -3, 2, 7, 2, -3, -6, 0 },
			{ 4, 4, 6, 9, -1, -1, 1, 4, -1, 7, -1, 9, -1, 9, -1, 7, -1, 4, 1, -1 },
			{ 3, 6, 11, 8, 10, 7, 9, 6, 3, -1, -5, -9, -5, -1, 3, 6, 9, 7, 10, 8 },
			{ -6, -4, -6, 6, 0, 6, 5, 6, -6, 0, -6, -5, -6, -4, -2, 0, 6, 4, 2, 0 } };
	private MozartNote[] phrase;
	private Random random;
	private MozartScale scale;

	public MozartPhrase(MozartScale scale, MozartOctaveType octave, Random random, int length)
			throws MozartRuntimeException {
		try {
			this.setScale(scale);
			this.setOctave(octave);
			this.setRandom(random);
			this.setLength(length);
			this.create();
			return;
		} catch (MozartRuntimeException e) {
			throw new MozartRuntimeException(e);
		}
	}

	private void create() throws MozartRuntimeException {
		ArrayList<MozartNote> phrase;
		MozartNote patternNote;
		int scaleIndex;
		int patternsIndex;
		int patternIndex;
		int rootNoteIndex;
		try {
			scaleIndex = 0;
			rootNoteIndex = 0;
			patternsIndex = 0;
			patternIndex = 0;
			phrase = new ArrayList<MozartNote>();
			for (MozartNote scaleNote : this.getScale().getScale()) {
				if (scaleNote.getPitch() == this.getScale().getRoot() & scaleNote.getOctave() == this.getOctave()) {
					rootNoteIndex = scaleIndex;
					break;
				}
				scaleIndex += 1;
			}
			for (int i = 0; i < this.getLength(); i++) {
				patternsIndex = this.getRandom().nextInt(this.getPattern().length);
				for (patternIndex = 0; patternIndex < this.getPattern()[patternsIndex].length; patternIndex++) {
					patternNote = this.getScale().getScale()[rootNoteIndex
							+ this.getPattern()[patternsIndex][patternIndex]];
					phrase.add(new MozartNote(MozartNoteType.PITCH, patternNote.getPitch(), patternNote.getOctave(),
							MozartLengthType.QUARTER));
					i += 1;
				}
			}
			this.setPhrase(new MozartNote[phrase.size()]);
			this.setPhrase(phrase.toArray(this.getPhrase()));
			return;
		} catch (MozartRuntimeException e) {
			throw new MozartRuntimeException(e);
		}
	}

	public int getLength() {
		return this.length;
	}

	public MozartOctaveType getOctave() {
		return this.octave;
	}

	private int[][] getPattern() {
		return this.pattern;
	}

	public MozartNote[] getPhrase() {
		return this.phrase;
	}

	private Random getRandom() {
		return this.random;
	}

	private MozartScale getScale() {
		return this.scale;
	}

	private void setLength(int length) throws MozartRuntimeException {
		try {
			if (length < 1) {
				throw new MozartRuntimeException(this.getClass().getName() + ": length is zero or less.");
			}
			this.length = length;
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

	private void setPhrase(MozartNote[] phrase) throws MozartRuntimeException {
		try {
			if (phrase == null) {
				throw new MozartRuntimeException(this.getClass().getName() + ": phrase is null.");
			}
			this.phrase = phrase;
			return;
		} catch (MozartRuntimeException e) {
			throw new MozartRuntimeException(e);
		}
	}

	private void setRandom(Random random) throws MozartRuntimeException {
		try {
			if (random == null) {
				throw new MozartRuntimeException(this.getClass().getName() + ": random is null.");
			}
			this.random = random;
			return;
		} catch (MozartRuntimeException e) {
			throw new MozartRuntimeException(e);
		}
	}

	private void setScale(MozartScale scale) throws MozartRuntimeException {
		try {
			if (scale == null) {
				throw new MozartRuntimeException(this.getClass().getName() + ": scale is null.");
			}
			this.scale = scale;
			return;
		} catch (MozartRuntimeException e) {
			throw new MozartRuntimeException(e);
		}
	}
}
