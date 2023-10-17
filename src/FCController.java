import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FCController {

    public void getPlayerInfo() {
            try {
                URL pInfoURL = new URL("https://static.api.nexon.co.kr/fifaonline4/latest/spid.json");
                HttpURLConnection pInfoConnection = (HttpURLConnection) pInfoURL.openConnection();
                pInfoConnection.setRequestMethod("GET");

                int pInfoResponseCode = pInfoConnection.getResponseCode();

                if (pInfoResponseCode == 200) {
                    BufferedReader pInfoReader = new BufferedReader(new InputStreamReader(pInfoConnection.getInputStream()));
                    String line;
                    StringBuilder pInfoResponse = new StringBuilder();

                    while ((line = pInfoReader.readLine()) != null) {
                        pInfoResponse.append(line);
                    }

                    pInfoReader.close();
                    pInfoConnection.disconnect();

                    List<PlayerInfo> playerInfoList = new ArrayList<>();

                    JSONArray pInfoJsonArray = new JSONArray(pInfoResponse.toString());
                    for (int i = 0; i < pInfoJsonArray.length(); i++) {
                        JSONObject jsonObject = pInfoJsonArray.getJSONObject(i);
                        long id = jsonObject.getLong("id");
                        String name = jsonObject.getString("name");

                        PlayerInfo playerInfo = new PlayerInfo(id, name);
                        playerInfoList.add(playerInfo);
                    }

                    for (PlayerInfo player : playerInfoList) {
                        System.out.println("선수 ID: " + player.getId());
                        System.out.println("선수 이름: " + player.getName());
                    }
                    int numberOfPlayers = playerInfoList.size();
                    System.out.println("총 선수 수 : " + numberOfPlayers);

                } else {
                    System.out.println("Error Response Code: " + pInfoResponseCode);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    public void getMatchId() {
        try {
            URL matchIdURL = new URL("https://public.api.nexon.com/openapi/fconline/v1.0/matches?matchtype=50&offset=0&limit=100&orderby=desc");
            String my_API_Key = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJYLUFwcC1SYXRlLUxpbWl0IjoiNTAwOjEwIiwiYWNjb3VudF9pZCI6IjE1NjA3OTAxOTEiLCJhdXRoX2lkIjoiMiIsImV4cCI6MTcwNDI2NzY4NiwiaWF0IjoxNjg4NzE1Njg2LCJuYmYiOjE2ODg3MTU2ODYsInNlcnZpY2VfaWQiOiI0MzAwMTE0ODEiLCJ0b2tlbl90eXBlIjoiQWNjZXNzVG9rZW4ifQ.kbqI0QMNJWZLNhwsKPUmsWn-jtkpiwIFyWt4E9kDM7E";

            HttpURLConnection matchIdConnection = (HttpURLConnection) matchIdURL.openConnection();
            matchIdConnection.setRequestMethod("GET");
            matchIdConnection.setRequestProperty("Authorization", my_API_Key);


            int matchIdResponseCode = matchIdConnection.getResponseCode();

            if (matchIdResponseCode == 200) {

                BufferedReader matchIdReader = new BufferedReader(new InputStreamReader(matchIdConnection.getInputStream()));
                String matchId;
                StringBuilder matchIdStringBuilder = new StringBuilder();

                while ((matchId = matchIdReader.readLine()) != null) {
                    matchIdStringBuilder.append(matchId);
                }

                String matchId_Response = matchIdStringBuilder.toString();
                String[] matchId_Array = matchId_Response.split(",");

                matchIdReader.close();
                matchIdConnection.disconnect();


                if (matchId_Array.length >= 5) {
                    System.out.println("최근 5경기를 불러옵니다.");
                    for (int i = 1; i <= 5; i++) {
                        System.out.println(i + " : " + matchId_Array[i]);
                    }
                    System.out.println("가장 최근 경기 데이터를 불러옵니다.");
                } else {
                    System.out.println("데이터가 5개 미만입니다.");
                }


                String Match_ID_processed = new String(matchId_Array[1]);
                Match_ID_processed = Match_ID_processed.substring(1, Match_ID_processed.length() - 1);


                URL matchDetails_URL = new URL("https://public.api.nexon.com/openapi/fconline/v1.0/matches/" + Match_ID_processed);
                HttpURLConnection matchDetails_Connection = (HttpURLConnection) matchDetails_URL.openConnection();
                matchDetails_Connection.setRequestMethod("GET");
                matchDetails_Connection.setRequestProperty("Authorization", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJYLUFwcC1SYXRlLUxpbWl0IjoiNTAwOjEwIiwiYWNjb3VudF9pZCI6IjE1NjA3OTAxOTEiLCJhdXRoX2lkIjoiMiIsImV4cCI6MTcwNDI2NzY4NiwiaWF0IjoxNjg4NzE1Njg2LCJuYmYiOjE2ODg3MTU2ODYsInNlcnZpY2VfaWQiOiI0MzAwMTE0ODEiLCJ0b2tlbl90eXBlIjoiQWNjZXNzVG9rZW4ifQ.kbqI0QMNJWZLNhwsKPUmsWn-jtkpiwIFyWt4E9kDM7E");
                int matchDetails_responseCode = matchDetails_Connection.getResponseCode();

                if (matchDetails_responseCode == 200) {
                    BufferedReader matchDetails_Reader = new BufferedReader(new InputStreamReader(matchDetails_Connection.getInputStream()));
                    String Match_details;
                    StringBuilder matchDetail_StringBuilder = new StringBuilder();

                    while ((Match_details = matchDetails_Reader.readLine()) != null) {
                        matchDetail_StringBuilder.append(Match_details);
                    }

                    System.out.println(matchDetail_StringBuilder);


                    matchDetails_Reader.close();
                    matchDetails_Connection.disconnect();


                } else {
                    System.out.println("데이터를 불러올 수 없습니다. 에러 코드 :" + matchDetails_responseCode);
                }

            } else {
                System.out.println("Error Response Code: " + matchIdResponseCode);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    public void getMatchDetails() {
        try {
            URL URL = new URL("https://public.api.nexon.com/openapi/fconline/v1.0/matches?matchtype=50&offset=0&limit=100&orderby=desc");
            String my_API_Key = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJYLUFwcC1SYXRlLUxpbWl0IjoiNTAwOjEwIiwiYWNjb3VudF9pZCI6IjE1NjA3OTAxOTEiLCJhdXRoX2lkIjoiMiIsImV4cCI6MTcwNDI2NzY4NiwiaWF0IjoxNjg4NzE1Njg2LCJuYmYiOjE2ODg3MTU2ODYsInNlcnZpY2VfaWQiOiI0MzAwMTE0ODEiLCJ0b2tlbl90eXBlIjoiQWNjZXNzVG9rZW4ifQ.kbqI0QMNJWZLNhwsKPUmsWn-jtkpiwIFyWt4E9kDM7E";

            HttpURLConnection matchId_connection = (HttpURLConnection) URL.openConnection();
            matchId_connection.setRequestMethod("GET");
            matchId_connection.setRequestProperty("Authorization", my_API_Key);


            int matchId_ResponseCode = matchId_connection.getResponseCode();

            if (matchId_ResponseCode == 200) {

                BufferedReader matchId_Reader = new BufferedReader(new InputStreamReader(matchId_connection.getInputStream()));
                String matchId;
                StringBuilder matchId_StringBuilder = new StringBuilder();

                while ((matchId = matchId_Reader.readLine()) != null) {
                    matchId_StringBuilder.append(matchId);
                }

                String matchId_Response = matchId_StringBuilder.toString();
                String[] matchId_Array = matchId_Response.split(",");

                matchId_Reader.close();
                matchId_connection.disconnect();


                if (matchId_Array.length >= 5) {
                    System.out.println("최근 5경기를 불러옵니다.");
                    for (int i = 1; i <= 5; i++) {
                        System.out.println(i + " : " + matchId_Array[i]);
                    }
                    System.out.println("가장 최근 경기 데이터를 불러옵니다.");
                } else {
                    System.out.println("데이터가 5개 미만입니다.");
                }


                String Match_ID_processed = new String(matchId_Array[1]);
                Match_ID_processed = Match_ID_processed.substring(1, Match_ID_processed.length() - 1);


                URL matchDetails_URL = new URL("https://public.api.nexon.com/openapi/fconline/v1.0/matches/" + Match_ID_processed);
                HttpURLConnection matchDetails_Connection = (HttpURLConnection) matchDetails_URL.openConnection();
                matchDetails_Connection.setRequestMethod("GET");
                matchDetails_Connection.setRequestProperty("Authorization", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJYLUFwcC1SYXRlLUxpbWl0IjoiNTAwOjEwIiwiYWNjb3VudF9pZCI6IjE1NjA3OTAxOTEiLCJhdXRoX2lkIjoiMiIsImV4cCI6MTcwNDI2NzY4NiwiaWF0IjoxNjg4NzE1Njg2LCJuYmYiOjE2ODg3MTU2ODYsInNlcnZpY2VfaWQiOiI0MzAwMTE0ODEiLCJ0b2tlbl90eXBlIjoiQWNjZXNzVG9rZW4ifQ.kbqI0QMNJWZLNhwsKPUmsWn-jtkpiwIFyWt4E9kDM7E");
                int matchDetails_responseCode = matchDetails_Connection.getResponseCode();

                if (matchDetails_responseCode == 200) {
                    BufferedReader matchDetails_Reader = new BufferedReader(new InputStreamReader(matchDetails_Connection.getInputStream()));
                    String Match_details;
                    StringBuilder matchDetail_StringBuilder = new StringBuilder();

                    while ((Match_details = matchDetails_Reader.readLine()) != null) {
                        matchDetail_StringBuilder.append(Match_details);
                    }

                    System.out.println(matchDetail_StringBuilder);


                    matchDetails_Reader.close();
                    matchDetails_Connection.disconnect();


                } else {
                    System.out.println("데이터를 불러올 수 없습니다. 에러 코드 :" + matchDetails_responseCode);
                }

            } else {
                System.out.println("Error Response Code: " + matchId_ResponseCode);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }


        }
    }
}