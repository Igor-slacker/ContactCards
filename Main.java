package igor.contactCards;

import java.io.IOException;

public class Main {
    public static void processDuplicatedCards(CardsCollection cardsCollection) throws IOException {
        cardsCollection.gettingCards();
        cardsCollection.gettingDuplicateNumbers();
//        mainCardsCollection.saveDuplicatedCards();

        cardsCollection.removeDuplicateddCards();
        cardsCollection.saveCardsToFile();
        /*
        test
         */
//        System.out.println(mainCardsCollection.cards.get(0).getName()+"\n\n"+mainCardsCollection.cards.get(0).getPhoneNumber()+"\n\n"+mainCardsCollection.cards.get(0).getContent());

    }

    public static void main(String[] args) throws IOException {
        CardsCollection mainCardsCollection = new CardsCollection();
        processDuplicatedCards(mainCardsCollection);


        }
}
