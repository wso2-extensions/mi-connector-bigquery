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

/**
 * Create Json Web Token to get the accessToken for
 * BigQuery Connector using serviceAccount authentication.
 */
public class CreateJWT extends AbstractConnector {
    private static final Log log = LogFactory.getLog(CreateJWT.class);
    public static String keyAlias = JWTConstant.KEYALIAS;

    public void connect(MessageContext messageContext) {
        try {
            String token = getJsonWebToken(messageContext);
            messageContext.setProperty(JWTConstant.JWT_PROP, token);

        } catch (IOException | InvalidKeyException | SignatureException |
                NoSuchAlgorithmException | UnrecoverableKeyException | CertificateException |
                KeyStoreException e) {
            handleException(e.getMessage(), e, messageContext);
        }
    }

    /**
     * The method to sign the byte array of data using the private key.
     *
     * @param data the byte array of data to generate the signature
     * @param privateKey the private key to sign the byte array
     * @return the signed signature
     * @throws InvalidKeyException
     * @throws SignatureException
     * @throws NoSuchAlgorithmException
     */
    public static byte[] signData(byte[] data, PrivateKey privateKey) throws InvalidKeyException,
            SignatureException, NoSuchAlgorithmException {

        Signature signature = Signature.getInstance(JWTConstant.SIGNATURE);
        signature.initSign(privateKey);
        signature.update(data);
        return signature.sign();
    }

    /**
     * The method is using to extract the private key from the p12 file
     *
     * @param keyFile the p12 file to extract the private key
     * @param password the password to extract the p12 file
     * @return the private key
     * @throws KeyStoreException
     * @throws IOException
     * @throws NoSuchAlgorithmException
     * @throws CertificateException
     * @throws UnrecoverableKeyException
     */
    private static PrivateKey getPrivateKey(String keyFile, String password)
            throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException,
            UnrecoverableKeyException {

        KeyStore keystore = KeyStore.getInstance(JWTConstant.KEY_STORE);
        keystore.load(new FileInputStream(keyFile), password.toCharArray());
        return (PrivateKey) keystore.getKey(keyAlias, password.toCharArray());
    }

    /**
     * The method is using to construct the Json Web Token
     * @param messageContext the message context
     * @return the json web token
     * @throws InvalidKeyException
     * @throws SignatureException
     * @throws NoSuchAlgorithmException
     * @throws UnrecoverableKeyException
     * @throws KeyStoreException
     * @throws CertificateException
     * @throws IOException
     */
    private static String getJsonWebToken(MessageContext messageContext) throws
            InvalidKeyException, SignatureException, NoSuchAlgorithmException,
            UnrecoverableKeyException, KeyStoreException, CertificateException, IOException {

        String keyStoreLocation = (String) ConnectorUtils.lookupTemplateParamater(messageContext,
                JWTConstant.KEY_STORE_LOCATION);
        String serviceAccount = (String) ConnectorUtils.lookupTemplateParamater(messageContext,
                JWTConstant.SERVICE_ACCOUNT);
        String scope = (String) ConnectorUtils.lookupTemplateParamater(messageContext,
                JWTConstant.SCOPE);
        String password = JWTConstant.PASSWORD;
        String jwtHeaderStr;
        String jwtClaimStr;
        PrivateKey privateKey;

        //Construct JWT Header
        JSONObject jwtHeader = new JSONObject();
        try {
            jwtHeader.put(JWTConstant.JWT_HEADER_ALGO, JWTConstant.JWT_HEADER_ALGO_VALUE);
            jwtHeader.put(JWTConstant.JWT_HEADER_TYPE, JWTConstant.JWT_HEADER_TYPE_VALUE);
            jwtHeaderStr = jwtHeader.toString();
        } catch (JSONException e) {
            throw new SynapseException(e.getMessage(), e);
        }

        byte[] encodedHeader = new byte[0];
        if (jwtHeaderStr != null) {
            encodedHeader = Base64.encodeBase64(jwtHeaderStr.getBytes(JWTConstant.UTF));
        }

        //Construct JWT Claim
        JSONObject jwtClaimSet = new JSONObject();
        long iat = (System.currentTimeMillis() / 1000) - 60;
        long exp = iat + 3600;
        try {
            jwtClaimSet.put(JWTConstant.JWT_CLAIMSET_ISS, serviceAccount);
            jwtClaimSet.put(JWTConstant.SCOPE, scope);
            jwtClaimSet.put(JWTConstant.JWT_CLAIMSET_AUD, JWTConstant.TOKEN_ENDPOINT);
            jwtClaimSet.put(JWTConstant.JWT_CLAIMSET_EXP, +exp);
            jwtClaimSet.put(JWTConstant.JWT_CLAIMSET_IAT, +iat);
            jwtClaimStr = jwtClaimSet.toString();
        } catch (JSONException e) {
            throw new SynapseException(e.getMessage(), e);
        }

        byte[] encodedClaimSet = new byte[0];
        if (jwtClaimStr != null) {
            encodedClaimSet = Base64.encodeBase64(jwtClaimStr.getBytes(JWTConstant.UTF));
        }

        StringBuilder token = new StringBuilder();
        token.append(new String(encodedHeader));
        token.append(".");
        token.append(new String(encodedClaimSet));
        try {
            //Create JWT SIGNATURE
            privateKey = getPrivateKey(keyStoreLocation, password);
            byte[] sig = signData(token.toString().getBytes(JWTConstant.UTF), privateKey);
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