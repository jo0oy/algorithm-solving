package programmers;

public class 숫자문자열과영단어 {

    public int solution(String s) {

        String[] arr = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

        for(int i = 0; i < arr.length; i++){
            s = s.replaceAll(arr[i], String.valueOf(i));
        }

        return Integer.parseInt(s);
    }

}
