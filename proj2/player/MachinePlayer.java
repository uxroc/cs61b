/* MachinePlayer.java */

package player;

/**
 *  An implementation of an automatic Network player.  Keeps track of moves
 *  made by both players.  Can select a move for itself.
 */
public class MachinePlayer extends Player {

	private final static int BLACK = 0;
	private final static int WHITE = 1;

	private final static int[] cornerX = {0, 0, 8, 8};
	private final static int[] cornerY = {0, 8, 0, 8};

	private final static int[] fx = {1, 1,  1,  0, 0, -1, -1, -1};
	private final static int[] fy = {1, 0, -1, -1, 1, -1,  0,  1};

	private static int[][] visited;
	private int[][] board;
	private int color;
	private int opponentColor;
	private int searchDepth;

	static {
		visited = new int[8][8];
	}

	{
		board = new int[8][8];
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
				board[i][j] = -1;
	}

	// Creates a machine player with the given color.  Color is either 0 (black)
	// or 1 (white).  (White has the first move.)
	public MachinePlayer(int color) {
		this(color, 1);
	}

	// Creates a machine player with the given color and search depth.  Color is
	// either 0 (black) or 1 (white).  (White has the first move.)
	public MachinePlayer(int color, int searchDepth) {
		this.color = color;
		this.opponentColor = 1 - color;
		this.searchDepth = searchDepth;

	}

	// Returns a new move by "this" player.  Internally records the move (updates
	// the internal game board) as a move by "this" player.
	public Move chooseMove() {
		return new Move();
	}

	// If the Move m is legal, records the move as a move by the opponent
	// (updates the internal game board) and returns true.  If the move is
	// illegal, returns false without modifying the internal state of "this"
	// player.  This method allows your opponents to inform you of their moves.
	public boolean opponentMove(Move m) {
		if (isLegalMove(m, opponentColor)) {
			recordMove(m, opponentColor);
			return true;
		} else {
			return false;
		}
	}

	// If the Move m is legal, records the move as a move by "this" player
	// (updates the internal game board) and returns true.  If the move is
	// illegal, returns false without modifying the internal state of "this"
	// player.  This method is used to help set up "Network problems" for your
	// player to solve.
	public boolean forceMove(Move m) {
		if (isLegalMove(m, color)) {
			recordMove(m, color);
			return true;
		} else {
			return false;
		}
	}

	private void recordMove(Move m, int color) {
		if (m.moveKind == Move.QUIT) return;
		board[m.x1][m.y1] = color;
		if (m.moveKind == Move.STEP) {
			board[m.x2][m.y2] = -1;
		}
		return;
	}

	private static double eval(int[][] board) {
		return 0;
	}

	private boolean atCorner(int x, int y) {
		for (int i = 0; i < 4; i++) {
			if (x == cornerX[i] && y == cornerY[i]) {
				return true;
			}
		}
		return false;
	}

	private boolean atGoal(int x, int y, int color) {
		return (atBlackGoal(x, y) && color == BLACK) || (atWhiteGoal(x, y) && color == WHITE);
	}

	private boolean atBlackGoal(int x, int y) {
		return (!atCorner(x, y)) && (y == 0 || y == 7);
	}

	private boolean atWhiteGoal(int x, int y) {
		return (!atCorner(x, y)) && (x == 0 || x == 7);
	}

	private void clearVisited() {
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
				visited[i][j] = 0;
	}

	private int countGroupChips(int x, int y, int color) {
		int ret = 1;
		visited[x][y] = 1;
		for (int i = 0; i < 8; i++) {
			int xx = x + fx[i], yy = y + fy[i];
			if (xx >= 0 && xx < 8 && yy >= 0 && yy < 8 && board[x][y] == color && visited[x][y] == 0) {
				ret += countGroupChips(xx, yy, color);
			}
		}
	}

	private boolean isLegalMove(Move m, int color) {
		if (m.moveKind == Move.QUIT) return true;
		int x = m.x1, y = m.y1;
		if (atCorner(x, y)) return false;
		if (color == BLACK && atWhiteGoal(x, y)) return false;
		if (color == WHITE && atBlackGoal(x, y)) return false;
		if (x > 7 || x < 0 || y > 7 || y < 0) return false;
		if (board[y][x] > -1) return false;

		if (m.moveKind == Move.STEP) {
			if (m.x2 > 7 || m.x2 < 0 || m.y2 > 7 || m.y2 < 0 || board[x][y] != color) return false;
			if (m.x1 == m.x2 && m.y1 == m.y2) return false;
		}

		clearVisited();
		if (countGroupChips(x, y, color) >= 3) return false;

		return true;
	}

	private boolean isWinningNet(int color) {
		for (int x = 0; x < 8; x++)
			for (int y = 0; y < 8; y++)
				if (board[x][y] == color && atGoal(x, y, color)) {
					clearVisited();
					if (dfsWinningNet(x, y, color, 1)) return true;
				}
		return false;
	}

	private boolean dfsWinningNet(int x, int y, int color, int num) {
		visited[x][y] = 1;
		if (num >= 6 && atGoal(x, y, color)) return true;
		for (int i = 0; i < 8; i++) {
			for (int k = 1; k < 8; k++) {
				int xx = x + fx[i], yy = y + fy[i];
				int oppoColor = 1 - color;
				if (xx < 0 || yy < 0 || xx > 7 || yy > 8 || board[xx][yy] == oppoColor) break;
				if (board[xx][yy] == color && visited[xx][yy] == 0) {
					if (dfsWinningNet(x, y, color, num + 1)) return true;
				}
			}
		}
		visited[x][y] = 0;
		return false;
	}
}
