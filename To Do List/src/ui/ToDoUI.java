package ui;

import model.TaskManager;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ToDoUI {
    private TaskManager taskManager;
    private JFrame frame;
    private JTextField taskInput;
    private DefaultListModel<String> taskModel;
    private JList<String> taskList;
    private JComboBox<String> listSelector;

    private boolean isDark = false;

    public ToDoUI(String initialList) {
        setup(initialList);
    }

    private void setup(String listName) {
        taskManager = new TaskManager(listName);
        String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        frame = new JFrame("To-Do List - " + today + " - " + listName);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());
        taskInput = new JTextField();
        JButton addButton = new JButton("Add");
        topPanel.add(taskInput, BorderLayout.CENTER);
        topPanel.add(addButton, BorderLayout.EAST);

        JPanel controls = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton removeButton = new JButton("Remove Selected");
        JButton clearButton = new JButton("Clear All");
        JButton themeButton = new JButton("🎨 Theme");
        String[] listNames = {"Personal", "Work", "Study"};
        listSelector = new JComboBox<>(listNames);
        listSelector.setSelectedItem(listName);

        controls.add(removeButton);
        controls.add(clearButton);
        controls.add(themeButton);
        controls.add(new JLabel("List:"));
        controls.add(listSelector);

        taskModel = new DefaultListModel<>();
        for (String task : taskManager.getTasks()) {
            taskModel.addElement(task);
        }

        taskList = new JList<>(taskModel);
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(new JScrollPane(taskList), BorderLayout.CENTER);
        frame.add(controls, BorderLayout.SOUTH);

        addButton.addActionListener(e -> {
            String task = taskInput.getText().trim();
            if (!task.isEmpty()) {
                taskManager.addTask(task);
                taskModel.addElement(task);
                taskInput.setText("");
                Toolkit.getDefaultToolkit().beep();
            }
        });

        removeButton.addActionListener(e -> {
            int index = taskList.getSelectedIndex();
            if (index != -1) {
                taskManager.removeTask(index);
                taskModel.remove(index);
            }
        });

        clearButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to clear all tasks?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                taskManager.clearTasks();
                taskModel.clear();
            }
        });

        themeButton.addActionListener(e -> toggleTheme());

        listSelector.addActionListener(e -> {
            String selectedList = (String) listSelector.getSelectedItem();
            frame.dispose();
            new ToDoUI(selectedList);
        });

        JOptionPane.showMessageDialog(frame, "📅 Today's List: " + listName, "Daily Reminder", JOptionPane.INFORMATION_MESSAGE);

        frame.setVisible(true);
    }

    private void toggleTheme() {
        String[] options = {"Light", "Dark", "Mint", "Fire"};
        String theme = (String) JOptionPane.showInputDialog(
                frame, "Choose a theme:", "Theme Selector",
                JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

        Color bg, fg;
        switch (theme) {
            case "Dark":
                bg = Color.DARK_GRAY; fg = Color.WHITE; break;
            case "Mint":
                bg = new Color(204, 255, 229); fg = Color.BLACK; break;
            case "Fire":
                bg = new Color(255, 102, 102); fg = Color.BLACK; break;
            default:
                bg = Color.WHITE; fg = Color.BLACK; break;
        }

        frame.getContentPane().setBackground(bg);
        taskList.setBackground(bg);
        taskList.setForeground(fg);
        taskInput.setBackground(bg);
        taskInput.setForeground(fg);
    }
}
