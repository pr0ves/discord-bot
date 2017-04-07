package me.rabrg.rabrgbot.listener.command.impl;

import me.rabrg.rabrgbot.RabrgBot;
import me.rabrg.rabrgbot.listener.command.Command;
import me.rabrg.rabrgbot.paragon.ParagonMatch;
import me.rabrg.rabrgbot.paragon.ParagonProfileData;
import net.dv8tion.jda.events.message.MessageReceivedEvent;

import java.text.DecimalFormat;

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
                    DecimalFormat df = new DecimalFormat("0.00");
                    final String[] response = {
                            "Match ID: " + match.getId() + "\n" +" Length: " + formatSeconds(match.getLenght()) + "\n\n" +
                                    (match.getWinningTeam() == 0 ? "Team One won.\n\n" : "Team Two won.\n\n") +
                                    "Team One\n"};
                    response[0] += "```diff\n";
                    match.getTeams().get(0).forEach(h -> response[0] += h.getName() + " " + h.getElo() +
                            "\n" + bot.getParagonApi().getParagonHeroFromCode(h.getHero()).getName() +
                            " " + h.getKills() +
                            "/" + h.getDeaths() +
                            "/" + h.getAssists() +
                            " KDA: " + df.format((h.getKills() + h.getAssists()) / (float) h.getDeaths()) +
                            " " + formatDamage(h.getHeroDamage() + h.getTowerDamage() + h.getJungleDamage() + h.getMinionDamage()) + " dmg\n\n");

                    response[0] += "```\nTeam Two\n" +
                            "```diff\n";
                    match.getTeams().get(1).forEach(h -> response[0] += h.getName() + " " + h.getElo() +
                            "\n" + bot.getParagonApi().getParagonHeroFromCode(h.getHero()).getName() +
                            " " + h.getKills() +
                            "/" + h.getDeaths() +
                            "/" + h.getAssists() +
                            " KDA: " + df.format((h.getKills() + h.getAssists()) / (float) h.getDeaths()) +
                            " " + formatDamage(h.getHeroDamage() + h.getTowerDamage() + h.getJungleDamage() + h.getMinionDamage()) + " dmg\n\n");
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

    private String formatSeconds(int secs) {
        String time = "";
        if (secs / 3600 >= 1) {
            time += secs / 3600 + ":";
            int tmp = (secs / 3600);
            secs -= tmp * 3600;
        }
        if (secs / 60 >= 1) {
            time += String.format("%02d", secs / 60) + ":";
            int tmp = (secs / 60);
            secs -= tmp * 60;
        } else {
            time += "00:";
        }
        time += secs;
        return time;
    }
}
