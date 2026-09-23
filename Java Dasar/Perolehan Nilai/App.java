import java.util.*;

public class App {

    private static final String[] KODE = {"PA", "T", "K", "P", "UTS", "UAS"};
    private static final String[] NAMA = {"Partisipatif", "Tugas", "Kuis", "Proyek", "UTS", "UAS"};

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Locale.setDefault(Locale.US);

        int[] bobotFinal = bacaBobotFinal(in);
        if (bobotFinal == null) {
            in.close();
            return;
        }

        int[] totalBobotKomponen = new int[6];
        int[] totalPerolehan = new int[6];

        prosesSubkomponen(in, totalBobotKomponen, totalPerolehan);
        hitungDanCetakOutput(bobotFinal, totalBobotKomponen, totalPerolehan);

        in.close();
    }

    /**
     * Membaca 6 baris bobot komponen utama dan memvalidasi totalnya harus 100.
     * Menggunakan try-catch untuk mengantisipasi NumberFormatException.
     */
    private static int[] bacaBobotFinal(Scanner in) {
        int[] bobotFinal = new int[6];
        int totalBobot = 0;

        for (int i = 0; i < 6; i++) {
            if (!in.hasNextLine()) break;
            try {
                bobotFinal[i] = Integer.parseInt(in.nextLine().trim());
                totalBobot += bobotFinal[i];
            } catch (NumberFormatException e) {
                System.out.println("Format bobot tidak valid");
                return null;
            }
        }

        if (totalBobot != 100) {
            System.out.println("Total bobot harus 100");
            return null;
        }

        return bobotFinal;
    }

    /**
     * Membaca dan menjumlahkan bobot serta perolehan nilai dari tiap subkomponen.
     */
    private static void prosesSubkomponen(Scanner in, int[] totalBobotKomponen, int[] totalPerolehan) {
        while (in.hasNextLine()) {
            String baris = in.nextLine().trim();
            if (baris.equals("---")) break;
            if (baris.isEmpty()) continue;

            String[] potongan = baris.split("\\|", -1);
            if (potongan.length != 3) {
                System.out.println("Data tidak valid. Silahkan menggunakan format: Simbol|Bobot|Perolehan-Nilai");
                continue;
            }

            String simbol = potongan[0].trim();
            int idx = cariIndeksSimbol(simbol);

            if (idx == -1) {
                System.out.println("Simbol tidak dikenal");
                continue;
            }

            try {
                int b = Integer.parseInt(potongan[1].trim());
                int p = Integer.parseInt(potongan[2].trim());

                // Clamping dihapus sesuai spesifikasi agar tidak mengubah hasil perolehan nilai
                totalBobotKomponen[idx] += b;
                totalPerolehan[idx] += p;
            } catch (NumberFormatException e) {
                System.out.println("Data tidak valid. Silahkan menggunakan format: Simbol|Bobot|Perolehan-Nilai");
            }
        }
    }

    /**
     * Mencari indeks simbol komponen pada array referensi.
     */
    private static int cariIndeksSimbol(String simbol) {
        for (int i = 0; i < KODE.length; i++) {
            if (KODE[i].equals(simbol)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Menghitung persentase, kontribusi nilai, total nilai akhir, dan mencetak hasilnya.
     */
    private static void hitungDanCetakOutput(int[] bobotFinal, int[] totalBobotKomponen, int[] totalPerolehan) {
        double nilaiAkhir = 0.0;
        System.out.println("Perolehan Nilai:");

        for (int i = 0; i < 6; i++) {
            int persen = (totalBobotKomponen[i] == 0) ? 0 : (totalPerolehan[i] * 100) / totalBobotKomponen[i];
            double kontribusi = Math.round((persen / 100.0) * bobotFinal[i] * 100) / 100.0;
            nilaiAkhir += kontribusi;

            System.out.printf(">> %s: %d/100 (%.2f/%d)%n", NAMA[i], persen, kontribusi, bobotFinal[i]);
        }

        nilaiAkhir = Math.round(nilaiAkhir * 100) / 100.0;

        System.out.println();
        System.out.printf(">> Nilai Akhir: %.2f%n", nilaiAkhir);
        System.out.println(">> Grade: " + tentukanGrade(nilaiAkhir));
    }

    /**
     * Menentukan grade berdasarkan standar acuan batas nilai.
     */
    static String tentukanGrade(double n) {
        if (n >= 79.5) return "A";
        if (n >= 72.0) return "AB";
        if (n >= 64.5) return "B";
        if (n >= 57.0) return "BC";
        if (n >= 49.5) return "C";
        if (n >= 34.0) return "D";
        return "E";
    }
}