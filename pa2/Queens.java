//Name:Surya Rani CruziD: srani@ucsc.edu Class: 12b and 12M Date: 2/5/19  
//Description: This program will be able to create a n by n chess board and place 9 queens on it without them being able to kill each other and will return either just
// the number of solutions or both the number of solutions and what column each queen is on for each row
//File Name: Queens.java

import java.io.*;

public class Queens{
	
	public static void main(String[] args) throws IOException{
	int n=0;
	String mode="";
	
	//if arguments inputted are less than 1 or greater than 2 then stop the program
	if(args.length < 1 || args.length > 2){
		System.out.println("Usage: Queens [-v] number");
		System.out.println("Option: -v verbose output, print all solutions");
		System.exit(1);
	}
	//if argument length is 2 it checks if the first arg is not verbose and second argument is not an int if both true then it stops the program
	if(args.length == 2 && (!args[0].equals("-v") || !isNum(args[1]))){
			System.out.println("Usage: Queens [-v] number");
			System.out.println("Option: -v verbose output, print all solutions");
			System.exit(1);
	}
	//if args length is 1 and the arg is not an int then it stops the program
	if(args.length == 1 && !isNum(args[0])){
			System.out.println("Usage: Queens [-v] number");
			System.out.println("Option: -v verbose output, print all solutions");	
			System.exit(1);
	}
	//nominal execution with 1 argument 
	if(args.length ==1){
		n=Integer.parseInt(args[0]);
	}
	//nominal program execution with 2 arguments and changing the mode to verbose and setting n to the second argument
	if(args.length == 2){
		n=Integer.parseInt(args[1]);
		mode = "verbose";
	}
	//filling array with zeros
	int[][] board = new int[n+1][n+1];
	for(int i =0; i<board[0].length;i++){
		for(int j=0; j<board.length;j++){
			board[i][j] = 0;
		}
	}

	//executing program
	System.out.println(n + "-Queens has " + findSolutions(board,1,mode) + " solutions");
	/*
	placeQueen(board, 1,2);
	for(int i =0; i<board[0].length;i++){
		for(int j=0; j<board.length;j++){
			System.out.print(board[i][j]);
		}
		System.out.println();
	}
	*/

}
//isNum()
//figuring out whether something is a int or not
static boolean isNum(String str) {
    try {
        Integer.parseInt(str);
        return true;
    } catch (NumberFormatException e) {
        return false;
    }
}
//placeQueen()
//placing queens on the chess board
static void placeQueen(int[][] B, int i, int j){
	B[i][j]++;
	B[i][0] = j;
	int x = i+1;
	int y = j;
	int a=i+1;
	int b=j+1;
	int c= i+1;
	int d= j-1;

	while(x<B.length && y<=B.length){
		B[x][y]--;
		x++;
	}

	while(a<B.length && b<B.length){
		B[a][b]--;
		a++;
		b++;
	}

	while(c<B.length && d>=1){
		B[c][d]--;
		c++;
		d--;
	}
}
//removeQueen()
//removing a queen from the chess board 
static void removeQueen(int[][] B, int i, int j){
	B[i][j]--;
	B[i][0] = 0;
	int x = i+1;
	int y = j;
	int a=i+1;
	int b=j+1;
	int c= i+1;
	int d= j-1;

	while(x<B.length && y<B.length){
		B[x][y]++;
		x++;
	}

	while(a<B.length && b<B.length){
		B[a][b]++;
		a++;
		b++;
	}

	while(c<B.length && d>=1){
		B[c][d]++;
		c++;
		d--;
	}
}
//printBoard()
//prints out what columnn each queen is located on for each row
static void printBoard(int[][] B){
	System.out.print("(");
	for(int i=1; i<B.length-1;i++){
		System.out.print(B[i][0] + ", ");
	}
	System.out.print(B[B.length-1][0] + ")");
	System.out.println();
}

static int findSolutions(int[][] B, int i, String mode){
	int sum =0;

	if(i>B.length-1){
		if(mode.equals("verbose")){
		printBoard(B);
	}
	return 1;
	}
	else{
	for(int h = 1; h<B.length; h++){
		if(B[i][h] == 0){
			placeQueen(B, i, h);
			sum += findSolutions(B, i+1, mode);
			removeQueen(B,i,h);
		}
	}
	return sum;
	}

}

}