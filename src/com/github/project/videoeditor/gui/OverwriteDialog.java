package com.github.project.videoeditor.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.github.project.videoeditor.model.MarkerHandler;

public class OverwriteDialog extends JOptionPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6042351675060076870L;
	private JFrame dialogFrame;

	public OverwriteDialog(JFrame dialogFrame) {

		this.dialogFrame = dialogFrame;
		
		setMessage("Möchten sie die vorhandene Markerliste\n überschreiben?");
		setMessageType(JOptionPane.INFORMATION_MESSAGE);

		JButton yesButton = getYesButton(this, "JA");
		JButton noButton = getNoButton(this, "NEIN");
		this.setOptions(new Object[] { yesButton, noButton });
		JDialog dialog = this.createDialog(dialogFrame, "Sicherheitsabfrage");
		dialog.setVisible(true);
	}

	private JButton getNoButton(OverwriteDialog overwriteDialog, String text) {

		final JButton button = new JButton(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				closeWindow();
			}
		});
		button.setText(text);

		return button;
	}

	private JButton getYesButton(OverwriteDialog overwriteDialog, String text) {

		final JButton button = new JButton(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MarkerHandler.getInstance().getMarkerList().clear();
				closeWindow();
			}
		});
		button.setText(text);

		return button;
	}
	
	// close frame without storing
		private void closeWindow() {
			dialogFrame.dispatchEvent(new WindowEvent(
					dialogFrame, WindowEvent.WINDOW_CLOSING));
		}
}
