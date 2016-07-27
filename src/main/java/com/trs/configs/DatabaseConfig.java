package com.trs.configs;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import com.trs.proxool.ManagedProxoolDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DatabaseConfig {

  @Value("${db.driver}")
  private String DB_DRIVER;
  
  @Value("${db.password}")
  private String DB_PASSWORD;
  
  @Value("${db.url}")
  private String DB_URL;
  
  @Value("${db.username}")
  private String DB_USERNAME;

  @Value("${hibernate.dialect}")
  private String HIBERNATE_DIALECT;
  
  @Value("${hibernate.show_sql}")
  private String HIBERNATE_SHOW_SQL;
  
  @Value("${hibernate.hbm2ddl.auto}")
  private String HIBERNATE_HBM2DDL_AUTO;

  @Value("${hibernate.jdbc.batch_size}")
  private String  HIBERNATE_JDBC_BATCH_SIZE;

  @Value("${hibernate.cache.use_second_level_cache}")
  private String HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE;

  @Value("${hibernate.cache.use_query_cache}")
  private String HIBERNATE_CACHE_USE_QUERY_CACHE;

  @Value("${entitymanager.packagesToScan}")
  private String ENTITYMANAGER_PACKAGES_TO_SCAN;

  @Value("${db.maximumConnectionCount}")
  private Integer MAXIMUM_CONNECTION_COUNT;

  @Value("${db.maximumActiveTime}")
  private Long MAXIMUN_ACTIVE_TIME;

  @Value("${db.prototypeCount}")
  private Integer PROTOTYPE_COUNT;

  @Value("${db.houseKeepingTestSql}")
  private String HOUSE_KEEPINT_TEST_SQL;

  @Value("${db.testBeforeUse}")
  private Boolean TEST_BEFORE_USE;

  @Bean
  public DataSource dataSource() {
    ManagedProxoolDataSource dataSource = new ManagedProxoolDataSource();
    dataSource.setDriver(DB_URL);
    dataSource.setDriver(DB_DRIVER);
    dataSource.setDriverUrl(DB_URL);
    dataSource.setUser(DB_USERNAME);
    dataSource.setPassword(DB_PASSWORD);
    dataSource.setMaximumConnectionCount(MAXIMUM_CONNECTION_COUNT);
    dataSource.setMaximumActiveTime(MAXIMUN_ACTIVE_TIME);
    dataSource.setPrototypeCount(PROTOTYPE_COUNT);
    dataSource.setHouseKeepingTestSql(HOUSE_KEEPINT_TEST_SQL);
    dataSource.setTestBeforeUse(TEST_BEFORE_USE);
    LazyConnectionDataSourceProxy dataSourceProxy = new LazyConnectionDataSourceProxy(dataSource);
    return dataSourceProxy;
  }

  @Bean
  public LocalSessionFactoryBean sessionFactory() {
    LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
    sessionFactoryBean.setDataSource(dataSource());
    sessionFactoryBean.setPackagesToScan(ENTITYMANAGER_PACKAGES_TO_SCAN);
    Properties hibernateProperties = new Properties();
    hibernateProperties.put("hibernate.dialect", HIBERNATE_DIALECT);
    hibernateProperties.put("hibernate.show_sql", HIBERNATE_SHOW_SQL);
    hibernateProperties.put("hibernate.hbm2ddl.auto", HIBERNATE_HBM2DDL_AUTO);
    hibernateProperties.put("hibernate.jdbc.batch_size", HIBERNATE_JDBC_BATCH_SIZE);
    hibernateProperties.put("hibernate.cache.use_second_level_cache", HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE);
    hibernateProperties.put("hibernate.cache.use_query_cache", HIBERNATE_CACHE_USE_QUERY_CACHE);
    sessionFactoryBean.setHibernateProperties(hibernateProperties);
    return sessionFactoryBean;
  }

  @Bean
  public HibernateTransactionManager transactionManager() {
    HibernateTransactionManager transactionManager = 
        new HibernateTransactionManager();
    transactionManager.setSessionFactory(sessionFactory().getObject());
    return transactionManager;
  }

}
