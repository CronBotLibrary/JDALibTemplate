package jp.cron.jdalib.util.activity;

import jp.cron.jdalib.JDALib;
import net.dv8tion.jda.api.entities.Activity;

public class ActivityManager {

    private JDALib jdaLib;
    private Boolean status;
    private String pattern;
    private Activity.ActivityType activityType = Activity.ActivityType.DEFAULT;

    public ActivityManager(JDALib jdaLib, Boolean status, String pattern) {
        this.jdaLib = jdaLib;
        this.status = status;
        this.pattern = pattern;

        if (this.pattern.contains(jdaLib.property.getActivityEmbedServerCount())){
            jdaLib.property.setUpdateActivityWhenServerJoined(true);
            jdaLib.property.setUpdateActivityWhenServerLeft(true);
        }
        if (this.pattern.contains(jdaLib.property.getActivityEmbedMemberCount())){
            jdaLib.property.setUpdateActivityWhenMemberJoined(true);
            jdaLib.property.setUpdateActivityWhenMemberLeft(true);
        }

        updateActivityIfEnabled();
    }

    public void updateActivity() {
            jdaLib.jda.getPresence().setActivity(
                    ActivityUtil.generateActivity(
                            activityType,
                            ActivityUtil.generateActivityString(pattern, jdaLib)
                    )
            );
    }

    public void resetActivity() {
        jdaLib.jda.getPresence().setActivity(null);
    }

    public void updateActivityIfEnabled() {
        if (status){
            updateActivity();
        }
    }

    public void setActivityType(Activity.ActivityType activityType){
        this.activityType = activityType;
        updateActivityIfEnabled();
    }

    public void setStatus(Boolean status) {
        this.status = status;
        updateActivityIfEnabled();
        if (!status){
            resetActivity();
        }
    }

    public void setPattern(String str){
        this.pattern = str;
        updateActivityIfEnabled();
    }
}
