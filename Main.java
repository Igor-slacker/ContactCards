package igor.contactCards;

import java.io.IOException;

public class Main {
    private static CardsCollection mainCardsCollection;
    private static CardsCollection updatedCardsCollection;

    public static void processMainCardsCollection() throws IOException {
        mainCardsCollection.gettingCards();
        mainCardsCollection.gettingDuplicateNumbers();
//        mainCardsCollection.saveDuplicatedCards();

        mainCardsCollection.removeDuplicateddCards();
        mainCardsCollection.saveCardsToFile();
    }

    public static void processUpdatedCardsCollection() throws IOException {
        updatedCardsCollection.gettingCards();
        updatedCardsCollection.gettingDuplicateNumbers(mainCardsCollection.getCards());
//        updatedCardsCollection.saveDuplicatedCards();
        updatedCardsCollection.removeAllDuplicateddCards();
        updatedCardsCollection.saveCardsToFile();
    }

    public static void main(String[] args) throws IOException {
        mainCardsCollection = new CardsCollection();
        processMainCardsCollection();

        updatedCardsCollection = new CardsCollection();
        processUpdatedCardsCollection();
    }
}
