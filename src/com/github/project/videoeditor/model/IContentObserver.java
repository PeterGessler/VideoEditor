package com.github.project.videoeditor.model;

import java.util.List;

import com.github.project.videoeditor.container.Marker;
import com.github.project.videoeditor.container.Movie;

/**
 * 
 * @author Peter Gessler
 * @version 1.0
 * @DevelopmentDate 31.12.2015
 * @LastUpdate 03.01.2016
 * @Assignment Interface to inform other observer about new content(marker objects) data.
 * 
 */

public interface IContentObserver {

	public String getName();

	public void setNewMarkerList(List<Marker> markerList);

	public void setNewMovieInformation(Movie movie);
}
