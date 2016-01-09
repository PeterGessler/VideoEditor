package com.github.project.videoeditor.model;

import java.awt.image.BufferedImage;
import java.io.File;

import com.xuggle.mediatool.IMediaReader;
import com.xuggle.mediatool.IMediaWriter;
import com.xuggle.mediatool.MediaListenerAdapter;
import com.xuggle.mediatool.MediaToolAdapter;
import com.xuggle.mediatool.ToolFactory;
import com.xuggle.mediatool.event.IAudioSamplesEvent;
import com.xuggle.mediatool.event.IReadPacketEvent;
import com.xuggle.mediatool.event.IVideoPictureEvent;
import com.xuggle.mediatool.event.IWriteHeaderEvent;
import com.xuggle.mediatool.event.IWritePacketEvent;
import com.xuggle.mediatool.event.IWriteTrailerEvent;
import com.xuggle.xuggler.Global;

public class Cutter extends MediaListenerAdapter {

	private String TMP_DIR;

	private IMediaWriter writers[];

	public Cutter(double[] starts, double[] ends, String[] markerName,
			String videoPathin, String videoPathout)

	{

		writers = new IMediaWriter[starts.length];

		IMediaReader reader = ToolFactory.makeReader(videoPathin);

		reader.setBufferedImageTypeToGenerate(BufferedImage.TYPE_3BYTE_BGR);

		TMP_DIR = videoPathout;

		for (int i = 0; i < starts.length; i++)

		{

			starts[i] *= Global.DEFAULT_PTS_PER_SECOND;

			ends[i] *= Global.DEFAULT_PTS_PER_SECOND;

			writers[i] = ToolFactory.makeWriter(videoPathout + "/"
					+ markerName[i] + ".flv", reader);

		}

		videoCheck checkPos = new videoCheck();

		reader.addListener(checkPos);

		boolean updatedS = false;

		boolean updatedE = false;

		int rp = 0;

		while (reader.readPacket() == null)

		{

			if (!updatedS && (checkPos.timeInMilisec >= starts[rp]))

			{

				updatedS = true;

				updatedE = false;

				checkPos.addListener(writers[rp]);

			}

			if (!updatedE && (checkPos.timeInMilisec >= ends[rp]))

			{

				updatedE = true;

				checkPos.removeListener(writers[rp]);

				writers[rp].close();

				rp++;

				if (rp == starts.length)

				{
					// writer.close();

				}

				else

					updatedS = false;

			}

		}

	}

	public void deleteAllFromTmpFolder()

	{

		System.out.print("deleting tmpfile and folder" + TMP_DIR + " \n");

		File td = new File(TMP_DIR);

		String[] fileslist = td.list();

		for (String fpath : fileslist)
			new File(TMP_DIR + "/" + fpath).delete();

		td.delete();

	}

	class videoCheck extends MediaToolAdapter

	{

		public Long timeInMilisec = (long) 0;

		public boolean convert = true;

		@Override
		public void onVideoPicture(IVideoPictureEvent event)

		{

			timeInMilisec = event.getTimeStamp();

			if (convert)
				super.onVideoPicture(event);

		}

		@Override
		public void onAudioSamples(IAudioSamplesEvent event) {
			if (convert)
				super.onAudioSamples(event);
		}

		@Override
		public void onWritePacket(IWritePacketEvent event) {
			if (convert)
				super.onWritePacket(event);
		}

		@Override
		public void onWriteTrailer(IWriteTrailerEvent event)

		{

			if (convert)

				super.onWriteTrailer(event);

		}

		@Override
		public void onReadPacket(IReadPacketEvent event) {
			if (convert)
				super.onReadPacket(event);
		}

		@Override
		public void onWriteHeader(IWriteHeaderEvent event) {
			if (convert)
				super.onWriteHeader(event);

		}
	}

}
