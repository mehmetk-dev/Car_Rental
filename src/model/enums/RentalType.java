package model.enums;

public enum RentalType {
    HOURLY("Saatlik",1),
    DAILY("Günlük",24),
    WEEKLY("Haftalık",24 * 7),
    MONTHLY("Aylık", 24 * 30);

    private final String displayName;
    private final int hourMultiplier;

    RentalType(String displayName, int hourMultiplier) {
        this.displayName = displayName;
        this.hourMultiplier = hourMultiplier;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getHourMultiplier() {
        return hourMultiplier;
    }
}
