import java.io.*;
import java.nio.file.*;
import java.util.*;

public class CifradoV2 {
    private static final String ABC = "abcdefghijklmnñopqrstuvwxyzABCDEFGHIJKLMNÑOPQRSTUVWXYZ0123456789 ";

    public static String cifrar(String cadena, String clave) {
        StringBuilder cadenaCifrada = new StringBuilder();
        for (int i = 0; i < cadena.length(); i++) {        // O(n)
            char letra = cadena.charAt(i);
            char claveChar = clave.charAt(i % clave.length());
            int posLetra = ABC.indexOf(letra);
            int posClave = ABC.indexOf(claveChar);
            if (posLetra == -1 || posClave == -1) continue;
            int modulo = (posLetra + posClave) % ABC.length();
            cadenaCifrada.append(ABC.charAt(modulo));
        }
        System.out.println("Texto cifrado: " + cadenaCifrada.toString());
        return cadenaCifrada.toString();
    }

    public static String descifrar(String cadena, String clave) {
        StringBuilder cadenaDescifrada = new StringBuilder();
        for (int i = 0; i < cadena.length(); i++) {         // O(n)
            char letra = cadena.charAt(i);
            char claveChar = clave.charAt(i % clave.length());
            int posLetra = ABC.indexOf(letra);
            int posClave = ABC.indexOf(claveChar);
            if (posLetra == -1 || posClave == -1) continue;
            int modulo = (posLetra - posClave + ABC.length()) % ABC.length();
            cadenaDescifrada.append(ABC.charAt(modulo));
        }
        System.out.println("Texto descifrado: " + cadenaDescifrada.toString());
        return cadenaDescifrada.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1 Cifrar    ");
            System.out.println("2 Descifrar ");
            System.out.println("3 Salir     ");
            System.out.print("Ingrese opción: ");

            int opt;
            try {
                opt = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor ingrese un número válido.");
                continue;
            }

            switch (opt) {
                case 1:
                    System.out.print("Texto a cifrar: ");
                    String cadenaCifrar = scanner.nextLine();
                    System.out.print("Clave: ");
                    String claveCifrar = scanner.nextLine();
                    cifrar(cadenaCifrar, claveCifrar);
                    break;
                case 2:
                    System.out.print("Texto a descifrar: ");
                    String cadenaDescifrar = scanner.nextLine();
                    System.out.print("Clave: ");
                    String claveDescifrar = scanner.nextLine();
                    descifrar(cadenaDescifrar, claveDescifrar);
                    break;
                case 3:
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }
}