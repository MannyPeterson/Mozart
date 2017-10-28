package org.codehamster;

import java.util.ArrayList;

public class MozartScale {
	MozartPitchType root;
	MozartNote[] scale;
	MozartStepType[][] step = {
			{ MozartStepType.WHOLE, MozartStepType.WHOLE, MozartStepType.HALF, MozartStepType.WHOLE,
					MozartStepType.WHOLE, MozartStepType.WHOLE, MozartStepType.HALF },
			{ MozartStepType.WHOLE, MozartStepType.HALF, MozartStepType.WHOLE, MozartStepType.WHOLE,
					MozartStepType.HALF, MozartStepType.WHOLE, MozartStepType.WHOLE } };
	MozartScaleType type;

	public MozartScale(MozartScaleType type, MozartPitchType root) throws MozartRuntimeException {
		try {
			this.setType(type);
			this.setRoot(root);
			this.create();
		} catch (MozartRuntimeException e) {
			throw new MozartRuntimeException(e);
		}
	}

	private void create() throws MozartRuntimeException {
		ArrayList<MozartNote> scale;
		boolean foundRootNote;
		int stepIndex;
		int stepsRemaining;
		try {
			scale = new ArrayList<MozartNote>();
			foundRootNote = false;
			stepIndex = 0;
			stepsRemaining = 0;
			for (MozartOctaveType octave : MozartOctaveType.values()) {
				for (MozartPitchType pitch : MozartPitchType.values()) {
					if (!foundRootNote & pitch == this.getRoot()) {
						scale.add(new MozartNote(MozartNoteType.PITCH, pitch, octave, MozartLengthType.QUARTER));
						stepsRemaining = this.getStep()[this.getType().getValue()][stepIndex].getValue() - 1;
						stepIndex = (stepIndex + 1) % this.step[this.getType().getValue()].length;
						foundRootNote = true;
					} else if (foundRootNote & stepsRemaining == 0) {
						scale.add(new MozartNote(MozartNoteType.PITCH, pitch, octave, MozartLengthType.QUARTER));
						stepsRemaining = this.getStep()[this.getType().getValue()][stepIndex].getValue() - 1;
						stepIndex = (stepIndex + 1) % this.step[this.getType().getValue()].length;
					} else if (foundRootNote & stepsRemaining > 0) {
						stepsRemaining -= 1;
					}
				}
			}
			this.setScale(new MozartNote[scale.size()]);
			this.setScale(scale.toArray(this.getScale()));
			return;
		} catch (MozartRuntimeException e) {
			throw new MozartRuntimeException(e);
		}
	}

	public MozartPitchType getRoot() {
		return this.root;
	}

	public MozartNote[] getScale() {
		return this.scale;
	}

	private MozartStepType[][] getStep() {
		return this.step;
	}

	public MozartScaleType getType() {
		return this.type;
	}

	private void setRoot(MozartPitchType root) throws MozartRuntimeException {
		try {
			if (root == null) {
				throw new MozartRuntimeException(this.getClass().getName() + ": root is null.");
			}
			this.root = root;
			return;
		} catch (MozartRuntimeException e) {
			throw new MozartRuntimeException(e);
		}
	}

	private void setScale(MozartNote[] scale) throws MozartRuntimeException {
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

	private void setType(MozartScaleType type) throws MozartRuntimeException {
		try {
			if (type == null) {
				throw new MozartRuntimeException(this.getClass().getName() + ": type is null.");
			}
			this.type = type;
			return;
		} catch (MozartRuntimeException e) {
			throw new MozartRuntimeException(e);
		}
	}

}
