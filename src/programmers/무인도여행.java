package programmers;

import java.util.*;

public class 무인도여행 {

    private final int[][] dArr = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private int N; private int M;

    public int[] solution(String[] maps) {
        N = maps.length; M = maps[0].length();

        boolean[][] visited = new boolean[N][M];
        List<Integer> list = new ArrayList<>();

        // 구역별로 bfs 탐색하면서 구역의 총 머물 수 있는 일수 리턴 --> list 에 add
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                char c = maps[i].charAt(j);
                if(!visited[i][j] && c != 'X') { // 방문하지 않았고, 'X'가 아닌 위치를 시작으로 구역 탐색
                    list.add(bfs(maps, visited, i, j));
                }
            }
        }

        if(list.size() == 0) return new int[] {-1}; // list 가 비어있다면, 지낼 수 없는 무인도 없음 : -1 리턴

        Collections.sort(list); // 오름차순 정렬

        int[] answer = new int[list.size()];
        int idx = 0;
        for(int n : list) {
            answer[idx++] = n;
        }

        return answer;
    }

    int bfs(String[] maps, boolean[][] visited, int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {x, y});
        visited[x][y] = true;
        int cnt = maps[x].charAt(y) - '0';

        while(!q.isEmpty()) {
            int[] curr = q.poll();

            for(int[] d : dArr) {
                int nx = curr[0] + d[0];
                int ny = curr[1] + d[1];

                if(nx < 0 || nx >= N || ny < 0 || ny >= M ||
                        visited[nx][ny] || maps[nx].charAt(ny) == 'X') continue;

                q.add(new int[] {nx, ny});
                visited[nx][ny] = true;
                cnt += maps[nx].charAt(ny) - '0';
            }
        }

        return cnt;
    }
}
