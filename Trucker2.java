import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


class Trucker2 {
    public void run() {
        // Initialize the number of mileages.
        int n = 0;

        ArrayList<Integer> mileages = new ArrayList<>();
        ArrayList<Integer> locations = new ArrayList<>();
        setup(n, mileages, locations);

        ArrayList<Boolean> isItAvailable = new ArrayList<>();

        for (int i = 0; i < mileages.size(); i++) {
            isItAvailable.add(true);

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
                                     ArrayList<Integer> solutions, ArrayList<Boolean> isItAvailable, 
                                     int currentLocation) {

        int maxValue = Collections.max(locations);

        

        if (!isItAvailable.contains(true) && currentLocation > maxValue) {
            return solutions;
        }

        // Comment: for input 5 2 4 89 11 3 6 91 13 5, output becomes 2 0 3 4
        // Perhaps the isItAvailable value does not reset to true and it can no 
        // longer be added to the list
        for (int i = 0; i < mileages.size(); i++) {
            if (isItAvailable.get(i)) {
                currentLocation += mileages.get(i);

                if (locations.contains(currentLocation)) {
                    currentLocation -= mileages.get(i);
                    continue;
                } else {
                    isItAvailable.set(i, false);
                    solutions.add(i);

                    solutions = solve(mileages, locations, solutions, 
                                      isItAvailable, currentLocation);
                }

                if (currentLocation > maxValue) {
                    break;
                }

                //solutions = solve(mileages, locations, solutions, isItAvailable, currentLocation);
                //return solve(mileages, locations, solutions, isItAvailable, currentLocation);

                if (currentLocation < maxValue) {
                    currentLocation -= mileages.get(i);
                    isItAvailable.set(i, true);
                    solutions.remove(Integer.valueOf(i));
                }
            }
        }

        return solutions;
    }

    public static void main(String[] args) {
        new Trucker2().run();
    }

}