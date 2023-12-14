package utils.math;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.With;

@Data
@AllArgsConstructor
public class Interval {

    double start;
    double size;

    public boolean contains(Double x){
        return x >= start  && x < start+size;
    }

    // TODO TESTER ca
    public double getEnd(){
        return start+size-1;
    }
}
