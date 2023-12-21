package lk.ijse.dep.service;

public class AiPlayer extends Player{


    public AiPlayer(Board board) {
        super(board);
    }

    @Override
    public void movePiece(int col) {

        do{
            col= (int) Math.floor(Math.random()*6);
        }while(!board.isLegalMove(col));
        board.updateMove(col,Piece.GREEN);

        board.getBoardUI().update(col,false);
        Winner w=board.findWinner();

        if(w.getWinningPiece()!=Piece.EMPTY){
            board.getBoardUI().notifyWinner(w);
        }else{
            board.existLegalMoves();
        }
    }
}