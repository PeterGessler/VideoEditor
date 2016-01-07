package com.github.project.videoeditor.iosystem;

/**
 * 
 * @author Peter Gessler
 * @version 1.0
 * @DevelopmentDate 07.01.2016
 * @LastUpdate -
 * @Assignment Class to instantiate filter.
 * 
 */

public class FileFormatFilter extends AFileFilter {

	private static volatile FileFormatFilter singleton = null;

	public static synchronized FileFormatFilter getInstance() {
		if (singleton == null)
			singleton = new FileFormatFilter();
		return singleton;
	}

	private FileFormatFilter() {
		super();
	}
}
