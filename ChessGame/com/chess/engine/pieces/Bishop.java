package com.chess.engine.pieces;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;




public class Bishop extends Piece {
	
	private final static int[] CANDIDATE_MOVE_VECTOR_COORDINATES = { -9, -7, 7, 9};

	public Bishop(final Alliance pieceAlliance,
		           final int piecePosition) {
		super(PieceType.BISHOP, piecePosition, pieceAlliance);	
	}


	@Override
	public List<Move> calculateLegalMoves(final Board board) {
		
		final List<Move> legalMoves = new ArrayList<>();
		
		for(final int candidateCoordinateOffset: CANDIDATE_MOVE_VECTOR_COORDINATES) {
			
			int candidateDestinationCoordinate = this.piecePosition;
			
			while(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {
				
				if(isFirstColumnExclusion(candidateDestinationCoordinate, candidateCoordinateOffset) ||
						isEigthColumnExclusion(candidateDestinationCoordinate, candidateCoordinateOffset)) {
				break;
				}
			     candidateDestinationCoordinate += candidateCoordinateOffset;
			     if(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {
			    	 final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
			    	 if (!candidateDestinationTile.isTileOccupied()) {
			    		 legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));
			    	 }
			    	 else {
			    		 final Piece pieceAtDestination = candidateDestinationTile.getPiece();
			    		 final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();
			    		 if(this.pieceAlliance != pieceAlliance) {
			    			 legalMoves.add(new Move.AttackMove(board, this, candidateDestinationCoordinate, pieceAtDestination));
			    		 }
			    	     break; 
			         }
		          }
       	      }
		  }
		
		  	return Collections.unmodifiableList(legalMoves); 
	}
	
	@Override
	public Bishop movePiece(final Move move) {
		return new Bishop(move.getMovedPiece().getPieceAlliance(), move.getDestinationCoordinate());
	}
	
	@Override
	public String toString() {
		return PieceType.BISHOP.toString();
	} 
    
	private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset) {
		return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset == -9 || candidateOffset ==7);
	}
	
	private static boolean isEigthColumnExclusion(final int currentPosition, final int candidateOffset) {
			return BoardUtils.EIGTH_COLUMN[currentPosition] && (candidateOffset == -7 || candidateOffset ==9);
	}


	
	
}
