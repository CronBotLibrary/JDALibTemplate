package jp.cron.jdalib.command.entity

import jp.cron.jdalib.command.Command
import jp.cron.jdalib.util.common.CommonUtil
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import java.lang.reflect.Method
import java.util.function.Consumer

abstract class Category {
    @kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
    @Target(AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.CLASS)
    annotation class Declaration(val name: String, val prefix: String, val description: String = "")

    private val declaration = javaClass.getAnnotation(Declaration::class.java)

    val name = declaration.name
    val prefix = declaration.prefix
    val description = declaration.description

    private val methods = mutableListOf<Method>()

    fun precall() : Boolean {
        return true
    }

    fun call(e: MessageReceivedEvent) {
        if (e.message.contentRaw.startsWith(prefix)){
            methods.forEach { method ->
                val anno = method.getAnnotation(Command::class.java)
                if (e.message.contentRaw.startsWith(prefix+anno.name)){
                    if (precall()){
                        method.invoke(this, e)
                    }
                }
            }
        }
    }

    fun registerMethodMap() {
        CommonUtil.getMethodsAnnotatedWith(
            this.javaClass,
            Command::class.java
        ).forEach(Consumer { m: Method? ->
            if (m != null) {
                methods.add(m)
            }
        })
    }
}