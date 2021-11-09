package jp.cron.jdalib.command;

import jp.cron.jdalib.JDALib;
import jp.cron.jdalib.command.entity.Category;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;

public class CommandListener extends ListenerAdapter {
    private JDALib jdaLib;

    public CommandListener(JDALib jdaLib) {
        this.jdaLib = jdaLib;
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        for (Category category : jdaLib.commandManager.categories) {
            category.call(event);
        }
    }
}
