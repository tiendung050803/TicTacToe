import java.util.Scanner;

enum CellStatus {
    X, O, EMPTY
}

class Cell {
    int row = 3, col = 3;
    CellStatus[][] cell = new CellStatus[row][col];
    Cell() {
        for(int i = 0; i < row; i++)
            for(int j = 0; j < col; j++)
                cell[i][j] = CellStatus.EMPTY;
    }
    void input(int who) {
        if(who == 0) System.out.print("X turn: ");
        else System.out.print("O turn: ");
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt(), y = scanner.nextInt();
        if(x < 0 || y < 0 || x > 2 || y > 2 || cell[x][y] != CellStatus.EMPTY) {
            System.out.println("Invalid positition! Retry");
            input(who);
            return;
        }
        else if(who == 0 && cell[x][y] == CellStatus.EMPTY) {
            cell[x][y] = CellStatus.X;
            who = 1 - who;
        }
        else if(who == 1 && cell[x][y] == CellStatus.EMPTY) {
            cell[x][y] = CellStatus.O;
            who = 1 - who;
        }
        return;

    }
}

class Board {
    void display(Cell ele) {
        int row = 3, col = 3;
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(ele.cell[i][j] == CellStatus.O) System.out.print("[O]");
                if(ele.cell[i][j] == CellStatus.X) System.out.print("[X]");
                if(ele.cell[i][j] == CellStatus.EMPTY) System.out.print("[ ]");
            }
            System.out.print("\n");
        }
    }
}


public class Game{
    static int row = 3, col = 3;
    static CellStatus check(Cell ele) {
        for(int i = 0; i < row; i++) {
            if(ele.cell[i][0] == ele.cell[i][1] && ele.cell[i][2] == ele.cell[i][1] && ele.cell[i][1] != CellStatus.EMPTY) return ele.cell[i][0];
        }
        for(int j = 0; j < col; j++) {
            if(ele.cell[0][j] == ele.cell[1][j] && ele.cell[1][j] == ele.cell[2][j] && ele.cell[2][j] != CellStatus.EMPTY) return ele.cell[2][j];
        }
        if(ele.cell[0][0] == ele.cell[1][1] && ele.cell[2][2] == ele.cell[1][1] && ele.cell[1][1] != CellStatus.EMPTY) return ele.cell[0][0];
        if(ele.cell[0][2] == ele.cell[1][1] && ele.cell[2][0] == ele.cell[1][1] && ele.cell[1][1] != CellStatus.EMPTY) return ele.cell[0][2];
        return CellStatus.EMPTY;
    }
    public static void main(String[] args) {
        Board board = new Board();
        Cell ele = new Cell();
        int player = 1, cnt = 0;
        while (true) {
            cnt++;
            player = 1 - player;
            board.display(ele);
            CellStatus status = check(ele);
            if(status != CellStatus.EMPTY) {
                System.out.print(status + " Wins!");
                return;
            }
            else if(status == CellStatus.EMPTY && cnt == 10) {
                System.out.println("Tie!");
                return;
            }
            ele.input(player);
        }
    }
}
