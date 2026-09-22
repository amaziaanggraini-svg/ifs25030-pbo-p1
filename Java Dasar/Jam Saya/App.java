import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        if (!sc.hasNextLine()) {
            System.out.println("Jam tidak valid");
            sc.close();
            return;
        }

        String line1 = sc.nextLine().trim();

        int colonIndex = line1.indexOf(':');
        if (colonIndex == -1 || colonIndex != line1.lastIndexOf(':')) {
            System.out.println("Jam tidak valid");
            sc.close();
            return;
        }

        String[] timeParts = line1.split(":");
        if (timeParts.length != 2) {
            System.out.println("Jam tidak valid");
            sc.close();
            return;
        }

        int startH, startM;
        try {
            startH = Integer.parseInt(timeParts[0].trim());
            startM = Integer.parseInt(timeParts[1].trim());
        } catch (NumberFormatException e) {
            System.out.println("Jam tidak valid");
            sc.close();
            return;
        }

        if (startH < 0 || startH > 23 || startM < 0 || startM > 59) {
            System.out.println("Jam tidak valid");
            sc.close();
            return;
        }

        String jamAwal = String.format("%02d:%02d", startH, startM);

        int currentMinutes = startH * 60 + startM;
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

            // Simulasi per 1 menit atau kalkulasi langsung
            if (n > 0) {
                for (int i = 0; i < n; i++) {
                    currentMinutes++;
                    if (currentMinutes == 1440) {
                        pergantianHari++;
                        currentMinutes = 0;
                    }
                }
            } else if (n < 0) {
                for (int i = 0; i < -n; i++) {
                    if (currentMinutes == 0) {
                        pergantianHari++;
                        currentMinutes = 1440;
                    }
                    currentMinutes--;
                }
            }
        }

        int finalH = currentMinutes / 60;
        if (finalH == 24) finalH = 0;
        int finalM = currentMinutes % 60;
        String jamAkhir = String.format("%02d:%02d", finalH, finalM);

        String strTotalMenit;
        if (totalGeser > 0) {
            strTotalMenit = "+" + totalGeser;
        } else {
            strTotalMenit = String.valueOf(totalGeser);
        }

        System.out.println("Jam Awal: " + jamAwal);
        System.out.println("Jam Akhir: " + jamAkhir);
        System.out.println("Total Menit: " + strTotalMenit);
        System.out.println("Pergantian Hari: " + pergantianHari);

        sc.close();
    }
}