package com.mahmadi.zarebin_urlshortener.controller

import com.mahmadi.zarebin_urlshortener.entities.UrlEntity
import com.mahmadi.zarebin_urlshortener.service.URLShortenerService
import org.hashids.Hashids
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

/**
 * @project Zarebin_URLShortener_SpringBoot
 * @author Milad Ahmadi on 9/6/2021 and 4:52 PM.
 */

@Controller
@RequestMapping(value = ["/url"])
class URLShortenerController {

    @Autowired
    lateinit var urlShortenerService: URLShortenerService


    @GetMapping("/create_short_url")
    fun createShortUrl(mode: Model):String {
        mode.addAttribute("url", UrlEntity())
        return "create_short_url_page"
    }


    @RequestMapping("/created_url")
    fun processCreateShortUrl(urlEntity: UrlEntity,  model : Model) : String {
        model.addAttribute("shortenString", urlShortenerService.createShortUrlFromOriginal(urlEntity))
        return "created_urls_page"
    }

    @RequestMapping("/list")
    fun urlList (urlEntity: UrlEntity,  model : Model) : String {
        model.addAttribute("urlLists", urlShortenerService.getUrlLists())
        return "url_state_page"
    }

}