
public class MozartNote {
	public static final int DEFAULT_TTL = 32;
	public static final int TYPE_NORMAL = 0;
	public static final int TYPE_REST = 1;
	private int length;
	private int note;
	private int ttl;
	private int type;

	public MozartNote(int type, int note, int length) throws MozartRuntimeException {
		try {
			this.ttl = MozartNote.DEFAULT_TTL;
			if (type < MozartNote.TYPE_NORMAL | type > MozartNote.TYPE_REST) {
				throw new MozartRuntimeException(this.getClass().getName() + ": invalid note type.");
			}
			if (note < 0 | note > 127) {
				throw new MozartRuntimeException(this.getClass().getName() + ": note out of range.");
			}
			if (length > this.ttl) {
				throw new MozartRuntimeException(
						this.getClass().getName() + ": note length must not exceed time to live (TTL).");
			}
			this.type = type;
			this.note = note;
			this.length = length;
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

	public int getTTL() {
		return this.ttl;
	}

	public int getType() {
		return this.type;
	}

	public boolean isAlive() {
		if (this.getTTL() > 0) {
			return true;
		}
		return false;
	}

	public void tick() {
		this.ttl -= 1;
		return;
	}
}