package labirinto;


public record Coord(int x, int y) implements Pretty, FromString {

	@Override
	public String prettify() {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("(").append(this.x).append(", ").append(this.y).append(")");
	
		return sb.toString();
	}

	@Override
	public boolean equals(Object obj) {
		
		if(obj instanceof Coord) {
			
			Coord c = (Coord) obj;
			
			return this.x == c.x && this.y == c.y;
		}
		
		return false;
	}
	
	public static Coord fromString(String s) throws ParseException {
		
		String[] parts = s.split(" ");
		
		if (parts.length != 2) {
			throw new ParseException("No parse: invalid coord.");
		}
		
		int x = Integer.parseInt(parts[0]);
		int y = Integer.parseInt(parts[1]);
		
		return new Coord(x, y);
		
	}
	

}
