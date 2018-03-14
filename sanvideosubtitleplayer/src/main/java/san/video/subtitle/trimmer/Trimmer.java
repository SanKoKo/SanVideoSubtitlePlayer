package san.video.subtitle.trimmer;

import java.util.ArrayList;
import java.util.StringTokenizer;

import san.video.subtitle.formater.Formater;
import san.video.subtitle.player.MyData;

public class Trimmer {
	
	public static ArrayList<MyData> FormatSRT(ArrayList<String> arrayList, ArrayList<Integer> mTitle) {
		ArrayList<MyData> mList = new ArrayList<MyData>();
		int count=0;
		StringBuilder sb = new StringBuilder();
		 for( int i =0; i<arrayList.size();i++)
	        {
	            if(arrayList.get(i).contains(Formater.SRTF1)) {
	                String[] strTime = arrayList.get(i).split(Formater.SRTF1);
	                String[] strStartTime1 = strTime[0].split(Formater.SRTF2);
	                String[] strEndTime2 = strTime[1].split(Formater.SRTF2);
	                ++count;
	            if(count<mTitle.size()) {
	                for (int ii = i + 1; ii < mTitle.get(count); ii++) {
	                    sb.append(arrayList.get(ii));
	                    sb.append("\n");
	                }
	            } else {
	                for (int ii = i + 1; ii < arrayList.size(); ii++) {
	                    sb.append(arrayList.get(ii));
	                    sb.append("\n");
	                }
	            }
	               mList.add(new MyData(strStartTime1[0],strStartTime1[1],strEndTime2[0],strEndTime2[1],sb.toString()));
	                sb = new StringBuilder();
	            }
	        }
		return mList;
	}

	public static ArrayList<MyData> FormatASS(ArrayList<String> arrNormalLine) {
		ArrayList<MyData> mList = new ArrayList<MyData>();
		for(String strNormal : arrNormalLine){
			ArrayList<String> lst = new ArrayList<String>();
			StringTokenizer sTokenizer = new StringTokenizer(strNormal,Formater.SRTF2);
			while (sTokenizer.hasMoreTokens()) {
				lst.add(sTokenizer.nextToken());
			}
			String startTime [] = lst.get(1).split(Formater.ASS2);
			String endTime [] = lst.get(2).split(Formater.ASS2);
			String data="";
			try {
				
				if(lst.size()>7)
				{
					for(int i=7;i<lst.size();i++)
					{
						data += lst.get(i);
					}
				} else {
				data = lst.get(7);
				}
				if(data.contains("\\N")){
					data = data.replaceAll("\\\\N", "</br>");
				}
			} catch (IndexOutOfBoundsException ie) {
				data = "";
			}
			mList.add(new MyData(startTime[0],startTime[1],endTime[0],endTime[1],data));
		}
		
		return mList;
	}

	
}
