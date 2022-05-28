public class Lec {
    public static void main(String[] args) {
            int[] p = {0,1,5,3,2};
        System.out.println(memorizedRoadCut(4, p));
    }
    public static int memorizedRoadCutAux(int[] r, int n, int[] p){
        if(r[n] >= 0){
            return r[n];
        }
        int q;
        if(n==0){
            q=0;
        }else{
            q = Integer.MIN_VALUE;
            for(int i = 1; i<=n;i++){
                q = Math.max(q,p[i] + memorizedRoadCutAux(r,n-i,p));
            }
            r[n] = q;

        }
        return q;
    }
    public static int memorizedRoadCut(int n, int[] p){
        int[] r = new int[n+1];

        for(int i = 0; i<=n; i++){
            r[i] = Integer.MIN_VALUE;
        }
        return memorizedRoadCutAux(r,n,p);
    }
}