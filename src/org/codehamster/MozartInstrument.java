package org.codehamster;

import java.util.ArrayList;

public class MozartInstrument {
	private MozartInstrumentNote[] notes;

	public MozartInstrument() throws MozartRuntimeException {
		try {
			this.init();
			return;
		} catch (MozartRuntimeException e) {
			throw new MozartRuntimeException(e);
		}
	}

	public void play(MozartPhrase phrase) throws MozartRuntimeException {
		try {
			if (phrase == null) {
				throw new MozartRuntimeException(this.getClass().getName() + ": phrase is null.");
			}
			return;
		} catch (MozartRuntimeException e) {
			throw new MozartRuntimeException(e);
		}
	}

	private MozartInstrumentNote[] getNotes() {
		return this.notes;
	}

	private void init() throws MozartRuntimeException {
		ArrayList<MozartInstrumentNote> notes;
		try {
			notes = new ArrayList<MozartInstrumentNote>();
			for (MozartOctaveType octave : MozartOctaveType.values()) {
				for (MozartPitchType pitch : MozartPitchType.values()) {
					notes.add(new MozartInstrumentNote(
							new MozartNote(MozartNoteType.PITCH, pitch, octave, MozartLengthType.QUARTER), 64));
				}
			}
			this.setNotes(new MozartInstrumentNote[notes.size()]);
			this.setNotes(notes.toArray(this.getNotes()));
			return;
		} catch (MozartRuntimeException e) {
			throw new MozartRuntimeException(e);
		}
	}

	private void setNotes(MozartInstrumentNote[] notes) throws MozartRuntimeException {
		try {
			if (notes == null) {
				throw new MozartRuntimeException(this.getClass().getName() + ": notes is null.");
			}
			this.notes = notes;
			return;
		} catch (MozartRuntimeException e) {
			throw new MozartRuntimeException(e);
		}
	}
}
