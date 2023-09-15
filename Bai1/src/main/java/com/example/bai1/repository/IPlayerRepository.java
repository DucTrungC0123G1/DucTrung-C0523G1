package com.example.bai1.repository;

import com.example.bai1.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IPlayerRepository extends JpaRepository<Player,Integer> {
    @Query(value = " select * from player where player.status = true",nativeQuery = true)
    List<Player> findAllPlayer();


    @Transactional
    @Modifying
    @Query(value = "update player set player.status = 0 where player.id = :id",nativeQuery = true)
    void deletePlayer(@Param("id") Player player);
}
