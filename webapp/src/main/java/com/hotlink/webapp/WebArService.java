package com.hotlink.webapp;

import com.hotlink.webapp.request.MatchRequest;
import com.hotlink.webapp.response.MatchResponse;
import com.hotlink.business.impl.ScanHandlerImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/webar/*")
public class WebArService {

    @Resource(name = "scanHandlerImpl")
    private ScanHandlerImpl scanHandler;

    @PostMapping("match")
    public MatchResponse getMatchResult(@RequestBody MatchRequest request) {

        //LOG.info("match image {}", request.getPic());

        MatchResponse response = new MatchResponse();

        response.setMatched(scanHandler.isMatched(request.getActivityId(), request.getPic()));

        //LOG.info("match result {}", scanHandler.isMatched(request.getActivityId(), request.getPic()));
        return response;
    }

    @GetMapping("test")
    public MatchResponse getMatchResult(@RequestParam Integer testId) {

        //LOG.info("match image {}", request.getPic());

        MatchResponse response = new MatchResponse();

        response.setMatched(true);

        //LOG.info("match result {}", scanHandler.isMatched(request.getActivityId(), request.getPic()));
        return response;
    }
}
