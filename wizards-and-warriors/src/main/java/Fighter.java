class Fighter {

    boolean isVulnerable() {
        return true;
    }

    int getDamagePoints(Fighter fighter) {
        return 1;
    }
}

class Warrior extends Fighter{

    @Override
    boolean isVulnerable() {
        return false;
    }

    @Override
    public String toString() {
        return "Fighter is a Warrior";
    }

    int getDamagePoints(Fighter fighter) {
        if(fighter.isVulnerable()){
            return 10;
        }
        else{
            return 6;
        }
    }
}
class Wizard extends Fighter{
    private boolean prepared = false;
    @Override
    public String toString() {
        return "Fighter is a Wizard";
    }
    @Override
    boolean isVulnerable() {
        return !prepared;
    }

    public boolean prepareSpell() {
        prepared = true;
        return prepared;
    }

    int getDamagePoints(Fighter fighter) {
        if(prepared) {
            return 12;
        }
        else{
            return 3;
        }
    }
}