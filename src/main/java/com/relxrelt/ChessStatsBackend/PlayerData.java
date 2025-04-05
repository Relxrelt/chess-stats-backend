package com.relxrelt.ChessStatsBackend;

public record PlayerData (
        String name,
        long rapidRating,
        long blitzRating,
        long highestRapidRating,
        long highestBlitzRating
    ){
}
