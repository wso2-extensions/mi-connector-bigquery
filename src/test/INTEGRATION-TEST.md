Product: Integration tests for the WSO2 EI Bigquery connector

**Pre-requisites:**

 - Maven 3.x
 - Java 1.8

**Tested Platform:**

 - Mac OX 10.12.6
 - WSO2 EI 6.4.0
 - Java 1.8

Follow the steps given below to set up integration tests.

 1. Download WSO2 EI 6.4.0 zip file from the official site and place it in the "{BIGQUERY_CONNECTOR_HOME}/repository/" folder.

 2. Set authorization details:
 
        i)   Go to "https://accounts.google.com/SignUp" and create a Google account.
        ii)  Go to "https://developers.google.com/oauthplayground/".
        iii) Go to **Select & authorize APIs**, select all the available scopes, and authorize the BigQuery API.
        iv)  Select **Exchange authorization code for tokens** and click **Exchange authorization code for token**. You can then get the access token from the **Access token** section. Be sure to note the access token for future use.
        v)  Go to "https://console.developers.google.com/" and log in with the google account you already created. You can then create a new project using the drop down option in the top menu. Be sure to note the project ID for future use. 
        vi)  Go to the **API** tab under **APIs & auth** and enable the **BigQuery** API.
        vii) Go to the **Credentials** tab under **APIs & auth**, select **OAuth 2.0 client ID**, and add the credentials. You need to configure the consent screen and then create a client ID for a **Web application**. Be sure to note the redirect uri for future use.
        viii) Note the client ID and client secret for future use.
        ix) Get the authorization code by sending a GET request to the following URL (replace the <redirect_uri> and <client_ID> with the redirect uri and client ID values noted in the previous steps): https://accounts.google.com/o/oauth2/auth?redirect_uri=<redirect_uri>&response_type=code&client_id=<client_ID>&scope=https://www.googleapis.com/auth/bigquery&approval_prompt=force&access_type=offline.
      Note the authorization code for future use.
        x)   Get the access token and refresh token by sending a POST request to the URL given below. Be sure to use an x-www-form-urlencoded body with the <code>, <client_id>, <client_secret>, and <redirect_uri> values noted before, and also set the grant_type to **authorization_code**. Note the access token and refresh token for future use.

       https://www.googleapis.com/oauth2/v3/token.


 3. Update the Bigquery properties file (stored in the <BIGQUERY_CONNECTOR_HOME>/src/test/resources/artifacts/ESB/connector/config/ directory and the <BIGQUERY_CONNECTOR_HOME>/repository/ directory) as follows:

        i)   apiUrl             -  The API URL of Bigquery(e.g. https://www.googleapis.com).
        ii)  projectId          -  A valid project ID.
        iii) datasetId          -  A valid dataset ID. 
        iv)  tableId            -  A valid table ID.
        v)   query              -  A query string that complies with the BigQuery query syntax. This should always be 'SELECT count(*) FROM [publicdata:samples.github_nested]'.   
        vi)  defaultDatasetId   -  A unique ID for the dataset without the project name. The ID must contain only letters (a-z, A-Z), numbers (0-9), or underscores (_). The maximum length is 1,024 characters.
        vii) runQueryProjectId  - The ID of the project noted in step 3(v) above. 
        viii)clientId           - Use the client ID obtained in the above step.
        ix)  clientSecret       - Use the client Secret obtained in the above step.
        x)   accessToken        - Use the access token obtained in the above step.
        x1)  refreshToken       - Use the refresh token obtained in the above step.

 4. Navigate to the <BIGQUERY_CONNECTOR_HOME> directory and run the following command:
    ```mvn clean install -Dskip-tests=false```
