/*******************************************************************************
 * Copyright (c) 1998, 2009 Oracle. All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0 and Eclipse Distribution
 * License v. 1.0 which accompanies this distribution. The Eclipse Public License is available at
 * http://www.eclipse.org/legal/epl-v10.html and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 * 
 * Contributors: dclarke - created for CShema Generation example without a live JDBC connection
 ******************************************************************************/
package it.osapulie.etl.ddl.support;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

/**
 * Mock JDBC {@link Driver} which does nothing but provide fake connections when the JDBC URL starts
 * with "emulate:". <br/><br/> <i>Code adapted from
 * https://bugs.eclipse.org/bugs/show_bug.cgi?id=297544</i>
 */
public class CreateSchemaMockDriver implements Driver {

	// public static void main( String[] args ) {
	// Map<String, String> properties = new HashMap<String, String>();
	//
	// properties.put( PersistenceUnitProperties.LOGGING_LEVEL, SessionLog.FINE_LABEL );
	// properties.put( PersistenceUnitProperties.CATEGORY_LOGGING_LEVEL_ +
	// SessionLog.EJB_OR_METADATA, SessionLog.WARNING_LABEL );
	//
	// properties.put( PersistenceUnitProperties.JDBC_DRIVER, CreateSchemaMockDriver.class.getName()
	// );
	// properties.put( PersistenceUnitProperties.JDBC_URL, "emulate:mysql" );
	// properties.put( PersistenceUnitProperties.JDBC_CONNECTIONS_MIN, "0" );
	// properties.put( PersistenceUnitProperties.JDBC_WRITE_CONNECTIONS_MIN, "0" );
	//
	// properties.put( PersistenceUnitProperties.DDL_GENERATION,
	// PersistenceUnitProperties.DROP_AND_CREATE );
	// properties.put( PersistenceUnitProperties.DDL_GENERATION_MODE,
	// PersistenceUnitProperties.DDL_SQL_SCRIPT_GENERATION );
	// properties.put( PersistenceUnitProperties.APP_LOCATION, "/tmp" );
	//
	// properties.put( PersistenceUnitProperties.ECLIPSELINK_PERSISTENCE_XML,
	// "META-INF/ddl-persistence.xml" );
	// properties.put( PersistenceUnitProperties.TARGET_DATABASE, MySQLPlatform.class.getName() );
	//
	// EntityManagerFactory emf = Persistence.createEntityManagerFactory( "osapulie", properties );
	// EntityManager em = emf.createEntityManager();
	// em.close();
	// emf.close();
	// }

	static {
		try {
			DriverManager.registerDriver(new CreateSchemaMockDriver());
		}
		catch (SQLException ignore) {
		}
	}

	@Override
	public Connection connect(String url, java.util.Properties info) throws SQLException {
		if (acceptsURL(url)) {
			return (Connection) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[] { Connection.class }, new ConnectionHandler());
		}
		return null;
	}

	@Override
	public boolean acceptsURL(String url) {
		return url.startsWith("emulate:");
	}

	@Override
	public DriverPropertyInfo[] getPropertyInfo(String url, java.util.Properties info) {
		return null;
	}

	@Override
	public int getMajorVersion() {
		return 1;
	}

	@Override
	public int getMinorVersion() {
		return 0;
	}

	@Override
	public boolean jdbcCompliant() {
		return true;
	}

	/**
	 * Proxy handler for {@link Connection} which does nothing.
	 */
	class ConnectionHandler implements InvocationHandler {

		@Override
		public Object invoke(Object arg0, Method arg1, Object[] arg2) throws Throwable {
			return null;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Driver#getParentLogger()
	 */
	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}
}
