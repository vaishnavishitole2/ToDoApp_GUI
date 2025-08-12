package com.example.todo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;

/**
 * A simple To-Do list application built using Java Swing.
 * This class extends JFrame to create the main application window.
 */
public class ToDoAppGUI extends JFrame {

    // Declare the components that need to be accessed by other methods (e.g., event listeners)
    private DefaultListModel<String> listModel;
    private JList<String> taskList;
    private JTextField taskInputField;

    /**
     * The constructor for the ToDoAppGUI class.
     * This method initializes and configures all GUI components.
     */
    public ToDoAppGUI() {
        // --- Step 1: Set up the main application window (JFrame) ---
        setTitle("My To-Do List");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window on the screen

        // --- Step 2: Create a main container panel with a BorderLayout ---
        // A JPanel is used to organize other components. BorderLayout is ideal
        // for placing components in the NORTH, CENTER, and SOUTH regions.
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10)); // 10-pixel gaps between components
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // 10-pixel padding around the edges

        // --- Step 3: Create the components for adding a new task ---
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout(5, 5)); // 5-pixel gaps for the input area
        
        taskInputField = new JTextField();
        taskInputField.setFont(new Font("Arial", Font.PLAIN, 16));
        inputPanel.add(taskInputField, BorderLayout.CENTER);

        JButton addButton = new JButton("Add Task");
        addButton.setFont(new Font("Arial", Font.BOLD, 14));
        inputPanel.add(addButton, BorderLayout.EAST);
        
        // Add the input panel to the top of the main window
        mainPanel.add(inputPanel, BorderLayout.NORTH);

        // --- Step 4: Create the task list and make it scrollable ---
        // DefaultListModel is the data structure that holds our tasks.
        // JList is the component that visually displays the tasks.
        listModel = new DefaultListModel<>();
        taskList = new JList<>(listModel);
        taskList.setFont(new Font("Arial", Font.PLAIN, 16));

        // JScrollPane adds scrollbars to a component (like JList) when its contents are too large.
        JScrollPane scrollPane = new JScrollPane(taskList);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // --- Step 5: Create the delete button ---
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        JButton deleteButton = new JButton("Delete Task");
        deleteButton.setFont(new Font("Arial", Font.BOLD, 14));
        buttonPanel.add(deleteButton);

        // Add the button panel to the bottom of the main window
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // --- Step 6: Add all panels to the main JFrame ---
        add(mainPanel);

        // --- Step 7: Define event handling for the buttons ---
        
        // ActionListener for the "Add Task" button
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String task = taskInputField.getText().trim();
                if (!task.isEmpty()) {
                    listModel.addElement(task); // Add the task to the data model
                    taskInputField.setText(""); // Clear the input field
                }
            }
        });
        
        // ActionListener for the "Delete Task" button
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = taskList.getSelectedIndex();
                if (selectedIndex != -1) {
                    listModel.remove(selectedIndex); // Remove the selected task from the data model
                } else {
                    // Show an error message if no task is selected
                    JOptionPane.showMessageDialog(ToDoAppGUI.this,
                                "Please select a task to delete.",
                                "No Task Selected",
                                JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        // --- Step 8: Make the GUI visible ---
        setVisible(true);
    }

    /**
     * The main method is the entry point of the application.
     * It ensures the GUI is created and run on the Event Dispatch Thread (EDT),
     * which is a best practice for all Swing applications.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ToDoAppGUI());
    }
}