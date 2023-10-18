import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        List<String[]>  deliveriesData = getDeliveriesData();
        List<String[]>  matchesData = getMatchesData();
//        findMatchesPlayedPerYearInIpl(matchesData);
//        findMatchesWonPerTeam(matchesData);
//        findExtraRunsScoredIn2016PerTeam(matchesData, deliveriesData);
        findTopEconomicalBowlersOf2015(matchesData, deliveriesData);
    }

    private static void findTopEconomicalBowlersOf2015(List<String[]> matchesData, List<String[]> deliveriesData) {
        Set<String> matchIdsOf2015Matches = new TreeSet<>();
        for (String[] match : matchesData){
            if(match[1].equals("2015")){
                matchIdsOf2015Matches.add(match[0]);
            }
        }
        Map<String, Integer[]> bowlersWithTheirRunsGivesAndDeliveries = new HashMap<>();
            for(String[] delivery:deliveriesData){
                if(matchIdsOf2015Matches.contains(delivery[0])){
                Integer[] runsAndDeliveries = {Integer.parseInt(delivery[17]),1};
                if(bowlersWithTheirRunsGivesAndDeliveries.containsKey(delivery[8])){
                    runsAndDeliveries[0] += bowlersWithTheirRunsGivesAndDeliveries.get(delivery[8])[0];
                    runsAndDeliveries[1] += bowlersWithTheirRunsGivesAndDeliveries.get(delivery[8])[1];
                }
                bowlersWithTheirRunsGivesAndDeliveries.put(delivery[8], runsAndDeliveries);
            }
        }
        Map<String, Double> bowlersWithTheirEconomy = new HashMap<>();
        for(Map.Entry<String, Integer[]> m : bowlersWithTheirRunsGivesAndDeliveries.entrySet()){
            Integer[] runsAndDeliveries = m.getValue();
            bowlersWithTheirEconomy.put(m.getKey(), ((double) runsAndDeliveries[0])/((double) runsAndDeliveries[1]));
        }
        List <Map.Entry<String, Double>> bowlersSortedByTheirEconomy =new ArrayList<>(bowlersWithTheirEconomy.entrySet());
        Collections.sort(bowlersSortedByTheirEconomy, new Comparator<Map.Entry<String, Double>>() {
            public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
                if(o1.getValue() -o2.getValue() > 0) return -1;
                else if(o1.getValue() -o2.getValue() < 0) return  -1;
                return  0;
            }
        });
         for (Map.Entry<String, Double> m : bowlersSortedByTheirEconomy){
             System.out.println( m.getKey() + " "+ m.getValue());
         }
    }

//    private static Map<String, Integer> sortHashMapByValue(Map<String, Double> bowlersWithTheirEconomy) {
//          Collections.sort(bowlersWithTheirEconomy, new Comparator<Integer>(){
//
//          })
//    }


    private static void findExtraRunsScoredIn2016PerTeam(List<String[]> matchesData, List<String[]> deliveriesData) {
           Set<String> matchIdsOf2016Matches = new TreeSet<>();
           for (String[] match : matchesData){
               if(match[1].equals("2016")){
                   matchIdsOf2016Matches.add(match[0]);
               }
           }
           Map<String, Integer> extraRunsPerTeamIn2016 = new TreeMap<>();
           for (String[] delivery : deliveriesData){
               if(matchIdsOf2016Matches.contains(delivery[0])){
                  extraRunsPerTeamIn2016.put(delivery[3], extraRunsPerTeamIn2016.getOrDefault(delivery[3], 0)+Integer.parseInt(delivery[16]));
               }
           }
          for(Map.Entry m: extraRunsPerTeamIn2016.entrySet()){
              System.out.println(m.getKey()+" :- "+m.getValue());
          }
    }

    private static void findMatchesWonPerTeam(List<String[]> matchesData) {
        Map<String, Integer> matchesWonPerTeam =  new TreeMap<>();
        for (String[] match : matchesData){
            if(match[10].equals("")){
                continue;
            }
            matchesWonPerTeam.put( match[10] , matchesWonPerTeam.getOrDefault( match[10],0)+1);
        }
        for (Map.Entry m : matchesWonPerTeam.entrySet()){
            System.out.println( "Team:-"+m.getKey()+" "+"Wins:-"+m.getValue());
        }
    }

    private static void findMatchesPlayedPerYearInIpl(List<String[]>  matchesData ) {
       Map<Integer, Integer> matchesPerYear =  new TreeMap<>();
       for (String[] match : matchesData){
           matchesPerYear.put(Integer.parseInt(match[1]) , matchesPerYear.getOrDefault(Integer.parseInt(match[1]) ,0)+1);
       }
        for (Map.Entry m : matchesPerYear.entrySet()){
            System.out.println( m.getKey()+" "+m.getValue());
        }
    }




    public static List<String[]> getMatchesData() {
        List< String[]>  matchesData  = new ArrayList<>();
        try {
            FileReader file  = new FileReader("/home/aditya/IdeaProjects/iplJavaProject/src/matches.csv");
            BufferedReader br = new BufferedReader(file);
            while(true){
                String line = br.readLine();
                if(line == null){
                    break;
                }
                String[] match = line.split(",");
                if(match[0].equals("id")){
                    continue;
                }
                matchesData.add(match);
            }
        }
        catch (IOException e){
            System.out.println(e);
        }
        return matchesData;
    }
    public static List<String[]> getDeliveriesData() {
        List< String[]>  deliveriesData  = new ArrayList<>();
        try {
            FileReader file  = new FileReader("/home/aditya/IdeaProjects/iplJavaProject/src/deliveries.csv");
            BufferedReader br = new BufferedReader(file);
            while(true){
                String line = br.readLine();
                if(line == null){
                    break;
                }

                String[] dilivery = line.split(",");
                if(dilivery[0].equals("id")){
                    continue;
                }
                deliveriesData.add(dilivery);
            }
        }
        catch (IOException e){
            System.out.println(e);
        }
        return deliveriesData;
    }
}