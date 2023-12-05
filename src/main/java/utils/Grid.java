package utils;


import java.util.*;

/**
 * Grid.... maybe a bit over engineered
 * start at 0:0
 * @param <T>
 */
public class Grid <T> {

    List<GridElement<T>> dataList = new ArrayList<>();
    Map<GridCoord,GridElement<T>> dataMap = new HashMap<>();


    public Grid() {
        super();
    }

    public void add( GridElement<T> gridElement){
        dataList.add(gridElement);
        dataMap.put(gridElement.getCoord(),gridElement);
    }


    public void add(GridCoord coord,T element){
        add( new GridElement<>(coord, element));
    }
    public void add(int X, int Y,T element){
        add( new GridCoord(X,Y),element);
    }



    public List<GridElement<T>> getElements(){
        return dataList;
    }

    public GridElement<T> getByCoord(GridCoord coord){
        return dataMap.get(coord);
    }

    public GridElement<T> getByCoord(int X, int Y){
        return getByCoord(new GridCoord(X,Y));
    }



    public List<GridElement<T>> getAdjacentCase(int X, int Y){
        List<GridElement<T>> result = new ArrayList<>();

        // Check all adjacent possibility, checked line by line
        addIfExists(X-1 ,Y+1,result);
        addIfExists(X ,Y+1,result);
        addIfExists(X+1 ,Y+1,result);

        addIfExists(X-1 ,Y,result);
        addIfExists(X+1 ,Y,result);

        addIfExists(X-1 ,Y-1,result);
        addIfExists(X ,Y-1,result);
        addIfExists(X+1 ,Y-1,result);

        return result;
    }

    public Set<GridElement<T>> getAdjacentCases(List<GridElement<T>> input){
        Set<GridElement<T>> result = new HashSet<>();

        for (GridElement<T> element : input){
            result.addAll(getAdjacentCase(element.getCoord()));
        }

        return result;

    }

    public List<GridElement<T>> getAdjacentCase(GridCoord coord){
        return getAdjacentCase(coord.getX(),coord.getY());
    }

    private void addIfExists(int X, int Y,List<GridElement<T>> result){
        GridElement<T> element = getByCoord(X,Y);
        if ( element != null)
            result.add(element);
    }


}
