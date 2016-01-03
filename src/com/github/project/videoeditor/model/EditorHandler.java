package com.github.project.videoeditor.model;

import java.util.List;

import com.github.project.videoeditor.container.Marker;
import com.github.project.videoeditor.container.Movie;
import com.github.project.videoeditor.gui.GUI;

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

public class EditorHandler implements IContentObserver{

	private static final String className = "EditorHandler";
	private GUI gui;

	public void setGui(GUI guiGeneral) {

		this.gui = guiGeneral;
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.className;
	}

	@Override
	public void setNewMarkerList(List<Marker> markerList) {
		gui.updateMarkerList(markerList);
		
	}

	@Override
	public void setNewMovieInformation(Movie movie) {

		gui.updateMovieInformation(movie);
		
	}

}
