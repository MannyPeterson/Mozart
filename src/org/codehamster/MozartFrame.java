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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public MozartFrame() throws MozartRuntimeException {
		String[] scaleItems = { "MAJOR", "MINOR" };
		String[] keyItems = { "C", "C#/Db", "D", "D#/Eb", "E", "F", "F#/Gb", "G", "G#/Ab", "A", "A#/Bb", "B" };
		JLabel lblMozartImg;
		JLabel lblScale;
		JLabel lblKey;
		JLabel lblPhraseLength;
		JLabel lblSongLength;
		JButton btnExit;
		JButton btnPlay;
		try {
			setTitle("Mozart Digital Composer");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 800, 560);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);

			lblMozartImg = new JLabel("");
			lblMozartImg.setBounds(6, 6, 185, 257);
			lblMozartImg.setIcon(new ImageIcon(MozartFrame.class.getResource("/org/codehamster/Mozart.png")));
			contentPane.add(lblMozartImg);

			lblScale = new JLabel("Scale");
			lblScale.setBounds(197, 266, 90, 22);
			contentPane.add(lblScale);

			lblKey = new JLabel("Key");
			lblKey.setBounds(356, 266, 90, 22);
			contentPane.add(lblKey);

			lblPhraseLength = new JLabel("Phrase Length (Notes)");
			lblPhraseLength.setBounds(507, 265, 151, 22);
			contentPane.add(lblPhraseLength);

			lblSongLength = new JLabel("Song Length (Phrases)");
			lblSongLength.setBounds(507, 325, 151, 22);
			contentPane.add(lblSongLength);

			btnExit = new JButton("Exit");
			btnExit.setBounds(677, 504, 117, 29);
			btnExit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					System.exit(0);
				}
			});
			contentPane.add(btnExit);

			btnPlay = new JButton("Play");
			btnPlay.setBounds(555, 504, 117, 29);
			btnPlay.addActionListener(new ActionListener() {
				private MozartFrame frame;

				public void actionPerformed(ActionEvent ae)  {
					MozartFrame frame = this.frame;
					new SwingWorker<Void, Void>() {
						public Void doInBackground() {
							Random random;
							MozartScale scale;
							MozartArrangement arrangement;
							MozartInstrument instrument;
							try {
								random = new Random(System.currentTimeMillis());
								frame.writeConsole("Scale: " + frame.getScale().getString());
								frame.writeConsole("Key: " + frame.getKey().getString());
								scale = new MozartScale(frame.getScale(), frame.getKey());
								arrangement = new MozartArrangement(10);
								instrument = new MozartInstrument();
								for (int i = 0; i < 6; i++) {
									arrangement
											.addPhrase(new MozartPhrase(scale, MozartOctaveType.SIXTH, random,  60));
								}
								arrangement.create();
								instrument.open();
								for (MozartPhrase phrase : arrangement.getArrangement()) {
									instrument.play(phrase, frame);
								}
								instrument.close();
								return null;
							} catch (MozartRuntimeException e) {
								e.printStackTrace();
								return null;
							} catch (InvalidMidiDataException e) {
								e.printStackTrace();
								return null;
							} catch (InterruptedException e) {
								e.printStackTrace();
								return null;
							}
						}

						protected void done() {

						}
					}.execute();
				}

				public ActionListener setFrame(MozartFrame frame) throws MozartRuntimeException {
					try {
						if (frame == null) {
							throw new MozartRuntimeException(this.getClass().getName() + ": frame is null.");
						}
						this.frame = frame;
						return this;
					} catch (MozartRuntimeException e) {
						throw new MozartRuntimeException(e);
					}
				}
			}.setFrame(this));
			contentPane.add(btnPlay);

			this.txtConsole = new JTextArea();
			this.txtConsole.setBounds(197, 6, 597, 257);
			this.txtConsole.setEditable(false);
			contentPane.add(this.txtConsole);

			this.txtConsoleScrollPane = new JScrollPane(this.txtConsole);
			this.txtConsoleScrollPane.setBounds(197, 6, 597, 257);
			this.txtConsoleScrollBar = this.txtConsoleScrollPane.getVerticalScrollBar();
			contentPane.add(this.txtConsoleScrollPane);

			this.lstScale = new JList(scaleItems);
			this.lstScale.setBounds(197, 290, 224, 182);
			this.lstScale.setSelectedIndex(0);
			contentPane.add(this.lstScale);

			this.lstScaleScrollPane = new JScrollPane(this.lstScale);
			this.lstScaleScrollPane.setBounds(197, 290, 129, 182);
			contentPane.add(this.lstScaleScrollPane);

			this.lstKey = new JList(keyItems);
			this.lstKey.setBounds(454, 290, 224, 182);
			this.lstKey.setSelectedIndex(0);
			contentPane.add(this.lstKey);

			this.lstKeyScrollPane = new JScrollPane(this.lstKey);
			this.lstKeyScrollPane.setBounds(356, 290, 129, 182);
			contentPane.add(this.lstKeyScrollPane);

			this.txtPhraseLength = new JTextField();
			this.txtPhraseLength.setBounds(502, 287, 130, 26);
			this.txtPhraseLength.setText("60");
			this.txtPhraseLength.setColumns(10);
			contentPane.add(this.txtPhraseLength);

			this.txtSongLength = new JTextField();
			this.txtSongLength.setBounds(502, 347, 130, 26);
			this.txtSongLength.setText("10");
			this.txtSongLength.setColumns(10);
			contentPane.add(this.txtSongLength);

			this.writeConsole(
					"Mozart Digital Composer Version 0.1\nCopyright (C) 2017 Manny Peterson <me@mannypeterson.com>\n\nThis program is free software: you can redistribute it and/or modify\nit under the terms of the GNU General Public License as published by\nthe Free Software Foundation, either version 3 of the License, or\n(at your option) any later version.\n\nThis program is distributed in the hope that it will be useful,\nbut WITHOUT ANY WARRANTY; without even the implied warranty of\nMERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the\nGNU General Public License for more details.\n\nYou should have received a copy of the GNU General Public License\nalong with this program.  If not, see <https://www.gnu.org/licenses/>.\n");

			return;
		} catch (MozartRuntimeException e) {
			throw new MozartRuntimeException(e);
		}
	}

	public MozartPitchType getKey() throws MozartRuntimeException {
		MozartPitchType retVal;
		String selectedKey;
		try {
			selectedKey = this.lstKey.getSelectedValue().toString();
			if (MozartPitchType.C.getString().equals(selectedKey)) {
				retVal = MozartPitchType.C;
			} else if (MozartPitchType.C_SHARP_D_FLAT.getString().equals(selectedKey)) {
				retVal = MozartPitchType.C_SHARP_D_FLAT;
			} else if (MozartPitchType.D.getString().equals(selectedKey)) {
				retVal = MozartPitchType.D;
			} else if (MozartPitchType.D_SHARP_E_FLAT.getString().equals(selectedKey)) {
				retVal = MozartPitchType.D_SHARP_E_FLAT;
			} else if (MozartPitchType.E.getString().equals(selectedKey)) {
				retVal = MozartPitchType.E;
			} else if (MozartPitchType.F.getString().equals(selectedKey)) {
				retVal = MozartPitchType.F;
			} else if (MozartPitchType.F_SHARP_G_FLAT.getString().equals(selectedKey)) {
				retVal = MozartPitchType.F_SHARP_G_FLAT;
			} else if (MozartPitchType.G.getString().equals(selectedKey)) {
				retVal = MozartPitchType.G;
			} else if (MozartPitchType.G_SHARP_A_FLAT.getString().equals(selectedKey)) {
				retVal = MozartPitchType.G_SHARP_A_FLAT;
			} else {
				throw new MozartRuntimeException(this.getClass().getName() + ": unsupported pitch type.");
			}
			return retVal;
		} catch (MozartRuntimeException e) {
			throw new MozartRuntimeException(e);
		}
	}

	public MozartScaleType getScale() throws MozartRuntimeException {
		MozartScaleType retVal;
		String selectedScale;
		try {
			if (this.lstScale == null) {
				throw new MozartRuntimeException(this.getClass().getName() + ": lstScale is null.");
			}
			selectedScale = this.lstScale.getSelectedValue().toString();
			if (MozartScaleType.MAJOR.getString().equals(selectedScale)) {
				retVal = MozartScaleType.MAJOR;
			} else if (MozartScaleType.MINOR.getString().equals(selectedScale)) {
				retVal = MozartScaleType.MINOR;
			} else {
				throw new MozartRuntimeException(this.getClass().getName() + ": unsupported scale type.");
			}
			return retVal;
		} catch (MozartRuntimeException e) {
			throw new MozartRuntimeException(e);
		}
	}

	public void writeConsole(String text) throws MozartRuntimeException {
		try {
			if (text == null) {
				throw new MozartRuntimeException(this.getClass().getName() + ": text is null.");
			}
			if (this.txtConsole == null) {
				throw new MozartRuntimeException(this.getClass().getName() + ": txtConsole is null.");
			}
			if (this.txtConsoleScrollBar == null) {
				throw new MozartRuntimeException(this.getClass().getName() + ": txtConsoleScrollBar is null.");
			}
			this.txtConsole.append(text + "\n");
			this.txtConsoleScrollBar.setValue(this.txtConsoleScrollBar.getMaximum());
			return;
		} catch (MozartRuntimeException e) {
			throw new MozartRuntimeException(e);
		}
	}
}
