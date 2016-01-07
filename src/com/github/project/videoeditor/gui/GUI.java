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
import java.io.File;
import java.util.List;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import com.github.project.videoeditor.container.Marker;
import com.github.project.videoeditor.container.Movie;
import com.github.project.videoeditor.iosystem.AFileFilter;
import com.github.project.videoeditor.iosystem.FileFormatFilter;
import com.github.project.videoeditor.iosystem.IFileObserver;
import com.github.project.videoeditor.iosystem.MovieFormatFilter;
import com.github.project.videoeditor.iosystem.TextFormatFilter;
import com.github.project.videoeditor.model.AContentHandler;
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

public class GUI extends JFrame implements IContentObserver {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6886137606438237473L;
	private JList<Marker> markerList;
	private DefaultTableModel tableModel;
	private IFileObserver fileInputObserver;
	private AContentHandler contentHandler;

	String[] tableColumnNames = { "Id", "Marker name", "Start time", "End time" };

	String[][] tableRowData = {};

	private JPanel leftPanelMain = null;
	private JPanel leftPanelTop = null;
	private JPanel leftPanelBottom = null;

	private JLabel srcAddrInputLabel;
	private JLabel movDurationInputLabel;
	private JLabel movSizeInputLabel;
	
	private JTable table;

	// constructor
	public GUI(String name, IFileObserver editorHandler) {
		super(name);

		fileInputObserver = editorHandler;

		contentHandler = MarkerHandler.getInstance();
		contentHandler.addContentListener(this);
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
		leftPanelMain.setPreferredSize(new Dimension(300, 600));

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
		srcAddrInputLabel = new JLabel("", 10);

		leftPanelTop.add(srcAddrTxtLabel);
		leftPanelTop.add(srcAddrInputLabel);

		JLabel movLengthLabel = new JLabel("Movie time: ");
		movDurationInputLabel = new JLabel("", 10);

		leftPanelTop.add(movLengthLabel);
		leftPanelTop.add(movDurationInputLabel);

		JLabel movSizeLabel = new JLabel("Movie length: ");
		movSizeInputLabel = new JLabel("", 10);

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

				JFileChooser chooser = new JFileChooser("Video waehlen");

				AFileFilter fileFilter = FileFormatFilter.getInstance();
				fileFilter.setFileFilterStrategy(MovieFormatFilter
						.getInstance());

				chooser.setFileFilter(fileFilter.getFormatFilter());

				int windowFeedback = chooser.showOpenDialog(null);

				if (windowFeedback == JFileChooser.APPROVE_OPTION) {

					fileInputObserver.newMovieFileAvailable(chooser
							.getSelectedFile());
				}

			}
		});
		loadMovieBtn.setText("Load movie");

		JButton loadMarkerBtn = new JButton(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JFileChooser chooser = new JFileChooser("Video waehlen");

				AFileFilter fileFilter = FileFormatFilter.getInstance();
				fileFilter.setFileFilterStrategy(TextFormatFilter.getInstance());

				chooser.setFileFilter(fileFilter.getFormatFilter());

				int windowFeedback = chooser.showOpenDialog(null);

				if (windowFeedback == JFileChooser.APPROVE_OPTION) {

					fileInputObserver.newMarkerFileAvailable(chooser
							.getSelectedFile());
				}

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
		tableModel = new DefaultTableModel(tableColumnNames, 0);
		
		table = new JTable(tableModel);
		centerPanel.add(new JScrollPane(table));
		// markerList = new JList<Marker>(new
		// Vector<>(MarkerHandler.getInstance().getMarkerItems()));
		// centerPanel.add(new JScrollPane(markerList), BorderLayout.CENTER);

		return centerPanel;
	}

	@Override
	public void setNewMarkerList(List<Marker> markerList) {

		// delete all
		if (tableModel.getRowCount() > 0) {
		    for (int i = tableModel.getRowCount() - 1; i > -1; i--) {
		    	tableModel.removeRow(i);
		    }
		}
		
		// set new
		if (!markerList.isEmpty()) {

			for (int i = 0; i < markerList.size(); i++) {
				int id = markerList.get(i).getMarkerId();
				String markerName = markerList.get(i).getMarkerName();
				double startTime = markerList.get(i).getStartTime();
				double endTime = markerList.get(i).getEndTime();

				String[] data = {String.valueOf(id), markerName, String.valueOf(startTime), String.valueOf(endTime)};

				tableModel.addRow(data);

			}

		}
		/*
		 * this.markerList.setListData(new Vector<>(MarkerHandler.getInstance()
		 * .getMarkerItems()));
		 */
	}

	@Override
	public void setNewMovieInformation(Movie movie) {

		if (movie != null) {
			srcAddrInputLabel.setText(movie.getSourceAddress());
			movDurationInputLabel.setText(String.format("%.3f" + " sec",
					movie.getMovDuration() / 1000));
			movSizeInputLabel.setText(String.valueOf(movie.getMovsize())
					+ " kB");
		}

	}

}
