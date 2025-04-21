package create_quiz ;

import create_flashcard.FlashcardStorage;
import create_flashcard.Flashcard;
import component.Toaster;
import Utils.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List ;
import javax.swing.Timer ;

public class QuizPage extends JFrame {
    private final String subject;
    private final List<Flashcard> questions;
    private final JPanel panel;
    private final Toaster toaster;
    private Flashcard currentQuestion;
    private int currentIndex = 0;

    public QuizPage(String subject) {
        this.subject = subject;
        this.questions = new ArrayList<>(FlashcardStorage.getCards(subject));
        Collections.shuffle(questions);

        setTitle("Quiz - " + subject);
        setSize(800, 500);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(UIUtils.COLOR_BACKGROUND);
        setLayout(null);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 800, 500);
        panel.setBackground(UIUtils.COLOR_BACKGROUND);
        add(panel);

        toaster = new Toaster(panel);

        if (questions.size() < 4) {
            toaster.warn("Not enough flashcards for quiz.");
            return;
        }

        addCloseButton();
        loadNextQuestion();
        setVisible(true);
    }

    private void loadNextQuestion() {
        panel.removeAll();
        addCloseButton();

        if (currentIndex >= questions.size()) {
            JLabel done = new JLabel("Quiz Completed!", SwingConstants.CENTER);
            done.setFont(UIUtils.FONT_GENERAL_UI);
            done.setForeground(Color.WHITE);
            done.setBounds(0, 200, 800, 40);
            panel.add(done);
            panel.repaint();
            return;
        }

        currentQuestion = questions.get(currentIndex);
        JLabel qLabel = new JLabel("Q: " + currentQuestion.getQuestion(), SwingConstants.CENTER);
        qLabel.setFont(UIUtils.FONT_GENERAL_UI);
        qLabel.setForeground(Color.WHITE);
        qLabel.setBounds(50, 60, 700, 40);
        panel.add(qLabel);

        List<String> options = generateOptions(currentQuestion.getAnswer());

        int y = 140;
        for (String opt : options) {
            JLabel option = new JLabel(opt, SwingConstants.CENTER);
            option.setFont(UIUtils.FONT_GENERAL_UI);
            option.setOpaque(true);
            option.setBackground(new Color(66, 133, 244));
            option.setForeground(Color.WHITE);
            option.setBounds(250, y, 300, 40);
            option.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            option.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (opt.equals(currentQuestion.getAnswer())) {
                        toaster.success("Correct!");
                    } else {
                        toaster.error("Wrong! Correct: " + currentQuestion.getAnswer());
                    }
                    currentIndex++;
                    Timer timer = new Timer(1000, evt -> loadNextQuestion());
                    timer.setRepeats(false);
                    timer.start();
                }
            });

            panel.add(option);
            y += 60;
        }

        panel.repaint();
    }

    private List<String> generateOptions(String correct) {
        Set<String> allAnswers = new HashSet<>();
        for (Flashcard fc : FlashcardStorage.getCards(subject)) {
            if (!fc.getAnswer().equals(correct)) {
                allAnswers.add(fc.getAnswer());
            }
        }

        List<String> fakeAnswers = new ArrayList<>(allAnswers);
        Collections.shuffle(fakeAnswers);

        List<String> options = new ArrayList<>();
        options.add(correct);
        for (int i = 0; i < 3 && i < fakeAnswers.size(); i++) {
            options.add(fakeAnswers.get(i));
        }

        while (options.size() < 4) options.add("Option " + (options.size() + 1));

        Collections.shuffle(options);
        return options;
    }

    private void addCloseButton() {
        JLabel closeBtn = new JLabel("X", SwingConstants.CENTER);
        closeBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        closeBtn.setForeground(Color.WHITE);
        closeBtn.setBackground(new Color(200, 70, 70));
        closeBtn.setOpaque(true);
        closeBtn.setBounds(760, 10, 30, 30);
        closeBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        closeBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                closeBtn.setBackground(new Color(170, 50, 50));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                closeBtn.setBackground(new Color(200, 70, 70));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                dispose();
            }
        });

        panel.add(closeBtn);
    }
}
