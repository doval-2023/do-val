package com.dov.sbapp;

import com.dov.sbapp.rest.entity.TrackingRequest;
import com.dov.sbapp.rest.manager.SbappManager;
import com.dov.sbapp.rest.response.helper.JsonResponseHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/next-tracking-number")
public class SbaController {

    public static final String SINGLE_REQUEST_ID = "SINGLE_REQUEST_ID";
    public static final String MULTIPLE_REQUEST_IDS = "MULTIPLE_REQUEST_IDS";
    public static final Logger logger = LogManager.getLogger(SbaController.class);

    @GetMapping(path = "/")
    public ResponseEntity<Object> getSingleRequestId() throws Exception {

        TrackingRequest singleTracRequest = null;
        try{
            SbappManager sbappManager = new SbappManager();
            singleTracRequest = sbappManager.getSingleRequestId();
        }catch(Exception exception){
            logger.info("Exception while trying to generate single request id : {}", exception);
        }
        return JsonResponseHelper.generateJsonResponse(SINGLE_REQUEST_ID, HttpStatus.OK, singleTracRequest);
    }

    @GetMapping(path = "/multiple-request-ids")
    public ResponseEntity<Object> getMultipleRequestIds() throws Exception {

        ResponseEntity<List<TrackingRequest>> multipleTracRequests = null;
        try{
            SbappManager sbappManager = new SbappManager();
            multipleTracRequests = sbappManager.getMultipleRequestIds();
        }catch(Exception exception){
            logger.info("Exception while trying to generate single request id : {}", exception);
        }
        return JsonResponseHelper.generateJsonResponse(MULTIPLE_REQUEST_IDS, HttpStatus.OK, multipleTracRequests);
    }


}
