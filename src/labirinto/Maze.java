package labirinto;

import java.util.ArrayList;
import java.util.List;

public class Maze extends Solver<List<Coord>> implements Pretty{
	
	private Coord start;
	private Coord end;
	private List<Coord> solution;
	private Square[][] maze;

	public Maze(Square[][] maze, Coord start, Coord end) {
		this.start = start;
		this.end = end;
		this.maze = maze;
		
	}

	@Override
	public String prettify() {
		if(this.maze.length == 0)
			return "";
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("╔");
		for(int i = 0; i < maze[0].length; i++) {
			sb.append("══");
		}
		sb.append("╗\n");
		
		for(Square[] row : maze) {
			sb.append("║");
			
			for(Square s : row)
				sb.append(s.prettify());
			sb.append("║\n");
		}
		
		sb.append("╚");
		for(int i = 0; i < maze[0].length; i++) {
			sb.append("══");
		}
		sb.append("╝\n");
		
		
		return sb.toString();
	}

	@Override
	public List<Coord> solve() throws NoSolutionException {
		solution = new ArrayList<Coord>();
		
		for(Square[] row : maze) {
			for(Square s : row)
				if(s.getStatus().equals(SquareStatus.VISITED))
					s.setStatus(SquareStatus.FREE);
		}
		
		if(solve(start))
			return solution;
		
		throw new NoSolutionException();
	}

	public Coord getStart() {
		return start;
	}


	public Coord getEnd() {
		return end;
	}
	
	private boolean solve(Coord c) {
		
		solution.add(c);
		
		maze[c.x()][c.y()].setStatus(SquareStatus.VISITED);
		
		if(c.equals(end))
			return true;
		
		Coord tmp;
		
		tmp = new Coord(c.x()-1, c.y());
		if(isFree(tmp) && solve(tmp))
			return true;
		
		tmp = new Coord(c.x()+1, c.y());
		if(isFree(tmp) && solve(tmp))
			return true;
		
		tmp = new Coord(c.x(), c.y()-1);
		if(isFree(tmp) && solve(tmp))
			return true;
		
		tmp = new Coord(c.x(), c.y()+1);
		if(isFree(tmp) && solve(tmp))
			return true;
		
		solution.remove(solution.size()-1);
		maze[c.x()][c.y()].setStatus(SquareStatus.FREE);
		return false;
		
	}
	
	private boolean isFree(Coord c) {
		return isSafe(c) && maze[c.x()][c.y()].getStatus().equals(SquareStatus.FREE);
	}

	private boolean isSafe(Coord c) {
		return c.x() >= 0 && c.x() <= maze.length && c.y() >= 0 && c.y() <= maze[0].length;
	}

}
