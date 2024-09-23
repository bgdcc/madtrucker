import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;


class OGTrucker {
    private boolean isItFound = false;

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
        int currentLocation = 0;

        HashSet<Integer> locations2 = new HashSet<>();


        for (int z = 0; z < locations.size(); z++) {
            locations2.add(locations.get(z));
        }

        ArrayList<Integer> reversedMileages = mileages;
        Collections.reverse(reversedMileages);
        System.out.println(reversedMileages);

        if (checkMistakes(mileages, locations2) < checkMistakes(reversedMileages, locations2)) {
            System.out.println("This!");
            getPermutation(mileages, locations2, solutions, isItAvailable, currentLocation);
        } else {
            System.out.println("That");
            getPermutation(reversedMileages, locations2, solutions, isItAvailable, currentLocation);
            Collections.reverse(solutions);
        }

        return solutions;
    }

    private void getPermutation(ArrayList<Integer> mileages, HashSet<Integer> locations2, 
                                ArrayList<Integer> solutions, HashMap<Integer, Boolean> isItAvailable,
                                int currentLocation) {
        int s = 0;
        int nr = 0;

        if (solutions.size() == mileages.size() && currentLocation > Collections.max(locations2)) {
            for (int i = 0; i < solutions.size(); i++) {
                s += mileages.get(solutions.get(i));
                if (!locations2.contains(s)) {
                    nr++;
                }
            }

            if (nr == solutions.size()) {
                isItFound = true;
            }
        }

        if (isItFound) {
            return;
        }

        for (int i = 0; i < mileages.size(); i++) {
            if (isItAvailable.get(i) && !locations2.contains(currentLocation + mileages.get(i))) {

                currentLocation += mileages.get(i);
                solutions.add(i);
                isItAvailable.put(i, false);

                getPermutation(mileages, locations2, solutions, isItAvailable, currentLocation);
                if (isItFound) {
                    return;
                }

                currentLocation -= mileages.get(i);
                solutions.remove(Integer.valueOf(i));
                isItAvailable.put(i, true);
            }
        }
    }

    private int checkMistakes (ArrayList<Integer> mileages, HashSet<Integer> locations2) {
        int s = 0;
        int nr = 0;

        for (int i = 0; i < mileages.size(); i++) {
            s += mileages.get(i);
            if (locations2.contains(s)) {
                nr++;
            }
        }

        return nr;
    }

    public static void main(String[] args) {
        new OGTrucker().run();
    }

}