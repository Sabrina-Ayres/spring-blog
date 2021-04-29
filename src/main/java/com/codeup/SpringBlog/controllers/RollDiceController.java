package com.codeup.SpringBlog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ThreadLocalRandom;

@Controller
public class RollDiceController {

    @GetMapping("/roll-dice")
    public String diceRoller() {
        return "roll-dice";
    }

    @PostMapping("/roll-dice{n}")
    public String diceGuess(@RequestParam(name = "dice") @PathVariable String dice, Model model) {
        int getRandomNum = ThreadLocalRandom.current().nextInt(1, 6 + 1);
        model.addAttribute("dice", "You guessed " + dice + "!" +
                " The dice rolled " + getRandomNum + "!");
        return "roll-dice";
    }

}
