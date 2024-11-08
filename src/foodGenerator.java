import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class foodGenerator implements ActionListener {

    //declaring global variables
    JFrame frame;
    JTextField textField;
    JLabel lDirections;
    JLabel lRandomRest;
    JLabel lEndOfList;
    JLabel lOneInput;
    JButton bSubmit;
    JButton bReRoll;
    JButton bStartOver;

    Font boldFont = new Font("Montserrat", Font.BOLD, 20);
    Font italicFont = new Font("Montserrat", Font.ITALIC, 20);
    Font font = new Font("Montserrat", Font.PLAIN, 20);

    String sTextField;
    ArrayList<String> arrayList;
    Random random = new Random();

    foodGenerator() {

        textField = new JTextField();
        textField.setBounds(45, 40, 400, 40);
        textField.setFont(italicFont);

        lDirections = new JLabel();
        lDirections.setBounds(50, 10, 500, 30);
        lDirections.setText("Enter list of restaurants:");
        lDirections.setFont(font);

        lRandomRest = new JLabel();
        lRandomRest.setBounds(50, 85, 400, 30);
        lRandomRest.setFont(font);

        lEndOfList = new JLabel();
        lEndOfList.setText("<html>You've re-rolled through the whole list. <br> Please start over and be less picky.</html>");
        lEndOfList.setBounds(50, 120, 425, 90);
        lEndOfList.setFont(font);

        lOneInput = new JLabel();
        lOneInput.setText("<html>You only entered one restaurant. <br> Please start over and be less picky.</html>");
        lOneInput.setBounds(50, 120, 425, 90);
        lOneInput.setFont(font);

        bSubmit = new JButton("Submit");
        bSubmit.setBounds(475, 40, 150, 40);
        bSubmit.addActionListener(this);
        bSubmit.setFont(boldFont);

        bReRoll = new JButton("Re-roll");
        bReRoll.setBounds(475, 40, 150, 40);
        bReRoll.addActionListener(this);
        bReRoll.setFont(boldFont);

        bStartOver = new JButton("Start Over");
        bStartOver.setBounds(475, 148, 150, 40);
        bStartOver.addActionListener(this);
        bStartOver.setFont(boldFont);

        frame = new JFrame("Random Restaurant Generator");
        frame.setSize(750, 600);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(textField);
        frame.add(lDirections);
        frame.add(bSubmit);
        frame.setVisible(true);

        while (true) {
            if (textField.getText().equals("")) {
                bSubmit.setEnabled(false);
            } else {
                bSubmit.setEnabled(true);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bSubmit) {

            bSubmit.setEnabled(false);

            sTextField = textField.getText();
            String[] restaurantsList = sTextField.split(", ");

            arrayList = new ArrayList<>(Arrays.asList(restaurantsList));

            int randomIndex = random.nextInt(arrayList.size());

            if (arrayList.size() == 1) {
                frame.add(lRandomRest);
                lRandomRest.setText("You should go to " + arrayList.get(randomIndex));
                frame.add(bStartOver);
                frame.add(lOneInput);
            } else {
                frame.add(lRandomRest);
                lRandomRest.setText("You should go to " + arrayList.get(randomIndex));
                arrayList.remove(randomIndex);
                frame.add(bReRoll);
                bSubmit.setVisible(false);
                frame.repaint();
            }
        }

        if (e.getSource() == bReRoll) {
            int randomIndex = random.nextInt(arrayList.size());
            lRandomRest.setText("You should go to " + arrayList.get(randomIndex));
            arrayList.remove(randomIndex);

            if (arrayList.isEmpty()) {
                bReRoll.setEnabled(false);
                frame.add(lEndOfList);
                frame.add(bStartOver);
                frame.repaint();
            }
        }

        if (e.getSource() == bStartOver) {
            textField.setText("");
            bSubmit.setEnabled(true);
            bSubmit.setVisible(true);

            frame.remove(bReRoll);
            frame.remove(lRandomRest);
            frame.remove(lEndOfList);
            frame.remove(lOneInput);
            frame.remove(bStartOver);
            bReRoll.setEnabled(true);
            frame.repaint();
        }
    }
}