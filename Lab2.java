import java.util.Arrays;
import java.util.Random;

public class Lab2 {
    public static void printArr(int[] arr) {
        int len = arr.length;
        if (len > 21) {
            for (int i = 0; i < 10; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println("...");
            for (int i = len - 1; i > len - 11; i--) {
                System.out.print(arr[i] + " ");
            }
        } else {
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
        }
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("You should choose dev or prod environment");
            return;
        }
        if (args.length > 5) {
            System.out.println("You've entered more arguments than necessary");
            return;
        }

        if (args[0].equals("prod")) {
            if (args.length < 4) {
                System.out.println("You have forgotten to pass some arguments");
                return;
            }
            int[] arr = generateRandomArrayWithRestrictions(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
            System.out.println("Original array: ");
            printArr(arr);
            long after = System.currentTimeMillis();
            insertSorting(arr, args[3]);
            long before = System.currentTimeMillis();
            int diff = (int) (before - after);
            System.out.println();
            System.out.println("Sorting time: " + diff);
        } else if (args[0].equals("dev")) {
            testing();
        }
    }

    public static boolean isBetween(int lowerbound, int upperbound, int value) {
        if (value > lowerbound && value < upperbound) {
            return true;
        }
        return false;
    }

    public static int[] generateRandomArray(int bound) {
        Random random = new Random();
        int length = -1;
        while ((length <= 0)) {
            length = random.nextInt(bound);
        }
        int[] randarr = new int[length];
        for (int i = 0; i < length; i++) {
            randarr[i] = random.nextInt();
        }
        return randarr;
    }

    public static int[] generateRandomArrayWithRestrictions(int bound, int itemBound) {
        Random random = new Random();
        int length = -1;
        while ((length <= 0)) {
            length = random.nextInt(bound);
        }
        int lowerbound = -itemBound;
        int[] randarr = new int[length];
        for (int i = 0; i < length; i++) {
            int item = random.nextInt(itemBound);
            while (item < lowerbound) {
                item = random.nextInt(itemBound);
            }
            randarr[i] = item;
        }
        return randarr;
    }

    public static void insertSortDescending(int [] unsorted){
        insertSort(unsorted);
        reverse(unsorted);
    }
    public static void bubbleSortDescending(int [] unsorted){
        bubbleSortAscending(unsorted);
        reverse(unsorted);
    }
    public static void insertSort(int[] arr) {
        int tmp = 0;
        for (int i = 1; i < arr.length; i++) {
            int k = i;
            while (k > 0 && arr[k - 1] > arr[k]) {
                tmp = arr[k - 1];
                arr[k - 1] = arr[k];
                arr[k] = tmp;
                k--;
            }
        }
    }


    public static void testing() {
        long beforeTime = 0;
        long afterTime = 0;
        int[] results = new int[4];
        int[] unsorted = arrayFiller(100000, 140);
        int[] toBeSorted = new int[unsorted.length];
        System.arraycopy(unsorted, 0, toBeSorted, 0, unsorted.length);
        for (int i = 0; i < 4; i++) {
            if (i == 0) {
                beforeTime = System.currentTimeMillis();
                bubbleSortAscending(toBeSorted);
                afterTime = System.currentTimeMillis();
                if (isSortedAscending(toBeSorted)) {
                    System.out.println("Bubble sorting test: OK");
                } else {
                    System.out.println("Bubble sorting test: FAILED");
                }
                int diff = (int) (afterTime - beforeTime);
                System.out.println("Sorting time: " + diff);
                results[i] = diff;
            } else if (i == 1) {
                beforeTime = System.currentTimeMillis();
                insertSort(toBeSorted);
                afterTime = System.currentTimeMillis();
                if (isSortedAscending(toBeSorted)) {
                    System.out.println("Insert sorting test: OK");
                } else {
                    System.out.println("Insert sorting test: FAILED");
                }
                int diff = (int) (afterTime - beforeTime);
                System.out.println("Sorting time: " + diff);
                results[i] = diff;
            } else if (i == 2) {
                beforeTime = System.currentTimeMillis();
                Arrays.sort(toBeSorted);
                afterTime = System.currentTimeMillis();
                if (isSortedAscending(toBeSorted)) {
                    System.out.println("STL sorting test: OK");
                } else {
                    System.out.println("STL sorting test: FAILED");
                }
                int diff = (int) (afterTime - beforeTime);
                System.out.println("Sorting time: " + diff);
                results[i] = diff;
            } else {
                beforeTime = System.currentTimeMillis();
                Arrays.parallelSort(toBeSorted);
                afterTime = System.currentTimeMillis();
                if (isSortedAscending(toBeSorted)) {
                    System.out.println("Parallel STL sorting test: OK");
                } else {
                    System.out.println("Parallel STL sorting test: FAILED");
                }
                int diff = (int) (afterTime - beforeTime);
                System.out.println("Sorting time: " + diff);
                results[i] = diff;
            }


        }
        int[] sortedResults = new int[4];
        System.arraycopy(results, 0, sortedResults, 0, 4);
        bubbleSortAscending(sortedResults);
        String[] map = new String[]{"Bubble sorting", "Insert sorting", "STL sorting", "Parallel STL sorting"};
        int fastest = 0;
        int slowest = 0;
        int second = 0;
        int third = 0;
        for (int i = 0; i < 4; i++) {
            if (sortedResults[0] == results[i]) {
                fastest = i;
                break;
            }
        }
        for (int i = 0; i < 4; i++) {
            if (sortedResults[1] == results[i]) {
                second = i;
                break;
            }
        }
        for (int i = 0; i < 4; i++) {
            if (sortedResults[2] == results[i]) {
                third = i;
                break;
            }
        }
        for (int i = 0; i < 4; i++) {
            if (sortedResults[3] == results[i]) {
                slowest = i;
                break;
            }
        }

        System.out.println("The fastest sorting is " + map[fastest]);
        System.out.println("Third fastest sorting is " + map[third]);
        System.out.println("Second fastest sorting is " + map[second]);
        System.out.println("The slowest sorting is " + map[slowest]);
    }

    public static int[] arrayFiller(int length, int mod) {
        int[] arr = new int[length];
        Random rand = new Random();
        for (int i = 0; i < length; i++) {
            arr[i] = rand.nextInt(mod);
        }
        return arr;
    }

    public static void bubbleSortAscending(int[] arr) {
        int len = arr.length;
        int tmp;
        for (int i = 0; i < len; i++) {
            boolean sorted = true;
            for (int k = 0; k < len - 1; k++) {
                if (arr[k] > arr[k + 1]) {
                    tmp = arr[k + 1];
                    arr[k + 1] = arr[k];
                    arr[k] = tmp;
                    sorted = false;
                }

            }
            if (sorted) {
                return;
            }
        }
    }

    public static boolean isSortedAscending(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public static boolean isSortedDescending(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] < arr[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public static void reverse(int[] arr) {
        int tmp;
        int len = arr.length;
        for (int i = 0; i < len / 2; i++) {
            tmp = arr[len - i - 1];
            arr[len - i - 1] = arr[i];
            arr[i] = tmp;
        }
    }

    public static void insertSorting(int[] unsorted, String sortingType) {
        System.out.println();
        if ("desc".equals(sortingType)) {
            insertSortDescending(unsorted);
            System.out.println("Descend sorted: ");
            printArr(unsorted);
        } else if ("asc".equals(sortingType)) {
            insertSort(unsorted);
            System.out.println("Ascend sorted: ");
            printArr(unsorted);
        } else if ("both".equals(sortingType)) {
            insertSort(unsorted);
            System.out.println("Ascend sorted: ");
            printArr(unsorted);
            System.out.println();
            System.out.print("Descend sorted: ");
            reverse(unsorted);
            printArr(unsorted);
        }
    }


    public static int[] arrayCopy(int[] a) {
        int lenA = a.length;
        int[] copy = new int[lenA];
        for(int i=0; i< lenA;i++) {
            copy[i] = a[i];
        }
        return copy;
    }

}