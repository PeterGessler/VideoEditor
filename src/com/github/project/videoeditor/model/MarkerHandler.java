package com.github.project.videoeditor.model;

import java.util.Collection;
import java.util.Collections;

import com.github.project.videoeditor.container.Marker;


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

	}

	// delete one marker from list
	public void deleteMarkerFromList(int markerIndex) {

		markerList.remove(markerIndex);

	}

	/**
	 * Method to edit a marker from list.
	 * 
	 * @param markerIndex
	 * @param modifiedMarker
	 */
	public void editMarker(int markerIndex, Marker modifiedMarker) {

		markerList.set(markerIndex, modifiedMarker);

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
}
