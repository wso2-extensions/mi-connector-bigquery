# BigQuery EI Connector

The BigQuery [connector](https://docs.wso2.com/display/EI640/Working+with+Connectors) allows you to access the [BigQuery REST API](https://cloud.google.com/bigquery/docs/reference/rest/v2/) through WSO2 ESB. BigQuery is a tool that allows you to execute SQL-like queries on large amounts of data at an outstanding speed.


## Compatibility

| Connector version | Supported BigQuery API version | Supported WSO2 ESB/EI version |
| ------------- | ---------------|------------- |
| [1.0.8](https://github.com/wso2-extensions/esb-connector-bigquery/tree/org.wso2.carbon.connector.bigquery-1.0.8) | v2 | EI 6.4.0    |
| [1.0.7](https://github.com/wso2-extensions/esb-connector-bigquery/tree/org.wso2.carbon.connector.bigquery-1.0.7) | v2 | ESB 4.9.0, ESB 5.0.0, EI 6.1.1, EI 6.3.0, EI 6.4.0    |

## Getting started

#### Download and install the connector

1. Download the connector from the [WSO2 Store](https://store.wso2.com/store/assets/esbconnector/details/3fcaf309-1a69-4edf-870a-882bb76fdaa1) by clicking the **Download Connector** button.
2. Then you can follow this [documentation](https://docs.wso2.com/display/EI640/Working+with+Connectors+via+the+Management+Console) to add and enable the connector via the Management Console in your EI instance.
3. For more information on using connectors and their operations in your EI configurations, see [Using a Connector](https://docs.wso2.com/display/EI640/Using+a+Connector).
4. If you want to work with connectors via EI tooling, see [Working with Connectors via Tooling](https://docs.wso2.com/display/EI640/Working+with+Connectors+via+Tooling).

#### Configuring the connector operations

To get started with the **BigQuery** connector and their operations, see [Configuring BigQuery Operations](docs/config.md).

## Building from the Source

Follow the steps given below to build the BigQuery connector from the source code:

1. Get a clone or download the source from [Github](https://github.com/wso2-extensions/esb-connector-bigquery).
2. Run the following Maven command from the `esb-connector-bigquery` directory: `mvn clean install`.
3. The ZIP file with the BigQuery connector is created in the `esb-connector-bigquery/target` directory

## How you can contribute

As an open source project, WSO2 extensions welcome contributions from the community.
Check the [issue tracker](https://github.com/wso2-extensions/esb-connector-bigquery/issues) for open issues that interest you. We look forward to receiving your contributions.
