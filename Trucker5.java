import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;


class Trucker5 {
    public void run() {
        // Initialize the number of mileages.
        int n = 0;

        ArrayList<Integer> mileages = new ArrayList<>();
        ArrayList<Integer> locations = new ArrayList<>();
        setup(n, mileages, locations);

        HashMap<Integer, Boolean> isItAvailable = new HashMap<>();

        for (int i = 0; i < mileages.size(); i++) {
            isItAvailable.put(i, true);
        }

        for (int s: solve(mileages, locations, isItAvailable)) {
            System.out.print(s + " ");
        }
    }

    private void setup(int n, ArrayList<Integer> mileages, ArrayList<Integer> locations) {
        Scanner scan = new Scanner(System.in);

        n = scan.nextInt();

        // Add all the mileages to the ArrayList.
        for (int i = 0; i < n; i++) {
            mileages.add(scan.nextInt());

        }

        // Add all the locations to the ArrayList.
        for (int i = 0; i < n - 1; i++) {
            locations.add(scan.nextInt());
        }        

        scan.close();
    }


    private ArrayList<Integer> solve(ArrayList<Integer> mileages, ArrayList<Integer> locations, 
                                     HashMap<Integer, Boolean> isItAvailable) {

        ArrayList<Integer> solutions = new ArrayList<>();

        HashSet<Integer> locations2 = new HashSet<>();

        for (int k: locations) {
            locations2.add(k);
        }

        ArrayList<Integer> reversedMileages = mileages;
        Collections.reverse(reversedMileages);
        int s1 = 0;
        int s2 = 0;
        int nr1 = 0;
        int nr2 = 0;

        for (int i = 0; i < mileages.size(); i++) {
            s1 += mileages.get(i);

            if (locations.contains(s1)) {
                nr1++;
            }
        }

        for (int i = 0; i < reversedMileages.size(); i++) {
            s2 += reversedMileages.get(i);

            if (locations.contains(s2)) {
                nr2++;
            }
        }

        if (nr1 < nr2) {
            getPermutation(mileages, locations2, solutions, isItAvailable);
        } else if (nr2 < nr1) {
            getPermutation(reversedMileages, locations2, solutions, isItAvailable);
        } else {
            getPermutation(mileages, locations2, solutions, isItAvailable);
        }

        return solutions;
    }

    private void getPermutation(ArrayList<Integer> mileages, HashSet<Integer> locations2, 
                                ArrayList<Integer> solutions, HashMap<Integer, Boolean> isItAvailable) {
        int s = 0;

        if (solutions.size() == mileages.size()) {
            for (int i = 0; i < solutions.size(); i++) {
                s += mileages.get(solutions.get(i));
                if (locations2.contains(s)) {
                    break;
                }

                if (i == mileages.size() - 1) {
                    System.out.println(solutions);
                    return;
                }
            }
        }

        for (int i = 0; i < mileages.size(); i++) {
            if (isItAvailable.get(i)) {

                solutions.add(i);
                isItAvailable.put(i, false);

                getPermutation(mileages, locations2, solutions, isItAvailable);

                solutions.remove(Integer.valueOf(i));
                isItAvailable.put(i, true);
            }
        }
    }

    public static void main(String[] args) {
        new Trucker5().run();
    }

}