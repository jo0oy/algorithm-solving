package programmers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class 개인정보_수집_유효기간 {

    public int[] solution(String today, String[] terms, String[] privacies) {

        List<Integer> answerList = new ArrayList<>(); // 정답 리스트 초기화
        Map<String, Integer> term_map = new HashMap<>(); // 약관별 유효기한 저장 map 초기화

        // 'yyyy.MM.dd' 패턴 문자열 -> LocalDate 로 파싱하기 위한 DateTimeFormatter 선언
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        LocalDate today_date = LocalDate.parse(today, formatter); // 입력받은 today 문자열, LocalDate 타입으로 파싱

        // term_map 초기화 (약관별 유효기한 정보 삽입)
        for(String term : terms) {
            String[] temp = term.trim().split(" ");
            term_map.put(temp[0], Integer.parseInt(temp[1]));
        }

        int idx = 0;
        // 파기할 약관 탐색
        for(String privacy : privacies) {

            String[] temp = privacy.trim().split(" ");

            LocalDate start_date = LocalDate.parse(temp[0], formatter); // 시작일
            int term_gap = term_map.get(temp[1]); // term_map 에 저장된 약관의 유효기한 구하기

            // 약관 파기일 계산 --> (시작일 + 유효기한 개월수 (ex. 6달))
            LocalDate end_date = start_date.plusMonths(term_gap);

            // (모든 달 = 28일) 이기 때문에 29, 30, 31일은 28일로 수정
            if(end_date.getDayOfMonth() > 28) {
                int y = end_date.getYear();
                int m = end_date.getMonthValue();

                end_date = LocalDate.of(y, m, 28);
            }

            // 파기일이 'today' 와 같거나 이전인 경우 answerList 에 추가!
            if(end_date.isBefore(today_date) || end_date.isEqual(today_date)) {
                answerList.add(idx + 1);
            }

            idx++;
        }

        return answerList.stream().mapToInt(i -> i).toArray();
    }

}
