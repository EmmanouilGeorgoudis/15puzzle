import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GameWindow extends JFrame implements ActionListener {

    ButtonList buttonList = new ButtonList();
    List<JButton> buttons = buttonList.getButtons();
    JButton resetButton;
    boolean justReset = false;

    public GameWindow() {
        this.setTitle("15-Puzzle?");

        JPanel gamePanel = new JPanel(new GridLayout(4, 4));
        JPanel controlPanel = new JPanel();

        for (JButton b : buttons) {
            b.addActionListener(this);
            b.setFont(new Font("Arial", Font.BOLD, 20));
            gamePanel.add(b);
        }

        JLabel timer = new JLabel("");
        resetButton = new JButton("Nytt spel");
        resetButton.addActionListener(this);

        controlPanel.add(timer);
        controlPanel.add(resetButton);

        setLayout(new BorderLayout());
        add(gamePanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);


        this.setSize(400, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae){
        Object source = ae.getSource();

        if (source == resetButton) {
            buttonList.randomizer();
            justReset = true;
            return;
        } else if (justReset) {
            justReset = false;
        }

        for (int i = 0; i < buttons.size(); i++) {
            if (source == buttons.get(i)) {
                buttonList.numberExchanger(buttons.get(i), buttons);
            }
        }

        if (!justReset && buttonList.isComplete()) {
            JOptionPane.showMessageDialog(this, "Perkele Satana!");
        }

    }

    public static void main(String[] args) {
        new GameWindow();
    }
}
