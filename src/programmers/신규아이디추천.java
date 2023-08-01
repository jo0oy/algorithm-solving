package programmers;

public class 신규아이디추천 {

    public String solution(String new_id) {

        String excludeRegex = "[^-_.a-z0-9]"; // 영소문자, 숫자, '.', '-', '_' 제외
        String periodRegex = "[.]{2,}"; // '.' 연속 2번 이상
        String startEndRegex = "^[.]|[.]$"; // 시작과 끝 '.'

        String id = new_id.toLowerCase() // 1단계
                .replaceAll(excludeRegex, "") // 2단계) 영소문자, 숫자, 마침표, '-', '_' 를 제외
                .replaceAll(periodRegex, ".") // 3단계) '.' 연속 2번 이상인 경우 하나로 치환
                .replaceAll(startEndRegex, ""); // 4단계) 시작/끝 '.' 제거

        if(id.equals("")) id += "a"; // 5단계) 빈 문자열인 경우 'a' 삽입

        StringBuilder sb = new StringBuilder(id);
        // 6단계
        if(sb.length() >= 16) { // (문자열 idx : 0 ~ 14) = 15자
            sb.delete(15, sb.length()); // 6단계-1) 15자 이외 뒤의 문자 모두 제거

            int idx = sb.length() - 1;
            if(sb.charAt(idx) == '.') sb.deleteCharAt(idx); // 6단계-2) 마지막 문자가 마침표인 경우 제거
        }

        // 7단계
        // 길이가 2자 이하인 경우, id의 마지막 문자를 3자 이상 될때까지 반복하여 끝에 추가
        if(sb.length() <= 2) {
            while (sb.length() < 3) {
                char ch = sb.charAt(sb.length() - 1); // 마지막 문자
                sb.append(ch);
            }
        }

        return sb.toString();
    }

}
