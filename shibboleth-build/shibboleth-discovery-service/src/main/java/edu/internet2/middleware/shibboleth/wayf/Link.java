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

/**
 *
 */
public class Link {

	private String name;
	private String nameLabel;
	private String value;

	/**
	 * Constructor.
	 *
	 * @param key
	 * @param value2
	 */
	public Link(String name, String nameLabel, String value) {
		this.name = name;
		this.nameLabel = nameLabel;
		this.value = value;
	}

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
	 * @return Returns the value.
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value The value to set.
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the nameLabel
	 */
	public String getNameLabel() {
		return nameLabel;
	}

	/**
	 * @param nameLabel the nameLabel to set
	 */
	public void setNameLabel(String nameLabel) {
		this.nameLabel = nameLabel;
	}
}
