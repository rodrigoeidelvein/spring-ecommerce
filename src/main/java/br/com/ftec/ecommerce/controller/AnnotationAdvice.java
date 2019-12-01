package br.com.ftec.ecommerce.controller;

import br.com.ftec.ecommerce.model.User;
import br.com.ftec.ecommerce.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Security;
import java.util.Collection;

@ControllerAdvice("br.com.ftec.ecommerce.controller")
public class AnnotationAdvice {
    @Autowired
    UserService userService;

    private static final Logger log = LoggerFactory.getLogger(AnnotationAdvice.class);

    @ModelAttribute("currentUser")
    public String getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentName = "";
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentName = authentication.getName();
        }
        log.info("CURRNET USER: " + currentName);
        return currentName;
    }

    @ModelAttribute("isAdmin")
    public Boolean isAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        boolean authorized = authorities.contains(new SimpleGrantedAuthority("ADMIN"));
        return authorized;
    }
}
