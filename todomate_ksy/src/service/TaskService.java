package service;

import entity.Task;
import repository.TaskRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskService {
    private final TaskRepository taskRepository;
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void addTask(Long goalId, String name, String dateString) {
        LocalDate date = LocalDate.parse(dateString);
        Task task = new Task(goalId, name, date, false);
        taskRepository.save(task);
    }

    public Task getTask(Long taskId) {
        if (taskRepository.existTask(taskId)) {
            return taskRepository.getATask(taskId);
        } else {
            return null;
            // todo: 예외처리 하기
        }
    }

    public List<Task> getAllTask() {
        ArrayList<Task> Tasks = new ArrayList<>();
        return taskRepository.getTasks(Tasks);
    }

    public List<Task> getAllTaskByGoalId(Long goalId) {
        if (taskRepository.existTaskByGoal(goalId)) {
            ArrayList<Task> Tasks = new ArrayList<>();
            return taskRepository.getTasksByGoalId(goalId, Tasks);
        } else {
            // todo: 예외처리 하기
            return null;
        }
    }

    public void deleteTask(Long taskId) {
        if (taskRepository.existTask(taskId)) {
            taskRepository.delete(taskId);
        } else {
            // todo: 예외처리 하기
        }
    }

    public void updateTaskName(Long taskId, String name) {
        if (taskRepository.existTask(taskId)) {
            taskRepository.updateName(taskId, name);
        } else {
            // todo: 예외처리 하기
        }
    }

    public void updateTaskState(Long taskId, boolean state){
        if (taskRepository.existTask(taskId)) {
            taskRepository.updateState(taskId, state);
        } else {
            // todo: 예외처리 하기
        }
    }

    public int getLeftTask(LocalDate date){
        if (taskRepository.existDate(date)){
            return taskRepository.amountOfTask(date) - taskRepository.amountOfDoneTask(date);
        } else {
            return 77;
            // todo: 예외처리 하기
        }
    }
}
