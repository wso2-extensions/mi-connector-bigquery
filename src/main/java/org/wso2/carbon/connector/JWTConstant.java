package org.wso2.carbon.connector;

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

/**
 *The constants for BigQuery
 */
public class JWTConstant {

    public static final String KEYALIAS = "privatekey";
    public static final String SIGNATURE = "SHA256withRSA";
    public static final String KEY_STORE = "PKCS12";
    public static final String KEY_STORE_LOCATION = "keyStoreLocation";
    public static final String SERVICE_ACCOUNT = "serviceAccount";
    public static final String SCOPE = "scope";
    public static final String PASSWORD = "notasecret";
    public static final String JWT_HEADER_ALGO = "alg";
    public static final String JWT_HEADER_ALGO_VALUE = "RS256";
    public static final String JWT_HEADER_TYPE = "typ";
    public static final String JWT_HEADER_TYPE_VALUE = "JWT";
    public static final String JWT_CLAIMSET_ISS = "iss";
    public static final String JWT_CLAIMSET_AUD = "aud";
    public static final String JWT_CLAIMSET_EXP = "exp";
    public static final String JWT_CLAIMSET_IAT = "iat";

    public static final String UTF = "UTF-8";
    public static final String TOKEN_ENDPOINT = "https://www.googleapis.com/oauth2/v4/token";
    public static final String JWT_PROP = "uri.var.jwt";
}