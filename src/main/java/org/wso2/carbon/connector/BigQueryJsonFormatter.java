/*
 *  Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.connector;

import org.apache.commons.lang.StringUtils;
import org.apache.oltu.oauth2.common.utils.JSONUtils;
import org.apache.synapse.MessageContext;
import org.apache.synapse.SynapseException;
import org.apache.synapse.SynapseLog;
import org.json.JSONArray;
import org.json.JSONObject;
import org.wso2.carbon.connector.core.AbstractConnector;

import java.util.Map;

/**
 * Format the json to support bigQuery table schema string
 */
public class BigQueryJsonFormatter extends AbstractConnector {

    private final String JSON_ARRAY_START_CHAR = "[";

    public void connect(MessageContext messageContext) {
        SynapseLog synapseLog = getLog(messageContext);
        String jsonObject = formatJsonString((String) messageContext.getProperty(JWTConstant.JSON_PAYLOAD), synapseLog);
        messageContext.setProperty(JWTConstant.FORMATTED_JSON_PAYLOAD, jsonObject);
    }

    /**
     * Convert the json to biqQuery supported format
     *
     * @param jsonString the json payload
     * @return the formatted json
     */
    private String formatJsonString(String jsonString, SynapseLog synapseLog) {
        if (StringUtils.isEmpty(jsonString)) {
            synapseLog.error("Invalid request. No jsonPay(row data) present in the request.");
            throw new SynapseException("Invalid request. No jsonPay(row data) present in the request.");
        } else {
            try {
                JSONArray finalDataArray = new JSONArray();
                if (jsonString.startsWith(JSON_ARRAY_START_CHAR)) {
                    JSONArray jsonArray = new JSONArray(jsonString);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        //JSONObject rowdata = constructDataRow(jsonArray.getJSONObject(i).toString());
                        finalDataArray.put(jsonArray.get(i));
                    }
                } else {
                    JSONObject rowdata = new JSONObject(jsonString);
                    finalDataArray.put(rowdata);
                }
                return finalDataArray.toString();
            } catch (Exception e) {
                throw new SynapseException("Error while formatting the json", e);
            }
        }
    }

    private JSONObject constructDataRow(String jsonString) {
        Map<String, Object> jsonObject = JSONUtils.parseJSON(jsonString);
        for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            if (entry.getKey().equals(JWTConstant.JSON)) {
                Map<String, Object> jsonTagObject = JSONUtils.parseJSON((entry.getValue().toString()));
                for (Map.Entry<String, Object> jsonEntry : jsonTagObject.entrySet()) {
                    if (jsonEntry.getValue().toString().contains(JWTConstant.JSON_START_TAG)) {
                        String escaped = jsonEntry.getValue().toString().replace("\"", "\\\"");
                        jsonTagObject.put(jsonEntry.getKey(), escaped);
                    }
                }
                jsonObject.put(entry.getKey(), jsonTagObject);
            }
        }
        return new JSONObject(jsonObject);
    }
}
