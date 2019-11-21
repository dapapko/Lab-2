import java.util.Random;

public class SortingArrays {
    public static void main(String[] args) {
        if (args.length !=1) {
            System.out.println("Invalid number of arguments");
            return;
        }
        System.out.println("Sorting random arrays:");
        maxRandomArrIfExists(Integer.parseInt(args[0]));
        System.out.println();
        System.out.println("Sorting random arrays by sum:");
        maxRandomArrBySum(Integer.parseInt(args[0]));
    }
    public static boolean moreBySum(int[] a, int[] b) {
        int lenA = a.length;
        int lenB = b.length;
        if (lenA > lenB) {
            return true;
        } else if (lenA < lenB) {
            return false;
        }
        int sumA = sumOfElementsInArray(a);
        int sumB = sumOfElementsInArray(b);
        if (sumA < sumB) {
            return false;
        }
        return true;
    }
    public static int sumOfElementsInArray(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum = sum + arr[i];
        }
        return sum;
    }

    public static boolean moreIfExists(int []a, int[]b) {
        int lenA = a.length;
        int lenB = b.length;
        if (lenA > lenB) {
            return true;
        } else if (lenA < lenB) {
            return false;
        }
        insertSort(a);
        insertSort(b);
        if (a[lenA-1] > b[lenA-1]) {
            return true;
        } else {
            return false;
        }
    }
    public static void maxRandomArrIfExists(int count){
        Random rand = new Random();
        int[] firstmax = new int[0];
        int[] max = firstmax;
        for (int i=0; i<count-1;i++) {
            int length = rand.nextInt(100)+1;
            int[] randarr = generateRandomArray(length);
            if (moreIfExists(randarr, max)) {
                max = randarr;
            }
        }
        System.out.println("Max array is ");
        printArr(max);
    }

    public static void maxRandomArrBySum(int count){
        Random rand = new Random();
        int[] firstmax = new int[0];
        int[] max = firstmax;
        for (int i=0; i<count-1;i++) {
            int length = rand.nextInt(100)+1;
            int[] randarr = generateRandomArray(length);
            if (moreBySum(randarr, max)) {
                max = randarr;
            }
        }
        System.out.println("Max array is ");
        printArr(max);
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


    public static int[] generateRandomArray(int len) {
        Random random = new Random();
        int[] randarr = new int[len];
        for (int i = 0; i < len; i++) {
            randarr[i] = random.nextInt();
        }
        return randarr;
    }

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

    public static boolean checkTakingOrderIntoAccount(int[] a, int[] b) {
        int lenA = a.length;
        int lenB = b.length;
        if (lenA != lenB) {
            return false;
        }
        for (int k = 0; k < lenA; k++) {
            if (a[k] != b[k]) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkWithoutTakingOrderIntoAccount(int[] a, int[] b) {
        int lenA = a.length;
        int lenB = b.length;
        if (lenA != lenB) {
            return false;
        }
        insertSort(a);
        insertSort(b);
        for (int k = 0; k < lenA; k++) {
            if (a[k] != b[k]) {
                return false;
            }
        }
        return true;
    }
}
