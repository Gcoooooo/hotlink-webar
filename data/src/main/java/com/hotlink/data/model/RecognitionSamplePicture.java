package com.hotlink.data.model;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;

public class RecognitionSamplePicture implements Serializable {

    private static final long serialVersionUID = 3652037019246742831L;
    
    private Integer id;
    private String samplePic;
    private String url;
    private Integer activityId;
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getSamplePic() {
        return samplePic;
    }
    
    public void setSamplePic(String samplePic) {
        this.samplePic = samplePic;
    }
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public Integer getActivityId() {
        return activityId;
    }
    
    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }
    
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}

