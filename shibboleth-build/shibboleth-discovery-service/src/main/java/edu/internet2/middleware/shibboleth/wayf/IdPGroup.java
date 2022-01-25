/*
 * Licensed to the University Corporation for Advanced Internet Development, Inc. (UCAID) under one
 * or more contributor license agreements. See the NOTICE file distributed with this work for
 * additional information regarding copyright ownership. The UCAID licenses this file to You under
 * the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package edu.internet2.middleware.shibboleth.wayf;

import java.util.List;

/**
 *
 */
public class IdPGroup {

	private String name;

	private String logo;

	private String buttonCssClass;

	private String description;

	private String descriptionLabel;

	private int order;

	private List<Link> links;

	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @return Returns the order.
	 */
	public int getOrder() {
		return order;
	}

	/**
	 * @param order The order to set.
	 */
	public void setOrder(int order) {
		this.order = order;
	}

	/**
	 * @return Returns the description.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return Returns the links.
	 */
	public List<Link> getLinks() {
		return links;
	}

	/**
	 * @param links The links to set.
	 */
	public void setLinks(List<Link> links) {
		this.links = links;
	}

	/**
	 * @return Returns the buttonCssClass.
	 */
	public String getButtonCssClass() {
		return buttonCssClass;
	}

	/**
	 * @param buttonCssClass The buttonCssClass to set.
	 */
	public void setButtonCssClass(String buttonCssClass) {
		this.buttonCssClass = buttonCssClass;
	}

	/**
	 * @return the descriptionLabel
	 */
	public String getDescriptionLabel() {
		return descriptionLabel;
	}

	/**
	 * @param descriptionLabel the descriptionLabel to set
	 */
	public void setDescriptionLabel(String descriptionLabel) {
		this.descriptionLabel = descriptionLabel;
	}

}
