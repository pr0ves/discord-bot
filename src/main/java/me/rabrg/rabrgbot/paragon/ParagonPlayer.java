package me.rabrg.rabrgbot.paragon;

public class ParagonPlayer {

    private final int id;
    private final String name;
    private final int elo;
    private final double lastSeasonElo;
    private final int team;
    private final String hero;
    private final int kills;
    private final int deaths;
    private final int assists;
    private final int towers;
    private final int heroDamage;
    private final int minionDamage;
    private final int jungleDamage;
    private final int rigDamage;
    private final int towerDamage;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getElo() {
        return elo;
    }

    public double getLastSeasonElo() {
        return lastSeasonElo;
    }

    public int getTeam() {
        return team;
    }

    public String getHero() {
        return hero;
    }

    public int getKills() {
        return kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getAssists() {
        return assists;
    }

    public int getTowers() {
        return towers;
    }

    public int getHeroDamage() {
        return heroDamage;
    }

    public int getMinionDamage() {
        return minionDamage;
    }

    public int getJungleDamage() {
        return jungleDamage;
    }

    public int getRigDamage() {
        return rigDamage;
    }

    public int getTowerDamage() {
        return towerDamage;
    }

    public ParagonPlayer(int id, String name, int elo, double lastSeasonElo, int team, String hero, int kills, int deaths, int assists, int towers, int heroDamage, int minionDamage, int jungleDamage, int rigDamage, int towerDamage) {

        this.id = id;
        this.name = name;
        this.elo = elo;
        this.lastSeasonElo = lastSeasonElo;
        this.team = team;
        this.hero = hero;
        this.kills = kills;
        this.deaths = deaths;
        this.assists = assists;
        this.towers = towers;
        this.heroDamage = heroDamage;
        this.minionDamage = minionDamage;
        this.jungleDamage = jungleDamage;
        this.rigDamage = rigDamage;
        this.towerDamage = towerDamage;
    }
}
