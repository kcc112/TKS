package hex.controllers;

import hex.configuration.captcha.Captcha;
import hex.model.users.User;
import hex.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("/users")
@Controller
public class UserController {

    private final IUserService userService;
    private final RestTemplate restTemplate;

    @Autowired
    public UserController(IUserService userService, RestTemplate restTemplate) {
        this.userService = userService;
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public String index(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("page", "users/index");
        model.addAttribute("pageName", "users");
        return "application/index";
    }

    @GetMapping("new")
    public String _new(Model model) {
        User user = new User();
        model.addAttribute("pageName", "users");
        model.addAttribute("user", user);
        model.addAttribute("page", "users/new");
        return "application/index";
    }

    @PostMapping
    private String create(@Validated @ModelAttribute("user") User user, BindingResult bindingResult, Model model, Principal principal,
                          @RequestParam(name = "g-recaptcha-response") String captcha) {

        String url = "https://www.google.com/recaptcha/api/siteverify";
        String params = "?secret=6Lf7pMoUAAAAAEilK6w2URiI6ot6bRKJikOdrzHm&response=" + captcha;

        Captcha captchaResponse = restTemplate.exchange(url+params, HttpMethod.POST,null, Captcha.class).getBody();

        if (captchaResponse != null && captchaResponse.isSuccess()) {
            if (principal != null) {
                model.addAttribute("pageName", "users");
                if (bindingResult.hasErrors()) {
                    model.addAttribute("page", "users/new");
                    return "application/index";
                }
                return "redirect:/users";
            } else {
                if (bindingResult.hasErrors()) {
                    return "application/register";
                }
                user.setUserType("CLIENT");
                user.setActive(false);
                userService.addUser(user);
                return "redirect:/login";
            }
        } else if (principal != null) {
            return "redirect:/users/new";
        } else {
            return "redirect:/register";
        }
    }

    @PostMapping("{id}/delete")
    private String destroy(@PathVariable UUID id, Model model) {
        model.addAttribute("pageName", "users");
        userService.destroyUser(id);
        return "redirect:/users";
    }

    @PostMapping("{id}")
    private String update(@PathVariable UUID id,
                          @Validated @ModelAttribute("user") User user,
                          BindingResult bindingResult,
                          Model model) {
        model.addAttribute("pageName", "users");
        user.setUserId(id);
        if (bindingResult.hasFieldErrors("userName") || bindingResult.hasFieldErrors("userSurname")) {
            model.addAttribute("user", user);
            model.addAttribute("page", "users/edit");
            return "application/index";
        }
        userService.updateUser(user);
        return "redirect:/users";
    }

    @GetMapping("{id}/edit")
    public String edit(@PathVariable UUID id, Model model) {
        Optional<User> user = userService.selectUserById(id);
        if (user.isPresent()) {
            model.addAttribute("pageName", "users");
            model.addAttribute("user", user.get());
            model.addAttribute("page", "users/edit");
            return "application/index";
        } else {
            return "redirect:/users";
        }
    }

    //Custom controllers region
    @GetMapping("{id}/activate-or-deactivate")
    public String activateOrDeactivate(@PathVariable UUID id, Model model) {
        model.addAttribute("pageName", "users");
        userService.activateOrDeactivateUser(id);
        return "redirect:/users";
    }

    @GetMapping("{id}/info")
    public String info(@PathVariable UUID id, Model model) {
        Optional<User> user =  userService.selectUserById(id);
        user.ifPresent(value -> model.addAttribute("user", value));
        model.addAttribute("page", "users/info");
        model.addAttribute("pageName", "users");
        return "application/index";
    }

    @GetMapping("search")
    public String search(@RequestParam(value="name", required=false) String name, Model model) {
        List<User> users;
        if (name.isBlank()) {
            users = userService.getAllUsers();
        } else {
            users = userService.getUsersByName(name);
        }
        model.addAttribute("users", users);
        model.addAttribute("page", "users/index");
        model.addAttribute("pageName", "users");
        return "application/index";
    }
}
