import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;


class Trucker4 {
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
        HashSet<Integer> locations2 = new HashSet<>();

        for (int k: locations) {
            locations2.add(k);
        }

        ArrayList<Integer> solutions = new ArrayList<>();

        int currentLocation = 0;

        

        for (int s: solve(mileages, locations2, solutions, isItAvailable, currentLocation)) {
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


    private ArrayList<Integer> solve(ArrayList<Integer> mileages, HashSet<Integer> locations2, 
                                     ArrayList<Integer> solutions, HashMap<Integer, Boolean> isItAvailable, 
                                     int currentLocation) {

        if (solutions.size() == mileages.size() && currentLocation > Collections.max(locations2)) {
            return solutions;
        }
        
        // Initializes a for loop which will go through every ArrayList element.
        for (int i = 0; i < mileages.size(); i++) {
            int possibleLocation = currentLocation + mileages.get(i);

            // Checks if element at index i is available to use.
            if (isItAvailable.get(i) && !locations2.contains(possibleLocation)) {

                /* If "locations" does not contain it,
                 * "currentLocation", "isItAvailable" and "solutions" are all updated.
                 * 
                 * 
                */
                currentLocation += mileages.get(i);
                solutions.add(i);
                isItAvailable.put(i, false);

                // The function calls itself recursively.
                ArrayList<Integer> result = solve(mileages, locations2, solutions, 
                                                  isItAvailable, currentLocation);

                if (result.size() == mileages.size() && currentLocation > Collections.max(locations2)) {
                    return result;
                }
                
                isItAvailable.put(i, true);
                solutions.remove(solutions.size() - 1);
                currentLocation -= mileages.get(i);
            }
        }

        return new ArrayList<>();
    }

    public static void main(String[] args) {
        new Trucker4().run();
    }

}
