package programmers;

import java.util.*;

public class 당구연습 {

    /** 상/하 대칭점 공을 맞출 수 없는 경우: 시작점과 공의 위치가 y축과 수평
        상 대칭점 : (x,  (n - y) * 2 + y)
        하 대칭점 : (x, (-1) * y)

        좌/우 대칭점 공을 맞출 수 없는 경우: 시작점과 공이 x축과 수평
        좌 대칭점 : ((-1) * x, y)
        우 대칭점 : ((m - x) * 2 + x, y)
     */
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] answer = new int[balls.length];

        int idx = 0;
        for(int[] ball : balls) {

            // 해당 공을 맞출 수 있는 경우의 거리 제곱근을 담을 우선순위 큐
            PriorityQueue<Integer> distQueue = new PriorityQueue<>();

            // 상 대칭
            if(!(startX == ball[0] && startY < ball[1])) {
                distQueue.add(calculateDistance(startX, startY, ball[0], (n - ball[1]) * 2 + ball[1]));
            }

            // 하 대칭
            if(!(startX == ball[0] && startY > ball[1])) {
                distQueue.add(calculateDistance(startX, startY, ball[0], (-1) * ball[1]));
            }

            // 좌 대칭
            if(!(startY == ball[1] && startX > ball[0])) {
                distQueue.add(calculateDistance(startX, startY, (-1) * ball[0], ball[1]));
            }

            // 우 대칭
            if(!(startY == ball[1] && startX < ball[0])) {
                distQueue.add(calculateDistance(startX, startY, (m - ball[0]) * 2 + ball[0], ball[1]));
            }

            if(distQueue.isEmpty()) continue;

            // 기본 우선순위 큐 --> 오름차순 정렬
            answer[idx++] = distQueue.poll();
        }

        return answer;
    }

    // 시작점과 대칭점 사이의 거리의 제곱근 계산 메서드
    int calculateDistance(int startX, int startY, int x, int y) {
        return (int) (Math.pow(Math.abs(startX - x), 2) + Math.pow(Math.abs(startY - y), 2));
    }
}
