package yps.systems.ai.config;

import org.neo4j.driver.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.AbstractNeo4jConfig;
import org.springframework.data.neo4j.core.transaction.ReactiveNeo4jTransactionManager;
import org.springframework.transaction.ReactiveTransactionManager;

@Configuration
public class Neo4jConfig extends AbstractNeo4jConfig {

    @Autowired
    private Driver driver;

    @Override
    public Driver driver() {
        return driver;
    }

    @Bean
    public ReactiveTransactionManager reactiveTransactionManager() {
        return new ReactiveNeo4jTransactionManager(driver());
    }

}
