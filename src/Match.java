import java.util.Scanner;

import FCController.*;

public class Match {
    public static void main(String[] args) {
        Scanner scanner1 = new Scanner(System.in);

        System.out.println("1 : 선수 정보 조회");
        System.out.println("2 : 최근 경기 MatchID 조회");
        System.out.println("0 : 종료");
        System.out.println("원하는 데이터를 선택하세요. : ");

        int user = scanner1.nextInt();

        if (user == 1) {
            getPlayerInfo();
        } else if (user == 2) {
            System.out.printf("최근 5경기의 MatchID입니다.");
            getMatchId();

            Scanner scanner2 = new Scanner(System.in);

            System.out.println("경기 세부 정보가 필요하시다면 원하는 경기의 MatchID를 입력해주세요. : ");

            String matchDetails_want = scanner2.nextLine();

            // 여기서부터는 선형탐색으로 찾아서 입력한 MatchID와 일치하는게 있으면 세부 정보를 출력해주는 코드 필요
            // getMatchDetails()

        } else if (user == 0) {
            break;
        } else {
            System.out.println("올바른 번호를 입력하세요.");
        }
    }
}