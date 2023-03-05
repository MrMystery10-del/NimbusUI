package content;

import visual.Shape;
import visual.Shapes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Button extends JButton implements NimbusComponent {

    private boolean paintBorder = false;
    private Shapes shape = Shapes.RECTANGLE;

    /**
     * Creates a new button
     */
    public Button() {
        setDefaultButton();
    }

    /**
     * Creates a new button
     *
     * @param text text which will be displayed on the button
     */
    public Button(String text) {
        setText(text);
        setDefaultButton();
    }

    /**
     * Creates a new button
     *
     * @param text text which will be displayed on the button
     */
    public Button(String text, Shapes shape) {
        this.shape = shape;

        setText(text);
        setDefaultButton();
    }

    private void setDefaultButton() {
        setFocusable(false);
        setContentAreaFilled(false);
        addMouseListener(new ButtonBorder());
        setFont(new Font("Arial", Font.BOLD, 30));
    }

    public void setShape(Shapes shape) {
        this.shape = shape;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        final int width = getSize().width;
        final int height = getSize().height;
        final Color backgroundColor = getBackground();
        final Color borderColor = paintBorder ? Color.GREEN : backgroundColor;
        final Color armedColor = Color.GRAY;

        Graphics2D g2d = (Graphics2D) graphics.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Shape.drawShape(g2d, shape, width, height, backgroundColor, borderColor, armedColor, getModel().isArmed());
        super.paintComponent(graphics);
    }

    @Override
    protected void paintBorder(Graphics g) {
    }

    @Override
    public boolean contains(int x, int y) {
        return Shape.isInShape(x, y, getWidth(), getHeight(), shape);
    }

    private class ButtonBorder extends MouseAdapter {

        @Override
        public void mouseEntered(MouseEvent event) {
            super.mouseEntered(event);
            paintBorder = true;
        }

        @Override
        public void mouseExited(MouseEvent event) {
            super.mouseExited(event);
            paintBorder = false;
        }
    }
}
