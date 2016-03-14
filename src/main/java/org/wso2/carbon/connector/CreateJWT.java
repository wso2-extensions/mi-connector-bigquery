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

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.synapse.MessageContext;
import org.apache.synapse.SynapseException;
import org.json.JSONException;
import org.json.JSONObject;
import org.wso2.carbon.connector.core.AbstractConnector;
import org.wso2.carbon.connector.core.util.ConnectorUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

public class CreateJWT extends AbstractConnector {
    private static final Log log = LogFactory.getLog(CreateJWT.class);
    public static String keyAlias = BigQueryConstant.keyAlias;

    public void connect(MessageContext messageContext) {
        try {
            String token = getJsonWebToken(messageContext);
            messageContext.setProperty(BigQueryConstant.JWT_PROP, token);

        } catch (IOException | InvalidKeyException | SignatureException |
                NoSuchAlgorithmException | UnrecoverableKeyException | CertificateException |
                KeyStoreException e) {
            handleException(e.getMessage(), e, messageContext);
        }
    }

    public static byte[] signData(byte[] data, PrivateKey privateKey) throws InvalidKeyException,
            SignatureException, NoSuchAlgorithmException {

        Signature signature = Signature.getInstance(BigQueryConstant.signature);
        signature.initSign(privateKey);
        signature.update(data);
        return signature.sign();
    }

    private static PrivateKey getPrivateKey(String keyFile, String password)
            throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException,
            UnrecoverableKeyException {

        KeyStore keystore = KeyStore.getInstance(BigQueryConstant.keyStore);
        keystore.load(new FileInputStream(keyFile), password.toCharArray());
        PrivateKey privateKey = (PrivateKey) keystore.getKey(keyAlias, password.toCharArray());
        return privateKey;
    }

    private static String getJsonWebToken(MessageContext messageContext) throws
            InvalidKeyException, SignatureException, NoSuchAlgorithmException,
            UnrecoverableKeyException, KeyStoreException, CertificateException, IOException {

        String keyStoreLocation = (String) ConnectorUtils.lookupTemplateParamater(messageContext,
                BigQueryConstant.keyStoreLocation);
        String serviceAccount = (String) ConnectorUtils.lookupTemplateParamater(messageContext,
                BigQueryConstant.serviceAccount);
        String scope = (String) ConnectorUtils.lookupTemplateParamater(messageContext,
                BigQueryConstant.scope);
        String password = BigQueryConstant.password;
        String jwtHeaderStr;
        String jwtClaimStr;
        PrivateKey privateKey;

        JSONObject jwtHeader = new JSONObject();
        try {
            jwtHeader.put(BigQueryConstant.JWT_HEADER_ALGO, BigQueryConstant.JWT_HEADER_ALGO_VALUE);
            jwtHeader.put(BigQueryConstant.JWT_HEADER_TYPE, BigQueryConstant.JWT_HEADER_TYPE_VALUE);
            jwtHeaderStr = jwtHeader.toString();
        } catch (JSONException e) {
            throw new SynapseException(e.getMessage(), e);
        }

        byte[] encodedHeader = new byte[0];
        if (jwtHeaderStr != null) {
            encodedHeader = Base64.encodeBase64(jwtHeaderStr.getBytes(BigQueryConstant.utf));
        }

        JSONObject jwtClaimSet = new JSONObject();
        long iat = (System.currentTimeMillis() / 1000) - 60;
        long exp = iat + 3600;
        try {
            jwtClaimSet.put(BigQueryConstant.JWT_CLAIMSET_ISS, serviceAccount);
            jwtClaimSet.put(BigQueryConstant.scope, scope);
            jwtClaimSet.put(BigQueryConstant.JWT_CLAIMSET_AUD, BigQueryConstant.tokenEndpoint);
            jwtClaimSet.put(BigQueryConstant.JWT_CLAIMSET_EXP, +exp);
            jwtClaimSet.put(BigQueryConstant.JWT_CLAIMSET_IAT, +iat);
            jwtClaimStr = jwtClaimSet.toString();
        } catch (JSONException e) {
            throw new SynapseException(e.getMessage(), e);
        }

        byte[] encodedClaimSet = new byte[0];
        if (jwtClaimStr != null) {
            encodedClaimSet = Base64.encodeBase64(jwtClaimStr.getBytes(BigQueryConstant.utf));
        }

        StringBuilder token = new StringBuilder();
        token.append(new String(encodedHeader));
        token.append(".");
        token.append(new String(encodedClaimSet));
        try {
            privateKey = getPrivateKey(keyStoreLocation, password);
            byte[] sig = signData(token.toString().getBytes(BigQueryConstant.utf), privateKey);
            byte[] encodedSig = Base64.encodeBase64(sig);
            token.append(".");
            token.append(new String(encodedSig));
        } catch (IOException | InvalidKeyException | SignatureException | NoSuchAlgorithmException |
                KeyStoreException | UnrecoverableKeyException | CertificateException e) {
            throw new SynapseException(e.getMessage(), e);
        }
        return token.toString();
    }
}