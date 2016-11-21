% ------------- Generated Maze -------------

/**
 * ----------
 * |   @    |
 * ----  ----
 * |A |E  @ |
 * -  ----  -
 * |   @    |
 * ----------
 */

max_x(3).
max_y(3).

start_location(1, 0).
end_location(1, 1).

has_pokemon(1, 2).
has_pokemon(2, 1).
has_pokemon(0, 1).

has_north_wall(0, 0).
has_north_wall(1, 0).
has_north_wall(2, 0).
has_north_wall(0, 1).
has_north_wall(2, 1).
has_north_wall(1, 2).

has_south_wall(0, 0).
has_south_wall(2, 0).
has_south_wall(1, 1).
has_south_wall(0, 2).
has_south_wall(1, 2).
has_south_wall(2, 2).

has_east_wall(2, 0).
has_east_wall(0, 1).
has_east_wall(2, 1).
has_east_wall(2, 2).

has_west_wall(0, 0).
has_west_wall(0, 1).
has_west_wall(1, 1).
has_west_wall(0, 2).

% ------------- End Generated Maze -------------


% ------------- Inference Rules -------------

at(1, 1, s, s0 ).

result(_, s0).

at(X,Y,ORIENT, result(A, S)):-

  at(X_0, Y_0, ORIENT, S),
  A=forward,

  (

  ( ORIENT==n, Y is Y_0 - 1, X = X_0 );
  ( ORIENT==s, Y is Y_0 + 1, X = X_0 );
  ( ORIENT==e, Y = Y_0, X is X_0 + 1 );
  ( ORIENT==w, Y = Y_0, X is X_0 - 1 )

  ).




  % Y_0 is Y - 1,
  % at(X, Y_0, S),
  % A=forward,
  % ORIENT=n,
