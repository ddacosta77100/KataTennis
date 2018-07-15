package ddc.kata.service;

import ddc.kata.model.Player;
import ddc.kata.model.Point;
import ddc.kata.score.Advantage;
import ddc.kata.score.Forty;
import ddc.kata.score.Points;
import ddc.kata.score.Score;

import java.util.Optional;

/**
 * @author ddacosta 15/07/2018
 * @project KataTennis
 */
public interface ScoreSE {
    public Score newGame();
    public Score score(Score current, Player winner);
    public Score scoreSeq(Player... winners);
    public Optional<Point> incrementPoint(Point point);
    public Points pointTo(Player player, Point point, Points current);
    public Point pointFor(Player player, Points current);
    public Score scoreWhenGame(Player winner);
    public Score scoreWhenAdvantage(Advantage advantage, Player scoredPlayer);
    public Score scoreWhenDeuce(Player scoredPlayer);
    public Score scoreWhenForty(Forty forty, Player scoredPlayer);
    public Score scoreWhenPoints(Points points, Player scoredPlayer);
}
