package org.codehamster;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class MozartFrame extends JFrame {

	private static final long serialVersionUID = -8514276561086641341L;
	private JPanel contentPane;

	public MozartFrame() {
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

		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(677, 504, 117, 29);
		contentPane.add(btnExit);

		JButton btnPlay = new JButton("Play");
		btnPlay.setBounds(555, 504, 117, 29);
		contentPane.add(btnPlay);

		JTextArea txtConsole = new JTextArea();
		txtConsole.setEditable(false);
		txtConsole.setBounds(197, 6, 597, 257);
		contentPane.add(txtConsole);

		JScrollPane txtConsoleScrollPane = new JScrollPane(txtConsole);
		txtConsoleScrollPane.setBounds(197, 6, 597, 257);
		contentPane.add(txtConsoleScrollPane);

		JScrollBar txtConsoleScrollBar = txtConsoleScrollPane.getVerticalScrollBar();

		txtConsole.append(
				"Mozart Digital Composer Version 0.1\nCopyright (C) 2017 Manny Peterson <me@mannypeterson.com>\n\nThis program is free software: you can redistribute it and/or modify\nit under the terms of the GNU General Public License as published by\nthe Free Software Foundation, either version 3 of the License, or\n(at your option) any later version.\n\nThis program is distributed in the hope that it will be useful,\nbut WITHOUT ANY WARRANTY; without even the implied warranty of\nMERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the\nGNU General Public License for more details.\n\nYou should have received a copy of the GNU General Public License\nalong with this program.  If not, see <https://www.gnu.org/licenses/>.\n");

		txtConsoleScrollBar.setValue(txtConsoleScrollBar.getMaximum());

	}

}
