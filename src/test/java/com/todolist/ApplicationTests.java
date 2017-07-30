package com.todolist;

import com.todolist.model.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	public static final String REST_SERVICE_URI = "http://localhost:8080/rest/todolist";

	@Test
	public void testGetTaskInfo(){
		System.out.println("testGetTaskInfo() start.");
		RestTemplate restTemplate = new RestTemplate();
		List<Task> taskList = restTemplate.getForObject(REST_SERVICE_URI+"/view", List.class);
		System.out.println("taskList size = "+ taskList.size());
		System.out.println("testGetTaskInfo() end.");
	}

	@Test
	public void testGetTaskInfoByID(){
		System.out.println("testGetTaskInfoByID() start.");
		RestTemplate restTemplate = new RestTemplate();
		Task task = restTemplate.getForObject(REST_SERVICE_URI+"/view/3", Task.class);
		System.out.println(task);
		System.out.println("testGetTaskInfoByID() end.");
	}

	@Test
	public void testAddTaskInfo(){
		System.out.println("testAddTaskInfo() start.");
		RestTemplate restTemplate = new RestTemplate();
		Task newTask = new Task(0,"Do homework","English and Math","Pending");
		URI uri = restTemplate.postForLocation(REST_SERVICE_URI+"/add/", newTask, Task.class);
		System.out.println("Location : "+uri.toASCIIString());
		System.out.println("testAddTaskInfo() end.");
	}

	@Test
	public void testEditTaskInfo(){
		System.out.println("testEditTaskInfo() start.");
		RestTemplate restTemplate = new RestTemplate();
		Task editTask = new Task(0,"Buy groceries","1.Milk 2.Bread 3.Butter 4.Ham","Pending");
		restTemplate.put(REST_SERVICE_URI+"/edit/3", editTask, Task.class);
		System.out.println(editTask);
		System.out.println("testEditTaskInfo() end.");
	}

	@Test
	public void testEditTaskStatus(){
		System.out.println("testEditTaskStatus() start.");
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		Task editTask = new Task(0,"","","Done");
		Task editTaskStatus = restTemplate.patchForObject(REST_SERVICE_URI+"/edit/2/status", editTask, Task.class);
		System.out.println(editTaskStatus);
		System.out.println("testEditTaskStatus() end.");
	}

	@Test
	public void testDeleteTaskInfo(){
		System.out.println("testDeleteTaskInfo() start.");
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(REST_SERVICE_URI+"/delete/3");
		System.out.println("testDeleteTaskInfo() end.");
	}
}
