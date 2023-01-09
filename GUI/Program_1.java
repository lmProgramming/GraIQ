package GUI;

import Figures.Circle;
import Figures.Figure;
import Figures.Rectangle;
import Figures.Triangle;
import Tests.Test_1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class Program_1 implements ActionListener {


//    add(new Panel());
//    setTitle("Raven's Matrices Test by Michal Maksanty");
//    setDefaultCloseOperation(EXIT_ON_CLOSE);
//    setResizable(false);
//    pack();
//    setVisible(true);
//    setLocationRelativeTo(null);

    private final int WIDTH = 1200;
    private final int HEIGHT = 700;
    private final int PIC_WIDTH = 150;
    private final int PIC_HEIGHT = 100;
    private final int answer_nb = 6;
    private JPanel questionPanel = new JPanel(new FlowLayout());
    private JPanel textPanel = new JPanel(new BorderLayout());
    private JPanel answerPanel = new JPanel(new FlowLayout());
    private JPanel confirmPanel = new JPanel();
    private JPanel[] questions;
    private JPanel[] answers;
    private JPanel[] buttons;
    private ButtonGroup buttonGroup = new ButtonGroup();
    private JPanel good_answer = new JPanel();
    private Figure figure_a;
    private Figure figure_b;
    private int amount_a;
    private int amount_b;

    public Program_1(int amount) {

        //textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));

//        setPreferredSize(new Dimension(WIDTH, HEIGHT));
//        setFocusable(true);
        //questionPanel.setMaximumSize(new Dimension(WIDTH, HEIGHT/3));
        //answerPanel.setPreferredSize(new Dimension(200, HEIGHT/3 * 2));

        questions = new JPanel[amount];
        answers = new JPanel[answer_nb];
        buttons = new JPanel[answer_nb];
        good_answer.setLayout(new BoxLayout(good_answer, BoxLayout.Y_AXIS));

        Test_1 test = new Test_1();

        figure_a = test.getFigure_a();
        figure_b = test.getFigure_b();

        amount_a = test.getAmount_a(); //TU MUSISZ ZMIENIC BO NA ODWROT JEST
        //System.out.println("AMOUNT a:" + amount_a);
        amount_b = test.getAmount_b();
        //System.out.println("AMOUNT b:" + amount_b);


        for (int i = 0; i < questions.length; i++) {

            questions[i] = new JPanel();
            //questions[i].setSize(new Dimension(PIC_WIDTH, PIC_HEIGHT)); //bylo setSize
            if (i+1 != questions.length)
                questions[i].add(new MyGraphics(amount_a, amount_b));
            else {
                good_answer.add(new MyGraphics(amount_a, amount_b));
                questions[i].add(new MyGraphics(0, 0));
            }
            amount_a++;
            amount_b--;
            questionPanel.add(questions[i]);

        }

        // tutaj answer for loop:
        JLabel text = new JLabel("Choose correct answer:");

        //text.setMinimumSize(new Dimension(700, 30));
        textPanel.setPreferredSize(new Dimension(500, 100));
        text.setFont(new Font("Arial", Font.PLAIN, 30));
        text.setHorizontalAlignment(SwingConstants.CENTER);
        textPanel.add(BorderLayout.SOUTH, text);

        Random random = new Random();
        int good_answer_index = random.nextInt(0, answer_nb);

        for (int i = 0; i < answer_nb; i++) {

            answers[i] = new JPanel();
            answers[i].setLayout(new BoxLayout(answers[i], BoxLayout.Y_AXIS));

            buttons[i] = new JPanel();
            buttons[i].add(new JRadioButton(String.valueOf((char) (65+i)))); //A - 65
            buttonGroup.add((JRadioButton) buttons[i].getComponent(0));
            buttons[i].setPreferredSize(new Dimension(PIC_WIDTH, 40));

            //answers[i].setSize(new Dimension(PIC_WIDTH, PIC_HEIGHT));

            if (i == good_answer_index)
                answers[i] = good_answer;//good answer
            else
                answers[i].add(new MyGraphics(random.nextInt(0, 10), random.nextInt(0, 10)));

            answers[i].add(buttons[i]);
            answerPanel.add(answers[i]);

        }

        //button potwierdz
        JButton confirm_button = new JButton("Confirm");
        confirm_button.addActionListener(this);
        confirmPanel.add(confirm_button);
    }

    public JPanel getQuestionPanel() {
        return questionPanel;
    }

    public JPanel getAnswerPanel() {
        return answerPanel;
    }

    public JPanel getTextPanel() {
        return textPanel;
    }

    public JPanel getConfirmPanel() {
        return confirmPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < answer_nb; i++) {
            if (answers[i] == good_answer)
                if (((JRadioButton) (buttons[i].getComponent(0))).isSelected())
                    System.out.println("CORRECT ANSWER!");
                else
                    System.out.println("INCORRECT ANSWER!");
        }
    }

    public class MyGraphics extends JComponent {

        private JPanel panel;
        private final ArrayList<int[]> figures_position = new ArrayList<>();
        private static final int size = 10;
        private int am_a;
        private int am_b;

        public MyGraphics(int am_a, int am_b) {
            setPreferredSize(new Dimension(PIC_WIDTH, PIC_HEIGHT));
            //this.panel = panel;
            this.am_a = am_a;
            this.am_b = am_b;
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            //g.fillRect(0, 50, 200, 100);
            //g.drawRect(panel.getX() + 30, panel.getY() + 30, PIC_WIDTH-60, PIC_HEIGHT-60);
            g.drawRect(0, 0, PIC_WIDTH-1, PIC_HEIGHT-1);

            drawSelector(g, figure_a, am_a);
            drawSelector(g, figure_b, am_b);
//            amount_a++;
//            amount_b--;
            //System.out.println(panel.getX() + " " + panel.getY());

        }

        public void drawSelector(Graphics g, Figure figure, int amount) {
            Random random = new Random();
            int curr_x = 0;
            int curr_y = 0;
            double radius = (Math.sqrt(2)*size)/2;
            //System.out.println("RADIUS: " + radius); //r = 7.071
            boolean good_pos;

            for (int i = 0; i < amount; i++) {

                good_pos = false;

                while (!good_pos) {
                    curr_x = random.nextInt(5, PIC_WIDTH-size-5);
                    curr_y = random.nextInt(5, PIC_HEIGHT-size-5);
                    good_pos = true;
                    for (int[] f_pos : figures_position) {
                        if (Math.sqrt(Math.pow(curr_x - f_pos[0], 2) + Math.pow(curr_y - f_pos[1], 2)) <= 2*radius) {
                            good_pos = false;
                            break;
                        }
                    }
                }

                figure.drawComponent(g, curr_x, curr_y, size, size);
                figures_position.add(new int[]{curr_x, curr_y});

            }
        }

    }
}
