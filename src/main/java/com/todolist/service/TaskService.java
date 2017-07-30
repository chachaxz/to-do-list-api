package com.todolist.service;

import com.todolist.model.Task;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class TaskService {
    private static Map<Integer,Task> tasks = new HashMap<Integer,Task>();
    private static final AtomicInteger count = new AtomicInteger(0);

    static {
        //Initialize data
        int id = 0;
        id = generateTaskID();
        tasks.put(id,new Task(id,"Do API assignment","","Done"));
        id = generateTaskID();
        tasks.put(id,new Task(id,"Send parcel to customer","","Pending"));
        id = generateTaskID();
        tasks.put(id,new Task(id,"Buy groceries","1.Milk 2.Bread 3.Butter","Pending"));
    }

    public List<Task> getAllTasks() {
        List<Task> taskList = new ArrayList<Task>(tasks.values());
        return taskList;
    }

    public Task getTaskById(int id){
        if(!isTaskExist(id)){
            return null;
        }
        return tasks.get(id);
    }

    public void addTask(Task task){
        task.setId(generateTaskID());
        tasks.put(task.getId(), task);
    }

    public Task updateTask(int id, Task task){
        Task updatedTask = tasks.get(id);
        updatedTask.setSubject(task.getSubject());
        updatedTask.setDetail(task.getDetail());
        updatedTask.setStatus(task.getStatus());
        return updatedTask;
    }

    public Task updateTaskStatus(int id, Task task){
        Task updatedTaskStatus = tasks.get(id);
        updatedTaskStatus.setStatus(task.getStatus());
        return updatedTaskStatus;
    }

    public void deleteTaskById(int id){
        tasks.remove(id);
    }

    private boolean isTaskExist(int id){
        if(!tasks.containsKey(id)){
            return false;
        }
        return true;
    }

    public static int generateTaskID(){
        return count.incrementAndGet();
    }
}
