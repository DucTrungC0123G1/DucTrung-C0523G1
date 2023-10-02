package com.example.bai1.service;

import com.example.bai1.dto.IPlayerDto;
import com.example.bai1.dto.PlayerDto;
import com.example.bai1.model.Player;
import com.example.bai1.model.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPlayerService {
    

    Player findId(int id);

    void remove(Player player);

    void save(Player player);

    void saveEdit(int id, Player player);

    Page<IPlayerDto> searchByName(Pageable pageable, String nameSearch, String dayStart, String dayEnd, String teamSearch);

    void active(Player player);

    void reserve(Player player);


    List<Player> checkLimitPlayer(int id);


    void delete(int id);


    List<Player> findAll();
}
