import java.util.*;


/**
 * Created by Benjamin on 11/5/2014.
 * This class is a very simple code example built for Hannon Hill.
 *
 * Original prompt:
 * You are given a Secret which encapsulates a function that accepts a single integer parameter and returns an integer.
 * In Java or a language of your choice, write an application that determines if Secret is an additive function
 * secret(x+y) = secret(x) + secret(y)
 * for all prime numbers less than N where N is a given integer.
 *
 * This implementation uses the console for user input and output. It will accept an integer "N" from the user,
 * and displays a message indicating if secret(N) is additive or not for all prime values at or below N.
 *
 * In this code I assumed that x == y to simplify calculation.
 *
 * I chose Java because it is well documented and is an industry standard making it simple for an individual to modify
 * the function secret for testing.
 */
public class CodeTrial {
    //secret function
    public static int secret(int input){
        return input;
    }

    public static void main(String args[]){

        //User input
        Scanner input = new Scanner(System.in);
        System.out.print("Enter an Integer: ");

        int num;
        List<Integer> listOfPrimes;

        try {
            num = input.nextInt();
            listOfPrimes = runEratosthenesSieve(num);
            boolean isAdditive = true;

            for (int i = 0; i < listOfPrimes.size(); i++) {
                int primeValue = listOfPrimes.get(i);

                //additive check
                int left = secret(primeValue + primeValue);
                int right = secret(primeValue) + secret(primeValue);
                if(left != right){
                    isAdditive = false;
                    break;
                }
            }

            if(isAdditive){
                System.out.println("Secret is additive for all primes");
            } else {
                System.out.println("Secret is NOT additive for all primes");
            }

        } catch (InputMismatchException e) {
            String error = input.next();
            System.err.println(error + " is not an integer");
            main(new String[]{});
        }
    }

    //Convenient algorithm taken and adapted from the following location
    //http://www.algolist.net/Algorithms/Number_theoretic/Sieve_of_Eratosthenes
    public static List runEratosthenesSieve(int upperBound) {
        List<Integer> returnedListOfPrimes = new ArrayList<Integer>();

        int upperBoundSquareRoot = (int) Math.sqrt(upperBound);
        boolean[] isComposite = new boolean[upperBound + 1];

        for (int m = 2; m < upperBoundSquareRoot; m++) {
            if (!isComposite[m]) {
                returnedListOfPrimes.add(m);
                for (int k = m * m; k <= upperBound; k += m)
                    isComposite[k] = true;
            }
        }
        for (int m = upperBoundSquareRoot; m <= upperBound; m++)
            if (!isComposite[m])
                returnedListOfPrimes.add(m);

        return returnedListOfPrimes;
    }
}
