package com.artisaniron.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
public class AdminLoginController {

    @GetMapping("/admin/login")
    public String loginPage(@RequestParam(required = false) String error,
                             @RequestParam(required = false) String logout,
                             Model model) {
        model.addAttribute("error", error != null);
        model.addAttribute("logoutSuccess", logout != null);
        return "admin/login";
    }
}
