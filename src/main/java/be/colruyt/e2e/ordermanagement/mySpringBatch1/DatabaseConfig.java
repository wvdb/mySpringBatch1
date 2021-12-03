package be.colruyt.e2e.ordermanagement.mySpringBatch1;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {
    @Bean(name = "ORACLE_DataSource")
    @Primary
    @ConfigurationProperties("spring-db1.datasource")
    public DataSource oracleDataSource() {
        DataSourceBuilder builder = DataSourceBuilder.create();
        builder.type(HikariDataSource.class);
        return builder.build();
    }

    @Bean(name = "MySQL_DataSource")
    @ConfigurationProperties("spring-db2.datasource")
    public DataSource mySqlDataSource() {
        DataSourceBuilder builder = DataSourceBuilder.create();
        builder.type(HikariDataSource.class);
        return builder.build();
    }

    @Bean
    PlatformTransactionManager oracleDbTxManager() {
        return new DataSourceTransactionManager(oracleDataSource());
    }

    @Bean
    PlatformTransactionManager mysqlTxManager() {
        return new DataSourceTransactionManager(mySqlDataSource());
    }

    @Bean(name = "chainTxManager")
    PlatformTransactionManager chainTxManager() {
        ChainedTransactionManager txManager =
                new ChainedTransactionManager(
                        oracleDbTxManager(),mysqlTxManager()
                );
        return txManager;
    }

}
