package com.in28minutes.rest.webservices.restfulwebservices.todo;

import com.in28minutes.rest.webservices.restfulwebservices.todo.repository.TodoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoJpaResource {
    private TodoService todoService;
    private TodoRepository todoRepository;

    public TodoJpaResource(TodoService todoService, TodoRepository todoRepository) {
        this.todoService = todoService;
        this.todoRepository = todoRepository;
    }

    @GetMapping(path="/users/{username}/todos")
    public List<Todo> retrieveTodos(@PathVariable String username){
            //return todoService.findByUsername(username);
        return todoRepository.findByUsername(username);

    }

    @GetMapping(path="/users/{username}/todos/{id}")
    public Todo retrieveTodo(@PathVariable String username, @PathVariable int id ){
        //return todoService.findById(id);
        return todoRepository.findById(id).get();
    }

    @DeleteMapping(path="/users/{username}/todos/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable int id ){
         //todoService.deleteById(id);
        todoRepository.deleteById(id);
         return ResponseEntity.noContent().build();//return a success. we can use public void ...
    }
    @PutMapping (path="/users/{username}/todos/{id}")
    public Todo updateTodo(@PathVariable String username, @PathVariable int id, @RequestBody Todo todo ){
        //todoService.updateTodo(todo);
        todoRepository.save(todo);
        return todo;
    }


    @PostMapping (path="/users/{username}/todos")
    public Todo createTodo(@PathVariable String username, @RequestBody Todo todo ){
        todo.setUsername(username);
        todo.setId(null);
       return todoRepository.save(todo);
        //Todo createdTodo = todoService.addTodo(username, todo.getDescription(), todo.getTargetDate(), todo.isDone());
       // return createdTodo;
    }

}

