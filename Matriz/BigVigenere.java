public class BigVigenere {
    private int[] key; // Clave numérica
    private char[][] alphabet; // Matriz de cifrado

    // Constructor que recibe la clave como String
    public BigVigenere(String numericKey) {
        this.key = convertKey(numericKey);
        generateAlphabet();
    }

    // Método para convertir la clave String en un arreglo de enteros
    private int[] convertKey(String numericKey) {
        int[] keyArray = new int[numericKey.length()];
        for (int i = 0; i < numericKey.length(); i++) {
            keyArray[i] = Character.getNumericValue(numericKey.charAt(i));
        }
        return keyArray;
    }

    // Método para generar la matriz del alfabeto
    private void generateAlphabet() {
        int filas = 64;
        int columnas = 64;
        alphabet = new char[filas][columnas];

        int count = 0;
        int contador = 0;
        char c;

        while (true) {
            for (c = 'A'; c <= 'Z' && count < filas * columnas; c++) {
                alphabet[count / columnas][(count % columnas - contador + columnas) % columnas] = c;
                count++;
                if (c == 'N' && count < filas * columnas) {
                    alphabet[count / columnas][(count % columnas - contador + columnas) % columnas] = 'Ñ';
                    count++;
                }
            }

            for (c = 'a'; c <= 'z' && count < filas * columnas; c++) {
                alphabet[count / columnas][(count % columnas - contador + columnas) % columnas] = c;
                count++;
                if (c == 'n' && count < filas * columnas) {
                    alphabet[count / columnas][(count % columnas - contador + columnas) % columnas] = 'ñ';
                    count++;
                }
            }

            for (c = '0'; c <= '9' && count < filas * columnas; c++) {
                alphabet[count / columnas][(count % columnas - contador + columnas) % columnas] = c;
                count++;
            }

            contador++;
            if (contador == 64) {
                break;
            }
        }
    }

    // Método para cifrar un mensaje con la clave numérica
    public String encrypt(String message) {
        StringBuilder encrypted = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            char msgChar = message.charAt(i);
            int msgIndex = findCharIndex(msgChar);
            int keyIndex = key[i % key.length];

            if (msgIndex != -1) {
                encrypted.append(alphabet[msgIndex][keyIndex]);
            } else {
                encrypted.append(msgChar);
            }
        }
        return encrypted.toString();
    }

    // Método para descifrar un mensaje
    public String decrypt(String encryptedMessage) {
        StringBuilder decrypted = new StringBuilder();
        for (int i = 0; i < encryptedMessage.length(); i++) {
            char encChar = encryptedMessage.charAt(i);
            int keyIndex = key[i % key.length];
            int row = keyIndex;
            int col = findColumnIndex(row, encChar);

            if (col != -1) {
                decrypted.append(alphabet[0][col]);
            } else {
                decrypted.append(encChar);
            }
        }
        return decrypted.toString();
    }

    // Método para buscar la posición de un carácter en la primera fila de la matriz
    private int findCharIndex(char c) {
        for (int i = 0; i < alphabet[0].length; i++) {
            if (alphabet[0][i] == c) {
                return i;
            }
        }
        return -1; // No encontrado
    }

    // Método para encontrar la columna donde está el carácter en la fila de la clave
    private int findColumnIndex(int row, char c) {
        for (int i = 0; i < alphabet[row].length; i++) {
            if (alphabet[row][i] == c) {
                return i;
            }
        }
        return -1; // No encontrado
    }

    // Método search básico
    public char search(int position) {
        int row = position / 64;
        int col = position % 64;
        return alphabet[row][col];
    }

    // Método de búsqueda optimizado usando acceso directo
    public char optimalSearch(int position) {
        return alphabet[position / 64][position % 64];
    }

    // Método para cambiar la clave y re-cifrar un mensaje
    public void reEncrypt() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.println("Ingrese el mensaje encriptado:");
        String encryptedMessage = scanner.nextLine();
        String decryptedMessage = decrypt(encryptedMessage);

        System.out.println("Ingrese la nueva clave numérica:");
        String newKey = scanner.nextLine();
        this.key = convertKey(newKey);

        String reEncryptedMessage = encrypt(decryptedMessage);
        System.out.println("Nuevo mensaje encriptado: " + reEncryptedMessage);
        scanner.close();
    }
}