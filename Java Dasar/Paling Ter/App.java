import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> kataList = bacaInput(scanner);
        scanner.close();

        if (kataList.isEmpty()) {
            return; // Tidak mencetak apapun jika input kosong
        }

        prosesDanCetakPalingTer(kataList);
    }

    private static List<String> bacaInput(Scanner scanner) {
        List<String> list = new ArrayList<>();
        while (scanner.hasNext()) {
            String kata = scanner.next();
            if (kata.equalsIgnoreCase("---")) {
                break;
            }
            list.add(kata);
        }
        return list;
    }

    private static void prosesDanCetakPalingTer(List<String> kataList) {
        int maxLen = 0;
        int minLen = Integer.MAX_VALUE;

        for (String k : kataList) {
            int len = k.length();
            if (len > maxLen) maxLen = len;
            if (len < minLen) minLen = len;
        }

        List<String> terpanjang = new ArrayList<>();
        List<String> terpendek = new ArrayList<>();

        for (String k : kataList) {
            if (k.length() == maxLen && !terpanjang.contains(k)) {
                terpanjang.add(k);
            }
            if (k.length() == minLen && !terpendek.contains(k)) {
                terpendek.add(k);
            }
        }

        Collections.sort(terpanjang);
        Collections.sort(terpendek);

        cetakHasil(terpanjang, terpendek);
    }

    private static void cetakHasil(List<String> terpanjang, List<String> terpendek) {
        for (String k : terpanjang) {
            System.out.println(k);
        }
        for (String k : terpendek) {
            System.out.println(k);
        }
    }
}