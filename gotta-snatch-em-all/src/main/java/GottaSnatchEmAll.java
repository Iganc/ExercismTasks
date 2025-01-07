import java.util.HashSet;
import java.util.List;
import java.util.Set;

class GottaSnatchEmAll {

    static Set<String> newCollection(List<String> cards) {
        Set<String> setCards = new HashSet<String>(cards);
        setCards.addAll(cards);
        return setCards;
    }

    static boolean addCard(String card, Set<String> collection) {
        if(collection.contains(card)){
            return false;
        }
        else{
            collection.add(card);
        }
        return collection.contains(card);
    }

    static boolean canTrade(Set<String> myCollection, Set<String> theirCollection) {
        if(myCollection.isEmpty() || theirCollection.isEmpty()){
            return false;
        }
        else if (myCollection.containsAll(theirCollection)){
            return false;
        } else if (theirCollection.containsAll(myCollection)) {
            return false;
        }
        else{
            return true;
        }

    }

    static Set<String> commonCards(List<Set<String>> collections) {
        Set<String> result = new HashSet<>(collections.get(0));
        for (Set<String> collection : collections) {
            result.retainAll(collection);
        }
        return result;
    }

    static Set<String> allCards(List<Set<String>> collections) {
        Set<String> allcards = new HashSet<>();
        for (Set<String> collection : collections){
            allcards.addAll(collection);
        }
        return allcards;
    }
}
