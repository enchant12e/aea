import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Auth {
    private static final String AUTH_URL = "https://authserver.mojang.com/authenticate";

    public static String authenticate(String username, String password) throws Exception {
        URL url = new URL(AUTH_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        String payload = "{ \"agent\": { \"name\": \"Minecraft\", \"version\": 1 }, \"username\": \"" 
                          + username + "\", \"password\": \"" + password + "\" }";

        try (OutputStream os = connection.getOutputStream()) {
            os.write(payload.getBytes());
            os.flush();
        }

        Scanner scanner = new Scanner(connection.getInputStream());
        StringBuilder response = new StringBuilder();
        while (scanner.hasNext()) {
            response.append(scanner.nextLine());
        }
        scanner.close();

        // Extraer el token de acceso
        String token = parseToken(response.toString());
        return token;
    }

    private static String parseToken(String response) {
        int tokenIndex = response.indexOf("\"accessToken\":\"") + 15;
        return response.substring(tokenIndex, response.indexOf("\"", tokenIndex));
    }
}
