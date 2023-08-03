package programmers;

import java.util.Stack;

public class 크레인인형뽑기게임 {

    public int solution(int[][] board, int[] moves) {
        int N = board.length;
        int[] top = new int[N]; // 각 col 의 가장 위에 위치한 인형의 row 인덱스 저장하는 배열

        for (int i = 0; i < N; i++) { // i = col (열) 인덱스
            int onTop = 100; // 현재 col 에서 가장 위에 위치한 인형의 row 인덱스, 없으면 100 (N을 초과하는 임의의 큰 수)
            for (int j = 0; j < N; j++) { // j = row (행) 인덱스, 방향 top -> down

                if(board[j][i] > 0) { // 가장 처음 인형이 나타는 순간
                    onTop = j;
                    break;
                }
            }
            top[i] = onTop;
        }

        int answer = 0; // 없어지는 인형의 수 counter
        Stack<Integer> st = new Stack<>(); // 인형 저장 바구니

        for (int move : moves) {
            int colIdx = move - 1; // col 인덱스
            int topRowIdx = top[colIdx]; // 해당 col 의 가장 위에 있는 인형의 row 인덱스 위치

            if(topRowIdx >= N) continue; // row 인덱스가 범위 이상일 경우, col 에 인형이 없음 -> 아무일 일어나지 않음

            int num = board[topRowIdx][colIdx]; // 인형의 종류

            // 1. 바구니가 비어있지 않고,
            // 2. 가장 위에 위치한 인형이 현재 인형과 같을 경우
            // 3. 인형 사라짐.
            // 4. 그렇지 않은 경우, 바구니에 넣음.
            if (!st.isEmpty() && st.peek() == num) {
                st.pop();
                answer += 2; // 사라진 인형 수만큼 누적
            } else st.push(num);

            top[colIdx]++; // 인형을 바구니로 옮겼으므로, 해당 col 의 가장 위에 있는 인형 row 위치 변경
        }

        return answer;
    }
}
