package GUI;

import javax.swing.*;
import java.awt.*;

public class ProgramFrame extends JFrame {

    public ProgramFrame() {

        Program_1 pr_1 = new Program_1(6);
        JPanel questionPanel = pr_1.getQuestionPanel();
        JPanel answerPanel = pr_1.getAnswerPanel();
        JPanel textPanel = pr_1.getTextPanel();
        JPanel confirmPanel = pr_1.getConfirmPanel();

        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        add(questionPanel);
        add(textPanel);
        add(answerPanel);
        add(confirmPanel);

        setTitle("Raven's Matrices Test by Michal Maksanty");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //setSize(1200, 700);
        setResizable(false);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);

    }

}
