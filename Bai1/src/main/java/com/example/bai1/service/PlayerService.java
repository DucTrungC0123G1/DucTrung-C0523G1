package com.example.bai1.service;

import com.example.bai1.model.Player;
import com.example.bai1.repository.IPlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService implements IPlayerService{
    @Autowired
    private IPlayerRepository playerRepository;
    @Override
    public List<Player> findAll() {
        return playerRepository.findAllPlayer();
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
}
