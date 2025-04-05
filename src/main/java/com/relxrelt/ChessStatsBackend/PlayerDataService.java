package com.relxrelt.ChessStatsBackend;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PlayerDataService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public PlayerDataService(RestTemplateBuilder builder, ObjectMapper objectMapper) {
        this.restTemplate = builder.build();
        this.objectMapper = objectMapper;
    }

    public PlayerData fetchPlayerData(String playerName) {
        String apiUrl = "https://api.chess.com/pub/player/" + playerName + "/stats";

        String rawJson = restTemplate.getForObject(apiUrl, String.class);

        try {
            JsonNode data = objectMapper.readTree(rawJson);

            int rapidRating = data.path("chess_rapid").path("last").path("rating").asInt();
            int blitzRating = data.path("chess_blitz").path("last").path("rating").asInt();
            int highestRapidRating = data.path("chess_rapid").path("best").path("rating").asInt();
            int highestBlitzRating = data.path("chess_blitz").path("best").path("rating").asInt();
            return new PlayerData(playerName, rapidRating, blitzRating, highestRapidRating, highestBlitzRating);
        } catch (JsonProcessingException e) {
            System.out.println(e);
            return null;
        }
    }

}
