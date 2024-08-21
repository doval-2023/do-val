
## This is a Rest API based spring boot application to generate unique request ids.

## This project is available in the below branch of my Git repository.

## Repository name : do-val
## The above repository has two branches
## 1) master
## 2) do-val-sbapp-aug (This is the branch where you can find the latest code)

##  check out project from the above repository
##  load all maven dependencies
##  run the command > mvn clean install > package
##  run the spring boot app "SbappApplication.java"

##  ** GET SINGLE REQUEST ID **
##  open your browser and try the rest api url :  http://localhost:8080/next-tracking-number/

##  ** GET MULTIPLE REQUEST ID ** THIS WILL GENERATE NEXT 100 UNIQUE REQUEST ID'S **
##  open your browser and try the rest api url :  http://localhost:8080/next-tracking-number/multiple-request-ids

## After the request got served, a .txt file with unique request id's will be generated in your local
## directory along with the browser response.
## sample file format :: REQ_ID_FILE_19_7_1724078307772.txt



##  ** APPROACH **

## For now I am not validating any of the request params that are mentioned, 
## but I am using the request params as property values in my Entity(TrackingRequest) to return the response.

##  Created TrackingRequest.java for holding all the request param values.

##  Created SbaController.java class for handling rest api requests from the browser.

##  Created FileHandlerHelper.java class to create a file and write all the request id's that are generated.

##  Created SbappApplication.java to run the spring boot application.

##  Created JsonResponseHelper.java for returning JSON response.

##  Created LogManager for logging.
