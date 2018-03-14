package san.video.subtitle.player;

import android.media.MediaPlayer;
import android.widget.TextView;

import java.io.File;
import java.io.InvalidObjectException;
import java.util.List;

/**
 * Created by sanyatihan on 13-Mar-18.
 */

public class SanVideoSubtitle {

    private static String subFormat;

    public static String renderAndShowSubtitle(File file,int currentPosition,int duration) throws InvalidObjectException {
        if(file.getName().endsWith("srt")){
            subFormat = "srt";
            return new SanSRT().getCurrentSubtitle(file,currentPosition,duration);


        } else if(file.getName().endsWith("ass")) {
            subFormat = "ass";
            return new SanAss().getCurrentSubtitle(file,currentPosition,duration);

        }else {
            subFormat="";
            throw new InvalidObjectException("This is not a valid subtitle file extension.");

        }
    }

    public static void renderAndShowSubtitle(File file, MediaPlayer mediaPlayer, TextView textView) throws InvalidObjectException {
        if(file.getName().endsWith("srt")){
            new SanSRT().renderSubtitle(file,mediaPlayer,textView).show();
            subFormat = "srt";

        } else if(file.getName().endsWith("ass")) {
            new SanAss().renderSubtitle(file,mediaPlayer,textView).show();
            subFormat = "ass";
        }else {
            subFormat="";
            throw new InvalidObjectException("This is not a valid subtitle file extension.");
        }

    }

    public static void setSubtitleSpeed(int speed){
        if(subFormat.equals("srt")){
            new SanSRT().speed(speed);
        } else if(subFormat.equals("ass")){
            new SanAss().speed(speed);
        }

    }

    public static void stopSubtitle(){
        if(subFormat.equals("srt")){
            new SanSRT().stopSubtitle();
        } else if(subFormat.equals("ass")){
            new SanAss().stopSubtitle();
        }
    }

    public static Boolean isRunning(){
        if(subFormat.equals("srt")){
          return  new SanSRT().isRunning();
        } else if(subFormat.equals("ass")){
            return new SanAss().isRunning();
        }
        return false;
    }

    public static List<String> getSubtitleList(){
        if(subFormat.equals("srt")){
            return  new SanSRT().getSubtitleList();
        } else if(subFormat.equals("ass")){
            return new SanAss().getSubtitleList();
        }
        return null;
    }

}
