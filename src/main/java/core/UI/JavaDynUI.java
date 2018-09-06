package core.UI;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class JavaDynUI extends JFrame {

    private static JavaDynUI myFrame;
    private static int countMe = 0;
    private JPanel mainPanel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(JavaDynUI::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        myFrame = new JavaDynUI();
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.prepareUI();
        myFrame.pack();
        myFrame.setVisible(true);
    }

    private void prepareUI() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JLabel PlatformName = new JLabel();
        PlatformName.setText("Select Platform");
        mainPanel.add(PlatformName);

        JComboBox<String> Platform = new JComboBox<>();
        Platform.addItem("Android");
        Platform.addItem("iOS");
        mainPanel.add(Platform);

        JComboBox<String> AndroidDevices = new JComboBox<>();
        AndroidDevices.addItem("LG");
        AndroidDevices.addItem("Pixel");

        JButton buttonAdd = new JButton("Add subPanel");
        buttonAdd.addActionListener(e -> {
            mainPanel.add(new subPanel());
            myFrame.pack();
        });

        JButton buttonRemoveAll = new JButton("Remove All");
        buttonRemoveAll.addActionListener(e -> {
            mainPanel.removeAll();
            myFrame.pack();
        });

        getContentPane().add(mainPanel, BorderLayout.CENTER);
        getContentPane().add(buttonAdd, BorderLayout.PAGE_START);
        getContentPane().add(buttonRemoveAll, BorderLayout.PAGE_END);
    }

    private class subPanel extends JPanel {

        subPanel me;

        subPanel() {
            super();
            me = this;
            JLabel myLabel = new JLabel("Hello subPanel(): " + countMe++);
            add(myLabel);
            JButton myButtonRemoveMe = new JButton("remove me");
            myButtonRemoveMe.addActionListener(e -> {
                me.getParent().remove(me);
                myFrame.pack();
            });
            add(myButtonRemoveMe);
        }
    }
}
