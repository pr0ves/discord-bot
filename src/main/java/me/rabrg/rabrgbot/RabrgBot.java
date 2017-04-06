package me.rabrg.rabrgbot;

import me.rabrg.rabrgbot.listener.ChatbotListener;
import me.rabrg.rabrgbot.listener.CommandListener;
import me.rabrg.rabrgbot.listener.MessageLogListener;
import me.rabrg.rabrgbot.listener.NiceTryListener;
import me.rabrg.rabrgbot.overwatch.OverwatchApi;
import me.rabrg.rabrgbot.paragon.ParagonApi;
import net.dv8tion.jda.JDA;
import net.dv8tion.jda.JDABuilder;
import net.dv8tion.jda.audio.player.Player;
import net.dv8tion.jda.entities.MessageChannel;

import java.util.HashMap;
import java.util.Map;

public final class RabrgBot {

    private static final String BOT_TOKEN = "Mjk5NDY0ODA5Mzc5MDA0NDE2.C8eR9w.wT1uYtnTEfFhpK7iFBlQd9tZYvo";

    private JDA api;
    private OverwatchApi overwatchApi;
    private ParagonApi paragonApi;

    private CommandListener commandListener;
    private NiceTryListener niceTryListener;
    private ChatbotListener chatbotListener;
    private MessageLogListener messageLogListener;

    private boolean chatbot = false;
    private boolean silent = false;

    private final Map<String, Player> audioPlayers = new HashMap<>();

    public static void main(final String[] args) {
        try {
            new RabrgBot().create();
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    public RabrgBot create() throws Exception {
        commandListener = new CommandListener(this);
        niceTryListener = new NiceTryListener(this);
        chatbotListener = new ChatbotListener(this);
        messageLogListener = new MessageLogListener();
        api = new JDABuilder()
                .setBotToken(BOT_TOKEN)
                .addListener(chatbotListener)
                .addListener(commandListener)
                .addListener(niceTryListener)
                .addListener(messageLogListener)
                .buildBlocking();
        overwatchApi = new OverwatchApi();
        paragonApi = new ParagonApi();
        return this;
    }

    public void sendMessage(final MessageChannel channel, final String message) {
        if (!silent)
            channel.sendMessage(message);
    }

    public JDA getApi() {
        return api;
    }

    public OverwatchApi getOverwatchApi() {
        return overwatchApi;
    }

    public ParagonApi getParagonApi() {
        return paragonApi;
    }

    public CommandListener getCommandListener() {
        return commandListener;
    }

    public NiceTryListener getNiceTryListener() {
        return niceTryListener;
    }

    public boolean toggleChatbot() {
        return (chatbot = !chatbot);
    }

    public boolean chatbotEnabled() {
        return chatbot;
    }

    public Player getAudioPlayer(final String guildId) {
        return audioPlayers.get(guildId);
    }

    public Player putAudioPlayer(final String guildid, final Player player) {
        return audioPlayers.put(guildid, player);
    }

    public boolean toggleSilence() {
        return (silent = !silent);
    }
}
