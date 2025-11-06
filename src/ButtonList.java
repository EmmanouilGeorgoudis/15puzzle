import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ButtonList {


    private final List<JButton> buttons = new ArrayList<>();
    private final List<String> correctOrder = new ArrayList<>(Arrays.asList("1", "2", "3", "4",
            "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", ""));
    private final List<String> displayNumbers = new ArrayList<>(correctOrder);


    public ButtonList() {
        for (int i = 0; i < 16; i++) {
            JButton b = new JButton(displayNumbers.get(i));
//            b.putClientProperty("name", "b" + (i + 1));
            buttons.add(b);
        }
    }

    public List<JButton> getButtons() {
        return buttons;
    }

    public void randomizer() {
        Collections.shuffle(displayNumbers);
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setText(displayNumbers.get(i));
        }
    }

    public boolean hasEmptyNearby(JButton pressedButton, List<JButton> buttons) {

        int indexOfPressedButton = buttons.indexOf(pressedButton);

        if (buttons.get(indexOfPressedButton).getText().isBlank()) {
            return false;
        }

        int indexOfEmpty = -1;
        for (int i = 0; i < buttons.size(); i++) {
            if (buttons.get(i).getText().isBlank()) {
                indexOfEmpty = i;
            }
        }

        return switch (indexOfPressedButton) {
            case 0 -> (indexOfEmpty == 1 || indexOfEmpty == 4);
            case 1 -> (indexOfEmpty == 0 || indexOfEmpty == 2 || indexOfEmpty == 5);
            case 2 -> (indexOfEmpty == 1 || indexOfEmpty == 3 || indexOfEmpty == 6);
            case 3 -> (indexOfEmpty == 2 || indexOfEmpty == 7);
            case 4 -> (indexOfEmpty == 0 || indexOfEmpty == 5 || indexOfEmpty == 8);
            case 5 -> (indexOfEmpty == 1 || indexOfEmpty == 4 || indexOfEmpty == 6 || indexOfEmpty == 9);
            case 6 -> (indexOfEmpty == 2 || indexOfEmpty == 5 || indexOfEmpty == 7 || indexOfEmpty == 10);
            case 7 -> (indexOfEmpty == 3 || indexOfEmpty == 6 || indexOfEmpty == 11);
            case 8 -> (indexOfEmpty == 4 || indexOfEmpty == 9 || indexOfEmpty == 12);
            case 9 -> (indexOfEmpty == 5 || indexOfEmpty == 8 || indexOfEmpty == 10 || indexOfEmpty == 13);
            case 10 -> (indexOfEmpty == 6 || indexOfEmpty == 9 || indexOfEmpty == 11 || indexOfEmpty == 14);
            case 11 -> (indexOfEmpty == 7 || indexOfEmpty == 10 || indexOfEmpty == 15);
            case 12 -> (indexOfEmpty == 8 || indexOfEmpty == 13);
            case 13 -> (indexOfEmpty == 9 || indexOfEmpty == 12 || indexOfEmpty == 14);
            case 14 -> (indexOfEmpty == 13 || indexOfEmpty == 10 || indexOfEmpty == 15);
            case 15 -> (indexOfEmpty == 11 || indexOfEmpty == 14);
            default -> false;
        };
    }

    public void numberExchanger(JButton pressedButton, List<JButton> buttons) {

        while (hasEmptyNearby(pressedButton, buttons)) {

            int indexOfPressedButton = buttons.indexOf(pressedButton);
            int indexOfEmpty = -1;

            for (int i = 0; i < buttons.size(); i++) {
                if (buttons.get(i).getText().isBlank()) {
                    indexOfEmpty = i;
                }
            }

            String pressedButtonNumber = buttons.get(indexOfPressedButton).getText();
            String emptyButton = buttons.get(indexOfEmpty).getText();

            buttons.get(indexOfPressedButton).setText(emptyButton);
            buttons.get(indexOfEmpty).setText(pressedButtonNumber);
        }
    }

    public boolean isComplete() {

        for (int i = 0; i < buttons.size(); i++) {
            if (!buttons.get(i).getText().equals(correctOrder.get(i))) {
                return false;
            }
        }
        return true;
    }
}





