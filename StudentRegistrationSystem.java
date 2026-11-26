import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class StudentRegistrationSystem extends JFrame {

    private JTextField nameField, rollField, branchField, emailField;
    private JComboBox<String> genderBox;
    private DefaultTableModel model;

    public StudentRegistrationSystem() {

        setTitle("Student Registration System");
        setSize(750, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel form = new JPanel(new GridLayout(6, 2, 10, 10));
        form.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        nameField = new JTextField();
        rollField = new JTextField();
        branchField = new JTextField();
        emailField = new JTextField();

        genderBox = new JComboBox<>(
            new String[]{"Select Gender", "Male", "Female", "Other"}
        );

        form.add(new JLabel("Student Name:"));
        form.add(nameField);

        form.add(new JLabel("Roll Number:"));
        form.add(rollField);

        form.add(new JLabel("Branch / Class:"));
        form.add(branchField);

        form.add(new JLabel("Email:"));
        form.add(emailField);

        form.add(new JLabel("Gender:"));
        form.add(genderBox);

        JButton registerButton = new JButton("Register");
        JButton resetButton = new JButton("Reset");

        form.add(registerButton);
        form.add(resetButton);

        model = new DefaultTableModel(
            new String[]{"Roll No", "Name", "Branch", "Email", "Gender"}, 0
        );

        JTable table = new JTable(model);
        JScrollPane tableScroll = new JScrollPane(table);

        registerButton.addActionListener(e -> registerStudent());
        resetButton.addActionListener(e -> resetForm());

        add(form, BorderLayout.NORTH);
        add(tableScroll, BorderLayout.CENTER);
    }

    private void registerStudent() {

        String name = nameField.getText().trim();
        String roll = rollField.getText().trim();
        String branch = branchField.getText().trim();
        String email = emailField.getText().trim();
        String gender = (String) genderBox.getSelectedItem();

        if (name.isEmpty() || roll.isEmpty() || branch.isEmpty()
                || email.isEmpty() || "Select Gender".equals(gender)) {

            JOptionPane.showMessageDialog(this,
                    "Please fill all fields.");
            return;
        }

        if (!email.contains("@") || !email.contains(".")) {
            JOptionPane.showMessageDialog(this,
                    "Please enter a valid email address.");
            return;
        }

        for (int i = 0; i < model.getRowCount(); i++) {

            if (roll.equalsIgnoreCase(
                    model.getValueAt(i, 0).toString())) {

                JOptionPane.showMessageDialog(this,
                        "Roll number already registered.");
                return;
            }
        }

        model.addRow(new Object[]{
            roll, name, branch, email, gender
        });

        JOptionPane.showMessageDialog(this,
                "Student registered successfully!");

        resetForm();
    }

    private void resetForm() {

        nameField.setText("");
        rollField.setText("");
        branchField.setText("");
        emailField.setText("");
        genderBox.setSelectedIndex(0);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new StudentRegistrationSystem().setVisible(true);
        });
    }
}