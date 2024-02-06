package repository;

import entity.Goal;
import entity.Range;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoalRepository {
    static Long goalCount = 0L;
    public static Map<Long, Object> goalMemory = new HashMap<>();

    public boolean existGoal(Long goalId){
        return goalMemory.containsKey(goalId);
    }
    public void save(Goal goal) {
        goal.setId(goalCount);
        goalMemory.put(goalCount, goal);
        goalCount++;
    }

    public void delete(Long goalId){
        goalMemory.remove(goalId);
    }

    public Goal getAGoal(Long goalId){
        return (Goal) goalMemory.get(goalId);
    }

    public List<Goal> getAllGoals(List<Goal> Goals){
        for(Long key : goalMemory.keySet()) {
            Goal value = (Goal) goalMemory.get(key);
            Goals.add(value);
        }
        return Goals;
    }

    public void updateName(Long goalId, String name){
        Goal goal = (Goal) goalMemory.get(goalId);
        goal.setName(name);
        goalMemory.put(goalId, goal);
    }

    public void updateRange(Long goalId, Range range){
        Goal goal = (Goal) goalMemory.get(goalId);
        goal.setRange(range);
        goalMemory.put(goalId, goal);
    }


}
