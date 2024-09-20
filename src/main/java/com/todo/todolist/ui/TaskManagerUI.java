package com.todo.todolist.ui;

import com.todo.todolist.model.Task;
import com.todo.todolist.model.TaskFactory;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TaskManagerUI extends Application {
    private ObservableList<Task> tasks = FXCollections.observableArrayList();
    private ListView<String> taskListView;
    private TextField titleField;
    private TextArea descriptionArea;

    @Override
    public void start(Stage primaryStage) {
        taskListView = new ListView<>();
        titleField = new TextField();
        descriptionArea = new TextArea();
        descriptionArea.setPromptText("Enter task description");

        Button addButton = new Button("Add Task");
        addButton.setOnAction(e -> addTask());

        Button editButton = new Button("Edit Task");
        editButton.setOnAction(e -> editTask());

        Button completeButton = new Button("Mark as Completed");
        completeButton.setOnAction(e -> markTaskCompleted());

        VBox layout = new VBox(10, titleField, descriptionArea, addButton, editButton, completeButton, taskListView);
        layout.setPadding(new Insets(20));
        Scene scene = new Scene(layout, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Task Manager");
        primaryStage.show();

        updateTaskListView();
    }

    private void addTask() {
        String title = titleField.getText();
        String description = descriptionArea.getText();
        if (!title.isEmpty() && !description.isEmpty()) {
            Task task = TaskFactory.createTask(title, description, false);
            tasks.add(task);
            updateTaskListView();
            clearFields();
        }
    }

    private void editTask() {
        int selectedIndex = taskListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Task selectedTask = tasks.get(selectedIndex);
            titleField.setText(selectedTask.getTitle());
            descriptionArea.setText(selectedTask.getDescription());
            tasks.remove(selectedIndex);
            updateTaskListView();
        }
    }

    private void markTaskCompleted() {
        int selectedIndex = taskListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Task selectedTask = tasks.get(selectedIndex);
            selectedTask.setCompleted(true);
            tasks.set(selectedIndex, selectedTask);
            updateTaskListView();
        }
    }

    private void updateTaskListView() {
        taskListView.getItems().clear();
        for (Task task : tasks) {
            String status = task.isCompleted() ? "[Completed] " : "[Pending] ";
            taskListView.getItems().add(status + task.getTitle() + ": " + task.getDescription());
        }
    }

    private void clearFields() {
        titleField.clear();
        descriptionArea.clear();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
