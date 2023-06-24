public class QuickFindUF {
    private final int[] arr;

    public QuickFindUF(int N){ // Constructor
        arr = new int[N];
        for(int i = 0; i < N; i++)
            arr[i] = i;
    }
    public boolean connected(int p, int q){
        return (arr[p] == arr[q]);
    }
    public void union(int p, int q) {
        int pid = arr[p];
        int qid = arr[q];
        for (int i = 0; i < arr.length; i++)
            if (arr[i] == pid)
                arr[i] = qid;
    }

    public static void main(String[] args) {
        QuickFindUF Q = new QuickFindUF(9);
        System.out.println(Q.connected(5,0));
        Q.union(5,0);
        System.out.println(Q.connected(5,0));
    }
}
