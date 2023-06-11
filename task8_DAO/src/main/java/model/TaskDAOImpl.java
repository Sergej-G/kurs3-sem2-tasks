package model;

import model.Task;
import model.TaskDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDAOImpl implements TaskDAO {
    private Connection conn;

    public TaskDAOImpl() {

        try {
            //Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
            Statement stmt = conn.createStatement();

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS TASK (" +
                    "id INTEGER PRIMARY KEY AUTO_INCREMENT," +
                    "name VARCHAR(255)," +
                    "time VARCHAR(255)," +
                    "status VARCHAR(255))");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM task");
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
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM task WHERE id = ?");
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
            PreparedStatement ps = conn.prepareStatement("INSERT INTO task (name, time, status) VALUES (?, ?, ?)");
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
            PreparedStatement ps = conn.prepareStatement("UPDATE task SET name = ?, time = ?, status = ? WHERE id = ?");
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
            PreparedStatement ps = conn.prepareStatement("DELETE FROM task WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void drop() {
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM task");
            ps.executeUpdate();

            PreparedStatement as = conn.prepareStatement("ALTER TABLE task ALTER COLUMN id RESTART WITH 1;");
            as.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
