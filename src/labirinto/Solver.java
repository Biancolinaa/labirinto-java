package labirinto;

public abstract class Solver <T> {
	public abstract T solve() throws NoSolutionException;

}
