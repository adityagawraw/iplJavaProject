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
        findExtraRunsScoredIn2016PerTeam(matchesData, deliveriesData);
    }

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