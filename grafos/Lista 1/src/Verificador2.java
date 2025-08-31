import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Verificador2 {
    // ---------------- Verificador 2 ----------------
        private int[][] matriz;
        private int n;

        public Verificador2(int[][] matriz) {
            this.matriz = matriz;
            this.n = matriz.length;
        }

        public boolean isConexo() {
            boolean[] visitado = new boolean[n];
            int componentes = 0;
            for (int i = 0; i < n; i++) {
                if (!visitado[i]) {
                    dfs(i, visitado);
                    componentes++;
                }
            }
            return componentes == 1;
        }

        private void dfs(int v, boolean[] visitado) {
            visitado[v] = true;
            for (int i = 0; i < n; i++) {
                if (matriz[v][i] != 0 && !visitado[i]) dfs(i, visitado);
            }
        }

        public boolean isBipartido() {
            int[] cor = new int[n];
            Arrays.fill(cor, -1);
            for (int i = 0; i < n; i++) {
                if (cor[i] == -1) {
                    if (!bfsColorir(i, cor)) return false;
                }
            }
            return true;
        }

        private boolean bfsColorir(int s, int[] cor) {
            Queue<Integer> q = new LinkedList<>();
            cor[s] = 0;
            q.add(s);
            while (!q.isEmpty()) {
                int u = q.poll();
                for (int v = 0; v < n; v++) {
                    if (matriz[u][v] != 0) {
                        if (cor[v] == -1) {
                            cor[v] = 1 - cor[u];
                            q.add(v);
                        } else if (cor[v] == cor[u]) return false;
                    }
                }
            }
            return true;
        }

        public boolean temCiclo() {
            boolean[] visitado = new boolean[n];
            for (int i = 0; i < n; i++) {
                if (!visitado[i]) {
                    if (dfsCiclo(i, visitado, -1)) return true;
                }
            }
            return false;
        }

        private boolean dfsCiclo(int v, boolean[] visitado, int pai) {
            visitado[v] = true;
            for (int u = 0; u < n; u++) {
                if (matriz[v][u] != 0) {
                    if (!visitado[u]) {
                        if (dfsCiclo(u, visitado, v)) return true;
                    } else if (u != pai) {
                        return true;
                    }
                }
            }
            return false;
        }

        public boolean isBipartidoCompleto() {
            int[] cor = new int[n];
            Arrays.fill(cor, -1);
            for (int i = 0; i < n; i++) {
                if (cor[i] == -1) {
                    if (!bfsColorir(i, cor)) return false;
                }
            }
            List<Integer> partA = new ArrayList<>();
            List<Integer> partB = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if (cor[i] == 0) partA.add(i); else partB.add(i);
            }
            for (int a : partA) {
                for (int b : partB) {
                    if (matriz[a][b] == 0) return false;
                }
            }
            return true;
        }

        public void verificar() {
            System.out.print((isConexo() ? "1 " : "0 "));
            System.out.print((isBipartido() ? "1 " : "0 "));
            System.out.print((temCiclo() ? "1 " : "0 "));
            System.out.print((isBipartidoCompleto() ? "1" : "0"));
            System.out.println();
        }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // ---------------- Grafo 1 ----------------
        int n1 = sc.nextInt();
        sc.nextLine();
        int[][] matriz1 = new int[n1][n1];
        for (int i = 0; i < n1; i++) {
            String[] linha = sc.nextLine().trim().split(" ");
            for (String s : linha) {
                int viz = Integer.parseInt(s);
                if (viz == -1) break;
                if (viz < 1 || viz > n1) continue;
                matriz1[i][viz - 1] = 1;
            }
        }
        Verificador1 v1 = new Verificador1(matriz1);
        v1.verificar();

        // ---------------- Grafo 2 ----------------
        int n2 = sc.nextInt();
        sc.nextLine();
        int[][] matriz2 = new int[n2][n2];
        for (int i = 0; i < n2; i++) {
            String[] linha = sc.nextLine().trim().split(" ");
            for (String s : linha) {
                int viz = Integer.parseInt(s);
                if (viz == -1) break;
                if (viz < 1 || viz > n2) continue;
                matriz2[i][viz - 1] = 1;
            }
        }
        Verificador1 v2 = new Verificador1(matriz2);
        v2.verificar();
        sc.close();
    }
}
