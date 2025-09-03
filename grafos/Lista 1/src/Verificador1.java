import java.util.*;

public class Verificador1 {
    private int[][] matriz;
    private int n;

    public Verificador1(int[][] matriz) {
        this.matriz = matriz;
        this.n = matriz.length;
    }

    public int isSimples() {
        for (int i = 0; i < n; i++) {
            if (matriz[i][i] != 0) return 0; // laço
            for (int j = i + 1; j < n; j++) {
                if (matriz[i][j] > 1) return 0; // múltiplas arestas
            }
        }
        return 1;
    }

    public int isCompleto() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && matriz[i][j] == 0) return 0;
            }
        }
        return 1;
    }

    private int getGrau(int v) {
        int grau = 0;
        for (int j = 0; j < n; j++) {
            if (matriz[v][j] > 0) grau++;
        }
        return grau;
    }

    public int isRegular() {
        int base = getGrau(0);
        for (int i = 1; i < n; i++) {
            if (getGrau(i) != base) return 0;
        }
        return 1;
    }

    public int isNulo() {
        for (int i = 0; i < n; i++) {
            if (getGrau(i) != 0) return 0;
        }
        return 1;
    }

    public int isLinear() {
        int countGrau1 = 0;
        for (int i=0; i<n; i++){
            for (int j=0; j<n; j++){
                if (matriz[i][j] > 1) return 0;
                if (matriz[i][j] == 1) countGrau1++;
            }
        }
        return (countGrau1 == 2) ? 1 : 0;   
    }

    public String imprimir() {
        return isSimples() + " " + isRegular() + " " + isCompleto() + " " + isNulo() + " " + isLinear();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            int n = sc.nextInt();
            if (n == 0) break; // fim da entrada
            sc.nextLine();

            int[][] matriz = new int[n][n];
            for (int i = 0; i < n; i++) {
                for( int j = n-1; j > i; j--) {
                    int valor = sc.nextInt();
                    if (valor > 0) {
                        valor = 1;
                        matriz[i][j] = valor;
                        matriz[j][i] = valor;
                    }
                }
            }

            Verificador1 g = new Verificador1(matriz);
            System.out.println(g.imprimir());
        }

        sc.close();
    }
}
