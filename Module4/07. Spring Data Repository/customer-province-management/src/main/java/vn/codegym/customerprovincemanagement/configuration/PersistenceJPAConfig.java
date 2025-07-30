package vn.codegym.customerprovincemanagement.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Persistence JPA configuration for the application.
 * Configures data source, entity manager factory, and transaction management.
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("vn.codegym.customerprovincemanagement.repository")
public class PersistenceJPAConfig {

    /**
     * Configures and provides the EntityManager bean.
     * @param entityManagerFactory The EntityManagerFactory to create the EntityManager from.
     * @return An EntityManager instance.
     */
    @Bean
    @Qualifier(value = "entityManager")
    public EntityManager entityManager(EntityManagerFactory entityManagerFactory) {
        return entityManagerFactory.createEntityManager();
    }

    /**
     * Configures and provides the LocalContainerEntityManagerFactoryBean.
     * This bean is responsible for creating the EntityManagerFactory.
     * @return A new instance of LocalContainerEntityManagerFactoryBean.
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("vn.codegym.customerprovincemanagement.model");

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());
        return em;
    }

    /**
     * Configures and provides the DataSource bean.
     * @return A new instance of DriverManagerDataSource.
     */
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/cms?useSSL=false&useUnicode=true&characterEncoding=utf8");
        dataSource.setUsername("root");
        dataSource.setPassword("ducthien2014");
        return dataSource;
    }

    /**
     * Configures and provides the PlatformTransactionManager bean.
     * @param emf The EntityManagerFactory to manage transactions for.
     * @return A new instance of JpaTransactionManager.
     */
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    /**
     * Provides additional Hibernate properties.
     * @return A Properties object containing Hibernate configurations.
     */
    public Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        return properties;
    }
}
