class AnnalynsInfiltration {
    public static boolean canFastAttack(boolean knightIsAwake) {
        return !knightIsAwake;
    }

    public static boolean canSpy(boolean knightIsAwake, boolean archerIsAwake, boolean prisonerIsAwake) {
        boolean cond = knightIsAwake || archerIsAwake || prisonerIsAwake;
        return cond;
    }

    public static boolean canSignalPrisoner(boolean archerIsAwake, boolean prisonerIsAwake) {
        boolean cond = !archerIsAwake && prisonerIsAwake;
        return cond;
    }

    public static boolean canFreePrisoner(boolean knightIsAwake, boolean archerIsAwake, boolean prisonerIsAwake, boolean petDogIsPresent) {
        boolean dog_cond = petDogIsPresent && !archerIsAwake;
        boolean sl_cond = !petDogIsPresent && prisonerIsAwake && !knightIsAwake && !archerIsAwake;
        return dog_cond || sl_cond;
    }
}