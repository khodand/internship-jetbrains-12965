import java.util.Arrays;
import java.util.Scanner;

public class AlphabetOrder {

    final int alphabetLength = 26;
    char[][] names;
    int[][] d;

    AlphabetOrder(int namesNumber) {
        names = new char[namesNumber][100];
        d = new int[alphabetLength][alphabetLength];
    }

    void readNames(Scanner sc) {
        for (int i = 0; i < names.length; ++i) {
            names[i] = sc.nextLine().toCharArray();
        }
    }

    char[] getValidAlphabet() {
        int n = names.length;

        for (int i = 1; i < n; i++) {
            int j = 0;
            while (j < names[i].length && j < names[i - 1].length) {
                if (names[i][j] != names[i - 1][j]) {
                    break;
                }
                j++;
            }

            if (j < names[i].length && j < names[i - 1].length) {
                d[names[i][j] - 'a'][names[i - 1][j] - 'a'] = 1;
            }

            if (j >= names[i].length && j < names[i - 1].length) {
                return "Impossible".toCharArray();
            }
        }

        char[] tmpAlphabet = new char[alphabetLength];
        for (int i = 0; i < alphabetLength; i++) {
            tmpAlphabet[i] = (char)(i + 'a');
        }

        for (int i = 0; i < alphabetLength; i++) {
            for (int j = 0; j < alphabetLength; j++) {
                for (int k = j + 1; k < alphabetLength; k++) {
                    if (d[tmpAlphabet[j] - 'a'][tmpAlphabet[k] - 'a'] == 1) {
                        char t = tmpAlphabet[j];
                        tmpAlphabet[j] = tmpAlphabet[k];
                        tmpAlphabet[k] = t;
                    }
                }
            }
        }

        for (int j = 0; j < alphabetLength; j++) {
            for (int k = j + 1; k < alphabetLength; k++) {
                if (d[tmpAlphabet[j] - 'a'][tmpAlphabet[k] - 'a'] == 1) {
                    return "Impossible".toCharArray();
                }
            }
        }
        return tmpAlphabet;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();

        AlphabetOrder solver = new AlphabetOrder(n);
        solver.readNames(scanner);

        System.out.println(solver.getValidAlphabet());
    }
}
