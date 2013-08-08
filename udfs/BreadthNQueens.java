package myudfs;
import java.io.IOException;
import org.apache.pig.EvalFunc;
import org.apache.pig.data.Tuple;
import org.apache.pig.data.DataBag;
import org.apache.pig.data.BagFactory;
import org.apache.pig.data.TupleFactory;

public class BreadthQueensN extends EvalFunc<DataBag>
{
    BagFactory bagFactory = BagFactory.getInstance();
    TupleFactory tupleFactory = TupleFactory.getInstance();

    public DataBag exec(Tuple input) throws IOException {
        if (input == null || input.size() == 0)
            return null;
        try{
            Integer left = (Integer) input.get(1),
                col = (Integer) input.get(2),
                right = (Integer) input.get(3),
                cap = (1<<(Integer)input.get(0)) - 1,
                possible = ~(left | col | right) & cap,
                pick, board;

            DataBag results = bagFactory.newDefaultBag();
            while(possible != 0) {
                Tuple newResult = tupleFactory.newTuple(3);
                pick = possible & -possible;
                possible ^= pick;
                newResult.set(0, (left | pick) << 1);
                newResult.set(1, col | pick);
                newResult.set(2, (right | pick) >>> 1);
                results.add(newResult);
            }

            return results;
        }catch(Exception e){
            throw new IOException("Caught exception processing input row ", e);
        }
    }
}