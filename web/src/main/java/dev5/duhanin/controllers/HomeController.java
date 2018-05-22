package dev5.duhanin.controllers;


import dev5.duhanin.dto.NewsDTO;
import dev5.duhanin.dto.UserDTO;
import dev5.duhanin.exceptions.NotFoundException;
import dev5.duhanin.interfaces.NewsService;
import dev5.duhanin.interfaces.UserService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@Api(value = "accountControllerAPI")
public class HomeController {
    @Autowired
    private UserService userService;
    private static final Logger LOG = LoggerFactory.getLogger(NewsController.class);

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index() {
        LOG.debug("start output news");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        return mv;
    }

    @RequestMapping(value = "/home", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean hasRoleAdmin = auth.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_Administrator"));
        if (hasRoleAdmin) {
            mv.setViewName("admin");
        } else {
            mv.setViewName("home");
        }

        return mv;
    }

    @RequestMapping(value = "/register", method = {RequestMethod.GET})
    public ModelAndView register() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("register");
        return mv;
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    @Validated
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        LOG.debug("start creating  user");
        return userService.createUser(userDTO);
    }


}