# Configuring BigQuery Operations

[[Prerequisites]](#Prerequisites) [[Initializing the Connector]](#initializing-the-connector)

## Prerequisites

### Get User Credentials

   1.  Using the URL "https://accounts.google.com/SignUp" create a Google account.
   2.  Go to "https://developers.google.com/oauthplayground/".
   3.  Authorize BigQuery API from "Select & authorize APIs" by selecting all the scopes available.
   4.  Then go to "Exchange authorization code for tokens" and click on "Exchange authorization code for token" button and get the access token from "Access token" box (Note down the access token for future use.).
   5.  Go to "https://console.developers.google.com/" and log in with the created google account and create a new project using the dropdown in the top bar(Note down the project Id for future use.). 
   6.  Enable BigQuery API by navigating to the "APIs" tab which is under "APIs & auth" tab.
   7.  Go to "Credentials" tab which is under "APIs & auth" tab and add credentials by selecting OAuth 2.0 client ID option.( Configure consent screen and then create client ID for 'Web application' type of applications. Note down the redirect uri for future use.)
   8.  Note down the client ID and client secret for future use.
   9.  Get the authorization code by sending a GET request using url, https://accounts.google.com/o/oauth2/auth?redirect_uri=<redirect_uri>&response_type=code&client_id=<client_ID>&scope=https://www.googleapis.com/auth/bigquery&approval_prompt=force&access_type=offline (Replace <redirect_uri> and <client_ID> with the redirect uri and client ID values noted in step vii and viii. Note down the authorization code for future use.)
   10. Get the access token and refresh token by sending a POST request to the url https://www.googleapis.com/oauth2/v3/token with x-www-form-urlencoded body with code,client_id,client_secret,redirect_uri values noted before and with grant_type value "authorization_code" (Note down the access token and refresh token for future use.).


### Using Service Account
   
   1. Open the [Service Accounts](https://console.cloud.google.com/projectselector2/iam-admin/serviceaccounts?) page in the GCP Console.
   2. Click Select a project.
   3. Select your project and click Open.
   4. Click Create Service Account.
   5. Enter a service account name, select a role you wish to grant to the service account, and then click Save.
   6. Go to the service account for which you wish to create a key, click the More button in that row, and then click Create key.
   7. Select a Key type as P12 and click Create.

## Initializing the Connector

To use the BigQuery connector, add the <bigquery.init> or <bigquery.getAccessTokenFromServiceAccount> element in your configuration before carrying out any other BigQuery operations. 

The BigQuery API requires all requests to be authenticated as a user or a service account. For more information, see https://cloud.google.com/bigquery/authentication and about the service account authentication see https://developers.google.com/identity/protocols/OAuth2ServiceAccount

**init**
```xml
<bigquery.init>
    <apiUrl>{$ctx:apiUrl}</apiUrl>
    <accessToken>{$ctx:accessToken}</accessToken>
    <clientSecret>{$ctx:clientSecret}</clientSecret>
    <clientId>{$ctx:clientId}</clientId>
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
* accessToken: OAuth token for BigQuery API.
* clientId:  Client Id for BigQuery API.
* clientSecret: Client Secret for BigQuery API.
* refreshToken: RefreshToken token for BigQuery API.
* registryPath: Registry path to save the access token.
* fields: List of fields to be returned in the response.
* callback: Name of the JavaScript callback function that handles the response. Used in JavaScript JSON-P requests.
* apiKey: API key. Required, unless you provide an OAuth 2.0 token.
* prettyPrint: Returns response with indentations and line breaks. Returns the response in a human-readable format if true.
* quotaUser: Alternative to userIp. Lets you enforce per-user quotas from a server-side application even in cases when the user's IP address is unknown.
* userIp: IP address of the end user for whom the API call is being made. Lets you enforce per-user quotas when calling the API from a server-side application.
* ifMatch: Etag value to use for returning a page of list values if the values have not changed.
* ifNoneMatch: Etag value to use for returning a page of list values if the values have changed.

or 

You can using only the below getAccessTokenFromServiceAccount  to get the access token and do all the other operations.

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
* serviceAccount: The value of the service Account.
* scope: The space delimited scope to access the API.
* accessTokenRegistryPath: The registry path to store the access token(This is an optional parameter).

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
* authorizationCode: Authorization code to be used to obtain the access token.
* redirectUrl: Redirect URL to be used in the OAuth 2.0 authorization flow.
* clientId:  Client Id for BigQuery API.
* clientSecret: Client Secret for BigQuery API.
* registryPath: Registry path to save the access token.

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
* clientId:  Client Id for BigQuery API.
* clientSecret: Client Secret for BigQuery API.
* refreshToken: RefreshToken token for BigQuery API.

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
