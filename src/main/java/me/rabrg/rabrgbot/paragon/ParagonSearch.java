package me.rabrg.rabrgbot.paragon;

import java.util.ArrayList;

public class ParagonSearch {

    private final ArrayList<ParagonSearchData> data;

    ParagonSearch(ArrayList<ParagonSearchData> data) {
        this.data = data;
    }

    public ArrayList<ParagonSearchData> getData() {
        return data;
    }
}
