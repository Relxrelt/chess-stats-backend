package com.relxrelt.ChessStatsBackend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerDataController {

    private final PlayerDataService service;

    public PlayerDataController(PlayerDataService service) {
        this.service = service;
    }

    @GetMapping("/playerdata")
    public PlayerData playerData(@RequestParam(value = "username", defaultValue = "hikaru") String username) {

        return service.fetchPlayerData(username);
    }
}
