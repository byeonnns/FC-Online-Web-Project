import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class Application {
    public static void main(String[] args) {
        try {
            String apiUrl = "https://static.api.nexon.co.kr/fifaonline4/latest/spid.json"; //

            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();

            if (responseCode == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                reader.close();
                connection.disconnect();

                List<PlayerInfo> playerInfoList = new ArrayList<>();

                JSONArray jsonArray = new JSONArray(response.toString());
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    long id = jsonObject.getLong("id");
                    String name = jsonObject.getString("name");

                    PlayerInfo playerInfo = new PlayerInfo(id, name);
                    playerInfoList.add(playerInfo);
                }

                for (PlayerInfo player : playerInfoList) {
                    System.out.println("Player ID: " + player.getId());
                    System.out.println("Player Name: " + player.getName());
                }
                int numberOfPlayers = playerInfoList.size();
                System.out.println("Number of PlayerInfo objects in the list: " + numberOfPlayers);

            } else {
                System.out.println("Error Response Code: " + responseCode);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}