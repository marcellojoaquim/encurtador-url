package com.url_shortener.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;

import java.util.Collections;
import java.util.List;

@Configuration
public class CassandraConfig extends AbstractCassandraConfiguration {
    @Override
    protected String getKeyspaceName() {
        return "encurtador_url";
    }

    @Override
    public SchemaAction getSchemaAction() {
        return SchemaAction.CREATE_IF_NOT_EXISTS;
    }

    @Override
    protected List<String> getStartupScripts() {
        String script = "CREATE KEYSPACE IF NOT EXISTS encurtador_url " +
                "WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1};";
        return Collections.singletonList(script);
    }
}
