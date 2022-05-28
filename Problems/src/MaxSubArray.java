public class MaxSubArray {
    public static void main(String[] args) {
         int[] arr = {-3,1,0,-1,-2,3,2,-1};
        System.out.println(func2(arr, 0, 3));
    }
    public static int func2(int[] arr, int low, int high){
        if(low == high){
            return arr[low];
        }else {
            int mid = (low + high)/2;
            int maxRight = func2(arr,mid+1, high);
            int maxLeft = func2(arr,low,mid);
            int crossSum = func(arr,low,high);
            return Math.max(Math.max(maxLeft,maxRight), crossSum);
        }
    }
    public static int func(int[] arr, int low, int high){
        int sum = 0;
        int leftSum = -1111111;
        for(int i = arr.length /2; i>=low; i--){
            sum = sum + arr[i];
            if(sum  >  leftSum){
                leftSum = sum;
            }

        }
        int rightSum = -11111111 ;

        sum = 0;
        for(int j = arr.length/2 +1; j<= high; j++){
            sum = sum +arr[j];
            if(sum > rightSum){
                rightSum = sum;
            }
        }
        int res = rightSum+leftSum;
        return Math.max(res, Math.max(rightSum,leftSum));
    }
}
