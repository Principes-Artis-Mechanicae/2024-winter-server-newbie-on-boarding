package entity;

import java.time.LocalDate;

public class Task {
    private Long goalId;
    private Long taskId;
    private String name;
    private boolean state;
    private LocalDate date;

    public Task(Long goalId, String name, LocalDate date, boolean state){
        this.goalId = goalId;
        this.name = name;
        this.date = date;
        this.state = state;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public boolean isDateSame(LocalDate exDate){
        if (exDate.isEqual(date)){
            return true;
        } else {
            return false;
        }
    }

    public boolean isState(){
        return this.state;
    }

    public Long getGoalId() {
        return goalId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }
}
