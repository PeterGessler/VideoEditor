package com.github.project.videoeditor.container;


/**
 * 
 * @author Peter Gessler
 * @version 1.0
 * @DevelopmentDate 31.12.2015
 * @LastUpdate 03.01.2016
 * @Assignment Container class with information about the movie and file content.
 * 
 */
public class Movie {

	private String sourceAddress;
	private long movLength;
	private long movsize;
	
	private static volatile Movie singleton = null;

	public static synchronized Movie getInstance() {
		if (singleton == null)
			singleton = new Movie();
		return singleton;
	}

	public String getSourceAddress() {
		return sourceAddress;
	}

	public void setSourceAddress(String sourceAddress) {
		this.sourceAddress = sourceAddress;
	}

	public double getMovDuration() {
		return movLength;
	}

	public void setMovDuration(long movLength) {
		this.movLength = movLength;
	}

	public long getMovsize() {
		return movsize;
	}

	public void setMovsize(long movsize) {
		this.movsize = movsize;
	}
	
}
