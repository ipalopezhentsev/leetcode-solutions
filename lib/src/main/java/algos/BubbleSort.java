package algos;

public class BubbleSort {
    /**
     * sorts input array in place, in ascending order
     * @param src
     */
    public static void sort(int[] src) {
        for (int i = src.length-1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (src[j] > src[j+1]) {
                    var tmp = src[j];
                    src[j] = src[j+1];
                    src[j+1] = tmp;
                }
            }
        }
    }
}
