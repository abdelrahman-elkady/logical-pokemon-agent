import java.util.Arrays;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by kady on 14/10/16.
 *
 * @author kady
 */
public class Maze {
	private int pokemonCount;

	private Location startLocation;
	private Location endLocation;

	private int eggHatchingInterval;

	private int width;
	private int height;

	private Cell[][] maze;

	private boolean[][] northWalls;
	private boolean[][] southWalls;
	private boolean[][] eastWalls;
	private boolean[][] westWalls;
	private LinkedList<Cell> openCells;
	private LinkedList<Cell> pokemonCells;

	public Maze(int width, int height) {
		if (width < 3 || height < 3) {
			System.err.println("Maze size must be at least 3x3");
		}

		this.height = height;
		this.width = width;

		startLocation = Location.generateRandomLocation(width, height);
		endLocation = Location.generateRandomLocation(width, height);
		openCells = new LinkedList<Cell>();

		// TODO: That looks silly
		while (endLocation.equals(startLocation)) {
			endLocation = Location.generateRandomLocation(width, height);
		}

		initializeMazeCells();
		initializeMazeWalls();
		generateMaze(startLocation.getY(), startLocation.getX());
		generatePokemonLocations();

	}

	public Maze() {
		// TODO Auto-generated constructor stub
	}

