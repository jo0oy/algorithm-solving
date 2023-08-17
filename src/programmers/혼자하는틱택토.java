package programmers;

public class 혼자하는틱택토 {

    public int solution(String[] board) {
        int oCnt = 0;
        int xCnt = 0;

        // 'O', 'X'를 각각 카운팅
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                char c = board[i].charAt(j);
                if (c == 'O') oCnt++;
                else if (c == 'X') xCnt++;
            }
        }

        int oWin = winningTimes(board, 'O');
        int xWin = winningTimes(board, 'X');

        // 1. 후공 갯수 > 선공 갯수 or 선공/후공 갯수 차이가 1 이상인 경우
        // 2. 선공, 후공 모두 이긴 경우
        // 3. 선공 이김, 선공 갯수 == 후공 갯수
        // 4. 후공 이김, 선공 갯수 > 후공 갯수
        if(oCnt < xCnt || oCnt - xCnt > 1) return 0;
        else if(oWin > 0 && xWin > 0) return 0;
        else if(oWin > 0) {
            if(oCnt == xCnt) return 0;
        } else if(xWin > 0) {
            if(oCnt > xCnt) return 0;
        }

        return 1;
    }

    private int winningTimes(String[] board, char c) {

        int cnt = 0;
        for (int i = 0; i < 3; i++) {
            boolean flag = true;
            // 가로
            for(int j = 0; j < 3; j++) {
                if(board[i].charAt(j) != c) {
                    flag = false;
                    break;
                }
            }

            if(flag) cnt++;

            // 세로
            flag = true;
            for (int j = 0; j < 3; j++) {
                if(board[j].charAt(i) != c) {
                    flag = false;
                    break;
                }
            }

            if(flag) cnt++;
        }

        // 좌, 우 아래로 향하는 대각선
        if((board[0].charAt(0) == c) && (board[1].charAt(1) == c) && (board[2].charAt(2) == c)) cnt++;
        if((board[0].charAt(2) == c) && (board[1].charAt(1) == c) && (board[2].charAt(0) == c)) cnt++;

        return cnt;
    }
}
