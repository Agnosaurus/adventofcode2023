package utils.math;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Proportional implements Function {

    Interval interval;
    Double increment;

    @Override
    public Interval getInterval() {
        return interval;
    }

    @Override
    public Double getImage(Double x) {
        return x + increment;
    }

    @Override
    public boolean isApplicable(Double x) {
        return interval.contains(x);
    }


}
