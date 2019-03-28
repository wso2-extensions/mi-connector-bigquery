# Working with Tables in BigQuery

[[Overview]](#overview)  [[Operation details]](#operation-details)  [[Sample configuration]](#sample-configuration)

### Overview 

The following operations allow you to work with tables. Click an operation name to see details on how to use it.
For a sample proxy service that illustrates how to work with tables, see [Sample configuration](#sample-configuration).

| Operation        | Description |
| ------------- |-------------|
| [getTable](#retrieving-a-table-by-id)    | Retrieves  a table by ID. |
| [listTables](#retrieving-all-available-tables)      | Retrieves all available tables. |

### Operation details

This section provides further details on the operations related to tables.

#### Retrieving a table by ID

The getTable operation retrieves a table by ID.

**getTable**
```xml
<bigquery.getTable>
    <tableId>{$ctx:tableId}</tableId>
    <datasetId>{$ctx:datasetId}</datasetId>
    <projectId>{$ctx:projectId}</projectId>
</bigquery.getTable>
```

**Properties**
* tableId: Table ID of the requested table.
* datasetId: Dataset ID of the requested table.
* projectId: Project ID of the requested table.

**Sample request**

Following is a sample request that can be handled by the getTable operation.

```json
{
    "quotaUser":"1hx46f5g4h5ghx6h41x54gh6f4hx",
    "userIp":"192.77.88.12",
    "accessToken":"ya29.6QFjdRjTZyXmIjxkO6G6dJoLrch1Ktt1IzFm",
    "clientId": "504627865627-kdni8r2s10sjddfgXzqb4bvnba.apps.googleusercontent.com",
    "clientSecret": "ChlbHI_T7zssXXTRYuqj_-TM",
    "refreshToken": "1/uWful-diQNAdk-alDUa6ixxxxxxxx-LpJIikEQ2sqA",
    "registryPath": "connectors/bq",
    "prettyPrint":"true",
    "callback":"callBackFunction",
    "apiUrl":"https://www.googleapis.com",
    "apiKey":"154987fd5h4x6gh4",
    "fields":"id,etag",
    "tableId":"github_nested",
    "datasetId":"samples",
    "projectId":"publicdata",
    "ifNoneMatch":"hnk59tKBkX8cdlePZ8VtzgVzuO4/tS1oqpXxnkU21hZeK5k4lqRrRr8",
    "ifMatch":"hnk59tKBkX8cdlePZ8VtzgVzuO4/tS1oqpXxnkU21hZeK5k4lqRrRr8"
}
```

**Sample response**

Given below is a sample response for the getTable operation.

```json
{
  "kind": "bigquery#tableList",
  "etag": "ASMRI9cY0t0ilhpaFI4OMA==",
  "tables": [
    {
      "kind": "bigquery#table",
      "id": "testbig-235116:testData.github_nested_copy",
      "tableReference": {
        "projectId": "testbig-235116",
        "datasetId": "testData",
        "tableId": "github_nested_copy"
      },
      "type": "TABLE",
      "creationTime": "1553104818977",
      "expirationTime": "1558288818977"
    },
    {
      "kind": "bigquery#table",
      "id": "testbig-235116:testData.sample_20190322",
      "tableReference": {
        "projectId": "testbig-235116",
        "datasetId": "testData",
        "tableId": "sample_20190322"
      },
      "type": "TABLE",
      "creationTime": "1553239767833",
      "expirationTime": "1558423767833"
    }
  ],
  "totalItems": 2
}
```
**Related BigQuery documentation**
https://cloud.google.com/bigquery/docs/reference/v2/tables/get

#### Retrieving all available tables

The listTables operation retrieves all available tables in the specified dataset.

**listTables**
```xml
<bigquery.listTables>
    <datasetId>{$ctx:datasetId}</datasetId>
    <pageToken>{$ctx:pageToken}</pageToken>
    <projectId>{$ctx:projectId}</projectId>
    <maxResults>{$ctx:maxResults}</maxResults>
</bigquery.listTables>
```

**Properties**
* datasetId: Dataset ID of the tables to list.
* pageToken: Page token, returned by a previous call, to request the next page of results.
* projectId: Project ID of the tables to list.
* maxResults: Maximum number of results to return.

**Sample request**

Following is a sample request that can be handled by the listTables operation.

```json
{
    "quotaUser":"1hx46f5g4h5ghx6h41x54gh6f4hx",
    "userIp":"192.77.88.12",
    "accessToken":"ya29.6QFjdRjTZyXmIjxkO6G6dJoLrch1Ktt1IzFm",
    "clientId": "504627865627-kdni8r2s10sjddfgXzqb4bvnba.apps.googleusercontent.com",
    "clientSecret": "ChlbHI_T7zssXXTRYuqj_-TM",
    "refreshToken": "1/uWful-diQNAdk-alDUa6ixxxxxxxx-LpJIikEQ2sqA",
    "registryPath": "connectors/bq",
    "prettyPrint":"true",
    "callback":"callBackFunction",
    "apiUrl":"https://www.googleapis.com",
    "apiKey":"154987fd5h4x6gh4",
    "fields":"id,etag",
    "datasetId":"samples",
    "pageToken":"trigrams",
    "projectId":"publicdata",
    "maxResults":"5",
    "ifNoneMatch":"hnk59tKBkX8cdlePZ8VtzgVzuO4/tS1oqpXxnkU21hZeK5k4lqRrRr8",
    "ifMatch":"hnk59tKBkX8cdlePZ8VtzgVzuO4/tS1oqpXxnkU21hZeK5k4lqRrRr8"
}
```

**Sample response**

Given below is a sample response for the listTables operation.

```json
{
  "kind": "bigquery#tableList",
  "etag": "ASMRI9cY0t0ilhpaFI4OMA==",
  "tables": [
    {
      "kind": "bigquery#table",
      "id": "testbig-235116:testData.github_nested_copy",
      "tableReference": {
        "projectId": "testbig-235116",
        "datasetId": "testData",
        "tableId": "github_nested_copy"
      },
      "type": "TABLE",
      "creationTime": "1553104818977",
      "expirationTime": "1558288818977"
    },
    {
      "kind": "bigquery#table",
      "id": "testbig-235116:testData.sample_20190322",
      "tableReference": {
        "projectId": "testbig-235116",
        "datasetId": "testData",
        "tableId": "sample_20190322"
      },
      "type": "TABLE",
      "creationTime": "1553239767833",
      "expirationTime": "1558423767833"
    }
  ],
  "totalItems": 2
}
```
**Related BigQuery documentation**
https://cloud.google.com/bigquery/docs/reference/v2/tables/list

### Sample configuration

Following example illustrates how to connect to BigQuery with the init operation and getTable operation.

1. Create a sample proxy as below :

```xml
<?xml version="1.0" encoding="UTF-8"?>
    <proxy xmlns="http://ws.apache.org/ns/synapse" name="getTable" transports="https,http" statistics="disable" trace="disable" startOnLoad="true">
     <target>
     <inSequence>
      <property name="quotaUser" expression="json-eval($.quotaUser)"/>
      <property name="userIp" expression="json-eval($.userIp)"/>
      <property name="accessToken" expression="json-eval($.accessToken)"/>
      <property name="clientId" expression="json-eval($.clientId)"/>
      <property name="refreshToken" expression="json-eval($.refreshToken)"/>
      <property name="clientSecret" expression="json-eval($.clientSecret)"/>
      <property name="registryPath" expression="json-eval($.registryPath)"/>
      <property name="prettyPrint" expression="json-eval($.prettyPrint)"/>
      <property name="callback" expression="json-eval($.callback)"/>
      <property name="apiUrl" expression="json-eval($.apiUrl)"/>
      <property name="apiKey" expression="json-eval($.apiKey)"/>
      <property name="fields" expression="json-eval($.fields)"/>
      <property name="tableId" expression="json-eval($.tableId)"/>
      <property name="datasetId" expression="json-eval($.datasetId)"/>
      <property name="projectId" expression="json-eval($.projectId)"/>
      <property name="ifMatch" expression="json-eval($.ifMatch)" />
      <property name="ifNoneMatch" expression="json-eval($.ifNoneMatch)" />
      <bigquery.init>
         <quotaUser>{$ctx:quotaUser}</quotaUser>
         <userIp>{$ctx:userIp}</userIp>
         <accessToken>{$ctx:accessToken}</accessToken>
         <clientSecret>{$ctx:clientSecret}</clientSecret>
         <clientId>{$ctx:clientId}</clientId>
         <refreshToken>{$ctx:refreshToken}</refreshToken>
         <registryPath>{$ctx:registryPath}</registryPath>
         <prettyPrint>{$ctx:prettyPrint}</prettyPrint>
         <callback>{$ctx:callback}</callback>
         <apiUrl>{$ctx:apiUrl}</apiUrl>
         <apiKey>{$ctx:apiKey}</apiKey>
         <fields>{$ctx:fields}</fields>
         <ifMatch>{$ctx:ifMatch}</ifMatch>
         <ifNoneMatch>{$ctx:ifNoneMatch}</ifNoneMatch>
      </bigquery.init>
      <bigquery.getTable>
         <tableId>{$ctx:tableId}</tableId>
         <datasetId>{$ctx:datasetId}</datasetId>
         <projectId>{$ctx:projectId}</projectId>
      </bigquery.getTable>
       <respond/>
     </inSequence>
      <outSequence>
       <send/>
      </outSequence>
     </target>
   <description/>
  </proxy>
```
2. Create an json file named getTable.json and copy the configurations given below to it:

```json
{
    "quotaUser":"1hx46f5g4h5ghx6h41x54gh6f4hx",
    "userIp":"192.77.88.12",
    "accessToken":"ya29.6QFjdRjTZyXmIjxkO6G6dJoLrch1Ktt1IzFm",
    "clientId": "504627865627-kdni8r2s10sjddfgXzqb4bvnba.apps.googleusercontent.com",
    "clientSecret": "ChlbHI_T7zssXXTRYuqj_-TM",
    "refreshToken": "1/uWful-diQNAdk-alDUa6ixxxxxxxx-LpJIikEQ2sqA",
    "registryPath": "connectors/bq",
    "prettyPrint":"true",
    "callback":"callBackFunction",
    "apiUrl":"https://www.googleapis.com",
    "apiKey":"154987fd5h4x6gh4",
    "fields":"id,etag",
    "tableId":"github_nested",
    "datasetId":"samples",
    "projectId":"publicdata",
    "ifNoneMatch":"hnk59tKBkX8cdlePZ8VtzgVzuO4/tS1oqpXxnkU21hZeK5k4lqRrRr8",
    "ifMatch":"hnk59tKBkX8cdlePZ8VtzgVzuO4/tS1oqpXxnkU21hZeK5k4lqRrRr8"
}
```

3. Replace the credentials with your values.

4. Execute the following curl command:

```bash
curl http://localhost:8280/services/getTable -H "Content-Type: application/json" -d @getTable.json
```
5. BigQuery returns an json response similar to the one shown below:
 
```json
{
  "kind": "bigquery#tableList",
  "etag": "ASMRI9cY0t0ilhpaFI4OMA==",
  "tables": [
    {
      "kind": "bigquery#table",
      "id": "testbig-235116:testData.github_nested_copy",
      "tableReference": {
        "projectId": "testbig-235116",
        "datasetId": "testData",
        "tableId": "github_nested_copy"
      },
      "type": "TABLE",
      "creationTime": "1553104818977",
      "expirationTime": "1558288818977"
    },
    {
      "kind": "bigquery#table",
      "id": "testbig-235116:testData.sample_20190322",
      "tableReference": {
        "projectId": "testbig-235116",
        "datasetId": "testData",
        "tableId": "sample_20190322"
      },
      "type": "TABLE",
      "creationTime": "1553239767833",
      "expirationTime": "1558423767833"
    }
  ],
  "totalItems": 2
}
```