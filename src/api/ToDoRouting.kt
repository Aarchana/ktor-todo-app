import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.*

fun Route.ToDoRoute(todoService: ToDoService) {
    route("/todo") {
        get {
            call.respond(todoService.getAllToDos())
        }

        get("{id}") {
            val id = call.parameters["id"]!!.toInt();
            call.respond(todoService.getToDOById(id));
        }

        post {
            val toDo = call.receive<ToDo>()
            todoService.createToDo(toDo)
            call.respond(HttpStatusCode.Created)
        }

        put {
            val toDo = call.receive<ToDo>()
            todoService.updateToDo(toDo)
            call.respond(HttpStatusCode.OK)
        }

        delete ("{id}") {
            val id = call.parameters["id"]!!.toInt();
            todoService.deleteToDoById(id);
            call.respond(HttpStatusCode.NoContent);
        }
    }
}