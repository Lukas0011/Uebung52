package main;

import controller.OertlicheController;
import model.Entry;

public class OertlicheMain {
    public static void main(String[] args) {
        new OertlicheController();
        Entry t = new Entry("a", "s", "d", "l", "no", "m", "w", "21213", "213123", "12312323");

        for (String[] arr : t.allInformation()) {
            for (String s : arr) {
                System.out.println(s);
            }
        }
    }
}
