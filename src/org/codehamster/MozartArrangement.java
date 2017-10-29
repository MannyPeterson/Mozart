package org.codehamster;

public class MozartArrangement {
	private MozartPhrase[] arrangement;
	private int length;

	public MozartArrangement(int length) throws MozartRuntimeException {
		try {
			this.setLength(length);
			return;
		} catch (MozartRuntimeException e) {
			throw new MozartRuntimeException(e);
		}
	}

	public void addPhrase(MozartPhrase phrase) throws MozartRuntimeException {
		MozartPhrase[] newArrangement;
		int newArrangementIndex;
		try {
			if (phrase == null) {
				throw new MozartRuntimeException(this.getClass().getName() + ": phrase is null.");
			}
			if (this.getArrangement() == null) {
				this.setArrangement(new MozartPhrase[1]);
				this.getArrangement()[0] = phrase;
			} else {
				newArrangementIndex = 0;
				newArrangement = new MozartPhrase[this.getArrangement().length + 1];
				for (MozartPhrase phraseCopy : this.getArrangement()) {
					newArrangement[newArrangementIndex] = phraseCopy;
					newArrangementIndex += 1;
				}
				newArrangement[newArrangementIndex] = phrase;
				this.setArrangement(newArrangement);
			}
			return;
		} catch (MozartRuntimeException e) {
			throw new MozartRuntimeException(e);
		}
	}

	public MozartPhrase[] getArrangement() {
		return this.arrangement;
	}

	public int getLength() {
		return this.length;
	}

	private void setArrangement(MozartPhrase[] arrangement) throws MozartRuntimeException {
		try {
			if (arrangement == null) {
				throw new MozartRuntimeException(this.getClass().getName() + ": arrangement is null.");
			}
			this.arrangement = arrangement;
			return;
		} catch (MozartRuntimeException e) {
			throw new MozartRuntimeException(e);

		}
	}

	private void setLength(int length) throws MozartRuntimeException {
		try {
			if (length < 1) {
				throw new MozartRuntimeException(this.getClass().getName() + ": length is zero or less.");
			}
			this.length = length;
			return;
		} catch (MozartRuntimeException e) {
			throw new MozartRuntimeException(e);
		}
	}
}
