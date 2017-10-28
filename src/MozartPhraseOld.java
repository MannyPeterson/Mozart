
import java.util.Random;

import org.codehamster.MozartRuntimeException;

public class MozartPhraseOld {
	private final static int[][] patterns = { {1},{2}};

	private final static int[] lengths = { 4, 8, 16, 32 };
	private int key;
	private int octave;
	private MozartNotes[] phrase;
	private int[] scale;

	public MozartPhraseOld(MozartScaleOld scale, int octave) throws MozartRuntimeException {
		try {
			this.setScale(scale);
			this.setKey(scale);
			this.setOctave(octave);
			this.create();
			return;
		} catch (MozartRuntimeException e) {
			throw new MozartRuntimeException(e);
		}
	}

	public void create() {
		MozartNotes[] phraseTemp = null;
		Random rand = null;
		int i = 0;
		boolean skip = false;
		int note = 0;
		int length = 0;
		int patternIndex = 0;
		int patternsIndex = 0;
		int scaleIndex = 0;
		int scaleIndexTemp = 0;
		int startNote = 0;
		try {
			phraseTemp = new MozartNotes[100];
			rand = new Random();
			startNote = (this.getKey() + 60) + (octave * 12);
			for (i = 0; i < this.getScale().length; i++) {
				if (this.getScale()[i] >= startNote) {
					scaleIndex = i;
					break;
				}
			}
			i = 0;
			do {
				skip = false;
				scaleIndexTemp = scaleIndex;
				patternsIndex = rand.nextInt(MozartPhraseOld.patterns.length);
				for (patternIndex = 0; patternIndex < MozartPhraseOld.patterns[patternsIndex].length; patternIndex++) {
					scaleIndexTemp += MozartPhraseOld.patterns[patternsIndex][patternIndex];
					if (scaleIndexTemp < 0 | scaleIndexTemp > this.scale.length - 1) {
						skip = true;
						break;
					} else {
						note = this.scale[scaleIndexTemp];
						if (note < startNote - 24 | note > startNote + 36) {
							skip = true;
							break;
						}
					}
				}
				length = 8;
				if (!skip) {
					for (patternIndex = 0; patternIndex < MozartPhraseOld.patterns[patternsIndex].length; patternIndex++) {
						scaleIndex += MozartPhraseOld.patterns[patternsIndex][patternIndex];
						note = this.scale[scaleIndex];
						phraseTemp[i] = new MozartNotes(MozartNotes.TYPE_NORMAL, note, length);
						i += 1;
						if (i > phraseTemp.length - 1) {
							break;
						}
					}
				}
			} while (i < phraseTemp.length);
			this.setPhrase(phraseTemp);
			return;
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}

	public int getKey() {
		return this.key;
	}

	public int getOctave() {
		return this.octave;
	}

	public MozartNotes[] getPhrase() {
		return this.phrase;
	}

	public int[] getScale() {
		return this.scale;
	}

	private void setKey(MozartScaleOld scale) throws MozartRuntimeException {
		try {
			if (scale == null) {
				throw new MozartRuntimeException(this.getClass().getName() + ": scale is null.");
			}
			if (scale.getKey() < 0 | scale.getKey() > 127) {
				throw new MozartRuntimeException(this.getClass().getName() + ": key out of range.");
			}
			this.key = scale.getKey();
			return;
		} catch (MozartRuntimeException e) {
			throw new MozartRuntimeException(e);
		}
	}

	private void setOctave(int octave) throws MozartRuntimeException {
		try {
			if (octave < -3 | octave > 3) {
				throw new MozartRuntimeException(this.getClass().getName() + ": octave out of range.");
			}
			this.octave = octave;
		} catch (MozartRuntimeException e) {
			throw new MozartRuntimeException(e);
		}
	}

	private void setPhrase(MozartNotes[] phrase) throws MozartRuntimeException {
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

	private void setScale(MozartScaleOld scale) throws MozartRuntimeException {
		try {
			if (scale == null) {
				throw new MozartRuntimeException(this.getClass().getName() + ": scale is null.");
			}
			if (scale.getScale() == null) {
				throw new MozartRuntimeException(this.getClass().getName() + ": scale is null.");
			}
			this.scale = scale.getScale();
			return;
		} catch (MozartRuntimeException e) {
			throw new MozartRuntimeException(e);
		}
	}
}