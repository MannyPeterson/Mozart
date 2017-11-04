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

import javax.sound.midi.InvalidMidiDataException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JTextField;

public class MozartFrame extends JFrame {

	private static final long serialVersionUID = -8514276561086641341L;
	private JPanel contentPane;
	private JList lstKey;
	private JScrollPane lstKeyScrollPane;
	private JList lstScale;
	private JScrollPane lstScaleScrollPane;
	private JTextArea txtConsole;
	private JScrollBar txtConsoleScrollBar;
	private JScrollPane txtConsoleScrollPane;
	private JTextField txtPhraseLength;
	private JTextField txtSongLength;

	public MozartFrame() {
		/*
		 * 
		 * THIS CODE IS A MESS AT THE MOMENT - IT WILL BE REFINED
		 * 
		 */
		setTitle("Mozart Digital Composer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 560);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblMozartImg = new JLabel("");
		lblMozartImg.setIcon(new ImageIcon(MozartFrame.class.getResource("/org/codehamster/Mozart.png")));
		lblMozartImg.setBounds(6, 6, 185, 257);
		contentPane.add(lblMozartImg);

		JLabel lblScale = new JLabel("Scale");
		lblScale.setBounds(197, 266, 90, 22);
		contentPane.add(lblScale);

		JLabel lblKey = new JLabel("Key");
		lblKey.setBounds(356, 266, 90, 22);
		contentPane.add(lblKey);

		JLabel lblPhraseLength = new JLabel("Phrase Length (Notes)");
		lblPhraseLength.setBounds(507, 265, 151, 22);
		contentPane.add(lblPhraseLength);

		JLabel lblSongLength = new JLabel("Song Length (Phrases)");
		lblSongLength.setBounds(507, 325, 151, 22);
		contentPane.add(lblSongLength);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				System.exit(0);
			}
		});
		btnExit.setBounds(677, 504, 117, 29);
		contentPane.add(btnExit);

		JButton btnPlay = new JButton("Play");
		btnPlay.addActionListener(new ActionListener() {
			private MozartFrame frame;

			public void actionPerformed(ActionEvent ae) {
				MozartFrame frame = this.frame;
				new SwingWorker<Void, Void>() {
					public Void doInBackground() throws MozartRuntimeException {
						Random random;
						MozartScale scale;
						MozartArrangement arrangement;
						MozartInstrument instrument;
						try {
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
							return null;
						} catch (MozartRuntimeException ex) {
							throw new MozartRuntimeException(ex);
						} catch (InvalidMidiDataException ex) {
							throw new MozartRuntimeException(ex);
						} catch (InterruptedException ex) {
							throw new MozartRuntimeException(ex);
						}
					}

					protected void done() {

					}
				}.execute();

			}

			public ActionListener setFrame(MozartFrame frame) {
				this.frame = frame;
				return this;
			}
		}.setFrame(this));
		btnPlay.setBounds(555, 504, 117, 29);
		contentPane.add(btnPlay);

		this.txtConsole = new JTextArea();
		this.txtConsole.setEditable(false);
		this.txtConsole.setBounds(197, 6, 597, 257);
		contentPane.add(this.txtConsole);

		this.txtConsoleScrollPane = new JScrollPane(this.txtConsole);
		this.txtConsoleScrollPane.setBounds(197, 6, 597, 257);
		contentPane.add(this.txtConsoleScrollPane);

		String[] scaleItems = { "Major Scale", "Minor Scale" };

		this.lstScale = new JList(scaleItems);
		this.lstScale.setBounds(197, 290, 224, 182);
		this.lstScale.setSelectedIndex(0);
		contentPane.add(this.lstScale);

		String[] keyItems = { "C", "C# / Db", "D", "D# / Eb", "E", "F", "F# / Gb", "G", "G# / Ab", "A", "A# / Bb",
				"B" };

		this.lstKey = new JList(keyItems);
		this.lstKey.setBounds(454, 290, 224, 182);
		this.lstKey.setSelectedIndex(0);
		contentPane.add(this.lstKey);

		this.lstScaleScrollPane = new JScrollPane(this.lstScale);
		this.lstScaleScrollPane.setBounds(197, 290, 129, 182);
		contentPane.add(this.lstScaleScrollPane);

		this.lstKeyScrollPane = new JScrollPane(this.lstKey);
		this.lstKeyScrollPane.setBounds(356, 290, 129, 182);
		contentPane.add(this.lstKeyScrollPane);

		this.txtPhraseLength = new JTextField();
		this.txtPhraseLength.setText("60");
		this.txtPhraseLength.setBounds(502, 287, 130, 26);
		contentPane.add(this.txtPhraseLength);
		this.txtPhraseLength.setColumns(10);

		this.txtSongLength = new JTextField();
		this.txtSongLength.setText("10");
		this.txtSongLength.setColumns(10);
		this.txtSongLength.setBounds(502, 347, 130, 26);
		contentPane.add(this.txtSongLength);

		this.txtConsoleScrollBar = this.txtConsoleScrollPane.getVerticalScrollBar();

		this.txtConsole.append(
				"Mozart Digital Composer Version 0.1\nCopyright (C) 2017 Manny Peterson <me@mannypeterson.com>\n\nThis program is free software: you can redistribute it and/or modify\nit under the terms of the GNU General Public License as published by\nthe Free Software Foundation, either version 3 of the License, or\n(at your option) any later version.\n\nThis program is distributed in the hope that it will be useful,\nbut WITHOUT ANY WARRANTY; without even the implied warranty of\nMERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the\nGNU General Public License for more details.\n\nYou should have received a copy of the GNU General Public License\nalong with this program.  If not, see <https://www.gnu.org/licenses/>.\n");

	}

	public MozartPitchType getKey() {

		return null;
	}

	public MozartScaleType getScale() {

		return null;
	}

	public void writeConsole(String text) {
		this.txtConsole.append(text + "\n");
		this.txtConsoleScrollBar.setValue(this.txtConsoleScrollBar.getMaximum());
		return;
	}
}
