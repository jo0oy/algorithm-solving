package programmers;

import java.util.*;

public class 프로세스 {

    public int solution(int[] priorities, int location) {
        List<int[]> list = new LinkedList<>(); // '우선순위'와 '순서' 정보를 담은 배열을 담은 리스트
        for (int i = 0; i < priorities.length; i++) {
            list.add(new int[]{priorities[i], i});
        }

        // 우선순위 큐 선언 및 초기화
        PriorityQueue<int[]> pq
                = new PriorityQueue<>((o1, o2) -> o2[0] - o1[0]); // 우선순위 내림차순 정렬
        pq.addAll(list);

        int result = 0; // 실행 순서 num

        while (!list.isEmpty()) {
            int[] cur = list.get(0); // 리스트 가장 앞단 : 현재 current
            int[] peek = Objects.requireNonNull(pq.peek()); // 우선순위가 가장 높은 프로세스

            boolean flag = false; // peek 와 우선순위가 같은 프로세스인지 체크하는 flag

            list.remove(0);

            if (cur[0] != peek[0]) { // 우선순위가 같지 않은 경우 다시 리스트 끝에 add
                list.add(cur);
            } else { // 현재 최상의 우선순위와 같은 경우
                pq.poll();
                flag = true; // 실행 여부 true
                result++; // 실행 순서 증가
            }

            if(cur[1] == location && flag) break; // 실행 가능 여부가 true && 현재 위치 == location
        }

        return result;
    }
}
