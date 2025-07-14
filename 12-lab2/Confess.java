import java.util.Scanner;

public class Confess {
    /**
     * Convert binary string into ASCII Character
     *
     * @param ascii Binary string of an ASCII Character
     * @return ASCII Character
     */
    public static char binaryAscii(String ascii) {
        return ((char) Integer.parseInt(ascii, 2));
    }

    /**
     * Parse/decrypt confess string into readable message.
     *
     * @param originalConfess Original hashed message
     * @param parsedConfess Parsed original message
     * @param strIndex Index of accessing the string
     * @param strBinary Current binary string
     * @return Confess string that has been decrypted into readable message
     */
    public static String parseConfess(String originalConfess, String parsedConfess, int strIndex, String strBinary) {
        if (strIndex == originalConfess.length())
            return parsedConfess + (strBinary.isEmpty() ? "" : binaryAscii(strBinary));

        char token = originalConfess.charAt(strIndex);
        // Append token to strBinary if token is 0 or 1
        if (token == '0' || token == '1')
            return parseConfess(
                    originalConfess,
                    parsedConfess,
                    ++strIndex,
                    strBinary + token);

        // Skip to the next token/char if it's any char other than 0 and 1
        // and the strBinary is empty
        if (Character.isDigit(token) || strBinary.isEmpty())
            return parseConfess(
                    originalConfess,
                    parsedConfess,
                    ++strIndex,
                    strBinary);

        // Skip to the next token/char
        return parseConfess(
                originalConfess,
                parsedConfess + binaryAscii(strBinary),
                ++strIndex,
                "");
    }

    public static void main(String[] args) {
        String confessInput;
        Scanner input = new Scanner(System.in);

        System.out.println("Masukkan confession dalam bentuk kode (ketik 'selesai' untuk keluar):");
        while (true) {
            confessInput = input.nextLine();

            // Break the loop if 'selesai' is omitted
            if (confessInput.equals("selesai"))
                break;

            String confessOutput = parseConfess(confessInput, "", 0, "");
            System.out.println(confessOutput);
        }

        input.close();
    }
}
