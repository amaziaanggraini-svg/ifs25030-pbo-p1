import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String nim = bacaNim(sc);

        if (!validasiPanjang(nim)) {
            System.out.println("NIM harus 8 karakter");
            return;
        }

        String programStudi = cariProgramStudi(nim);
        if (programStudi == null) {
            System.out.println("Kode tidak tersedia");
            return;
        }

        int[] detail = bacaDetailNim(nim);
        if (detail == null) {
            System.out.println("NIM harus 8 karakter");
            return;
        }

        cetakHasil(
            nim,
            programStudi,
            detail[0],
            detail[1]
        );
    }

    static String bacaNim(Scanner sc) {
        return sc.nextLine().trim();
    }

    static boolean validasiPanjang(String nim) {
        return nim.length() == 8;
    }

    static String cariProgramStudi(String nim) {
        String prefix = nim.substring(0, 3);

        switch (prefix) {
            case "11S":
                return "Sarjana Informatika";
            case "12S":
                return "Sarjana Sistem Informasi";
            case "13S":
                return "Sarjana Teknik Elektro";
            case "21S":
                return "Sarjana Manajemen Rekayasa";
            case "22S":
                return "Sarjana Teknik Metalurgi";
            case "31S":
                return "Sarjana Teknik Bioproses";
            case "32S":
                return "Sarjana Bioteknologi";
            case "114":
                return "Diploma 4 Teknologi Rekayasa Perangkat Lunak";
            case "113":
                return "Diploma 3 Teknologi Informasi";
            case "133":
                return "Diploma 3 Teknologi Komputer";
            default:
                return null;
        }
    }

    static int[] bacaDetailNim(String nim) {
        try {
            int angkatan =
                Integer.parseInt("20" + nim.substring(3, 5));
            int urutan =
                Integer.parseInt(nim.substring(5, 8));

            return new int[] {angkatan, urutan};
        } catch (NumberFormatException e) {
            return null;
        }
    }

    static void cetakHasil(
        String nim,
        String programStudi,
        int angkatan,
        int urutan
    ) {
        System.out.println("Informasi NIM " + nim + ": ");
        System.out.println(">> Program Studi: " + programStudi);
        System.out.println(">> Angkatan: " + angkatan);
        System.out.println(">> Urutan: " + urutan);
    }
}
