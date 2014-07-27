package connectFour.model;

public class ConnectFour {
    
	//Class variables WIDTH and LENGTH are the dimensions of the playing field
	public final static int WIDTH = 6;
	public final static int LENGTH = 7;
	
	private String player1Name;
	private String player2Name;
	private PieceColor playerColor;
	private ComputerPlayer AI;
	private PieceColor startColor;
	
	//the game board is represented as a PieceColor array
	//in this array the first numer is the y-coordinate, the 2nd the x-co-ordinate
	//the highest y value is the bottom row, highest x value the right side of the board
	//and vice versa lowest y value the top of the board and low x values the left
	private PieceColor[][] board;
	private PieceColor turn;
	
	
	/**
	 * Constructor gives values to playerName, playerColor and determines
	 * the first value of turn
	 * @param name name of human player
	 * @param playerColor colour of human player
	 * @param startColor color that gets the first turn
	 */
	public ConnectFour(String name, PieceColor playerColor, PieceColor startingColor ){
		
		this.player1Name = name;
		this.playerColor = playerColor;
		this.startColor = startingColor;
		this.AI = new ComputerPlayer();
		this.player2Name = "computer";
		this.turn = this.startColor;
		
		//initialize the game board with class integers LENGTH and WIDTH
		board = new PieceColor[LENGTH][WIDTH];
		}
	
	/**
	 * default constructor
	 */
	public ConnectFour(){
		
		//call other constructor with default values player name Jantje
		//playing with RED and RED starting the game
		this("Jantje", PieceColor.R, PieceColor.R);
		
	}
	
	public PieceColor[][] getBoard(){
		return this.board;
	}
	
	/**
	 * Method checks if the board is full
	 * It does this by counting the number of top
	 * squares in array not equal to null
	 * @return true if board is full, false if board is not full
	 */
	private boolean boardFullCheck(){
		int fullRows = 0;
		
		for(int i = 0; i < ConnectFour.WIDTH; i++){
			//if top square not null, row is full
			//therefore increase fullRows
			if(! (board[0][i] == null) ){
				fullRows++;
			}
		}
		
		return (fullRows >= ConnectFour.WIDTH);
	}
	
	/**
	 * returns true if there is a winner, or if the board is full
	 * @return
	 */
	public void hasGameEnded(){
		PieceColor winner = getWinner();
		
		if(boardFullCheck()){
			System.out.println("Game over, the board is full");
			System.exit(0);
		}
		
		else if(! (winner == null) ){
			System.out.println("Game over. " + winner + " has won!");
			System.exit(0);
			
			
		}
		
	}
	
	/**
	 * checks if given color has four in a row. The function starts checking
	 * in the bottom left corner and moves from left to right
	 * and down to up.  For each point it checks in four direction:
	 * up, left, up-right and up-left. checking is stopped if a 
	 * row has been found. Because checking starts in bottom left
	 * and moves up, the other direction do not need to be checked
	 * @param color color to be checked
	 * @return true if color has 4 in a row, otherwise false
	 */
	public boolean fourInRowCheck(PieceColor color){
	  
		for(int i = ConnectFour.LENGTH -1; i >= 0; i--){
			for(int j = 0; j < ConnectFour.WIDTH; j++){
				if(checkDirectionTop(color, i, j)){
					return true;
				}
				else if(checkDirectionRight(color, i, j)){
					return true;
				}
				else if(checkDirectionTopRight(color, i, j)){
					return true;
				}
				else if(checkDirectionTopLeft(color, i, j)){
					return true;
				}
			}
		}
	  
		return false;
	}
		
	
	/**
	 * checks for a row of 4 of the given color in up direction from given point
	 * starting point also counts for the row
	 * 
	 * @param color the PieceColor
	 * @param lengthPoint length axis co-ordinate of starting point
	 * @param widthPoint width axis co-ordinate of starting point
	 * @return
	 */
	public boolean checkDirectionTop(PieceColor color, int lengthPoint, int widthPoint){
		
		if(board[lengthPoint][widthPoint] == color){
		  int counter = 1;
		  
		  for(int i = lengthPoint -1; i >= 0 ; i--){
			if(board[i][widthPoint] == color){
				counter++;
				if(counter == 4){
					return true;
				}
			}
			else{
				return false;
			}
		  }
		}
	  //this is just here to appease the compiler	
	  return false;	
	}
		
	/**
	 * checks for rows towards the right, including starting point	
	 * @param color color of the row
	 * @param lengthPoint length co-ordinate of starting point
	 * @param widthPoint width co-ordinate of starting point
	 * @return true if row is found, otherwise false
	 */
	public boolean checkDirectionRight(PieceColor color, int lengthPoint, int widthPoint){
		
	  //loop will only start if first point if equal to give color	
	  if(board[lengthPoint][widthPoint] == PieceColor.R){
		 
		  int counter = 1;
		  for (int i = widthPoint + 1 ; i < ConnectFour.WIDTH; i++){
			  if(board[lengthPoint][i] == color){
				  //if next point to the right also is given color, increaes counter
				  counter++;
				  if(counter == 4){
					  return true;
				  }
			  }
			  //if next point on the right is not PieceColor param, return false
			  else{
				  return false;
			  }
		  }
	  }
		//here for the compiler
		return false;
	}
	
