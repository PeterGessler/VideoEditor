package com.github.project.videoeditor.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.github.project.videoeditor.container.Marker;
import com.github.project.videoeditor.container.Movie;


/**
 * 
 * @author Peter Gessler
 * @version 1.0
 * @DevelopmentDate 31.12.2015
 * @LastUpdate 03.01.2016
 * @Assignment Handle important data like marker list and movie data. Refresh
 *             user interface if new data available.
 * 
 */

public abstract class AContentHandler {

	protected List<IContentObserver> contentListener;
	protected List<Marker> markerList;
	protected Movie movie;
	private String storeDirectoryPath;
	
	// Constructor
	protected AContentHandler() {
		
		setMarkerList(new ArrayList<Marker>());
		contentListener = new ArrayList<IContentObserver>();
	}
	
	// add content listener
	public void addContentListener(IContentObserver contentObserver) {
		contentListener.add(contentObserver);
	}
	
	// remove content listener by listener name.
	public void removeContentListener(IContentObserver contentObserver) {
		
		for (IContentObserver iContentObserver : contentListener) {
			if (iContentObserver.getName().equals(contentObserver.getName())) {
				contentListener.remove(contentObserver);
			}
		}
	}

	public List<Marker> getMarkerList() {
		return markerList;
	}

	public void setMarkerList(List<Marker> markerList) {
		this.markerList = markerList;
	}
	
	public void setCurrentMovie(Movie movie) {
		this.movie = movie;
	}
	
	// Method call all listener to refresh her data content
	protected void refreshDataContent() {
		
		for (IContentObserver iContentObserver : contentListener) {
			iContentObserver.setNewMarkerList(markerList);
			iContentObserver.setNewMovieInformation(movie);
		}
	}

	// Method to set store directory
	public void setStoreDirectory(String path) {

		storeDirectoryPath = path;
		
	}
	
	// Method to get store directory
	public String getStoreDirectory() {
		return storeDirectoryPath;
	}
	
}
