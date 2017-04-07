package me.rabrg.rabrgbot.paragon;

import com.google.gson.Gson;
import me.rabrg.rabrgbot.util.JsonUtil;

public final class ParagonApi {

    private static final String BASE_ENDPOINT = "https://api.agora.gg/";
    private static final String PLAYERS = "players/";
    private static final String SEARCH = "players/search/";
    private static final String GAMES = "games/";
    private static final String MATCHHISTORY = "history/match/";
    private static final String HEROES = "gamedata/heroes/";


    private final Gson gson = new Gson();

    public ParagonProfileData getProfileData(final String username) {
        ParagonSearch search = gson.fromJson(JsonUtil.getJsonResponse(BASE_ENDPOINT + SEARCH + username), ParagonSearch.class);
        return gson.fromJson(JsonUtil.getJsonResponse(BASE_ENDPOINT + PLAYERS + search.getData().get(0).getId()), ParagonProfile.class).getData();
    }

    public ParagonHeroes getParagonHeroes() {
        return gson.fromJson(JsonUtil.getJsonResponse(BASE_ENDPOINT + HEROES), ParagonHeroes.class);
    }

    public ParagonHero getParagonHeroFromCode(String code) {
        return getParagonHeroes().getHero(code);
    }

    public ParagonMatch getLastMatch(String username) {
        return gson.fromJson(JsonUtil.getJsonResponse(
                BASE_ENDPOINT + PLAYERS + Integer.toString(getProfileData(username).getId()) + "/" + MATCHHISTORY
        ), ParagonMatchHistory.class).getData().get(0);
    }


}
