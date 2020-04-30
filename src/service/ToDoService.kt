import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class ToDoService {

    fun getAllToDos(): List<ToDo> {
       val todos = transaction {
           ToDos.selectAll().map{ ToDos.toToDo(it)}
       }
        return todos;
    }

     fun getToDOById(id: Int): List<ToDo> {
        val todo: List<ToDo> = transaction {
            ToDos.select {
                ToDos.id eq id
            }.map{  ToDos.toToDo(it) }
        }

        return todo;
    }

     fun createToDo(toDo: ToDo): Unit {
        transaction {
            ToDos.insert {
                it[ToDos.heading] = toDo.heading
                it[ToDos.description] = toDo.description
                it[ToDos.status] = toDo.status
            }
        }
    }

     fun  updateToDo(toDo: ToDo): Unit {
        transaction {
            ToDos.update({ ToDos.id eq toDo.id }) {
                it[heading] = toDo.heading
                it[description] = toDo.description
                it[status] = toDo.status
            }
        }
    }

     fun deleteToDoById(id: Int): Unit {
     transaction {
            ToDos.deleteWhere {
                ToDos.id eq id
            }
        }
    }

}