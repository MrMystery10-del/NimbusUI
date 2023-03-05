# NimbusUI

NimbusUI is a user interface library that provides a Swing extension with a modern and sleek design. It is very easy to use, making it perfect for developers of all skill levels. With NimbusUI, you can create professional-looking applications in no time.

## Features

- Easy to use
- Swing extension
- Modern and sleek design
- Wide variety of styles to choose from
- Customizable shapes and colors

## Installation

NimbusUI can be installed using your favorite build tool. Add the following dependency to your `pom.xml` file:

```xml
<dependency>
    <groupId>com.nimbusui</groupId>
    <artifactId>nimbusui</artifactId>
    <version>1.0.0</version>
</dependency>
```
Alternatively, you can download the library from the GitHub repository and include it in your project manually.

## Getting started
To get started with NimbusUI, you can use the following code as an example:

```java
import content.*;
import visual.Shapes;

public class NimbusExample {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException {
        Frame frame = new Frame("Nimbus example");
        Screen screen = new Screen(new Style(Styles.END_CRYSTAL));
        Button button = new Button("Start application! ", Shapes.HEXAGON);

        button.setBounds(650, 300, 500, 300);
        screen.add(button);

        frame.setContentPane(screen);
        frame.setDefaultCloseOperation("newCloseOperation");
    }

    @SuppressWarnings("unused")
    public static void newCloseOperation(){
        System.out.println("You not closed the window :P");
        System.exit(0);
    }
}
```
In this example, we create a frame with a screen and a button. We set the content pane of the frame to the screen and set a custom close operation. The button is styled with a hexagon shape and the End Crystal style.

## Contributing
If you would like to contribute to NimbusUI, please read our contribution guidelines for more information.

## License
NimbusUI is licensed under the MIT License.
