import org.codehamster.*;

public class MozartMain {
	public static void main(String[] args) {
		for (MozartOctaveType o : MozartOctaveType.values()) {
			for (MozartNoteType n : MozartNoteType.values()) {
				System.out.println(new MozartNote(n, o));
			}
		}

		/*
		 * MozartScale mozartScale; MozartPhrase mozartPhrase; MozartNotes[] phrase;
		 * mozartScale = new MozartScale("C", MozartScale.SCALE_MAJOR); mozartPhrase =
		 * new MozartPhrase(mozartScale, 1); phrase = mozartPhrase.getPhrase(); int i;
		 * int j; try { Synthesizer synth = MidiSystem.getSynthesizer(); synth.open();
		 * MidiChannel[] channels = synth.getChannels(); MidiChannel channel =
		 * channels.clone()[0]; channel.allNotesOff(); channel.allSoundOff();
		 * Thread.sleep(5000); for (i = 0; i < phrase.length; i++) {
		 * System.out.println(i); if (phrase[i].getType() == MozartNotes.TYPE_NORMAL) {
		 * channel.noteOn(phrase[i].getNote(), 127); for (j = 0; j <
		 * phrase[i].getLength(); j++) { Thread.sleep(25); }
		 * channel.noteOff(phrase[i].getNote()); } else if (phrase[i].getType() ==
		 * MozartNotes.TYPE_REST) { for (j = 0; j < phrase[i].getLength(); j++) {
		 * Thread.sleep(25); } } } } catch (MidiUnavailableException e) {
		 * e.printStackTrace(); } catch (InterruptedException e) { e.printStackTrace();
		 * }
		 */
	}
}
