package com.dov.sbapp;


import com.dov.sbapp.files.FileHandlerHelper;
import com.dov.sbapp.mdc.dec.SbaAsyncTaskDecorator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@SpringBootApplication
public class SbappApplication {

	public static final String FILE_FOR_UNIQUE_REQ_IDS = "REQ_ID_FILE";
	public static final String FILE_EXT = ".txt";
	public static final Logger logger = LogManager.getLogger(SbappApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SbappApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext appCtx){
		return args -> {

			ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
			executor.setTaskDecorator(new SbaAsyncTaskDecorator());

			String todayDateAndMonth = getTodayInStringFormat();
			String fileName = FILE_FOR_UNIQUE_REQ_IDS+"_"+todayDateAndMonth+FILE_EXT;
			// Generate a new file
			FileHandlerHelper helper = new FileHandlerHelper();
			File generatedFile = helper.createFileForReqIds(fileName);
			BufferedWriter writer = new BufferedWriter(new FileWriter(generatedFile));

			try {

				// Generate unique request id's and write it to the above file one by one
				for (int i = 0; i < 100; i++) {
					String randomRequestId = UUID.randomUUID().toString();
                    // Check if request id already exists. If not, then write the new request id to the file
					if(!helper.searchReqIdExists(generatedFile.getAbsolutePath(), randomRequestId)){
						//helper.writeToFile(generatedFile, randomRequestId);
						writer.write(randomRequestId);
						writer.newLine();
						logger.info("Generated unique request id {}", randomRequestId);
					}else{
						logger.info("Generated unique request id already exists in file");
						throw new RuntimeException();
					}

				}

			}catch (IOException e){
				logger.info(e);
			}finally {
				writer.close();
			}
		};
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
