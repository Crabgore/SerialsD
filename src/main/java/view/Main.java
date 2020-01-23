package view;

import controller.Controller;
import model.Serial;

import java.util.List;

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

    public static void added(String title) {
        System.out.println("Сериал " + title + " добавлен в базу!");
    }

    public static void deleted(String title) {
        System.out.println("Сериал " + title + " удалён из базы!");
    }

    public static void showSerials(List<Serial> serials) {
        System.out.println(serials);
    }
}
