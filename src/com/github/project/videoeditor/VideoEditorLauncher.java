package com.github.project.videoeditor;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

import com.github.project.videoeditor.gui.GUI;
import com.github.project.videoeditor.iosystem.IFileObserver;
import com.github.project.videoeditor.model.EditorHandler;


/**
 * 
 * @author Peter Gessler
 * @version 1.0
 * @DevelopmentDate 31.12.2015
 * @LastUpdate 03.01.2016
 * @Assignment Start video editor and initialize user interface.
 * 
 */

public class VideoEditorLauncher {

	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		final IFileObserver model = new EditorHandler();

		// start GUI
		GUI guiGeneral = new GUI("VideoEditor", model);
		guiGeneral.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		guiGeneral.pack();
		guiGeneral.setSize(1024, 768);
		guiGeneral.setVisible(true);

	}

}
