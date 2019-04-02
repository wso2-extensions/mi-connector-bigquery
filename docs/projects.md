# Working with Projects in BigQuery

The listProjects operation retrieves all projects.

**listProjects**
```xml
<bigquery.listProjects>
    <maxResults>{$ctx:maxResults}</maxResults>
    <pageToken>{$ctx:pageToken}</pageToken>
</bigquery.listProjects>
```

**Properties**
* maxResults: The maximum number of results per page.
* pageToken: The page token value.

**Sample request**

Following is a sample request that can be handled by the listProjects operation.

```json
{
    "accessToken" : "ya29.BwKYx40Dith1DFQBDjZOHNqhcxmKs9zbkjAWQa1q8mdMFndp2-q8ifG66fwprOigRwKSNw",
    "apiUrl" : "https://www.googleapis.com",
    "clientId": "504627865627-kdni8r2s10sjddfgXzqb4bvnba.apps.googleusercontent.com",
    "clientSecret": "ChlbHI_T7zssXXTRYuqj_-TM",
    "refreshToken": "1/uWful-diQNAdk-alDUa6ixxxxxxxx-LpJIikEQ2sqA",
    "registryPath": "connectors/bq",
    "maxResults" : "1",
    "pageToken" : "1",
    "fields": "id",
    "callback": "callBackFunction",
    "apiKey": "154987fd5h4x6gh4",
    "prettyPrint": "true",
    "quotaUser": "1hx46f5g4h5ghx6h41x54gh6f4hx",
    "userIp": "192.77.88.12",
    "ifNoneMatch":"hnk59tKBkX8cdlePZ8VtzgVzuO4/tS1oqpXxnkU21hZeK5k4lqRrRr8",
    "ifMatch":"hnk59tKBkX8cdlePZ8VtzgVzuO4/tS1oqpXxnkU21hZeK5k4lqRrRr8"
}
```

**Sample response**

Given below is a sample response for the listProjects operation.

```json
{
  "kind": "bigquery#projectList",
  "etag": "jdhx8JpxmSC6iJhWFNchpw==",
  "projects": [
    {
      "kind": "bigquery#project",
      "id": "ascendant-lore-235117",
      "numericId": "719690246975",
      "projectReference": {
        "projectId": "ascendant-lore-235117"
      },
      "friendlyName": "My First Project"
    },
    {
      "kind": "bigquery#project",
      "id": "true-kite-235118",
      "numericId": "911077124704",
      "projectReference": {
        "projectId": "true-kite-235118"
      },
      "friendlyName": "My First Project"
    }
  ],
  "totalItems": 2
}
```
**Related BigQuery documentation**
https://cloud.google.com/bigquery/docs/reference/v2/projects/list

### Sample configuration

Follow the steps given below to connect to BigQuery with the init and listProjects operations.

