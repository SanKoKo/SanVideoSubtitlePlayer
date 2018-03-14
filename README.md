# SanVideoSubtitlePlayer
This library is design for showing subtitle in video.

# To use this library 

add in project gradle

     allprojects {
       repositories {
        ...
        maven { url 'https://jitpack.io' }
      }
    }
    
    
add in app gradle

    dependencies {
              compile 'com.github.SanKoKo:SanVideoSubtitlePlayer:1'
      }
      
      
      
 # All method in this library
 
     try {
                SanVideoSubtitle.renderAndShowSubtitle(file,mediaPlayer,tv);
                SanVideoSubtitle.setSubtitleSpeed(-7);
            } catch (InvalidObjectException e) {
                e.printStackTrace();
            }

            SanVideoSubtitle.isRunning();
            SanVideoSubtitle.stopSubtitle();
            
            
