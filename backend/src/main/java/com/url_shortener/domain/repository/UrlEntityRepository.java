package com.url_shortener.domain.repository;

import com.url_shortener.domain.entity.UrlEntity;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableCassandraRepositories
public interface UrlEntityRepository extends CassandraRepository<UrlEntity, String> {
    boolean existsByOriginalUrl(String url);

}
