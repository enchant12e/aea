import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LauncherUI extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton launchButton;

    public LauncherUI() {
        setTitle("Minecraft Launcher");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(10, 10, 80, 25);
        panel.add(usernameLabel);

        usernameField = new JTextField(20);
        usernameField.setBounds(100, 10, 160, 25);
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 40, 80, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField(20);
        passwordField.setBounds(100, 40, 160, 25);
        panel.add(passwordField);

        launchButton = new JButton("Launch");
        launchButton.setBounds(10, 80, 250, 25);
        panel.add(launchButton);

        launchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                try {
                    String token = Auth.authenticate(username, password);
                    launchGame(token);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Authentication Failed");
                }
            }
        });

        add(panel);
    }

    private void launchGame(String token) {
        // Aquí es donde se lanzarían los archivos de Minecraft
        JOptionPane.showMessageDialog(null, "Launching Minecraft with token: " + token);
        // Puedes añadir más código para inicializar el juego y configurar los parámetros.
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LauncherUI launcherUI = new LauncherUI();
            launcherUI.setVisible(true);
        });
    }
}
