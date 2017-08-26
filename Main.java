package igor.contactCards;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        CardsCollection mainCardsCollection = new CardsCollection();
        mainCardsCollection.gettingCards();
        mainCardsCollection.gettingDuplicateNumbers();
//        mainCardsCollection.saveDuplicatedCards();

        mainCardsCollection.removeDuplicateddCards();
        mainCardsCollection.saveCardsToFile();
        /*
        test
         */
//        System.out.println(mainCardsCollection.cards.get(0).getName()+"\n\n"+mainCardsCollection.cards.get(0).getPhoneNumber()+"\n\n"+mainCardsCollection.cards.get(0).getContent());
    }
}
