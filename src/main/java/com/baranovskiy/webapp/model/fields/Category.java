package com.baranovskiy.webapp.model.fields;

/**
 * Enum represents 6 product categories: fruits, vegetables, meat, alcohol
 * drinks and other
 *
 * @version 1.0
 * @author Baranovskiy Artem
 */
public enum Category {

    FRUITS(0), VEGETABLES(1), MEAT(2), ALCOHOL(3), DRINKS(4), OTHER(5);

    /** This parameter is stored as an integer value in the database **/
    private int value;

    Category(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @SuppressWarnings("unused")
    public static Category getCategory (int id) {
        switch (id) {
            case 0: return FRUITS;
            case 1: return VEGETABLES;
            case 2: return MEAT;
            case 3: return ALCOHOL;
            case 4: return DRINKS;
            default: return OTHER;
        }
    }

}
