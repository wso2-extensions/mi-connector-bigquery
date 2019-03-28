# Working with Jobs in BigQuery

The runQuery operation runs a BigQuery SQL query and returns results if the query completes within a specified timeout.

**runQuery**
```xml
<bigquery.runQuery>
    <useQueryCache>{$ctx:useQueryCache}</useQueryCache>
    <timeoutMs>{$ctx:timeoutMs}</timeoutMs>
    <query>{$ctx:query}</query>
    <dryRun>{$ctx:dryRun}</dryRun>
    <defaultProjectId>{$ctx:defaultProjectId}</defaultProjectId>
    <defaultDatasetId>{$ctx:defaultDatasetId}</defaultDatasetId>
    <projectId>{$ctx:projectId}</projectId>
    <maxResults>{$ctx:maxResults}</maxResults>
    <kind>{$ctx:kind}</kind>
    <useLegacySql>{$ctx:useLegacySql}</useLegacySql>
</bigquery.runQuery>
```

**Properties**
* useQueryCache: Whether to look for the result in the query cache. The default value is true.
* timeoutMs: How long to wait for the query to complete, in milliseconds, before the request times out and returns. 
* query: Required - A query string, following the BigQuery query syntax of the query to execute.
* dryRun: If set to true, BigQuery does not run the job. Instead, if the query is valid, BigQuery returns statistics about the job. If the query is invalid, an error returns. The default value is false.
* defaultProjectId: The ID of the project containing this dataset.
* defaultDatasetId: Required - A unique ID for this dataset, without the project name. The ID must contain only letters (a-z, A-Z), numbers (0-9), or underscores (_). The maximum length is 1,024 characters. 
* projectId: The project ID of the project billed for the query.
* maxResults: The maximum number of rows of data to return per page of results. Responses are also limited to 10 MB. By default, there is no maximum row count, and only the byte limit applies.
* kind: The resource type of the request.
* useLegacySql: Specifies whether to use BigQuery's legacy SQL dialect for this query. The default value is true. If set to false, the query will use BigQuery's standard SQL. For information on use BigQuery's standard SQL, see https://cloud.google.com/bigquery/docs/reference/standard-sql/migrating-from-legacy-sql.

**Sample request**

