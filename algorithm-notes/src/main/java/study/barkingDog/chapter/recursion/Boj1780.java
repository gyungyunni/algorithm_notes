package study.barkingDog.chapter.recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1780 {
    public static int[][] board;
    public static int mOne = 0;		// -1
    public static int zero = 0;	// 0
    public static int one = 0;	// 1
    static int cnt1, cnt2, cnt3;

    public static void func(int r, int c, int size) { //현재함수에 해당하는 종이의 시작좌표, 한변의 길
        if(size==1) { //한변이 1이면 해당하는 숫자에 갯수 1개 추가하고 리턴
            if(board[r][c]==-1) mOne++;
            else if(board[r][c]==0) zero++;
            else one++;

            return;
        }

        cnt1=0; cnt2=0; cnt3=0; //현재 함수에 해당하는 종이에서 각각의 원소 갯수 확인

        for(int i = r; i < r+size; i++) {
            for(int j = c; j < c+size; j++) {
                if(board[i][j]==-1) cnt1++;
                else if(board[i][j]==0) cnt2++;
                else cnt3++;
            }
        }

        int multi=size*size; //종이에서의 원소 전체 갯수
        if(cnt1==multi || cnt2==multi || cnt3==multi) { // 현재함수에 해당하는 종이가 모든 같은 수로 되어있는 경우
            if(cnt1==multi) mOne++;
            else if(cnt2==multi) zero++;
            else one++;
        }else { // 다 같은수가 아닌 경우
            int newSize = size/3;
            for(int i = 0; i < 3; i++) {
                for(int j=0; j<3; j++) {
                    func(r + i*newSize, c + j*newSize, newSize); // 9등분으로 나눠서 재귀함수 진행
                }
            }

        }

    }
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        StringTokenizer st;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        func(0,0, N);
        System.out.println(mOne);
        System.out.println(zero);
        System.out.println(one);
    }
}
