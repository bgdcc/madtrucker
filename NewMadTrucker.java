import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


class NewMadTrucker {
    public void run() {
        // Initialize the number of mileages.
        int n = 0;

        ArrayList<Integer> mileages = new ArrayList<>();
        ArrayList<Integer> locations = new ArrayList<>();
        setup(n, mileages, locations);

        for (int s: solve(mileages, locations)) {
            System.out.print(s + " ");
        }
    }

    private void correctness(ArrayList<Integer> solutions, ArrayList<Integer> locations, ArrayList<Integer> mileages) {
        int s = 0;

        for (int i: solutions) {
            s += mileages.get(i);
            if (locations.contains(s)) {
                System.out.println("Hey there pal");
            }
        }
    }

    private void setup(int n, ArrayList<Integer> mileages, ArrayList<Integer> locations) {
        Scanner scan = new Scanner(System.in);

        n = scan.nextInt();

        for (int i = 0; i < n; i++) {
            mileages.add(scan.nextInt());

        }

        for (int i = 0; i < n - 1; i++) {
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
        ArrayList<Integer> fallBack = new ArrayList<>();
        fallBack.add(63);
        fallBack.add(9028);

        if (passTheTests(mileages, locations, solutions, isItAvailable, currentLocation)) {
            correctness(solutions, locations, mileages);

            return solutions;
        }

        return fallBack;
    }

    //Redo of the canItPass function
    private boolean passTheTests(ArrayList<Integer> mileages, ArrayList<Integer> locations, 
                                 ArrayList<Integer> solutions, ArrayList<Boolean> isItAvailable, 
                                 int currentLocation) {
        if (locations.contains(currentLocation)) {
            return false;
        }
        // Find the biggest value in the Locations arraylist.
        int maxValue = Collections.max(locations);

        //if (currentLocation > maxValue && !isItAvailable.contains(true)) {
          //  return true;
        //}

        if (currentLocation > maxValue) {
            if (isItAvailable.contains(true)) {
                for (int k = 0; k < mileages.size(); k++) {
                    if (isItAvailable.get(k)) {
                        currentLocation += mileages.get(k);
                        isItAvailable.set(k, false);
                    }
                }
            }
            return true;
        }

        for (int i = 0; i < mileages.size(); i++) {

            if (isItAvailable.get(i)) {
                currentLocation += mileages.get(i);

                if (locations.contains(currentLocation)) {
                    currentLocation -= mileages.get(i);

                    if (i == mileages.size() - 1) {
                        return false;
                    } else {
                        continue;
                    }
                }
                isItAvailable.set(i, false);
                solutions.add(i);

                if (passTheTests(mileages, locations, solutions, 
                                 isItAvailable, currentLocation)) {
                    
                    System.out.println("There");

                    if (currentLocation > maxValue) {
                        if (isItAvailable.contains(true)) {
                            for (int k = 0; k < mileages.size(); k++) {
                                if (isItAvailable.get(k)) {
                                    currentLocation += mileages.get(k);
                                    isItAvailable.set(k, false);
                                }
                            }
                        }
                    }
                    
                    return true;
                } else {
                    System.out.println("Here");
                    currentLocation -= mileages.get(i);
                    isItAvailable.set(i, true);
                    solutions.remove(Integer.valueOf(i));

                    continue;

                }

                /*
                if (locations.contains(currentLocation)) {
                    currentLocation -= mileages.get(i);
                    System.out.println("Here");

                    continue;
                } else {
                    isItAvailable.set(i, false);
                    solutions.add(i);

                    if (passTheTests(mileages, locations, solutions, 
                                     isItAvailable, currentLocation)) {
                        System.out.println("There");
                        return true;
                    }

                */
                

                //isItAvailable.set(i, true);
                //solutions.remove(Integer.valueOf(i));
                //currentLocation -= mileages.get(i);

            }
        }  

        return true;
    }

    public static void main(String[] args) {
        new NewMadTrucker().run();
    }
}