package programmers;

public class 주식가격 {

    public int[] solution(int[] prices) {

        int len = prices.length;
        int[] answer = new int[len];
        answer[len - 1] = 0; // 마지막 배열 인덱스는 항상 0초일 것이므로, 0 대입

        for(int i = 0; i < len - 1; i++) { // 마지막 초의 가격은 비교할 필요 없음.
            int cur_price = prices[i];

            boolean flag = false; // 가장 처음 가격이 떨어지는 순간이 존재하는지 체크하는 flag

            for(int j = i + 1; j < len; j++) { // i 초 이후 부터 끝까지 비교

                // 가장 처음 주식 가격이 떨어지는 순간
                // 1. 떨어지기 직전 가격이 유지되었던 기간 answer 배열에 저장
                // 2. flag = true
                // 3. 더 이상 반복할 필요 없으므로 break
                if(cur_price > prices[j]) {
                    answer[i] = j - i;
                    flag = true;
                    break;
                }
            }

            if(!flag) { // 한번도 가격이 떨어지지 않은 경우, 그 기간 계산해서 저장
                answer[i] = len - i - 1;
            }
        }

        return answer;
    }

}
