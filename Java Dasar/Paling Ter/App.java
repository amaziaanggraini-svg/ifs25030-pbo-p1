import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<Integer, Integer> frekuensi = bacaFrekuensi(sc);

        if (frekuensi.isEmpty()) {
            return;
        }

        int tertinggi = cariTertinggi(frekuensi);
        int terendah = cariTerendah(frekuensi);
        int terbanyak = cariTerbanyak(frekuensi);
        int tersedikit = cariTersedikit(frekuensi);
        int jumlahTertinggi = cariJumlahTerbesar(frekuensi);
        int jumlahTerendah = cariJumlahTerkecil(frekuensi);

        cetakHasil(
            frekuensi,
            tertinggi,
            terendah,
            terbanyak,
            tersedikit,
            jumlahTertinggi,
            jumlahTerendah
        );
    }

    static Map<Integer, Integer> bacaFrekuensi(Scanner sc) {
        Map<Integer, Integer> frekuensi = new HashMap<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();

            if (line.equals("---")) {
                break;
            }

            try {
                int nilai = Integer.parseInt(line);
                frekuensi.put(
                    nilai,
                    frekuensi.getOrDefault(nilai, 0) + 1
                );
            } catch (NumberFormatException e) {
                // Input yang bukan bilangan tidak diproses.
            }
        }

        return frekuensi;
    }

    static int cariTertinggi(Map<Integer, Integer> frekuensi) {
        int hasil = Integer.MIN_VALUE;

        for (int nilai : frekuensi.keySet()) {
            if (nilai > hasil) {
                hasil = nilai;
            }
        }

        return hasil;
    }

    static int cariTerendah(Map<Integer, Integer> frekuensi) {
        int hasil = Integer.MAX_VALUE;

        for (int nilai : frekuensi.keySet()) {
            if (nilai < hasil) {
                hasil = nilai;
            }
        }

        return hasil;
    }

    static int cariTerbanyak(Map<Integer, Integer> frekuensi) {
        int hasil = 0;
        int frekuensiTerbesar = -1;
        boolean pertama = true;

        for (Map.Entry<Integer, Integer> entry : frekuensi.entrySet()) {
            int nilai = entry.getKey();
            int jumlah = entry.getValue();

            if (pertama
                || jumlah > frekuensiTerbesar
                || (jumlah == frekuensiTerbesar && nilai > hasil)) {
                hasil = nilai;
                frekuensiTerbesar = jumlah;
                pertama = false;
            }
        }

        return hasil;
    }

    static int cariTersedikit(Map<Integer, Integer> frekuensi) {
        int hasil = 0;
        int frekuensiTerkecil = Integer.MAX_VALUE;
        boolean pertama = true;

        for (Map.Entry<Integer, Integer> entry : frekuensi.entrySet()) {
            int nilai = entry.getKey();
            int jumlah = entry.getValue();

            if (pertama
                || jumlah < frekuensiTerkecil
                || (jumlah == frekuensiTerkecil && nilai < hasil)) {
                hasil = nilai;
                frekuensiTerkecil = jumlah;
                pertama = false;
            }
        }

        return hasil;
    }

    static int cariJumlahTerbesar(Map<Integer, Integer> frekuensi) {
        int hasil = 0;
        long jumlahTerbesar = Long.MIN_VALUE;
        boolean pertama = true;

        for (Map.Entry<Integer, Integer> entry : frekuensi.entrySet()) {
            int nilai = entry.getKey();
            int jumlah = entry.getValue();
            long hasilKali = (long) nilai * jumlah;

            if (pertama
                || hasilKali > jumlahTerbesar
                || (hasilKali == jumlahTerbesar && nilai > hasil)) {
                hasil = nilai;
                jumlahTerbesar = hasilKali;
                pertama = false;
            }
        }

        return hasil;
    }

    static int cariJumlahTerkecil(Map<Integer, Integer> frekuensi) {
        int hasil = 0;
        long jumlahTerkecil = Long.MAX_VALUE;
        boolean pertama = true;

        for (Map.Entry<Integer, Integer> entry : frekuensi.entrySet()) {
            int nilai = entry.getKey();
            int jumlah = entry.getValue();
            long hasilKali = (long) nilai * jumlah;

            if (pertama
                || hasilKali < jumlahTerkecil
                || (hasilKali == jumlahTerkecil && nilai < hasil)) {
                hasil = nilai;
                jumlahTerkecil = hasilKali;
                pertama = false;
            }
        }

        return hasil;
    }

    static void cetakHasil(
        Map<Integer, Integer> frekuensi,
        int tertinggi,
        int terendah,
        int terbanyak,
        int tersedikit,
        int jumlahTertinggi,
        int jumlahTerendah
    ) {
        long nilaiJumlahTertinggi =
            (long) jumlahTertinggi * frekuensi.get(jumlahTertinggi);

        long nilaiJumlahTerendah =
            (long) jumlahTerendah * frekuensi.get(jumlahTerendah);

        System.out.println("Tertinggi: " + tertinggi);
        System.out.println("Terendah: " + terendah);
        System.out.println(
            "Terbanyak: " + terbanyak
            + " (" + frekuensi.get(terbanyak) + "x)"
        );
        System.out.println(
            "Tersedikit: " + tersedikit
            + " (" + frekuensi.get(tersedikit) + "x)"
        );
        System.out.println(
            "Jumlah Tertinggi: " + jumlahTertinggi
            + " * " + frekuensi.get(jumlahTertinggi)
            + " = " + nilaiJumlahTertinggi
        );
        System.out.println(
            "Jumlah Terendah: " + jumlahTerendah
            + " * " + frekuensi.get(jumlahTerendah)
            + " = " + nilaiJumlahTerendah
        );
    }
}
