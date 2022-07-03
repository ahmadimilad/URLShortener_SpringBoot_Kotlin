package com.mahmadi.zarebin_urlshortener.utils

import java.net.MalformedURLException
import java.net.URL

/**
 * @project Zarebin_URLShortener_SpringBoot
 * @author Milad Ahmadi on 9/6/2021 and 9:14 PM.
 */
object UrlUtil {
    /**
     * @param url any valid url
     * @return returns protocol://domain:port/ part of the input url
     * @throws MalformedURLException
     */
    @Throws(MalformedURLException::class)
    fun getBaseUrl(url: String?): String {
        val reqUrl = URL(url)
        val protocol = reqUrl.protocol
        val host = reqUrl.host
        val port = reqUrl.port
        return if (port == -1) {
            String.format("%s://%s/", protocol, host)
        } else {
            String.format("%s://%s:%d/", protocol, host, port)
        }
    }
}