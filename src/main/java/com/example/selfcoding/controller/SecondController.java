package com.example.selfcoding.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecondController {
        @GetMapping("/random-quote")
        public String randomQuote(Model model) {

            String[] quotes = {
                    "1번 명언",
                    "2번 명언",
                    "3번 명언",
                    "4번 명언",
                    "5번 명언"
            };

            int randInt = (int) (Math.random() * quotes.length);
            model.addAttribute("randomQuote", quotes[randInt]);
            return "quote";
        }


}
