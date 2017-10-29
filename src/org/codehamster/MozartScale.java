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

public class MozartScale {
	private MozartPitchType root;
	private MozartNote[] scale;
	private MozartStepType[][] step = {
			{ MozartStepType.WHOLE, MozartStepType.WHOLE, MozartStepType.HALF, MozartStepType.WHOLE,
					MozartStepType.WHOLE, MozartStepType.WHOLE, MozartStepType.HALF },
			{ MozartStepType.WHOLE, MozartStepType.HALF, MozartStepType.WHOLE, MozartStepType.WHOLE,
					MozartStepType.HALF, MozartStepType.WHOLE, MozartStepType.WHOLE } };
	private MozartScaleType type;

	public MozartScale(MozartScaleType type, MozartPitchType root) throws MozartRuntimeException {
		try {
			this.setType(type);
			this.setRoot(root);
			this.create();
			return;
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
