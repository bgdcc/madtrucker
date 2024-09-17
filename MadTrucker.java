import java.util.ArrayList;
import java.util.Scanner;

class MadTrucker {
    private void run() {
        // Initialize the number of mileages.
        int n = 0;

        ArrayList<Integer> mileages = new ArrayList<>();
        ArrayList<Integer> locations = new ArrayList<>();
        setup(n, mileages, locations);

        for (int s: solve(mileages, locations)) {
            System.out.print(s + " ");
        }
    }

    private void setup(int n, ArrayList<Integer> mileages, ArrayList<Integer> locations) {
        Scanner scan = new Scanner(System.in);

        n = scan.nextInt();

        for (int i = 0; i < n; i++) {
            mileages.add(scan.nextInt());

        }

        while (scan.hasNextLine()) {
            locations.add(scan.nextInt());
        }

        scan.close();
    }

    private ArrayList<Integer> solve(ArrayList<Integer> mileages, ArrayList<Integer> locations) {
        ArrayList<Integer> solutions = new ArrayList<>();
        ArrayList<Boolean> isItAvailable = new ArrayList<>();

        for (int i = 0; i < mileages.size(); i++) {
            isItAvailable.add(true);

        }
        int currentLocation = 0;

        if (passTheTests(mileages, locations, solutions, isItAvailable, currentLocation)) {
            return solutions;
        }

        return solutions;
    }

    //Redo of the canItPass function
    private boolean passTheTests(ArrayList<Integer> mileages, ArrayList<Integer> locations, 
                                 ArrayList<Integer> solutions, ArrayList<Boolean> isItAvailable, 
                                 int currentLocation) {

        if (locations.contains(currentLocation)) {
            return false;
        }

        for (int i = 0; i < mileages.size(); i++) {
            if (currentLocation < locations.get(locations.size() - 1)) {

                if (isItAvailable.get(i)) {
                    currentLocation += mileages.get(i);

                    isItAvailable.set(i, false);
                    solutions.add(i);

                    if (locations.contains(currentLocation)) {
                        isItAvailable.set(i, true);
                        solutions.remove(Integer.valueOf(i));

                        currentLocation -= mileages.get(i);
                        return false;
                    }
                    if (passTheTests(mileages, locations, solutions, 
                        isItAvailable, currentLocation)) {
                        return true;
                    }

                }
            }  
        }

        return true;
    }

    public static void main(String[] args) {
        new MadTrucker().run();
    }
}