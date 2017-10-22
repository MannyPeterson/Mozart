import java.util.Arrays;
import java.util.Random;

public class MozartPhrase {
	public final static String[] keys = { "C", "C#/Db", "D", "D#/Eb", "E", "F", "F#/Gb", "G", "G#/Ab", "A", "A#/Bb",
			"B" };
	public final static int[][] patterns = { { 1 }, { -1 }, { 1, 1 }, { -1, -1 }, { 1, 1, 1 }, { -1, -1, -1 },
			{ 1, 1, 1, 1 }, { -1, -1, -1, -1 }, { 1, -1 }, { -1, 1 }, { 3 }, { -3 }, { 4 }, { -4 }, { 5 }, { -5 },
			{ 6 }, { -6 } };
	public final static int SCALE_MAJOR = 0;
	public final static int SCALE_MINOR = 1;
	public final static int OCTAVE_NORMAL = 0;
	public final static int OCTAVE_UP_ONE = 1;
	public final static int OCTAVE_DOWN_OWN = -1;
	public final static int OCTAVE_UP_TWO = 2;
	public final static int OCTAVE_DOWN_TWO = -2;
	public final static int[][] scales = { { 2, 2, 1, 2, 2, 2, 1 }, { 2, 1, 2, 2, 1, 2, 2 } };
	private int[] scale;

	public MozartPhrase(String key, int type) {
		scale = new int[128];
		this.setScale(key, type);
		return;
	}

	public MozartNote[] create(String key, int octave) {
		try {
			MozartNote[] phraseTemp = new MozartNote[100];
			Random rand = new Random();
			int i = 0;
			boolean skip = false;
			int note = 0;
			int patternIndex = 0;
			int patternsIndex = 0;
			int scaleIndex = 0;
			int scaleIndexTemp = 0;
			int startNote = 0;
			for (i = 0; i < MozartPhrase.keys.length; i++) {
				if (MozartPhrase.keys[i].equals(key)) {
					startNote = (i + 60) + (octave * 12);
					break;
				}
			}
			for (i = 0; i < this.scale.length; i++) {
				if (this.scale[i] >= startNote) {
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
			return phraseTemp;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private void setScale(String key, int type) {
		try {
			int[] scaleTemp = new int[128];
			int i = 0;
			int note = 0;
			int scalesIndex = 0;
			int scaleTempIndex = 0;
			for (i = 0; i < MozartPhrase.keys.length; i++) {
				if (MozartPhrase.keys[i].equals(key)) {
					note = i;
					break;
				}
			}
			if (type == MozartPhrase.SCALE_MAJOR) {
				do {
					scaleTemp[scaleTempIndex] = note;
					note = note + MozartPhrase.scales[MozartPhrase.SCALE_MAJOR][scalesIndex];
					scalesIndex = (scalesIndex + 1) % MozartPhrase.scales.length;
					scaleTempIndex += 1;
				} while (note < 128);
			} else if (type == MozartPhrase.SCALE_MINOR) {
				do {
					scaleTemp[scaleTempIndex] = note;
					note = note + MozartPhrase.scales[MozartPhrase.SCALE_MAJOR][scalesIndex];
					scalesIndex = (scalesIndex + 1) % MozartPhrase.scales.length;
					scaleTempIndex += 1;
				} while (note < 128);
			}
			this.scale = Arrays.copyOf(scaleTemp, scaleTempIndex);
			return;
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}
}