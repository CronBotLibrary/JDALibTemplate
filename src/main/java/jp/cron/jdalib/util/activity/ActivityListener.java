package jp.cron.jdalib.util.activity;

import jp.cron.jdalib.JDALib;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberLeaveEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ActivityListener extends ListenerAdapter {

    private JDALib jdaLib;

    public ActivityListener(JDALib jdaLib) {
        this.jdaLib = jdaLib;
    }

    @Override
    public void onReady(ReadyEvent readyEvent){
        if (jdaLib.activityManager != null) {
            jdaLib.activityManager.updateActivityIfEnabled();
        }
    }

    @Override
    public void onGuildJoin(GuildJoinEvent event) {
        if (jdaLib.property.getUpdateActivityWhenServerJoined() && jdaLib.activityManager!=null){
             jdaLib.activityManager.updateActivityIfEnabled();
        }
    }

    @Override
    public void onGuildLeave(GuildLeaveEvent event) {
        if (jdaLib.property.getUpdateActivityWhenServerLeft() && jdaLib.activityManager!=null){
            jdaLib.activityManager.updateActivityIfEnabled();
        }
    }

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event){
        if (jdaLib.property.getUpdateActivityWhenMemberJoined() && jdaLib.activityManager!=null){
            jdaLib.activityManager.updateActivityIfEnabled();
        }
    }

    @Override
    public void onGuildMemberRemove(GuildMemberRemoveEvent event){
        if (jdaLib.property.getUpdateActivityWhenMemberLeft() && jdaLib.activityManager!=null){
            jdaLib.activityManager.updateActivityIfEnabled();
        }
    }

}
