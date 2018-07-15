package ddc.kata;

import ddc.kata.model.Player;
import ddc.kata.model.PlayerKind;
import ddc.kata.model.Point;
import ddc.kata.score.*;
import ddc.kata.service.ScoreSEFcty;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Optional;

/**
 * @author ddacosta 15/07/2018
 * @project KataTennis
 */
public class ScoreTest {

    private static final Player PLAYER_ONE = new Player.Builder("Nadal", PlayerKind.PLAYER_ONE).build();
    private static final Player PLAYER_TWO = new Player.Builder("Djokovic", PlayerKind.PLAYER_TWO).build();

    @Test
    public void shouldPlayFullGameWithSeqTooManyPlayers() throws Exception {
        final Score actual = ScoreSEFcty.getScoreSe().scoreSeq(
                PLAYER_ONE,
                PLAYER_ONE,
                PLAYER_TWO,
                PLAYER_ONE,
                PLAYER_TWO,
                PLAYER_ONE,
                PLAYER_TWO,
                PLAYER_TWO,
                PLAYER_TWO);
        final Score expected = new Game(PLAYER_TWO);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void shouldPlayFullGameWithSeq() throws Exception {
        final Score actual = ScoreSEFcty.getScoreSe().scoreSeq(
                PLAYER_ONE,
                PLAYER_ONE,
                PLAYER_TWO,
                PLAYER_ONE,
                PLAYER_TWO,
                PLAYER_ONE);
        final Score expected = new Game(PLAYER_ONE);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void shouldPlayFullGame() throws Exception {
        final Score actual = Optional.of(ScoreSEFcty.getScoreSe().newGame())
                .map(current -> ScoreSEFcty.getScoreSe().score(current, PLAYER_ONE))
                .map(current -> ScoreSEFcty.getScoreSe().score(current, PLAYER_ONE))
                .map(current -> ScoreSEFcty.getScoreSe().score(current, PLAYER_TWO))
                .map(current -> ScoreSEFcty.getScoreSe().score(current, PLAYER_ONE))
                .map(current -> ScoreSEFcty.getScoreSe().score(current, PLAYER_TWO))
                .map(current -> ScoreSEFcty.getScoreSe().score(current, PLAYER_ONE))
                .orElseThrow(() -> new RuntimeException("failed"));

        final Score expected = new Game(PLAYER_ONE);
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void givenPlayer40_OtherLove_whenOtherWins_ThenScoreIsCorrect() throws Exception {
        Player player = PLAYER_ONE;
        final Forty f = new Forty(player, Point.LOVE);
        final Score actual = ScoreSEFcty.getScoreSe().scoreWhenForty(f, other(player));
        final Score expected = new Forty(player, Point.FIFTEEN);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void givenPlayer30_whenPlayerWins_ThenScoreIsCorrect() throws Exception {
        Points points = new Points(Point.LOVE, Point.LOVE);
        Player winner = PLAYER_ONE;
        final Points current = ScoreSEFcty.getScoreSe().pointTo(winner, Point.THIRTY, points);
        final Score actual = ScoreSEFcty.getScoreSe().scoreWhenPoints(current, winner);
        final Score expected = new Forty(winner, ScoreSEFcty.getScoreSe().pointFor(other(winner), current));
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void givenPlayer40_Other15_whenOtherWins_ThenScoreIsCorrect() throws Exception {
        Player player = PLAYER_ONE;
        final Forty f = new Forty(player, Point.FIFTEEN);
        final Score actual = ScoreSEFcty.getScoreSe().scoreWhenForty(f, other(f.getPlayer()));
        final Score expected = new Forty(player, Point.THIRTY);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void givenAdvantagedPlayer_WhenAdvantagedPlayerWins_ScoreIsCorrect() throws Exception {
        Player advantagedPlayer = PLAYER_ONE;
        final Score actual = ScoreSEFcty.getScoreSe().scoreWhenAdvantage(new Advantage(advantagedPlayer), advantagedPlayer);
        final Score expected = new Game(advantagedPlayer);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void givenAdvantagedPlayer_WhenOtherPlayerWins_ScoreIsCorrect() throws Exception {
        Player advantagedPlayer = PLAYER_ONE;
        final Score actual = ScoreSEFcty.getScoreSe().scoreWhenAdvantage(new Advantage(advantagedPlayer), other(advantagedPlayer));
        final Score expected = new Deuce();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void givenDeuce_WhenOtherPlayerScores_ScoreIsCorrect() throws Exception {
        Player advantagedPlayer = PLAYER_ONE;
        final Score actual = ScoreSEFcty.getScoreSe().scoreWhenDeuce(advantagedPlayer);
        final Score expected = new Advantage(advantagedPlayer);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void givenPlayer40_whenPlayerWins_ThenScoreIsCorrect() throws Exception {
        Player player = PLAYER_ONE;
        Forty forty = new Forty(player, Point.LOVE);
        final Score actual = ScoreSEFcty.getScoreSe().scoreWhenForty(forty, player);
        final Score expected = new Game(player);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void givenPlayer40_Other30_whenOtherWins_ThenScoreIsCorrect() throws Exception {
        final Forty f = new Forty(PLAYER_ONE, Point.THIRTY);
        final Score actual = ScoreSEFcty.getScoreSe().scoreWhenForty(f, other(f.getPlayer()));
        final Score expected = new Deuce();
        Assert.assertEquals(expected, actual);
    }

    public static Player other(Player player) {
        return player.getKind() == PlayerKind.PLAYER_ONE?PLAYER_TWO:PLAYER_ONE;
    }
}
