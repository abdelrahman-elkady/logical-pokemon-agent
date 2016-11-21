% ------------- Generated Maze -------------

%!Generated maze:

/**
 * ----------
 * |E |@    |
 * -  -  -  -
 * |  |A |  |
 * -  ----  -
 * |      @ |
 * ----------
 */


max_x(3).
max_y(3).
start_location(1, 1).
end_location(0, 0).

has_pokemon(2, 2).
has_pokemon(0, 1).

has_north_wall(0, 0).
has_north_wall(0, 1).
has_north_wall(0, 2).
has_north_wall(2, 1).

has_east_wall(0, 0).
has_east_wall(0, 2).
has_east_wall(1, 0).
has_east_wall(1, 1).
has_east_wall(2, 2).
has_east_wall(1, 2).

has_west_wall(0, 0).
has_west_wall(0, 1).
has_west_wall(1, 0).
has_west_wall(2, 0).
has_west_wall(1, 1).
has_west_wall(1, 2).

has_south_wall(1, 1).
has_south_wall(2, 0).
has_south_wall(2, 1).
has_south_wall(2, 2).


% ------------- Helpers -------------
in_bounds(ROW,COL):-
  max_x(ROW_MAX),
  max_y(COL_MAX),
  ROW >= 0,
  ROW < ROW_MAX,
  COL >= 0,
  COL < COL_MAX.


add_tail([],X,[X]).
add_tail([H|T],X,[H|L]):-add_tail(T,X,L).


% ------------- Inference Rules -------------

agent(1, 1, n, 0, [], s0 ).

agent(ROW, COL, ORIENT, POKEMONS, GRABBED, result(A, S)):-

  agent(ROW_0, COL_0, ORIENT_0, POKEMONS_0, GRABBED_0, S),

  (
    (
      A          = forward,
      ORIENT_0   = ORIENT,
      POKEMONS_0 = POKEMONS,
      GRABBED_0  = GRABBED,
      (

      ( ORIENT==n, ROW is ROW_0 - 1, COL = COL_0, in_bounds(ROW,COL), \+has_north_wall(ROW_0, COL_0));
      ( ORIENT==s, ROW is ROW_0 + 1, COL = COL_0, in_bounds(ROW,COL), \+has_south_wall(ROW_0, COL_0));
      ( ORIENT==e, ROW = ROW_0, COL is COL_0 + 1, in_bounds(ROW,COL), \+has_east_wall(ROW_0, COL_0));
      ( ORIENT==w, ROW = ROW_0, COL is COL_0 - 1, in_bounds(ROW,COL), \+has_west_wall(ROW_0, COL_0))

      )
    );

    (

      A          = rotate_right,
      ROW_0      = ROW,
      COL_0      = COL,
      POKEMONS_0 = POKEMONS,
      GRABBED_0  = GRABBED,
      (

      ( ORIENT=n, ORIENT_0=w );
      ( ORIENT=s, ORIENT_0=e );
      ( ORIENT=e, ORIENT_0=n );
      ( ORIENT=w, ORIENT_0=s )

      )

    );

    (

      A          = rotate_left,
      ROW_0      = ROW,
      COL_0      = COL,
      POKEMONS_0 = POKEMONS,
      GRABBED_0  = GRABBED,
      (

      ( ORIENT=n, ORIENT_0=e );
      ( ORIENT=s, ORIENT_0=w );
      ( ORIENT=e, ORIENT_0=s );
      ( ORIENT=w, ORIENT_0=n )

      )

    );

    (

      A=grab,
      ROW_0 = ROW,
      COL_0 = COL,
      has_pokemon(ROW_0, COL_0),
      \+member((ROW_0, COL_0), GRABBED_0),
      add_tail(GRABBED_0, (ROW_0, COL_0), GRABBED),
      POKEMONS is POKEMONS_0 + 1

    )

  ).
