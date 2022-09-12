import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame {
    public JButton[] numButtons;
    public JButton[] operButtons;
    public JButton[] memoryButtons;
    public String stroka = "";
    private String memory = "0";
    public String sim;
    public JLabel memLabel;
    public JLabel label;
    public JTextField textField2;

    Calculator() {
        JFrame frame = new JFrame("Калькулятор");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        setDefaultLookAndFeelDecorated(true);
        frame.setBounds(100, 100, 400, 800);
        frame.setLayout(null);
        Font f = new Font(Font.DIALOG, Font.BOLD | Font.ITALIC, 20);
        Font f1 = new Font(Font.DIALOG, Font.BOLD, 40);
        frame.setVisible(true);

        JPanel jPanel = new JPanel();
        jPanel.setBounds(5, 5, 380, 800);
        jPanel.setVisible(true);
        jPanel.setBackground(Color.lightGray);
        jPanel.setLayout(null);
        frame.add(jPanel);

        label = new JLabel();
        label.setBounds(80, 10, 240, 25);
        label.setVisible(true);
        label.setBackground(Color.WHITE);
        label.setHorizontalAlignment(JTextField.RIGHT);
        label.setBorder(null);
        jPanel.add(label);

        memLabel = new JLabel();
        memLabel.setBounds(20, 10, 50, 25);
        memLabel.setVisible(true);
        memLabel.setBackground(Color.lightGray);
        memLabel.setHorizontalAlignment(JTextField.RIGHT);
        memLabel.setBorder(null);
        jPanel.add(memLabel);

        textField2 = new JTextField();
        textField2.setBounds(20, 60, 340, 50);
        textField2.setVisible(true);
        textField2.setFont(f1);
        textField2.setBackground(Color.lightGray);
        textField2.setHorizontalAlignment(JTextField.RIGHT);
        textField2.setBorder(null);
        jPanel.add(textField2);
        jPanel.repaint();
        memoryButtons = new JButton[5];
        for (int i = 0; i <= 4; i++) {
            memoryButtons[i] = new JButton();
            memoryButtons[i].setBounds(i * 70 + 10, 120, 70, 50);
            memoryButtons[i].setText(textOfMemButton(i));
            memoryButtons[i].setVisible(true);
            int finalI = i;
            memoryButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (memoryButtons[finalI].getText().equals("MC")) {
                        memory = "0";
                        System.out.println(memory);
                    } else if (memoryButtons[finalI].getText().equals("MR")) {
                        textField2.setText(memory);
                    } else if (memoryButtons[finalI].getText().equals("M+")) {
                        memory = Double.toString(Double.parseDouble(memory) + Double.parseDouble(textField2.getText()));
                    } else if (memoryButtons[finalI].getText().equals("M-")) {
                        memory = Double.toString(Double.parseDouble(memory) - Double.parseDouble(textField2.getText()));
                    } else if (memoryButtons[finalI].getText().equals("MS")) {
                        memLabel.setText(memory);
                    }
                }
            });
            jPanel.add(memoryButtons[i]);
        }
        jPanel.repaint();

        numButtons = new JButton[18];
        int n = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                int k = i + j + n;
                numButtons[k] = new JButton();
                numButtons[k].setBounds(j * 5 + j * 80 + 20, i * 80 + 220, 80, 80);
                numButtons[k].setText(textOfNumButton(k));
                numButtons[k].setFont(f);
                numButtons[k].setVisible(true);
                numButtons[k].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (!(numButtons[k].getText().equals("="))) {
                            sim = numButtons[k].getText();
                            stroka = stroka + sim;
                            label.setText(stroka);
                            textField2.setText(stroka);
                        } else if (!(textField2.getText().isEmpty())) {
                            sim = numButtons[k].getText();
                            float res = (float) new LogicCalc().resultOperation(stroka);
                            textField2.setText(Float.toString(res));
                            label.setText(stroka + sim);
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
            operButtons[i].setBounds(280, i * 80 + 220, 80, 80);
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
                    } else if (!textField2.getText().isEmpty()) {
                        stroka = Float.toString(Float.parseFloat(textField2.getText()));
                        sim = operButtons[finalI].getText();
                        stroka = stroka + sim;
                    }
                }
            });
            jPanel.add(operButtons[i]);
        }

        JButton clearButton = new JButton();
        clearButton.setBounds(20, 540, 150, 80);
        clearButton.setText("Очистить");
        clearButton.setFont(f);
        clearButton.setVisible(true);
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField2.setText("");
                label.setText("");
                stroka = "";
            }
        });
        jPanel.add(clearButton);
        jPanel.repaint();
    }

    private String textOfMemButton(int num) {
        switch (num) {
            case 0:
                return "MC";

            case 1:
                return "MR";
            case 2:
                return "M+";
            case 3:
                return "M-";
            case 4:
                return "MS";
            default:
                return "";
        }
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


