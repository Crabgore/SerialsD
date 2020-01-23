package view;

import controller.Controller;

public class Main {
    private static Controller controller;

    public static void main(String[] args) {
        controller = new Controller();

        controller.addSerials();
        controller.showSerials();

        controller.deleteSerial(3);
        controller.showSerials();


        controller.end();
    }
}
