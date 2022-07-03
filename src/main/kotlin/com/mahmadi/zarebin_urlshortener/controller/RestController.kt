package com.mahmadi.zarebin_urlshortener.controller

import com.mahmadi.zarebin_urlshortener.entities.UrlEntity
import com.mahmadi.zarebin_urlshortener.repository.UrlRepository
import org.hashids.Hashids
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.ModelAndView
import java.util.*


/**
 * @project Zarebin_URLShortener_SpringBoot
 * @author Milad Ahmadi on 9/6/2021 and 10:14 PM.
 */

@RestController
@RequestMapping("/tu")
class RestController {

    var logger: Logger = LoggerFactory.getLogger(javaClass.name)

    @Autowired
    lateinit var urlRepository: UrlRepository

    @Autowired
    private val mongo: MongoOperations? = null

    @RequestMapping("/{shortenString}")
    fun redirectToFullUrl(@PathVariable shortenString: String): ModelAndView {

        val startTime = System.currentTimeMillis()
        val hashids = Hashids("zarebin", 7)
        val orginalId = hashids.decode(shortenString)
        var urlEntity: UrlEntity? = null
        val mongoTemplate: MongoTemplate? = null

        val optionalUrlEntity: Optional<UrlEntity> = urlRepository.findById(orginalId[0])
        if (optionalUrlEntity.isPresent) {
            urlEntity = optionalUrlEntity.get()
            urlEntity.clicks = urlEntity.clicks?.plus(1)
            mongo!!.save(urlEntity)
        }

        val endTime = System.currentTimeMillis()

        logger.info("Response time: ${endTime - startTime}")

        return ModelAndView("redirect:${urlEntity!!.originalUrl}")
    }


}