	private void initializeMazeCells() {
		maze = new Cell[height][width];
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				maze[row][col] = new Cell(col, row);
			}
		}
	}

	private void generatePokemonLocations() {
		// At least two points should
		// be free of freaking pokemons
		int pokemonLimit = openCells.size() - 2;

		// Read the docs for ThreadLocalRandom usage ;)
		int maxPokemonCount = ThreadLocalRandom.current().nextInt(2,
				pokemonLimit + 1);

		maxPokemonCount = Math.min(maxPokemonCount,
				Math.min(this.width, this.height));

		int randomIndex;

		// Location.equals() is overridden, safe to use contains()
		// to make sure that the locations are unique
		pokemonCells = new LinkedList<Cell>();
		LinkedList<Cell> openTmp = new LinkedList<Cell>();
		for (Cell c : openCells)
			openTmp.add(c);
		while (pokemonCount < maxPokemonCount) {
			randomIndex = ThreadLocalRandom.current().nextInt(openCells.size());

			Cell cell = openCells.remove(randomIndex);

			if (!cell.getLocation().equals(startLocation)
					&& !cell.getLocation().equals(endLocation)) {
				cell.setPokemon(true);
				pokemonCells.add(cell);
				pokemonCount++;
			}

		}
		openCells.clear();
		for (Cell c : openTmp)
			openCells.add(c);

	}

	private void initializeMazeWalls() {
		northWalls = new boolean[height][width];
		southWalls = new boolean[height][width];
		eastWalls = new boolean[height][width];
		westWalls = new boolean[height][width];
		for (int row = 0; row < height; row++) {
			Arrays.fill(northWalls[row], true);
			Arrays.fill(southWalls[row], true);
			Arrays.fill(eastWalls[row], true);
			Arrays.fill(westWalls[row], true);
		}
	}

	public void generateMaze(int row, int col) {
		maze[row][col].setWall(false);
		openCells.add(maze[row][col]);

		// while there is an unvisited neighbor
		while (inBoundsNotVisited(row, col + 1)
				|| inBoundsNotVisited(row + 1, col)
				|| inBoundsNotVisited(row, col - 1)
				|| inBoundsNotVisited(row - 1, col)) {

			// pick random neighbor (could use Knuth's trick instead)
			while (true) {
				int r = ThreadLocalRandom.current().nextInt(4);
				if (r == 0 && inBoundsNotVisited(row, col + 1)) {
					eastWalls[row][col] = false;
					westWalls[row][col + 1] = false;
					generateMaze(row, col + 1);
					break;
				} else if (r == 1 && inBoundsNotVisited(row + 1, col)) {
					southWalls[row][col] = false;
					northWalls[row + 1][col] = false;
					generateMaze(row + 1, col);
					break;
				} else if (r == 2 && inBoundsNotVisited(row, col - 1)) {
					westWalls[row][col] = false;
					eastWalls[row][col - 1] = false;
					generateMaze(row, col - 1);
					break;
				} else if (r == 3 && inBoundsNotVisited(row - 1, col)) {
					northWalls[row][col] = false;
					southWalls[row - 1][col] = false;
					generateMaze(row - 1, col);
					break;
				}
			}
		}
	}

	public boolean canMove(Location location, ORIENTATION orientation) {
		int col = location.getX();
		int row = location.getY();
		switch (orientation) {
		case NORTH:
			return !northWalls[row][col];
		case SOUTH:
			return !southWalls[row][col];
		case EAST:
			return !eastWalls[row][col];
		case WEST:
			return !westWalls[row][col];
		default:
			break;
		}
		return false;
	}

	public StringBuffer printMaze(Location agentLoc, boolean p, boolean addC) {
		StringBuffer out = new StringBuffer();
		if (addC)
			out.append("/**\n * ");
		for (int i = 0; i < width * 3 + 1; i++) {
			out.append('-');
		}
		out.append('\n');
		for (int row = 0; row < height; row++) {
			if (addC)
				out.append(" * ");
			for (int col = 0; col < width; col++) {
				if (westWalls[row][col])
					out.append('|');
				else
					out.append(' ');

				if (agentLoc.getX() == col && agentLoc.getY() == row)
					out.append("A ");
				else if (startLocation.getX() == col
						&& startLocation.getY() == row)
					out.append("S ");
				else if (endLocation.getX() == col && endLocation.getY() == row)
					out.append("E ");
				else if (maze[row][col].havePokemon())
					out.append("@ ");
				else
					out.append("  ");
			}
			out.append("|\n");
			if (addC)
				out.append(" * ");
			out.append('-');
			for (int col = 0; col < width; col++) {
				if (southWalls[row][col])
					out.append("---");
				else
					out.append("  -");
			}
			out.append("\n");
		}
		if (addC)
			out.append(" */");
		if (p)
			System.out.print(out);
		return out;
	}

	public boolean inBoundsNotVisited(int row, int col) {
		return inBounds(row, col) && maze[row][col].isWall();
	}

	public boolean inBounds(Cell cell) {
		return inBounds(cell.getLocation());
	}

	public boolean inBounds(Location location) {
		return inBounds(location.getY(), location.getX());
	}

	public boolean inBounds(int row, int col) {
		return row >= 0 && row < height && col >= 0 && col < width;
	}

	public Cell getCellByLocation(Location location) {
		return this.maze[location.getY()][location.getX()];
	}

	public Cell getCellByLocation(int row, int col) {
		return this.maze[row][col];
	}

	public boolean[][] getNorthWalls() {
		return northWalls;
	}

	public boolean[][] getSouthWalls() {
		return southWalls;
	}

	public boolean[][] getEastWalls() {
		return eastWalls;
	}

	public boolean[][] getWestWalls() {
		return westWalls;
	}

	public int getPokemonCount() {
		return pokemonCount;
	}

	public void setPokemonCount(int pokemonCount) {
		this.pokemonCount = pokemonCount;
	}

	public Location getStartLocation() {
		return startLocation;
	}

	public void setStartLocation(Location startLocation) {
		this.startLocation = startLocation;
	}

	public Location getEndLocation() {
		return endLocation;
	}

	public void setEndLocation(Location endLocation) {
		this.endLocation = endLocation;
	}

	public int getEggHatchingInterval() {
		return eggHatchingInterval;
	}

	public void setEggHatchingInterval(int eggHatchingInterval) {
		this.eggHatchingInterval = eggHatchingInterval;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public LinkedList<Cell> getOpenCells() {
		return openCells;
	}

	public void setOpenCells(LinkedList<Cell> openCells) {
		this.openCells = openCells;
	}

	public LinkedList<Cell> getPokemonCells() {
		return pokemonCells;
	}

	public void setPokemonCells(LinkedList<Cell> pokemonCells) {
		this.pokemonCells = pokemonCells;
	}

}
