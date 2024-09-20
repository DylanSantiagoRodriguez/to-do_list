package com.todo.todolist.service;

import com.todo.todolist.model.Task;
import java.util.ArrayList;
import java.util.List;

public class TaskService {

    private static TaskService instance;
    private final List<Task> tasks;

    private TaskService() {
        tasks = new ArrayList<>();
    }

    public static TaskService getInstance() {
        if (instance == null) {
            instance = new TaskService();
        }
        return instance;
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(Task task) {
        tasks.remove(task);
    }
}
