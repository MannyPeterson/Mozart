package org.codehamster;

import java.util.ArrayList;

public class MozartScale {
	MozartPitchType key;
	MozartScaleType scale;
	MozartStepType[][] step = {
			{ MozartStepType.WHOLE, MozartStepType.WHOLE, MozartStepType.HALF, MozartStepType.WHOLE,
					MozartStepType.WHOLE, MozartStepType.WHOLE, MozartStepType.HALF },
			{ MozartStepType.WHOLE, MozartStepType.HALF, MozartStepType.WHOLE, MozartStepType.WHOLE,
					MozartStepType.HALF, MozartStepType.WHOLE, MozartStepType.WHOLE } };

	public MozartScale(MozartScaleType scale, MozartPitchType key) throws MozartRuntimeException {
		try {
			this.setScale(scale);
			this.setKey(key);
			this.create();
		} catch (MozartRuntimeException e) {
			throw new MozartRuntimeException(e);
		}
	}

	private void create() throws MozartRuntimeException {
		ArrayList<MozartNote> allNotes;
		boolean firstNote;
		try {
			allNotes = new ArrayList<MozartNote>();
			firstNote = true;
			int i = 0;
			int j = 0;
			for (MozartOctaveType octave : MozartOctaveType.values()) {
				for (MozartPitchType pitch : MozartPitchType.values()) {
					if (firstNote & pitch == this.getKey()) {
						allNotes.add(new MozartNote(MozartNoteType.PITCH, pitch, octave, MozartLengthType.QUARTER));
						j = this.step[this.getScale().getValue()][i].getValue();
						i = (i + 1) % this.step[this.getScale().getValue()].length;
						firstNote = false;
					} else if (!firstNote & j == 0) {
						allNotes.add(new MozartNote(MozartNoteType.PITCH, pitch, octave, MozartLengthType.QUARTER));
						j = this.step[this.getScale().getValue()][i].getValue();
					} else if (!firstNote & j > 0) {
						j -= 1;
					}
				}
			}
		} catch (MozartRuntimeException e) {
			throw new MozartRuntimeException(e);
		}
	}

	public MozartPitchType getKey() {
		return this.key;
	}

	public MozartScaleType getScale() {
		return this.scale;
	}

	private void setKey(MozartPitchType key) throws MozartRuntimeException {
		try {
			if (key == null) {
				throw new MozartRuntimeException(this.getClass().getName() + ": key is null.");
			}
			this.key = key;
			return;
		} catch (MozartRuntimeException e) {
			throw new MozartRuntimeException(e);
		}
	}

	private void setScale(MozartScaleType scale) throws MozartRuntimeException {
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
