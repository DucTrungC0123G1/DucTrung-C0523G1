package com.example.bai1.dto;

import java.util.HashMap;
import java.util.Map;

public class LikeDto {
    private Map<PlayerDto, Integer> playerDtoMap = new HashMap<>();

    public LikeDto() {
    }

    public void addPlayerLike(PlayerDto playerDto) {
        if (playerDtoMap.containsKey(playerDto)) {
            Integer currentValue = playerDtoMap.get(playerDto);
            playerDtoMap.replace(playerDto, currentValue + 1);
        } else {
            playerDtoMap.put(playerDto, 1);
        }
    }

    public Map<PlayerDto, Integer> getPlayerDtoMap() {
        return playerDtoMap;
    }

    public void setPlayerDtoMap(Map<PlayerDto, Integer> playerDtoMap) {
        this.playerDtoMap = playerDtoMap;
    }

    public LikeDto(Map<PlayerDto, Integer> playerDtoMap) {
        this.playerDtoMap = playerDtoMap;
    }
}
