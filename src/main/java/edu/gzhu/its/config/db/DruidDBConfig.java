package edu.gzhu.its.config.db;

import javax.sql.DataSource;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * DruidDBConfig类被@Configuration标注，用作配置信息； 
 * DataSource对象被@Bean声明，为Spring容器所管理， 
 * @Primary表示这里定义的DataSource将覆盖其他来源的DataSource。
 * jdbc.url=${jdbc.url} 
 * 最新的支持方式如下: 
 * jdbc.url=@jdbc.url@  
 */
@Configuration
@EnableTransactionManagement
public class DruidDBConfig {
//  private Logger logger = LoggerFactory.getLogger(DruidDBConfig.class);

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driverClassName}")
    private String driverClassName;

    @Value("${spring.datasource.initialSize}")
    private int initialSize;

    @Value("${spring.datasource.minIdle}")
    private int minIdle;

    @Value("${spring.datasource.maxActive}")
    private int maxActive;

    @Value("${spring.datasource.maxWait}")
    private int maxWait;

    @Value("${spring.datasource.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;

    @Value("${spring.datasource.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;

    @Value("${spring.datasource.validationQuery}")
    private String validationQuery;

    @Value("${spring.datasource.testWhileIdle}")
    private boolean testWhileIdle;

    @Value("${spring.datasource.testOnBorrow}")
    private boolean testOnBorrow;

    @Value("${spring.datasource.testOnReturn}")
    private boolean testOnReturn;

    @Value("${spring.datasource.poolPreparedStatements}")
    private boolean poolPreparedStatements;

    @Value("${spring.datasource.maxPoolPreparedStatementPerConnectionSize}")
    private int maxPoolPreparedStatementPerConnectionSize;

    @Value("${spring.datasource.filters}")
    private String filters;

    @Value("{spring.datasource.connectionProperties}")
    private String connectionProperties;
    
    @Value("{spring.jpa.properties.hibernate.dialect}")
    private String dialect;
    
    @Value("{spring.jpa.show-sql}")
    private String show_sql;
    
    @Bean
    @ConfigurationProperties(prefix ="spring.datasource")
    public DataSource dataSource(){
    return BeanUtils.instantiate(DruidDataSource.class);
    }

}
