package buttonUi.Buttons;

import buttonUi.GameButton;

public class ClassButton extends GameButton {

    private int gameClass;
    private boolean mousePressed;
    public ClassButton(int x, int y, int width, int height, int gameClass) {
        super(x, y, width, height);
        this.gameClass = gameClass;
    }

    public int getGameClassClass() {
        return gameClass;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }
}
