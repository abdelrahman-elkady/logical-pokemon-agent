import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the width and height of the maze:");
		int width = sc.nextInt();
		int height = sc.nextInt();
		PrintWriter pw = new PrintWriter(new File("kb.out"));

		Maze maze = new Maze(width, height);
		StringBuffer sb = maze.printMaze(maze.getStartLocation(), false, true);
		pw.append("%!Generated maze:\n\n");
		pw.append(sb);
		pw.append("\n\n\n");


		appendPredicate("max_x", pw, width);
		appendPredicate("max_y", pw, height);
		pw.append("\n");
		appendPredicate("hatch_time", pw, maze.getEggHatchingInterval());
		pw.append("\n");
		appendPredicate("start_location", pw, maze.getStartLocation().getY(),
				maze.getStartLocation().getX());
		appendPredicate("end_location", pw, maze.getEndLocation().getY(), maze
				.getEndLocation().getX());
		pw.append("\n");
		appendPredicate("pokemon_count", pw, maze.getPokemonCells().size());
		pw.append("\n");

		for (Cell cell : maze.getPokemonCells()) {
			appendPredicate("has_pokemon", pw, cell.getLocation().getY(), cell
					.getLocation().getX());
		}
		pw.append("\n");

		for (int i = 0; i < maze.getHeight(); i++) {
			for (int j = 0; j < maze.getWidth(); j++) {
				boolean line = false;
				Cell c = maze.getCellByLocation(i, j);
				if (maze.getNorthWalls()[i][j]) {
					appendPredicate("has_north_wall", pw, c.getLocation().getY(),
							c.getLocation().getX());
					line = true;
				}
				if (maze.getSouthWalls()[i][j]) {
					appendPredicate("has_south_wall", pw, c.getLocation().getY(),
							c.getLocation().getX());
					line = true;
				}
				if (maze.getEastWalls()[i][j]) {
					appendPredicate("has_east_wall", pw, c.getLocation().getY(),
							c.getLocation().getX());
					line = true;
				}
				if (maze.getWestWalls()[i][j]) {
					appendPredicate("has_west_wall", pw, c.getLocation().getY(),
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
				format.append("%d).\n");
		}
		pw.append(String.format(format.toString(), args));
	}
}
