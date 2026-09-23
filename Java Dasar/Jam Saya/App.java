import java.util.Scanner;

public class App {

    private static final long MENIT_PER_HARI = 1440L;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        if (!sc.hasNextLine()) {
            return;
        }

        String inputJam = sc.nextLine().trim();
        int[] jamAwal = parseJam(inputJam);

        if (jamAwal == null) {
            System.out.println("Jam tidak valid");
            return;
        }

        long totalMenit = jamAwal[0] * 60L + jamAwal[1];
        long totalGeser = 0L;
        long pergantianHari = 0L;

        while (sc.hasNextLine()) {
            String perintah = sc.nextLine().trim();

            if (perintah.equals("---")) {
                break;
            }

            Long geser = parsePerintah(perintah);

            if (geser == null) {
                System.out.println("Perintah tidak valid");
                continue;
            }

            long totalBaru = totalMenit + geser;
            pergantianHari += hitungPergantianHari(totalBaru);
            totalMenit = Math.floorMod(totalBaru, MENIT_PER_HARI);
            totalGeser += geser;
        }

        cetakHasil(
            jamAwal,
            totalMenit,
            totalGeser,
            pergantianHari
        );
    }

    static int[] parseJam(String input) {
        String[] bagian = input.split(":", -1);

        if (bagian.length != 2) {
            return null;
        }

        int jam;
        int menit;

        try {
            jam = Integer.parseInt(bagian[0].trim());
            menit = Integer.parseInt(bagian[1].trim());
        } catch (NumberFormatException e) {
            return null;
        }

        if (jam < 0 || jam > 23 || menit < 0 || menit > 59) {
            return null;
        }

        return new int[] {jam, menit};
    }

    static Long parsePerintah(String input) {
        if (input.length() < 2) {
            return null;
        }

        char tanda = input.charAt(0);
        String angka = input.substring(1);

        if ((tanda != '+' && tanda != '-') || !hanyaDigit(angka)) {
            return null;
        }

        try {
            long nilai = Long.parseLong(angka);
            return tanda == '-' ? -nilai : nilai;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    static boolean hanyaDigit(String teks) {
        for (int i = 0; i < teks.length(); i++) {
            if (!Character.isDigit(teks.charAt(i))) {
                return false;
            }
        }

        return !teks.isEmpty();
    }

    static long hitungPergantianHari(long totalBaru) {
        /*
         * Karena waktu sebelum perintah sudah dinormalisasi ke 0..1439,
         * floorDiv(totalBaru, 1440) menunjukkan jumlah batas hari yang dilewati.
         */
        return Math.abs(Math.floorDiv(totalBaru, MENIT_PER_HARI));
    }

    static void cetakHasil(
        int[] jamAwal,
        long totalMenit,
        long totalGeser,
        long pergantianHari
    ) {
        int jamAkhir = (int) (totalMenit / 60);
        int menitAkhir = (int) (totalMenit % 60);

        System.out.printf(
            "Jam Awal: %02d:%02d%n",
            jamAwal[0],
            jamAwal[1]
        );

        System.out.printf(
            "Jam Akhir: %02d:%02d%n",
            jamAkhir,
            menitAkhir
        );

        System.out.println(
            "Total Menit: " + formatTotalMenit(totalGeser)
        );

        System.out.println(
            "Pergantian Hari: " + pergantianHari
        );
    }

    static String formatTotalMenit(long total) {
        if (total > 0) {
            return "+" + total;
        }

        return String.valueOf(total);
    }
}
