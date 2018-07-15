package ddc.kata.service;

/**
 * @author ddacosta 15/07/2018
 * @project KataTennis
 */
public class ScoreSEFcty {
    private final static ScoreSE SCORE_SE = new ScoreSEImpl();

    public static ScoreSE getScoreSe() {
        return SCORE_SE;
    }


    private ScoreSEFcty() {

    }
}
