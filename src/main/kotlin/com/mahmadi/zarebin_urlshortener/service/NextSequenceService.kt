package com.mahmadi.zarebin_urlshortener.service

import com.mahmadi.zarebin_urlshortener.entities.CustomSequences
import org.springframework.data.mongodb.core.FindAndModifyOptions.options
import org.springframework.data.mongodb.core.query.Criteria.where
import org.springframework.data.mongodb.core.query.Query.query
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.query.Update
import org.springframework.stereotype.Service



/**
 * @project Zarebin_URLShortener_SpringBoot
 * @author Milad Ahmadi on 9/6/2021 and 10:03 PM.
 */
@Service
class NextSequenceService {

    @Autowired
    private val mongo: MongoOperations? = null

    fun getNextSequence(seqName: String?): Long {
        val counter = mongo!!.findAndModify(
            query(where("_id").`is`(seqName)),
            Update().inc("seq", 1),
            options().returnNew(true).upsert(true),
            CustomSequences::class.java
        )
        return counter!!.seq
    }
}