1. Create a sample proxy service as follows:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<proxy xmlns="http://ws.apache.org/ns/synapse" name="listProjects" transports="https,http" statistics="disable" trace="disable" startOnLoad="true">
    <target>
        <inSequence>
            <property name="accessToken" expression="json-eval($.accessToken)" />
            <property name="apiUrl" expression="json-eval($.apiUrl)" />
            <property name="clientId" expression="json-eval($.clientId)"/>
            <property name="refreshToken" expression="json-eval($.refreshToken)"/>
            <property name="clientSecret" expression="json-eval($.clientSecret)"/>
            <property name="registryPath" expression="json-eval($.registryPath)"/>
            <property name="maxResults" expression="json-eval($.maxResults)" />
            <property name="pageToken" expression="json-eval($.pageToken)" />
            <property name="fields" expression="json-eval($.fields)"/>
            <property name="callback" expression="json-eval($.callback)"/>
            <property name="apiKey" expression="json-eval($.apiKey)"/>
            <property name="prettyPrint" expression="json-eval($.prettyPrint)"/>
            <property name="quotaUser" expression="json-eval($.quotaUser)"/>
            <property name="userIp" expression="json-eval($.userIp)"/>
            <property name="ifMatch" expression="json-eval($.ifMatch)" />
            <property name="ifNoneMatch" expression="json-eval($.ifNoneMatch)" />
            <bigquery.init>
                <apiUrl>{$ctx:apiUrl}</apiUrl>
                <accessToken>{$ctx:accessToken}</accessToken>
                <clientSecret>{$ctx:clientSecret}</clientSecret>
                <clientId>{$ctx:clientId}</clientId>
                <refreshToken>{$ctx:refreshToken}</refreshToken>
                <registryPath>{$ctx:registryPath}</registryPath>
                <fields>{$ctx:fields}</fields>
                <callback>{$ctx:callback}</callback>
                <apiKey>{$ctx:apiKey}</apiKey>
                <prettyPrint>{$ctx:prettyPrint}</prettyPrint>
                <quotaUser>{$ctx:quotaUser}</quotaUser>
                <userIp>{$ctx:userIp}</userIp>
                <ifMatch>{$ctx:ifMatch}</ifMatch>
                <ifNoneMatch>{$ctx:ifNoneMatch}</ifNoneMatch>
            </bigquery.init>
            <bigquery.listProjects>
                <maxResults>{$ctx:maxResults}</maxResults>
                <pageToken>{$ctx:pageToken}</pageToken>
            </bigquery.listProjects>
            <respond />
        </inSequence>
        <outSequence>
            <send />
        </outSequence>
    </target>
    <description />
</proxy>
```
2. Create a JSON file named listProjects.json and add the configurations given below:

```json
{
    "accessToken" : "ya29.BwKYx40Dith1DFQBDjZOHNqhcxmKs9zbkjAWQa1q8mdMFndp2-q8ifG66fwprOigRwKSNw",
    "apiUrl" : "https://www.googleapis.com",
    "clientId": "504627865627-kdni8r2s10sjddfgXzqb4bvnba.apps.googleusercontent.com",
    "clientSecret": "ChlbHI_T7zssXXTRYuqj_-TM",
    "refreshToken": "1/uWful-diQNAdk-alDUa6ixxxxxxxx-LpJIikEQ2sqA",
    "registryPath": "connectors/bq",
    "maxResults" : "1",
    "pageToken" : "1",
    "fields": "id",
    "callback": "callBackFunction",
    "apiKey": "154987fd5h4x6gh4",
    "prettyPrint": "true",
    "quotaUser": "1hx46f5g4h5ghx6h41x54gh6f4hx",
    "userIp": "192.77.88.12",
    "ifNoneMatch":"hnk59tKBkX8cdlePZ8VtzgVzuO4/tS1oqpXxnkU21hZeK5k4lqRrRr8",
    "ifMatch":"hnk59tKBkX8cdlePZ8VtzgVzuO4/tS1oqpXxnkU21hZeK5k4lqRrRr8"
}
```

3. Replace the credentials with your values.

4. Execute the following curl command:

```bash
curl http://localhost:8280/services/listProjects -H "Content-Type: application/json" -d @listProjects.json
```
5. BigQuery returns a JSON response similar to the one shown below:
 
```json
{
  "kind": "bigquery#projectList",
  "etag": "jdhx8JpxmSC6iJhWFNchpw==",
  "projects": [
    {
      "kind": "bigquery#project",
      "id": "ascendant-lore-235117",
      "numericId": "719690246975",
      "projectReference": {
        "projectId": "ascendant-lore-235117"
      },
      "friendlyName": "My First Project"
    },
    {
      "kind": "bigquery#project",
      "id": "true-kite-235118",
      "numericId": "911077124704",
      "projectReference": {
        "projectId": "true-kite-235118"
      },
      "friendlyName": "My First Project"
    }
  ],
  "totalItems": 2
}
```
