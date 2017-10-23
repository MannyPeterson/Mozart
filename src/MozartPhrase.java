
import java.util.Random;

public class MozartPhrase {
	private final static int[][] patterns = { { 1 }, { -1 }, { 1, 1 }, { -1, -1 }, { 1, 1, 1 }, { -1, -1, -1 },
			{ 1, 1, 1, 1 }, { -1, -1, -1, -1 }, { 1, -1 }, { -1, 1 }, { 3 }, { -3 }, { 4 }, { -4 }, { 5 }, { -5 },
			{ 6 }, { -6 } };
	private int key;
	private int octave;
	private MozartNote[] phrase;
	private int[] scale;

	public MozartPhrase(MozartScale scale, int octave) throws MozartRuntimeException {
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
		MozartNote[] phraseTemp = null;
		Random rand = null;
		int i = 0;
		boolean skip = false;
		int note = 0;
		int patternIndex = 0;
		int patternsIndex = 0;
		int scaleIndex = 0;
		int scaleIndexTemp = 0;
		int startNote = 0;
		try {
			phraseTemp = new MozartNote[100];
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
				patternsIndex = rand.nextInt(MozartPhrase.patterns.length);
				for (patternIndex = 0; patternIndex < MozartPhrase.patterns[patternsIndex].length; patternIndex++) {
					scaleIndexTemp += MozartPhrase.patterns[patternsIndex][patternIndex];
					if (scaleIndexTemp < 0 | scaleIndexTemp > this.scale.length - 1) {
						skip = true;
						break;
					} else {
						note = this.scale[scaleIndexTemp];
						if (note < startNote - 36 | note > startNote + 36) {
							skip = true;
							break;
						}
					}
				}
				if (!skip) {
					for (patternIndex = 0; patternIndex < MozartPhrase.patterns[patternsIndex].length; patternIndex++) {
						scaleIndex += MozartPhrase.patterns[patternsIndex][patternIndex];
						note = this.scale[scaleIndex];
						phraseTemp[i] = new MozartNote(MozartNote.TYPE_NORMAL, note, 34);
						i += 1;
						if (i > phraseTemp.length - 1) {
							break;
						}
						System.out.print(">");
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

	public MozartNote[] getPhrase() {
		return this.phrase;
	}

	public int[] getScale() {
		return this.scale;
	}

	private void setKey(MozartScale scale) throws MozartRuntimeException {
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