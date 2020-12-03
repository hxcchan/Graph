import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * 邻接表
 * */
public class AdjList {
    private int V;   // 顶点
    private int E;   // 边
    private List<Integer>[] adj;

    public AdjList(String filename) {
        File file = new File(filename);
        try(Scanner scanner = new Scanner(file)) {
            V = scanner.nextInt();

            if (V < 0) {
                throw new IllegalArgumentException("V must not be negative.");
            }

            /**
             * 根据顶点个数创建n * n的邻接矩阵
             * */
            adj = new LinkedList[V];
            for (int i = 0; i < V; i++) {
                adj[i] = new LinkedList<>();
            }


            E = scanner.nextInt();

            if (E < 0) {
                throw new IllegalArgumentException("E must not be negative.");
            }

            for (int i = 0; i < E; i++) {
                int a = scanner.nextInt();
                validateVertex(a);
                int b = scanner.nextInt();
                validateVertex(b);

                // 自环边检测
                if (a == b) {
                    throw new IllegalArgumentException("Self Loop is detected.");
                }

                // 平行边检测 O(V), 导致总时间复杂度O(V * E)
                if (adj[a].contains(b)) {
                    throw new IllegalArgumentException("Parallel is detected.");
                }

                /**
                 * 此处针对无向图进行矩阵赋值，主对角线对称。
                 * O（E） -- Time Complexity
                 * */
                adj[a].add(b);
                adj[b].add(a);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查看节点是否符合条件。
     * */
    private void validateVertex(int v) {
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("vertex" + v + "is invalid.");
        }
    }

    public static void main(String[] args) {
        AdjList adjList = new AdjList("g.txt");
        System.out.println(adjList);
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("V = %d, E = %d\n", V, E));
        for (int v = 0; v < adj.length; v ++) {
            sb.append(String.format("%d: ", v));
            for (int w : adj[v]) {
                sb.append(String.format("%d ", w));
            }
            sb.append('\n');    //每一行打印结束之后换行
        }

        return sb.toString();
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public boolean hasEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        return adj[v].contains(w);
    }

    public List<Integer> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    public int degree(int v) {
        return adj(v).size();
    }
}
