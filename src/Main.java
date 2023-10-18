import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {

        List<String[]>  deliveriesData = getDeliveriesData();
        List<String[]>  matchesData = getMatchesData();
        findMatchesPlayedPerYearInIpl(matchesData);
        find
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