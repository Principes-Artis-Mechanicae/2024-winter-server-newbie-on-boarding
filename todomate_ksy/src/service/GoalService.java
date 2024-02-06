package service;

import entity.Goal;
import entity.Range;
import repository.GoalRepository;

import java.util.ArrayList;
import java.util.List;

public class GoalService {
    private final GoalRepository goalRepository;

    public GoalService(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    public void addGoal(String name) {
        Goal goal = new Goal(name, Range.PUBLIC);
        goalRepository.save(goal);
    }

    public Goal getGoal(Long goalId) {
        if (goalRepository.existGoal(goalId)) {
            return goalRepository.getAGoal(goalId);
        } else {
            return null;
            // todo: 예외처리 하기
        }
    }

    public List<Goal> getAllGoal() {
        ArrayList<Goal> Goals = new ArrayList<>();
        return goalRepository.getAllGoals(Goals);
    }

    public void deleteGoal(Long goalId) {
        if (goalRepository.existGoal(goalId)) {
            goalRepository.delete(goalId);
        } else {
            // todo: 예외처리 하기
        }
    }

    public void updateGoalName(Long goalId, String name) {
        if (goalRepository.existGoal(goalId)) {
            goalRepository.updateName(goalId, name);
        } else {
            // todo: 예외처리 하기
        }
    }

    public void updateGoalRange(Long goalId, Range range) {
        if (goalRepository.existGoal(goalId)) {
            goalRepository.updateRange(goalId, range);
        } else {
            // todo: 예외처리 하기
        }
    }
}