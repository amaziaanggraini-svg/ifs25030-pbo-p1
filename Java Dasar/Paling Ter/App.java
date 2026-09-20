import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Menggunakan array dinamis manual untuk menggantikan ArrayList
        int capacity = 10;
        int[] list = new int[capacity];
        int size = 0;

        while (sc.hasNext()) {
            if (sc.hasNextInt()) {
                if (size == capacity) {
                    capacity *= 2;
                    int[] temp = new int[capacity];
                    System.arraycopy(list, 0, temp, 0, size);
                    list = temp;
                }
                list[size++] = sc.nextInt();
            } else {
                String token = sc.next();
                if (token.equals("---")) {
                    break;
                }
            }
        }

        if (size == 0) {
            System.out.println("Data kosong");
            sc.close();
            return;
        }

        // Cari min dan max
        int min = list[0];
        int max = list[0];
        for (int i = 1; i < size; i++) {
            if (list[i] < min) min = list[i];
            if (list[i] > max) max = list[i];
        }

        // Hitung frekuensi setiap angka unik (menggantikan HashMap)
        int[] uniqueNums = new int[size];
        int[] freqs = new int[size];
        int uniqueCount = 0;

        for (int i = 0; i < size; i++) {
            int num = list[i];
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

        // Cari angka dengan frekuensi terbanyak dan tersedikit
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

        // Cari jumlah kemunculan min dan max
        int countMax = 0;
        int countMin = 0;
        for (int i = 0; i < uniqueCount; i++) {
            if (uniqueNums[i] == max) countMax = freqs[i];
            if (uniqueNums[i] == min) countMin = freqs[i];
        }

        long sumTertinggi = (long) max * countMax;
        long sumTerendah = (long) min * countMin;

        // Output Resmi sesuai Test Case
        System.out.println("Tertinggi: " + max);
        System.out.println("Terendah: " + min);
        System.out.println("Terbanyak: " + mostFreqNum + " (" + maxFreq + "x)");
        System.out.println("Tersedikit: " + leastFreqNum + " (" + minFreq + "x)");
        System.out.println("Jumlah Tertinggi: " + max + " * " + countMax + " = " + sumTertinggi);
        System.out.println("Jumlah Terendah: " + min + " * " + countMin + " = " + sumTerendah);

        sc.close();
    }
}