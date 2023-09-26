package com.example.bai1.repository;

import com.example.bai1.dto.IPlayerDto;
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

    @Query(value = "select" +
            " p.id as id," +
            " p.name as name," +
            " p.code_player as codePlayer," +
            " p.birth_day as birthDay, " +
            " p.exp as exp, " +
            " p.picture as picture," +
            " p.action as action, " +
            " po.position as position, " +
            " t.name as team " +
            " from player p" +
            " join position po on p.position_id = po.id" +
            " join team t on p.team_id = t.id" +
            " where birth_day > :dayStart and birth_day < :dayEnd" +
            " and p.name like :nameSearch" +
            " and t.name like :teamSearch" +
            " and p.status = true",nativeQuery = true)
    Page<IPlayerDto> findPlayerByNameContaining(Pageable pageable,
                                                @Param("nameSearch") String nameSearch,
                                                @Param("dayStart") String dayStart,
                                                @Param("dayEnd") String dayEnd,
                                                @Param("teamSearch") String teamSearch);

    @Transactional
    @Modifying
    @Query(value = "update player set player.action = 0 where player.id = :id",nativeQuery = true)
    void playerActive(@Param("id") Player player);


    @Transactional
    @Modifying
    @Query(value = "update player set player.action = 1 where player.id = :id",nativeQuery = true)
    void playerReserve(@Param("id") Player player);

    @Query(value = "select * from player where team_id :id",nativeQuery = true)
    List <Player> checkLimit(@Param("id") int id);
}
