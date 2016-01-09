package com.github.project.videoeditor.iosystem;

import java.io.File;

/**
 * 
 * @author Peter Gessler
 * @version 1.0
 * @DevelopmentDate 31.12.2015
 * @LastUpdate -
 * @Assignment Interface to inform other observer about new file data.
 * 
 */


public interface IInputFileObserver {

	public void newMovieFileAvailable(File movieFile);
	
	public void newMarkerFileAvailable(File markerFile);
}
