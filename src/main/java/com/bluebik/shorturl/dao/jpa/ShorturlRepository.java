package com.bluebik.shorturl.dao.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bluebik.shorturl.domain.ShortUrl;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ShorturlRepository extends JpaRepository<ShortUrl, String> {
    
    @Modifying
    @Query(value = "UPDATE short_url SET static_click = static_click + 1 WHERE id = :gid", nativeQuery = true)
    void updateClick(@Param("gid") String id);

}
