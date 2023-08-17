package programmers;

import java.util.*;

public class 미로탈출 {

    private int[] dx = {-1, 1, 0, 0};
    private int[] dy = {0, 0, -1, 1};
    // N x M
    private int N;
    private int M;

    public int solution(String[] maps) {
        N = maps.length; M = maps[0].length();

        int[] start = new int[2];
        int[] lever = new int[2];
        int[] exit = new int[2];

        // 시작/레버/출구 위치 탐색 로직
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++) {
                char c = maps[i].charAt(j);

                switch(c) {
                    case 'S':
                        start[0] = i;
                        start[1] = j;
                        break;
                    case 'L' :
                        lever[0] = i;
                        lever[1] = j;
                        break;
                    case 'E':
                        exit[0] = i;
                        exit[1] = j;
                        break;
                }
            }
        }

        int toLever = bfs(maps, start, lever); // 레버까지의 거리

        if(toLever == -1) return -1; // 레버 갈 수 있는 방법이 없는 경우 return -1 후 종료

        int toExit = bfs(maps, lever, exit); // 출구까지의 거리

        return (toExit == -1) ? -1 : (toLever + toExit);
    }

    // s 위치에서 시작해서 t 위치에 도달하는 최소 거리를 구하는 BFS
    int bfs(String[] maps, int[] s, int[] t) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{s[0], s[1], 0});
        boolean[][] visited = new boolean[N][M];
        visited[s[0]][s[1]] = true;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];
            int curData  = cur[2];

            if(curX == t[0] && curY == t[1]) {
                return curData;
            }

            for(int i = 0; i < 4; i++) {
                int nx = curX + dx[i];
                int ny = curY + dy[i];

                if(OOB(nx, ny) || visited[nx][ny])
                    continue;

                if(maps[nx].charAt(ny) != 'X') {
                    visited[nx][ny] = true;
                    q.add(new int[]{nx, ny, curData + 1});
                }
            }
        }

        return -1;
    }

    // x, y 값이 maps 의 범위 Out Of Bound 인지 판별하는 메서드
    boolean OOB(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= M;
    }
}
