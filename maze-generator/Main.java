import java.io.File;
import java.io.PrintWriter;

public class Main {
	public static void main(String[] args) throws Exception {
		PrintWriter pw = new PrintWriter(new File("kb.out"));
		Maze maze = new Maze(3, 3);
		StringBuffer sb = maze.printMaze(maze.getStartLocation(), false, true);
		pw.append("%!Generated maze:\n\n");
		pw.append(sb);
		pw.append("\n\n\n");
		appendPredicate("startLocation", pw, maze.getStartLocation().getY(),
				maze.getStartLocation().getX());
		appendPredicate("endLocation", pw, maze.getEndLocation().getY(), maze
				.getEndLocation().getX());
		pw.append("\n");

		for (Cell cell : maze.getPokemonCells()) {
			appendPredicate("hasPokemon", pw, cell.getLocation().getY(), cell
					.getLocation().getX());
		}
		pw.append("\n");

		for (int i = 0; i < maze.getHeight(); i++) {
			for (int j = 0; j < maze.getWidth(); j++) {
				boolean line = false;
				Cell c = maze.getCellByLocation(i, j);
				if (maze.getNorthWalls()[i][j]) {
					appendPredicate("hasNorthWall", pw, c.getLocation().getY(),
							c.getLocation().getX());
					line = true;
				}
				if (maze.getSouthWalls()[i][j]) {
					appendPredicate("hasSouthWall", pw, c.getLocation().getY(),
							c.getLocation().getX());
					line = true;
				}
				if (maze.getEastWalls()[i][j]) {
					appendPredicate("hasEastWall", pw, c.getLocation().getY(),
							c.getLocation().getX());
					line = true;
				}
				if (maze.getWestWalls()[i][j]) {
					appendPredicate("hasWestWall", pw, c.getLocation().getY(),
							c.getLocation().getX());
					line = true;
				}
				if (line)
					pw.append("\n");
			}

		}
		pw.flush();
		pw.close();
	}

	private static void appendPredicate(String predicateName, PrintWriter pw,
			Object... args) {
		StringBuffer format = new StringBuffer(predicateName + "(");
		for (int i = 0; i < args.length; i++) {
			if (i + 1 < args.length)
				format.append("%d, ");
			else
				format.append("%d)\n");
		}
		pw.append(String.format(format.toString(), args));
	}
}
