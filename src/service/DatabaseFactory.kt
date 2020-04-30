package com.trial.ktorRest.service

import ToDos
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils.create
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.insert


object DatabaseFactory {

    fun init() {
        Database.connect("jdbc:h2:mem:regular;DB_CLOSE_DELAY=-1;", "org.h2.Driver")

        transaction {
            addLogger(StdOutSqlLogger)
            create(ToDos)

            ToDos.insert {
                it[heading] = "first"
                it[description] = "dummy first todo"
                it[status] = false
            }

            ToDos.insert {
                it[heading] = "second"
                it[description] = "dummy second todo"
                it[status] = false
            }
        }
    }

}