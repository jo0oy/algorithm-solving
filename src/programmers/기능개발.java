package programmers;

import java.util.*;

public class 기능개발 {

    public int[] solution(int[] progresses, int[] speeds) {
        Queue<Integer> queue = new LinkedList<>(); // 각 기능 100% 달성까지 남은 일수를 순서대로 담은 큐

        // queue 초기화
        int idx = 0;
        for (int progress : progresses) {
            int days = (int) Math.ceil((double) (100 - progress) / speeds[idx++]); // 진도 100% 달성까지 남은 일수 계산
            queue.add(days);
        }

        List<Integer> answerList = new ArrayList<>(); // 임시 정답 리스트
        int before = queue.poll(); // current(현재의 값) 직전의 남은 일 수 중 가장 큰 수, 초기값: 1번째 기능의 남은 일수
        int cnt = 1; // 한번에 배포될 기능 갯수 누적 카운터

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            if (cur <= before) { // 현재 남은 일수가 before 보다 작은 경우 before 과 함께 배포
                cnt++;
            } else {
                // 직전 남은 최대 일수보다 현재 남은 일수가 클 경우
                // 이전까지 누적된 배포할 기능 갯수 정답 리스트에 삽입 후
                // before, cnt 초기화
                answerList.add(cnt);
                before = cur;
                cnt = 1;
            }
        }
        answerList.add(cnt); // 마지막으로 배포할 나머지 기능 총 갯수

        // 정답 배열로 옮기기
        int[] answer = new int[answerList.size()];
        idx = 0;
        for (int num : answerList) {
            answer[idx++] = num;
        }

        return answer;
    }
}
