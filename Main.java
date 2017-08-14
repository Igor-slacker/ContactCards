package igor.contactCards;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Main {
    private Map<String, Integer> duplicatedCards;
    private InputStream contacts;

    public void getingContacts() throws IOException {
        System.out.println("Input path to contacts file");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            contacts = new FileInputStream(reader.readLine());
        }
    }

    public void getingDuplicateNumbers() throws IOException {
        List<String> numbers = new LinkedList<>();
        Map<String, Integer> result = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(contacts))) {
            while (reader.ready()) {
                String s = reader.readLine();
                if (s.startsWith("TEL;CELL")) {
                    numbers.add(s.substring(s.length() - 10));
                }
            }
        }

        for (int i = 0; i < numbers.size(); i++) {
            String number = numbers.get(i);
            if (!result.containsKey(number)) {
                for (int j = i + 1; j < numbers.size(); j++) {
                    if (number.equals(numbers.get(j))) {
                        int quantity = result.containsKey(number) ? result.get(number) + 1 : 2;
                        result.put(number, quantity);
                    }
                }
            }
        }

        duplicatedCards = result;
    }

    public void printDuplicatedCards() {
        for (Map.Entry<String, Integer> pair : duplicatedCards.entrySet()) {
            System.out.printf("%s : %d\n", pair.getKey(), pair.getValue());
        }
    }

    public void saveDuplicatedCards() throws IOException {
        try (FileWriter writer = new FileWriter("/home/igor/duplicatedContacts")) {
            for (Map.Entry<String, Integer> pair : duplicatedCards.entrySet()) {
                writer.write(pair.getKey() + " : " + pair.getValue() + "\n");
            }
        }
    }

    public void close() throws IOException {
        contacts.close();
    }

    public static void main(String[] args) throws IOException {
        Main app = new Main();
        app.getingContacts();
        app.getingDuplicateNumbers();
        app.saveDuplicatedCards();
        app.printDuplicatedCards();
        app.close();
    }
}
