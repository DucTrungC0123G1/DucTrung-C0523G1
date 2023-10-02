package com.example.bai1.controller;

import com.example.bai1.dto.PlayerDto;
import com.example.bai1.model.Player;
import com.example.bai1.service.IPlayerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/players")
public class RestPlayerController {
    @Autowired
    private IPlayerService playerService;
    @GetMapping("")
    public ResponseEntity<List<Player>> getList(){
        List<Player> playerList = playerService.findAll();
        if (playerList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(playerList,HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> save (@RequestBody PlayerDto playerDto){
        if (playerDto==null){
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Player player = new Player();
        player.setStatus(true);
        player.setAction(true);
        BeanUtils.copyProperties(playerDto,player);
        playerService.save(player);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable int id){
        Player player = playerService.findId(id);
        if (player==null){
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        playerService.delete(id);
        return  new ResponseEntity<>(HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id,
                                    @RequestBody PlayerDto playerDto){
        Player player = playerService.findId(id);
        if (player==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        BeanUtils.copyProperties(playerDto,player);
            playerService.save(player);
            return new ResponseEntity<>(HttpStatus.OK);
    }

}
