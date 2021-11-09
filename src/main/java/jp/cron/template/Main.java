package jp.cron.template;

import jp.cron.jdalib.JDALib;
import jp.cron.template.commands.GeneralCategory;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;
import java.util.Arrays;

public class Main {
    public final static GatewayIntent[] INTENTS = {GatewayIntent.DIRECT_MESSAGES, GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MESSAGE_REACTIONS, GatewayIntent.GUILD_VOICE_STATES};


    public static void main(String[] args) throws LoginException {
        JDALib jdaLib = new JDALib();
        jdaLib.configManager.read();
        String token = jdaLib.configManager.getString("TOKEN");

        if (token == null || token == "SET TOKEN HERE"){

            jdaLib.configManager.setValue("TOKEN", "SET TOKEN HERE");
            System.out.println("Please set token in config.txt!");
            return;

        } else {
            JDA jda = JDABuilder.create(token, Arrays.asList(INTENTS))
                    .enableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE)
                    .build();
            jdaLib.setJDA(jda);
            jdaLib.setActivity(true, "{serverCount} Servers");
            jdaLib.commandManager.register(new GeneralCategory());
        }
    }
}
