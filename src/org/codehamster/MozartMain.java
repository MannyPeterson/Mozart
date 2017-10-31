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

import java.util.Random;

import javax.sound.midi.InvalidMidiDataException;

public class MozartMain {
	public static void main(String[] args) {
		Random random;
		MozartScale scale;
		MozartArrangement arrangement;
		MozartInstrument instrument;
		MozartFrame frame;
		try {
			frame = new MozartFrame();
			frame.setVisible(true);
			random = new Random(System.currentTimeMillis());
			scale = new MozartScale(MozartScaleType.MAJOR, MozartPitchType.D_SHARP_E_FLAT);
			arrangement = new MozartArrangement(10);
			instrument = new MozartInstrument();
			for (int i = 0; i < 6; i++) {
				arrangement.addPhrase(new MozartPhrase(scale, MozartOctaveType.SIXTH, random, 60));
			}
			arrangement.create();
			instrument.open();
			for (MozartPhrase phrase : arrangement.getArrangement()) {
				instrument.play(phrase, frame);
			}
			instrument.close();
			return;
		} catch (MozartRuntimeException e) {
			throw new MozartRuntimeException(e);
		} catch (InvalidMidiDataException e) {
			throw new MozartRuntimeException(e);
		} catch (InterruptedException e) {
			throw new MozartRuntimeException(e);
		}
	}
}
