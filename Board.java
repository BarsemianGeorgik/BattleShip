import java.util.Random;
/** 
 * The Board class creates two board: one that displays the progress of the match (shown to user) 
 * and one that contains the answers (hidden)
 * This class also contains methods that will output the result of a hit rocket and methods that check which player has won the match.
 * <br/>COMP249
 * <br/>ASSIGNMENT 1
 * <br/>February 1st 2017
 * @author Merdkhanian Tamar ID:40030718
 * @author Barsemian Georgik ID: 40032101
 * @version 1.1
 * @see Player 
 * @see Battleship*/
public class Board {

	private char[][] empty_board= {{'_','_','_','_','_','_','_','_',},
									{'_','_','_','_','_','_','_','_',},
									{'_','_','_','_','_','_','_','_',},
									{'_','_','_','_','_','_','_','_',},
									{'_','_','_','_','_','_','_','_',},
									{'_','_','_','_','_','_','_','_',},
									{'_','_','_','_','_','_','_','_',},
									{'_','_','_','_','_','_','_','_',}};
	
	private char[][] filled_board= {{'_','_','_','_','_','_','_','_',},
									{'_','_','_','_','_','_','_','_',},
									{'_','_','_','_','_','_','_','_',},
									{'_','_','_','_','_','_','_','_',},
									{'_','_','_','_','_','_','_','_',},
									{'_','_','_','_','_','_','_','_',},
									{'_','_','_','_','_','_','_','_',},
									{'_','_','_','_','_','_','_','_',}};
	private char[][] copy_answer = new char[8][8];
	
	private boolean hit_grenade;

	
	/**
	* Takes in two objects from the Player class, one of them is the user's information
	* and the other is the robot's information. This class places all the coordinates
	* from both the user and robot on an empty board. After completing the action, it also
	* makes a copy of the board with all the coordinates on it
	*
	* @param user an object of the Player class
	* @param robot an object of the Player class
	*/
	public Board(Player user, Player robot){
		int[][] user_grenade=user.getGrenade();
		int[][] user_ship=user.getShip();
		int[][] robot_grenade=robot.getGrenade();
		int[][] robot_ship=robot.getShip();
		hit_grenade = false;

		
		for(int i =0; i<6;i++)
		{
			int index = ((user_grenade[i][1])-49);
			int index2 = ((user_grenade[i][0])-65);
			filled_board[index][index2]='G';
		}
		
		for(int i =0; i<6;i++)
		{
			int index = ((user_ship[i][1])-49);
			int index2 = ((user_ship[i][0])-65);
			filled_board[index][index2]='S';
		}
		
		for(int i =0; i<6;i++)
		{
			int index = ((robot_grenade[i][1])-49);
			int index2 = ((robot_grenade[i][0])-65);
			filled_board[index][index2]='g';
		}
		
		for(int i =0; i<6;i++)
		{
			int index = ((robot_ship[i][1])-49);
			int index2 = ((robot_ship[i][0])-65);
			filled_board[index][index2]='s';
		}
		
		for(int i=0; i<empty_board.length;i++){
			System.out.println();
			for(int j=0;j<8;j++)
					System.out.print(empty_board[i][j]+" ");}
		
		setAnswer();
	}

