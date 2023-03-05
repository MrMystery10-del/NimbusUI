package content;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Frame extends JFrame {

    /**
     * Creates a new Frame
     */
    public Frame() {
        setDefaultFrame();
    }

    /**
     * Creates a new Frame
     *
     * @param title title displayed on the top bar
     */
    public Frame(String title) {
        setTitle(title);
        setDefaultFrame();
    }

    private void setDefaultFrame() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int) dimension.getWidth() / 2, (int) dimension.getHeight() / 2);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setVisible(true);
    }

    @Override
    public Component add(Component comp){
        return addComponent(comp);
    }

    @Override
    public Component add(String name, Component comp) {
        comp.setName(name);
        return addComponent(comp);
    }

    @Override
    public Component add(Component comp, int layer) {
        return addComponent(comp);
    }

    private Component addComponent(Component comp){
        addImpl(comp, null, -1);
        getComponent(0).revalidate();
        return super.add(comp);
    }

    @Override
    public void setContentPane(Container contentPane) {
        super.setContentPane(contentPane);
        getContentPane().revalidate();
    }

    /**
     * Sets a new function on closing frame
     *
     * @param methodName name of the method which should get executed on close button, this method should be static
     */
    public void setCloseOperation(String methodName) throws NoSuchMethodException, ClassNotFoundException {
        Class<?> executor = Class.forName(Thread.currentThread().getStackTrace()[2].getClassName());

        Method method = executor.getDeclaredMethod(methodName);
        method.setAccessible(true);

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                super.windowClosing(event);
                try {
                    method.invoke(executor);
                } catch (IllegalAccessException | InvocationTargetException | IllegalArgumentException exception) {
                    exception.printStackTrace();
                }
            }
        });
    }
}
