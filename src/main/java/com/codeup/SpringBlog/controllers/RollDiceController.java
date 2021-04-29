package com.codeup.SpringBlog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.ThreadLocalRandom;

@Controller
public class RollDiceController {

    @GetMapping("/roll-dice")
    public String diceRoller() {
        return "roll-dice";
    }

    @PostMapping("/roll-dice")
    public String diceGuess(@RequestParam(name = "dice") String dice, Model model) {
        int getRandomNum = ThreadLocalRandom.current().nextInt(1, 6 + 1);
        model.addAttribute("dice", "You guessed " + dice + "!" +
                " The dice rolled " + getRandomNum + "!");
        return "roll-dice";
    }

}
