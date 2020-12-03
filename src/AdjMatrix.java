import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AdjMatrix {
    private int V;   // 顶点
    private int E;   // 边
    private int[][] adj;

    public AdjMatrix(String filename) {
        File file = new File(filename);
        try(Scanner scanner = new Scanner(file)) {
            V = scanner.nextInt();
            adj = new int[V][V];     //根据顶点个数创建n * n的邻接矩阵
            E = scanner.nextInt();
            for (int i = 0; i < E; i++) {
                int a = scanner.nextInt();
                int b = scanner.nextInt();
                adj[a][b] = 1;
                adj[b][a] = 1;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
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
}
