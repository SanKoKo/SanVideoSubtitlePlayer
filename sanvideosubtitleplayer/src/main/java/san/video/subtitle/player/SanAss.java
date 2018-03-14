package san.video.subtitle.player;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import san.video.subtitle.formater.Formater;
import san.video.subtitle.trimmer.Trimmer;

import android.media.MediaPlayer;
import android.widget.TextView;

final class SanAss {
private ArrayList<String> arrNormalLine;
private ArrayList<MyData> arrTimeList;
private MediaPlayer mPlayer;
private TextView mtextView;
private String [] fontname;

	 SanAss() {
		super();
		arrNormalLine = new ArrayList<String>();
		arrTimeList = new ArrayList<MyData>();
	}
	
	
	 SanAss renderSubtitle(File file,MediaPlayer mediaPlayer,TextView textview) {
		this.mPlayer = mediaPlayer;
		this.mtextView = textview;
		try {
			ReadFromAssFile(file);
			arrTimeList = Trimmer.FormatASS(arrNormalLine);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this;
	}


	private void ReadFromAssFile(File file) throws IOException{
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));

        while (true)
        {
            String str = bufferedReader.readLine();
            if(str == null) {
                bufferedReader.close();
                return;
            }
            if(str.startsWith(Formater.ASS3))
            {
            	fontname = str.split(Formater.SRTF2);
            }
            if(str.startsWith(Formater.ASS1))
            {
            	str = str.replaceAll(Formater.ASS1, "");
                arrNormalLine.add(str);
            }
        }		
	}
	
	void show() {
		if(mPlayer!=null && mPlayer.isPlaying())
		{
			RenderAndShow.start();
			RenderAndShow.show(mPlayer,mtextView,arrTimeList);
		} else {
			RenderAndShow.stop();
		}
	}
	 void stopSubtitle() {
		RenderAndShow.running = false;
	}
	
	 Boolean isRunning() {
		return RenderAndShow.running;
	}
	
	 void startSubtitle() {
		RenderAndShow.running = true;
	}
	
	 void speed(int speed) {
		RenderAndShow.speed = speed;
	}
	
	 String getFontName() {
		return fontname[1];
	}

	 String getCurrentSubtitle(File file, int currentDuration, int duration) {
		 try {
			 ReadFromAssFile(file);
			 arrTimeList = Trimmer.FormatASS(arrNormalLine);
		 } catch (IOException e) {
			 e.printStackTrace();
		 }
		 return RenderAndShow.getCurrentSubtitle(arrTimeList,currentDuration,duration);

	}

	 List<String> getSubtitleList() {
		List<String> list = new ArrayList<>();
		for(MyData myData : arrTimeList){
			list.add(myData.getSubTitle());
		}
		return list;
	}
}
