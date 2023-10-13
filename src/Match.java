import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Match {
    public static void main(String[] args) {
        try {
            String Find_Matchid = "https://public.api.nexon.com/openapi/fconline/v1.0/matches?matchtype=50&offset=0&limit=100&orderby=desc";

            URL Find_Matchid_URL = new URL(Find_Matchid);
            HttpURLConnection connection_of_Find_Matchid = (HttpURLConnection) Find_Matchid_URL.openConnection();
            connection_of_Find_Matchid.setRequestMethod("GET");
            connection_of_Find_Matchid.setRequestProperty("Authorization", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJYLUFwcC1SYXRlLUxpbWl0IjoiNTAwOjEwIiwiYWNjb3VudF9pZCI6IjE1NjA3OTAxOTEiLCJhdXRoX2lkIjoiMiIsImV4cCI6MTcwNDI2NzY4NiwiaWF0IjoxNjg4NzE1Njg2LCJuYmYiOjE2ODg3MTU2ODYsInNlcnZpY2VfaWQiOiI0MzAwMTE0ODEiLCJ0b2tlbl90eXBlIjoiQWNjZXNzVG9rZW4ifQ.kbqI0QMNJWZLNhwsKPUmsWn-jtkpiwIFyWt4E9kDM7E");

            int responseCode_of_Matchid = connection_of_Find_Matchid.getResponseCode();

            if (responseCode_of_Matchid == 200) {
                BufferedReader reader_of_Matchid = new BufferedReader(new InputStreamReader(connection_of_Find_Matchid.getInputStream()));
                String Match_ID;
                StringBuilder response_of_Find_Matchid = new StringBuilder();

                while ((Match_ID = reader_of_Matchid.readLine()) != null) {
                    response_of_Find_Matchid.append(Match_ID);
                }

                reader_of_Matchid.close();
                connection_of_Find_Matchid.disconnect();


                System.out.println("최근 10경기를 불러옵니다.");

                String Match_ID_response = response_of_Find_Matchid.toString();
                String[] Match_ID_response_array = Match_ID_response.split(",");

                if (Match_ID_response_array.length >= 11) {
                    for (int i = 0; i < 11; i++) {
                        System.out.println(i + ": " + Match_ID_response_array[i]);
                    }
                } else {
                    System.out.println("데이터가 10개 미만입니다.");
                }

                System.out.println("가장 최근 경기 데이터를 불러옵니다.");

                String Match_ID_processed = new String(Match_ID_response_array[10]);
                Match_ID_processed = Match_ID_processed.substring(1, Match_ID_processed.length() - 1);


                String MatchUrl = "https://public.api.nexon.com/openapi/fconline/v1.0/matches/" + Match_ID_processed;

                URL Match_details_URL = new URL(MatchUrl);
                HttpURLConnection connection_of_Match_details = (HttpURLConnection) Match_details_URL.openConnection();
                connection_of_Match_details.setRequestMethod("GET");
                connection_of_Match_details.setRequestProperty("Authorization", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJYLUFwcC1SYXRlLUxpbWl0IjoiNTAwOjEwIiwiYWNjb3VudF9pZCI6IjE1NjA3OTAxOTEiLCJhdXRoX2lkIjoiMiIsImV4cCI6MTcwNDI2NzY4NiwiaWF0IjoxNjg4NzE1Njg2LCJuYmYiOjE2ODg3MTU2ODYsInNlcnZpY2VfaWQiOiI0MzAwMTE0ODEiLCJ0b2tlbl90eXBlIjoiQWNjZXNzVG9rZW4ifQ.kbqI0QMNJWZLNhwsKPUmsWn-jtkpiwIFyWt4E9kDM7E");
                int responseCode_of_Match_details = connection_of_Match_details.getResponseCode();

                if (responseCode_of_Match_details == 200) {
                    BufferedReader reader_of_Match_details = new BufferedReader(new InputStreamReader(connection_of_Match_details.getInputStream()));
                    String Match_detail;
                    StringBuilder response_of_Match_detail = new StringBuilder();

                    while ((Match_detail = reader_of_Match_details.readLine()) != null) {
                        response_of_Match_detail.append(Match_detail);
                    }

                    System.out.println(response_of_Match_detail);


                    reader_of_Match_details.close();
                    connection_of_Match_details.disconnect();


                } else {
                    System.out.println("데이터를 불러올 수 없습니다. 에러 코드 :" + responseCode_of_Match_details);
                }

            } else {
                System.out.println("Error Response Code: " + responseCode_of_Matchid);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}