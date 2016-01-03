package com.github.project.videoeditor.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;

import com.github.project.videoeditor.container.Marker;
import com.github.project.videoeditor.container.Movie;
import com.github.project.videoeditor.model.EditorHandler;
import com.github.project.videoeditor.model.IContentObserver;
import com.github.project.videoeditor.model.MarkerHandler;

/**
 * 
 * @author Peter Gessler
 * @version 1.0
 * @DevelopmentDate 31.12.2015
 * @LastUpdate 03.01.2016
 * @Assignment Start video editor and initialize user interface.
 * 
 */

public class GUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6886137606438237473L;
	private EditorHandler model;
	private JList<Marker> markerList;
	private JTable table;

	String[] tableColumnNames = { "Id", "Marker name", "Start time", "End time" };
	
	String[][] tableRowData = {};


	private JPanel leftPanelMain = null;
	private JPanel leftPanelTop = null;
	private JPanel leftPanelBottom = null;

	// constructor
	public GUI(String name, EditorHandler model) {
		super(name);
		this.model = model;
		buildComponent();
	}

	// method create GUI Components
	private void buildComponent() {
		add(buildLeftView(), BorderLayout.WEST);
		add(buildCenterView(), BorderLayout.CENTER);

	}

	// Frame left side
	private JPanel buildLeftView() {

		leftPanelMain = new JPanel(new GridLayout(2, 0));
		leftPanelMain.setPreferredSize(new Dimension(200, 600));

		buildLeftPanelTop();
		buildLeftPanelBottom();

		leftPanelMain.add(leftPanelTop);
		leftPanelMain.add(leftPanelBottom);

		return leftPanelMain;
	}

	// build the top panel of the left view
	private void buildLeftPanelTop() {

		leftPanelTop = new JPanel(new GridLayout(0, 2));
		leftPanelTop.setBorder(BorderFactory.createLineBorder(Color.darkGray));

		JLabel srcAddrTxtLabel = new JLabel("Src.: ");
		JLabel srcAddrInputLabel = new JLabel("", 10);

		leftPanelTop.add(srcAddrTxtLabel);
		leftPanelTop.add(srcAddrInputLabel);

		JLabel movLengthLabel = new JLabel("Movie time: ");
		JLabel movLengthInputLabel = new JLabel("", 10);

		leftPanelTop.add(movLengthLabel);
		leftPanelTop.add(movLengthInputLabel);

		JLabel movSizeLabel = new JLabel("Movie length: ");
		JLabel movSizeInputLabel = new JLabel("", 10);

		leftPanelTop.add(movSizeLabel);
		leftPanelTop.add(movSizeInputLabel);

	}

	// build the bottom panel of the left view
	private void buildLeftPanelBottom() {

		leftPanelBottom = new JPanel(new GridLayout(0, 2));
		leftPanelBottom.setBorder(BorderFactory
				.createLineBorder(Color.darkGray));

		JButton loadMovieBtn = new JButton(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		});
		loadMovieBtn.setText("Load movie");

		JButton loadMarkerBtn = new JButton(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		});
		loadMarkerBtn.setText("Load Marker");

		leftPanelBottom.add(loadMovieBtn);
		leftPanelBottom.add(loadMarkerBtn);

		JButton addMarkerBtn = new JButton(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		});
		addMarkerBtn.setText("Add marker");

		JButton deleteMarkerBtn = new JButton(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		});
		deleteMarkerBtn.setText("Delete Marker");

		leftPanelBottom.add(addMarkerBtn);
		leftPanelBottom.add(deleteMarkerBtn);

		JButton editmarkerBtn = new JButton(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		});
		editmarkerBtn.setText("Delete marker");

		JButton saveDirBtn = new JButton(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		});
		saveDirBtn.setText("Save directory");

		leftPanelBottom.add(editmarkerBtn);
		leftPanelBottom.add(saveDirBtn);

		JButton saveNewMarkerListBtn = new JButton(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		});
		saveNewMarkerListBtn.setText("Save marker");

		JButton cutAndCreateVideosBtn = new JButton(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		});
		cutAndCreateVideosBtn.setText("Cut and Create");

		leftPanelBottom.add(saveNewMarkerListBtn);
		leftPanelBottom.add(cutAndCreateVideosBtn);

	}

	// Frame center
	private JPanel buildCenterView() {

		final JPanel centerPanel = new JPanel(new BorderLayout());
		table = new JTable(tableRowData, tableColumnNames );
		centerPanel.add( new JScrollPane( table ) );
		//markerList = new JList<Marker>(new Vector<>(MarkerHandler.getInstance().getMarkerItems()));
		//centerPanel.add(new JScrollPane(markerList), BorderLayout.CENTER);

		return centerPanel;
	}

	// update information if we load a movie
	public void updateMovieInformation(Movie movie) {
		// TODO Auto-generated method stub

	}

	// update marker information
	public void updateMarkerList(List<Marker> markerList) {

		this.markerList.setListData(new Vector<>(MarkerHandler.getInstance()
				.getMarkerItems()));

	}

}
