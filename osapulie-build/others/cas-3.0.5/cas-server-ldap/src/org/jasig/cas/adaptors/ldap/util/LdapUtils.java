/*
 * Copyright 2005 The JA-SIG Collaborative. All rights reserved. See license
 * distributed with this file and available online at
 * http://www.uportal.org/license.html
 */
package org.jasig.cas.adaptors.ldap.util;

import java.util.Iterator;
import java.util.Properties;

import javax.naming.NamingException;
import javax.naming.directory.DirContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Utilities related to LDAP functions.
 * 
 * @author Scott Battaglia
 * @version $Revision: 1.7 $ $Date: 2006/07/20 01:45:35 $
 * @since 3.0
 */
public final class LdapUtils {

    private static final Log logger = LogFactory.getLog(LdapUtils.class);
    
    private LdapUtils() {
        // private constructor so that no one can instantiate.
    }

    /**
     * Utility method to replace the placeholders in the filter with the proper
     * values from the userName.
     * 
     * @param filter
     * @param userName
     * @return the filtered string populated with the username
     */
    public static String getFilterWithValues(final String filter,
        final String userName) {
        final Properties properties = new Properties();
        final String[] userDomain;
        String newFilter = filter;

        properties.setProperty("%u", userName);

        userDomain = userName.split("@");

        properties.setProperty("%U", userDomain[0]);

        if (userDomain.length > 1) {
            properties.setProperty("%d", userDomain[1]);

            final String[] dcArray = userDomain[1].split("\\.");

            for (int i = 0; i < dcArray.length; i++) {
                properties.setProperty("%" + (i + 1), dcArray[dcArray.length
                    - 1 - i]);
            }
        }

        for (final Iterator iter = properties.keySet().iterator(); iter.hasNext();) {
            final String key = (String) iter.next();
            final String value = properties.getProperty(key, "");

            newFilter = newFilter.replaceFirst(key, value);
        }

        return newFilter;
    }
    
    /**
     * Close the given context and ignore any thrown exception. This is useful
     * for typical finally blocks in manual Ldap statements.
     * 
     * @param context the Ldap context to close
     */
    public static void closeContext(final DirContext context) {
        if (context != null) {
            try {
                context.close();
            } catch (NamingException ex) {
                logger.warn("Could not close context", ex);
            }
        }
    }
}
