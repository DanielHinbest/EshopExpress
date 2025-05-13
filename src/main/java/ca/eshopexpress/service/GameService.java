package ca.eshopexpress.service;

import ca.eshopexpress.model.entity.Game;
import ca.eshopexpress.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

/**
 * Service interface defining operations related to games.
 */
public interface GameService {

    /**
     * Retrieves all games.
     *
     * @return a list of all games
     */
    List<Game> findAllGames();

    /**
     * Finds a game by its ID.
     *
     * @param id the ID of the game
     * @return an Optional containing the game if found, or empty otherwise
     */
    Optional<Game> findById(Long id);

    /**
     * Finds a game by ID or throws an exception if not found.
     *
     * @param id the ID of the game
     * @return the game with the specified ID
     * @throws ResourceNotFoundException if the game does not exist
     */
    Game getGameOrThrow(Long id);

    /**
     * Searches for games by title (case-insensitive, partial match).
     *
     * @param title the title to search
     * @return a list of matching games
     */
    List<Game> findByTitle(String title);

    /**
     * Retrieves games associated with a specific platform.
     *
     * @param platformName the platform name (e.g., "PC", "PlayStation 5")
     * @return a list of games available on the platform
     * @throws ResourceNotFoundException if the platform is not found
     */
    List<Game> findByPlatform(String platformName);

    /**
     * Retrieves games by genre.
     *
     * @param genreName the genre name (e.g., "Action", "RPG")
     * @return a list of games in the specified genre
     * @throws ResourceNotFoundException if the genre is not found
     */
    List<Game> findByGenre(String genreName);

    /**
     * Retrieves games released within the past month, sorted by newest first.
     *
     * @return a list of new releases
     */
    List<Game> findNewReleases();

    /**
     * Retrieves games with average rating >= 4.0.
     *
     * @return a list of top-rated games
     */
    List<Game> findTopRated();

    /**
     * Saves or updates a game entity.
     *
     * @param game the game to save or update
     * @return the saved game
     */
    Game save(Game game);

    /**
     * Deletes a game by its ID.
     *
     * @param id the ID of the game to delete
     */
    void deleteById(Long id);

    /**
     * Updates stock quantity for physical games.
     *
     * @param gameId the ID of the game
     * @param quantity the amount to adjust (positive or negative)
     */
    void updateStock(Long gameId, int quantity);

    /**
     * Calculates and updates the average rating for a game.
     *
     * @param gameId the ID of the game
     * @return the new average rating, or null if no reviews exist
     */
    Double calculateAverageRating(Long gameId);
}