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

% ------------- Helpers -------------
in_bounds(X,Y):-
  max_x(X_MAX),
  max_y(Y_MAX),
  X >= 0,
  X < X_MAX,
  Y >= 0,
  Y < Y_MAX.

% ------------- Inference Rules -------------


at(0, 0, e, s0 ).

at(X,Y,ORIENT, result(A, S)):-

  at(X_0, Y_0, ORIENT_0, S),

  (
    (
      A=forward,
      ORIENT_0 = ORIENT,
      (

      ( ORIENT==n, Y is Y_0 - 1, X = X_0, in_bounds(X,Y), \+has_south_wall(X, Y));
      ( ORIENT==s, Y is Y_0 + 1, X = X_0, in_bounds(X,Y), \+has_north_wall(X, Y));
      ( ORIENT==e, Y = Y_0, X is X_0 + 1, in_bounds(X,Y), \+has_west_wall(X, Y));
      ( ORIENT==w, Y = Y_0, X is X_0 - 1, in_bounds(X,Y), \+has_east_wall(X, Y))

      )
    );

    (

      A=rotate_right,

      (

      ( ORIENT==n, ORIENT_0=w );
      ( ORIENT==s, ORIENT_0=e );
      ( ORIENT==e, ORIENT_0=n );
      ( ORIENT==w, ORIENT_0=s )

      )

    )
  ).




  % Y_0 is Y - 1,
  % at(X, Y_0, S),
  % A=forward,
  % ORIENT=n,
