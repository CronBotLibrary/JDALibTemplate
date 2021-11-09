package jp.cron.jdalib;

import jp.cron.jdalib.command.CommandListener;
import jp.cron.jdalib.command.CommandManager;
import jp.cron.jdalib.util.activity.ActivityListener;
import jp.cron.jdalib.util.activity.ActivityManager;
import jp.cron.jdalib.util.config.ConfigManager;
import jp.cron.jdalib.util.usage.UsageManager;
import net.dv8tion.jda.api.JDA;

public class JDALib {
    public static JDALib INSTANCE;
    public JDA jda;

    public Property property;

    // util futures
    public ActivityManager activityManager = null;
    public UsageManager usageManager = new UsageManager(this);
    public CommandManager commandManager = new CommandManager();
    public ConfigManager configManager = new ConfigManager(this);

    public JDALib() {
        INSTANCE = this;
        this.property = new Property();
    }

    public JDALib setJDA(JDA jda){
        this.jda = jda;

        jda.addEventListener(
                new Listener(),
                new ActivityListener(this),
                new CommandListener(this)
        );
        return this;
    }

    public JDALib setActivity(Boolean status, String pattern){
        if (activityManager == null){
            this.activityManager = new ActivityManager(this, status, pattern);
        } else {
            this.activityManager.setStatus(status);
            this.activityManager.setPattern(pattern);
        }
        return this;
    }
}
