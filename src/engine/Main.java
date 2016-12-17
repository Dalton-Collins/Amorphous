package engine;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String args[]){
		GameState GS = new GameState();
		Scanner reader = new Scanner(System.in);
		FindMoves fm = new FindMoves();
		while(true){
			if(GS.turnPlayer.canDraw(1)){
				GS.turnPlayer.draw(1);
			}
			ArrayList<Command> moves;
			moves = fm.getMoves(GS, GS.turnPlayer);
			while(!moves.isEmpty()){
				System.out.println("Choose a move from " + moves.size());
				int i = reader.nextInt();
				if(i == -1){
					break;
				}
				GS.doCommand(moves.get(i));
				moves = fm.getMoves(GS, GS.turnPlayer);
			}
			
			GS.nextTurn();
		}
	}
}
