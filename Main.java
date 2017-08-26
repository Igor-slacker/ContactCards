package igor.contactCards;

import java.io.IOException;

public class Main {
    public static void processMainCardsCollection(CardsCollection cardsCollection) throws IOException {
        cardsCollection.gettingCards();
        cardsCollection.gettingDuplicateNumbers();
//        cardsCollection.saveDuplicatedCards();

        cardsCollection.removeDuplicateddCards();
        cardsCollection.saveCardsToFile();
    }

    private static void processUpdatedCardsCollection(CardsCollection mainCardsCollection, CardsCollection updatedCardsCollection) throws IOException {
        updatedCardsCollection.gettingCards();
        updatedCardsCollection.gettingDuplicateNumbers(mainCardsCollection.getCards());
//        updatedCardsCollection.saveDuplicatedCards();
        updatedCardsCollection.removeAllDuplicateddCards();
        updatedCardsCollection.saveCardsToFile();
    }

    public static void main(String[] args) throws IOException {
        CardsCollection mainCardsCollection = new CardsCollection();
        processMainCardsCollection(mainCardsCollection);

        CardsCollection updatedCardsCollection = new CardsCollection();
        processUpdatedCardsCollection(mainCardsCollection,updatedCardsCollection);
    }
}
