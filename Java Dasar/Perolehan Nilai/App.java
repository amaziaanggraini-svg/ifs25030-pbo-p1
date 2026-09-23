import java.util.Scanner;

public class App {

    private static final String[] SIMBOL = {
        "PA", "T", "K", "P", "UTS", "UAS"
    };

    private static final String[] NAMA_KOMPONEN = {
        "Partisipatif",
        "Tugas",
        "Kuis",
        "Proyek",
        "UTS",
        "UAS"
    };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] bobot = bacaBobot(sc);

        if (jumlahBobot(bobot) != 100) {
            System.out.println("Total bobot harus 100");
            return;
        }

        int[] total = new int[SIMBOL.length];
        int[] perolehan = new int[SIMBOL.length];
        bacaDataKomponen(sc, total, perolehan);

        int[] perolehan100 = hitungPerolehan100(total, perolehan);
        double[] kontribusi = hitungKontribusi(perolehan100, bobot);
        double nilaiAkhir = bulatkanDuaDesimal(hitungNilaiAkhir(kontribusi));
        String grade = tentukanGrade(nilaiAkhir);

        cetakHasil(perolehan100, kontribusi, bobot, nilaiAkhir, grade);
    }

    static int[] bacaBobot(Scanner sc) {
        int[] bobot = new int[SIMBOL.length];

        for (int i = 0; i < bobot.length; i++) {
            bobot[i] = Integer.parseInt(sc.nextLine().trim());
        }

        return bobot;
    }

    static int jumlahBobot(int[] bobot) {
        int total = 0;

        for (int nilai : bobot) {
            total += nilai;
        }

        return total;
    }

    static void bacaDataKomponen(
        Scanner sc,
        int[] total,
        int[] perolehan
    ) {
        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();

            if (line.equals("---")) {
                break;
            }

            prosesBaris(line, total, perolehan);
        }
    }

    static void prosesBaris(
        String line,
        int[] total,
        int[] perolehan
    ) {
        String[] data = line.split("\\|", -1);

        if (data.length != 3) {
            cetakErrorFormat();
            return;
        }

        String kode = data[0].trim();
        int nilaiBobot;
        int nilaiPerolehan;

        try {
            nilaiBobot = Integer.parseInt(data[1].trim());
            nilaiPerolehan = Integer.parseInt(data[2].trim());
        } catch (NumberFormatException e) {
            cetakErrorFormat();
            return;
        }

        int index = cariSimbol(kode);

        if (index == -1) {
            System.out.println("Simbol tidak dikenal");
            return;
        }

        nilaiPerolehan = normalisasiPerolehan(
            nilaiPerolehan,
            nilaiBobot
        );

        total[index] += nilaiBobot;
        perolehan[index] += nilaiPerolehan;
    }

    static int cariSimbol(String kode) {
        for (int i = 0; i < SIMBOL.length; i++) {
            if (SIMBOL[i].equals(kode)) {
                return i;
            }
        }

        return -1;
    }

    static int normalisasiPerolehan(int perolehan, int bobot) {
        // Spesifikasi meminta nilai dibatasi pada 0..bobot.
        return Math.max(0, Math.min(perolehan, bobot));
    }

    static int[] hitungPerolehan100(
        int[] total,
        int[] perolehan
    ) {
        int[] hasil = new int[SIMBOL.length];

        for (int i = 0; i < hasil.length; i++) {
            if (total[i] == 0) {
                hasil[i] = 0;
                continue;
            }

            double persentase =
                ((double) perolehan[i] / total[i]) * 100.0;

            // Nilai yang ditampilkan disimpan sebagai bilangan bulat.
            hasil[i] = (int) Math.round(persentase);
        }

        return hasil;
    }

    static double[] hitungKontribusi(
        int[] perolehan100,
        int[] bobot
    ) {
        double[] kontribusi = new double[SIMBOL.length];

        for (int i = 0; i < kontribusi.length; i++) {
            kontribusi[i] =
                (perolehan100[i] * bobot[i]) / 100.0;
        }

        return kontribusi;
    }

    static double hitungNilaiAkhir(double[] kontribusi) {
        double total = 0.0;

        for (double nilai : kontribusi) {
            total += nilai;
        }

        return total;
    }

    static double bulatkanDuaDesimal(double nilai) {
        return Math.round(nilai * 100.0) / 100.0;
    }

    static String tentukanGrade(double nilai) {
        if (nilai >= 79.5) {
            return "A";
        }
        if (nilai >= 72) {
            return "AB";
        }
        if (nilai >= 64.5) {
            return "B";
        }
        if (nilai >= 57) {
            return "BC";
        }
        if (nilai >= 49.5) {
            return "C";
        }
        if (nilai >= 34) {
            return "D";
        }
        return "E";
    }

    static void cetakErrorFormat() {
        System.out.println(
            "Data tidak valid. Silahkan menggunakan format: Simbol|Bobot|Perolehan-Nilai"
        );
    }

    static void cetakHasil(
        int[] perolehan100,
        double[] kontribusi,
        int[] bobot,
        double nilaiAkhir,
        String grade
    ) {
        System.out.println("Perolehan Nilai:");

        for (int i = 0; i < NAMA_KOMPONEN.length; i++) {
            System.out.printf(
                ">> %s: %d/100 (%.2f/%d)%n",
                NAMA_KOMPONEN[i],
                perolehan100[i],
                kontribusi[i],
                bobot[i]
            );
        }

        System.out.println();
        System.out.printf(">> Nilai Akhir: %.2f%n", nilaiAkhir);
        System.out.println(">> Grade: " + grade);
    }
}
