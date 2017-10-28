package org.codehamster;

import java.util.ArrayList;

public class MozartPhrase {
	private MozartOctaveType octave;
	private MozartNote[] phrase;
	private MozartScale scale;

	public MozartPhrase(MozartScale scale, MozartOctaveType octave) throws MozartRuntimeException {
		try {
			this.setScale(scale);
			this.setOctave(octave);
			this.create();
			return;
		} catch (MozartRuntimeException e) {
			throw new MozartRuntimeException(e);
		}
	}

	private void create() throws MozartRuntimeException {
		ArrayList<MozartNote> phrase;
		try {
			phrase = new ArrayList<MozartNote>();

			/*
			 * 
			 * Do the important stuff in here.
			 */
			
			this.setPhrase(new MozartNote[phrase.size()]);
			this.setPhrase(phrase.toArray(this.getPhrase()));
			return;
		} catch (MozartRuntimeException e) {
			throw new MozartRuntimeException(e);
		}
	}

	public MozartOctaveType getOctave() {
		return this.octave;
	}

	public MozartNote[] getPhrase() {
		return this.phrase;
	}

	public MozartScale getScale() {
		return this.scale;
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
