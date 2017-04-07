package me.rabrg.rabrgbot.listener.command.impl;

import me.rabrg.rabrgbot.RabrgBot;
import me.rabrg.rabrgbot.listener.command.Command;
import me.rabrg.rabrgbot.paragon.ParagonHeroes;
import me.rabrg.rabrgbot.paragon.ParagonMatch;
import me.rabrg.rabrgbot.paragon.ParagonProfileData;
import net.dv8tion.jda.events.message.MessageReceivedEvent;

import java.time.LocalTime;

public final class ParagonProfileCommand implements Command {

    @Override
    public String getName() {
        return "paragon";
    }

    @Override
    public String getDescription() {
        return "Gets the profile of the specified Paragon user";
    }

    @Override
    public void run(final RabrgBot bot, final MessageReceivedEvent event, final String args) {
        if (args == null || args.length() == 0) {
            bot.sendMessage(event.getChannel(), "You need to specify the username");
            return;
        }
        String[] argsArray = args.split(" ");

        switch (argsArray[0]) {
            case "profile":
                if (argsArray.length == 2) {
                    String username = argsArray[1];
                    try {
                        final ParagonProfileData data = bot.getParagonApi().getProfileData(username);
                        bot.sendMessage(event.getChannel(), "```diff\n" + data.toString() + "\n```");
                    } catch (final Exception e) {
                        e.printStackTrace();
                        bot.sendMessage(event.getChannel(), "Failed to find " + args + "'s profile");
                    }
                } else {
                    bot.sendMessage(event.getChannel(), "You need to specify the username");
                }

                break;
            case "lastmatch":
                String username = argsArray[1];
                if (argsArray.length == 2) {
                    ParagonMatch match = bot.getParagonApi().getLastMatch(username);
                    final String[] response = {
                            "Match ID: " + match.getId() + "\n" +
                            "Started " + match.getCreatedAt() + " Length: " + LocalTime.ofSecondOfDay(match.getLenght()) + "\n" +
                            (match.getWinningTeam() == 0 ? "Team One won.\n" : "Team Two won\n\n") +
                            "Team One\n"};
                    response[0] += "```diff\nName\tHero\tElo\tKills\tDeaths\tAssists\tKDA\tHero\tTower\tJungle\tMinion\tTotal\n\n";
                    match.getTeams().get(0).forEach(h -> response[0] += h.getName() +
                            "\t" + bot.getParagonApi().getParagonHeroFromCode(h.getHero()).getName() +
                            "\t" + h.getElo() +
                            "\t" + h.getKills() +
                            "\t" + h.getDeaths() +
                            "\t" + h.getAssists() +
                            "\t" + ((h.getKills() + h.getAssists()) / (float) h.getDeaths()) +
                            "\t" + formatDamage(h.getHeroDamage()) +
                            "\t" + formatDamage(h.getTowerDamage()) +
                            "\t" + formatDamage(h.getJungleDamage()) +
                            "\t" + formatDamage(h.getMinionDamage()) +
                            "\t" + formatDamage(h.getHeroDamage() + h.getTowerDamage() + h.getJungleDamage() + h.getMinionDamage()) + "|\n");

                    response[0] += "```Team Two\n" +
                            "```diff\nName\tHero\tElo\tKills\tDeaths\tAssists\tKDA\tHero\tTower\tJungle\tMinion\tTotal\n\n";
                    match.getTeams().get(1).forEach(h -> response[0] += h.getName() +
                            "\t" + bot.getParagonApi().getParagonHeroFromCode(h.getHero()).getName() +
                            "\t" + h.getElo() +
                            "\t" + h.getKills() +
                            "\t" + h.getDeaths() +
                            "\t" + h.getAssists() +
                            "\t" + ((h.getKills() + h.getAssists()) / (float) h.getDeaths()) +
                            "\t" + formatDamage(h.getHeroDamage()) +
                            "\t" + formatDamage(h.getTowerDamage()) +
                            "\t" + formatDamage(h.getJungleDamage()) +
                            "\t" + formatDamage(h.getMinionDamage()) +
                            "\t" + formatDamage(h.getHeroDamage() + h.getTowerDamage() + h.getJungleDamage() + h.getMinionDamage()) + "|\n");
                    response[0] += "```\n";
                    bot.sendMessage(event.getChannel(), response[0]);
                } else {
                    bot.sendMessage(event.getChannel(), "You need to specify the username");
                }
                break;
            default:
        }
    }

    private String formatDamage(int damage) {
        String s = "";
        if (damage >= 1000) {
            s += damage / 1000 + "k";
        } else {
            s += damage;
        }
        return s;
    }
}
