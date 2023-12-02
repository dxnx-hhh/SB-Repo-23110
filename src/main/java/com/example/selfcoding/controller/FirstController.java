package com.example.selfcoding.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {
        @GetMapping("/hi")
        public String niceToMeetYou(Model model) {
            model.addAttribute("username", "동동");
            return "greetings";
        }

        @GetMapping("bye")
        public String seYouNext(Model model) {
            model.addAttribute("username", "동동");
            return "goodbye";
        }
}
