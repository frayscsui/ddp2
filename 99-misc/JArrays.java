import java.util.Arrays;

public class JArrays {
    public static int[] appendArrays(int[] arr, int val) {
        int[] newArr = new int[arr.length+1];
        System.arraycopy(arr, 0, newArr, 0, arr.length);
        newArr[arr.length] = val;
        return newArr;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1};
        arr = appendArrays(arr, 2);
        System.out.println(Arrays.toString(arr));

        int[] myInts = new int[]{9,1,7,7};      // [9, 1, 7, 7]
        int[] copiedInts = new int[3];          // [0, 0, 0]
        System.arraycopy(myInts, 1, copiedInts, 1, 2);
        System.out.println(Arrays.toString(copiedInts));
    }
}
