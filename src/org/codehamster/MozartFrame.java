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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MozartFrame extends JFrame {

	private static final long serialVersionUID = -8514276561086641341L;
	private JPanel contentPane;
	private JTextArea txtConsole;
	private JScrollBar txtConsoleScrollBar;
	private JScrollPane txtConsoleScrollPane;

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
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(677, 504, 117, 29);
		contentPane.add(btnExit);

		JButton btnPlay = new JButton("Play");
		btnPlay.setBounds(555, 504, 117, 29);
		contentPane.add(btnPlay);

		this.txtConsole = new JTextArea();
		this.txtConsole.setEditable(false);
		this.txtConsole.setBounds(197, 6, 597, 257);
		contentPane.add(this.txtConsole);

		this.txtConsoleScrollPane = new JScrollPane(this.txtConsole);
		this.txtConsoleScrollPane.setBounds(197, 6, 597, 257);
		contentPane.add(this.txtConsoleScrollPane);

		this.txtConsoleScrollBar = this.txtConsoleScrollPane.getVerticalScrollBar();

		txtConsole.append(
				"Mozart Digital Composer Version 0.1\nCopyright (C) 2017 Manny Peterson <me@mannypeterson.com>\n\nThis program is free software: you can redistribute it and/or modify\nit under the terms of the GNU General Public License as published by\nthe Free Software Foundation, either version 3 of the License, or\n(at your option) any later version.\n\nThis program is distributed in the hope that it will be useful,\nbut WITHOUT ANY WARRANTY; without even the implied warranty of\nMERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the\nGNU General Public License for more details.\n\nYou should have received a copy of the GNU General Public License\nalong with this program.  If not, see <https://www.gnu.org/licenses/>.\n");

	}

	public void writeConsole(String text) {
		this.txtConsole.append(text + "\n");
		this.txtConsoleScrollBar.setValue(this.txtConsoleScrollBar.getMaximum());
		return;
	}

}
