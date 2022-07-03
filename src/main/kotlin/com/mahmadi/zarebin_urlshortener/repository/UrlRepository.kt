package com.mahmadi.zarebin_urlshortener.repository

import com.mahmadi.zarebin_urlshortener.entities.UrlEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

/**
 * @project Zarebin_URLShortener_SpringBoot
 * @author Milad Ahmadi on 9/6/2021 and 3:20 PM.
 */
@Repository
interface UrlRepository : MongoRepository<UrlEntity, Long> {
}