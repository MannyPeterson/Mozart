import java.util.Arrays;

import org.codehamster.MozartRuntimeException;

public class MozartScaleOld {
	
	public final static String[] keys = { "C", "C#/Db", "D", "D#/Eb", "E", "F", "F#/Gb", "G", "G#/Ab", "A", "A#/Bb",
			"B" };
	public final static int SCALE_MAJOR = 0;
	public final static int SCALE_MINOR = 1;
	private final static int[][] scaleInterval = { { 2, 2, 1, 2, 2, 2, 1 }, { 2, 1, 2, 2, 1, 2, 2 } };
	private int key;
	private int[] scale;
	private int type;

	public MozartScaleOld(String key, int type) throws MozartRuntimeException {
		try {
			this.setKey(key);
			this.setType(type);
			this.create();
			return;
		} catch (MozartRuntimeException e) {
			throw new MozartRuntimeException(e);
		}
	}

	private void create() throws MozartRuntimeException {
		int note = 0;
		int scaleIntervalIndex = 0;
		int[] scaleTemp = null;
		int scaleTempIndex = 0;
		try {
			scaleTemp = new int[128];
			note = this.getKey();
			do {
				scaleTemp[scaleTempIndex] = note;
				note = note + MozartScaleOld.scaleInterval[this.getType()][scaleIntervalIndex];
				scaleIntervalIndex = (scaleIntervalIndex + 1) % MozartScaleOld.scaleInterval[this.getType()].length;
				scaleTempIndex += 1;
			} while (note < 128);
			this.setScale(Arrays.copyOf(scaleTemp, scaleTempIndex)); 
			return;
		} catch (MozartRuntimeException e) {
			throw new MozartRuntimeException(e);
		}
	}

	public int getKey() {
		return this.key;
	}

	public int[] getScale() {
		return this.scale;
	}

	public int getType() {
		return this.type;
	}

	private void setKey(String key) throws MozartRuntimeException {
		boolean foundKey = false;
		int i = 0;
		try {
			if (key == null) {
				throw new MozartRuntimeException(this.getClass().getName() + ": key is null.");
			}
			for (i = 0; i < MozartScaleOld.keys.length; i++) {
				if (MozartScaleOld.keys[i].equals(key)) {
					foundKey = true;
					break;
				}
			}
			if (foundKey == false) {
				throw new MozartRuntimeException(this.getClass().getName() + ": invalid scale key.");
			}
			this.key = i;
			return;
		} catch (MozartRuntimeException e) {
			throw new MozartRuntimeException(e);
		}
	}

	private void setScale(int[] scale) throws MozartRuntimeException {
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

	private void setType(int type) throws MozartRuntimeException {
		try {
			if (type < MozartScaleOld.SCALE_MAJOR | type > MozartScaleOld.SCALE_MINOR) {
				throw new MozartRuntimeException(this.getClass().getName() + ": invalid scale type.");
			}
			this.type = type;
			return;
		} catch (MozartRuntimeException e) {
			throw new MozartRuntimeException(e);
		}
	}
}
