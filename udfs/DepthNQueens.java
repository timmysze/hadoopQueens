package myudfs;
import java.io.IOException;
import org.apache.pig.EvalFunc;
import org.apache.pig.data.Tuple;
import java.util.List;
import java.util.ArrayList;

public class DepthQueensN extends EvalFunc<Integer>
{
    public Integer exec(Tuple input) throws IOException {
        if (input == null || input.size() < 4)
            return null;
        try{
            Integer cap = (1<<(Integer)input.get(0)) - 1,
              result = 0,
              pick, possible, count = 0;

            List<Integer> board;
            List<List<Integer>> storage = new ArrayList();
            List newBoard = new ArrayList();

            newBoard.add((Integer)input.get(1));
            newBoard.add((Integer)input.get(2));
            newBoard.add((Integer)input.get(3));
            storage.add(newBoard);

            while(storage.size() > 0) {
              board = storage.get(0);
              storage.remove(0);
              if (board.get(1) == cap) {
                result++;
                continue;
              }

              possible = ~(board.get(0) | board.get(1) | board.get(2)) & cap;

              while(possible > 0){
                pick = possible & -possible;
                possible ^= pick;
                newBoard = new ArrayList();
                newBoard.add((board.get(0) | pick)<<1);
                newBoard.add(board.get(1) | pick);
                newBoard.add((board.get(2) | pick)>>>1);
                storage.add(newBoard);
              }
            }

            return result;
        }catch(Exception e){
            throw new IOException("Caught exception processing input row ", e);
        }
    }
}
