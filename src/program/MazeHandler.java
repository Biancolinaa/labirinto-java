package program;

import java.io.*;
import java.util.*;

import labirinto.*;

public class MazeHandler {
	private Maze maze;
	private String fileName;
	

	public MazeHandler(String s) throws IOException, ParseException {
		fileName = s;
		this.maze = this.parse();
	}
	
	private Maze parse() throws IOException, ParseException {
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			String line = br.readLine();
			Coord start = Coord.fromString(line);
			
			line = br.readLine();
			Coord end = Coord.fromString(line);

			var rows = new ArrayList<ArrayList<Square>>();
			
			while ((line = br.readLine()) != null) {
			   if (line.length() == 0)
				   continue;
			   
			   var row = new ArrayList<Square>();
			   for (int i = 0; i < line.length(); i++)
				   row.add(Square.fromChar(line.charAt(i)));
			   
			   rows.add(row);
			}
			
			Square[][] maze = new Square[rows.size()][rows.get(0).size()];
			for (int x = 0; x < maze.length; x++)
				for (int y = 0; y < maze[0].length; y++)
					maze[x][y] = rows.get(x).get(y);
			
			return new Maze(maze, start, end);
		}
	}
	
	public void printSolution() {
		System.out.print("Starting from ");
		System.out.println(maze.getStart().prettify());
		
		System.out.print("Exit is at ");
		System.out.println(maze.getEnd().prettify());
		
		try {
			this.maze.solve();
			
			System.out.println(this.maze.prettify());
		} catch (NoSolutionException e) {
			System.out.println("No solution");
		}
	}

}

