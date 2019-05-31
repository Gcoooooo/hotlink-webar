package com.hotlink.business;

import com.hotlink.data.model.RecognitionSamplePicture;
import java.util.List;

public interface IScanHandler {
	
	public boolean isMatched(int activityId, int pageNumber, String picDetect);
	
	public List<RecognitionSamplePicture> getRecognitionSamplePicturesByActivityId(int id);

	public void addRecognitionSamplePicturesByActivityId(List<RecognitionSamplePicture> recognitionSamplePictures);

}