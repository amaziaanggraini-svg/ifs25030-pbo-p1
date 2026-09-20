import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextLine()) {
            sc.close();
            return;
        }
        String nim = sc.nextLine().trim();

        if (nim.length() != 8) {
            System.out.println("NIM harus 8 karakter");
            sc.close();
            return;
        }

        String prefix = nim.substring(0, 3);
        String prodi = "";

        switch (prefix) {
            case "11S": prodi = "Sarjana Informatika"; break;
            case "12S": prodi = "Sarjana Sistem Informasi"; break;
            case "13S": prodi = "Sarjana Teknik Elektro"; break;
            case "21S": prodi = "Sarjana Manajemen Rekayasa"; break;
            case "22S": prodi = "Sarjana Teknik Metalurgi"; break;
            case "31S": prodi = "Sarjana Teknik Bioproses"; break;
            case "32S": prodi = "Sarjana Bioteknologi"; break;
            case "114": prodi = "Diploma 4 Teknologi Rekayasa Perangkat Lunak"; break;
            case "113": prodi = "Diploma 3 Teknologi Informasi"; break;
            case "133": prodi = "Diploma 3 Teknologi Komputer"; break;
            default:
                System.out.println("Kode tidak tersedia");
                sc.close();
                return;
        }

        int angkatan = Integer.parseInt("20" + nim.substring(3, 5));
        int urutan = Integer.parseInt(nim.substring(5, 8));

        System.out.println("Informasi NIM " + nim + ": ");
        System.out.println(">> Program Studi: " + prodi);
        System.out.println(">> Angkatan: " + angkatan);
        System.out.println(">> Urutan: " + urutan);

        sc.close();
    }
}