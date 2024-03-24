import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        UI ui = new UI();

        ui.startApp();

        }
    }
// TODO
// is it raining. User enter city, terminal see if its raining via api call, reply to user no or yes
// user should have input, to add city of choice. the user inputted city, is stored in City attribute.
// City is returned as a string.
//
// FUTURE: in future. api should lookup city via accuweather to get current data
// FUTURE: if its raining in the city, then api should return true / false, that should be stored in value Rain.
//