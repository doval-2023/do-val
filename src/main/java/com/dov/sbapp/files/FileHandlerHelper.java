package com.dov.sbapp.files;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

public class FileHandlerHelper {

    public static final Logger logger = LogManager.getLogger(FileHandlerHelper.class);
    // This method will create a file in the project directory
    public File createFileForReqIds(String fileNameWithExt) throws Exception{

        File file = new File(fileNameWithExt); //initialize File object and passing path as argument
        boolean result;
        try
        {
            result = file.createNewFile();//creates a new file
            if(result){
                logger.info("New file create with name : {} ---> file location : {}", fileNameWithExt, file.getCanonicalPath());
            }else{
                logger.info("File already exist at location: {} ---> ", file.getCanonicalPath());
            }

        }catch (IOException e){
            logger.info("Error while trying to create a file ---> ", e);
            e.printStackTrace();    //prints exception if any
        }

        return file;
    }

    public void writeToFile(File file, String stringToWrite) throws IOException{
        try {
            logger.info("writing to file started...");
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(stringToWrite);
            writer.close();
            logger.info("writing to file completed...");
        } catch (IOException e) {
            logger.info("Exception while writing to file : {} "+ e);
            throw new RuntimeException(e);
        }
    }

    public boolean searchReqIdExists(String absolutePath, String reqId) throws IOException {
        boolean reqIdExists = false;
        InputStream inputStream = null;
        try {
            File fileFromLocalDir = new File(absolutePath);
            inputStream = new FileInputStream(fileFromLocalDir);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            while(reader.ready()) {
                String line = reader.readLine();
                if(line.trim().equalsIgnoreCase(reqId.trim())){
                    reqIdExists = true;
                    logger.info("The generated request Id already exists : {}", line);
                    break;
                }
            }
        }
        finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return reqIdExists;
    }

}
