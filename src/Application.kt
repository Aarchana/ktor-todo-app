package com.trial.ktorRest

import ToDoRoute
import ToDoService
import com.trial.ktorRest.service.DatabaseFactory
import io.ktor.application.*
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.jackson.jackson
import io.ktor.routing.Routing

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(CallLogging)
    install(ContentNegotiation) {
        jackson {

        }
    }

    val toDoService = ToDoService()

    install(Routing) {
        ToDoRoute(toDoService)
    }


    DatabaseFactory.init()
}

