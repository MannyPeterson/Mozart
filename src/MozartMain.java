
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

public class MozartMain {

	public static void main(String[] args) {
		MozartSequencer mozart = new MozartSequencer();
		int[] phrase = new int[100];
		int i;
		mozart.setScale("C", MozartSequencer.SCALE_MAJOR);
		phrase = mozart.createPhrase("C", MozartSequencer.OCTAVE_NORMAL);
		try {

			Synthesizer synth = MidiSystem.getSynthesizer();
			synth.open();
			MidiChannel[] channels = synth.getChannels();
			MidiChannel channel = channels.clone()[0];
			channel.allNotesOff();
			channel.allSoundOff();
			Thread.sleep(5000);
			for (i = 0; i < phrase.length; i++) {
				channel.noteOn(phrase[i], 1207);
				Thread.sleep(200);
				channel.noteOff(phrase[i]);
			}

		} catch (MidiUnavailableException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
