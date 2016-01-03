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
 *             to get files with *.txt only.
 * 
 */

public class TextFormatFileFilter implements IFileFilter {

	private static volatile TextFormatFileFilter singleton = null;

	public static synchronized TextFormatFileFilter getInstance() {
		if (singleton == null)
			singleton = new TextFormatFileFilter();
		return singleton;
	}

	private TextFormatFileFilter() {
		super();
	}

	@Override
	public FileFilter formatFilter() {
		
		FileFilter filter = new FileNameExtensionFilter("Text file", "txt",
				"text");

		return filter;
	}
}
