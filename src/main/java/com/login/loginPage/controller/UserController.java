package com.login.loginPage.controller;


import com.login.loginPage.service.User;
import com.login.loginPage.service.UserRepository;
import com.login.loginPage.module.UserModule;
import org.springframework.ui.Model;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {
    private UserModule user;
    private UserRepository<UserModule> UserRepository = new User();

    @RequestMapping("/login")
    public String index() {
        return "index";
    }

    @RequestMapping("/createA")
    public String createAccount() {
        return "signin";
    }

    @RequestMapping("/welcome")
    public ModelAndView toWelcomePage(Model m) {
        if (this.user != null) {
            m.addAttribute("id", UserRepository.getUser().getId());
            m.addAttribute("username", UserRepository.getUser().getUsername());
            m.addAttribute("password", UserRepository.getUser().getPassword());
            m.addAttribute("userType", UserRepository.getUser().getUserType());
            return new ModelAndView("/welcome");
        }
        return new ModelAndView("redirect:/login");
    }

    @PostMapping(value = "/login")
    public ModelAndView login(@ModelAttribute(name = "loginForm") UserModule login, RedirectAttributes m) {
        try {
            String userN = login.getUsername();
            String password = login.getPassword();
            if (UserRepository.isUser(login)) {
                this.user = login;
                m.addFlashAttribute("id", UserRepository.getUser().getId());
                m.addFlashAttribute("username", UserRepository.getUser().getUsername());
                m.addFlashAttribute("password", UserRepository.getUser().getPassword());
                m.addFlashAttribute("userType", UserRepository.getUser().getUserType());
                return new ModelAndView("redirect:/welcome");
            }
            this.user = null;
            return new ModelAndView("index");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ModelAndView("index");
        }
    }

    @PostMapping(value = "/createA")
    public ModelAndView createA(@ModelAttribute(name = "RegisterForm") UserModule newUser, RedirectAttributes m) {
        try {

            if (!UserRepository.isUser(newUser)) {
                newUser.setId(newUser.getId()+1);
                newUser.setUserType("user");
                UserRepository.addNewUser(newUser);
                return new ModelAndView("redirect:/login");
            } else {
                m.addFlashAttribute("error", "i found this account");
                return new ModelAndView("redirect:/createA");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ModelAndView("redirect:/login");
        }
    }
}
