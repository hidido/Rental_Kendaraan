package view;

import java.util.Scanner;

public class View {
    private final Scanner scanner = new Scanner(System.in);

    // Metode untuk menampilkan pesan ke pengguna
    public void tampilkanPesan(String pesan) {
        System.out.println(pesan);
    }

    // Metode untuk mendapatkan input integer dari pengguna
    public int getInputInt(String prompt) {
        int input;
        while (true) {
            try {
                System.out.print(prompt);
                input = Integer.parseInt(scanner.nextLine());
                return input; // Input valid, keluar dari loop
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid. Masukkan angka.");
            }
        }
    }

    // Metode untuk mendapatkan input string dari pengguna
    public String getInputString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
}
