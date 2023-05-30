package main;

import javax.swing.*;
import java.awt.*;

public class InventoryPanel extends JPanel {
    private boolean isVisible;

    public InventoryPanel() {
        setupInventoryPanel();
        this.isVisible = false;
        this.setVisible(false);
    }

    private void setupInventoryPanel() {
        JLabel inventory = new JLabel("inventory");
        inventory.setFont(new Font("Sans", Font.PLAIN, 20));
        this.add(inventory);
    }

    public void switchVisibility(){
        this.isVisible = !isVisible;
        this.setVisible(isVisible);
        System.out.println("Switched inventory panel to ".concat(Boolean.toString(isVisible)));
    }
}