Following is a sample request that can be handled by the runQuery operation.

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
    "fields":"id,etag",
    "useQueryCache":"true",
    "timeoutMs":"10000",
    "query":"SELECT count(*) FROM [publicdata:samples.github_nested]",
    "dryRun":"false",
    "defaultProjectId":"bigqueryproject-1092",
    "defaultDatasetId":"test_100",
    "projectId":"bigqueryproject-1092",
    "maxResults":"10000",
    "kind":"bigquery#queryRequest",
    "ifNoneMatch":"hnk59tKBkX8cdlePZ8VtzgVzuO4/tS1oqpXxnkU21hZeK5k4lqRrRr8",
    "ifMatch":"hnk59tKBkX8cdlePZ8VtzgVzuO4/tS1oqpXxnkU21hZeK5k4lqRrRr8"
}
```

**Sample response**

Given below is a sample response for the runQuery operation.

```json
{
 "kind": "bigquery#queryResponse",
 "schema": {
  "fields": [
   {
    "name": "Name",
    "type": "STRING",
    "mode": "NULLABLE"
   },
   {
    "name": "Age",
    "type": "INTEGER",
    "mode": "NULLABLE"
   }
  ]
 },
 "jobReference": {
  "projectId": "testbig-235116",
  "jobId": "job_GECobzPaLdbBW-SqIG-WrfOzaqtQ",
  "location": "US"
 },
 "totalRows": "2",
 "rows": [
  {
   "f": [
    {
     "v": "John"
    },
    {
     "v": "45"
    }
   ]
  },
  {
   "f": [
    {
     "v": "Harry"
    },
    {
     "v": "25"
    }
   ]
  }
 ],
 "totalBytesProcessed": "670",
 "jobComplete": true,
 "cacheHit": false
}
```
**Related BigQuery documentation**
https://cloud.google.com/bigquery/docs/reference/v2/jobs/query

### Sample configuration

Following example illustrates how to connect to BigQuery with the init operation and runQuery operation.

1. Create a sample proxy as below :

```xml
<?xml version="1.0" encoding="UTF-8"?>
    <proxy xmlns="http://ws.apache.org/ns/synapse" name="runQuery" transports="https,http" statistics="disable" trace="disable" startOnLoad="true">
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
      <property name="useQueryCache" expression="json-eval($.useQueryCache)"/>
      <property name="timeoutMs" expression="json-eval($.timeoutMs)"/>
      <property name="query" expression="json-eval($.query)"/>
      <property name="dryRun" expression="json-eval($.dryRun)"/>
      <property name="defaultProjectId" expression="json-eval($.defaultProjectId)"/>
      <property name="defaultDatasetId" expression="json-eval($.defaultDatasetId)"/>
      <property name="projectId" expression="json-eval($.projectId)"/>
      <property name="maxResults" expression="json-eval($.maxResults)"/>
      <property name="kind" expression="json-eval($.kind)"/>
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
      <bigquery.runQuery>
         <useQueryCache>{$ctx:useQueryCache}</useQueryCache>
         <timeoutMs>{$ctx:timeoutMs}</timeoutMs>
         <query>{$ctx:query}</query>
         <dryRun>{$ctx:dryRun}</dryRun>
         <defaultProjectId>{$ctx:defaultProjectId}</defaultProjectId>
         <defaultDatasetId>{$ctx:defaultDatasetId}</defaultDatasetId>
         <projectId>{$ctx:projectId}</projectId>
         <maxResults>{$ctx:maxResults}</maxResults>
         <kind>{$ctx:kind}</kind>
      </bigquery.runQuery>
       <respond/>
     </inSequence>
      <outSequence>
       <send/>
      </outSequence>
     </target>
   <description/>
  </proxy>
```
2. Create an json file named runQuery.json and copy the configurations given below to it:

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
    "fields":"id,etag",
    "useQueryCache":"true",
    "timeoutMs":"10000",
    "query":"SELECT count(*) FROM [publicdata:samples.github_nested]",
    "dryRun":"false",
    "defaultProjectId":"bigqueryproject-1092",
    "defaultDatasetId":"test_100",
    "projectId":"bigqueryproject-1092",
    "maxResults":"10000",
    "kind":"bigquery#queryRequest",
    "ifNoneMatch":"hnk59tKBkX8cdlePZ8VtzgVzuO4/tS1oqpXxnkU21hZeK5k4lqRrRr8",
    "ifMatch":"hnk59tKBkX8cdlePZ8VtzgVzuO4/tS1oqpXxnkU21hZeK5k4lqRrRr8"
}
```

3. Replace the credentials with your values.

4. Execute the following curl command:

```bash
curl http://localhost:8280/services/runQuery -H "Content-Type: application/json" -d @runQuery.json
```
5. BigQuery returns an json response similar to the one shown below:
 
```json
{
 "kind": "bigquery#queryResponse",
 "schema": {
  "fields": [
   {
    "name": "Name",
    "type": "STRING",
    "mode": "NULLABLE"
   },
   {
    "name": "Age",
    "type": "INTEGER",
    "mode": "NULLABLE"
   }
  ]
 },
 "jobReference": {
  "projectId": "testbig-235116",
  "jobId": "job_GECobzPaLdbBW-SqIG-WrfOzaqtQ",
  "location": "US"
 },
 "totalRows": "2",
 "rows": [
  {
   "f": [
    {
     "v": "John"
    },
    {
     "v": "45"
    }
   ]
  },
  {
   "f": [
    {
     "v": "Harry"
    },
    {
     "v": "25"
    }
   ]
  }
 ],
 "totalBytesProcessed": "670",
 "jobComplete": true,
 "cacheHit": false
}
```