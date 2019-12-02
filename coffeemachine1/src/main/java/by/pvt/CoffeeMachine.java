package by.pvt;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Random;
import java.util.logging.Logger;

public class CoffeeMachine {


    public static void main(String[] args) throws IOException, InterruptedException {

        Logger log = Logger.getLogger("CoffeeMachineDataController");

        int water_size = 1000;
        Random random = new Random();

        for (int i = 0; i < 100; i++) {
            String body = "{\n" +
                    "  \"coffeeMachineId\": \"" + args[0] + "\",\n" +
                    "  \"milkMl\": " + (double)(water_size - (random.nextInt(500 - 10) + 10)) + ",\n" +
                    "  \"waterMl\": " + (double)(water_size - (random.nextInt(200 - 10) + 10)) + ",\n" +
                    "  \"coffeeBeansGr\":  " + (double)(water_size - (random.nextInt(100 - 10) + 10)) + ",\n" +
                    "  \"usedCoffeeContainerPortion\": " + (random.nextInt(40 - 10) + 10) + "\n" +
                    "}";

            log.info("!!!!main method of CM jar file is running!!!! ");
            sendPostRequest(body);
            Thread.sleep(20_000);
        }
    }

    private static void sendPostRequest(String body) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .PUT(HttpRequest.BodyPublishers.ofString(body))
                .uri(URI.create("http://localhost:8087/coffee-machine-data-event"))
                .header("Content-Type", "application/json")
                .build();


        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Status Code=" + response.statusCode());
        System.out.println("Response Body=" + response.body());
    }
}

