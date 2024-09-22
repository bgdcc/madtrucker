import java.util.ArrayList;
import java.util.HashMap;
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

        ArrayList<Integer> solutions = new ArrayList<>();

        int currentLocation = 0;

        for (int s: solve(mileages, locations, solutions, isItAvailable, currentLocation)) {
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
                                     ArrayList<Integer> solutions, HashMap<Integer, Boolean> isItAvailable, 
                                     int currentLocation) {

        if (solutions.size() == mileages.size()) {
            return solutions;
        }
        
        // Initializes a for loop which will go through every ArrayList element.
        for (int i = 0; i < mileages.size(); i++) {

            // Checks if element at index i is available to use.
            if (isItAvailable.get(i)) {

                /* Checks if the locations ArrayList contains the possible new location.
                 * If so, the loop continues to the next iteration.
                 */

                System.out.println("Here");
                if (locations.contains(currentLocation + mileages.get(i))) {
                    continue;
                }

                /* If "locations" does not contain it,
                 * "currentLocation", "isItAvailable" and "solutions" are all updated.
                 * 
                 * 
                */
                currentLocation += mileages.get(i);
                solutions.add(i);
                isItAvailable.put(i, false);

                System.out.println("There");
                solutions = solve(mileages, locations, solutions, isItAvailable, currentLocation);
                if (solutions.size() == mileages.size()) {
                    break;
                }
                
                isItAvailable.put(i, true);
                solutions.remove(Integer.valueOf(i));
                currentLocation -= mileages.get(i);
            }
        }

        return solutions;
    }

    public static void main(String[] args) {
        new Trucker4().run();
    }

}