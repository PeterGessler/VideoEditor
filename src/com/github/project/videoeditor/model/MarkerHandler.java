package com.github.project.videoeditor.model;

import java.util.Collection;
import java.util.Collections;

import com.github.project.videoeditor.container.Marker;
import com.github.project.videoeditor.iosystem.MarkerListWriter;


/**
 * 
 * @author Peter Gessler
 * @version 1.0
 * @DevelopmentDate 31.12.2015
 * @LastUpdate 03.01.2016
 * @Assignment Handle all marker actions.
 * 
 */

public class MarkerHandler extends AContentHandler {

	private static volatile MarkerHandler singleton = null;
	
	private String markerListPath;

	public static synchronized MarkerHandler getInstance() {
		if (singleton == null)
			singleton = new MarkerHandler();
		return singleton;
	}

	// Constructor
	private MarkerHandler() {
		super();
	}

	// add marker
	public void addMarkerToList(Marker newMarker) {

		markerList.add(newMarker);
		refreshDataContent();

	}

	// delete one marker from list
	public void deleteMarkerFromList(int markerIndex) {

		markerList.remove(markerIndex);
		
		while (markerIndex < markerList.size()) {
			
			markerList.get(markerIndex).setMarkerId(markerIndex);
			
			markerIndex++;
		}
		
		refreshDataContent();

	}

	/**
	 * Method to edit a marker from list.
	 * 
	 * @param markerIndex
	 * @param modifiedMarker
	 */
	public void editMarker(int markerIndex, Marker modifiedMarker) {

		markerList.set(markerIndex, modifiedMarker);
		refreshDataContent();

	}

	// delete all marker
	public void deleteAllMarker() {

		markerList.clear();

	}

	// get marker from MarkerList
	public Marker getMarkerFromList(int id) {
		return markerList.get(id);
	}
	
	public Collection<Marker> getMarkerItems() {
		return Collections.unmodifiableList(markerList);
	}

	public void setMarkerlistPath(String markerListPath) {
		this.markerListPath = markerListPath;
	}
	
	public String getMarkerlistPath() {
		return this.markerListPath;
	}
	
	public void writeMarkerlist() {
		
		MarkerListWriter.getInstance().setStorePath(markerListPath);
		MarkerListWriter.getInstance().writeMarkerlist(getMarkerList());
	}
}
