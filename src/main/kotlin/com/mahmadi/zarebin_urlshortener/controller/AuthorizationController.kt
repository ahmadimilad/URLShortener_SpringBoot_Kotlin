package com.mahmadi.zarebin_urlshortener.controller

import com.mahmadi.zarebin_urlshortener.entities.UrlEntity
import com.mahmadi.zarebin_urlshortener.entities.UserEntity
import com.mahmadi.zarebin_urlshortener.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

/**
 * @project Zarebin_URLShortener_SpringBoot
 * @author Milad Ahmadi on 9/6/2021 and 3:36 PM.
 */

@Controller
class AuthorizationController {

    @Autowired
    private val userRepo: UserRepository? = null

    @RequestMapping(value = ["", "/"])
    fun viewHomePage(): String? {
        return "index"
    }

    @GetMapping("/register")
    fun showRegistrationForm(model: Model): String? {
        model.addAttribute("user", UserEntity())
        return "signup_form"
    }

    @PostMapping("/process_register")
    fun processRegister(user: UserEntity): String? {
        print("process_register $user")
        val passwordEncoder = BCryptPasswordEncoder()
        val encodedPassword = passwordEncoder.encode(user.password)
        user.password = encodedPassword
        userRepo?.save(user)
        return "register_success"
    }

    @GetMapping("/users")
    fun listUsers(model: Model): String? {
        val listUsers: List<UserEntity> = userRepo!!.findAll()
        model.addAttribute("listUsers", listUsers)
        model.addAttribute("createUrl", UrlEntity())
        return "users"
    }


//    @GetMapping( "/create_short")
//    fun createShortUrl() :String {
//        return "create_short_url"
//    }
}