	/**
	* It prints out the board with progress of the match between the player and the robot
	*/	
	public void getBoard(){
		for (int i=0;i<empty_board.length;i++){
			System.out.println();
			for(int j=0;j<empty_board.length;j++)
				System.out.print(empty_board[i][j]+" ");}
	}
	/**
	* It prints out the board with all the coordinates of the ships and grenades of the player and the robot
	*/	
	public void getFilledBoard(){
		for (int i=0;i<copy_answer.length;i++){
			System.out.println();
			for(int j=0;j<copy_answer.length;j++)
				System.out.print(copy_answer[i][j]+" ");}
	}
	/**
	* It copies the cooridnates of all the ships and the grenades to display in the end of the game.
	*/	
	private void setAnswer(){
		for (int i=0;i<copy_answer.length;i++){
			for(int j=0;j<copy_answer.length;j++)
				copy_answer[i][j]=filled_board[i][j];}
	}
	/**
	* Takes a string from the user that represents the targeted coordinates for the attack
	*  and checks to see what is present in that position on the filled board.
	* Based on what there is at that position, it outputs the result and alters the empty board at the same position to show the user
	* the result.
	* It also replaces that position on the filled board with a '*' to know what has already been called. 
	* 
	* @param coordinates a string
	*/	
	public void rocket_user(String coordinates)
	{
		coordinates = coordinates.toUpperCase();
		
	//compare with filled_board
		int index1 = (int)(coordinates.charAt(1)-49);//digit
		int index2 = (int)(coordinates.charAt(0)-65);//letter
		
		
		char result = filled_board[index1][index2];
		
		
		switch (result){
		case 'S': {	System.out.println("Sank your own ship");
					filled_board[index1][index2] = '*';
					empty_board[index1][index2]	 = 'S';
					} break;
		case 's': {	System.out.println("Ship hit.");
					filled_board[index1][index2] = '*';
					empty_board[index1][index2] = 's';
					} break;
		case 'G': {	System.out.println("Hit your own grenade");
					filled_board[index1][index2] = '*';
					empty_board[index1][index2] = 'G';
					hit_grenade = true;
					} break;
		case 'g': {	System.out.println("Boom! grenade.");
					filled_board[index1][index2] = '*';
					empty_board[index1][index2] = 'g';
					hit_grenade = true;
					} break;
		case '*':{	System.out.println("Position already called.");
					} break;
		default:	{System.out.println("Nothing happened");
					empty_board[index1][index2] = '*';
					filled_board[index1][index2] = '*';}}
		}
		
	/**
	* Generates random coordinates for the attack on the behalf of the robot
	*  and checks to see what is present in that position on the filled board.
	* Based on what there is at that position, it outputs the result and alters the empty board at the same position to show the user
	* the result.
	* It also replaces that position on the filled board with a '*' to know what has already been called. 
	* 
	*/			
		public void rocket_robot()
		{
			Random rand = new Random ();	
			//compare with filled_board
			int index1 = rand.nextInt(8);
			int index2 = rand.nextInt(8);
			
			char result = filled_board[index1][index2];
			
			switch (result){
			case 's': {	System.out.println("Sank your own ship");
						filled_board[index1][index2] = '*';
						empty_board[index1][index2] = 's';
						} break;
			case 'S': {	System.out.println("Ship hit.");
						filled_board[index1][index2] = '*';
						empty_board[index1][index2]	 = 'S';
						} break;
			case 'g': {	System.out.println("Hit your own grenade");
						filled_board[index1][index2] = '*';
						empty_board[index1][index2] = 'g';
						hit_grenade = true;
						} break;
			case 'G': {	System.out.println("Boom! grenade.");
						filled_board[index1][index2] = '*';
						empty_board[index1][index2] = 'G';
						hit_grenade = true;
						} break;
			case '*':{	System.out.println("Position already called.");
						} break;
			default:	{System.out.println("Nothing happened");
						empty_board[index1][index2] = '*';
						filled_board[index1][index2] = '*';}}
			}

		
		/**
		*  It detects if a grenade was hit or not 
		* 
		* @return a boolean - false if grenade isn't hit and true if grenade is hit
		*/			
		public boolean grenadeHit_status()
		{
			if (hit_grenade==true){
				hit_grenade=false;
				return true;}
			else
				return false;
		
		}
		/**
		* It verifies if the user has won the match ( all the 6 ships of the robot must be hit)
		* 
		*@return It returns a true (if won) or a false (if it didn't win yet)
		*/	
		public boolean user_winning(){
			int count=0;
			boolean win=false;
			
			for (int i=0;i<empty_board.length;i++){
				for(int j=0;j<empty_board.length;j++){
					if(empty_board[i][j]=='s')
						count++;}}
			if (count==6)
				win=true;
			return win;
		}
		/**
		* It verifies if the robot has won the match ( all the 6 ships of the user must be hit)
		* 
		*@return It returns a true (if won) or a false (if it didn't win yet)
		*/	
		public boolean computer_winning(){
			int count=0;
			boolean win=false;
			
			for (int i=0;i<empty_board.length;i++){
				for(int j=0;j<empty_board.length;j++){
					if(empty_board[i][j]=='S')
						count++;}}
			if (count==6)
				win=true;
			return win;
		}	
	}

