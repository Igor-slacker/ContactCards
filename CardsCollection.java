package igor.contactCards;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class CardsCollection {
    private String filePath;
    private String duplicatedContactsFile = "/home/igor/duplicatedContacts";
    private List<String> duplicatedCards;
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
                    cardContent.append(string + "\n");
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
        List<String> result = new LinkedList<>();

        for (int i = 0; i < cards.size(); i++) {
            String number = cards.get(i).getPhoneNumber();
            if (!result.contains(number)) {
                for (int j = i + 1; j < cards.size(); j++) {
                    if (number.equals(cards.get(j).getPhoneNumber())) {
                        result.add(number);
                    }
                }
            }
        }

        duplicatedCards = result;
    }

    public void saveDuplicatedCards() throws IOException {
        try (FileWriter writer = new FileWriter(duplicatedContactsFile)) {
            for (String phoneNumber : duplicatedCards) {
                writer.write(phoneNumber + "\n");
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
        for (int i = 0; i < indexes.length; i++) {
            cards.remove(indexes[i] - i);
        }
    }

    public void saveCardsToFile() throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Card card : cards) {
                writer.write(card.getContent() + "\n");
            }
        }
    }


    public void removeDuplicateddCards() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Choose indexes of the cards(separated by comma) which you want to remove\n" +
                "or press enter if you don't want to remove any card");
        for (String phoneNumber : duplicatedCards) {
            printDuplicateCards(phoneNumber);

            String readSt = reader.readLine();
            if (!readSt.equals("")) {
                String[] indexesString = readSt.split(",");
                int[] indexes = new int[indexesString.length];
                for (int i = 0; i < indexes.length; i++) {
                    indexes[i] = Integer.parseInt(indexesString[i].trim());
                }

                removeSelectedCards(indexes);
            }
        }
    }
}
