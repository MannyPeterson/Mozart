package org.codehamster;

public class MozartPhrase {
	private MozartScale scale;

	public MozartPhrase(MozartScale scale) throws MozartRuntimeException {
		try {
			this.setScale(scale);
			return;
		} catch (MozartRuntimeException e) {
			throw new MozartRuntimeException(e);
		}
	}

	public MozartScale getScale() {
		return this.scale;
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
