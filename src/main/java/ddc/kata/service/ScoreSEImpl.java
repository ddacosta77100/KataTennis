package ddc.kata.service;

import ddc.kata.generic.TechnicalException;
import ddc.kata.model.Player;
import ddc.kata.model.PlayerKind;
import ddc.kata.model.Point;
import ddc.kata.score.*;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author ddacosta 15/07/2018
 * @project KataTennis
 */
public class ScoreSEImpl implements ScoreSE {

    @Override
    public Score newGame() {
        return new Points(Point.LOVE, Point.LOVE);
    }

    @Override
    public Score score(Score current, Player winner) {
        if (current instanceof Points) {
            Points obj = (Points) current;
            return scoreWhenPoints(obj, winner);
        }

        if (current instanceof Forty) {
            return scoreWhenForty((Forty) current, winner);
        }

        if (current instanceof Deuce) {
            return scoreWhenDeuce(winner);
        }

        if (current instanceof Advantage) {
            return scoreWhenAdvantage((Advantage) current, winner);
        }

        if (current instanceof Game) {
            return scoreWhenGame(winner);
        }

        throw new TechnicalException("Error score, not managed " + current);
    }

    @Override
    public Score scoreSeq(Player... winners) {
        return Stream.of(winners)
                .reduce(newGame(), this::score, (score, score2) -> score);
    }

    @Override
    public Optional<Point> incrementPoint(Point point) {
        switch (point) {
            case LOVE:
                return Optional.of(Point.FIFTEEN);
            case FIFTEEN:
                return Optional.of(Point.THIRTY);
            case THIRTY:
                return Optional.empty();
        }
        throw new TechnicalException("Point not managed");
    }

    @Override
    public Points pointTo(Player player, Point point, Points current) {
        return player.getKind() == PlayerKind.PLAYER_ONE ?
                new Points(point, current.getPlayerTwoPoint()) :
                new Points(current.getPlayerOnePoint(), point);
    }

    @Override
    public Point pointFor(Player player, Points current) {
        return player.getKind() == PlayerKind.PLAYER_ONE ? current.getPlayerOnePoint(): current.getPlayerTwoPoint();
    }

    @Override
    public Score scoreWhenGame(Player winner) {
        return new Game(winner);
    }

    @Override
    public Score scoreWhenAdvantage(Advantage advantage, Player scoredPlayer) {
        return scoredPlayer.equals(advantage.getPlayer()) ? new Game(advantage.getPlayer()) : new Deuce();
    }

    @Override
    public Score scoreWhenDeuce(Player scoredPlayer) {
        return new Advantage(scoredPlayer);
    }

    @Override
    public Score scoreWhenForty(Forty forty, Player scoredPlayer) {
        return forty.getPlayer().equals(scoredPlayer) ?
                new Game(scoredPlayer) :
                incrementPoint(forty.getOtherPlayerPoint())
                        .map(point -> new Forty(forty.getPlayer(), point))
                        .map(f -> (Score) f)
                        .orElseGet(Deuce::new);
    }

    @Override
    public Score scoreWhenPoints(Points points, Player scoredPlayer) {
        boolean playerOneScored = scoredPlayer.getKind() == PlayerKind.PLAYER_ONE;
        final Point pointToIncrease = scoredPlayer.getKind() == PlayerKind.PLAYER_ONE ? points.getPlayerOnePoint() : points.getPlayerTwoPoint();

        return incrementPoint(pointToIncrease)
                .map(point -> new Points(
                        playerOneScored ? point : points.getPlayerOnePoint(),
                        playerOneScored ? points.getPlayerTwoPoint() : point))
                .map(p -> (Score) p)
                .orElseGet(() -> new Forty(scoredPlayer, playerOneScored ? points.getPlayerTwoPoint() : points.getPlayerOnePoint()));
    }
}
