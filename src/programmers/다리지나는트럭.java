package programmers;

import java.util.LinkedList;
import java.util.Queue;

public class 다리지나는트럭 {

    public int solution(int bridge_length, int weight, int[] truck_weights) {

        Queue<Integer> queue = new LinkedList<>(); // 다리 위에 있는 트럭 큐 (limit size = bridge_length)

        int weight_sum = 0; // 현재 다리 위에 있는 트럭의 총 무게
        int time = 0; // 대기하는 트럭을 순회하는데 걸린 시간
        int idx = 0; // 대기하는 트럭 배열 인덱스

        while(idx < truck_weights.length) { // 대기열을 차례로 순회

            int cur = truck_weights[idx]; // idx 위치의 대기하고 있는 트럭

            // 제한된 길이에 도달하면 맨 앞에 트럭 제거
            if(queue.size() >= bridge_length) {
                int front = queue.poll();
                weight_sum -= front;
            }

            // 총 트럭 무게 초과 여부 확인
            // 1. 초과하는 경우 0 삽입
            // 2. 이하인 경우 현재 트럭을 queue 에 삽입, idx 증가
            if((weight_sum + cur) > weight) {
                queue.add(0);
            } else {
                queue.add(cur);
                weight_sum += cur;
                idx++;
            }

            time++; // 시간은 트럭을 다리 위로 보내든 안보내든 흘러가기 때문에 증가
        }

        return time + bridge_length; // 다리 위에 올라가는 시간 + 다리 위 대기열에서 완전히 빠져나오는데 걸리는 시간
    }

}
