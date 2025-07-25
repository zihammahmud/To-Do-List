package model;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class TaskManager {
    private ArrayList<String> tasks;
    private File taskFile;
    private String listName;
    private final int KEY = 7;

    public TaskManager(String listName) {
        this.listName = listName;
        tasks = new ArrayList<>();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String folderPath = System.getProperty("user.home") + File.separator + "todo-data" + File.separator + listName;
        File folder = new File(folderPath);
        if (!folder.exists()) folder.mkdirs();
        taskFile = new File(folder, date + ".txt");
        loadTasks();
    }

    private void loadTasks() {
        if (taskFile.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(taskFile))) {
                String line;
                while ((line = br.readLine()) != null) {
                    tasks.add(decrypt(line));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addTask(String task) {
        tasks.add(task);
        saveTasks();
    }

    public void removeTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
            saveTasks();
        }
    }

    public void clearTasks() {
        tasks.clear();
        saveTasks();
    }

    public ArrayList<String> getTasks() {
        return tasks;
    }

    private void saveTasks() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(taskFile))) {
            for (String task : tasks) {
                bw.write(encrypt(task));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String encrypt(String input) {
        StringBuilder sb = new StringBuilder();
        for (char c : input.toCharArray()) sb.append((char)(c + KEY));
        return sb.toString();
    }

    private String decrypt(String input) {
        StringBuilder sb = new StringBuilder();
        for (char c : input.toCharArray()) sb.append((char)(c - KEY));
        return sb.toString();
    }
}
