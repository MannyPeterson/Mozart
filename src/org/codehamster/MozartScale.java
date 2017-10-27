package org.codehamster;

public class MozartScale {
	MozartPitchType key;

	MozartScaleType scale;
	MozartStepType[][] step = {
			{ MozartStepType.WHOLE, MozartStepType.WHOLE, MozartStepType.HALF, MozartStepType.WHOLE,
					MozartStepType.WHOLE, MozartStepType.WHOLE, MozartStepType.HALF },
			{ MozartStepType.WHOLE, MozartStepType.HALF, MozartStepType.WHOLE, MozartStepType.WHOLE,
					MozartStepType.HALF, MozartStepType.WHOLE, MozartStepType.WHOLE } };

	public MozartScale(MozartScaleType scale, MozartPitchType key) {
		try {
			this.setScale(scale);
			this.setKey(key);
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
