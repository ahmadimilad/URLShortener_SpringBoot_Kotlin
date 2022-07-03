package com.mahmadi.zarebin_urlshortener.service

import com.mahmadi.zarebin_urlshortener.entities.UrlEntity
import com.mahmadi.zarebin_urlshortener.repository.UrlRepository
import org.apache.commons.validator.routines.UrlValidator
import org.hashids.Hashids
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @project Zarebin_URLShortener_SpringBoot
 * @author Milad Ahmadi on 9/6/2021 and 6:37 PM.
 */

@Service
class URLShortenerService {
    var logger: Logger = LoggerFactory.getLogger(javaClass.name)

    @Autowired
    lateinit var nextSequenceService: NextSequenceService

    @Autowired
    lateinit var urlRepository: UrlRepository


    fun createShortUrlFromOriginal(urlEntity: UrlEntity): String {

        val validator = UrlValidator(arrayOf("http", "https"))
        val url: String = urlEntity.originalUrl
        if (!validator.isValid(url)) {
            throw IllegalArgumentException("Malformed Url provided")
        } else {
            // Define salt and minHashLength
            val hashids = Hashids("zarebin", 7)
            // Get next generated ID
            val urlId = nextSequenceService.getNextSequence("customSequences")
            // Encode ID with Hashids
            val hashedId: String = hashids.encode(urlId)
            // Save Original URL and ID in MongoDB (created=LocalDateTime.now() and clicks first init by 0)
            urlRepository.save(UrlEntity(urlId, urlEntity.originalUrl, "http://localhost:7080/tu/$hashedId"))
            // Return Short URL to user
            return "http://localhost:7080/tu/$hashedId"
        }
    }


    fun getOriginalUrl(shortenString: String): UrlEntity {
        val hashids = Hashids("zarebin", 7)
        // Decode originalId with Hashids
        val originalId = hashids.decode(shortenString)
        // Create empty urlEntity
        var urlEntity: UrlEntity? = null
        // Check if the record is Present get that
        if (urlRepository.findById(originalId[0]).isPresent) {
            urlEntity = urlRepository.findById(originalId[0]).get()
        }
        // Return urlEntity to URLShortenerController
        return urlEntity!!
    }

    fun getUrlLists() : List<UrlEntity>{
        return urlRepository.findAll()
    }
}