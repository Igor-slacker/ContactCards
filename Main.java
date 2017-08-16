package igor.contactCards;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Main {
    private String filePath;
    private String duplicatedContactsFile = "/home/igor/duplicatedContacts";
    private Map<String, Integer> duplicatedCards;
    private List<Card> cards = new LinkedList<>();

    public void gettingCards() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Input path to contacts file\n" +
                "Be careful! This file will be override");
        filePath = reader.readLine();
        try (BufferedReader contacts = new BufferedReader(new FileReader(filePath))) {
            StringBuilder cardContent = new StringBuilder();
            String phoneNumber = null;
            String name = null;
            while (contacts.ready()) {
                String string = contacts.readLine();
                if (!string.equals("")) {
                    if (string.startsWith("TEL;"))
                        phoneNumber = string.substring(string.length() - 10);
                    if (string.startsWith("N:"))
                        name = string.substring(2);
                    cardContent.append(string+"\n");
                } else {
                    cards.add(new Card(phoneNumber, name, cardContent.toString()));
                    cardContent.setLength(0);
                    phoneNumber = null;
                    name = null;
                }
            }
        }
    }

    public void gettingDuplicateNumbers() throws IOException {
        Map<String, Integer> result = new HashMap<>();

        for (int i = 0; i < cards.size(); i++) {
            String number = cards.get(i).getPhoneNumber();
            if (!result.containsKey(number)) {
                for (int j = i + 1; j < cards.size(); j++) {
                    if (number.equals(cards.get(j).getPhoneNumber())) {
                        int quantity = result.containsKey(number) ? result.get(number) + 1 : 2;
                        result.put(number, quantity);
                    }
                }
            }
        }

        duplicatedCards = result;
    }

    public void saveDuplicatedCards() throws IOException {
        try (FileWriter writer = new FileWriter(duplicatedContactsFile)) {
            for (Map.Entry<String, Integer> pair : duplicatedCards.entrySet()) {
                writer.write(pair.getKey() + " : " + pair.getValue() + "\n");
            }
        }
    }

    public void printDuplicateCards(String phoneNumber) {
        for (Card card : cards) {
            if (card.getPhoneNumber().equals(phoneNumber))
                System.out.printf("%d :   %s \n", cards.indexOf(card), card.getName());
        }
    }

    public void removeSelectedCards(int... indexes) {
        for (int index : indexes) {
            cards.remove(index);
        }
    }

    public void saveCardsToFile() throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Card card : cards) {
                writer.write(card.getContent());
            }
        }
    }

    public Map<String, Integer> getDuplicatedCards() {
        return duplicatedCards;
    }

    private void removingProcess() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Choose indexes of the cards(separated by comma) which you want to remove");
        for (Map.Entry<String, Integer> pair :
                getDuplicatedCards().entrySet()) {
            printDuplicateCards(pair.getKey());

            String[] indexesString = reader.readLine().split(",");
            int[] indexes = new int[indexesString.length];
            for (int i = 0; i < indexes.length; i++) {
                indexes[i] = Integer.parseInt(indexesString[i].trim());
            }

            removeSelectedCards(indexes);
        }
    }

    public static void main(String[] args) throws IOException {
        Main app = new Main();
        app.gettingCards();
        app.gettingDuplicateNumbers();
//        app.saveDuplicatedCards();

        app.removingProcess();
//        app.saveCardsToFile();
        /*
        test
         */
//        System.out.println(app.cards.get(0).getName()+"\n\n"+app.cards.get(0).getPhoneNumber()+"\n\n"+app.cards.get(0).getContent());
    }
}
