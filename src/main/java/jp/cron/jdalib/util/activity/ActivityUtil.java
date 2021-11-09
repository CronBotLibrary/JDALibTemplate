package jp.cron.jdalib.util.activity;

import jp.cron.jdalib.JDALib;
import jp.cron.jdalib.util.common.CommonUtil;
import net.dv8tion.jda.api.entities.Activity;

public class ActivityUtil {
    public static String generateActivityString(String pattern, JDALib jdaLib) {
        return pattern
                .replace(jdaLib.property.getActivityEmbedMemberCount(), String.valueOf(CommonUtil.countMembers(jdaLib.jda)))
                .replace(jdaLib.property.getActivityEmbedServerCount(), String.valueOf(CommonUtil.countServer(jdaLib.jda)));
    }

    public static Activity generateActivity(Activity.ActivityType activityType, String activity){
        return Activity.of(activityType, activity);
    }
}
