package com.login.loginPage.controller;
import org.springframework.ui.Model;

import com.login.loginPage.service.Login;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
public class LoginController {
    private Login newLogin;
    private RedirectAttributes m;

    @RequestMapping("/login")
    public String index() {
        return "index";
    }

    @RequestMapping("/welcome")
    public ModelAndView toWelcomePage(Model m) {
        if (this.newLogin != null&&this.m!=null) {
            m.addAttribute("username", this.newLogin.getUsername());
            m.addAttribute("password", this.newLogin.getPassword());
            return new ModelAndView("/welcome");
        }
        return new ModelAndView("redirect:/login");
    }

    @PostMapping(value = "/login")
    public ModelAndView login(@ModelAttribute(name = "loginForm") Login login,RedirectAttributes m) {
        try {
            String userN = login.getUsername();
            String password = login.getPassword();
            if (userN.equals("mohammad") && password.equals("123456")) {
                this.newLogin = login;
                m.addFlashAttribute("username", login.getUsername());
                m.addFlashAttribute("password", login.getPassword());
                this.m=m;
                return new ModelAndView("redirect:/welcome");
            }
            this.newLogin = null;
            return new ModelAndView("index");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ModelAndView("index");
        }
    }
}
