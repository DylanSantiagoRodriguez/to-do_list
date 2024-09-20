package com.todo.todolist.model;

public class TaskFactory {
    public static Task createTask(String title, String description, boolean completed) {
        return new Task(title, description, completed);
    }
}
