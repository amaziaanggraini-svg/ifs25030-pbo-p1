import java.util.Scanner;

public class App {

    private static final int MINUTES_IN_DAY = 1440;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        if (!sc.hasNextLine()) {
            System.out.println("Jam tidak valid");
            sc.close();
            return;
        }

        String line1 = sc.nextLine().trim();
        int currentMinutes = parseJamAwal(line1);

        if (currentMinutes == -1) {
            System.out.println("Jam tidak valid");
            sc.close();
            return;
        }

        String jamAwal = String.format("%02d:%02d", currentMinutes / 60, currentMinutes % 60);
        
        prosesPergeseran(sc, currentMinutes, jamAwal);

        sc.close();
    }

    /**
     * Memvalidasi dan mengubah format HH:mm menjadi total menit sejak 00:00.
     * Mengembalikan -1 jika format jam tidak valid.
     */
    private static int parseJamAwal(String line) {
        int colonIndex = line.indexOf(':');
        if (colonIndex == -1 || colonIndex != line.lastIndexOf(':')) {
            return -1;
        }

        String[] timeParts = line.split(":");
        if (timeParts.length != 2) {
            return -1;
        }

        try {
            int startH = Integer.parseInt(timeParts[0].trim());
            int startM = Integer.parseInt(timeParts[1].trim());

            if (startH < 0 || startH > 23 || startM < 0 || startM > 59) {
                return -1;
            }

            return startH * 60 + startM;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * Memproses perintah pergeseran (+ / -) dan menghitung pergantian hari dengan aritmetika modulo.
     */
    private static void prosesPergeseran(Scanner sc, int currentMinutes, String jamAwal) {
        int totalGeser = 0;
        int pergantianHari = 0;

        while (sc.hasNextLine()) {
            String command = sc.nextLine().trim();

            if (command.equals("---")) {
                break;
            }

            if (command.isEmpty()) {
                continue;
            }

            if (!command.matches("^[+-]\\d+$")) {
                System.out.println("Perintah tidak valid");
                continue;
            }

            int n = Integer.parseInt(command);
            totalGeser += n;

            if (n > 0) {
                int totalTarget = currentMinutes + n;
                pergantianHari += totalTarget / MINUTES_IN_DAY;
                currentMinutes = totalTarget % MINUTES_IN_DAY;
            } else if (n < 0) {
                int geser = -n;
                if (geser < currentMinutes) {
                    currentMinutes -= geser;
                } else if (geser == currentMinutes) {
                    currentMinutes = 0;
                } else {
                    int sisa = geser - currentMinutes;
                    int countHari = 1 + (sisa - 1) / MINUTES_IN_DAY;
                    
                    // Pergantian hari bertambah (frekuensi melewati batas hari/00:00)
                    pergantianHari += countHari;

                    currentMinutes = (MINUTES_IN_DAY - (sisa % MINUTES_IN_DAY)) % MINUTES_IN_DAY;
                }
            }
        }

        cetakHasil(jamAwal, currentMinutes, totalGeser, pergantianHari);
    }

    /**
     * Mencetak output statistik sesuai dengan spesifikasi format.
     */
    private static void cetakHasil(String jamAwal, int finalMinutes, int totalGeser, int pergantianHari) {
        int finalH = finalMinutes / 60;
        int finalM = finalMinutes % 60;
        String jamAkhir = String.format("%02d:%02d", finalH, finalM);

        String strTotalMenit = (totalGeser > 0) ? "+" + totalGeser : String.valueOf(totalGeser);

        System.out.println("Jam Awal: " + jamAwal);
        System.out.println("Jam Akhir: " + jamAkhir);
        System.out.println("Total Menit: " + strTotalMenit);
        System.out.println("Pergantian Hari: " + pergantianHari);
    }
}