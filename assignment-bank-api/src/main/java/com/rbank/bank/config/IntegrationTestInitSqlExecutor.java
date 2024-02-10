package com.rbank.bank.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Profile("test")
@Component
@RequiredArgsConstructor
public class IntegrationTestInitSqlExecutor implements ApplicationListener<ContextRefreshedEvent> {

    private final DataSource dataSource;

    @Value("classpath:init.sql")
    private Resource initSqlScript;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        executeInitSql();
    }

    private void executeInitSql() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(initSqlScript);
        populator.execute(dataSource);
    }
    @Override
    public boolean supportsAsyncExecution() {
        return ApplicationListener.super.supportsAsyncExecution();
    }
}
