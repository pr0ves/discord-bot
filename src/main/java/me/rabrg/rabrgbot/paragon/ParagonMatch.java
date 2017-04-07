package me.rabrg.rabrgbot.paragon;

import java.util.ArrayList;

public class ParagonMatch {
    private final String id;
    private final String createdAt;
    private final int lenght;
    private final int winningTeam;

    private final ArrayList<ParagonTeam> teams;

    public ParagonMatch(String id, String createdAt, int lenght, int winningTeam, ArrayList<ParagonTeam> teams) {
        this.id = id;
        this.createdAt = createdAt;
        this.lenght = lenght;
        this.winningTeam = winningTeam;
        this.teams = teams;
    }

    public String getId() {
        return id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public int getLenght() {
        return lenght;
    }

    public int getWinningTeam() {
        return winningTeam;
    }

    public ArrayList<ParagonTeam> getTeams() {
        return teams;
    }
}
