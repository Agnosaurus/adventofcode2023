package utils.math;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Identity implements Function{
    Interval interval;

    @Override
    public Interval getInterval() {
        return interval;
    }

    @Override
    public Double getImage(Double x) {
        return x;
    }

    @Override
    public boolean isApplicable(Double x) {
        return interval.contains(x);
    }
}
