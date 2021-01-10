package com.bluebik.shorturl.dao.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bluebik.shorturl.domain.ShortUrl;

@Repository
public interface ShorturlRepository extends JpaRepository<ShortUrl, String> {
    
}
