import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class main {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<ArrayList<Long>> FromDist = new ArrayList<ArrayList<Long>>();
        ArrayList<ArrayList<Long>> Bridges = new ArrayList<ArrayList<Long>>();
        ArrayList<Long> Points = new ArrayList<Long>();
        
        boolean flag = true;
        while(flag){
            System.out.println("Введите 1 - если хотите добавить мост, введите 2 - если все мосты введены");
            int i = scanner.nextInt();
            switch (i) {
                case 1:
                    long from = scanner.nextLong();
                    long to = scanner.nextLong();
                    long way = scanner.nextLong();
                    boolean availability = false;
                    if (!Points.contains(from)){
                        Points.add(from);
                    }
                    if (!Points.contains(to)){
                        Points.add(to);
                    }
                    for(ArrayList<Long> bridge: Bridges){
                        if(bridge.get(0).equals(from) && bridge.get(1).equals(to)){
                            availability = true;
                        }
                    }
                    if (!availability){
                        ArrayList<Long> newBridge = new ArrayList<>();
                        newBridge.add(from);
                        newBridge.add(to);
                        newBridge.add(way);
                        Bridges.add(newBridge);
                    }
                    else{
                        System.out.println("Это путь уже записан");
                    }
                    break;
                case 2:
                    flag = false;
                    System.out.println("Запись завершена");
                    break;
            }
        }

        long INF = 10000000L;

        for (int idx = 0; idx < Points.size(); idx++) {
            ArrayList<Long> Point = new ArrayList<Long>();
            Point.add(-1L);
            Point.add(INF);
            FromDist.add(Point);
        }

        ArrayList<Long> first = new ArrayList<Long>();
        first.add(-1L);
        first.add(0L);
        FromDist.set(0, first);

        for(int y = 0; y < Points.size() - 1; y++){
            for (ArrayList<Long> Bridge : Bridges) {
                long f = Bridge.get(0);
                long t = Bridge.get(1);
                long w = Bridge.get(2);
                
                int idF = Points.indexOf(f);
                int idT = Points.indexOf(t);

                if(FromDist.get(idF).get(1) + w < FromDist.get(idT).get(1)){
                    FromDist.get(idT).set(1, FromDist.get(idF).get(1) + w);
                    FromDist.get(idT).set(0, (long)idF);
                }
            }
        }

        boolean negative = false;
        for (ArrayList<Long> bridge : Bridges) {
            long f = bridge.get(0);
            long t = bridge.get(1);
            long w = bridge.get(2);
            
            int idF = Points.indexOf(f);
            int idT = Points.indexOf(t);
            
            if (FromDist.get(idF).get(1) + w < FromDist.get(idT).get(1)) {
                negative = true;
                break;
            }
        }

        if (!negative){
            for(int i = 0; i < Points.size(); i++){
                long dist = FromDist.get(i).get(1);
                long p = Points.get(i);
                if(dist >= INF){
                    System.out.println("До вершины " + Points.get(i) +" из этой точки невозможно дойти");
                }
                else{
                    System.out.println("Путь до вершины - " + p);
                    print(FromDist, Points, i);
                }
            }
        }
        else{
            System.out.println("Негативная зацикленность");
        }
    }

    public static void print(ArrayList<ArrayList<Long>> FromDist, ArrayList<Long> Points, int idx){
        ArrayList<Integer> theWay = new ArrayList<Integer>();
        int cur = idx;

        while(cur != -1){
            theWay.add(cur);
            long previous = FromDist.get(cur).get(0);
            cur = (int)previous;
            if (theWay.size() > Points.size()) break;
        }

        Collections.reverse(theWay);

        System.out.print("Путь до " + Points.get(idx) + ": ");

        for(int i = 0; i < theWay.size(); i++){
            System.out.print(Points.get(theWay.get(i)));
            if (i < theWay.size() - 1) System.out.print(" - ");
        }
        System.out.println();
    }

}