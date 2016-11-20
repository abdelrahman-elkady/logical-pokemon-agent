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


startLocation(1, 0).
endLocation(1, 1).

hasPokemon(1, 2).
hasPokemon(2, 1).
hasPokemon(0, 1).

hasNorthWall(0, 0).
hasNorthWall(1, 0).
hasNorthWall(2, 0).
hasNorthWall(0, 1).
hasNorthWall(2, 1).
hasNorthWall(1, 2).

hasSouthWall(0, 0).
hasSouthWall(2, 0).
hasSouthWall(1, 1).
hasSouthWall(0, 2).
hasSouthWall(1, 2).
hasSouthWall(2, 2).

hasEastWall(2, 0).
hasEastWall(0, 1).
hasEastWall(2, 1).
hasEastWall(2, 2).

hasWestWall(0, 0).
hasWestWall(0, 1).
hasWestWall(1, 1).
hasWestWall(0, 2).

% ------------- End Generated Maze -------------


% ------------- Inference Rules -------------

at(1, 1, n, result(A,s0) ).

result(_, s0).

at(X,Y,ORIENT, result(A, S)):-

  at(X_0, Y_0, ORIENT, S),
  A=forward,

  (
  ORIENT==n,
  Y is Y_0 - 1,
  X = X_0
  );
  (
  ORIENT==s,
  Y is Y_0 + 1,
  X = X_0
  );
  (
  ORIENT==e,
  Y = Y_0,
  X is X_0 + 1
  );
  (
  ORIENT==w,
  Y = Y_0,
  X is X_0 - 1
  ).




  % Y_0 is Y - 1,
  % at(X, Y_0, S),
  % A=forward,
  % ORIENT=n,
