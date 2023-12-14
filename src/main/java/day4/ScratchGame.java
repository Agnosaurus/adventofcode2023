package day4;

import day3.Fragment;
import lombok.extern.slf4j.Slf4j;
import utils.FileReaderUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class ScratchGame {

    List<Card> cards = new ArrayList<>();
    List<Card> totalAdditionalCards = new ArrayList<>();


    ScratchGame(String resource){
        super();
        int result = initMap(resource);

        log.info("Sum of card value : "+result);
    }



    private int initMap(String resource){
        FileReaderUtils utils = new FileReaderUtils();
        int result=0;
        try {
            List<String> data = utils.readFile(resource);

            for (String datum : data) {
                // Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
                Card card = new Card();

                log.info("Input line  " + datum);

                String cardName = datum.split(":")[0].trim().replaceAll("Card", "");
                log.info("cardName  " + cardName);
                card.setName(Integer.parseInt(cardName.trim()));


                card.addWinningNumbers(datum.split(":")[1].split("\\|")[0]);
                card.addNumbers(datum.split(":")[1].split("\\|")[1]);

                cards.add(card);


                result += card.computeScore();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        //Applying second rule
        totalAdditionalCards.addAll(cards);

        List<Card> additionalCards = processCardWinnings(new ArrayList<>(),cards);
        totalAdditionalCards.addAll(additionalCards);

        while (additionalCards.size() > 0){
            additionalCards = processCardWinnings(new ArrayList<>(),additionalCards);
            totalAdditionalCards.addAll(additionalCards);
        }

        log.info("Nb total de cards "+totalAdditionalCards.size());

        return result;
    }

    /**
     *
     * @param additionalCards : list to store the new cards
     * @param cardsToProcess : card to process
     */
    public List<Card> processCardWinnings(List<Card> additionalCards,List<Card> cardsToProcess){

        for (Card card : cardsToProcess){
            int match = card.getMatch();
            int name = card.getName(); // Index of the card

            for ( int index = 1 ; index <= match ; index++){
                additionalCards.add(cards.get(name-1+index).copy());
            }
        }

        return additionalCards;
    }


}
