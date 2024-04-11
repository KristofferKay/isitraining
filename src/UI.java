import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI {
    private static String cityInput;
    private App app;
    private JFrame frame;
    private JTextArea appConsoleOutput;
    private JTextField userInputField;

    public void startApp() {
        startUI();
        app = new App(cityInput);
    }

    private void startUI() {

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("IsItRaining");

        appConsoleOutput = new JTextArea(10, 40);
        appConsoleOutput.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(appConsoleOutput);

        userInputField = new JTextField(20);
        JButton button = new JButton("Is It Raining?");
        frame.getRootPane().setDefaultButton(button);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cityInput = userInputField.getText();
                app.findUID(cityInput);
                 app.isItRainingInCity();
                userInputField.setText("");
                if (app.isItRainingInCity()){
                    printToConsole("It rains in " + cityInput);
                } else if (app.isItRainingInCity().equals(false)){
                    printToConsole("it does not rain in " + cityInput);
                }
            }

        });

        GroupLayout layout = new GroupLayout(frame.getContentPane());
        frame.getContentPane().setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(scrollPane).addGroup(layout.createSequentialGroup().addComponent(userInputField).addComponent(button)));

        layout.setVerticalGroup(layout.createSequentialGroup().addComponent(scrollPane).addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(userInputField).addComponent(button)));
        frame.pack();
        frame.setVisible(true);
    }

    private void printToConsole(String sytemout) {
        appConsoleOutput.append(sytemout + "\n");
    }

}
