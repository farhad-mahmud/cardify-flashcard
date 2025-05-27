package dashboard;

import com.mongodb.client.*;
import org.bson.Document;
import db.TestMongo;

import javax.swing.*;
import java.awt.*;

public class AllUsersViewer extends JFrame {

    public AllUsersViewer() {
        setTitle("All Registered Users");
        setSize(400, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        MongoDatabase db = TestMongo.connect();
        MongoCollection<Document> users = db.getCollection("users");
        FindIterable<Document> docs = users.find();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        for (Document user : docs) {
            String username = user.getString("username");
            String email = user.getString("email");

            JTextArea userEntry = new JTextArea("👤 " + username + "\n📧 " + email);
            userEntry.setEditable(false);
            userEntry.setBackground(new Color(240, 240, 255));
            userEntry.setMargin(new Insets(10, 10, 10, 10));
            userEntry.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            userEntry.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            panel.add(userEntry);
            panel.add(Box.createVerticalStrut(10));
        }

        JScrollPane scrollPane = new JScrollPane(panel);
        add(scrollPane);

        setVisible(true);
    }
}
