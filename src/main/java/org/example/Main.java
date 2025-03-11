package org.example;

import java.util.Scanner;
import java.util.Random;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Willkommen zu den Minigames, wähle aus was du spielen möchtest");
        System.out.println("(1) ---> number guessing");
        System.out.println("(2) ---> Coin Flip");
        System.out.println("(3) ---> Passwort Generator");
        System.out.println("(4) ---> Wetter App");
        System.out.print("Deine Eingabe: ");
        int auswahl = scanner.nextInt();

        if (auswahl == 1) {
            numberGuessing();
        } else if (auswahl == 2) {
            coinFlip();
        } else if (auswahl == 3) {
            passwortGenerator();

        } else if (auswahl == 4) {
        weatherApp();

        }

        else {
            System.out.println("Gebe bitte eine gültige Auswahl an");
        }



    }

    public static void numberGuessing() {
        int randomNummer = (int) (Math.random() * 100) +1;
        System.out.println("Willkommen zum Zahlenraten tippe bitte eine Zahl von 1-100\nNach Jedem Tipp kriegst du einen hinweis du hast 10 Versuche");
        System.out.print("Deine Eingabe: ");
        int Versuche = 10;


        for (int i = 1; i <= 10; i++) {
            Scanner scanner = new Scanner(System.in);
            int tipp = scanner.nextInt();

            if (tipp > randomNummer) {
                System.out.println("Die gesuchte Zahl ist KLEINER");
                Versuche --;
                System.out.println("Du hast noch "+ Versuche + " Versuche übrig");
            }
            else if (tipp < randomNummer) {
                System.out.println("Die gesuchte Zahl ist GRÖßER");
                Versuche --;
                System.out.println("Du hast noch "+ Versuche + " Versuche übrig");
            }
            else if (tipp == randomNummer) {
                System.out.println("DU HAST DIE RICHTIGE ZAHL ERRATEN\nZahl "+ randomNummer);
                Versuche --;
                System.out.println("Du hast "+ (10 - Versuche) + " Versuche gebraucht");
                break;
            }


            else if (Versuche == 0) {
                System.out.println("Du hast alle Versuche aufgebraucht\nDie gesuchte Zahl war " + randomNummer);
            }

        }
    }

    public static void coinFlip() {
        int zahl = 0;
        int kopf = 0;
        Scanner flipper = new Scanner(System.in);



        while (true) {
            System.out.print("Drücke Enter um zu flippen oder gibt exit ein um zu verlassen");
            String flip = flipper.nextLine();

            if (flip.equals("exit")) {
                System.out.println("Du hast " + kopf + " kopf geflippt ");
                System.out.println("Du hast " + zahl + " Zahl geflippt ");
                break;

            }

            int flipping = (int) (Math.random() * 2) +1;


            if (flipping == 1) {
                System.out.println("Du hast Kopf geworfen \uD83C\uDFB0");
                kopf++;
            } else {
                System.out.println("Du hast Zahl geworfen 1 \uFE0F⃣ ");
                zahl++;
            }



        }

    }

    public static void passwortGenerator() {
        Scanner leangeEingabe = new Scanner(System.in);
        System.out.print("Wie lang soll das Passwort sein: ");
        int passwordLength = leangeEingabe.nextInt();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+[]{}|;:,.<>?";
        int length = passwordLength;
        Random random = new Random();
        StringBuilder password = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            password.append(characters.charAt(index));
        }

        System.out.println("Generiertes Passwort: " + password);
    }

    public static void weatherApp() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Gebe den Namen der Stadt ein: ");
        String city = scanner.nextLine();

        String apiKey = "c8b095fd9f5b87e6c9ef9b93f9d43087";
        String urlString = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey + "&units=metric";

        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                JSONObject weatherData = new JSONObject((response.toString()));
                String weather = weatherData.getJSONArray("weather").getJSONObject(0).getString("description");
                double temperature = weatherData.getJSONObject("main").getDouble("temp");

                System.out.println("Wetter"+ weather);
                System.out.println("Temperatur: "+ temperature + "°C");
            } else {
                System.out.println("Anfrage Fehlgeschlagen");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


