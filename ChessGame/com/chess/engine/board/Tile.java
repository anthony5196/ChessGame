package com.chess.engine.board;

import com.chess.engine.pieces.Piece;

import java.util.Map;
import java.util.Collections;
import java.util.HashMap;


public abstract class Tile
{

	protected final int tileCoordinate;
	
	//Declaring a new member field                  //private static method being declared
	private static final Map<Integer, EmptyTile> EMPTY_TILES_CACHE = createAllPossibleEmptyTiles();
	
	
	//Method being created
	private static Map<Integer, EmptyTile> createAllPossibleEmptyTiles()
	{
		final Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();
		
		for(int i = 0; i < BoardUtils.NUM_TILES; i++)
		{
			emptyTileMap.put(i, new EmptyTile(i));
		}
		
		return Collections.unmodifiableMap(emptyTileMap);
	}
	
	
	
	
	
	/* Constructor that allows us to create 
		individual tiles. 
		@param tileCoordinate will equal
		whatever is passed through the constructor.
	**/	
		
	public static Tile createTile(final int tileCoordinate, final Piece piece)
		{
			return piece != null ? new OccupiedTile(tileCoordinate, piece) : EMPTY_TILES_CACHE.get(tileCoordinate);
		}	
		
	
     private Tile(final int tileCoordinate)
      {
		  this.tileCoordinate = tileCoordinate;
      }
	  
	  
	   /* 
		  Going to tell us whether a given tile is 
		  occupied or not
	   **/
	   public abstract boolean isTileOccupied();
	 
	 
	   /*  This method is going to tell you what 
		  piece is on a given tile. Null if empty.
	   **/
	  
	   public abstract Piece getPiece();
	  
      
	  
	 
	 
	    
		 // Defining an empty tile.
	  public static final class EmptyTile extends Tile
	  {
		  private EmptyTile(final int coordinate)
		  {
			  super(coordinate);
		  }
		  
		  
		  @Override 
		  public String toString() {
			  return "-";
		  }
		  @Override
		  public boolean isTileOccupied ()
		  {
			  return false;
		  }
		  @Override public Piece getPiece()
		  {
			  return null; 
		  }
	  }
	  
	
	
	
	  
	  
	   
		 // Defining an occupied tile
	 public static final class OccupiedTile extends Tile
		{
				 
		 private final Piece pieceOnTile;  //piece to find on an occupied tile.  
				                      
			/* 
				The constructor for an occupied tile is going 
				to take the coordinate and the piece. 
			*/
		 private  OccupiedTile(int tileCoordinate, final Piece pieceOnTile)
	      {
	   	      super(tileCoordinate);     //super is for the superclass constructor.
			  this.pieceOnTile = pieceOnTile; //makes pieceOnTile equal to constructor value.
		  }
				  
		 
		     @Override 
		     public String toString() {
		    	 return  getPiece().getPieceAlliance().isBlack() ? getPiece().toString().toLowerCase() :
						getPiece().toString();
		     }
			 
		     @Override
	    	  public boolean isTileOccupied()
				  {
					  return true;
				  }
				  
			  @Override 
			  public Piece getPiece()
				  {
					  return this.pieceOnTile;
				  }
		 }







	public int getTileCoordinate() {
		return tileCoordinate;
	}







	
	  
	  
	  



}