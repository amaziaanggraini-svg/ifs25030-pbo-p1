import java.util.*;

public class App {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Locale.setDefault(Locale.US);

        String[] kode = {"PA", "T", "K", "P", "UTS", "UAS"};
        String[] nama = {"Partisipatif", "Tugas", "Kuis", "Proyek", "UTS", "UAS"};

        int[] bobotFinal = new int[6];
        int totalBobot = 0;

        for (int i = 0; i < 6; i++) {
            if (!in.hasNextLine()) break;
            bobotFinal[i] = Integer.parseInt(in.nextLine().trim());
            totalBobot += bobotFinal[i];
        }

        if (totalBobot != 100) {
            System.out.println("Total bobot harus 100");
            in.close();
            return;
        }

        int[] totalBobotKomponen = new int[6];
        int[] totalPerolehan = new int[6];

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
            int idx = -1;
            for (int i = 0; i < 6; i++) {
                if (kode[i].equals(simbol)) {
                    idx = i;
                    break;
                }
            }

            if (idx == -1) {
                System.out.println("Simbol tidak dikenal");
                continue;
            }

            try {
                int b = Integer.parseInt(potongan[1].trim());
                int p = Integer.parseInt(potongan[2].trim());

                if (p > b) p = b;
                if (p < 0) p = 0;

                totalBobotKomponen[idx] += b;
                totalPerolehan[idx] += p;
            } catch (NumberFormatException e) {
                System.out.println("Data tidak valid. Silahkan menggunakan format: Simbol|Bobot|Perolehan-Nilai");
            }
        }

        double nilaiAkhir = 0.0;
        System.out.println("Perolehan Nilai:");

        for (int i = 0; i < 6; i++) {
            int persen = (totalBobotKomponen[i] == 0) ? 0 : (totalPerolehan[i] * 100) / totalBobotKomponen[i];
            double kontribusi = Math.round((persen / 100.0) * bobotFinal[i] * 100) / 100.0;
            nilaiAkhir += kontribusi;

            System.out.printf(">> %s: %d/100 (%.2f/%d)%n", nama[i], persen, kontribusi, bobotFinal[i]);
        }

        nilaiAkhir = Math.round(nilaiAkhir * 100) / 100.0;

        System.out.println();
        System.out.printf(">> Nilai Akhir: %.2f%n", nilaiAkhir);
        System.out.println(">> Grade: " + tentukanGrade(nilaiAkhir));

        in.close();
    }

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