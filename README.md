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
Currently there are no installations for build tools, for using this lib you need to install the jar and smash into ur project
```
Alternatively, you can download the library from the GitHub repository and include it in your project manually.

## Getting started
To get started with NimbusUI, you can use the following code as an example:

```java
import content.*;
import visual.Shapes; // Keep in mind that the imports can change based on the version and build tool

public class NimbusExample {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException {
        Frame frame = new Frame("Nimbus example");
        Screen screen = new Screen(new Style(Styles.END_CRYSTAL)); // There are many possible Styles you can choose
        Button button = new Button("Start application! ", Shapes.HEXAGON); // There are many shapes you can choose

        // You place the component on Imagine-Screen(1920x1080) and it will automatically resize
        // based on the users resolution of the window
        button.setBounds(650, 300, 500, 300);
        
        screen.add(button);

        frame.setContentPane(screen);
        frame.setDefaultCloseOperation("newCloseOperation"); // Set a new close operation when pressing close button
    }

    // When using a method as a new close operation, your IDE will still think that this method is unused
    @SuppressWarnings("unused")
    public static void newCloseOperation(){
        System.out.println("You not closed the window :P");
        System.exit(0);
    }
}
```
(https://user-images.githubusercontent.com/69641472/222967355-2d14a2f9-ff16-40e0-9a33-c752fae71aea.png)
In this example, we create a frame with a screen and a button. We set the content pane of the frame to the screen and set a custom![Screenshot 2023-03-05 154312]
close operation. The button is styled with a hexagon shape and the End Crystal style.

## Contributing
We welcome and greatly appreciate any contribution to NimbusUI. Whether you're a seasoned developer or just starting out, your input can help make the library even better.

Thank you for your interest in contributing to NimbusUI!

## License
NimbusUI is licensed under the Apache-2.0 License.
