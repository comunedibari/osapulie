/*
 * Copyright 2005 The JA-SIG Collaborative. All rights reserved. See license
 * distributed with this file and available online at
 * http://www.uportal.org/license.html
 */
package org.jasig.cas.adaptors.x509.authentication.principal;

import java.security.cert.X509Certificate;

import org.jasig.cas.authentication.principal.Principal;
import org.jasig.cas.authentication.principal.SimplePrincipal;

/**
 * 
 * @author Scott Battaglia
 * @version $Revision: 1.1 $ $Date: 2006/03/17 01:56:01 $
 * @since 3.0
 *
 */
public final class X509CertificateCredentialsToDistinguishedNamePrincipalResolver
    extends AbstractX509CertificateCredentialsToPrincipalResolver {

    protected Principal resolvePrincipalInternal(
        final X509Certificate certificate) {
        return  new SimplePrincipal(certificate.getSubjectDN().getName());
    }
}
