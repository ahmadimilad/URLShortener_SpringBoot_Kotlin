package com.mahmadi.zarebin_urlshortener.entities

import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import javax.persistence.*

/**
 * @project Zarebin_URLShortener_SpringBoot
 * @author Milad Ahmadi on 9/6/2021 and 2:56 PM.
 */

@Document("url_doc")
data class UrlEntity(
    @Id
    val id : Long?,

    @Column(name = "created", nullable = false)
    val created:LocalDateTime = LocalDateTime.now(),

    @Column(name = "original_url", nullable = false)
    val originalUrl : String = "default",

    @Column(name = "clicks", nullable = false)
    var clicks : Int?,

    @Column(name = "short_url", nullable = false)
    var shortUrl: String = "shortDefault"
){
    constructor() : this(Long.MIN_VALUE, LocalDateTime.now(), "", 0,"")
    constructor(id: Long ,originalUrl: String,shortUrl: String ) : this(id, LocalDateTime.now(), originalUrl, 0,shortUrl)
}
