package programmers;

import java.util.*;

public class 신고결과받기 {

    public int[] solution(String[] id_list, String[] report, int k) {

        Map<String, Integer> idListIdxMap = new HashMap<>(); // [key : id], [value : id_list 에서 id 인덱스]
        Map<String, Set<String>> reportedByMap = new HashMap<>(); // [key : 피신고인 id], [value : 신고인 id set]

        // idListIdxMap 에 id 별 id_list 인덱스 값 초기화
        int idx = 0;
        for (String id : id_list) {
            idListIdxMap.put(id, idx++);
        }

        // 전체 리포트 탐색
        for (String r : report) {
            String[] names = r.trim().split(" ");
            String reportedFrom = names[0]; // 신고인 id
            String reportedTo = names[1]; // 피신고인 id

            // 기존 신고인 set 에 id 값(reportedFrom)이 저장되어 있지 않은 경우 add
            reportedByMap.computeIfAbsent(reportedTo, key -> new HashSet<>()).add(reportedFrom);
        }

        int[] answer = new int[id_list.length];

        // reportedByMap 전체 순회하며 정답 배열(answer) 추출
        for (Map.Entry<String, Set<String>> entry : reportedByMap.entrySet()) {
            Set<String> valueSet = entry.getValue(); // 신고인 set

            // k 번 이상 피신고인인 경우, 모든 신고자에게 메일 전송
            // 1. valueSet(신고인 set) 전체 반복
            // 2. idListIdxMap 에서 해당 신고인 id 의 배열 인덱스 get
            // 3. 신고인 메일 전송받은 횟수 update -> answer[신고인 인덱스]++;
            if (valueSet.size() >= k) {
                for (String name : valueSet) {
                    answer[idListIdxMap.get(name)]++;
                }
            }
        }

        return answer;
    }
}
