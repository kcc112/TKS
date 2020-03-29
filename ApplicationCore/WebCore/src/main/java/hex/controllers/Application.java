package hex.controllers;

import hex.model.users.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@Controller
public class Application {

    @GetMapping("/")
    public String root() {
        return "redirect:/login";
    }

    @GetMapping("login")
    public String login() {
        return "login-hex";
    }

    @GetMapping("register")
    public String register(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "application/register";
    }

    @GetMapping("home")
    public String home(Model model) {
        model.addAttribute("pageName", "home");
        model.addAttribute("page", "application/home");
        return "application/index";
    }
}
