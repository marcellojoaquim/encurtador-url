package com.url_shortener.domain.repository;

import com.url_shortener.domain.entity.UrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UrlEntityRepository extends JpaRepository<UrlEntity, UUID> {

    Optional<UrlEntity> findByShortCode(String shortCode);
    boolean existsByShortCode(String shortCode);
    boolean existsByOriginalUrl(String url);

}
