package com.example.entityhistory.user.controller;

import com.example.entityhistory.user.controller.form.AddUserForm;
import com.example.entityhistory.user.controller.form.ModifyUserForm;
import com.example.entityhistory.user.controller.response.UserResponse;
import com.example.entityhistory.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/users")
@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;

    @GetMapping("/new")
    public String addUserForm(@ModelAttribute("user") AddUserForm addUserForm) {
        return "/user/add-user-form";
    }

    @PostMapping("/new")
    public String addUser(@ModelAttribute("user") AddUserForm addUserForm) {
        userService.save(addUserForm);
        return "redirect:/";
    }

    @GetMapping
    public String users(Model model) {
        List<UserResponse> users = userService.findAllWithGroups().stream()
                .map(UserResponse::from)
                .toList();

        model.addAttribute("users", users);
        return "/user/users";
    }

    @GetMapping("/{username}/modify")
    public String modifyUserForm(@PathVariable("username") String username, Model model) {
        UserResponse user = UserResponse.from(userService.findById(username));
        model.addAttribute("user", user);
        return "/user/modify-user-form";
    }

    @PostMapping("/{username}/modify")
    public String modifyUser(@PathVariable("username") String username, @ModelAttribute("user") ModifyUserForm form) {
        userService.modify(username, form);
        return "redirect:/users";
    }
}
