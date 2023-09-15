package com.example.bai1.controller;

import com.example.bai1.model.Player;
import com.example.bai1.model.Position;
import com.example.bai1.service.IPlayerService;
import com.example.bai1.service.IPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/player")
public class PlayerController {
    @Autowired
    private IPlayerService playerService;
    @Autowired
    private IPositionService positionService;

    @GetMapping("")
    public String showList(Model model){
        List<Player> playerList = playerService.findAll();
        model.addAttribute("playerList",playerList);
        return "/list";
    }
    @GetMapping("/delete")
    public String deletePlayer(@RequestParam int id, RedirectAttributes redirectAttributes){
        Player player = playerService.findId(id);
        playerService.remove(player);
        redirectAttributes.addFlashAttribute("msg","Delete Success");
        return "redirect:/player";
    }
    @GetMapping("/detail/{id}")
    public String showDetail(Model model, @PathVariable int id){
       Player player= playerService.findId(id);
        model.addAttribute("player",player);
        return "/detail";
    }
    @GetMapping("/create")
    public String showCreate(Model model){
        List<Position> positionList = positionService.findAll();
        model.addAttribute("positionList",positionList);
        model.addAttribute("player",new Player());
        return "/create";
    }
    @PostMapping("/save")
    public String save(@ModelAttribute Player player,RedirectAttributes redirectAttributes, Model model){
        player.setStatus(true);
    playerService.save(player);
    redirectAttributes.addFlashAttribute("msg","Add New Player Success");
    model.addAttribute("player",player);
    return "redirect:/player";
    }
    @GetMapping("/update/{id}")
    public String showEdit(@PathVariable int id, Model model){
        Player player =playerService.findId(id);
        List<Position> positionList = positionService.findAll();
        model.addAttribute("positionList",positionList);
        model.addAttribute("player",player);
        return "/edit";
    }
    @PostMapping("/edit")
    public String editPlayer(@ModelAttribute Player player, RedirectAttributes redirectAttributes){
        player.setStatus(true);
        playerService.saveEdit(player.getId(),player);
        redirectAttributes.addFlashAttribute("msg","Edit Player Success");
        return "redirect:/player";
    }


}
