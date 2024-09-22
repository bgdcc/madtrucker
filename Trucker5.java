import java.util.ArrayList;
import java.util.HashMap;
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
        getPermutation(mileages, locations, solutions, isItAvailable);

        return solutions;
    }

    private void getPermutation(ArrayList<Integer> mileages, ArrayList<Integer> locations, 
                                ArrayList<Integer> solutions, HashMap<Integer, Boolean> isItAvailable) {
        int s = 0;
        if (solutions.size() == mileages.size()) {
            for (int i = 0; i < solutions.size(); i++) {
                s += mileages.get(solutions.get(i));
                if (locations.contains(s)) {
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

                getPermutation(mileages, locations, solutions, isItAvailable);

                solutions.remove(Integer.valueOf(i));
                isItAvailable.put(i, true);
            }
        }
    }

    public static void main(String[] args) {
        new Trucker5().run();
    }

}