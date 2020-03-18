package hex.controllers;


import hex.model.developers.Developer;
import hex.model.events.Event;
import hex.model.users.User;
import hex.service.interfaces.IDeveloperService;
import hex.service.interfaces.IEventService;
import hex.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("/events")
@Controller
public class EventController {

    private final IEventService eventService;
    private final IUserService userService;
    private final IDeveloperService developerService;

    @Autowired
    public EventController(IEventService eventService, IUserService userService, IDeveloperService developerService) {
        this.userService = userService;
        this.developerService = developerService;
        this.eventService = eventService;
    }

    @GetMapping
    public String index(Model model, Authentication authentication) {
        List<Event> events;
        if (authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"))) {
            events = eventService.getAllEvents();
        } else {
            events = eventService.getAllEventsWithUser(authentication.getName());
        }
        model.addAttribute("events", events);
        model.addAttribute("page", "events/index");
        model.addAttribute("pageName", "events");
        return "application/index";
    }

    @GetMapping("new")
    public String newForm(Model model, Authentication authentication) {
        Event event = new Event();
        User user = new User();
        Developer developer = new Developer();
        List<Developer> developers = developerService.getAllUnemployedDevelopers();
        Optional<User> currentUser = userService.selectUserByEmail(authentication.getName());
        model.addAttribute("pageName", "events");
        model.addAttribute("users", currentUser.get());
        model.addAttribute("developers", developers);
        model.addAttribute("user", user);
        model.addAttribute("developer", developer);
        model.addAttribute("event", event);
        model.addAttribute("page", "events/new");
        return  "application/index";
    }

    @PostMapping
    private String create(@Validated @ModelAttribute("event") Event event,
                          @ModelAttribute("developer") Developer developer,
                          @ModelAttribute("user") User user,
                          Model model) {
        Optional<Developer> developerToSet = developerService.selectDeveloperById(developer.getDeveloperId());
        Optional<User> userToSet = userService.selectUserById(user.getUserId());
        model.addAttribute("pageName", "events");
        if (developerToSet.isPresent() && userToSet.isPresent()) {
            event.setUser(userToSet.get());
            event.setDeveloper(developerToSet.get());
            eventService.addEvent(event);
        }
        return "redirect:/events";
    }

    @PostMapping("{id}/delete")
    private String destroy(@PathVariable UUID id, Model model) {
        eventService.destroyEvent(id);
        model.addAttribute("pageName", "events");
        return "redirect:/events";
    }

    @PostMapping("{id}/end")
    public String endEvent(@PathVariable UUID id, Model model) {
        model.addAttribute("pageName", "events");
        eventService.finishEvent(id);
        return "redirect:/events";
    }
}
