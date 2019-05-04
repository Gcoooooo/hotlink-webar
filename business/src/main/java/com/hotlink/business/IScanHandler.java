package com.hotlink.business;

import com.hotlink.data.model.RecognitionSamplePicture;
import java.util.List;

public interface IScanHandler {
	
	public boolean isMatched(int activityId, String picDetect);
	
	public List<RecognitionSamplePicture> getRecognitionSamplePictures(int id);

}