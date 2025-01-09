import java.util.ArrayList;
import java.util.List;
import java.util.Random;
class DnDCharacter {
    private final int strenght;
    private final int dexterity;
    private final int consitution;
    private final int intelligence;
    private final int wisdom;
    private final int charisma;
    
    public DnDCharacter(){
        this.strenght = ability(rollDice());
        this.dexterity = ability(rollDice());
        this.consitution = ability(rollDice());
        this.intelligence = ability(rollDice());
        this.wisdom = ability(rollDice());
        this.charisma = ability(rollDice());
    }

    int ability(List<Integer> scores) {
        int smallest = scores.get(0);
        int sum = 0;
        for (int i = 0; i < 4; i++) {
            if (scores.get(i) < smallest) {
                smallest = scores.get(i);
            }
            sum += scores.get(i);
        }
        sum -= smallest;
        return sum;
    }

    List<Integer> rollDice() {
        Random rand = new Random();
        List<Integer> diceRolls = new ArrayList<>();
        int dice1 = 1 + rand.nextInt(6);
        int dice2 = 1 + rand.nextInt(6);
        int dice3 = 1 + rand.nextInt(6);
        int dice4 = 1 + rand.nextInt(6);
        diceRolls.add(dice1);
        diceRolls.add(dice2);
        diceRolls.add(dice3);
        diceRolls.add(dice4);
        return diceRolls;
    }

    int modifier(int input) {
        return (int) Math.floor((double) (input - 10) / 2);
    }
    
    int getStrength() {
        return strenght;
    }

    int getDexterity() {
        return dexterity;
    }

    int getConstitution() {
        return consitution;
    }

    int getIntelligence() {
        return intelligence;
    }

    int getWisdom() {
        return wisdom;
    }

    int getCharisma() {
        return charisma;
    }

    int getHitpoints() {
        int initialHitPoints = 10;
        return initialHitPoints + modifier(getConstitution());
    }
}
