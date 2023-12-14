package day5;

import lombok.extern.slf4j.Slf4j;
import utils.FileReaderUtils;
import utils.math.Function;
import utils.math.Interval;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Slf4j
public class Almanach2 {

    Map<Type,RangedMap> mappings = new HashMap<>();
    List<Interval> seedsInterval = new ArrayList<>();

    Almanach2(String resource){
        super();

        // On cree les maps, les fonctions et les intervalles de début
        FileReaderUtils utils = new FileReaderUtils();
        RangedMap currentMap = new RangedMap();
        try {
            List<String> data = utils.readFile(resource);
            for (String datum : data) {
                // Seed line
                if ( datum.startsWith("seeds")){
                    //Init intervals
                    initSeedsAsInterval(datum);

                    //Map declaration line
                } else if (datum.endsWith("map:")){
                    currentMap = new RangedMap();
                    Type type = extractType(datum);
                    mappings.put(type, currentMap);
                    //Map data line
                } else if ( !datum.trim().isEmpty()) {
                    addRange(datum,currentMap);
                }



            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        double result = applyMappings(seedsInterval).stream()
                .map(i -> i.getStart())
                .min(Double::compareTo)
                .get();

        // resolveClosestSeed(seedsString);
        log.info("Done :"+result);

        // Init maps
    }

//TODO : les Map en fait sont des fonctions affines presque, donc il "suffit" de calculer les bornes des images
    // Il faut
    /** Une classe aui modelise le comportement de la fonction
     *  Un truc qui calcule l image d un range, et donne du coup plusieurs ranges
     *  qqch qui aggrége les range peut être ?
     *  En fait il ne nous faut que qqs points : le debut du range de depart, le debut du range d'arrivée-1, la fin du range de départ
     *
     */


    public List<Interval> applyMappings(List<Interval> intervals) {
        List<Interval> curIntervals = intervals;
        for (Type type : Type.getSortedValues()){
            curIntervals=  computeIntervalForType(curIntervals,type);
            log.info("Applying {}, intervals result size : {}",type,curIntervals.size());
        }

        return curIntervals;
    }

    /**
     * IL FAUT TESTER CE BIDULE
     * @param intervals
     * @param type
     * @return
     */
    public List<Interval> computeIntervalForType(List<Interval> intervals, Type type){
        List<Interval> imageIntervals = new ArrayList<>();

        RangedMap mapping = mappings.get(type);
        for ( Interval interval : intervals){

            List<Double> tmpBornes = new ArrayList<>();
            double startInterval = interval.getStart();
            double endInterval = interval.getEnd();

            tmpBornes.add(startInterval);
            tmpBornes.add(endInterval);


            for ( Function f : mapping.getFunctions()){
                double startI = f.getInterval().getStart();
                double endI = f.getInterval().getEnd();

                if (startI> startInterval && startI < endInterval )
                    tmpBornes.add(startI);

                if (endI> startInterval && endI < endInterval )
                    tmpBornes.add(endI);
            }
/**

            mapping.getFunctions().stream()
                    .map(f -> f.getInterval())
                    .map(i -> interval.getStart())
                    .filter(s -> )
                    .forEach(s -> );

            mapping.getFunctions().stream()
                    .map(f -> f.getInterval())
                    .map(i -> interval.getEnd())
                    .filter(e -> e > startInterval && e <= endInterval)
                    .forEach(e -> tmpBornes.add(e));
*/
            //On a tous les points importants, on va calculer tous les intervales résultant

            tmpBornes.sort(Double::compareTo);

            List<Double> bornes = tmpBornes.stream()
                    .distinct()
                    .collect(toList());

            if (bornes.size() == 1)
                bornes.add(bornes.get(0));


            //

            // On a les images des intervales maintenant !
            // On essaye pas de les merge dans un premier temps !
            for ( int i=0 ; i< bornes.size()-1 ; i++){

                double start = mapping.getImage(bornes.get(i));
                double size = mapping.getImage(bornes.get(i+1))- mapping.getImage(bornes.get(i))+1;

               // log.info("Interval image : "+start +"-"+size);

                imageIntervals.add(new Interval(start,size));
            }


        }

        return imageIntervals;
    }
    /**
     public Double resolveClosestSeed(){
     String seedsS = seedsString;
     List<String> seedsL = Arrays.stream(seedsS.split("\\:")[1].split("\\ ")).filter(s -> !s.isEmpty()).collect(Collectors.toList());
     double result = Double.MAX_VALUE;

     int cursor = 0;

     for (int i = 0 ; i < seedsL.size() ; i++){
     Double start = Double.parseDouble(seedsL.get(i));
     i++;
     Double delta = Double.parseDouble(seedsL.get(i));

     for ( int increment = 0 ; increment < delta ; increment++ ){
     Range currentRange = new Range(start,delta);
     // We are on a seed range... let's test everything within the range

     for ( double seed = currentRange.getStart(); seed < currentRange.getStart()+ currentRange.getSize() ; seed++){

     double currentresult = applyMappings(seed);

     cursor++;

     if ( cursor>10000000){
     log.info("Processed 10M");
     cursor=0;
     }


     if ( result > currentresult) {
     log.info("New value "+currentresult);
     result = currentresult;
     }
     }
     }
     }


     log.info(""+result);
     return result;
     }

     /**
     public Double applyMappings(Double seed){
     Double curValue = seed;


     return curValue;
     }*/

    // Test OK
    public static Type extractType(String data){
        return  Type.valueOf(data.split("-")[2].split(" ")[0].toUpperCase());
    }


    //Test OK
    public void addRange(String rangeS,RangedMap rangedMap){
        String[] parsedRange = rangeS.split(" ");
        double sourceStart = Double.parseDouble(parsedRange[1]);
        double destStart = Double.parseDouble(parsedRange[0]);
        double range = Double.parseDouble(parsedRange[2]);

        rangedMap.addRange(sourceStart,destStart,range);
    }

    // seeds: 79 14 55 13
    public static void initSeeds(String seedsS,List<Double> seeds){
        String[] seedsA = seedsS.split("\\:")[1].split("\\ ");

        Arrays.stream(seedsA).filter(s -> !s.isEmpty()).forEach(seed -> seeds.add(Double.parseDouble(seed)));
    }


    // seeds: 79 14 55 13
    public  void initSeedsAsInterval(String seedsS){

        List<String> seedsL = Arrays.stream(seedsS.split("\\:")[1].split("\\ ")).filter(s -> !s.isEmpty()).collect(toList());
        for (int i = 0 ; i < seedsL.size() ; i++){
            Double start = Double.parseDouble(seedsL.get(i));
            i++;
            Double delta = Double.parseDouble(seedsL.get(i));

            seedsInterval.add(new Interval(start,delta));


        }
    }


    /**  public  void initSeedsAsRange(String seedsS){
     List<String> seedsL = Arrays.stream(seedsS.split("\\:")[1].split("\\ ")).filter(s -> !s.isEmpty()).collect(Collectors.toList());


     for (int i = 0 ; i < seedsL.size() ; i++){
     Double start = Double.parseDouble(seedsL.get(i));
     i++;
     Double delta = Double.parseDouble(seedsL.get(i));

     for ( int increment = 0 ; increment < delta ; increment++ ){
     seedRanges.add(new Range(start,delta));

     }

     log.info("Seed range parser : "+ start+"/"+delta);

     }

     }*/

}
