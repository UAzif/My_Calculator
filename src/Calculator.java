import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame {
    JFrame frame;
    JButton[] numButtons;
    JButton[] operButtons;
    public JTextField textField1;
    public JTextField textField2;
    public String stroka = "";
    public String sim;

    Calculator() {
        frame = new JFrame("Калькулятор");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        setDefaultLookAndFeelDecorated(true);
        frame.setBounds(100, 100, 400, 620);
        frame.setLayout(null);
        Font f = new Font(Font.DIALOG, Font.BOLD | Font.ITALIC, 20);
        Font f1 = new Font(Font.DIALOG, Font.BOLD, 40);
        frame.setVisible(true);

        JPanel jPanel = new JPanel();
        jPanel.setBounds(5, 5, 380, 600);
        jPanel.setVisible(true);
        jPanel.setBackground(Color.lightGray);
        jPanel.setLayout(null);
        frame.add(jPanel);

        textField1 = new JTextField();
        textField1.setBounds(20, 10, 340, 25);
        textField1.setVisible(true);
        textField1.setBackground(Color.lightGray);
        textField1.setHorizontalAlignment(JTextField.RIGHT);
        textField1.setBorder(null);
        jPanel.add(textField1);

        textField2 = new JTextField();
        textField2.setBounds(20, 60, 340, 50);
        textField2.setVisible(true);
        textField2.setFont(f1);
        textField2.setBackground(Color.lightGray);
        textField2.setHorizontalAlignment(JTextField.RIGHT);
        textField2.setBorder(null);
        jPanel.add(textField2);
        jPanel.repaint();

        numButtons = new JButton[18];
        int n = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                int k = i + j + n;
                numButtons[k] = new JButton();
                numButtons[k].setBounds(j * 5 + j * 80 + 20, i * 80 + 120, 80, 80);
                numButtons[k].setText(textOfNumButton(k));
                numButtons[k].setFont(f);
                numButtons[k].setVisible(true);
                numButtons[k].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if ((numButtons[k].getText() != "=")) {
                            sim = numButtons[k].getText();
                            stroka = stroka + sim;
                            System.out.println(" Тут " + stroka);
                            textField1.setText(stroka);
                            textField2.setText(stroka);
                        } else if (!(stroka.isEmpty())) {
                            sim = numButtons[k].getText();
                            float res = (float) new LogicCalc().resultOperation(stroka);
                            textField2.setText(Float.toString(res));
                            textField1.setText(stroka + sim);
                            System.out.println("Это туе " + stroka + sim);
                            stroka = "";
                        }
                    }
                });
                jPanel.add(numButtons[j + i + n]);
                jPanel.repaint();
            }
            n += 3;
        }
        operButtons = new JButton[4];
        for (int i = 0; i < 4; i++) {
            operButtons[i] = new JButton();
            operButtons[i].setBounds(280, i * 80 + 120, 80, 80);
            operButtons[i].setVisible(true);
            operButtons[i].setFont(f);
            operButtons[i].setText(textOfOperButton(i));
            int finalI = i;

            operButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!stroka.isEmpty()) {
                        if (endsWithOperation(stroka)) {
                            stroka = stroka.substring(0, stroka.length() - 1);
                            sim = operButtons[finalI].getText();
                            stroka = stroka + sim;
                        } else {
                            sim = operButtons[finalI].getText();
                            stroka = stroka + sim;
                        }
                    } else {
                      //  textField2.setText("0");
                        stroka = Float.toString(Float.parseFloat(textField2.getText()));
                        sim = operButtons[finalI].getText();
                        stroka = stroka + sim;

                    }
                }
            });
            jPanel.add(operButtons[i]);
        }
        JButton clearButton = new JButton();
        clearButton.setBounds(20, 440, 150, 80);
        clearButton.setText("Очистить");
        clearButton.setFont(f);
        clearButton.setVisible(true);
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField2.setText("");
                textField1.setText("");
                stroka = "";
            }
        });
        jPanel.add(clearButton);
        jPanel.repaint();
    }

    private String textOfOperButton(int num) {
        switch (num) {
            case 0:
                return "+";
            case 1:
                return "-";
            case 2:
                return "*";
            case 3:
                return "/";
            default:
                return "";
        }
    }

    public boolean endsWithOperation(String str) {
        return (str.endsWith("+") || str.endsWith("-") || str.endsWith("*") || str.endsWith("/"));
    }

    public String textOfNumButton(int num) {
        switch (num) {
            case 0:
                return "1";
            case 1:
                return "2";
            case 2:
                return "3";
            case 4:
                return "4";
            case 5:
                return "5";
            case 6:
                return "6";
            case 8:
                return "7";
            case 9:
                return "8";
            case 10:
                return "9";
            case 12:
                return "0";
            case 13:
                return ".";
            case 14:
                return "=";
            default:
                return "";
        }
    }
}


