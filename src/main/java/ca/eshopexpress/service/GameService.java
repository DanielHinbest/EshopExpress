package ca.eshopexpress.service;

import ca.eshopexpress.model.entity.Game;

import java.util.List;
import java.util.Optional;

/**
 * Service interface defining operations related to games.
 *
 * @author Daniel Hinbest
 * @version 1.0
 * @since 2025-05-12
 */
public interface GameService {

    List<Game> findAllGames();

    Optional<Game> findById(Long id);

    Game getGameOrThrow(Long id);

    List<Game> findByTitle(String title);

    List<Game> findByPlatform(String platformName);

    List<Game> findByGenre(String genreName);

    List<Game> findNewReleases();

    List<Game> findTopRated();

    Game save(Game game);

    void deleteById(Long id);

    void updateStock(Long gameId, int quantity);

    Double calculateAverageRating(Long gameId);
}
