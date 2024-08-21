package com.dov.sbapp.rest.manager;

import com.dov.sbapp.SbappApplication;
import com.dov.sbapp.files.FileHandlerHelper;
import com.dov.sbapp.mdc.dec.SbaAsyncTaskDecorator;
import com.dov.sbapp.rest.entity.TrackingRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class SbappManager {

    public static final String FILE_FOR_UNIQUE_REQ_IDS = "REQ_ID_FILE";
    public static final String FILE_EXT = ".txt";
    public static final Logger logger = LogManager.getLogger(SbappApplication.class);

    public TrackingRequest getSingleRequestId() throws Exception{

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setTaskDecorator(new SbaAsyncTaskDecorator());

        TrackingRequest trackingRequest = null;
        String todayDateAndMonth = getTodayInStringFormat();
        String fileName = FILE_FOR_UNIQUE_REQ_IDS+"_"+todayDateAndMonth+FILE_EXT;
        // Generate a new file
        FileHandlerHelper helper = new FileHandlerHelper();
        File generatedFile = helper.createFileForReqIds(fileName);
        BufferedWriter writer = new BufferedWriter(new FileWriter(generatedFile));

        try {
            // Generate single unique request id and return the json response
            String randomRequestId = UUID.randomUUID().toString();
            // Check if request id already exists. If not, then write the new request id to the file
            if(!helper.searchReqIdExists(generatedFile.getAbsolutePath(), randomRequestId)){
                //helper.writeToFile(generatedFile, randomRequestId);
                trackingRequest = new TrackingRequest();
                trackingRequest.setCustomerId(randomRequestId);
                writer.write(randomRequestId);
                writer.newLine();
                logger.info("Generated single unique request id {}", randomRequestId);
            }else{
                logger.info("Generated single unique request id already exists in file");
                throw new RuntimeException();
            }
        }catch (
                IOException e){
            logger.info(e);
        }finally {
            writer.close();
        }

        return trackingRequest;
    }


    /*
     * This method will generate 100 new unique request id's
     */
    public ResponseEntity<List<TrackingRequest>> getMultipleRequestIds() throws Exception {

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setTaskDecorator(new SbaAsyncTaskDecorator());

        TrackingRequest trackingRequest = null;
        List<TrackingRequest> trackingRequestList = null;
        String todayDateAndMonth = getTodayInStringFormat();
        String fileName = FILE_FOR_UNIQUE_REQ_IDS+"_"+todayDateAndMonth+FILE_EXT;
        // Generate a new file
        FileHandlerHelper helper = new FileHandlerHelper();
        File generatedFile = helper.createFileForReqIds(fileName);
        BufferedWriter writer = new BufferedWriter(new FileWriter(generatedFile));

        try {
            trackingRequestList = new ArrayList<TrackingRequest>();
            // Generate unique request id's and write it to the above file one by one
            for (int i = 0; i < 100; i++) {
                String randomRequestId = UUID.randomUUID().toString();
                // Check if request id already exists. If not, then write the new request id to the file
                if(!helper.searchReqIdExists(generatedFile.getAbsolutePath(), randomRequestId)){
                    //helper.writeToFile(generatedFile, randomRequestId);
                    trackingRequest = new TrackingRequest();
                    trackingRequest.setCustomerId(randomRequestId);
                    trackingRequestList.add(trackingRequest);
                    writer.write(randomRequestId);
                    writer.newLine();
                    logger.info("Generated unique request id {}", randomRequestId);
                }else{
                    logger.info("Generated unique request id already exists in file");
                    throw new RuntimeException();
                }
            }
        }catch (
                IOException e){
            logger.info(e);
        }finally {
            writer.close();
        }
        return ResponseEntity.ok(trackingRequestList);
    }


    private String getTodayInStringFormat(){
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH);
        int todayDate = calendar.get(Calendar.DATE);
        long milliSecs = calendar.getTimeInMillis();
        return todayDate+"_"+month+"_"+milliSecs;
    }

}
