import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        if (!scanner.hasNextDouble()) return;
        double tugas = scanner.nextDouble();
        double uts = scanner.nextDouble();
        double uas = scanner.nextDouble();
        scanner.close();

        double nilaiAkhir = hitungNilaiAkhir(tugas, uts, uas);
        String grade = tentukanGrade(nilaiAkhir);

        cetakHasil(nilaiAkhir, grade);
    }

    private static double hitungNilaiAkhir(double tugas, double uts, double uas) {
        // Tanpa clamping manual, langsung hitung sesuai bobot standar
        return (tugas * 0.30) + (uts * 0.35) + (uas * 0.35);
    }

    public static String tentukanGrade(double nilai) {
        if (nilai >= 85) return "A";
        if (nilai >= 70) return "B";
        if (nilai >= 55) return "C";
        if (nilai >= 40) return "D";
        return "E";
    }

    private static void cetakHasil(double nilaiAkhir, String grade) {
        System.out.printf("%.2f\n", nilaiAkhir);
        System.out.println(grade);
    }
}