package org.example;

public class Main {
     static void main() {
        new App().run();
    }
}

// change to this if you want to run GUI
//-----------------------------GUI-----------------------------
/*public class Main {
     static void main() {
        App app = new App();
        app.runWithoutConsole(); // new method that only seeds data
        new ShopGUI(app);
    }
}*/
