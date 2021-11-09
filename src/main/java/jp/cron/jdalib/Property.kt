package jp.cron.jdalib

class Property {
    // activity
    var activityEmbedServerCount = "{serverCount}"
    var activityEmbedMemberCount = "{memberCount}"
    var updateActivityWhenServerJoined = false
    var updateActivityWhenServerLeft = false
    var updateActivityWhenMemberJoined = false
    var updateActivityWhenMemberLeft = false

    // config
    var configFileName = "config.txt"
    var configLinePattern = Regex(pattern = "^([A-Z]+)\\s?=\\s?(.+)$")

}