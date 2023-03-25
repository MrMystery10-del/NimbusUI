package content;

import visual.Shape;
import visual.Shapes;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Label extends JLabel implements NimbusComponent {

    private Shapes shape = Shapes.RECTANGLE;
    private Color backgroundColor = Color.GRAY;
    private Icon icon = null;

    public Label() {
        setDefaultLabel();
    }

    public Label(String text) {
        setText(text);
        setDefaultLabel();
    }

    public Label(Shapes shape) {
        this.shape = shape;

        setDefaultLabel();
    }

    public Label(String text, Shapes shape) {
        this.shape = shape;

        setText(text);
        setDefaultLabel();
    }

    private void setDefaultLabel() {
        setOpaque(true);
        setFont(new Font("Arial", Font.BOLD, 30));
        setHorizontalAlignment(SwingConstants.CENTER);
    }

    @Override
    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    @Override
    public void setShape(Shapes shape) {
        this.shape = shape;
        repaint();
    }

    @Override
    public void setBackground(Color bg) {
        backgroundColor = bg;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        final int width = getSize().width;
        final int height = getSize().height;

        Graphics2D g2d = (Graphics2D) graphics.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Shape.drawShape(g2d, shape, width, height, backgroundColor);

        if (icon != null)
            g2d.drawImage(((ImageIcon) icon).getImage(), 0, 0, getWidth(), getHeight(), null);

        g2d.setColor(getForeground());
        FontMetrics fontMetrics = g2d.getFontMetrics();
        Rectangle2D textBounds = fontMetrics.getStringBounds(getText(), g2d);

        int textX = (int) ((width - textBounds.getWidth()) / 2);
        int textY = (int) ((height - textBounds.getHeight()) / 2) + fontMetrics.getAscent();
        g2d.drawString(getText(), textX, textY);

        g2d.dispose();
    }
}
