package utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@AllArgsConstructor
public class GridCoord {

    int X;
    int Y;

    @Override
    public String toString() {
        return X+":"+Y;
    }
}
