package it.eng.tz.area.vasta.protocollo.spring.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableScheduling
@ComponentScan(basePackages= {"it.eng.tz.area.vasta.protocollo"})
@PropertySource( value={"classpath:config.properties"}, encoding="UTF-8", ignoreResourceNotFound=false)
@EnableTransactionManagement
public class DBConfiguration
{
	
	@Autowired
	private Environment env;
	@Bean("txMgr")
	@Autowired
	public PlatformTransactionManager datasourceTxMgr()
	{
		
		DataSourceTransactionManager result = new DataSourceTransactionManager(hikariDataSourceOsapulie());
		return result;
	}
	@Bean(name="hikariDsOsapulie", destroyMethod ="close")
	public DataSource hikariDataSourceOsapulie(){
		
	    HikariConfig hikariConfig = new HikariConfig();
	    
	    hikariConfig.setDriverClassName(env.getProperty("area.vasta.protocollo.db.driverClassName"));
	    hikariConfig.setJdbcUrl(env.getProperty("area.vasta.protocollo.db.jdbcUrlOsapulie")); 
	    hikariConfig.setUsername(env.getProperty("area.vasta.protocollo.db.username"));
	    hikariConfig.setPassword(env.getProperty("area.vasta.protocollo.db.password"));
	    hikariConfig.setMaximumPoolSize(Integer.parseInt(env.getProperty("area.vasta.protocollo.db.maxPoolSize")));
	    hikariConfig.setConnectionTestQuery(env.getProperty("area.vasta.protocollo.db.validationQuery"));
	    hikariConfig.setPoolName("Area vasta Protocollo New Pool");
	    hikariConfig.setIdleTimeout(Integer.parseInt(env.getProperty("area.vasta.protocollo.db.maxIdleTime")));
	    hikariConfig.addDataSourceProperty("dataSource.cachePrepStmts", "true");
	    hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSize", "250");
	    hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSqlLimit", "2048");
	    hikariConfig.addDataSourceProperty("dataSource.useServerPrepStmts", "true");
	    HikariDataSource dataSource = new HikariDataSource(hikariConfig);
	    return dataSource;
	}
	
}