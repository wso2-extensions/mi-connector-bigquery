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

import org.apache.oltu.oauth2.common.utils.JSONUtils;
import org.apache.synapse.MessageContext;
import org.apache.synapse.SynapseException;
import org.json.JSONObject;
import org.wso2.carbon.connector.core.AbstractConnector;

import java.util.Map;

/**
 * Format the json to support bigQuery table schema string
 */
public class BigQueryJsonFormatter extends AbstractConnector {
    public void connect(MessageContext messageContext) {
        try {
            String jsonObject = getJsonString((String) messageContext.getProperty(JWTConstant.JSON_PAYLOAD));
            messageContext.setProperty(JWTConstant.FORMATTED_JSON_PAYLOAD, jsonObject);
        } catch (Exception e) {
            throw new SynapseException("Error while formatting the json", e);
        }
    }

    /**
     * Convert the json to biqQuery supported format
     *
     * @param jsonString the json payload
     * @return the formatted json
     */
    protected String getJsonString(String jsonString) {
        Map<String, Object> jsonObject = JSONUtils.parseJSON(jsonString);
        if (jsonObject != null && !jsonObject.isEmpty()) {
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
            JSONObject json = new JSONObject(jsonObject);
            return json.toString().replace("\\\\", "");
        }
        return null;
    }
}
