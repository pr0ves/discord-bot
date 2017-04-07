package me.rabrg.rabrgbot.paragon;

import java.util.ArrayList;

public class ParagonHeroes {
    private final int total;
    private final ArrayList<ParagonHero> data;

    public ParagonHeroes(int total, ArrayList<ParagonHero> data) {
        this.total = total;
        this.data = data;
    }

    public ParagonHero getHero(String code) {
        return data.stream().filter(paragonHero -> paragonHero.getCode().equals(code)).findFirst().get();
    }
}
