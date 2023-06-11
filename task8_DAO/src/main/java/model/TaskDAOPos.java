package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDAOPos implements TaskDAO {
    private Connection conn;

    public TaskDAOPos() {

        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/task8DAO", "postgres", "183729");
            /*Statement stmt = conn.createStatement();

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS TASK (" +
                    "id INTEGER PRIMARY KEY AUTO_INCREMENT," +
                    "name VARCHAR(255)," +
                    "time VARCHAR(255)," +
                    "status VARCHAR(255))");*/

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM tasks");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Task task = new Task(rs.getInt("id"),
                        //rs.getInt("id");
                        rs.getString("name"),
                        rs.getString("time"),
                        rs.getString("status"));
                tasks.add(task);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    @Override
    public Task getTaskById(int id) {
        Task task = null;
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM tasks WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                task = new Task(rs.getInt("id"),
                        //rs.getInt("id"));
                        rs.getString("name"),
                        rs.getString("time"),
                        rs.getString("status"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return task;
    }

    @Override
    public void addTask(Task task) {
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO tasks (name, time, status) VALUES (?, ?, ?)");
            ps.setString(1, task.getName());
            ps.setString(2, task.getTime());
            ps.setString(3, task.getStatus());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateTask(Task task) {
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE tasks SET name = ?, time = ?, status = ? WHERE id = ?");
            ps.setString(1, task.getName());
            ps.setString(2, task.getTime());
            ps.setString(3, task.getStatus());
            ps.setInt(4, task.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteTask(int id) {
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM tasks WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void drop() {
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM tasks");
            ps.executeUpdate();

            PreparedStatement as = conn.prepareStatement("ALTER TABLE tasks ALTER COLUMN id RESTART WITH 1;");
            as.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
