import entity.Goal;
import entity.Range;
import entity.Task;
import repository.GoalRepository;
import repository.TaskRepository;
import service.GoalService;
import service.TaskService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GoalService goalService = new GoalService(new GoalRepository());
        TaskService taskService = new TaskService(new TaskRepository());
        Scanner sc = new Scanner(System.in);


        while (true) {
            System.out.println("-----------------------------------------");
            System.out.println("   1 : 목표 관련, 2 : 날짜 관련, 0 : 종료    ");
            System.out.println(" 모드 입력에서 99를 입력하면 처음으로 돌아옵니다.");
            System.out.println("-----------------------------------------");

            int mode = sc.nextInt();
            sc.nextLine();

            if (mode == 1) {
                goalMenu(sc, goalService, taskService);
            } else if (mode == 2) {
                dateMenu(sc, goalService, taskService);
            }  else if (mode == 0) {
                break;
            }
        }

    }

    public static void goalMenu(Scanner sc, GoalService goalService, TaskService taskService){
        System.out.println("-----------------------------------------");
        System.out.println("1 : 목표 보기, 2 : 목표 등록, 3 : 목표 선택");
        System.out.println("-----------------------------------------");

        int menu = sc.nextInt();
        sc.nextLine();

        if (menu == 1) {                                                                                      // 목표 보기
            List<Goal> Goals = goalService.getAllGoal();

            if (Goals.isEmpty()){
                System.out.println("등록된 목표가 없습니다.");
                goalMenu(sc, goalService, taskService);
            } else {
                for (Goal goal : Goals) {
                    System.out.printf("%d %s %s%n", goal.getGoalId(), goal.getName(), goal.getRange().name());
                }
                goalMenu(sc, goalService, taskService);

            }

        } else if (menu == 2) {                                                                              // 등록
            System.out.println("등록할 목표를 입력하세요.");
            goalService.addGoal(sc.nextLine());
            System.out.println("등록되었습니다.");
            goalMenu(sc, goalService, taskService);

        } else if (menu == 3) {                                                                             // 목표 선택
            System.out.println("선택하실 목표의 GoalId를 입력하세요.");
            Long goalId = sc.nextLong();
            cGoalMenu(goalId, sc, goalService, taskService);

        } else if (menu == 99) {
            return;
        }
    }

    public static void cGoalMenu(Long goalId, Scanner sc, GoalService goalService, TaskService taskService){
        System.out.println("-----------------------------------------");
        System.out.println("1 : 이름 설정, 2 : 범위 설정, 3 : 삭제, 4 : 하위 할 일 관련");
        System.out.println("-----------------------------------------");

        int menu = sc.nextInt();
        sc.nextLine();

        if (menu == 1) {                                                                                              // 이름
            System.out.println("목표의 새로운 이름을 입력하세요.");
            String goalName = sc.nextLine();
            goalService.updateGoalName(goalId, goalName);
            System.out.println("수정되었습니다.");
            cGoalMenu(goalId, sc, goalService, taskService);

        } else if (menu == 2) {                                                                                       // 범위
            System.out.println("목표의 공개 범위를 입력하세요. (PUBILC, FOLLOWER, PRIVATE)");
            String goalRange = sc.nextLine();
            goalService.updateGoalRange(goalId,Range.valueOf(goalRange));
            System.out.println("수정되었습니다.");
            cGoalMenu(goalId, sc, goalService, taskService);

        } else if (menu == 3) {                                                                                      // 삭제
            goalService.deleteGoal(goalId);
            System.out.println("삭제되었습니다.");

        } else if (menu == 4) {                                                                                      // 할 일 관련
            taskMenu(goalId, sc, goalService, taskService);

        } else if (menu == 99) {
            return;
        }

    }

    public static void taskMenu(Long goalId, Scanner sc, GoalService goalService, TaskService taskService){
        System.out.println("-----------------------------------------");
        System.out.println("1 : 할 일 보기, 2 : 할 일 등록, 3 : 할 일 선택");
        System.out.println("-----------------------------------------");

        int menu = sc.nextInt();
        sc.nextLine();

        if (menu == 1) {                                                                                            // 할 일 보기
            List<Task> tasks = taskService.getAllTaskByGoalId(goalId);

            for (Task task : tasks){
                System.out.printf("%d %s %s %s %n",task.getTaskId(), task.getName(), task.getDate(), task.isState());
            }

            taskMenu(goalId, sc, goalService, taskService);

        } else if (menu == 2) {                                                                                    // 할 일 등록
            System.out.println("등록할 할 일을 입력하세요. (할 일 이름 0000-00-00)");
            String taskName = sc.next();
            String dateString = sc.next();
            sc.nextLine();
            taskService.addTask(goalId, taskName, dateString);
            System.out.println("등록되었습니다.");
            taskMenu(goalId, sc, goalService, taskService);

        } else if (menu == 3) {                                                                                     // 할 일 선택
            System.out.println("선택하실 할 일의 TaskId를 입력하세요.");
            cTaskMenu(goalId, sc.nextLong(), sc, goalService, taskService);

        } else if (menu == 99) {
            return;
        }
    }

    public static void cTaskMenu(Long goalId, Long taskId, Scanner sc, GoalService goalService, TaskService taskService){
        System.out.println("-----------------------------------------");
        System.out.println("1 : 이름 설정, 2 : 완료 여부, 3 : 삭제");
        System.out.println("-----------------------------------------");

        int menu = sc.nextInt();
        sc.nextLine();

        if (menu == 1) {                                                                                              // 할 일 이름
            System.out.println("할 일의 새로운 이름을 입력하세요.");
            String taskName = sc.nextLine();
            taskService.updateTaskName(taskId, taskName);
            System.out.println("수정되었습니다.");
            cTaskMenu(goalId, taskId, sc, goalService, taskService);

        } else if (menu == 2) {                                                                                       // 완료 여부
            System.out.println("할 일의 완료여부를 입력하세요. (완료 : true, 미완료 : false)");
            boolean taskState = sc.nextBoolean();
            taskService.updateTaskState(taskId, taskState);
            System.out.println("수정되었습니다.");
            cTaskMenu(goalId, taskId, sc, goalService, taskService);

        } else if (menu == 3) {                                                                                      // 삭제
            taskService.deleteTask(taskId);
            System.out.println("삭제되었습니다.");

        } else if (menu == 99) {
            return;
        }
    }

    public static void dateMenu(Scanner sc, GoalService goalService, TaskService taskService){
        System.out.println("날짜를 입력하세요. (0000-00-00)");
        LocalDate date = LocalDate.parse(sc.nextLine());
        System.out.println("해당 날짜에 남은 할 일은 " + taskService.getLeftTask(date) + "개 입니다.");
    }
}

