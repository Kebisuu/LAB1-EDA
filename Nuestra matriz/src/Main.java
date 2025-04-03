public class Main {
    public static void main(String[] args) {
        // Tamaño de la matriz
        int filas = 64;
        int columnas = 64;
        char[][] matriz = new char[filas][columnas];

        // Llenar con caracteres
        int count = 0;
        int contador = 0;
        char c;

        // Mayúsculas (A-Z) incluyendo Ñ
        while (true) {
            for (c = 'A'; c <= 'Z' && count < filas * columnas; c++) {
                matriz[count / columnas][(count % columnas - contador + columnas) % columnas] = c;
                count++;
                // Añadir 'Ñ' después de 'N'
                if (c == 'N' && count < filas * columnas) {
                    matriz[count / columnas][(count % columnas - contador + columnas) % columnas] = 'Ñ';
                    count++;
                }
            }

            // Minúsculas (a-z) incluyendo ñ
            for (c = 'a'; c <= 'z' && count < filas * columnas; c++) {
                matriz[count / columnas][(count % columnas - contador + columnas) % columnas] = c;
                count++;
                // Añadir 'ñ' después de 'n'
                if (c == 'n' && count < filas * columnas) {
                    matriz[count / columnas][(count % columnas - contador + columnas) % columnas] = 'ñ';
                    count++;
                }
            }

            // Números (0-9)
            for (c = '0'; c <= '9' && count < filas * columnas; c++) {
                matriz[count / columnas][(count % columnas - contador + columnas) % columnas] = c;
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

