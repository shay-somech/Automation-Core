package Tests;

import core.utils.ActionHelper;
import core.utils.Log;
import core.utils.TestListener;
import org.testng.annotations.Test;

import static core.constants.KeyboardEvents.SEARCH;

public class Testing extends TestListener {

    @Test
    public void test() {
        ActionHelper.getInstance().sendKeyboardEvent(SEARCH);
        Log.info("Success :)");
    }

    private static void drawRec() {
        for (int i = 0; i < 50; i++) {
            System.out.print("*");
        }

        for (int i = 0; i < 3; i++) {
            System.out.println("*");
            System.out.print("%nd *");
        }

        for (int i = 0; i < 51; i++) {
            System.out.print("*");
        }

    }

    private static void draw() {
        for (int i = 1; i < 13; i++) {
            for (int j = 1; j < 13; j++) {

                System.out.format("%5d", i * j);
            }
            System.out.println();  // To move to the next line.
        }
    }

    public static void main(String[] args) {

        String a = "Device Pixel Google 9 || FA6AS0306029";
        String b = a.substring(a.indexOf("||") + 3);
        System.out.println(b);
    }
}
