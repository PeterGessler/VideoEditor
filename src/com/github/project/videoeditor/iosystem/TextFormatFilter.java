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

public class TextFormatFilter implements IFileFilter {

	private static volatile TextFormatFilter singleton = null;

	public static synchronized TextFormatFilter getInstance() {
		if (singleton == null)
			singleton = new TextFormatFilter();
		return singleton;
	}

	private TextFormatFilter() {
		super();
	}

	@Override
	public FileFilter formatFilter() {
		
		FileFilter filter = new FileNameExtensionFilter("Text file", "txt",
				"text");

		return filter;
	}
}
