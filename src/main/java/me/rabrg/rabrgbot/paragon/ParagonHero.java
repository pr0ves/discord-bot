package me.rabrg.rabrgbot.paragon;

public class ParagonHero {
    private final String id;
    private final String name;
    private final String attack;
    private final String affinity1;
    private final String affinity2;
    private final String damageType;
    private final String code;

    public ParagonHero(String id, String name, String attack, String affinity1, String affinity2, String damageType, String code) {
        this.id = id;
        this.name = name;
        this.attack = attack;
        this.affinity1 = affinity1;
        this.affinity2 = affinity2;
        this.damageType = damageType;
        this.code = code;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAttack() {
        return attack;
    }

    public String getAffinity1() {
        return affinity1;
    }

    public String getAffinity2() {
        return affinity2;
    }

    public String getDamageType() {
        return damageType;
    }

    public String getCode() {
        return code;
    }


}
