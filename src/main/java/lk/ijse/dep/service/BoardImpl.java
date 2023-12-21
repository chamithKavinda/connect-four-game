package lk.ijse.dep.service;

public class BoardImpl implements Board {

    private final Piece [][] pieces;

    private final BoardUI boardUI;

    public BoardImpl(BoardUI boardUI) {
        this.boardUI = boardUI;
        this.pieces=new Piece[6][5];

        //pieces tika initialize
        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces[i].length; j++) {
                pieces[i][j]=Piece.EMPTY;
            }
        }
    }
    public Piece[][] getPieces() {
        return pieces;
    }
    public BoardUI getBoardUI() {
        return this.boardUI;
    }

    @Override
    public int findNextAvailableSpot(int col) {

        for (int i = 0; i < pieces[col].length; i++) {
            if (pieces[col][i]==Piece.EMPTY){
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean isLegalMove(int col) {
        int index=findNextAvailableSpot(col);
        return index != -1;
    }

    @Override
    public boolean existLegalMoves() {
        for (int i = 0; i < pieces.length; i++) {
            if (isLegalMove(i)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void updateMove(int col, Piece move) {
        int index=findNextAvailableSpot(col);
        pieces[col][index]=move;
    }

    @Override
    public void updateMove(int col, int row, Piece move) {
        pieces[col][row]=move;
    }

    @Override
    public Winner findWinner() {
        // vertically checking
        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j <= pieces[i].length - 4; j++) {
                if (pieces[i][j] != Piece.EMPTY
                        && pieces[i][j] == pieces[i][j + 1]
                        && pieces[i][j] == pieces[i][j + 2]
                        && pieces[i][j] == pieces[i][j + 3]) {
                    return new Winner(pieces[i][j], i, j, i, j + 3);
                }
            }
        }

        // horizontally checking
        for (int i = 0; i <= pieces[0].length - 4; i++) {
            for (int j = 0; j < pieces.length; j++) {
                if (pieces[j][i] != Piece.EMPTY
                        && pieces[j][i] == pieces[j][i + 1]
                        && pieces[j][i] == pieces[j][i + 2]
                        && pieces[j][i] == pieces[j][i + 3]) {
                    return new Winner(pieces[j][i], j, i, j, i + 3);
                }
            }
        }

        // If winner is not found
        return new Winner(Piece.EMPTY);
    }
}
