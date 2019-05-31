package com.hotlink.business.impl;

import com.hotlink.business.IScanHandler;
import com.hotlink.business.util.JNAUtility;
import com.hotlink.business.util.RedisService;
import com.hotlink.data.db.mapper.RecognitionMapper;
import com.hotlink.data.model.RecognitionSamplePicture;
import com.ochafik.lang.jnaerator.runtime.MangledFunctionMapper;
import com.sun.jna.Native;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Component;
import org.wechatvr.surf.SurfLibrary;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("scanHandlerImpl")
@EnableCaching
public class ScanHandlerImpl implements IScanHandler {

    private static final Logger LOG = LoggerFactory.getLogger(ScanHandlerImpl.class);

	@Autowired
	private RecognitionMapper recognitionMapper;

    @Autowired
    private RedisService redisService;

	private static final String OS_NAME = System.getProperty("os.name").toLowerCase();
	
	private static final SurfLibrary INSTANCE = (SurfLibrary) load();
	
    private static Object load() {

    	if (OS_NAME.indexOf("linux") != -1) {
	        String tmpLibDir = JNAUtility.extractLibraries("surflib", new String[] {
	                "surflib.so"
	        });
	        LOG.info("jna.library.path is {}", tmpLibDir);

	        System.setProperty("jna.library.path", tmpLibDir);

	        Map<String, Object> options = new HashMap<>();
            for (Map.Entry<Object, Object> entry : MangledFunctionMapper.DEFAULT_OPTIONS.entrySet()) {
                options.put(String.valueOf(entry.getKey()), entry.getValue());
            }

	        return Native.loadLibrary("surflib.so", SurfLibrary.class, options);
    	} else {
    		return null;
    	}
    }   
    
	public boolean isMatched(int activityId, int pageNumber, String picDetect) {
		int recognitionResult = 0;
		
		if (OS_NAME.indexOf("linux") != -1) { //当系统为linux时

		    List<RecognitionSamplePicture> picSamples = (List<RecognitionSamplePicture>) redisService.get("recognitionPic_"+activityId);
		    if (picSamples != null && picSamples.size() > 0) {
                LOG.info("从Redis中读取的原图像：" + picSamples.get(0));
            } else {
		    	picSamples = recognitionMapper.selectRecognitionSamplePicturesByActivityIdAndPageNumber(activityId, pageNumber);
				LOG.info("从数据库中读取的原图像：" + picSamples.get(0));
		    	redisService.set("recognitionPic_"+activityId, picSamples);
            }
		    
		    for (RecognitionSamplePicture picSample : picSamples) {
	
		        recognitionResult = INSTANCE.surf_match(ByteBuffer.wrap(picSample.getSamplePic().getBytes()), ByteBuffer.wrap(picDetect.getBytes()));
		        
		        if (recognitionResult == 1) {
		            return true;
		        }
		    }
            return false;
		} else {
			return true;
		}	
	}
	
	@Override 
	public List<RecognitionSamplePicture> getRecognitionSamplePicturesByActivityId(int id) {
		List<RecognitionSamplePicture> recognitionSamplePictures = recognitionMapper.selectRecognitionSamplePicturesByActivityId(id);
		
		return recognitionSamplePictures;
	}

	@Override
	public void addRecognitionSamplePicturesByActivityId(List<RecognitionSamplePicture> recognitionSamplePictures) {
		recognitionMapper.insertRecognitionSamplePictures(recognitionSamplePictures);
	}
}

