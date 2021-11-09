package jp.cron.jdalib.util.usage;

import jp.cron.jdalib.JDALib;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class UsageManager {
    private JDALib jdaLib;

    public UsageManager(JDALib jdaLib){
        this.jdaLib = jdaLib;
    }

    public String genMemoryUsage() {
        return genMemoryUsage(true, true, true, true, true);
    }

    public String genMemoryUsage(Boolean codeblock, Boolean fraction, Boolean maxMemory, Boolean meter, Boolean percent) {
        StringBuilder field;

        // Memory
        long totalMB = Runtime.getRuntime().totalMemory() / 1024 / 1024;
        long freeMB = Runtime.getRuntime().freeMemory() / 1024 / 1024;
        long usedMB = totalMB-freeMB;
        long maxMB = Runtime.getRuntime().maxMemory() / 1024 / 1024;

        // used / 40
        double magnif40 = 40.0 / totalMB;
        int used40 = Math.toIntExact(Math.round(usedMB * magnif40));
        int free40 = 40 - used40;

        // used / 100
        double magnif1000 = 1000.0 / totalMB;
        double used1000 = Math.round(usedMB * magnif1000);

        field = new StringBuilder();

        if (codeblock)
            field.append("```fix").append("\n");

        if (fraction)
            field.append("[").append(usedMB).append(" MB / ").append(totalMB).append(" MB] ");

        if (maxMemory)
            field.append("( MAX ").append(maxMB/1024).append(" GB )");

        if (codeblock || fraction || maxMemory)
            field.append("\n");

        if (meter)
            field.append("[")
                .append(
                        String.join("", Collections.nCopies(used40, "#"))
                ).append(
                        String.join("", Collections.nCopies(free40, " "))
                ).append("]");

        if (percent)
            field.append(" [").append(used1000/10).append("%]");

        if (codeblock)
            field.append("```");

        return field.toString();
    }
}
