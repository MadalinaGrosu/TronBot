/**
 * Proiect PA 2013
 * Echipa Nabucodonosor
 * @version 1.0
 */
import java.util.Scanner;
import java.util.ArrayList;

/**
 * 
 * @author Nabucodonosor
 * Clasa Position 
 *
 */
class Position {
	int x;
	int y;
	
	/**
	 * Constructor nular
	 * @param x - ordonata
	 * @param y - abscisa
	 */
	Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Pentru pozitia curenta determina mutarile posibile 
	 * @param tabla - harta 
	 * @return mutarile posibile din pozitia curenta
	 */
	public ArrayList<Integer> mutariPosibile(int[][] tabla) {
		ArrayList<Integer> mutari = new ArrayList<Integer>();
		int x = this.x;
		int y = this.y;
		
		if (tabla[x][y - 1] == 1)
			mutari.add(4);
		if (tabla[x][y + 1] == 1)
			mutari.add(2);
		if (tabla[x - 1][y] == 1)
			mutari.add(1);
		if (tabla[x + 1][y] == 1)
			mutari.add(3);
		
		return mutari;
	}
}

/**
 * Clasa Solution 
 * @author Nabucodonosor
 *
 */
public class Solution {

	/**
	 * Citeste datele de intrare de la stdin
	 * @param tabla - reprezentarea interna a hartii
	 * @param sc - scanner-ul
	 * @param m - numarul de linii al hartii
	 * @param n - numarul de coloane
	 * @param jucator - caracterul jucatorului nostru
	 */
	public static void citire(int[][] tabla, Scanner sc, int m, int n, String jucator) {
		
		sc.nextLine();
		for (int i = 0; i < m; i++) {
			String semn = sc.next();
			for (int j = 0; j < n; j++) {
				if (semn.charAt(j) == '#')
					tabla[i][j] = -2;
				else if (semn.charAt(j) == jucator.charAt(0))
					tabla[i][j] = 0;
				else if (semn.charAt(j) == '-')
					tabla[i][j] = 1;
				else 
					tabla[i][j] = 2;
			}
		}
			
	}
	
	/**
	 * Functia de evaluare pentru negamax
	 * @param tabla - harta
	 * @param player - pozitia jucatorului curent
	 * @param p - indicator pentru player-ul nostru sau adversar; 0 - player-ul nostru,  1 - adversar
	 * @return suma vecinilor
	 */
	public int evaluate(int[][] tabla, Position player, int p) {
		
		int x = player.x;
		int y = player.y;
		
		if (p == 0)
			return -(tabla[x - 1][y] + tabla[x][y + 1] + tabla[x + 1][y] + tabla[x][y - 1]);
		else
			return tabla[x - 1][y] + tabla[x][y + 1] + tabla[x + 1][y] + tabla[x][y - 1];
	}
	
	/**
	 * Algoritmul negamax
	 * @param depth - adancimea curenta
	 * @param tabla - harta
	 * @param my_player - pozitia jucatorului nostru
	 * @param opponent - pozitia adversarului
	 * @param move - mutarea 
	 * @param p - indicator pentru player-ul nostru sau adversar; 0 - player-ul nostru,  1 - adversar
	 * @return cea mai buna mutare pentru pasul curent
	 */
	public int negaMax(int depth, int[][] tabla, Position my_player, Position opponent, int[] move, int p) {
		
		int maxscore = -999;
		int  i;
		Position current_pl = (p == 0 ? my_player : opponent);
		
		if (depth == 0 )
			return evaluate(tabla, current_pl, p);
		
		for (i = 0; i < current_pl.mutariPosibile(tabla).size(); i++) {
			
			int d = current_pl.mutariPosibile(tabla).get(i);
			if (d == 1) {
				tabla[current_pl.x - 1][current_pl.y] = 0;
				current_pl.x--;
			}
			else if (d == 2) {
				tabla[current_pl.x][current_pl.y + 1] = 0;
				current_pl.y++;
			}
			else if (d == 3) {
				tabla[current_pl.x + 1][current_pl.y] = 0;
				current_pl.x++;
			}
			else {
				tabla[current_pl.x][current_pl.y - 1] = 0;
				current_pl.y--;
			}
			
			int score;
			if (current_pl.equals(my_player))
				score = -negaMax(depth - 1, tabla, current_pl, opponent, move, 1 - p);
			else
				score = -negaMax(depth - 1, tabla, my_player, current_pl, move, 1 - p);
			
			if (score > maxscore) {
				maxscore = score;
				if (depth == 15) 
					move[0] = d;
			}
			
			if (d == 1) {
				current_pl.x++;
				tabla[current_pl.x - 1][current_pl.y] = 1;
			}
			else if (d == 2) {
				current_pl.y--;
				tabla[current_pl.x][current_pl.y + 1] = 1;
			}
			else if (d == 3) {
				current_pl.x--;
				tabla[current_pl.x + 1][current_pl.y] = 1;
			}
			else {
				current_pl.y++;
				tabla[current_pl.x][current_pl.y - 1] = 1;
			}
		}
			
		return maxscore;
	}
	
	/**
	 * Realizeaza mutarea gasita
	 * @param tabla - harta
	 * @param my_player - pozitia jucatorului nostru
	 * @param opponent - pozitia adversarului
	 */
	public void move(int[][] tabla, Position my_player, Position opponent) {
		
		int[] move = new int[1];
		int score = negaMax(15, tabla, my_player, opponent, move, 0);
		
		
		if (move[0] == 1)
			System.out.println("UP");
		else if (move[0] == 2)
			System.out.println("RIGHT");
		else if (move[0] == 3)
			System.out.println("DOWN");
		else
			System.out.println("LEFT");
		
	}
	
	public static void main(String[] args) {
		
		Solution player = new Solution();
		String jucator;
		Position my_player;
		Position opponent;
		int[][] tabla = null;
		int m, n;
		Scanner sc = new Scanner(System.in);
	
		jucator = sc.next();
		if (jucator.equals("r")) {
			my_player = new Position(sc.nextInt(), sc.nextInt());
			opponent = new Position(sc.nextInt(), sc.nextInt());
		}
		else {
			opponent = new Position(sc.nextInt(), sc.nextInt());
			my_player = new Position(sc.nextInt(), sc.nextInt());
		}
			
		m = sc.nextInt();
		n = sc.nextInt();
		if (tabla == null)
			tabla = new int[m][n];
		citire(tabla, sc, m, n, jucator);
		player.move(tabla, my_player, opponent);
		
		sc.close();
	}

}

