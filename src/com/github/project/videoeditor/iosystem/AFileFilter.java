package com.github.project.videoeditor.iosystem;

import javax.swing.filechooser.FileFilter;



/**
 * 
 * @author Peter Gessler
 * @version 1.0
 * @DevelopmentDate 31.12.2015
 * @LastUpdate 03.01.2016
 * @Assignment Class to instantiate filter.
 * 
 */

public abstract class AFileFilter {

	IFileFilter fileFilterStrategy = TextFormatFileFilter.getInstance();
	
	// init filter with text filter strategy
	protected AFileFilter() {
		setFileFilterStrategy(fileFilterStrategy);
	}
	
	// set filter class
	public void setFileFilterStrategy(IFileFilter strategy) {
		fileFilterStrategy = strategy;
	}
	
	// return filter type
	public FileFilter getFormatFilter() {
		return fileFilterStrategy.formatFilter();
	}
}
