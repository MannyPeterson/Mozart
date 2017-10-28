package org.codehamster;

import java.util.ArrayList;
import java.util.Random;

public class MozartPhrase {
	private int length;
	private MozartOctaveType octave;
	private int[][] pattern = { { 1, 2, 3, 4, 5 }, { -1, -2, -3, -4, -5 }, { 0, 0, 0, 0, 0 } };
	private MozartNote[] phrase;
	private MozartScale scale;

	public MozartPhrase(MozartScale scale, MozartOctaveType octave, int length) throws MozartRuntimeException {
		try {
			this.setScale(scale);
			this.setOctave(octave);
			this.setLength(length);
			this.create();
			return;
		} catch (MozartRuntimeException e) {
			throw new MozartRuntimeException(e);
		}
	}

	private void create() throws MozartRuntimeException {
		ArrayList<MozartNote> phrase;
		Random random = new Random();
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
				patternsIndex = random.nextInt(this.getPattern().length);
				for (patternIndex = 0; patternIndex < this.getPattern()[patternsIndex].length; patternIndex++) {
					phrase.add(
							this.getScale().getScale()[rootNoteIndex + this.getPattern()[patternsIndex][patternIndex]]);
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

	public int[][] getPattern() {
		return this.pattern;
	}

	public MozartNote[] getPhrase() {
		return this.phrase;
	}

	public MozartScale getScale() {
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
