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

 final class SanSRT {
	private  ArrayList<String> arrayList;
	private  ArrayList<MyData> myarrList;
	private   ArrayList<Integer> mTitle;
	private MediaPlayer mPlayer;
	private  TextView mTextView;

		 SanSRT() {
			super();
	        arrayList = new ArrayList<>();
	        myarrList = new ArrayList<>();
	        mTitle = new ArrayList<>();
		}
	    
	      SanSRT renderSubtitle(File file, MediaPlayer player, TextView textView) {
	    	this.mPlayer = player;
	    	this.mTextView = textView;
	    	try {
				ReadFromSRT(file);
				myarrList = Trimmer.FormatSRT(arrayList,mTitle);
	    	} catch (IOException e) {
				e.printStackTrace();
			}
	    	return this;
	    }

		private void ReadFromSRT(File filename) throws IOException{
			
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filename),"UTF-8"));

	        while (true)
	        {
	            String str = bufferedReader.readLine();
	            if(str == null) {
	                bufferedReader.close();
	                return;
	            }
	            if(str.contains(Formater.SRTF1))
	            {
	                mTitle.add(arrayList.size()-1);
	            }
	           arrayList.add(str);
	        }
		}
		
		  void show() {
			if(mPlayer!=null && mPlayer.isPlaying())
			{
				RenderAndShow.start();
				RenderAndShow.show(mPlayer,mTextView,myarrList);
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

	  String getCurrentSubtitle(File file, int currentDuration, int duration) {
		  try {
			  ReadFromSRT(file);
			  myarrList = Trimmer.FormatSRT(arrayList,mTitle);
		  } catch (IOException e) {
			  e.printStackTrace();
		  }
		 return RenderAndShow.getCurrentSubtitle(myarrList,currentDuration,duration);
	 }

	  List<String> getSubtitleList() {
		 	List<String> list = new ArrayList<>();
		 	for(MyData myData : myarrList){
		 		list.add(myData.getSubTitle());
			}
		 return list;
	 }
 }
