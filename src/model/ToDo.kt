import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table

//enum class Status { IN_PROGRESS, CANCELLED, COMPLETED }


object ToDos : Table() {
    val id = integer("id").autoIncrement()
    val description = varchar("description", 255)
    val heading = varchar("heading", 255)
    val status = bool("status")
//    val status = customEnumeration("status", "ENUM('IN_PROGRESS', 'CANCELLED', 'COMPLETED')", { Status.values()[it as Int] }, { it.name })

    override val primaryKey = PrimaryKey(id)

    fun toToDo(row: ResultRow): ToDo =
        ToDo(
            id = row[ToDos.id],
            heading = row[ToDos.heading],
            description = row[ToDos.description],
            status = row[ToDos.status]
        )

}

data class ToDo(val id: Int, val description: String, val heading: String, val status: Boolean)