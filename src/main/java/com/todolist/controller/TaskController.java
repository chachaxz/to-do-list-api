package com.todolist.controller;

import com.todolist.model.Task;
import com.todolist.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(value="/rest/todolist")
public class TaskController {
    public static final Logger logger = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public ResponseEntity<List<Task>> getTaskInfo(){
        logger.info("View all tasks");
        List<Task> taskList = taskService.getAllTasks();
        if (taskList.isEmpty()){
            return new ResponseEntity<List<Task>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Task>>(taskList, HttpStatus.OK);
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public ResponseEntity<Task> getTaskInfoByID(@PathVariable int id){
        logger.info("View Task ID : {}", id);
        Task task = taskService.getTaskById(id);
        if (task == null){
            return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Task>(task, HttpStatus.OK);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<String> addTaskInfo(@RequestBody Task task, UriComponentsBuilder ucBuilder){
        logger.info("Create Task : {}", task);
        taskService.addTask(task);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/rest/todolist/view/{id}").buildAndExpand(task.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Task> editTaskInfo(@PathVariable int id, @RequestBody Task task){
        logger.info("Edit Task ID : {}", id);
        Task selectedTask = taskService.getTaskById(id);
        if (selectedTask == null){
            return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
        }
        Task updatedTask = taskService.updateTask(id, task);
        return new ResponseEntity<Task>(updatedTask, HttpStatus.OK);
    }

    @RequestMapping(value = "/edit/{id}/status", method = RequestMethod.PATCH)
    public ResponseEntity<Task> editTaskStatus(@PathVariable int id, @RequestBody Task task){
        logger.info("Set status to Task  ID : {}", id);
        Task selectedTask = taskService.getTaskById(id);
        if (selectedTask == null){
            return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
        }
        Task updatedTaskStatus = taskService.updateTaskStatus(id, task);
        return new ResponseEntity<Task>(updatedTaskStatus, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteTaskInfo(@PathVariable int id){
        logger.info("Delete Task ID : {}", id);
        Task task = taskService.getTaskById(id);
        if (task == null){
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        taskService.deleteTaskById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
