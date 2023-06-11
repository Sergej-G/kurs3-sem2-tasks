package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListTask implements TaskDAO {
    private List<Task> tasks;

    public ListTask(int size) {
        tasks = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            Task task = new Task(
                    i + 1,
                    "Task " + (i + 1),
                    randomTime(random),
                    "Context for task " + (i + 1)
            );
            tasks.add(task);
        }
    }
    private String randomTime(Random random) {
        int hours = random.nextInt(24);
        int minutes = random.nextInt(60);
        return String.format("%02d:%02d", hours, minutes);
    }


    public List<Task> getTasks() {
        return tasks;
    }

    @Override
    public List<Task> getAllTasks() {
        return tasks;
    }

    @Override
    public Task getTaskById(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }


    @Override
    public void addTask(Task task) {
        tasks.add(task);
    }

    @Override
    public void updateTask(Task task) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId() == task.getId()) {
                tasks.set(i, task);
                return;
            }
        }
    }

    @Override
    public void deleteTask(int id) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId() == id) {
                tasks.remove(i);
                return;
            }
        }
    }

    @Override
    public void drop() {
        for (int i = 0; i < tasks.size(); i++) {
            tasks.remove(i);
        }
    }
}
