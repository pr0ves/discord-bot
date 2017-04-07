package me.rabrg.rabrgbot.paragon;

import java.util.ArrayList;

public class ParagonMatchHistory {
    private final ArrayList<ParagonMatch> data;

    public ArrayList<ParagonMatch> getData() {
        return data;
    }

    public ParagonMatchHistory(ArrayList<ParagonMatch> data) {

        this.data = data;
    }
}
