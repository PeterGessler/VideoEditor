package com.github.project.videoeditor.iosystem;

import javax.swing.filechooser.FileFilter;


/**
 * 
 * @author Peter Gessler
 * @version 1.0
 * @DevelopmentDate 31.12.2015
 * @LastUpdate 03.01.2016
 * @Assignment Interface to define methods of concrete filter strategy.
 * 
 */

public interface IFileFilter {
	
	public FileFilter formatFilter();
}
