package entity;

public class Goal {
    private Long goalId;
    private String name;
    private Range range;


    public Goal(String name, Range range) {
        this.name = name;
        this.range = range;
    }

    public void setId(Long goalId) {
        this.goalId = goalId;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setRange(Range range) {
        this.range = range;
    }

    public Long getGoalId() {
        return goalId;
    }

    public String getName() {
        return name;
    }

    public Range getRange() {
        return range;
    }
}
