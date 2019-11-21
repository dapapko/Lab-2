public class MathTask {
public static void main(String[] args){
    if(args.length == 0) {
        System.out.println("You've forgotten to pass some arguments");
        return;
    } else if (args.length >3) {
        System.out.println("You've passed too many arguments");
        return;
    }
    if(args[0].equals("combinations")) {
        if (args.length <3) {
            System.out.println("You've forgoten some arguments");
            return;
        }
        int combin = combinations(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
        System.out.println("Combinations from" + args[1] + " from" + args[2] +":" +combin );
    } else if(args[0].equals("permutations")) {
        if (args.length <2) {
            System.out.println("You've forgoten some arguments");
            return;
        }
        int permut = permutations(Integer.parseInt(args[1]));
        System.out.println("Permutations of " + args[1]  +":" + permut );
    } else if (args[0].equals("binom")) {
        System.out.println("Decomposition coefficients of " + args[1] + ":");
        newtonsBinom(Integer.parseInt(args[1]));
    } else {
        System.out.println("Invalid first argument");
    }
}
    public static int numfactorial(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        return numfactorial(n - 1) * n;
    }

    public static int permutations(int n) {
        if (n < 0) {
            System.out.println("N must be positive");
            return -1;
        }
        return numfactorial(n);
    }

    public static int combinations(int m, int n) {
        if (m < 0 || n < 0 || n < m) {
            System.out.println("Validation failure");
            return -1;
        }
        return numfactorial(n) / (numfactorial(n - m) * numfactorial(m));
    }

    public static void newtonsBinom(int n) {
        if (n < 2) {
            System.out.println("N must be more than 2");
            return;
        }
        for (int k = 0; k < n; k++) {
            System.out.print(combinations(k, n) + " ");
        }
    }
}