	/**
	 * looks for rows in given color from given point
	 * @param color color of the row to look for
	 * @param lengthPoint y-axis co-ordinate
	 * @param widthPoint x-axis co-ordinate
	 * @return true if a row is found, false otherwise.
	 */
	public boolean checkDirectionTopRight(PieceColor color, int lengthPoint, int widthPoint){
	      
		  //loops start if starting point is a match with given color
		  if(board[lengthPoint][widthPoint] == color){
			  
			  int j = widthPoint +1;
			  int counter = 1;
			  //i is decreased each iteration to move up
			  for(int i = lengthPoint -1; i >= 0; i-- ){
				  
				  //if we reached outer edge of board, false will be returned
				  if(j < ConnectFour.WIDTH){
					
					  if(board[i][j] == color){
						counter++;
						//j is increased each iteration to move to the right
						j++;
						
						if(counter == 4){
							return true;
						}
					  }
					  else{
						 
						return false;
					  }
					  
				  }
				  else{
					 
					  return false;
				  }
			  }
		  }
		    //here for compiler
			return false;
		}
	
	public boolean checkDirectionTopLeft(PieceColor color, int lengthPoint, int widthPoint){
	      
		  //loops start if starting point is a match with given color
		  if(board[lengthPoint][widthPoint] == color){
			  
			  int j = widthPoint -1;
			  int counter = 1;
			  //i is decreased each iteration to move up
			  for(int i = lengthPoint -1; i >= 0; i-- ){
				  
				  //if we reached outer edge of board, false will be returned
				  if(j < ConnectFour.WIDTH){
					
					  if(board[i][j] == color){
						counter++;
						//j is increased each iteration to move to the right
						j--;
						
						if(counter == 4){
							return true;
						}
					  }
					  else{
						 
						return false;
					  }
					  
				  }
				  else{
					 
					  return false;
				  }
			  }
		  }
		    //here for compiler
			return false;
		}
	
	/**
	 * returns winner of the game if there is one, otherwise returns
	 * null
	 * @return if there is a Winner, returns winner PieceColor
	 * if there is no winner returns null
	 */
	public PieceColor getWinner(){
		
		
		for(PieceColor p : PieceColor.values()){
			if(fourInRowCheck(p)){
				return p;
			}
		}
		
		
		return null;
	}
	
	/**
	 * throws a stone in column with column number will be placed
	 * 
	 * @param columnnumber the number of column where stone
	 * 
	 * @param color color of the stone that will be placed
	 * @return true if stone was placed
	 * @throws ConnectFourGameException if row is full
	 * or if it's not given color's turn
	 * when the move is made, the turn attribute will be changed
	 * to the opposing color
	 */
	public boolean throwStone(PieceColor color, int columnnumber) 
	 throws ConnectFourGameException{
		
		if(!rowNotFullCheck(columnnumber)){
			throw new ConnectFourGameException("this row is full");
		}
		
		if(! turnCheck(color)){
			throw new ConnectFourGameException("not this players turn");
		}
		//the value of LENGTH where stone will be placed
		int piecePosition = -1;
		
		//determine 'height' of the stone
		for(int i = 0; i < ConnectFour.LENGTH; i++){
			PieceColor value = board[i][columnnumber];
			
			if(value == null){
				piecePosition = i;
			}
		}
		
		board[piecePosition][columnnumber]= color;
		
		if(turn == PieceColor.R){
			turn = PieceColor.Y;
		}
		else if(turn == PieceColor.Y){
			turn = PieceColor.R;
		}
		
		return true;
	}
	
	public void setTurn(PieceColor color){
		turn = color;
	}
	
	
	/**
	 * helper method for checking the if rown isn't full
	 * Method checks if 'top' square in row is null, then returns true
	 * @return true if top square of given row is null, else false
	 */
	private boolean rowNotFullCheck(int rownr){
       if(board[0][rownr] == null){
    	   return true;
       }
       else{
    	   return false;
       }
	}
	
	/**
	 * checks if the player making a move has the turn
	 * @param color color to be checked
	 * @return true if given color has the turn,
	 * false if color hasn't got the turn
	 */
	private boolean turnCheck(PieceColor color){
		if(color == turn){
			return true;
		}
		else{
			return false;
		}
	}
	
	public void newGame(){
		
	}
	
	public void printBoard(){
		
		for(int i = 0; i < ConnectFour.LENGTH; i++){
			String line = "";
			for(int j = 0; j < ConnectFour.WIDTH; j++){
				
				if(board[i][j] == null){
					line = line + "0";
				}
				else{
				  line = line + board[i][j];
				}  
			}
			System.out.println(line);
		}
	}
	
}
