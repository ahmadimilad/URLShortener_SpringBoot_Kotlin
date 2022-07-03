package com.mahmadi.zarebin_urlshortener.entities

import org.springframework.data.mongodb.core.mapping.Document
import javax.persistence.Id

/**
 * @project Zarebin_URLShortener_SpringBoot
 * @author Milad Ahmadi on 9/6/2021 and 10:01 PM.
 */

@Document(collection = "customSequences")
data class CustomSequences(
    @Id
    var id: String,
    var seq: Long
)
