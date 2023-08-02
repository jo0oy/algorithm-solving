package programmers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class 키패드누르기 {

    private Map<Integer, int[]> keyPadIdx = new HashMap<>(); // 키패드 숫자별 (x, y) 좌표 위치 map (3 * 3 배열)
    private Set<Integer> left = new HashSet<>(); // 왼손으로 누르는 번호 모음 set
    private Set<Integer> right = new HashSet<>(); // 오른손으로 누르는 번호 모음 set

    // 위의 map, set 초기화 메서드
    public void init() {
        keyPadIdx.put(1, new int[]{0, 0});
        keyPadIdx.put(2, new int[]{0, 1});
        keyPadIdx.put(3, new int[]{0, 2});
        keyPadIdx.put(4, new int[]{1, 0});
        keyPadIdx.put(5, new int[]{1, 1});
        keyPadIdx.put(6, new int[]{1, 2});
        keyPadIdx.put(7, new int[]{2, 0});
        keyPadIdx.put(8, new int[]{2, 1});
        keyPadIdx.put(9, new int[]{2, 2});
        keyPadIdx.put(0, new int[]{3, 1});

        left.add(1); left.add(4); left.add(7);
        right.add(3); right.add(6); right.add(9);
    }

    public String solution(int[] numbers, String hand) {

        init();
        int[] leftLoc = {3, 0}; // 왼손 start 위치 : '*'
        int[] rightLoc = {3, 2}; // 오른손 start 위치: '#'

        StringBuilder sb = new StringBuilder();
        for (int number : numbers) {
            char type; // 누르게 되는 손 (L or R)
            int[] idx = keyPadIdx.get(number); // number 의 인덱스 위치

            /** 1. 왼손/오른손 이미 정해진 번호 --> left/right set 에서 확인 후 type 결정
                2. 그렇지 않은 2, 5, 8, 0 (중양열 번호) 인 경우
                    2-1) 현재 왼/오른손 위치와 눌러야하는 번호의 거리 계산
                    2-2) 왼/오른손 거리 간격 중 더 가까운 거리에 위치한 손으로 type 결정
                    2-3) 양손 거리가 같은 경우, 왼/오른손잡이 여부로 type 결정
             */
            if(left.contains(number)) type = 'L';
            else if(right.contains(number)) type = 'R';
            else {
                int distLeft = Math.abs(leftLoc[0] - idx[0]) + Math.abs(leftLoc[1] - idx[1]); // 현재 왼손과의 거리
                int distRight = Math.abs(rightLoc[0] - idx[0]) + Math.abs(rightLoc[1] - idx[1]); // 현재 오른손과의 거리

                if(distLeft < distRight) type = 'L';
                else if (distLeft > distRight) type = 'R';
                else {
                    if(hand.equals("left")) type = 'L';
                    else type = 'R';
                }
            }

            sb.append(type);

            // type 에 결정된 손에 따라 현재 위치 reset
            if(type == 'L') leftLoc = idx.clone();
            else rightLoc = idx.clone();
        }

        return sb.toString();
    }
}
