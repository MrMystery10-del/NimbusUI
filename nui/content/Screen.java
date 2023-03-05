package content;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

public class Screen extends JPanel {

    private final static Dimension boundSize = new Dimension(1920, 1080);
    private final ArrayList<Rectangle> componentBounds = new ArrayList<>();
    private final ArrayList<Font> componentFonts = new ArrayList<>();
    private final ArrayList<Integer> componentLayers = new ArrayList<>();
    private final ArrayList<Component> components = new ArrayList<>();
    private final ComponentAdapter resizeManager;
    private Style style = new Style(Styles.DEFAULT);

    /**
     * Creates a new screen
     */
    public Screen() {
        setDefaultScreen();
    }

    /**
     * Creates a new screen
     *
     * @param style type of the style(color palette) used
     */
    public Screen(Style style) {
        this.style = style;

        setDefaultScreen();

        setBackground(style.getColor(0));
        for (int i = 0; i < getComponentCount(); i++)
            getComponents()[i].setBackground(style.getColor(componentLayers.get(i)));
    }

    private void setDefaultScreen() {
        setBackground(style.getColor(0));
        setLayout(null);

        addComponentListener(resizeManager);
    }

    /**
     * Set a new style(color palette)
     */
    public void setStyle(Style style) {
        this.style = style;

        setBackground(style.getColor(0));
        for (int i = 0; i < getComponentCount(); i++)
            getComponents()[i].setBackground(style.getColor(componentLayers.get(i)));
    }

    /**
     * Adds a new Component to the screen
     *
     * @param comp swing/nimbus component
     */
    @Override
    public Component add(Component comp) {
        return addComponent(comp, "", 3);
    }

    /**
     * Adds a new Component to the screen
     *
     * @param comp swing/nimbus component
     * @param name name of the component(not the displayed text)
     */
    @Override
    public Component add(String name, Component comp) {
        return addComponent(comp, name, 3);
    }

    /**
     * Adds a new Component to the screen
     *
     * @param comp swing/nimbus component
     * @param layer position of the component on the screen, 1 = top layer 3 = the lowest layer. 0 layer is not recommended
     */
    @Override
    public Component add(Component comp, int layer) {
        return addComponent(comp, "", layer);
    }

    private Component addComponent(Component comp, String name, int layer) {
        components.add(comp);
        componentBounds.add(comp.getBounds());
        componentFonts.add(comp.getFont());
        componentLayers.add(layer);

        comp.setBackground(style.getColor(layer));
        comp.setName(name);

        addImpl(comp, null, -1);
        return comp;
    }

    public void changeBoundsOfComp(Component comp){
        for(int i = 0; i < getComponentCount(); i++)
            if (comp.equals(getComponents()[i]))
                componentBounds.set(i, comp.getBounds());
    }

    public void changeBoundsOfComp(Component comp, int x, int y, int width, int height){
        for(int i = 0; i < getComponentCount(); i++)
            if (comp.equals(getComponents()[i]))
                componentBounds.set(i, new Rectangle(x, y, width, height));
    }

    public void changeBoundsOfComp(Component comp, Rectangle rect){
        for(int i = 0; i < getComponentCount(); i++)
            if (comp.equals(getComponents()[i]))
                componentBounds.set(i, rect);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
    }

    {
        resizeManager = new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent event) {
                super.componentResized(event);

                Dimension newSize = getSize();
                double scaleX = newSize.getWidth() / boundSize.getWidth();
                double scaleY = newSize.getHeight() / boundSize.getHeight();

                for (int i = 0; i < getComponentCount(); i++) {
                    Rectangle bounds = componentBounds.get(i);
                    int x = (int) Math.round(bounds.getX() * scaleX);
                    int y = (int) Math.round(bounds.getY() * scaleY);
                    int width = (int) Math.round(bounds.getWidth() * scaleX);
                    int height = (int) Math.round(bounds.getHeight() * scaleY);

                    getComponents()[i].setBounds(x, y, width, height);

                    Font font = componentFonts.get(i);
                    try {
                        font.getSize();
                    } catch (NullPointerException exception) {
                        continue;
                    }
                    float size = font.getSize() * (float) scaleX;
                    getComponents()[i].setFont(font.deriveFont(size));
                }
            }
        };
    }
}
