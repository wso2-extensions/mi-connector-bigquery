# Working with datasets in BigQuery

[[Overview]](#overview)  [[Operation details]](#operation-details)  [[Sample configuration]](#sample-configuration)

### Overview 

The following operations allow you to work with datasets. Click the operation name to see details on how to use it.
For a sample proxy service that illustrates how to work with datasets, see [Sample configuration](#sample-configuration).

| Operation        | Description |
| ------------- |-------------|
| [getDataset](#retrieving-a-dataset)    | Retrieves a dataset. |
| [listDatasets](#retrieving-a-dataset-list)      | Retrieves a dataset list. |

### Operation details

This section provides further details on the operations related to datasets.

#### Retrieving a dataset

The getDataset operation retrieves a dataset.

**getDataset**
```xml
<bigquery.getDataset>
    <projectId>{$ctx:projectId}</projectId>
    <datasetId>{$ctx:datasetId}</datasetId>
</bigquery.getDataset>
```

**Properties**
* projectId: The ID of the project to which the dataset belongs.
* datasetId: The ID of the dataset.

**Sample request**

Following is a sample request that can be handled by the getDataset operation.

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

Given below is a sample response for the getDataset operation.

```json
{
  "kind": "bigquery#dataset",
  "etag": "1xuEK5ngZZ+fj0iioOa6Og==",
  "id": "testbig-235116:testData",
  "selfLink": "https://content.googleapis.com/bigquery/v2/projects/testbig-235116/datasets/testData",
  "datasetReference": {
    "datasetId": "testData",
    "projectId": "testbig-235116"
  },
  "defaultTableExpirationMs": "5184000000",
  "access": [
    {
      "role": "WRITER",
      "specialGroup": "projectWriters"
    },
    {
      "role": "OWNER",
      "specialGroup": "projectOwners"
    },
    {
      "role": "OWNER",
      "userByEmail": "iamkesan@gmail.com"
    },
    {
      "role": "READER",
      "specialGroup": "projectReaders"
    }
  ],
  "creationTime": "1553104741840",
  "lastModifiedTime": "1553104741840",
  "location": "US",
  "defaultPartitionExpirationMs": "5184000000"
}
```
**Related BigQuery documentation**
https://cloud.google.com/bigquery/docs/reference/v2/datasets/get

#### Retrieving a dataset list

The listDatasets operation lists a set of data.

**listDatasets**
```xml
<bigquery.listDatasets>
    <projectId>{$ctx:projectId}</projectId>
    <maxResults>{$ctx:maxResults}</maxResults>
    <pageToken>{$ctx:pageToken}</pageToken>
    <isAll>{$ctx:isAll}</isAll>
</bigquery.listDatasets>
```

**Properties**
* projectId: The ID of the project to which the datasets belong.
* maxResults: The maximum number of results per page.
* pageToken: The page token value.
* isAll: A boolean value that determines whether to list all datasets, including hidden ones.

**Sample request**

Following is a sample request that can be handled by the listDatasets operation.

```json

{
  "accessToken": "ya29.BwKYx40Dith1DFQBDjZOHNqhcxmKs9zbkjAWQa1q8mdMFndp2-q8ifG66fwprOigRwKSNw",
  "apiUrl": "https://www.googleapis.com",
  "clientId": "504627865627-kdni8r2s10sjddfgXzqb4bvnba.apps.googleusercontent.com",
  "clientSecret": "ChlbHI_T7zssXXTRYuqj_-TM",
  "refreshToken": "1/uWful-diQNAdk-alDUa6ixxxxxxxx-LpJIikEQ2sqA",
  "registryPath": "connectors/bq",
  "projectId": "publicdata",
  "maxResults": "1",
  "pageToken": "1",
  "isAll": "true",
  "fields": "datasets/datasetReference",
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

Given below is a sample response for the listDatasets operation.

```json
{
  "kind": "bigquery#datasetList",
  "etag": "5xsXo/uZ5RUfG49EzOV9Gg==",
  "datasets": [
    {
      "kind": "bigquery#dataset",
      "id": "testbig-235116:testData",
      "datasetReference": {
        "datasetId": "testData",
        "projectId": "testbig-235116"
      },
      "location": "US"
    }
  ]
}

```
**Related BigQuery documentation**
https://cloud.google.com/bigquery/docs/reference/v2/datasets/list

### Sample configuration

Follow the steps given belwo to connect to BigQuery using the init operation and the getDataset operation.

1. Create a sample proxy as follows:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<proxy xmlns="http://ws.apache.org/ns/synapse" name="getDataset" transports="https,http" statistics="disable"
   trace="disable" startOnLoad="true">
   <target>
      <inSequence>
         <property name="accessToken" expression="json-eval($.accessToken)" />
         <property name="apiUrl" expression="json-eval($.apiUrl)" />
         <property name="clientId" expression="json-eval($.clientId)"/>
         <property name="refreshToken" expression="json-eval($.refreshToken)"/>
         <property name="clientSecret" expression="json-eval($.clientSecret)"/>
         <property name="registryPath" expression="json-eval($.registryPath)"/>
         <property name="projectId" expression="json-eval($.projectId)" />
         <property name="datasetId" expression="json-eval($.datasetId)" />
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
         <bigquery.getDataset>
            <projectId>{$ctx:projectId}</projectId>
            <datasetId>{$ctx:datasetId}</datasetId>
         </bigquery.getDataset>
         <respond />
      </inSequence>
      <outSequence>
         <send />
      </outSequence>
   </target>
   <description />
</proxy>
```
2. Create a json file named getDataset.json and add the configurations given below:

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
curl http://localhost:8280/services/getDataset -H "Content-Type: application/json" -d @getDataset.json
```
5. BigQuery returns a json response similar to the one shown below:
 
```json
{
  "kind": "bigquery#dataset",
  "etag": "1xuEK5ngZZ+fj0iioOa6Og==",
  "id": "testbig-235116:testData",
  "selfLink": "https://content.googleapis.com/bigquery/v2/projects/testbig-235116/datasets/testData",
  "datasetReference": {
    "datasetId": "testData",
    "projectId": "testbig-235116"
  },
  "defaultTableExpirationMs": "5184000000",
  "access": [
    {
      "role": "WRITER",
      "specialGroup": "projectWriters"
    },
    {
      "role": "OWNER",
      "specialGroup": "projectOwners"
    },
    {
      "role": "OWNER",
      "userByEmail": "iamkesan@gmail.com"
    },
    {
      "role": "READER",
      "specialGroup": "projectReaders"
    }
  ],
  "creationTime": "1553104741840",
  "lastModifiedTime": "1553104741840",
  "location": "US",
  "defaultPartitionExpirationMs": "5184000000"
}
```
