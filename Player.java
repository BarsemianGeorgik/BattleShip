import java.util.Random;
import java.util.Scanner;
/** 
 * The Player class allows the user to place its own ships and grenade coordinates.
 * This class also contains methods that generates ship and grenade coordinates for the robot
 * <br/>COMP249
 * <br/>ASSIGNMENT 1
 * <br/>February 1st 2017
 * @author Merdkhanian Tamar ID:40030718
 * @author Barsemian Georgik ID: 40032101
 * 
 * @version 1.1
 * @see Board 
 * @see Battleship*/
public class Player {

	Scanner keyboard = new Scanner(System.in);
	
	private int grenades[][] = new int[6][2];
	private int ships[][] = new int[6][2];

	
	/**
	* Takes a string of coordinates for a grenade and an integer representing the index of the array where the coordinates will get 
	* stored. Converts the characters of the coordinates in to Unicode values. 
	*
	* @param coordinates a String
	* @param position an integer value
	*/
	public void setGrenade(String coordinates, int position)
	{
		grenades[position][0] = (int) coordinates.charAt(0);
		grenades[position][1] = coordinates.charAt(1);
		
	}
	
	/**
	* Takes a string of coordinates for a ship and an integer representing the index of the array where the coordinates will get 
	* stored. Converts the characters of the coordinates in to Unicode values. 
	*
	* @param coordinates a String
	* @param position an integer value
	*/
	public void setShip(String coordinates, int position)
	{
		ships[position][0] = (int) coordinates.charAt(0);
		ships[position][1] = coordinates.charAt(1);
	}
	
	/**
	* Makes a copy of the 2D array of integers (Unicode values) for the ships
	*
	* @return a copy of the 2D array of integers (Unicode values) for the ships
	*/
	public int[][] getShip()
	{
		int [][] ship = ships.clone();
		return ship;
	}

	
	/**
	* Makes a copy of the 2D array of integers (Unicode values) for the grenades
	*
	* @return a copy of the 2D array of integers (Unicode values) for the grenades
	*/
	public int[][] getGrenade()
	{
		int [][] grenade = grenades.clone();
		return grenade;
	}
	

	/**
	* Verifies if the coordinate placed by the user is in range of the game board or not
	* 
	* @param check a string representing the coordinate 
	* @return a true (if in range) or false (not in range) statements 
	*/
	public boolean checkRange(String check)
	{
		boolean statement=true;
		int first=(int) check.charAt(0);
		int second=check.charAt(1);
		{
		if ((65>first) || (first >72)){
			statement = false; //not in range 
		}
		else {
			if ((49>second) || (second>56)){
				statement = false; //not in range
			}}}
		return statement;
	}
	
	/**
	* Verifies if the placed coordinate is already taken either in the ship array or the grenade array
	* 
	* @param check a string representing the coordinate 
	* @return a true (coordinate is available) or false (coordinate already in use) statement 
	*/
	public boolean checkDoubles(String check)
	{
		check = check.toUpperCase();
		boolean statement=true;
		int first=(int) check.charAt(0);
		int second=check.charAt(1);
		
		for (int i =0; i<6;i++)
			if (grenades[i][0]==first && grenades[i][1]==second ){
				statement= false;
				break;}
		
		for (int i =0; i<6;i++)
			if (ships[i][0]==first && ships[i][1]==second){
				statement= false;
				break;}
		
		return statement;
	}

	/**
	* Interacts with the user and asks to place the desired coordinates for grenades and ships
	* and verifies at the same time if the placed coordinates are valid or not.
	*/
	public void initializePlayer()
	{
		int count=1;
		
		do{
			
		System.out.println("Enter the coordinates of your ship # "+count+" :");
		String ship = keyboard.next();
		ship =ship.toUpperCase();
		
		boolean verdict= checkRange(ship);
		boolean verdict2 = checkDoubles(ship);
		if (verdict==false) //not in range
			{
			System.out.println("sorry, coordinates outside the grid. try again.");
			continue;
			}
		if (verdict2==false) //not in range
			{
			System.out.println("sorry, coordinates already used. try again.");
			continue;
			}
		
		setShip(ship, (count-1));
		
		
		count++;
		}while (count<=6);
		
		count=1;
		
		do{
			
		System.out.println("Enter the coordinates of your grenades # "+count+" :");
		String grenade = keyboard.next();
		grenade =grenade.toUpperCase();
		
		boolean verdict= checkRange(grenade);
		boolean verdict2 = checkDoubles(grenade);
		if (verdict==false) //not in range
			{
			System.out.println("sorry, coordinates outside the grid. try again.");
			continue;
			}
		if (verdict2==false) //not in range
			{
			System.out.println("sorry, coordinates already used. try again.");
			continue;
			}
		
		setGrenade(grenade, (count-1));
		
		
		count++;
		}while (count<=6);
		}
	
	
	/**
	* Generates random valid coordinates for the ships and grenades of the robot
	* and outputs a message confirming when the action is done.
	*/
		public void initializeComputer(Player user)
		{
			String coordinatesGrenades="";
			for(int i =0; i<6;i++)
			{
				Random rand = new Random ();
				do
				{
				char letter = (char) (rand.nextInt(8)+65);
				int number = (rand.nextInt(8)+1);
				coordinatesGrenades = letter+""+number;
				
				} while(!(checkDoubles(coordinatesGrenades)) || !(user.checkDoubles(coordinatesGrenades)));
				
				setGrenade(coordinatesGrenades, i);
			}
			

			String coordinatesShips;
			for(int i =0; i<6;i++)
			{
				Random rand = new Random ();
				do
				{
				char letter = (char) (rand.nextInt(8)+65);
				int number = (rand.nextInt(8)+1);
				coordinatesShips = letter+""+number;
				
				} while (!(checkDoubles(coordinatesShips)) || !(user.checkDoubles(coordinatesShips)));
				
				setShip(coordinatesShips, i);
			}
			System.out.println("\nOk, the computer places its ships and grenades at random. Let's play.\n");

		}
		
	}	
	
