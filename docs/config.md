# Configuring BigQuery Operations

[[Prerequisites]](#Prerequisites) [[Initializing the Connector]](#initializing-the-connector)

## Prerequisites

### Get user credentials

   1. Go to "https://accounts.google.com/SignUp" and create a Google account.
   2. Go to "https://developers.google.com/oauthplayground/".
   3. Select all the scopes available under **Select & authorize APIs** to authorize the BigQuery API.
   4. Select **Exchange authorization code for tokens** and click **Exchange authorization code for token**. You can then get the access token from the **Access token** section. Be sure to note the access token for future use.
   5. Go to "https://console.developers.google.com/" and log in with the google account you already created. You can then create a new project using the drop down option in the top menu. Be sure to note the project ID for future use. 
   6. Go to the **API** tab under **APIs & auth** and enable the **BigQuery** API.
   7. Go to the **Credentials** tab under **APIs & auth**, select **OAuth 2.0 client ID**, and add the credentials. 
   8. You need to configure the consent screen and then create a client ID for a **Web application**. Be sure to note the redirect uri, client ID and client secret for future use.
   9. Get the authorization code by sending a GET request to the following URL (replace the <redirect_uri> and <client_ID> with the redirect uri and client ID values noted in the previous steps): https://accounts.google.com/o/oauth2/auth?redirect_uri=<redirect_uri>&response_type=code&client_id=<client_ID>&scope=https://www.googleapis.com/auth/bigquery&approval_prompt=force&access_type=offline.
      Note the authorization code for future use.
  10. Get the access token and refresh token by sending a POST request to the URL given below. Be sure to use an x-www-form-urlencoded body with the <authorization_code>, <client_id>, <client_secret>, and <redirect_uri> values noted before, and also set the grant_type to **authorization_code**. Note the access token and refresh token for future use.
      https://www.googleapis.com/oauth2/v3/token.

### Using the service account
   
   1. Open the [Service Accounts](https://console.cloud.google.com/projectselector2/iam-admin/serviceaccounts?) page in the GCP console.
   2. Click **Select** to open a project.
   3. Select your project and click **Open**.
   4. Click **Create Service Account**.
   5. Enter a service account name, select a role you wish to grant to the service account, and then click **Save**.
   6. Go to the service account for which you wish to create a key, click **More** in that row, and then click **Create key**.
   7. Select the key type as P12 and click **Create**.

## Initializing the connector

To use the **BigQuery** connector, add the <bigquery.init> or <bigquery.getAccessTokenFromServiceAccount> element in your configuration before carrying out any other BigQuery operations. 

The **BigQuery** API requires all requests to be authenticated as a user or a service account. For more information, see https://cloud.google.com/bigquery/authentication. See https://developers.google.com/identity/protocols/OAuth2ServiceAccount for information on service account authentication.

**init**
```xml
<bigquery.init>
    <apiUrl>{$ctx:apiUrl}</apiUrl>
    <accessToken>{$ctx:accessToken}</accessToken>
    <clientSecret>{$ctx:clientSecret}</clientSecret>
    <client>{$ctx:clientId}</clientId>
    <refreshToken>{$ctx:refreshToken}</refreshToken>
    <registryPath>{$ctx:registryPath}</registryPath>
    <fields>{$ctx:fields}</fields>
    <prettyPrint>{$ctx:prettyPrint}</prettyPrint>
    <quotaUser>{$ctx:quotaUser}</quotaUser>
    <userIp>{$ctx:userIp}</userIp>
</bigquery.init>
```
**Properties** 
* apiUrl: The base endpoint URL of the BigQuery API.
* accessToken: The OAuth token for the BigQuery API.
* clientId:  The client ID for the BigQuery API.
* clientSecret: The client Secret for the BigQuery API.
* refreshToken: The refresh token for the BigQuery API.
* registryPath: The registry path to save the access token.
* fields: List of fields to be returned in the response.
* callback: The name of the JavaScript callback function that handles the response. Used in JavaScript JSON-P requests.
* apiKey: The API key. Required unless you provide an OAuth 2.0 token.
* prettyPrint: Returns the response with indentations and line breaks. If the property is **true**, the response is returned in a human-readable format.
* quotaUser: Alternative to userIp. Lets you enforce per-user quotas from a server-side application even in cases where the user's IP address is unknown.
* userIp: IP address of the end user for whom the API call is being made. Lets you enforce per-user quotas when calling the API from a server-side application.
* ifMatch: Etag value to use for returning a page of list values if the values have not changed.
* ifNoneMatch: Etag value to use for returning a page of list values if the values have changed.

or 

You can use only the below operation (getAccessTokenFromServiceAccount) to get the access token and to do all the other operations.

**getAccessTokenFromServiceAccount**
```xml
<bigquery.getAccessTokenFromServiceAccount>
    <apiUrl>{$ctx:apiUrl}</apiUrl>
    <keyStoreLocation>{$ctx:keyStoreLocation}</keyStoreLocation>
    <serviceAccount>{$ctx:serviceAccount}</serviceAccount>
    <scope>{$ctx:scope}</scope>
    <accessTokenRegistryPath>{$ctx:accessTokenRegistryPath}</accessTokenRegistryPath>
</bigquery.getAccessTokenFromServiceAccount>
```

**Properties** 
* apiUrl: The base endpoint URL of the BigQuery API.
* keyStoreLocation: The location where the p12 key file is located.
* serviceAccount: The value of the service account.
* scope: The space delimited scope to access the API.
* accessTokenRegistryPath: The registry path to store the access token (This is an optional parameter).

**Sample request**

Following is a sample request that can be handled by the getAccessTokenFromServiceAccount operation.

```json
{
    "apiUrl":"https://www.googleapis.com",
    "keyStoreLocation":"/home/hariprasath/Desktop/bigQuery/p12/Data Services - Non Production.p12",
    "serviceAccount":"service-account-bq.com",
    "scope":"https://www.googleapis.com/auth/bigquery",
    "accessTokenRegistryPath":"connectors/BigQuery/accessToken"
}
```
**Note**

**getAccessTokenFromAuthorizationCode**
```xml
<bigquery.getAccessTokenFromAuthorizationCode>
    <apiUrl>{$ctx:apiUrl}</apiUrl>
    <authorizationCode>{$ctx:authorizationCode}</authorizationCode>           
    <redirectUrl>{$ctx:redirectUrl}</redirectUrl>
    <clientSecret>{$ctx:clientSecret}</clientSecret>
    <clientId>{$ctx:clientId}</clientId>
    <registryPath>{$ctx:registryPath}</registryPath>
</bigquery.getAccessTokenFromAuthorizationCode>
```
**Properties** 
* apiUrl: The base endpoint URL of the BigQuery API.
* authorizationCode: The authorization code to be used for obtaining the access token.
* redirectUrl: The redirect URL to be used in the OAuth 2.0 authorization flow.
* clientId:  The client ID for the BigQuery API.
* clientSecret: The client secret for the BigQuery API.
* registryPath: The registry path to save the access token.

When the getAccessTokenFromAuthorizationCode operation is executed, the new values will be updated to the accessToken and refreshToken registry entries.

**Sample request**

Following is a sample REST/JSON request that can be handled by the getAccessTokenFromAuthorizationCode operation.

```json
{
  "authorizationCode": "4/crg1KE2zMBMQPJTrYct-wszP0j-J8Zfzd5adVz2ci7I",
  "apiUrl": "https://www.googleapis.com",
  "redirectUrl": "https://localhost",
  "clientId": "504627865627-kdni8r2s10sjddfgXzqb4bvnba.apps.googleusercontent.com",
  "clientSecret": "ChlbHI_T7zssXXTRYuqj_-TM",
  "registryPath": "connectors/bq"
}
```

**Related BigQuery documentation**

https://developers.google.com/identity/protocols/OAuth2WebServer

**getAccessTokenFromRefreshToken**
```xml
<bigquery.getAccessTokenFromRefreshToken>
    <apiUrl>{$ctx:apiUrl}</apiUrl>
    <clientSecret>{$ctx:clientSecret}</clientSecret>
    <clientId>{$ctx:clientId}</clientId>
    <refreshToken>{$ctx:refreshToken}</refreshToken>
</bigquery.getAccessTokenFromRefreshToken>
```
**Properties** 
* apiUrl: The base endpoint URL of the BigQuery API.
* clientId:  The client ID for the BigQuery API.
* clientSecret: The client secret for the BigQuery API.
* refreshToken: The refresh token for the BigQuery API.

**Sample request**

Following is a sample REST/JSON request that can be handled by the getAccessTokenFromRefreshToken operation.

```json
{
  "apiUrl": "https://www.googleapis.com",
  "clientId": "504627865627-kdni8r2s10sjcgd4v6stthdaqb4bvnba.apps.googleusercontent.com",
  "refreshToken": "1/uWful-diQNAdk-alDUa6ixxxxxxxx-LpJIikEQ2sqA",
  "clientSecret": "ChlbHI_T7zssXXTRYuqj_-TM"
}
```

**Related BigQuery documentation**

https://developers.google.com/identity/protocols/OAuth2WebServer

Now that you have connected to BigQuery, use the information in the following topics to perform various operations with the connector:

[Working with Datasets in BigQuery](datasets.md)

[Working with Jobs in BigQuery](jobs.md)

[Working with Projects in BigQuery](projects.md)

[Working with Table Data in BigQuery](table_data.md)

[Working with Tables in BigQuery](tables.md)
