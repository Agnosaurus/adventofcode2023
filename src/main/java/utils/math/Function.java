package utils.math;

public interface Function {

    Interval getInterval();
    Double getImage(Double x);
    boolean isApplicable(Double x);


}
