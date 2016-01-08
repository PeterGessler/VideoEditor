package com.github.project.videoeditor.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.github.project.videoeditor.container.Marker;
import com.github.project.videoeditor.container.Movie;
import com.github.project.videoeditor.gui.GUI;
import com.github.project.videoeditor.gui.OverwriteDialog;
import com.github.project.videoeditor.iosystem.IFileObserver;
import com.xuggle.xuggler.IContainer;

/**
 * 
 * @author Peter Gessler
 * @version 1.0
 * @DevelopmentDate 31.12.2015
 * @LastUpdate 03.01.2016
 * @Assignment Handle all incoming calls from user interface. Class is defined
 *             as facade.
 * 
 */

public class EditorHandler implements IFileObserver {

	private MarkerHandler contentHandler;

	@Override
	public void newMovieFileAvailable(File movieFile) {

		this.contentHandler = MarkerHandler.getInstance();

		Movie.getInstance().setSourceAddress(movieFile.getAbsolutePath());
		Movie.getInstance().setMovsize(movieFile.length() / 1024); // in kB

		IContainer container = IContainer.make();
		container.open(Movie.getInstance().getSourceAddress(),
				IContainer.Type.READ, null);
		Movie.getInstance().setMovDuration(container.getDuration() / 1000);

		contentHandler.setStoreDirectory(movieFile.getParent());

		contentHandler.setCurrentMovie(Movie.getInstance());

		contentHandler.refreshDataContent();
	}

	@Override
	public void newMarkerFileAvailable(File markerFile) {

		this.contentHandler = MarkerHandler.getInstance();

		contentHandler.setMarkerlistPath(markerFile.getAbsolutePath());

		// remove all marker from list
		if (!contentHandler.getMarkerList().isEmpty()) {

			JFrame dialogFrame = new JFrame();
			JOptionPane optionPane = new OverwriteDialog(dialogFrame);
		}

		try {

			int id;

			if (contentHandler.getMarkerList().isEmpty()) {
				id = 0;
			} else {
				id = contentHandler.getMarkerList()
						.get(contentHandler.getMarkerList().size() - 1)
						.getMarkerId() + 1;
			}

			FileReader fileReader = new FileReader(markerFile.getPath());
			BufferedReader buffReader = new BufferedReader(fileReader);

			String rowLine = "";

			while ((rowLine = buffReader.readLine()) != null) {
				String[] columnDetail = new String[3];
				columnDetail = rowLine.split("\t");

				Marker newMarker = new Marker(id, columnDetail[2],
						Double.valueOf(columnDetail[0].replace(',', '.')),
						Double.valueOf(columnDetail[1].replace(',', '.')));

				contentHandler.addMarkerToList(newMarker);

				// increment counter
				id++;
			}

			buffReader.close();

			contentHandler.refreshDataContent();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
