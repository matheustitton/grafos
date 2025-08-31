import java.util.*;

public class Verificador1 {

    // ---------------- Verificador 1 ----------------
        private int[][] matriz;
        private int n;

        public Verificador1(int[][] matriz) {
            this.matriz = matriz;
            this.n = matriz.length;
        }

        public boolean isSimples() {
            for (int i = 0; i < n; i++) {
                if (matriz[i][i] != 0) return false;
                for (int j = i + 1; j < n; j++) {
                    if (matriz[i][j] > 1) return false;
                }
            }
            return true;
        }

        public boolean isRegular() {
            int grau = grau(0);
            for (int i = 1; i < n; i++) {
                if (grau(i) != grau) return false;
            }
            return true;
        }

        public boolean isCompleto() {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i != j && matriz[i][j] == 0) return false;
                }
            }
            return true;
        }

        public boolean isNulo() {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (matriz[i][j] != 0) return false;
                }
            }
            return true;
        }

        public boolean isLinear() {
            int grau1 = 0;
            int grau2 = 0;
            for (int i = 0; i < n; i++) {
                int g = grau(i);
                if (g > 2) return false;
                if (g == 1) grau1++;
                if (g == 2) grau2++;
            }
            if ((grau1 == 2 && grau2 == n - 2) || (grau1 == 0 && grau2 == n)) return true;
            return false;
        }

        private int grau(int v) {
            int soma = 0;
            for (int j = 0; j < n; j++) 
                if (matriz[v][j] == 0) soma ++;
            return soma;
        }

        public void verificar() {
            System.out.print((isSimples() ? "1 " : "0 "));
            System.out.print((isRegular() ? "1 " : "0 "));
            System.out.print((isCompleto() ? "1 " : "0 "));
            System.out.print((isNulo() ? "1 " : "0 "));
            System.out.print((isLinear() ? "1" : "0"));
            System.out.println();
        }
    
    // ---------------- Main ----------------
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
        Verificador1 v1 = new Verificador1(matriz1);
        v1.verificar();
        Verificador1 v2 = new Verificador1(matriz2);
        v2.verificar();
        sc.close();
    }
}
