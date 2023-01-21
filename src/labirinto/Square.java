package labirinto;

public class Square implements Pretty, FromString {
	
	private SquareStatus status;
	
	public Square (SquareStatus status) {
		this.status = status;
	}

	@Override
	public String prettify() {
		
		switch(this.status) {
		case FREE: 
			return "  ";
		
		case VISITED:
			return "░░";
			
		case WALL:
		default:
			return "██";
		
		}
	}

	public SquareStatus getStatus() {
		return status;
	}

	public void setStatus(SquareStatus status) {
		this.status = status;
	}
	
	public static Square fromString(String s) {
		
		if(s.equals("."))
			return new Square(SquareStatus.FREE);
		else
			return new Square(SquareStatus.WALL);
		
	}
	
	public static Square fromChar(char c) { 
		return Square.fromString(Character.toString(c));
	}
	

}
