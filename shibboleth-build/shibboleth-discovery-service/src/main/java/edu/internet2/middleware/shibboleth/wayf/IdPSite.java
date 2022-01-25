/*
 * Licensed to the University Corporation for Advanced Internet Development,
 * Inc. (UCAID) under one or more contributor license agreements.  See the
 * NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The UCAID licenses this file to You under the Apache
 * License, Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package edu.internet2.middleware.shibboleth.wayf;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.opensaml.saml2.common.Extensions;
import org.opensaml.saml2.metadata.EntityDescriptor;
import org.opensaml.saml2.metadata.IDPSSODescriptor;
import org.opensaml.saml2.metadata.Organization;
import org.opensaml.saml2.metadata.OrganizationDisplayName;
import org.opensaml.saml2.metadata.SingleSignOnService;

/**
 * A class which abstracts an IdP for the sake of the WAYF display. Given an {@link EntityDescriptor} as input it
 * provides bean style get functions for the name (EntityId), the display name (a hybrid of Organization name or
 * EntityId and the IdP's SSO connection point.
 *
 */
public class IdPSite {

    /** The OpenSaml element that this stands for. */
    private final EntityDescriptor entity;

    /** The language we set up. */
    private String displayLanguage;

    private String logo;

    private IdPGroup idPGroup;

    private List<RequestParameter> requestParameters;

    /**
     * Create a new element from the provided Entity.
     *
     * @param entityParam - What to create from
     */
    public IdPSite(EntityDescriptor entityParam) {
        entity = entityParam;
    }

    /**
     * Get the name for the enclosed entity.
     *
     * @return the name for the enclosed entity.
     */
    public String getName() {
        return entity.getEntityID();
    }

    /**
     * Get the user friendly name for the entity, collecting the locale from the browser if possible.
     *
     * @param req the request
     * @return a user friendly name.
     */
    public String getDisplayName(HttpServletRequest req) {
        //
        // Get the browser locale, failing that the server one
        //
        Locale locale = req.getLocale();
        if (null == locale) {
            Locale.getDefault();
        }
        String lang = locale.getLanguage();

        return getDisplayName(lang);
    }

    /**
     * Get the user friendly name for the entity, using provided language.
     *
     * @param lang the language.
     *
     * @return a user friendly name.
     */
    private String getDisplayName(String lang) {
        Organization org = entity.getOrganization();

        if (org == null) {
            return entity.getEntityID();
        }

        List<OrganizationDisplayName> list = org.getDisplayNames();

        //
        // Lookup first by locale
        //

        for (OrganizationDisplayName name : list) {
            if (null != name && lang.equals(name.getName().getLanguage())) {
                return name.getName().getLocalString();
            }
        }

        //
        // If that doesn't work then anything goes
        //

        for (OrganizationDisplayName name : list) {
            if (null != name && null != name.getName().getLocalString()) {
                return name.getName().getLocalString();
            }
        }

        //
        // If there is still nothing then use the entity Id
        //
        return entity.getEntityID();
    }

    /**
     * Get the user friendly name for the entity, the language we previously set up.
     *
     * @return a user friendly name.
     */
    public String getDisplayName() {
        return getDisplayName(displayLanguage);
    }

    /**
     * Return all the extension elements.
     *
     * @return the extensions
     */
    public Extensions getExtensions() {
        IDPSSODescriptor idpss = entity.getIDPSSODescriptor(XMLConstants.SHIB_NS);
        if (null == idpss) {
            //
            // Get the SAML2 protocol
            //
            idpss = entity.getIDPSSODescriptor(XMLConstants.SAML2_PROTOCOL);
        }
        if (null == idpss) {
            return null;
        }
        return idpss.getExtensions();
    }

    /**
     * Comparison so we can sort the output for jsp.
     *
     * @param o What to compare against
     * @param req The request.
     * @return numeric encoding of comparison
     * @see java.lang.Comparator
     */
    protected int compareTo(Object o, HttpServletRequest req) {

        String myDisplayName;
        String otherDisplayName;
        IdPSite other;

        if (equals(o)) {
            return 0;
        }

        myDisplayName = getDisplayName(req);
        if (null == myDisplayName) {
            myDisplayName = "";
        }

        other = (IdPSite) o;
        otherDisplayName = other.getDisplayName(req);
        if (null == otherDisplayName) {
            otherDisplayName = "";
        }

        int result = myDisplayName.toLowerCase().compareTo(otherDisplayName.toLowerCase());
        if (result == 0) {
            result = myDisplayName.compareTo(otherDisplayName);
        }
        return result;
    }

    /**
     * Get URL for Shibboleth SSO endpoint. When a user has selected an IdP, or prior to displaying the entity this
     * provides the address to which we will redirect or null if we cannot
     *
     * @param theEntity the entity in question
     * @return http address for the IdP this represents.
     */

    protected static String getAddressForWAYF(EntityDescriptor theEntity) {
        IDPSSODescriptor idp = theEntity.getIDPSSODescriptor(XMLConstants.SHIB_NS);

        if (null == idp) {
            return null;
        }

        List<SingleSignOnService> ssoList = idp.getSingleSignOnServices();

        for (SingleSignOnService signOnService : ssoList) {
            if (XMLConstants.IDP_SSO_BINDING.equals(signOnService.getBinding())) {
                return signOnService.getLocation();
            }
        }
        return null;

    }

    /**
     * Get URL for Shibboleth SSO endpoint. When a user has selected an IdP, this provides the address to which we will
     * redirect or null if we cannot
     *
     * @return http address for the IdP this represents.
     */
    public String getAddressForWAYF() {

        return getAddressForWAYF(entity);
    }

    /**
     * Prior to display we set the display language from the browser. There is probably a proper way to do this using
     * jsp, but I want to keep the API between JSP and java the same 1.3->2.0
     *
     * @param lang the language to set
     */
    public void setDisplayLanguage(String lang) {
        this.displayLanguage = lang;
    }

    /**
     * This allows us to set up sorted lists of entities with respect to the browser request.
     *
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    public static class Compare implements Comparator<IdPSite> {

        /**
         * The request.
         */
        private final HttpServletRequest request;

        /**
         * Private default Constructor. Guarantees that req is non null
         *
         */
        @SuppressWarnings("unused") private Compare() {
            request = null;
        }

        /**
         * Constructor.
         *
         * @param theRequest the request which drives the comparison.
         */
        public Compare(HttpServletRequest theRequest) {
            this.request = theRequest;
        }

        /** {@inheritDoc} */
        @Override public int compare(IdPSite o1, IdPSite o2) {
            return o1.compareTo(o2, request);
        }

    }

    /**
     * @return Returns the logo.
     */
    public String getLogo() {
        return logo;
    }

    /**
     * @param logo The logo to set.
     */
    public void setLogo(String logo) {
        this.logo = logo;
    }

    /**
     * @return Returns the idPGroup.
     */
    public IdPGroup getIdPGroup() {
        return idPGroup;
    }

    /**
     * @param idPGroup The idPGroup to set.
     */
    public void setIdPGroup(IdPGroup idPGroup) {
        this.idPGroup = idPGroup;
    }

    /**
     * @return Returns the requestParameters.
     */
    public List<RequestParameter> getRequestParameters() {
        return requestParameters;
    }

    /**
     * @param requestParameters The requestParameters to set.
     */
    public void setRequestParameters(List<RequestParameter> requestParameters) {
        this.requestParameters = requestParameters;
    }
}
