import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = parseInput(sc);

        // Validasi n tidak boleh negatif atau nol
        if (n <= 0) {
            sc.close();
            return;
        }

        System.out.println("Pola L:");
        cetakPolaL(n);

        System.out.println();

        System.out.println("Pola L Kebalikan:");
        cetakPolaLKebalikan(n);

        sc.close();
    }

    /**
     * Membaca dan memvalidasi input integer dari Scanner.
     * Mengembalikan -1 jika input tidak ada atau bukan integer.
     */
    private static int parseInput(Scanner sc) {
        if (!sc.hasNextInt()) {
            return -1;
        }
        return sc.nextInt();
    }

    /**
     * Mencetak pola L standar berukuran n.
     */
    private static void cetakPolaL(int n) {
        // Batang vertikal
        for (int i = 0; i < n - 1; i++) {
            System.out.println("*");
        }
        // Garis horizontal bawah
        for (int i = 0; i < n; i++) {
            System.out.print("*");
        }
        System.out.println();
    }

    /**
     * Mencetak pola L kebalikan (terbalik) berukuran n.
     */
    private static void cetakPolaLKebalikan(int n) {
        // Garis horizontal atas
        for (int i = 0; i < n; i++) {
            System.out.print("*");
        }
        System.out.println();

        // Batang vertikal di sisi kanan
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1; j++) {
                System.out.print(" ");
            }
            System.out.println("*");
        }
    }
}