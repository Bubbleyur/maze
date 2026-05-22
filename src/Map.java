import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Map {
    public static int[][] pembuatanMap(String namaFile){
        ArrayList<int[]> listMap = new ArrayList<>();

        try {
            File file = new File(namaFile);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String baris = scanner.nextLine().trim();
                // Mencegah error jika ada baris kosong (enter ekstra) di dalam file txt
                if (baris.isEmpty()) {
                    continue;
                }
                // Memecah baris berdasarkan spasi
                String[] angkaString = baris.split("\\s+");
                // Buat array 1D seukuran jumlah angka di baris ini
                int[] barisAngka = new int[angkaString.length];
                // Masukkan teks angka ke dalam array integer
                for (int i = 0; i < angkaString.length; i++) {
                    barisAngka[i] = Integer.parseInt(angkaString[i]);
                }
                // Tambahkan baris yang sudah jadi angka ini ke dalam ArrayList
                listMap.add(barisAngka);
            }
            scanner.close();
            System.out.println("Berhasil memuat map dinamis: " + namaFile);
            System.out.println("Ukuran Map: " + listMap.size() + " baris x " + listMap.get(0).length + " kolom");
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: File " + namaFile + " tidak ditemukan!");
        } catch (NumberFormatException e) {
            System.out.println("ERROR: File txt mengandung huruf atau simbol yang bukan angka!");
        }
        // Konversi ArrayList<int[]> kembali menjadi array 2D standar (int[][])
        // Parameter new int[0][] adalah cara trik Java untuk memberitahu tipe kembaliannya
        return listMap.toArray(new int[0][]);
    }
}
