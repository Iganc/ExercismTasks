public class GameMaster {

    public String describe(Character character){
        return "You're a level "+ character.getLevel() +" "+ character.getCharacterClass()+" with "+character.getHitPoints()+" hit points.";
    }

    public String describe(Destination destination){
        return "You've arrived at "+destination.getName()+", which has "+destination.getInhabitants()+" inhabitants.";
    }

    public String describe(TravelMethod travelMethod){
        switch(travelMethod){
            case WALKING -> {
                return "You're traveling to your destination by walking.";
            }
            case HORSEBACK -> {
                return "You're traveling to your destination on horseback.";
            }
        }
        return "";
    }

    public String describe(Character character, Destination destination, TravelMethod travelMethod) {
        String charac = "You're a level "+ character.getLevel() +" "+ character.getCharacterClass()+" with "+character.getHitPoints()+" hit points. ";
        String dest = "You've arrived at "+destination.getName()+", which has "+destination.getInhabitants()+" inhabitants.";
        String meth;
        switch (travelMethod) {
            case WALKING -> {
                meth = "You're traveling to your destination by walking. ";
            }
            case HORSEBACK -> {
                meth = "You're traveling to your destination on horseback. ";
            }
            default -> throw new IllegalStateException("Unexpected value: " + travelMethod);
        }
        return charac + meth + dest;
    }
    public String describe(Character character, Destination destination) {
        String charac = "You're a level "+ character.getLevel() +" "+ character.getCharacterClass()+" with "+character.getHitPoints()+" hit points. ";
        String dest = "You've arrived at "+destination.getName()+", which has "+destination.getInhabitants()+" inhabitants.";
        return charac + "You're traveling to your destination by walking. " + dest;
    }
}
