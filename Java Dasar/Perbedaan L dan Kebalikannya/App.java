import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) {
            sc.close();
            return;
        }

        int n = sc.nextInt();
        long[][] matrix = new long[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = sc.nextLong();
            }
        }

        if (n < 3) {
            long totalSum = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    totalSum += matrix[i][j];
                }
            }
            System.out.println("Nilai L: Tidak Ada");
            System.out.println("Nilai Kebalikan L: Tidak Ada");
            System.out.println("Nilai Tengah: " + totalSum);
            System.out.println("Perbedaan: Tidak Ada");
            System.out.println("Dominan: " + totalSum);
        } else {
            long sumL = 0;
            long sumRevL = 0;
            long sumCenter = 0;

            // 1. Hitung Pola L: 
            // Seluruh Kolom 0 (i: 0 -> n-1) + Baris n-1 (j: 1 -> n-2)
            for (int i = 0; i < n; i++) {
                sumL += matrix[i][0];
            }
            for (int j = 1; j < n - 1; j++) {
                sumL += matrix[n - 1][j];
            }

            // 2. Hitung Pola Kebalikan L: 
            // Baris 0 (j: 1 -> n-1) + Kolom n-1 (i: 1 -> n-1)
            for (int j = 1; j < n; j++) {
                sumRevL += matrix[0][j];
            }
            for (int i = 1; i < n; i++) {
                sumRevL += matrix[i][n - 1];
            }

            // 3. Hitung Nilai Tengah (Elemen Pusat Matriks)
            if (n % 2 != 0) {
                sumCenter = matrix[n / 2][n / 2];
            } else {
                int mid = n / 2;
                sumCenter = matrix[mid - 1][mid - 1] + matrix[mid - 1][mid]
                          + matrix[mid][mid - 1]     + matrix[mid][mid];
            }

            long diff = Math.abs(sumL - sumRevL);

            // 4. Penentuan Nilai Dominan
            long maxVal;
            if (sumL == sumRevL) {
                maxVal = sumCenter; // Jika L dan Kebalikan L sama, yang dominan adalah Nilai Tengah
            } else {
                maxVal = Math.max(sumL, Math.max(sumRevL, sumCenter));
            }

            System.out.println("Nilai L: " + sumL);
            System.out.println("Nilai Kebalikan L: " + sumRevL);
            System.out.println("Nilai Tengah: " + sumCenter);
            System.out.println("Perbedaan: " + diff);
            System.out.println("Dominan: " + maxVal);
        }

        sc.close();
    }
}