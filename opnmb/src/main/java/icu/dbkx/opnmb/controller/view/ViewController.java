package icu.dbkx.opnmb.controller.view;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@CrossOrigin
@RequestMapping("/view")
public class ViewController {
    @RequestMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login.html");
    }

    @RequestMapping("/register")
    public ModelAndView register() {
        return new ModelAndView("register.html");
    }
    @RequestMapping("/index")
    public ModelAndView index() {
        return new ModelAndView("index.html");
    }
    @RequestMapping("/category/{}")
    public ModelAndView category(@RequestParam("id") String id) {
        return new ModelAndView("category.html");
    }

    @RequestMapping("/biscuit")
    public ModelAndView biscuit() {
        return new ModelAndView("biscuit.html");
    }

    @RequestMapping("/detail")
    public ModelAndView detail(@RequestParam Integer piece_id) {
        return new ModelAndView("detail.html");
    }
}
