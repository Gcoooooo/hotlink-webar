package com.hotlink.data.db.mapper;

import com.hotlink.data.model.RecognitionSamplePicture;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component("recognitionMapper")
public interface RecognitionMapper {
    
    public List<RecognitionSamplePicture> selectRecognitionSamplePicturesByActivityId(@Param("activityId") int activityId);

    public List<RecognitionSamplePicture> selectRecognitionSamplePicturesByActivityIdAndPageNumber(@Param("activityId") int activityId, @Param("pageNumber") int pageNumber);

    public void updateRecognitionSamplePictures(List<RecognitionSamplePicture> recogSamplePics);
    
    public void insertRecognitionSamplePictures(List<RecognitionSamplePicture> recogSamplePics);
    
    public void deleteRecognitionSamplePicturesByActivityId(@Param("activityId") int activityId);

}
