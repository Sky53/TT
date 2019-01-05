package ru.sky.sweater.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.sky.sweater.domain.Message;
import ru.sky.sweater.domain.User;
import ru.sky.sweater.repos.MesseageRepo;

import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private MesseageRepo messeageRepo;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        Iterable<Message> messages = messeageRepo.findAll();
        model.put("messages", messages);
        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag, Map<String, Object> model) {
        Message message = new Message(text, tag, user);
        messeageRepo.save(message);
        Iterable<Message> messages = messeageRepo.findAll();
        model.put("messages", messages);
        return "main";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        Iterable<Message> messages;
        if (filter != null && !filter.isEmpty()) {
            messages = messeageRepo.findByTag(filter);
        } else {
            messages = messeageRepo.findAll();
        }
        model.put("messages", messages);
        return "main";
    }
}