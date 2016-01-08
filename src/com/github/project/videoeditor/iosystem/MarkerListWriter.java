package com.github.project.videoeditor.iosystem;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.github.project.videoeditor.container.Marker;
import com.github.project.videoeditor.model.MarkerHandler;

/**
 * 
 * @author Peter Gessler
 * @version 1.0
 * @DevelopmentDate 31.12.2015
 * @LastUpdate -
 * @Assignment Class to write content to text file.
 * 
 */
public class MarkerListWriter extends AFileOutput {

	private static volatile MarkerListWriter singleton = null;

	private FileWriter writer;

	private String storePath;

	public static synchronized MarkerListWriter getInstance() {
		if (singleton == null)
			singleton = new MarkerListWriter();
		return singleton;
	}

	// Constructor
	private MarkerListWriter() {
		super();
	}

	public void setStorePath(String path) {

		storePath = path;
	}

	public void writeMarkerlist(List<Marker> markerList) {

		try {
			writer = new FileWriter(storePath);

			for (Marker marker : markerList) {

				StringBuffer line = new StringBuffer();
				line.append(marker.getStartTime());
				line.append("\t");
				line.append(marker.getEndTime());
				line.append("\t");
				line.append(marker.getMarkerName());
				line.append(System.getProperty("line.separator"));

				writer.write(line.toString());
			}
			writer.close();

		} catch (IOException e) {

		}
	}

}
