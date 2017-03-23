import java.util.Scanner;
/** 
 * The Battleship class is where all the pieces come in palce. This is where the game happens 
 * between the user, the robot and the board. Each round of the game is portrayed by this class.
 * <br/>COMP249
 * <br/>ASSIGNMENT 1
 * <br/>February 1st 2017
 * @author Merdkhanian Tamar ID:40030718
 * @author Barsemian Georgik ID: 40032101
 * @version 1.1
 * @see Player 
 * @see Board*/
public class Battleship {

Scanner keyboard = new Scanner(System.in);
	/**
	* It creates two Player objects, one for the user, where the user is going to choose its own coordinates 
	* and another for robot, where the coordinates are generated randomly.Then Board object is created
	* with the information of the user and the robot. After all these 3 objects created, the game begins.
	* At each round the user will call a coordinate on the board and a board will appear with the called coordinate.
	* After each player turn, the robot will also have a chance to call a coordinate to hit. The first player who
	* sinks the 6 ships of the other player will be declared as a winner
	*
	*/
public void run()
{
	System.out.println("Hey let's play Battleship!\n");
	
	
	Player user = new Player();
	user.initializePlayer();
	
	Player computer = new Player();
	computer.initializeComputer(user);
	
	Board gameboard = new Board(user,computer);
	
	int count =0;
	boolean lose_turn = false;
	boolean lose_turn_computer = false;
	
	do {	
		if (count%2==0){ // user turn if even
			System.out.println("\n\nYour turn");
			System.out.println("Position of your rocket :");
			if (lose_turn_computer==true)
				count++;
			
			lose_turn_computer=false;
			
			String coordinate = keyboard.nextLine();
			coordinate.toUpperCase();
			gameboard.rocket_user(coordinate); //rocket
		
			if (gameboard.grenadeHit_status()){
				lose_turn = true;}

			count++;

			gameboard.getBoard();
			if (gameboard.user_winning()||gameboard.computer_winning()){
				break;}

		}	
		if (count%2==1){ // computer turn if odd
			System.out.println("\n\nComputer's turn");
			if (lose_turn==true)
				count++;
			
			lose_turn=false;
			
			gameboard.rocket_robot(); //rocket
		
			if (gameboard.grenadeHit_status()){
				lose_turn_computer = true;}

			count++;
			gameboard.getBoard();
			
			if (gameboard.user_winning()||gameboard.computer_winning()){
				break;}
		}
		
		
	} while (!(gameboard.computer_winning() || gameboard.user_winning()));//no one has won 
	
	
	//declare winners 
	
	if (gameboard.computer_winning())
		System.out.println("\nCOMPUTER WON... sorry!");
	
	if (gameboard.user_winning())
		System.out.println("\nYOU WON!!!");
	
	gameboard.getFilledBoard();
	
	}
}