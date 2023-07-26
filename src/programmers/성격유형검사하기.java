package programmers;

import java.util.*;

public class 성격유형검사하기 {

    public String solution(String[] survey, int[] choices) {

        // 선택지에 따른 점수 배열, 비동의 <-> 동의
        int[] scores = {3, 2, 1, 0, 1, 2, 3};

        // 1 ~ 4번 지표별 성격 유형 종류 저장한 2차원 배열, 사전순으로 저장됨
        char[][] categories = {{'R', 'T'}, {'C', 'F'}, {'J', 'M'}, {'A', 'N'}};

        // 각 유형별 누적 획득 점수 저장할 map 선언 및 점수 0으로 초기화
        Map<Character, Integer> map = new HashMap<>();
        for(char[] cat : categories) {
            map.put(cat[0], 0);
            map.put(cat[1], 0);
        }

        // 첫번째 문자: 비동의
        // 두번째 문자: 동의
        int idx = 0;
        for(String s : survey) {
            char disagree = s.charAt(0); // 비동의
            char agree = s.charAt(1); // 동의

            int choice = choices[idx++] - 1; // 고른 선택지 (0 ~ 6)

            if(choice < 3) { // 비동의
                int tmp = map.get(disagree);
                map.put(disagree, tmp + scores[choice]);
            } else if(choice > 3) { // 동의
                int tmp = map.get(agree);
                map.put(agree, tmp + scores[choice]);
            }
        }

        StringBuilder sb = new StringBuilder();

        // 누적 점수를 저장한 map 에서 1~4 지표별 점수에 따라 성격 유형완성
        // 1. 높은 점수 유형이 우선
        // 2. 두 유형 점수가 같은 경우 사전순
        for(char[] cat : categories) {
            int first_score = map.get(cat[0]);
            int second_score = map.get(cat[1]);

            if(first_score >= second_score) sb.append(cat[0]);
            else sb.append(cat[1]);
        }

        return sb.toString();
    }
}
