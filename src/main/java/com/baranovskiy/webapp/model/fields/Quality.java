package com.baranovskiy.webapp.model.fields;

/**
 * Enum represents three types of  product quality:
 * not graded, bad, middle and good
 *
 * @version 1.0
 * @author Baranovskiy Artem
 */
public enum Quality {

    NOT_GRADED(0), BAD(1), MIDDLE(2), GOOD(3);

    /** This parameter is stored as an integer value in the database **/
    private int qualityValue;

    Quality(int qualityValue) {
        this.qualityValue = qualityValue;
    }

    public int getValue() {
        return qualityValue;
    }

    @SuppressWarnings("unused")
    public static Quality getQuality (int id) {
        switch (id) {
            case 1: return BAD;
            case 2: return MIDDLE;
            case 3: return GOOD;
            default: return NOT_GRADED;
        }
    }

}
