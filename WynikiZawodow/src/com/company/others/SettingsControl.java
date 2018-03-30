package com.company.others;

public class SettingsControl {
    private final int numberOfTournaments;
    private final int failuresAcceptableCount;
    private final int bonusPointsForChampionShip;
    public SettingsControl()
    {
        numberOfTournaments=4;
        failuresAcceptableCount=1;
        bonusPointsForChampionShip=5;
    }

    public int getNumberOfTournaments() {
        return numberOfTournaments;
    }

    public int getFailuresAcceptableCount() {
        return failuresAcceptableCount;
    }

    public int getBonusPointsForChampionShip() {
        return bonusPointsForChampionShip;
    }
}
