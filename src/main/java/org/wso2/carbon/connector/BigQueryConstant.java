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

public class BigQueryConstant {

    public static final String keyAlias = "privatekey";
    public static final String signature = "SHA256withRSA";
    public static final String keyStore = "PKCS12";
    public static final String keyStoreLocation = "keyStoreLocation";
    public static final String serviceAccount = "serviceAccount";
    public static final String scope = "scope";
    public static final String password = "notasecret";
    public static final String JWT_HEADER_ALGO = "alg";
    public static final String JWT_HEADER_ALGO_VALUE = "RS256";
    public static final String JWT_HEADER_TYPE = "typ";
    public static final String JWT_HEADER_TYPE_VALUE = "JWT";
    public static final String JWT_CLAIMSET_ISS = "iss";
    public static final String JWT_CLAIMSET_AUD = "aud";
    public static final String JWT_CLAIMSET_EXP = "exp";
    public static final String JWT_CLAIMSET_IAT = "iat";

    public static final String utf = "UTF-8";
    public static final String tokenEndpoint = "https://www.googleapis.com/oauth2/v4/token";
    public static final String JWT_PROP = "uri.var.jwt";
}