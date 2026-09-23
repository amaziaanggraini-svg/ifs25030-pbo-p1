import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] list = parseInput(sc);

        // Jika input kosong, LANGSUNG keluar tanpa mencetak output apapun
        if (list.length == 0) {
            sc.close();
            return;
        }

        prosesDanCetak(list);

        sc.close();
    }

    /**
     * Membaca input angka dari Scanner hingga sentinel '---' atau EOF.
     */
    private static int[] parseInput(Scanner sc) {
        int capacity = 10;
        int[] temp = new int[capacity];
        int size = 0;

        while (sc.hasNext()) {
            if (sc.hasNextInt()) {
                if (size == capacity) {
                    capacity *= 2;
                    int[] newTemp = new int[capacity];
                    System.arraycopy(temp, 0, newTemp, 0, size);
                    temp = newTemp;
                }
                temp[size++] = sc.nextInt();
            } else {
                String token = sc.next();
                if (token.equals("---")) {
                    break;
                }
            }
        }

        int[] result = new int[size];
        System.arraycopy(temp, 0, result, 0, size);
        return result;
    }

    /**
     * Menghitung frekuensi kemunculan setiap angka unik.
     */
    private static int hitungFrekuensi(int[] list, int[] uniqueNums, int[] freqs) {
        int uniqueCount = 0;

        for (int num : list) {
            int index = -1;
            for (int j = 0; j < uniqueCount; j++) {
                if (uniqueNums[j] == num) {
                    index = j;
                    break;
                }
            }

            if (index != -1) {
                freqs[index]++;
            } else {
                uniqueNums[uniqueCount] = num;
                freqs[uniqueCount] = 1;
                uniqueCount++;
            }
        }

        return uniqueCount;
    }

    /**
     * Mengolah statistik nilai (min, max, terbanyak, tersedikit) dan mencetak hasil.
     */
    private static void prosesDanCetak(int[] list) {
        int size = list.length;

        // 1. Cari Nilai Min dan Max
        int min = list[0];
        int max = list[0];
        for (int i = 1; i < size; i++) {
            if (list[i] < min) min = list[i];
            if (list[i] > max) max = list[i];
        }

        // 2. Hitung Frekuensi
        int[] uniqueNums = new int[size];
        int[] freqs = new int[size];
        int uniqueCount = hitungFrekuensi(list, uniqueNums, freqs);

        // 3. Cari Nilai Terbanyak dan Tersedikit
        int maxFreq = -1;
        int minFreq = Integer.MAX_VALUE;
        int mostFreqNum = list[0];
        int leastFreqNum = list[0];

        for (int i = 0; i < uniqueCount; i++) {
            int num = uniqueNums[i];
            int count = freqs[i];

            if (count > maxFreq) {
                maxFreq = count;
                mostFreqNum = num;
            } else if (count == maxFreq) {
                mostFreqNum = Math.max(mostFreqNum, num);
            }

            if (count < minFreq) {
                minFreq = count;
                leastFreqNum = num;
            } else if (count == minFreq) {
                leastFreqNum = Math.min(leastFreqNum, num);
            }
        }

        // 4. Hitung Frekuensi Min dan Max
        int countMax = 0;
        int countMin = 0;
        for (int i = 0; i < uniqueCount; i++) {
            if (uniqueNums[i] == max) countMax = freqs[i];
            if (uniqueNums[i] == min) countMin = freqs[i];
        }

        long sumTertinggi = (long) max * countMax;
        long sumTerendah = (long) min * countMin;

        // 5. Cetak Output Hasil
        System.out.println("Tertinggi: " + max);
        System.out.println("Terendah: " + min);
        System.out.println("Terbanyak: " + mostFreqNum + " (" + maxFreq + "x)");
        System.out.println("Tersedikit: " + leastFreqNum + " (" + minFreq + "x)");
        System.out.println("Jumlah Tertinggi: " + max + " * " + countMax + " = " + sumTertinggi);
        System.out.println("Jumlah Terendah: " + min + " * " + countMin + " = " + sumTerendah);
    }
}