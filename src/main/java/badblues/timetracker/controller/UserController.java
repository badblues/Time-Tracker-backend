package badblues.timetracker.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import badblues.timetracker.model.User;
import badblues.timetracker.service.UserService;


@RestController
@RequestMapping("timetracker")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> hello() {
        return userService.getUsers();
    }

}