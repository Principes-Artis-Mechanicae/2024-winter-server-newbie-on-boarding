package repository;

import entity.Goal;
import entity.Range;
import entity.Task;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class TaskRepository {

    static Long taskCount = 0L;
    public static Map<Long, Object> taskMemory = new HashMap<>();

    public boolean existTask(Long taskId){
        return taskMemory.containsKey(taskId);
    }
    public boolean existTaskByGoal(Long goalId){
        for(Long key : taskMemory.keySet()) {
            Task value = (Task) taskMemory.get(key);
            if (Objects.equals(value.getGoalId(), goalId)){
                return true;
            }
        }
        return false;
    }
    public boolean existDate(LocalDate date){
        for(Long key : taskMemory.keySet()) {
            Task value = (Task) taskMemory.get(key);
            if (value.isDateSame(date)){
                return true;
            }
        }
        return false;
    }

    public void save(Task task) {
        task.setTaskId(taskCount);
        taskMemory.put(taskCount, task);
        taskCount++;
    }

    public void delete(Long taskId){
        taskMemory.remove(taskId);
    }

    public Task getATask(Long taskId){
        return (Task) taskMemory.get(taskId);
    }

    public List<Task> getTasks(List<Task> tasks){
        for(Long key : taskMemory.keySet()) {
            Task value = (Task) taskMemory.get(key);
            tasks.add(value);
        }
        return tasks;
    }

    public List<Task> getTasksByGoalId(Long goalId, List<Task> tasks){
        for(Long key : taskMemory.keySet()) {
            Task value = (Task) taskMemory.get(key);
            if (Objects.equals(value.getGoalId(), goalId)){
                tasks.add(value);
            }
        }
        return tasks;
    }
    public void updateName(Long taskId, String name){
        Task task = (Task) taskMemory.get(taskId);
        task.setName(name);
        taskMemory.put(taskId, task);
    }

    public void updateState(Long taskId, boolean state){
        Task task = (Task) taskMemory.get(taskId);
        task.setState(state);
        taskMemory.put(taskId, task);
    }

    public int amountOfTask(LocalDate date){
        int dateCount = 0;
        for(Long key : taskMemory.keySet()) {
            Task value = (Task) taskMemory.get(key);
            if (value.isDateSame(date)){
                dateCount++;
            }
        }
        return dateCount;
    }

    public int amountOfDoneTask(LocalDate date){
        int dateCount = 0;
        for(Long key : taskMemory.keySet()) {
            Task value = (Task) taskMemory.get(key);
            if (value.isDateSame(date) && value.isState()){
                dateCount++;
            }
        }
        return dateCount;
    }

}
