package com.github.project.videoeditor.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.github.project.videoeditor.container.Marker;
import com.github.project.videoeditor.container.Movie;
import com.github.project.videoeditor.model.MarkerHandler;

/**
 * 
 * @author Peter Gessler
 * @version 1.0
 * @DevelopmentDate 03.01.2016
 * @LastUpdate -
 * @Assignment Frame to edit a marker.
 * 
 */

public class MarkerCreatorFrame {

	JFrame markerCreatorFrameParent = null;

	private int markerId;
	private String markerName;
	private double startTime;
	private double endTime;

	private JLabel idLabel;
	private JLabel nameLabel;
	private JLabel startTimeLabel;
	private JLabel endTimeLabel;

	private JLabel idTxtLabel;
	private JTextField nameTxtField;
	private JTextField startTimeTxtFieldMin;
	private JTextField startTimeTxtFieldSec;
	private JTextField startTimeTxtFieldMilliSeconds;
	private JTextField endTimeTxtFieldMin;
	private JTextField endTimeTxtFieldSec;
	private JTextField endTimeTxtFieldMilliSeconds;

	private JButton saveButton;
	private JButton abortButton;

	private String[] labels = { "Id: ", "Name: ", "Start in Min/Sec/Ms: ",
			"End in Min/Sec/Ms: " };

	private static volatile MarkerCreatorFrame singleton = null;

	public static synchronized MarkerCreatorFrame getInstance() {
		if (singleton == null)
			singleton = new MarkerCreatorFrame();
		return singleton;
	}

	// Constructor
	private MarkerCreatorFrame() {

		// Create and set up the window.
		markerCreatorFrameParent = new JFrame("Marker Creator");
		markerCreatorFrameParent
				.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panelContent = new JPanel(new GridLayout(0, 2));

		// id components
		idLabel = new JLabel(labels[0]);
		idTxtLabel = new JLabel("-");
		idTxtLabel.setLabelFor(idLabel);

		panelContent.add(idLabel);
		panelContent.add(idTxtLabel);

		// name components
		nameLabel = new JLabel(labels[1]);
		nameTxtField = new JTextField(10);
		nameLabel.setLabelFor(nameTxtField);

		panelContent.add(nameLabel);
		panelContent.add(nameTxtField);

		// startTime components
		startTimeLabel = new JLabel(labels[2]);
		JPanel startTimePanel = new JPanel(new GridLayout(0, 3));
		startTimeTxtFieldMin = new JTextField(2);
		startTimeTxtFieldSec = new JTextField(2);
		startTimeTxtFieldMilliSeconds = new JTextField(2);
		startTimeLabel.setLabelFor(startTimePanel);

		startTimePanel.add(startTimeTxtFieldMin);
		startTimePanel.add(startTimeTxtFieldSec);
		startTimePanel.add(startTimeTxtFieldMilliSeconds);

		panelContent.add(startTimeLabel);
		panelContent.add(startTimePanel);

		// endTime components
		endTimeLabel = new JLabel(labels[3]);
		JPanel endTimePanel = new JPanel(new GridLayout(0, 3));
		endTimeTxtFieldMin = new JTextField(2);
		endTimeTxtFieldSec = new JTextField(2);
		endTimeTxtFieldMilliSeconds = new JTextField(2);
		endTimeLabel.setLabelFor(endTimePanel);

		endTimePanel.add(endTimeTxtFieldMin);
		endTimePanel.add(endTimeTxtFieldSec);
		endTimePanel.add(endTimeTxtFieldMilliSeconds);

		panelContent.add(endTimeLabel);
		panelContent.add(endTimePanel);

		// button components
		saveButton = new JButton(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				saveValues();
			}
		});
		saveButton.setName("Hinzufügen");
		saveButton.setText("Hinzufügen");

		abortButton = new JButton(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				closeWindow();
			}
		});
		abortButton.setName("Abbrechen");
		abortButton.setText("Abbrechen");

		panelContent.add(saveButton);
		panelContent.add(abortButton);

		// Set up the content pane.
		panelContent.setOpaque(true); // content panes must be opaque
		markerCreatorFrameParent.setContentPane(panelContent);

	}

	public void createFrame() {
		// Display the window.
		markerCreatorFrameParent.pack();
		markerCreatorFrameParent.setVisible(true);

		setNewMarkerId();

		nameTxtField.setText("");
		startTimeTxtFieldMin.setText("");
		startTimeTxtFieldSec.setText("");
		startTimeTxtFieldMilliSeconds.setText("");
		endTimeTxtFieldMin.setText("");
		endTimeTxtFieldSec.setText("");
		endTimeTxtFieldMilliSeconds.setText("");
	}

	// set initial marker id
	private void setNewMarkerId() {

		try {
			MarkerHandler markerHandler = MarkerHandler.getInstance();
			markerId = markerHandler.getMarkerList()
					.get(markerHandler.getMarkerList().size() - 1)
					.getMarkerId() + 1;
		} catch (ArrayIndexOutOfBoundsException error) {
			markerId = 0;
		}
		idTxtLabel.setText(String.valueOf(markerId));
	}

	// store new values
	private void saveValues() {

		try {
			markerName = nameTxtField.getText();
			startTime = deformatTime(startTimeTxtFieldMin.getText(),
					startTimeTxtFieldSec.getText(),
					startTimeTxtFieldMilliSeconds.getText());
			endTime = deformatTime(endTimeTxtFieldMin.getText(),
					endTimeTxtFieldSec.getText(),
					endTimeTxtFieldMilliSeconds.getText());

			if (startTime < endTime && endTime < Movie.getInstance().getMovDuration()) {
				MarkerHandler.getInstance().addMarkerToList(
						new Marker(markerId, markerName, startTime, endTime));

				closeWindow();
			}
		} catch (Exception error) {

		}

	}

	// close frame without storing
	private void closeWindow() {
		markerCreatorFrameParent.dispatchEvent(new WindowEvent(
				markerCreatorFrameParent, WindowEvent.WINDOW_CLOSING));
	}

	// format to String
	private TimeView formatTime(double time) {

		int minutes = (int) (time / 60);
		int seconds = (int) (time % 60);

		long iPart = (long) time;
		int milliSeconds = (int) ((time - iPart) * 100);

		return (new TimeView(minutes, seconds, milliSeconds));
	}

	// format to double
	private double deformatTime(String minutes, String seconds,
			String milliSeconds) {

		int inSeconds = (Integer.valueOf(minutes) * 60)
				+ Integer.valueOf(seconds);
		double milliSec = Double.valueOf(milliSeconds) / 100;
		double deformatedTime = inSeconds + milliSec;
		return deformatedTime;
	}

	public static void main(String[] args) {

		MarkerEditorFrame.getInstance();
		MarkerEditorFrame.getInstance().initMarkerValues(
				new Marker(0, "Frost", 48.500, 122.260));
	}

}
