/* Copyright (c) 2014 Pathways Community Network Institute
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.openhmis.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.net.ssl.HttpsURLConnection;

import org.apache.log4j.Logger;
import org.openhmis.dao.TmpUserDAO;
import org.openhmis.domain.TmpUser;
import org.openhmis.exception.AuthenticationFailureException;

import com.google.api.client.auth.oauth2.TokenResponseException;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

public class Authentication {
	public static final String EXISTS = "EXISTS";
	public static final String READ = "READ";
	public static final String WRITE = "WRITE";
	public static final String ADMIN = "ADMIN";

	private static final HttpTransport TRANSPORT = new NetHttpTransport();
	private static final JacksonFactory JSON_FACTORY = new JacksonFactory();
	private static final ApplicationPropertyUtil applicationPropertyUtil = new ApplicationPropertyUtil();
	private static final String CLIENT_ID = applicationPropertyUtil.getGoogleClientId();
	private static final String CLIENT_SECRET = applicationPropertyUtil.getGoogleSecret();
        private static final Boolean AUTH_ENABLED = applicationPropertyUtil.getAuthEnabled();
	
	private static final Logger log = Logger.getLogger(Authentication.class);
	
	private Authentication() {}

	public static String getGoogleToken(String code) {
		try {
			// Upgrade the authorization code into an access and refresh token.
			GoogleTokenResponse tokenResponse =
				new GoogleAuthorizationCodeTokenRequest(TRANSPORT, JSON_FACTORY,
					CLIENT_ID, CLIENT_SECRET, code, "postmessage").execute();

			return tokenResponse.toString();

		} catch (TokenResponseException e) {
			log.error(e);
			throw new AuthenticationFailureException();
		} catch (IOException e) {
			// TODO: this should be a different exception
			log.error(e);
			throw new AuthenticationFailureException();
		}
	}

	public static Boolean googleAuthenticate(String tokenString) {
		return googleAuthenticate(tokenString, Authentication.EXISTS);	
	}
	
	public static Boolean googleAuthenticate(String tokenString, String authType) {
                if (getAuthEnabled() == false)
                        return true;

                if(tokenString == null)
                        return false;
                
                String externalId = resolveIdentity(tokenString);
			
                log.debug("Login attempt:" + externalId);
			
                // Make sure this user has the requested credentials
                TmpUserDAO tmpUserDAO = new TmpUserDAO();
                /*
                 * TBD: does this throw exceptions?  If so, where are
                 * they caught?
                 */
                TmpUser tmpUser = tmpUserDAO.getTmpUserByExternalId(externalId);
			
                // If the user doesn't exist, they aren't authorized
                if(externalId == null)
                        return false;
			
                switch(authType) {
                case Authentication.EXISTS:
                        return true;
                case Authentication.READ:
                        return true;
                case Authentication.WRITE:
                        if(tmpUser != null && tmpUser.getCanWrite() == 1)
                            return true;
                        break;
                case Authentication.ADMIN:
                        if(tmpUser != null && tmpUser.getCanAdmin() == 1)
                            return true;
                        break;
                }
                return false;
	}

        public static Boolean getAuthEnabled() {
            return AUTH_ENABLED;
        }

        /*
         * Given the id token, return the identity represented (usually a
         * Google email address, for now).
        */
        public static String resolveIdentity(String id_token) {
                String externalId;
                try {
                    // Verify that the token is a legitimate google token
                    GoogleIdToken token = GoogleIdToken.parse(JSON_FACTORY, id_token);
                    GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier(TRANSPORT, JSON_FACTORY);
                    verifier.verify(token);
    			
                    // If we get here then this is a valid google item
                    externalId = token.getPayload().getEmail();
		} catch (IOException e) {
			log.debug("IOException authenticating with Google: " + e.toString());
                        externalId = null;
		} catch (GeneralSecurityException e) {
			log.debug("GeneralSecurityException authenticating with Google: " + e.toString());
                        externalId = null;
		} catch (IllegalArgumentException e) {
			log.debug("IllegalArgumentException authenticating with Google: " + e.toString());
                        externalId = null;
		} catch (Exception e) {
			log.debug("Unexpected exception authenticating with Google: " + e.toString());
                        externalId = null;
		}
                
                return externalId;
        }

}
