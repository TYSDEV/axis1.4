/*
 * Copyright 2001-2004 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.geronimo.ews.ws4j2ee.context.security.impl;

import org.apache.geronimo.ews.ws4j2ee.context.security.SecurityContext4J2EE;

import javax.security.auth.callback.CallbackHandler;
import javax.security.cert.X509Certificate;

/**
 * @author Rajith Priyanga (rpriyanga@yahoo.com)
 * @date Apr 6, 2004
 */
public class SecurityContext4J2EEImpl implements SecurityContext4J2EE {

    private String nonce = null;
    private String created = null;
    private String username = null;
    private char[] pwd = null;

    private X509Certificate x509Cert = null;
    private byte[] kerberosTicket = null;

    private boolean privacy = false;
    private boolean integrity = false;
    private boolean isPwdDigested = false;
    private CallbackHandler cbh;

    public SecurityContext4J2EEImpl() {
    }

    /**
     * @see org.apache.geranimo.ews.ws4j2ee.context.security.SecurityContext4J2EE#sufficientIntegrity()
     */
    public boolean sufficientIntegrity() {
        // TODO 
        return false;
    }

    /**
     * @see org.apache.geranimo.ews.ws4j2ee.context.security.SecurityContext4J2EE#sufficientPrivacy()
     */
    public boolean sufficientPrivacy() {
        // TODO 
        return false;
    }

    /**
     * @see org.apache.geranimo.ews.ws4j2ee.context.security.SecurityContext4J2EE#getNonce()
     */
    public String getNonce() {
        return this.nonce;
    }

    /**
     * @see org.apache.geranimo.ews.ws4j2ee.context.security.SecurityContext4J2EE#getCreated()
     */
    public String getCreated() {
        return this.created;
    }

    /**
     * @see org.apache.geranimo.ews.ws4j2ee.context.security.SecurityContext4J2EE#getPasswordDigest()
     */
    public char[] getPasswordDigest() {
        if (this.isPwdDigested)
            return this.pwd;
        else
            return null;
    }

    /**
     * @see org.apache.geranimo.ews.ws4j2ee.context.security.SecurityContext4J2EE#getPasswordText()
     */
    public char[] getPasswordText() {
        if (this.isPwdDigested)
            return null;
        else
            return this.pwd;
    }

    /**
     * @see org.apache.geranimo.ews.ws4j2ee.context.security.SecurityContext4J2EE#getUsername()
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Sets the created.
     *
     * @param created The created to set
     */
    public void setCreated(String created) {
        this.created = created;
    }

    /**
     * Sets the integrity.
     *
     * @param integrity The integrity to set
     */
    public void setIntegritySufficient(boolean sufficient) {
        this.integrity = sufficient;
    }

    /**
     * Sets the nonce.
     *
     * @param nonce The nonce to set
     */
    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    /**
     * Sets the privacy.
     *
     * @param privacy The privacy to set
     */
    public void setPrivacySufficient(boolean sufficient) {
        this.privacy = sufficient;
    }

    /**
     * Sets the username.
     *
     * @param username The username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @see org.apache.geranimo.ews.ws4j2ee.context.security.SecurityContext4J2EE#getX509Certificate()
     */
    public X509Certificate getX509Certificate() {
        return this.x509Cert;
    }

    /**
     * @see org.apache.geranimo.ews.ws4j2ee.context.security.SecurityContext4J2EE#getKerberoseTicket()
     */
    public byte[] getKerberoseTicket() {
        return this.kerberosTicket;
    }

    /**
     * Sets the kerberosTicket.
     *
     * @param kerberosTicket The kerberosTicket to set
     */
    public void setKerberosTicket(byte[] kerberosTicket) {
        this.kerberosTicket = kerberosTicket;
    }

    /**
     * Sets the x509Cert.
     *
     * @param x509Cert The x509Cert to set
     */
    public void setX509Certificate(X509Certificate x509Cert) {
        this.x509Cert = x509Cert;
    }

    /**
     * Sets the pwdDigest.
     *
     * @param pwdDigest The pwdDigest to set
     */
    public void setPasswordDigested(boolean isPwdDigested) {
        this.isPwdDigested = isPwdDigested;
    }

    /**
     * @return boolean
     */
    public boolean isPasswordDigested() {
        return isPwdDigested;
    }

    /**
     * Sets the pwd.
     *
     * @param pwd The pwd to set
     */
    public void setPassword(char[] password) {
        this.pwd = password;
    }

    /**
     * @see org.apache.geranimo.ews.ws4j2ee.context.security.SecurityContext4J2EE#getPWDCallbackHandler4J2EE()
     */
    public CallbackHandler getPWDCallbackHandler4J2EE() {
        return this.cbh;
    }

    public void setPWDCallbackHandler4J2EE(CallbackHandler callbackHandler) {
        this.cbh = callbackHandler;
    }

}
