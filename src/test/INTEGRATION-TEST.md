Product: Integration tests for WSO2 EI Bigquery connector

Pre-requisites:

 - Maven 3.x
 - Java 1.8

Tested Platform: 

 - Mac OX 10.12.6
 - WSO2 EI 6.4.0
 - Java 1.8

Steps to follow in setting integration test.

 1. Download EI 6.4.0 zip file from the official site and place it into "{BIGQUERY_CONNECTOR_HOME}/repository/" folder.

 2. Set authorization details:
 
        i)   Using the URL "https://accounts.google.com/SignUp" create a Google account.
        ii)  Go to "https://developers.google.com/oauthplayground/".
        iii) Authorize BigQuery API from "Select & authorize APIs" by selecting all the scopes available.
        iv)  Then go to "Exchange authorization code for tokens" and click on "Exchange authorization code for token" button and get the access token from "Access token" box (Note down the access token for future use.).
        v)   Go to "https://console.developers.google.com/" and log in with the created google account and create a new project using the dropdown in the top bar(Note down the project Id for future use.). 
        vi)  Enable BigQuery API by navigating to the "APIs" tab which is under "APIs & auth" tab.
        vii) Go to "Credentials" tab which is under "APIs & auth" tab and add credentials by selecting OAuth 2.0 client ID option.( Configure consent screen and then create client ID for 'Web application' type of applications. Note down the redirect uri for future use.)
        viii)Note down the client ID and client secret for future use.
        ix)  Get the authorization code by sending a GET request using url, https://accounts.google.com/o/oauth2/auth?redirect_uri=<redirect_uri>&response_type=code&client_id=<client_ID>&scope=https://www.googleapis.com/auth/bigquery&approval_prompt=force&access_type=offline (Replace <redirect_uri> and <client_ID> with the redirect uri and client ID values noted in step vii and viii. Note down the authorization code for future use.)
        x)   Get the access token and refresh token by sending a POST request to the url https://www.googleapis.com/oauth2/v3/token with x-www-form-urlencoded body with code,client_id,client_secret,redirect_uri values noted before and with grant_type value "authorization_code" (Note down the access token and refresh token for future use.).


 3. Update the Bigquery properties file at location "<BIGQUERY_CONNECTOR_HOME>/src/test/resources/artifacts/ESB/connector/config" and "<BIGQUERY_CONNECTOR_HOME>/repository/" as below.

        i)   apiUrl             -  The API URL of Bigquery(e.g. https://www.googleapis.com) .
        ii)  projectId          -  A valid Project ID.
        iii) datasetId          -  A valid Dataset ID. 
        iv)  tableId            -  A valid Table ID.
        v)   query              -  A query string, following the BigQuery query syntax, of the query to execute(This should be always 'SELECT count(*) FROM [publicdata:samples.github_nested]').   
        vi)  defaultDatasetId   -  A unique ID for the dataset, without the project name. The ID must contain only letters (a-z, A-Z), numbers (0-9), or underscores (_). The maximum length is 1,024 characters.
        vii) runQueryProjectId  -  Id of the project noted in above step 3(v). 
        viii)clientId           - Use the Client ID obtained in the above step.
        ix)  clientSecret       - Use the Client Secret obtained in the above step.
        x)   accessToken        - Use the accessToken obtained in the above step.
        x1)  refreshToken       - Use the refreshToken obtained in the above step.

 4. Navigate to "<BIGQUERY_CONNECTOR_HOME>/" and run the following command.<br/>
     $ mvn clean install -Dskip-tests=false
