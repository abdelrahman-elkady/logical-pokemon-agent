
/**
 * Created by kady on 14/10/16.
 *
 * @author kady
 */
public class Cell {
	private Location location;
	private boolean visited;
	private boolean wall;

	private boolean pokemon;

	public Cell(Location location) {
		this(location.getX(), location.getY());
	}

	public Cell(int x, int y) {
		this.location = new Location(x, y);
		this.visited = false;
		this.wall = true; // initially all cells are walls
	}

	@Override
	public String toString() {
		return String.format("Cell at(%d, %d)", location.getY(),
				location.getX());
	}

	// ============ Setters ============

	public void setLocation(Location location) {
		this.location.setX(location.getX());
		this.location.setY(location.getY());
	}

	public void setLocation(int x, int y) {
		this.location.setX(x);
		this.location.setY(y);
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public void setWall(boolean wall) {
		this.wall = wall;
	}

	public void setPokemon(boolean occupiedWithPokemon) {
		this.pokemon = occupiedWithPokemon;
	}

	// ============ Getters ============

	public Location getLocation() {
		return this.location;
	}

	public boolean isVisited() {
		return visited;
	}

	public boolean isWall() {
		return wall;
	}

	public boolean havePokemon() {
		return pokemon;
	}
}
