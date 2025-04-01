public class Main {
    public static void main(String[] args) {
        // Tamaño de la matriz
        int filas = 64;
        int columnas = 64;
        char[][] matriz = new char[filas][columnas];

        // Llenar con mayúsculas (65-90), minúsculas (97-122) y números (48-57)
        int count = 0;
        int contador = 0;
        char c = 0;

        // Mayúsculas (A-Z)
        while (true) {
            for (c = 65; c <= 90 && count < filas * columnas; c++) {
                matriz[count / columnas][(count % columnas - contador + columnas) % columnas] = c;
                count++;
            }

            // Minúsculas (a-z)
            for (c = 97; c <= 122 && count < filas * columnas; c++) {
                matriz[count / columnas][(count % columnas - contador + columnas) % columnas] = c;
                count++;
            }

            // Números (0-9)
            for (c = 48; c <= 57 && count < filas * columnas; c++) {
                matriz[count / columnas][(count % columnas - contador + columnas) % columnas] = c;
                count++;
            }

            // Añadir manualmente la ñ y Ñ (no están en ASCII estándar)
            if (count < filas * columnas) {
                matriz[count / columnas][(count % columnas - contador + columnas) % columnas] = 'Ñ';
                count++;
            }
            if (count < filas * columnas) {
                matriz[count / columnas][(count % columnas - contador + columnas) % columnas] = 'ñ';
                count++;
            }

            contador++;
            if (contador == 64) {
                break;
            }
        }

        // Imprimir
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }
}