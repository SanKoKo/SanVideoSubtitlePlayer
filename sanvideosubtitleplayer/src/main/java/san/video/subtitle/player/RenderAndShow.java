package san.video.subtitle.player;

import android.media.MediaPlayer;
import android.os.Handler;
import android.text.Html;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

 class RenderAndShow {
 static Boolean running = true;
 private static int nullTime;
 static int speed=0;
	 static void show(final MediaPlayer mPlayer, final TextView mTextView, final ArrayList<MyData> myarrList) {
		
		final int mpDuration = mPlayer.getDuration();
		Runnable mRunnable = new Runnable() {
			
			@Override
			public void run() {
				for(int i=0;i<mpDuration;i++)
				{
					if(!running)
					{
						return;
					} 
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					try {
					int hour = (int) TimeUnit.MILLISECONDS.toHours(mPlayer.getCurrentPosition());
					int minute = (int) TimeUnit.MILLISECONDS.toMinutes(mPlayer.getCurrentPosition());
		            int second = (int) TimeUnit.MILLISECONDS.toSeconds(mPlayer.getCurrentPosition())+speed;
		        	final String mTime;
					if(myarrList.get(0).getStart().length()<8) {
						mTime = String.format(Locale.getDefault(), "%01d:%02d:%02d", hour, minute % 60, second % 60);
					}else {
						mTime = String.format(Locale.getDefault(), "%02d:%02d:%02d", hour, minute % 60, second % 60);
					}
		            new Thread(new Runnable() {
						
						@Override
						public void run() {
							mTextView.post(new Runnable() {
								
								@Override
								public void run() {
									if(!running)
									{
										return;
									}
									String showText = (String) FindTheEqualTimeSubtitle(mTime,myarrList);
									if(showText !=null) {
										nullTime =0;
										mTextView.setText(Html.fromHtml(showText));	
									} else {
									++nullTime;
									if(nullTime>100) {
										mTextView.setText("");
									}
									}
									
								}
							});
						}
		            
					}).start();
				} catch (IllegalStateException ie) {ie.printStackTrace();} 
				}
			}
		};
		new Thread(mRunnable).start();
	}

	private static CharSequence FindTheEqualTimeSubtitle(String mTime, ArrayList<MyData> myarrList) {
		
		for(int i=0;i<myarrList.size();i++)
		{
			if(mTime.equals(myarrList.get(i).getStart())) {
				return myarrList.get(i).getSubTitle();
			} else
			if(mTime.equals(myarrList.get(i).getEnd())){
				return " ";
			}
		}
		return null;
	}
	
	static void stop() {
		running = false;
	}

	static void start() {
		running = true;
	}

	  static String getCurrentSubtitle(ArrayList<MyData> myarrList, int currentPosition, int duration) {
		 int hour = (int) TimeUnit.MILLISECONDS.toHours(currentPosition);
		 int minute = (int) TimeUnit.MILLISECONDS.toMinutes(currentPosition);
		 int second = (int) TimeUnit.MILLISECONDS.toSeconds(currentPosition)+speed;
		 final String mTime;
		 if(myarrList.get(0).getStart().length()<8) {
			 mTime = String.format(Locale.getDefault(), "%01d:%02d:%02d", hour, minute % 60, second % 60);
		 }else {
			 mTime = String.format(Locale.getDefault(), "%02d:%02d:%02d", hour, minute % 60, second % 60);
		 }
		 String sub =(String) FindTheEqualTimeSubtitle(mTime,myarrList);
		 if(sub!=null){

			recentSub =  sub;
			 nullTime=0;
		 	return sub;

		 } else {
		 	++nullTime;
		 	if(nullTime>=100){
		 		recentSub = "";
		 		return "";
			} else {
				return recentSub;
			}
		 }
	 }
	 private static String recentSub;
 }
