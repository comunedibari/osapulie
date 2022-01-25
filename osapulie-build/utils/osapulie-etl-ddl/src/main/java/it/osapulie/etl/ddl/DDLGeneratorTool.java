/**
 * Copyright (c) 2011 Links Management & Technology S.p.A.
 */

package it.osapulie.etl.ddl;

import it.osapulie.etl.ddl.support.CreateSchemaMockDriver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.eclipse.persistence.logging.SessionLog;
import org.eclipse.persistence.platform.database.H2Platform;
import org.eclipse.persistence.platform.database.MySQLPlatform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility per la generazione dello schema di DB senza necessità di drivers o altro. <br/>
 * Alla fine dell'elaborazione, il risultato si troverà nella directory <strong>target/</strong>.
 * <br/><br/>
 * <i>Code adapted from https://bugs.eclipse.org/bugs/show_bug.cgi?id=297544</i>
 * 
 * @author Mario Scalas
 */
public class DDLGeneratorTool {

	private static Logger log = LoggerFactory.getLogger( DDLGeneratorTool.class );
	
	public static void main( String[] args ) {
		// 1.Read configuration
		log.info( "OS Apulie :: ETL per generazione schema" );
		DDLGeneratorTool ddlGenerator = new DDLGeneratorTool();
		
		// 2. Cycle on every stored audio and convert it using thumbnailator.
		log.info( "OS Apulie :: Generazione avviata ..." );
		ddlGenerator.generateSchema();
		log.info( "OS Apulie :: Generazione terminata con successo!" );
	}

	public void generateSchema() {
		final String persistenceUnitName = "osapulie";
		String ddlOutputDir = "target";
		String ddlCreateFileName = String.format( "%s-ddl-create.sql", persistenceUnitName );
		String ddlDropFileName = String.format( "%s-ddl-drop.sql", persistenceUnitName );
		String targetDbPlatform = MySQLPlatform.class.getName();
//		String targetDbPlatform = H2Platform.class.getName();
		
		
		Map<String, String> properties = new HashMap<String, String>();

		properties.put( PersistenceUnitProperties.LOGGING_LEVEL, SessionLog.FINEST_LABEL );
		properties.put( PersistenceUnitProperties.CATEGORY_LOGGING_LEVEL_ + SessionLog.EJB_OR_METADATA, SessionLog.WARNING_LABEL );
		
		properties.put( PersistenceUnitProperties.JDBC_DRIVER, CreateSchemaMockDriver.class.getName() );
		properties.put( PersistenceUnitProperties.JDBC_URL, "emulate:mysql" );
		properties.put( PersistenceUnitProperties.CONNECTION_POOL_MIN, "0" );

		properties.put( PersistenceUnitProperties.DDL_GENERATION, PersistenceUnitProperties.DROP_AND_CREATE );
		properties.put( PersistenceUnitProperties.DDL_GENERATION_MODE, PersistenceUnitProperties.DDL_SQL_SCRIPT_GENERATION );
		properties.put( PersistenceUnitProperties.CREATE_JDBC_DDL_FILE, ddlCreateFileName );
		properties.put( PersistenceUnitProperties.DROP_JDBC_DDL_FILE, ddlDropFileName );
		properties.put( PersistenceUnitProperties.APP_LOCATION, ddlOutputDir );
		
		properties.put( PersistenceUnitProperties.ECLIPSELINK_PERSISTENCE_XML, "META-INF/ddl-persistence.xml" );
		properties.put( PersistenceUnitProperties.TARGET_DATABASE, targetDbPlatform );

		EntityManagerFactory emf = Persistence.createEntityManagerFactory( persistenceUnitName, properties );
		EntityManager em = emf.createEntityManager();
		em.close();
		emf.close();

		// Questo è necessario perchè la generazione DDL di EclipseLink non aggiunge i ;
		fixSqlLineTerminators( ddlOutputDir, ddlCreateFileName );
		fixSqlLineTerminators( ddlOutputDir, ddlDropFileName );
	}

	/**
	 * @param file
	 */
	private void fixSqlLineTerminators( String ddlOutputDir, String ddlFileName ) {
		log.info( String.format( "Fixing %s ...", ddlFileName ) );
		File inputFile = new File( ddlOutputDir + File.separator + ddlFileName );
		try {
			BufferedReader br = new BufferedReader( new FileReader( inputFile ) );
			
			File outFile = File.createTempFile( "fix_", ".tmp" );
			log.debug( String.format( "Creating work file %s ...", outFile ) );
			BufferedWriter bw = new BufferedWriter( new FileWriter( outFile ) );
			
			String line = null;
			while ((line = br.readLine()) != null) {
				bw.write( line );
				bw.append( ";" );
				bw.append( "\n" );
				System.out.println( line );
			}
			br.close();
			bw.flush(); bw.close();

//			inputFile.delete();
			outFile.renameTo( inputFile );
			log.info( String.format( "Done fixing %s!", ddlFileName ) );
		} catch (Exception e) {
			log.error( e.getMessage(), e );
		}
	}
}
