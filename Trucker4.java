import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;


class Trucker4 {
    // Runs the program.
    public void run() {
        // Initialize the number of mileages.
        int n = 0;

        // Initialize the main ArrayLists.
        ArrayList<Integer> mileages = new ArrayList<>();
        ArrayList<Integer> locations = new ArrayList<>();

        // Get input for the variables.
        setup(n, mileages, locations);

        /*  Initialize a HashMap which record the availability of the element at index i.
        *   All values evaluate to true at first, as all the values are available.
        */
        HashMap<Integer, Boolean> isItAvailable = new HashMap<>();

        for (int i = 0; i < mileages.size(); i++) {
            isItAvailable.put(i, true);
        }

        // Create a HashSet for "locations" to facilitate a less complex searching algorithm.
        HashSet<Integer> locationSet = new HashSet<>();

        for (int k: locations) {
            locationSet.add(k);
        }

        // Initialize an ArrayList which will store the list of solutions.
        ArrayList<Integer> solutions = new ArrayList<>();

        // Initialize the variable which will store the current location.
        int currentLocation = 0;

        ArrayList<Integer> finalArr = solve(mileages, locations, locationSet, 
                                   solutions, isItAvailable, currentLocation);

        // Print out the elements.
        for (int i = 0; i < finalArr.size(); i++) {
            if (i == finalArr.size() - 1) {
                System.out.print(finalArr.get(i));
            } else {
                System.out.print(finalArr.get(i) + " ");
            }
        }
        
        
    }

    // Get input for the program.
    private void setup(int n, ArrayList<Integer> mileages, ArrayList<Integer> locations) {
        Scanner scan = new Scanner(System.in);

        // Initialize the number of mileages.
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


    /* Solves the problem.

     * The program checks the availability of the input through a loop.
     * The element is added to the "solutions" ArrayList if it is available,
     * then it calls itself again.
     * 
     * If there is no possible element which can be added without matching an 
     * element in "locations", the method ends and goes back to the previous call,
     * removing the previously added element in the process.
     * 
     * If at any point "solutions" has the necessary number of elements, the method will
     * return this result.
     */
    private ArrayList<Integer> solve(ArrayList<Integer> mileages,
                                     ArrayList<Integer> locations, 
                                     HashSet<Integer> locationSet, 
                                     ArrayList<Integer> solutions, 
                                     HashMap<Integer, Boolean> isItAvailable, 
                                     int currentLocation) {

        // If the ArrayList has a complete list of solutions, return it.
        if (solutions.size() == mileages.size()) {
            return solutions;
        }
        
        // Initializes a for loop which will go through every ArrayList element.
        for (int i = 0; i < mileages.size(); i++) {
            int possibleLocation = currentLocation + mileages.get(i);

            // Checks if element at index i is available to use.
            if (isItAvailable.get(i) && !locationSet.contains(possibleLocation)) {

                /* If "locations" does not contain it,
                 * "currentLocation", "isItAvailable" and "solutions" are all updated.
                */
                currentLocation += mileages.get(i);
                solutions.add(i);
                isItAvailable.put(i, false);

                // The function calls itself recursively.
                ArrayList<Integer> result = solve(mileages, locations, locationSet, solutions, 
                                                  isItAvailable, currentLocation);

                // Return result if, after the call, the result has enough elements.
                if (result.size() == mileages.size()) {
                    return result;
                }
                
                // Update the variables if the element at index i is not valid.
                isItAvailable.put(i, true);
                solutions.remove(solutions.size() - 1);
                currentLocation -= mileages.get(i);
            }
        }

        /* Return nothing in case there is no solution.
         * This return method will not be reached when the input is optimal.
        */
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        new Trucker4().run();
    }

}