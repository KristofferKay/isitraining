import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class App {
    private String city;

    private Boolean rain;


    private String apiKey = "API";
    private String accUrl = "http://dataservice.accuweather.com/currentconditions/v1/";

    private String uid;
    private String isitRaining;


    // GET SET
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }


    public App(String cityUserInput) {
        this.city = cityUserInput;
    }


    public String findUID(String cityUserInput){
        String apiUIDSearch = "http://dataservice.accuweather.com/locations/v1/cities/search?apikey=" + apiKey +"&q=" + cityUserInput;
        System.out.println(apiUIDSearch);

        try {
            URL url = new URL(apiUIDSearch);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            String jsonResponse = response.toString();
            int keyIndex = jsonResponse.indexOf("\"Key\":\"");
            if (keyIndex != -1) {
                int keyStartIndex = keyIndex + 7; // Length of Key
                int keyEndIndex = jsonResponse.indexOf("\"", keyStartIndex);
                if (keyEndIndex != -1) {
                    String key = jsonResponse.substring(keyStartIndex, keyEndIndex);
                    System.out.println("Key: " + key);
                    uid = key;
                } else {
                    System.out.println("Key not found in response.");
                }
            } else {
                System.out.println("Key not found in response.");
            }


            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();

        }
        return uid;
    }
    public Boolean isItRainingInCity(){
        rain = false;
        try {
            URL url = new URL("http://dataservice.accuweather.com/currentconditions/v1/"+uid+"?apikey="+apiKey+"&details=true");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            String jsonResponse = response.toString();
            int PrecipitationTypeIndex = jsonResponse.indexOf("\"PrecipitationType\":\"");
            if (PrecipitationTypeIndex != -1) {
                int PrecipitationTypeIndexStart = PrecipitationTypeIndex + 21; // Length PrecipitationType
                int keyEndIndex = jsonResponse.indexOf("\"", PrecipitationTypeIndexStart);
                if (keyEndIndex != -1) {
                    String precipitationType = jsonResponse.substring(PrecipitationTypeIndexStart, keyEndIndex);
                    System.out.println("PrecipitationType: " + precipitationType);
                    uid = precipitationType;
                } else {
                    System.out.println("PrecipitationType not found in response.");
                }
            } else {
                System.out.println("PrecipitationType not found in response.");
            }

            String rain = String.valueOf(response.equals("Link"));

            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rain = true;
    }
}
