package com.url_shortener.domain.repository;

import com.url_shortener.domain.entity.UrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlEntityRepository extends JpaRepository<UrlEntity, Long> {

    UrlEntity findByShortCode(String shortCode);
}
