package com.url_shortener.domain.repository;

import com.url_shortener.domain.entity.UrlEntity;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@EnableCassandraRepositories
public interface UrlEntityRepository extends CassandraRepository<UrlEntity, UUID> {

    Optional<UrlEntity> findByShortCode(String shortCode);
    boolean existsByShortCode(String shortCode);
    boolean existsByOriginalUrl(String url);

}
