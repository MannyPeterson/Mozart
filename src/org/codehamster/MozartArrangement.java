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

public class MozartArrangement {
	private MozartPhrase[] arrangement;
	private int length;
	private MozartPhrase[] phrases;

	public MozartArrangement(int length) throws MozartRuntimeException {
		try {
			this.setLength(length);
			return;
		} catch (MozartRuntimeException e) {
			throw new MozartRuntimeException(e);
		}
	}

	public void addPhrase(MozartPhrase phrase) throws MozartRuntimeException {
		MozartPhrase[] newPhrases;
		int newPhrasesIndex;
		try {
			if (phrase == null) {
				throw new MozartRuntimeException(this.getClass().getName() + ": phrase is null.");
			}
			if (this.getPhrases() == null) {
				this.setPhrases(new MozartPhrase[1]);
				this.getPhrases()[0] = phrase;
			} else {
				newPhrasesIndex = 0;
				newPhrases = new MozartPhrase[this.getPhrases().length + 1];
				for (MozartPhrase phraseCopy : this.getPhrases()) {
					newPhrases[newPhrasesIndex] = phraseCopy;
					newPhrasesIndex += 1;
				}
				newPhrases[newPhrasesIndex] = phrase;
				this.setPhrases(newPhrases);
			}
			return;
		} catch (MozartRuntimeException e) {
			throw new MozartRuntimeException(e);
		}
	}

	public void create() throws MozartRuntimeException {
		ArrayList<MozartPhrase> arrangement;
		int i;
		int j;
		try {
			arrangement = new ArrayList<MozartPhrase>();
			i = 0;
			j = 0;
			for (i = 0; i < this.getLength(); i++) {
				arrangement.add(this.getPhrases()[j]);
				j = (j + 1) % this.getPhrases().length;
			}
			this.setArrangement(new MozartPhrase[arrangement.size()]);
			this.setArrangement(arrangement.toArray(this.getArrangement()));
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

	public MozartPhrase[] getPhrases() {
		return this.phrases;
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

	private void setPhrases(MozartPhrase[] phrases) throws MozartRuntimeException {
		try {
			if (phrases == null) {
				throw new MozartRuntimeException(this.getClass().getName() + ": phrases is null.");
			}
			this.phrases = phrases;
			return;
		} catch (MozartRuntimeException e) {
			throw new MozartRuntimeException(e);
		}
	}
}
