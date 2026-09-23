import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        int[][] matrix = bacaMatriks(sc, n);

        if (n == 1) {
            cetakKasus1x1(matrix[0][0]);
            return;
        }

        if (n == 2) {
            cetakKasus2x2(hitungTotal(matrix));
            return;
        }

        long nilaiL = hitungNilaiL(matrix);
        long nilaiKebalikanL = hitungNilaiKebalikanL(matrix);
        long nilaiTengah = hitungNilaiTengah(matrix);
        long perbedaan = Math.abs(nilaiL - nilaiKebalikanL);
        long dominan = tentukanDominan(
            nilaiL,
            nilaiKebalikanL,
            nilaiTengah,
            perbedaan
        );

        cetakHasil(
            nilaiL,
            nilaiKebalikanL,
            nilaiTengah,
            perbedaan,
            dominan
        );
    }

    static int[][] bacaMatriks(Scanner sc, int n) {
        int[][] matrix = new int[n][n];

        for (int i = 0; i < n; i++) {
            String[] data = sc.nextLine().trim().split("\\s+");

            for (int j = 0; j < n; j++) {
                matrix[i][j] = Integer.parseInt(data[j]);
            }
        }

        return matrix;
    }

    static long hitungNilaiL(int[][] matrix) {
        int n = matrix.length;
        long total = 0;

        // Kolom pertama.
        for (int i = 0; i < n; i++) {
            total += matrix[i][0];
        }

        // Baris terakhir tanpa mengulang sudut kiri dan kanan.
        for (int j = 1; j < n - 1; j++) {
            total += matrix[n - 1][j];
        }

        return total;
    }

    static long hitungNilaiKebalikanL(int[][] matrix) {
        int n = matrix.length;
        long total = 0;

        // Kolom terakhir.
        for (int i = 0; i < n; i++) {
            total += matrix[i][n - 1];
        }

        // Baris pertama tanpa mengulang sudut kiri dan kanan.
        for (int j = 1; j < n - 1; j++) {
            total += matrix[0][j];
        }

        return total;
    }

    static long hitungNilaiTengah(int[][] matrix) {
        int n = matrix.length;

        if (n % 2 == 1) {
            return matrix[n / 2][n / 2];
        }

        int baris = n / 2 - 1;
        int kolom = n / 2 - 1;

        // Matriks genap memakai empat elemen pusat.
        return (long) matrix[baris][kolom]
            + matrix[baris][kolom + 1]
            + matrix[baris + 1][kolom]
            + matrix[baris + 1][kolom + 1];
    }

    static long tentukanDominan(
        long nilaiL,
        long nilaiKebalikanL,
        long nilaiTengah,
        long perbedaan
    ) {
        if (perbedaan == 0) {
            return nilaiTengah;
        }

        return Math.max(nilaiL, nilaiKebalikanL);
    }

    static long hitungTotal(int[][] matrix) {
        long total = 0;

        for (int[] baris : matrix) {
            for (int nilai : baris) {
                total += nilai;
            }
        }

        return total;
    }

    static void cetakKasus1x1(int nilai) {
        System.out.println("Nilai L: Tidak Ada");
        System.out.println("Nilai Kebalikan L: Tidak Ada");
        System.out.println("Nilai Tengah: " + nilai);
        System.out.println("Perbedaan: Tidak Ada");
        System.out.println("Dominan: " + nilai);
    }

    static void cetakKasus2x2(long total) {
        System.out.println("Nilai L: Tidak Ada");
        System.out.println("Nilai Kebalikan L: Tidak Ada");
        System.out.println("Nilai Tengah: " + total);
        System.out.println("Perbedaan: Tidak Ada");
        System.out.println("Dominan: " + total);
    }

    static void cetakHasil(
        long nilaiL,
        long nilaiKebalikanL,
        long nilaiTengah,
        long perbedaan,
        long dominan
    ) {
        System.out.println("Nilai L: " + nilaiL);
        System.out.println("Nilai Kebalikan L: " + nilaiKebalikanL);
        System.out.println("Nilai Tengah: " + nilaiTengah);
        System.out.println("Perbedaan: " + perbedaan);
        System.out.println("Dominan: " + dominan);
    }
}
