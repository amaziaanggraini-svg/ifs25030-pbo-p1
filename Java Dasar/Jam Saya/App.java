import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) return;
        
        int jam = scanner.nextInt();
        int menit = scanner.nextInt();
        int tambahMenit = scanner.nextInt();
        scanner.close();

        HitungWaktu(jam, menit, tambahMenit);
    }

    private static void HitungWaktu(int jam, int menit, int tambahMenit) {
        // Hitung total menit keseluruhan dari jam 00:00
        int totalMenitAwal = jam * 60 + menit;
        int totalMenitAkhir = totalMenitAwal + tambahMenit;

        // Gunakan modulo untuk perputaran 24 jam (1440 menit)
        int totalMenitDalamSehari = (totalMenitAkhir % 1440 + 1440) % 1440;

        int jamAkhir = totalMenitDalamSehari / 60;
        int menitAkhir = totalMenitDalamSehari % 60;

        cetakWaktu(jamAkhir, menitAkhir);
    }

    private static void cetakWaktu(int jam, int menit) {
        System.out.printf("%02d:%02d\n", jam, menit);
    }
}