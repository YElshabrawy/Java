public class BinarySearch {
    public static int binarySearch(int[] arr, int key) {
        int lo = 0;
        int hi = arr.length - 1;

        while (lo <= hi){
            int mid = lo + (hi - lo) / 2;
            if (key < arr[mid]) hi = mid - 1;
            else if (key > arr[mid]) lo = mid + 1;
            else return mid;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,5,7,8,9,12};
        System.out.println(binarySearch(arr,5));
        System.out.println(binarySearch(arr, 13));
    }
}
