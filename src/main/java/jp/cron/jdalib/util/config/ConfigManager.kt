package jp.cron.jdalib.util.config

import jp.cron.jdalib.JDALib
import java.io.File
import java.lang.StringBuilder

class ConfigManager(private val jdaLib: JDALib) {
    private var data = HashMap<String, String>()


    private val configFile: File
        get() = File(jdaLib.property.configFileName)

    private val linePatternRegex
        get() = jdaLib.property.configLinePattern


    fun read() {
        configFile.createNewFile()
        configFile.readLines().forEach { line ->
            val match = linePatternRegex.find(line)
            if (!line.startsWith("#") && match!=null) {
                data.put(
                    match.groupValues.get(1),
                    match.groupValues.get(2)
                )
            }
        }
    }

    fun getString(key: String) : String? {
        return data.get(key.uppercase())
    }

    fun setValue(key: String, value: Object) {
        data[key.uppercase()] = value.toString()

        val output = StringBuilder()
        var changedValue = false

        configFile.readLines().forEach { line ->
            val match = linePatternRegex.find(line)
            if (!line.startsWith("#") && match!=null) {
                if (match.groupValues.get(1) == key.uppercase()){
                    output.append(line.replace(match.groupValues.get(2), value.toString()))
                    changedValue = true
                } else {
                    output.append(line)
                }
            } else {
                output.append(line)
            }
            output.appendLine()
        }

        if (!changedValue){
            output.append(key.uppercase()+" = "+value.toString());
            output.appendLine()
        }

        configFile.writeText(output.toString())
    }
}