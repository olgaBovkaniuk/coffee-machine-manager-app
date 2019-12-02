package by.pvt;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableWebMvc
@EnableScheduling
@EnableAsync
@ComponentScan("by.pvt")
@EnableTransactionManagement
@PropertySource("classpath:database.properties")
@PropertySource("classpath:hibernate.properties")
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {

    @Autowired
    private Environment environment;

    @Bean
    @Primary
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver resolver =
                new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/coffee-machine/welcome").setViewName("welcome");
    }

    @Bean
    @Primary
    public CommonsMultipartResolver multipartResolver() {
        return new CommonsMultipartResolver();
    }

    @Bean
    @Primary
    public BasicDataSource basicDataSource() {
        BasicDataSource basicDataSource = new BasicDataSource();

        basicDataSource.setUrl(environment.getProperty("connection.read.url"));
        basicDataSource.setUsername(environment.getProperty("connection.read.username"));
        basicDataSource.setPassword(environment.getProperty("connection.read.password"));
        basicDataSource.setDriverClassName(environment.getProperty("connection.read.driver_class"));
        basicDataSource.setMaxTotal(Integer.parseInt(environment.getProperty("connection.read.max_total")));

        return basicDataSource;
    }

    @Bean
    @Primary
    public LocalSessionFactoryBean localSessionFactoryBean(DataSource basicDataSource) {
        LocalSessionFactoryBean localSessionFactoryBean
                = new LocalSessionFactoryBean();

        localSessionFactoryBean.setDataSource(basicDataSource);
        localSessionFactoryBean.setHibernateProperties(hibernateProperties());
        localSessionFactoryBean.setAnnotatedPackages("by.pvt.pojo");
        localSessionFactoryBean.setPackagesToScan("by.pvt.pojo");
        return localSessionFactoryBean;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();

        properties.put("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.dialect", environment.getProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
        return properties;
    }

    @Bean
    @Primary
    public HibernateTransactionManager readTransactionManager(SessionFactory localSessionFactoryBean) {
        HibernateTransactionManager transactionManager
                = new HibernateTransactionManager();

        transactionManager.setSessionFactory(localSessionFactoryBean);
        return transactionManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //
    //
    // WRITE DATASOURCE
    //
    //

    @Bean
    public HibernateTransactionManager writeTransactionManager(@Qualifier("writeLocalSessionFactoryBean") SessionFactory writeLocalSessionFactoryBean) {
        HibernateTransactionManager transactionManager
                = new HibernateTransactionManager();

        transactionManager.setSessionFactory(writeLocalSessionFactoryBean);
        return transactionManager;
    }

    @Bean
    public LocalSessionFactoryBean writeLocalSessionFactoryBean(@Qualifier("writeBasicDataSource") DataSource writeBasicDataSource) {
        LocalSessionFactoryBean localSessionFactoryBean
                = new LocalSessionFactoryBean();

        localSessionFactoryBean.setDataSource(writeBasicDataSource);
        localSessionFactoryBean.setHibernateProperties(hibernateProperties());
        localSessionFactoryBean.setAnnotatedPackages("by.pvt.updater");
        localSessionFactoryBean.setPackagesToScan("by.pvt.updater");
        return localSessionFactoryBean;
    }

    @Bean
    public BasicDataSource writeBasicDataSource() {
        BasicDataSource basicDataSource = new BasicDataSource();

        basicDataSource.setUrl(environment.getProperty("connection.write.url"));
        basicDataSource.setUsername(environment.getProperty("connection.write.username"));
        basicDataSource.setPassword(environment.getProperty("connection.write.password"));
        basicDataSource.setDriverClassName(environment.getProperty("connection.write.driver_class"));
        basicDataSource.setMaxTotal(Integer.parseInt(environment.getProperty("connection.write.max_total")));

        return basicDataSource;
    }
}
