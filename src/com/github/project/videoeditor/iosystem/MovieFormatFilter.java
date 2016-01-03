package com.github.project.videoeditor.iosystem;

import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * 
 * @author Peter Gessler
 * @version 1.0
 * @DevelopmentDate 31.12.2015
 * @LastUpdate 03.01.2016
 * @Assignment Concrete file filter strategy (see oodesign.com). Return filter
 *             to get files with *.mov only.
 * 
 */

public class MovieFormatFilter implements IFileFilter {

	private static volatile MovieFormatFilter singleton = null;

	public static synchronized MovieFormatFilter getInstance() {
		if (singleton == null)
			singleton = new MovieFormatFilter();
		return singleton;
	}

	private MovieFormatFilter() {
		super();
	}

	@Override
	public FileFilter formatFilter() {

		FileFilter filter = new FileNameExtensionFilter("Movie file", "mov",
				"ts");

		return filter;
	}
}
