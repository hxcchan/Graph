import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdjMatrix {
    private int V;   // 顶点
    private int E;   // 边
    private int[][] adj;

    public AdjMatrix(String filename) {
        File file = new File(filename);
        try(Scanner scanner = new Scanner(file)) {
            V = scanner.nextInt();

            if (V < 0) {
                throw new IllegalArgumentException("V must not be negative.");
            }

            /**
             * 根据顶点个数创建n * n的邻接矩阵
             * */
            adj = new int[V][V];

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

                // 平行边检测
                if (adj[a][b] == 1) {
                    throw new IllegalArgumentException("Parallel is detected.");
                }

                adj[a][b] = 1;
                adj[b][a] = 1;
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
        AdjMatrix adjMatrix = new AdjMatrix("g.txt");
        System.out.println(adjMatrix);
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("V = %d, E = %d\n", V, E));
        for (int i = 0;i < V; i ++) {
            for (int j = 0; j < V; j++) {
                sb.append(String.format("%d ", adj[i][j]));
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
        return adj[v][w] == 1;
    }

    public List<Integer> adj(int v) {
        validateVertex(v);
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            if (adj[v][i] == 1) {
                res.add(i);
            }
        }
        return res;
    }

    public int degree(int v) {
        return adj(v).size();
    }
}
