import java.util.Scanner;

public class UI {
    private static String cityInput;
    App app = new App(cityInput);


    public void startApp() {
        cityInput();
    }

    public void cityInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Skriv din by");
        cityInput = scanner.next();

//        used for testing if we get UID from accuweather
//        System.out.println("Let's find the UID for your city");
        app.findUID(cityInput);

        app.isItRainingInCity();
        if (true) {
            System.out.println("Det regner.");
        } else if (false) {
            System.out.println("Det regner ikke, endnu");
        }
    }
}
