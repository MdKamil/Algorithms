package algorithm.array;

public class PeakIndexMountainArray {
    public int peakIndexInMountainArray(int[] arr) {
        int peakIdx = 1;
        int low = 0;
        int high = arr.length - 1;
        while(low < high){
            int mid = (low + high)/2;
            if(arr[mid]> arr[mid+1] && arr[mid]>arr[mid-1]){
                peakIdx = mid;
                break;
            }else if(arr[mid+1]>arr[mid] && arr[mid]>arr[mid-1]){
                low = mid;
            }else if(arr[mid-1]>arr[mid] && arr[mid]>arr[mid+1]){
                high = mid;
            }
        }
        return peakIdx;
    }

    public static void main(String[] args) {
        int[] arr = {1,20,19,18,17,16,15,14,13,12,11,10,9,8,7,6,5,4,3,2,1,0};
        int peakIdx = new PeakIndexMountainArray().peakIndexInMountainArray(arr);
        System.out.println(peakIdx);
    }
}
