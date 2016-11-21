%!Generated maze:

/**
 * ----------
 * |   A |@ |
 * -  ----  -
 * |        |
 * -------  -
 * |@  @  E |
 * ----------
 */


max_x(3).
max_y(3).

hatch_time(3).

start_location(0, 1).
end_location(2, 2).

has_pokemon(0, 2).
has_pokemon(2, 1).
has_pokemon(2, 0).

has_north_wall(0, 0).
has_north_wall(0, 1).
has_north_wall(0, 2).
has_north_wall(1, 1).
has_north_wall(2, 0).
has_north_wall(2, 1).

has_east_wall(0, 1).
has_east_wall(0, 2).
has_east_wall(1, 2).
has_east_wall(2, 2).

has_west_wall(0, 0).
has_west_wall(0, 2).
has_west_wall(1, 0).
has_west_wall(2, 0).

has_south_wall(0, 1).
has_south_wall(1, 0).
has_south_wall(1, 1).
has_south_wall(2, 0).
has_south_wall(2, 1).
has_south_wall(2, 2).
