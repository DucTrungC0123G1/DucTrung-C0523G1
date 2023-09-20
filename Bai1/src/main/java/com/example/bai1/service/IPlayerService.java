package com.example.bai1.service;

import com.example.bai1.dto.PlayerDto;
import com.example.bai1.model.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPlayerService {
//    List<Player> findAll();

    Player findId(int id);

    void remove(Player player);

    void save(Player player);

    void saveEdit(int id, Player player);

    Page<Player> searchByName(Pageable pageable, String nameSearch, String dayStart, String dayEnd);
}
