# Working with Table Data in BigQuery

[[Overview]](#overview)  [[Operation details]](#operation-details)  [[Sample configuration]](#sample-configuration)

### Overview 

The following operations allow you to work with table data. Click an operation name to see details on how to use it.
For a sample proxy service that illustrates how to work with table data, see [Sample configuration](#sample-configuration).

| Operation        | Description |
| ------------- |-------------|
| [listTableData](#list-the-table-data)    | Retrieves table data from a specified set of rows |
| [insertAllTableData](#insert-all-data-to-a-table)      | Inserts the data into the table. |

### Operation details

This section provides further details on the operations related to table data.

#### List the table data

The listTabledata operation retrieves table data from a specified set of rows.

**listTableData**
```xml
<bigquery.listTabledata>
    <datasetId>{$ctx:datasetId}</datasetId>
    <projectId>{$ctx:projectId}</projectId>
    <tableId>{$ctx:tableId}</tableId>
    <maxResults>{$ctx:maxResults}</maxResults>
    <pageToken>{$ctx:pageToken}</pageToken>
    <startIndex>{$ctx:startIndex}</startIndex>
</bigquery.listTabledata>
```

**Properties**
* projectId: The ID of the project to which the dataset belongs.
* datasetId: The ID of the dataset to which the table belongs.
* tableId: The ID of the table.
* maxResults: The maximum results per page.
* pageToken: The page token value.
* startIndex: Zero-based index of the starting row.

**Sample request**

Following is a sample request that can be handled by the listTableData operation:

```json
{
  "accessToken": "ya29.BwKYx40Dith1DFQBDjZOHNqhcxmKs9zbkjAWQa1q8mdMFndp2-q8ifG66fwprOigRwKSNw",
  "apiUrl": "https://www.googleapis.com",
  "clientId": "504627865627-kdni8r2s10sjddfgXzqb4bvnba.apps.googleusercontent.com",
  "clientSecret": "ChlbHI_T7zssXXTRYuqj_-TM",
  "refreshToken": "1/uWful-diQNAdk-alDUa6ixxxxxxxx-LpJIikEQ2sqA",
  "registryPath": "connectors/bq",
  "projectId": "publicdata",
  "datasetId": "samples",
  "tableId": "github_nested",
  "maxResults": "1",
  "pageToken": "1",
  "startIndex": "1",
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

Given below is a sample response for the listTableData operation.

```json
{
  "kind": "bigquery#tableDataList",
  "etag": "RRRjVfSIc2CcCrEaLPH6Dg==",
  "totalRows": "2",
  "rows": [
    {
      "f": [
        {
          "v": "John"
        },
        {
          "v": null
        }
      ]
    },
    {
      "f": [
        {
          "v": "Harry"
        },
        {
          "v": "90"
        }
      ]
    }
  ]
}
```
**Related BigQuery documentation**
https://cloud.google.com/bigquery/docs/reference/v2/tabledata/list

#### Insert all data to a table

The insertAllTableData operation retrieves table data from a specified set of rows.

**insertAllTableData**
```xml
<bigquery.insertAllTableData>
    <datasetId>{$ctx:datasetId}</datasetId>
    <projectId>{$ctx:projectId}</projectId>
    <tableId>{$ctx:tableId}</tableId>
    <skipInvalidRows>{$ctx:skipInvalidRows}</skipInvalidRows>
    <ignoreUnknownValues>{$ctx:ignoreUnknownValues}</ignoreUnknownValues>
    <templateSuffix>{$ctx:templateSuffix}</templateSuffix>
    <jsonPay>{$ctx:jsonPay}</jsonPay>
</bigquery.insertAllTableData>
```

**Properties**
* datasetId: The ID of the dataset to which the table belongs.
* projectId: The ID of the project to which the datasets belongs.
* tableId: The ID of the table.
* skipInvalidRows: A boolean value to check whether the row should be validated.
* ignoreUnknownValues: A boolean value to validate whether the values match the table schema.
* templateSuffix: Instance table.
* jsonPay: A JSON object that contains a row of data.

**Sample request**

Following is a sample request that can be handled by the insertAllTableData operation.

```json
{
    "apiUrl":"https://www.googleapis.com",
    "keyStoreLocation":"/home/hariprasath/Desktop/bigQuery/p12/Non Production-232c0d8ac8f2.p12",
    "serviceAccount":"service-account.gserviceaccount.com",
    "scope":"https://www.googleapis.com/auth/bigquery",
    "datasetId": "zSta",
    "tableId": "ECOMM",
    "projectId": "dataservices",
    "kind": "bigquery#tableDataInsertAllRequest",
    "skipInvalidRows": true,
    "ignoreUnknownValues": true,
    "templateSuffix":"_20160315",
    "jsonPay":
         {
        "insertId": "xxxxx",
    "json":
            {
            "SOURCE_ID":"2",
            "DESTINATION_ID":"13",
            "SIGNAL_TYPE_ID":"13",
            "DATA":"hariprasath",
            "TRANSACTION_TIMESTAMP":"2014-03-01T22:12:22.000Z",
            "BQ_INSERT_TIMESTAMP":"2016-02-26 20:12:01"      
            }
        }
}
```
Following is a sample request that inserts multiple records.

```json
{
    "apiUrl":"https://www.googleapis.com",
    "keyStoreLocation":"/home/hariprasath/Desktop/bigQuery/p12/Non Production-232c0d8ac8f2.p12",
    "serviceAccount":"service-account.gserviceaccount.com",
    "scope":"https://www.googleapis.com/auth/bigquery",
    "datasetId": "zSta",
    "tableId": "Sample",
    "projectId": "dataservices",
    "kind": "bigquery#tableDataInsertAllRequest",
    "skipInvalidRows": true,
    "ignoreUnknownValues": true,
    "templateSuffix":"_20160315",
    "jsonPay":[
       {
          "insertId":"1014",
          "json":{
             "Name":"John",
             "Age":25
          }
       },
       {
          "insertId":"1015",
          "json":{
             "Name":"Vasan",
             "Age":45
          }
       }
    ]
}
```

**Sample response**

Given below is a sample response for the insertAllTableData operation.

```json
{
  "kind": "bigquery#tableDataInsertAllResponse"
}
```
**Related BigQuery documentation**
https://cloud.google.com/bigquery/docs/reference/v2/tabledata/insertAll

### Sample configuration

Follow the steps given below to connect to BigQuery with the init and listTabledata operations.

1. Create a sample proxy service as follows:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<proxy xmlns="http://ws.apache.org/ns/synapse" name="listTabledata" transports="https,http" statistics="disable"
   trace="disable" startOnLoad="true">
   <target>
      <inSequence>
         <property name="accessToken" expression="json-eval($.accessToken)" />
         <property name="apiUrl" expression="json-eval($.apiUrl)" />
         <property name="clientId" expression="json-eval($.clientId)"/>
         <property name="refreshToken" expression="json-eval($.refreshToken)"/>
         <property name="clientSecret" expression="json-eval($.clientSecret)"/>
         <property name="registryPath" expression="json-eval($.registryPath)"/>
         <property name="datasetId" expression="json-eval($.datasetId)" />
         <property name="projectId" expression="json-eval($.projectId)" />
         <property name="tableId" expression="json-eval($.tableId)" />
         <property name="maxResults" expression="json-eval($.maxResults)" />
         <property name="pageToken" expression="json-eval($.pageToken)" />
         <property name="startIndex" expression="json-eval($.startIndex)" />
         <property name="fields" expression="json-eval($.fields)" />
         <property name="callback" expression="json-eval($.callback)" />
         <property name="apiKey" expression="json-eval($.apiKey)" />
         <property name="prettyPrint" expression="json-eval($.prettyPrint)" />
         <property name="quotaUser" expression="json-eval($.quotaUser)" />
         <property name="userIp" expression="json-eval($.userIp)" />
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
         <bigquery.listTabledata>
            <datasetId>{$ctx:datasetId}</datasetId>
            <projectId>{$ctx:projectId}</projectId>
            <tableId>{$ctx:tableId}</tableId>
            <maxResults>{$ctx:maxResults}</maxResults>
            <pageToken>{$ctx:pageToken}</pageToken>
            <startIndex>{$ctx:startIndex}</startIndex>
         </bigquery.listTabledata>
         <respond />
      </inSequence>
      <outSequence>
         <send />
      </outSequence>
   </target>
   <description />
</proxy>
```
2. Create a JSO  file named listTabledata.json and add the configurations given below:

```json
{
  "accessToken": "ya29.BwKYx40Dith1DFQBDjZOHNqhcxmKs9zbkjAWQa1q8mdMFndp2-q8ifG66fwprOigRwKSNw",
  "apiUrl": "https://www.googleapis.com",
  "clientId": "504627865627-kdni8r2s10sjddfgXzqb4bvnba.apps.googleusercontent.com",
  "clientSecret": "ChlbHI_T7zssXXTRYuqj_-TM",
  "refreshToken": "1/uWful-diQNAdk-alDUa6ixxxxxxxx-LpJIikEQ2sqA",
  "registryPath": "connectors/bq",
  "projectId": "publicdata",
  "datasetId": "samples",
  "tableId": "github_nested",
  "maxResults": "1",
  "pageToken": "1",
  "startIndex": "1",
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
curl http://localhost:8280/services/listTabledata -H "Content-Type: application/json" -d @listTabledata.json
```
5. BigQuery returns a JSON response similar to the one shown below:
 
```json
{
  "kind": "bigquery#tableDataList",
  "etag": "RRRjVfSIc2CcCrEaLPH6Dg==",
  "totalRows": "2",
  "rows": [
    {
      "f": [
        {
          "v": "John"
        },
        {
          "v": null
        }
      ]
    },
    {
      "f": [
        {
          "v": "Harry"
        },
        {
          "v": "90"
        }
      ]
    }
  ]
}
```
