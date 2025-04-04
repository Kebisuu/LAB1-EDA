import java.util.Scanner;
class BigVigenere {
    private int[] llave;
    private char[][] matriz;

    public BigVigenere(String LlaveNumerica) {
        this.llave = Convertirllave(LlaveNumerica);
        GenerarMatriz();
    }

    private int[] Convertirllave(String LlaveNumerica) {
        int[] ALlave = new int[LlaveNumerica.length()];
        for (int i = 0; i < LlaveNumerica.length(); i++) {
            ALlave[i] = Character.getNumericValue(LlaveNumerica.charAt(i));
        }
        return ALlave;
    }

    private void GenerarMatriz() {
        int filas = 64;
        int columnas = 64;
        matriz = new char[filas][columnas];

        int count = 0;
        int contador = 0;
        char c;

        while (true) {
            for (c = 'A'; c <= 'Z' && count < filas * columnas; c++) {
                matriz[count / columnas][(count % columnas - contador + columnas) % columnas] = c;
                count++;
                if (c == 'N' && count < filas * columnas) {
                    matriz[count / columnas][(count % columnas - contador + columnas) % columnas] = 'Ñ';
                    count++;
                }
            }

            for (c = 'a'; c <= 'z' && count < filas * columnas; c++) {
                matriz[count / columnas][(count % columnas - contador + columnas) % columnas] = c;
                count++;
                if (c == 'n' && count < filas * columnas) {
                    matriz[count / columnas][(count % columnas - contador + columnas) % columnas] = 'ñ';
                    count++;
                }
            }

            for (c = '0'; c <= '9' && count < filas * columnas; c++) {
                matriz[count / columnas][(count % columnas - contador + columnas) % columnas] = c;
                count++;
            }

            contador++;
            if (contador == 64) {
                break;
            }
        }
    }

    public String Encriptar(String message) {
        StringBuilder Encriptar = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            char msgChar = message.charAt(i);
            int msgIndex = findCharIndex(msgChar);
            int keyIndex = llave[i % llave.length];

            if (msgIndex != -1) {
                Encriptar.append(matriz[msgIndex][keyIndex]);
            } else {
                Encriptar.append(msgChar);
            }
        }
        return Encriptar.toString();
    }

    public String Desencriptar(String encryptedMessage) {
        StringBuilder Desencriptar = new StringBuilder();
        for (int i = 0; i < encryptedMessage.length(); i++) {
            char encChar = encryptedMessage.charAt(i);
            int keyIndex = llave[i % llave.length];
            int row = keyIndex;
            int col = findColumnIndex(row, encChar);

            if (col != -1) {
                Desencriptar.append(matriz[0][col]);
            } else {
                Desencriptar.append(encChar);
            }
        }
        return Desencriptar.toString();
    }

    private int findCharIndex(char c) {
        for (int i = 0; i < matriz[0].length; i++) {
            if (matriz[0][i] == c) {
                return i;
            }
        }
        return -1;
    }

    private int findColumnIndex(int row, char c) {
        for (int i = 0; i < matriz[row].length; i++) {
            if (matriz[row][i] == c) {
                return i;
            }
        }
        return -1;
    }

    public char search(int position) {
        int row = position / 64;
        int col = position % 64;
        return matriz[row][col];
    }

    public char optimalSearch(int position) {
        return matriz[position / 64][position % 64];
    }

    public void reEncrypt() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.println("Ingrese el mensaje encriptado:");
        String encryptedMessage = scanner.nextLine();
        String decryptedMessage = Desencriptar(encryptedMessage);

        System.out.println("Ingrese la nueva clave numérica:");
        String newKey = scanner.nextLine();
        this.llave = Convertirllave(newKey);

        String reEncryptedMessage = Encriptar(decryptedMessage);
        System.out.println("Nuevo mensaje encriptado: " + reEncryptedMessage);
        scanner.close();
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(" 1. Encriptar mensaje ");
            System.out.println(" 2. Desencriptar mensaje ");
            System.out.println(" 3. Salir ");
            System.out.print(" Ingrese una opcion: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            if (opcion == 1) {
                System.out.print(" Texto que desea encriptar: ");
                String mensaje = scanner.nextLine();

                System.out.print(" Clave: ");
                String clave = scanner.nextLine();

                BigVigenere cipher = new BigVigenere(clave);
                String encriptado = cipher.Encriptar(mensaje);
                System.out.println("Cifrado: " + encriptado);


            } else if (opcion == 2) {

                System.out.print(" Texto que desea desencriptar: ");
                String encriptado = scanner.nextLine();

                System.out.print(" Clave: ");
                String clave = scanner.nextLine();

                BigVigenere cipher = new BigVigenere(clave);
                String desencriptar= cipher.Desencriptar(encriptado);
                System.out.println("Desencriptado: " + desencriptar);
            } else if (opcion == 3) {
                break;
            } else {
                System.out.println("Opción no válida. Intente de nuevo.");
            }
        }

    }
}

