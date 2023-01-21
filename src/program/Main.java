package program;

import java.io.IOException;

import labirinto.ParseException;

public class Main {
	public static void main(String[] args) throws IOException, ParseException {
		var handler = new MazeHandler("input");
		handler.printSolution();
	}
}

