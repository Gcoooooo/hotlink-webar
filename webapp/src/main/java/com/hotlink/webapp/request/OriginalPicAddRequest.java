package com.hotlink.webapp.request;

import com.hotlink.data.model.RecognitionSamplePicture;

import java.io.Serializable;
import java.util.List;

public class OriginalPicAddRequest implements Serializable {
    private List<RecognitionSamplePicture>  recognitionSamplePictures;

    public List<RecognitionSamplePicture> getRecognitionSamplePictures() {
        return recognitionSamplePictures;
    }

    public void setRecognitionSamplePictures(List<RecognitionSamplePicture> recognitionSamplePictures) {
        this.recognitionSamplePictures = recognitionSamplePictures;
    }
}
