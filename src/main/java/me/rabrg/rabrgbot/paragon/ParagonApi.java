package me.rabrg.rabrgbot.paragon;

import com.google.gson.Gson;
import me.rabrg.rabrgbot.util.JsonUtil;

public final class ParagonApi {

    private static final String BASE_ENDPOINT = "https://api.agora.gg/players/";

    private final Gson gson = new Gson();

    public ParagonProfileData getProfileData(final String username) {
        String response = JsonUtil.getJsonResponse(BASE_ENDPOINT + "search/" + username);
        response = response.replace("[", "");
        response = response.replace("]", "");
        ParagonSearch search = gson.fromJson(response, ParagonSearch.class);
        return gson.fromJson(JsonUtil.getJsonResponse(BASE_ENDPOINT + search.getData().getId()), ParagonProfile.class).getData();
    }
}
