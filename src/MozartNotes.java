import org.codehamster.MozartRuntimeException;

public class MozartNotes {
	public static final int TYPE_NORMAL = 0;
	public static final int TYPE_REST = 1;
	private int length; /* Number of ticks that must elapse before next note in sequence is played */
	private int note;
	private int type;

	public MozartNotes(int type, int note, int length) throws MozartRuntimeException {
		try {
			this.setType(type);
			this.setNote(note);
			this.setLength(length);
			return;
		} catch (MozartRuntimeException e) {
			throw new MozartRuntimeException(e);
		}
	}

	public int getLength() {
		return this.length;
	}

	public int getNote() {
		return this.note;
	}

	public int getType() {
		return this.type;
	}

	private void setLength(int length) throws MozartRuntimeException {
		try {
			if (length < 1) {
				throw new MozartRuntimeException(this.getClass().getName() + ": note length is equal to or less than zero.");
			}
			this.length = length;
		} catch (MozartRuntimeException e) {
			throw new MozartRuntimeException(e);
		}
	}

	private void setNote(int note) throws MozartRuntimeException {
		try {
			if (note < 0 | note > 127) {
				throw new MozartRuntimeException(this.getClass().getName() + ": note value out of range.");
			}
			this.note = note;
		} catch (MozartRuntimeException e) {
			throw new MozartRuntimeException(e);
		}
	}

	private void setType(int type) throws MozartRuntimeException {
		try {
			if (type < MozartNotes.TYPE_NORMAL | type > MozartNotes.TYPE_REST) {
				throw new MozartRuntimeException(this.getClass().getName() + ": invalid note type.");
			}
			this.type = type;
		} catch (MozartRuntimeException e) {
			throw new MozartRuntimeException(e);
		}
	}

}