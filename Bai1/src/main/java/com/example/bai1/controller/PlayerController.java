package com.example.bai1.controller;

import com.example.bai1.dto.PlayerDto;
import com.example.bai1.model.Player;
import com.example.bai1.model.Position;
import com.example.bai1.model.Team;
import com.example.bai1.service.IPlayerService;
import com.example.bai1.service.IPositionService;
import com.example.bai1.service.ITeamService;
import com.sun.javafx.collections.ImmutableObservableList;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/player")
public class PlayerController {
    @Autowired
    private IPlayerService playerService;
    @Autowired
    private IPositionService positionService;
    @Autowired
    private ITeamService teamService;

    //    @GetMapping("")
//    public String showList(Model model){
//        List<Player> playerList = playerService.findAll();
//        model.addAttribute("playerList",playerList);
//        return "/list";
//    }
    @ModelAttribute("teamList")
    public List<Team> teamList(){
       return teamService.findAll();
    }

    @GetMapping("")
    public String showList(@RequestParam(defaultValue = "0", required = false) int page,
                           @RequestParam(defaultValue = "") String nameSearch,
                           @RequestParam(defaultValue = "2", required = false) int size,
                           @RequestParam(defaultValue = "1990-01-01", required = false) String dayStart,
                           @RequestParam(defaultValue = "", required = false) String dayEnd,
                           @RequestParam(defaultValue = "",required = false) String teamSearch,
                           @ModelAttribute Player player,
                           Model model) {
        if (Objects.equals(dayEnd, "")) {
            dayEnd = String.valueOf(LocalDate.now());
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        Page<Player> playerPage = playerService.searchByName(pageable, nameSearch, dayStart, dayEnd, teamSearch);
        model.addAttribute("playerPage", playerPage);
        model.addAttribute("nameSearch", nameSearch);
        model.addAttribute("size", size);
        model.addAttribute("dayStart", dayStart);
        model.addAttribute("dayEnd", dayEnd);
        model.addAttribute("player", player);
        model.addAttribute("teamSearch",teamSearch);
        return "list";
    }

    @GetMapping("/delete")
    public String deletePlayer(@RequestParam int id, RedirectAttributes redirectAttributes) {
        Player player = playerService.findId(id);
        playerService.remove(player);
        redirectAttributes.addFlashAttribute("msg", "Delete Success");
        return "redirect:/player";
    }

    @GetMapping("/detail/{id}")
    public String showDetail(Model model, @PathVariable int id) {
        Player player = playerService.findId(id);
        model.addAttribute("player", player);
        return "/detail";
    }

    //    @GetMapping("/create")
//    public String showCreate(Model model) {
//        List<Position> positionList = positionService.findAll();
//        List<Team> teamList = teamService.findAll();
//        model.addAttribute("positionList", positionList);
//        model.addAttribute("player", new Player());
//        model.addAttribute("teamList", teamList);
//        return "/create";
//    }
    @GetMapping("/create")
    public String showCreate(Model model) {
        List<Position> positionList = positionService.findAll();
        List<Team> teamList = teamService.findAll();
        model.addAttribute("positionList", positionList);
        model.addAttribute("teamList", teamList);
        model.addAttribute("playerDto", new PlayerDto());
        return "/create";
    }

//        @PostMapping("/save")
//    public String save(@ModelAttribute Player player, RedirectAttributes redirectAttributes, Model model) {
//        player.setStatus(true);
//        playerService.save(player);
//        redirectAttributes.addFlashAttribute("msg", "Add New Player Success");
//        model.addAttribute("player", player);
//        return "redirect:/player";
//    }
    @PostMapping("/save")
    public String save(@Valid @ModelAttribute PlayerDto playerDto,
                       RedirectAttributes redirectAttributes,
                       BindingResult bindingResult,
                       Model model) {
        List<Position> positionList = positionService.findAll();
        List<Team> teamList = teamService.findAll();
        new PlayerDto().validate(playerDto, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("positionList", positionList);
            model.addAttribute("teamList", teamList);
            return "create";
        }
        Player player = new Player();
        BeanUtils.copyProperties(playerDto,player);
        player.setStatus(true);
        playerService.save(player);
        redirectAttributes.addFlashAttribute("msg","Add New Player");
        return "redirect:/player";
    }


    @GetMapping("/update/{id}")
    public String showEdit(@PathVariable int id, Model model) {
        Player player = playerService.findId(id);
        List<Position> positionList = positionService.findAll();
        List<Team> teamList = teamService.findAll();
        model.addAttribute("teamList", teamList);
        model.addAttribute("positionList", positionList);
        model.addAttribute("player", player);
        return "/edit";
    }

    @PostMapping("/edit")
    public String editPlayer(@ModelAttribute Player player, RedirectAttributes redirectAttributes) {
        player.setStatus(true);
        playerService.saveEdit(player.getId(), player);
        redirectAttributes.addFlashAttribute("msg", "Edit Player Success");
        return "redirect:/player";
    }


}
