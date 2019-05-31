package com.hotlink.webapp;

import com.hotlink.data.model.RecognitionSamplePicture;
import com.hotlink.webapp.request.MatchRequest;
import com.hotlink.webapp.request.OriginalPicAddRequest;
import com.hotlink.webapp.response.MatchResponse;
import com.hotlink.business.impl.ScanHandlerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.wechatvr.framework.common.service.rs.api.ServiceResponse;

import javax.annotation.Resource;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@RestController
@RequestMapping("/webar/*")
public class WebArService {

    @Resource(name = "scanHandlerImpl")
    private ScanHandlerImpl scanHandler;

    private static final Logger LOG = LoggerFactory.getLogger(WebArService.class);

    @PostMapping("match")
    public MatchResponse getMatchResult(@RequestBody MatchRequest request) {

        LOG.info("match image {}", request.getPic());

        MatchResponse response = new MatchResponse();
//
//        response.setMatched(scanHandler.isMatched(request.getActivityId(), request.getPageNumber(), request.getPic()));

        Thread.currentThread();
        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        response.setMatched(true);
//        LOG.info("match result {}", scanHandler.isMatched(request.getActivityId(), request.getPageNumber(), request.getPic()));
        return response;
    }

    @PostMapping("realMatch")
    public MatchResponse getMatchResultTest(@RequestBody MatchRequest request) {

        //LOG.info("match image {}", request.getPic());

        MatchResponse response = new MatchResponse();

        response.setMatched(scanHandler.isMatched(request.getActivityId(), request.getPageNumber(), request.getPic()));

        LOG.info("match result {}", scanHandler.isMatched(request.getActivityId(), request.getPageNumber(), request.getPic()));
        return response;
    }

    @PostMapping("add")
    public ServiceResponse originalPicAdd(@RequestBody OriginalPicAddRequest request) {
        List<RecognitionSamplePicture> recognitionSamplePictureList = request.getRecognitionSamplePictures();

        scanHandler.addRecognitionSamplePicturesByActivityId(recognitionSamplePictureList);

        return new ServiceResponse();
    }
}
