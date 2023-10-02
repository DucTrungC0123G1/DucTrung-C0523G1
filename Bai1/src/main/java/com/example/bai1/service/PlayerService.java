package com.example.bai1.service;

import com.example.bai1.dto.IPlayerDto;
import com.example.bai1.dto.PlayerDto;
import com.example.bai1.model.Player;
import com.example.bai1.model.Team;
import com.example.bai1.repository.IPlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService implements IPlayerService{
    @Autowired
    private IPlayerRepository playerRepository;
//    @Override
//    public List<Player> findAll() {
//        return playerRepository.findAllPlayer();
//    }

    @Override
    public List<Player> findAll() {
        return playerRepository.findAll();
    }

    @Override
    public Player findId(int id) {
        return playerRepository.findById(id).orElse(null);
    }

    @Override
    public void remove(Player player) {
        playerRepository.deletePlayer(player);
    }

    @Override
    public void save(Player player) {
        playerRepository.save(player);
    }

    @Override
    public void saveEdit(int id, Player player) {
        playerRepository.save(player);
    }

    @Override
    public Page<IPlayerDto> searchByName(Pageable pageable, String nameSearch, String dayStart, String dayEnd, String teamSearch) {
        return playerRepository.findPlayerByNameContaining(pageable,"%"+ nameSearch +"%",dayStart,dayEnd,"%"+teamSearch+"%");
    }

    @Override
    public void active(Player player) {
        playerRepository.playerActive(player);
    }

    @Override
    public void reserve(Player player) {
        playerRepository.playerReserve(player);
    }

    @Override
    public List<Player> checkLimitPlayer(int id) {
        return playerRepository.checkLimit(id);
    }

    @Override
    public void delete(int id) {
    playerRepository.deleteById(id);
    }

//    @Override
//    public Page<Player> findAll(Pageable pageable) {
//        return playerRepository.findAll(pageable);
//    }


}
