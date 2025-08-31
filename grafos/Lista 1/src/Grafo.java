import java.util.*;

public class Grafo {
    private List<String> vertices;
    private Map<String, Integer> indiceVertice;
    private int[][] matrizAdj;

    public Grafo() {
        vertices = new ArrayList<>();
        indiceVertice = new HashMap<>();
        matrizAdj = new int[0][0];
    }

    public Grafo(int numeroVertices) {
        vertices = new ArrayList<>();
        indiceVertice = new HashMap<>();
        matrizAdj = new int[numeroVertices][numeroVertices];
    }


    public void adicionarVertice(String v) {
        if (!indiceVertice.containsKey(v)) {
            vertices.add(v);
            indiceVertice.put(v, vertices.size() - 1);


            int n = vertices.size();
            int[][] novaMatriz = new int[n][n];
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - 1; j++) {
                    novaMatriz[i][j] = matrizAdj[i][j];
                }
            }
            matrizAdj = novaMatriz;
        }
    }


    public void adicionarArestaSemPeso(String origem, String destino) {
        int i = indiceVertice.get(origem);
        int j = indiceVertice.get(destino);
        matrizAdj[i][j] = 1;
        matrizAdj[j][i] = 1; 
    }


    public void adicionarArestaComPeso(String origem, String destino, int peso) {
        int i = indiceVertice.get(origem);
        int j = indiceVertice.get(destino);
        matrizAdj[i][j] = peso;
        matrizAdj[j][i] = peso; 
    }


    public boolean isSimple() {
        for (int i = 0; i < vertices.size(); i++) {
            if (matrizAdj[i][i] != 0) return false; 
            for (int j = i + 1; j < vertices.size(); j++) {
                if (matrizAdj[i][j] > 1) return false; 
            }
        }
        return true;
    }


    public boolean isAdjacente(String v1, String v2) {
        int i = indiceVertice.get(v1);
        int j = indiceVertice.get(v2);
        return matrizAdj[i][j] != 0;
    }


    public int getGrau(String v) {
        int i = indiceVertice.get(v);
        int grau = 0;
        for (int j = 0; j < vertices.size(); j++) {
            if (matrizAdj[i][j] != 0) grau++;
        }
        return grau;
    }


    public boolean isIsolado(String v) {
        return getGrau(v) == 0;
    }


    public boolean isPendente(String v) {
        return getGrau(v) == 1;
    }


    public boolean isRegular() {
        if (vertices.isEmpty()) return true;
        int grauRef = getGrau(vertices.get(0));
        for (String v : vertices) {
            if (getGrau(v) != grauRef) return false;
        }
        return true;
    }


    public boolean isCompleto() {
        int n = vertices.size();
        for (int i = 0; i < n; i++) {
            if (matrizAdj[i][i] != 0) return false; 
            for (int j = 0; j < n; j++) {
                if (i != j && matrizAdj[i][j] == 0) return false;
            }
        }
        return true;
    }


    public boolean isNulo() {
        for (int i = 0; i < vertices.size(); i++) {
            for (int j = 0; j < vertices.size(); j++) {
                if (matrizAdj[i][j] != 0) return false;
            }
        }
        return true;
    }


    public boolean isConexo() {
        if (vertices.isEmpty()) return true;

        Set<Integer> visitados = new HashSet<>();
        Queue<Integer> fila = new LinkedList<>();
        fila.add(0);

        while (!fila.isEmpty()) {
            int atual = fila.poll();
            if (!visitados.contains(atual)) {
                visitados.add(atual);
                for (int j = 0; j < vertices.size(); j++) {
                    if (matrizAdj[atual][j] != 0 && !visitados.contains(j)) {
                        fila.add(j);
                    }
                }
            }
        }
        return visitados.size() == vertices.size();
    }


    public boolean isLinear() {
        if (!isConexo()) return false;

        int grau1 = 0, grau2 = 0;
        for (String v : vertices) {
            int grau = getGrau(v);
            if (grau > 2) return false;
            if (grau == 1) grau1++;
            else if (grau == 2) grau2++;
        }

        if (grau2 == vertices.size()) return true;

        if (grau1 == 2 && grau2 == vertices.size() - 2) return true;

        return false;
    }


    public boolean hasCiclo() {
        for (int i = 0; i < vertices.size(); i++) {
            if (matrizAdj[i][i] != 0) return true;
        }
        Set<Integer> visitados = new HashSet<>();
        return dfsCiclo(0, -1, visitados);
    }

    private boolean dfsCiclo(int atual, int pai, Set<Integer> visitados) {
        visitados.add(atual);
        for (int j = 0; j < vertices.size(); j++) {
            if (matrizAdj[atual][j] != 0) {
                if (!visitados.contains(j)) {
                    if (dfsCiclo(j, atual, visitados)) return true;
                } else if (j != pai) {
                    return true;
                }
            }
        }
        return false;
    }

    public void removeVertice(String v) {
        if (!indiceVertice.containsKey(v)) return;
        int idx = indiceVertice.get(v);
        vertices.remove(idx);
        indiceVertice.clear();
        for (int i = 0; i < vertices.size(); i++) {
            indiceVertice.put(vertices.get(i), i);
        }
        int n = vertices.size();
        int[][] novaMatriz = new int[n][n];
        int ni = 0, nj;
        for (int i = 0; i < matrizAdj.length; i++) {
            if (i == idx) continue;
            nj = 0;
            for (int j = 0; j < matrizAdj.length; j++) {
                if (j == idx) continue;
                novaMatriz[ni][nj] = matrizAdj[i][j];
                nj++;
            }
            ni++;
        }
        matrizAdj = novaMatriz;
    }

    public void imprimirGrafo() {
        System.out.print("   ");
        for (String v : vertices) {
            System.out.print(v + " ");
        }
        System.out.println();
        for (int i = 0; i < vertices.size(); i++) {
            System.out.print(vertices.get(i) + " ");
            for (int j = 0; j < vertices.size(); j++) {
                System.out.print(" " + matrizAdj[i][j]);
            }
            System.out.println();
        }
    }
}
