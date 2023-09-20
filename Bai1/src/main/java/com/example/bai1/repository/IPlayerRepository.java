package com.example.bai1.repository;

import com.example.bai1.model.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IPlayerRepository extends JpaRepository<Player,Integer> {
//    @Query(value = " select * from player where player.status = true",nativeQuery = true)
//    List<Player> findAllPlayer();


    @Transactional
    @Modifying
    @Query(value = "update player set player.status = 0 where player.id = :id",nativeQuery = true)
    void deletePlayer(@Param("id") Player player);

    @Query(value = "select * from player where birth_day > :dayStart and birth_day < :dayEnd and name like :nameSearch and player.status = true",nativeQuery = true)
    Page<Player> findPlayerByNameContaining(Pageable pageable,@Param("nameSearch") String nameSearch,@Param("dayStart") String dayStart,@Param("dayEnd") String dayEnd);
}
