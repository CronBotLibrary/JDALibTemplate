package jp.cron.template.commands;

import jp.cron.jdalib.command.Command;
import jp.cron.jdalib.command.entity.Category;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

@Category.Declaration(name = "General", prefix = "!", description = "")
public class GeneralCategory extends Category {

    @Command(name = "ping")
    public void ping(MessageReceivedEvent e){
        e.getMessage().reply("Pong!").complete();
    }
}
