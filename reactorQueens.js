depthqueens27.outputSchema = "solutions:int";
openqueens27.outputSchema = "";

function depthqueens27(left, col, right) {
  var storage = [],
      cap = (1<<27) - 1,
      result = 0,
      pick, board = [left, col, right], possible, count = 0;

  storage.push([0,0,0]);
  while (storage.length > 0) {
    board = storage.pop();
    if (board[1] === cap) {
      result++;
      continue;
    }

    possible = ~(board[0] | board[1] | board[2]) & cap;

    while (possible > 0) {
      pick = possible & -possible;
      possible ^= pick;
      storage.push([(board[0] | pick)<<1, (board[1] | pick), (board[2] | pick)>>>1]);
    }

  }
  return result;

};

function breadthqueens27(left, col, right) {
  var result [],
      cap = (1<<27) - 1,
      possible = ~(left | col | right) & cap,
      pick, board;

  while (possible) {
    pick = possible & -possible;
    possible = ^= pick;
    result.push([(left | pick)<<1, col | pick, (right | pick) >>> 1]);
  }

  return result;
};

function openqueens27() {
  var result = [],
      cap = (1<<27) - 1,
      possible, pick;

  possible = cap;

  while (possible) {
    pick = possible & -possible;
    possible ^= pick;
    result.push([pick << 1, pick, pick >>> 1]);
  }

  return result;
};
