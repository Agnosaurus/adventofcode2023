package utils;

import org.javatuples.Pair;

public class GridElement<T> {

    Pair<GridCoord,T> gridElement;

    public GridElement(GridCoord coord,T element){
        super();
        gridElement = Pair.with(coord, element);
    }


    public T getElement(){
       return gridElement.getValue1();
    }

    public GridCoord getCoord(){
        return gridElement.getValue0();
    }


    @Override
    public String toString() {
        return getCoord() + " " + getElement();
    }

    public boolean isNext(GridElement<T> element){
        return this.getCoord().getY() == element.getCoord().getY()
                && this.getCoord().getX() - element.getCoord().getX() == 1;
    }


}
