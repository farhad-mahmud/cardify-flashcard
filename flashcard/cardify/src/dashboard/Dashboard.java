package dashboard;

import component.Toaster;
import Utils.*;


import db.mongoConnector;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Dashboard extends JFrame {

    private final Toaster toaster;

    public Dashboard() {
        setTitle("Dashboard - Cardify");
        //setUndecorated(true);
        setSize(800, 500);
        setLayout(null);
        setBackground(UIUtils.COLOR_BACKGROUND);
        getContentPane().setBackground(UIUtils.COLOR_BACKGROUND);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(UIUtils.COLOR_BACKGROUND);
        panel.setBounds(0, 0, 800, 500);
        add(panel);

        toaster = new Toaster(panel);

        addHeader(panel);
        addSubjectButtons(panel);
        addPlusButton(panel);

        setVisible(true);
    }

    private void addHeader(JPanel panel) {
        JLabel title = new JLabel("Flashcards", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 30));
        title.setForeground(Color.WHITE);
        title.setBounds(0, 30, 800, 40);
        panel.add(title);
    }

    private void addSubjectButtons(JPanel panel) {
        String[] subjects = {"Mathematics", "History", "Chemistry", "French"};
        Color[] colors = {
                new Color(52, 89, 95),    // Mathematics
                new Color(166, 72, 49),   // History
                new Color(39, 76, 66),    // Chemistry
                new Color(88, 62, 117)    // French
        };
    
        int x1 = 180, y1 = 100, w = 180, h = 80;
        int gapX = 240, gapY = 130;
    
        for (int i = 0; i < subjects.length; i++) {
            final int index = i; // ✅ fix: make a final copy
    
            int row = index / 2;
            int col = index % 2;
    

            JLabel btn = new JLabel(subjects[index]) {
                @Override
                protected void paintComponent(Graphics g) {
                    Graphics2D g2 = UIUtils.get2dGraphics(g);
                    g2.setColor(getBackground());
                    g2.fillRoundRect(0, 0, getWidth(), getHeight(), UIUtils.ROUNDNESS * 2, UIUtils.ROUNDNESS * 2);
            
                    FontMetrics metrics = g2.getFontMetrics(UIUtils.FONT_GENERAL_UI);
                    int x2 = (getWidth() - metrics.stringWidth(subjects[index])) / 2;
                    int y2 = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
            
                    g2.setColor(getForeground());
                    g2.setFont(UIUtils.FONT_GENERAL_UI);
                    g2.drawString(subjects[index], x2, y2);
                }
            };
            

            btn.setFont(UIUtils.FONT_GENERAL_UI);
            btn.setForeground(Color.WHITE);
            btn.setOpaque(true);
            btn.setBackground(colors[index]);
            btn.setBounds(x1 + col * gapX, y1 + row * gapY, w, h);
            btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    
            btn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    btn.setBackground(colors[index].darker());
                }
    
                @Override
                public void mouseExited(MouseEvent e) {
                    btn.setBackground(colors[index]);
                }
    
                @Override
                public void mousePressed(MouseEvent e) {
                    toaster.info(subjects[index] + " clicked!");
                }
            });
    
            btn.setBorder(BorderFactory.createLineBorder(UIUtils.COLOR_BACKGROUND, 1, true));
            panel.add(btn);
        }
    }
    

    private void addPlusButton(JPanel panel) {

        JLabel plusBtn = new JLabel("+", SwingConstants.CENTER) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = UIUtils.get2dGraphics(g);
                g2.setColor(getBackground());
                g2.fillOval(0, 0, getWidth(), getHeight());
        
                FontMetrics metrics = g2.getFontMetrics(getFont());
                int x = (getWidth() - metrics.stringWidth("+")) / 2;
                int y = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
        
                g2.setFont(getFont());
                g2.setColor(getForeground());
                g2.drawString("+", x, y);
            }
        };

        
        plusBtn.setFont(new Font("Segoe UI", Font.BOLD, 32));
        plusBtn.setForeground(Color.WHITE);
        plusBtn.setOpaque(true);
        plusBtn.setBackground(new Color(122, 201, 160));
        plusBtn.setBounds(370, 400, 60, 60);
        plusBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        plusBtn.setBorder(BorderFactory.createLineBorder(UIUtils.COLOR_BACKGROUND, 1, true));
        plusBtn.setBorder(BorderFactory.createEmptyBorder());
        plusBtn.setBorder(BorderFactory.createLineBorder(new Color(122, 201, 160), 1, true));
        plusBtn.setBorder(BorderFactory.createLineBorder(UIUtils.COLOR_BACKGROUND));

        plusBtn.setFocusable(false);
        plusBtn.setToolTipText("Add new subject");

        plusBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                toaster.success("Add new subject");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                plusBtn.setBackground(new Color(101, 176, 139));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                plusBtn.setBackground(new Color(122, 201, 160));
            }
        });

        panel.add(plusBtn);
    }
}
