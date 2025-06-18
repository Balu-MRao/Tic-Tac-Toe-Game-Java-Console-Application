import java.util.Scanner;

class TicTacMaking{
    //Declare a character board
    static char[][] board;
    
    //Initialize a empty 3x3 board with empty characters
    public TicTacMaking(){
        board = new char[3][3];
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[i].length; j++){
                board[i][j] = ' ';
            }
        }
    }

    //Method for display board 
    static void display(){
        System.out.println("-------------");
        for(int i=0; i<board.length; i++){
            System.out.print("| ");
            for(int j=0; j<board[i].length; j++){
                System.out.print(board[i][j] + " ");
                System.out.print("| ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    //checking for column win 
    static boolean colWin(){
        for(int j=0; j<board.length;j++){
            if(board[0][j]!= ' ' && board[0][j] == board[1][j] && board[1][j] == board[2][j]){
                return true;
            }
        }
        return false;
    }
    
    //checking for row win 
    static boolean rowWin(){
        for(int i=0; i<board.length;i++){
            if(board[i][0]!= ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2]){
                return true;
            }
        }
        return false;
    }
    
    //checking for diagnol win 
    static boolean diaWin(){
        if((board[0][0]!= ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) || (board[0][2]!= ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0])){
            return true;
        }
        return false;
    }

    //Method to check Draw condition
    static boolean drawCheck(){
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[i].length; j++){
                if(board[i][j] == ' '){
                    return false;
                }
            }
        }
        return true;
    }
}

class Humanplayer{

    String name;
    char mark;
    Scanner scan = new Scanner(System.in);

    //Constructor to initialize name and mark
    public Humanplayer(String name, char mark){
        this.name = name;
        this.mark = mark;
    }

    //Marking moves of players 
    int row, col;
    void markMove(){
        System.out.println("Enter the row and col: ");
        do{
        row = scan.nextInt();
        col = scan.nextInt();
        }while(!isValidMove(row, col));
        TicTacMaking.board[row][col] = mark;
    }

    //Checking the condition for position is valid or invalid
    boolean isValidMove(int row, int col){
        if(row >=0 && row <= 2 && col >=0 && col <= 2 && TicTacMaking.board[row][col] == ' '){
            return true;
        }
        System.out.println("Invalid Position Renter row and col: ");
        return false;
    }

}

public class TicTacToe {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        TicTacMaking tictactoe = new TicTacMaking();

        //Get the first player name and mark
        System.out.println("Enter the player1 name and mark:");
        String name1 = scan.nextLine();
        char mark1 = scan.next().charAt(0);
        
        Humanplayer p1 = new Humanplayer(name1, mark1);
        
        scan.nextLine();
        
        //Get the second player name and give remain mark for for player
        System.out.println("Enter the player2 name: ");
        String name2 = scan.nextLine();
        char mark2;
        if(mark1 == 'X'){
            mark2 = 'O';
        }
        else{
            mark2 = 'X';
        }
        
        Humanplayer p2 = new Humanplayer(name2, mark2);
        
        //Initialize currentPlayer reference to player1 in starting
        Humanplayer currentPlayer;
        currentPlayer = p1;

        //continues loop until anyone of the player win
        while (true) {
        TicTacMaking.display();
        System.out.println(                                           );
        System.out.println(currentPlayer.name + " turn: ");
        currentPlayer.markMove();

        if(TicTacMaking.colWin() || TicTacMaking.rowWin() || TicTacMaking.diaWin()){
            System.out.println(currentPlayer.name +" has won");
            break;

        }

        else if(TicTacMaking.drawCheck()){
            System.out.println("Game is draw");
            break;
        }

        if(currentPlayer == p1){
            currentPlayer = p2;
        }
        else{
            currentPlayer = p1;
        }

        }
        scan.close();
    }
}