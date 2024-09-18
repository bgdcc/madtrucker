import java.util.ArrayList;
import java.util.Scanner;

class NewMadTrucker {
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

        while (scan.hasNextInt()) {
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

        /*if (locations.contains(currentLocation)) {
            return false;
        }*/

        int max = 0;
        for (int z = 0; z < locations.size(); z++) {
            if (max < locations.get(z)) {
                max = locations.get(z);
            }
        }

        if (currentLocation > max && !isItAvailable.contains(true)) {
            return true;
        }

        for (int i = 0; i < mileages.size(); i++) {

            if (isItAvailable.get(i)) {
                currentLocation += mileages.get(i);

                if (locations.contains(currentLocation)) {
                    currentLocation -= mileages.get(i);

                    continue;
                } else {
                    isItAvailable.set(i, false);
                    solutions.add(i);
                }

                if (passTheTests(mileages, locations, solutions, 
                    isItAvailable, currentLocation)) {
                    return true;
                }
                isItAvailable.set(i, true);

                solutions.remove(Integer.valueOf(i));
                currentLocation -= mileages.get(i);

            }
        }  

        return false;
    }

    public static void main(String[] args) {
        new NewMadTrucker().run();
    }
}