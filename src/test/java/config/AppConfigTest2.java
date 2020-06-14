package config;

import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@Configuration
@ComponentScan(basePackages = {
        "cinema.dao.impl",
        "cinema.service.impl",
        "cinema.util",
        "cinema.model"})
@PropertySource("classpath:db.properties")
public class AppConfigTest2 {

    private static final String URL = "jdbc:mysql://localhost:3306/cinema_test?serverTimezone=UTC";
    private static final String HBM2DDL_AUTO = "create-drop";
    private Environment env;

    public AppConfigTest2(Environment env) {
        this.env = env;
    }

    @Bean
    public DataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(env.getProperty("db.driver"));
        dataSource.setUrl(URL);
        dataSource.setUsername(env.getProperty("db.username"));
        dataSource.setPassword(env.getProperty("db.password"));
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(getDataSource());
        Properties prop = new Properties();
        prop.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        prop.put("hibernate.hbm2ddl.auto", HBM2DDL_AUTO);
        localSessionFactoryBean.setHibernateProperties(prop);
        localSessionFactoryBean.setPackagesToScan("cinema.model");
        return localSessionFactoryBean;
    }
}
