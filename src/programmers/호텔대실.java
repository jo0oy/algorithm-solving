package programmers;

import java.util.*;

public class 호텔대실 {
    public int solution(String[][] book_time) {

        // 우선순위 큐 : 정렬 기준 --> 예약 시간 오름차순 (예약 시간 같은 경우, 퇴실 시간 오름차순)
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[0] == b[0]) ? a[1] - b[1] : a[0] - b[0]);

        for(String[] time : book_time) {
            String[] sArr = time[0].split(":");
            String[] eArr = time[1].split(":");
            int start = Integer.parseInt(sArr[0]) * 60 + Integer.parseInt(sArr[1]);
            int end = Integer.parseInt(eArr[0]) * 60 + Integer.parseInt(eArr[1]) + 10;

            pq.add(new int[] {start, end});
        }

        // 사용하고 있는 객실 퇴실 시간 우선순위 큐
        PriorityQueue<Integer> rooms = new PriorityQueue<>();
        rooms.add(pq.poll()[1]); // 예약 시간이 가장 이르면서 퇴실 시간이 가장 빠른 객실로 start

        while(!pq.isEmpty()) {
            int[] curr = pq.poll();

            if(curr[0] < rooms.peek()) { // 현재 사용중인 객실의 가장 빠른 퇴실 시간 > 다음 예약 시간
                rooms.add(curr[1]);  // 새로운 객실 필요
            } else {  // 현재 사용중인 객실의 가장 빠른 퇴실 시간 < 다음 예약 시간 : 퇴실 후 객실 사용 가능
                rooms.poll(); // 퇴실
                rooms.add(curr[1]); // 바로 다음 이용하게 될 이용자의 퇴실 시간 add
            }
        }

        return rooms.size();
    }
}
