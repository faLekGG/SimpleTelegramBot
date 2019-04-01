package com.goloveyko.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TelegramController {

    //@Autowired
    //ScheduleTelegramPosting scheduleTelegramPosting;

    @GetMapping("/")
    public String runApp(Model model) {
        //scheduleTelegramPosting.postTweetsToTelegram();
        model.addAttribute("appName", "Simple Telegram Bot");
        return "hello";
    }
}

