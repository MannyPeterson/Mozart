
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

public class MozartMain {

	public static void main(String[] args) {

		MozartScale mozartScale;
		MozartPhrase mozartPhrase;
		MozartNote[] phrase;

		mozartScale = new MozartScale("C", MozartScale.SCALE_MAJOR);
		mozartPhrase = new MozartPhrase(mozartScale, 0);
		phrase = new MozartNote[100];

		phrase = mozartPhrase.getPhrase();
		int i;
		try {

			Synthesizer synth = MidiSystem.getSynthesizer();
			synth.open();
			MidiChannel[] channels = synth.getChannels();
			MidiChannel channel = channels.clone()[0];
			channel.allNotesOff();
			channel.allSoundOff();
			Thread.sleep(5000);
			for (i = 0; i < phrase.length; i++) {
				System.out.println(i);
				channel.noteOn(phrase[i].getNote(), 127);
				Thread.sleep(200);
				channel.noteOff(phrase[i].getNote());
			}

		} catch (MidiUnavailableException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
