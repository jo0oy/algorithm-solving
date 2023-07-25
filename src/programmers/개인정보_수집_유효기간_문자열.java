package programmers;

import java.util.*;

public class 개인정보_수집_유효기간_문자열 {
    public int[] solution(String today, String[] terms, String[] privacies) {

        // 정답 리스트 초기화
        List<Integer> answerList = new ArrayList<>();

        // 약관별 유효기한 저장 map 초기화
        Map<String, Integer> term_map = new HashMap<>();

        // 문자열 'today' year/month/day 로 split 후 int 변환
        String[] today_arr = today.split("\\.");
        int today_year = Integer.parseInt(today_arr[0]);
        int today_month = Integer.parseInt(today_arr[1]);
        int today_day = Integer.parseInt(today_arr[2]);

        // 약관별 유효기간 저장 map (term_map) 에 입력받은 정보 삽입
        for (String term : terms) {
            String[] temp = term.trim().split(" ");
            term_map.put(temp[0], Integer.parseInt(temp[1]));
        }

        int idx = 0;

        // 반복하며 파기할 개인정보 추출
        for (String privacy : privacies) {
            String[] temp = privacy.trim().split(" ");

            // 문자열로 주어진 시작일 year/month/day 로 split
            String[] start = temp[0].split("\\.");

            // 시작일 날짜 년도/월/일 int 형 변환
            int s_year = Integer.parseInt(start[0]);
            int s_month = Integer.parseInt(start[1]);
            int s_day = Integer.parseInt(start[2]);

            int term_gap = term_map.get(temp[1]); // term_map 에서 약관 유효기간 get

            // 파기일 year = 시작일 year + (유효기간 개월 수 중 12개월 단위 갯수: 년도가 바뀌는 횟수)
            // 파기일 month = 시작일 month + (유효기간 개월 수 중 년도 바뀌는 월의 수를 제외한 달수)
            int e_year = s_year + (term_gap / 12);
            int e_month = s_month + (term_gap % 12);

            if(e_month > 12) { // (시작일 month + 유효기간 plus month) 가 12 초과인 경우 : year 이 올림돼어야 한다.
                e_year += (e_month / 12);
                e_month %= 12;
            }

            int num = (idx++) + 1; // 개인정보 번호

            // 'today' 와 비교 --> 파기일이 오늘과 같거나 오늘보다 작은 경우, 정답 리스트에 추가!
            if (e_year == today_year) {
                if(e_month == today_month) {
                    if(s_day <= today_day) answerList.add(num);
                } else if(e_month < today_month) answerList.add(num);

            } else if(e_year < today_year){
                answerList.add(num);
            }
        }

        return answerList.stream().mapToInt(i -> i).toArray();
    }
}
