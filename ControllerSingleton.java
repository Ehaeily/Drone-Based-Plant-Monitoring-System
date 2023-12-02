package application;

public class ControllerSingleton {
    private static Controller instance;

    private ControllerSingleton() {
        // private constructor to prevent instantiation
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
            instance.init();
        }
        return instance;
    }

    public static void setInstance(Controller controllerInstance) {
        instance = controllerInstance;
    }
}
