package ca.eshopexpress.service.impl;

import ca.eshopexpress.exception.ResourceNotFoundException;
import ca.eshopexpress.model.entity.Game;
import ca.eshopexpress.model.entity.Genre;
import ca.eshopexpress.model.entity.Platform;
import ca.eshopexpress.model.entity.Review;
import ca.eshopexpress.repository.GameRepository;
import ca.eshopexpress.repository.GenreRepository;
import ca.eshopexpress.repository.PlatformRepository;
import ca.eshopexpress.service.GameService;
import jakarta.persistence.Cacheable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

/**
 * Service implementation for managing game-related operations such as retrieving,
 * filtering, saving, deleting, and updating games.
 *
 * @author Daniel Hinbest
 * @version 1.0
 * @since 2025-05-12
 */
@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private PlatformRepository platformRepository;

    @Autowired
    private GenreRepository genreRepository;

    /**
     * Retrieves all games from the repository.
     *
     * @return a list of all games
     */
    @Override
    public List<Game> findAllGames() {
        return gameRepository.findAll();
    }

    /**
     * Finds a game by its ID.
     *
     * @param id the ID of the game
     * @return an Optional containing the found game, if any
     */
    @Override
    public Optional<Game> findById(Long id) {
        return gameRepository.findById(id);
    }

    /**
     * Retrieves a game by ID or throws an exception if not found.
     *
     * @param id the game ID
     * @return the game if found
     * @throws ResourceNotFoundException if game is not found
     */
    @Override
    public Game getGameOrThrow(Long id) {
        return gameRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Game not found with id: " + id));
    }

    /**
     * Searches for games by title using case-insensitive partial matching.
     *
     * @param title the title to search for
     * @return a list of matching games
     */
    @Override
    public List<Game> findByTitle(String title) {
        return gameRepository.findByTitleContainingIgnoreCase(title);
    }

    /**
     * Finds games available on a specific platform.
     *
     * @param platformName the name of the platform
     * @return a list of matching games
     * @throws ResourceNotFoundException if the platform is not found
     */
    @Override
    @Cacheable("gamesByPlatform")
    public List<Game> findByPlatform(String platformName) {
        Platform platform = platformRepository.findByNameContainingIgnoreCase(platformName)
                .orElseThrow(() -> new ResourceNotFoundException("Platform not found: " + platformName));
        return gameRepository.findByPlatformsContaining(platformName);
    }

    /**
     * Finds games by genre.
     *
     * @param genreName the name of the genre
     * @return a list of matching games
     * @throws ResourceNotFoundException if the genre is not found
     */
    @Override
    @Cacheable("gamesByGenre")
    public List<Game> findByGenre(String genreName) {
        Genre genre = genreRepository.findByNameContainingIgnoreCase(genreName)
                .orElseThrow(() -> new ResourceNotFoundException("Genre not found: " + genreName));
        return gameRepository.findByGenresContaining(genreName);
    }

    /**
     * Retrieves games released within the last month.
     *
     * @return a list of new release games
     */
    @Override
    public List<Game> findNewReleases() {
        LocalDate oneMonthAgo = LocalDate.now().minusMonths(1);
        return gameRepository.findByReleaseDateAfterOrderByReleaseDateDesc(oneMonthAgo);
    }

    /**
     * Retrieves games with an average rating of 4.0 or higher.
     *
     * @return a list of top-rated games
     */
    @Override
    public List<Game> findTopRated() {
        return gameRepository.findByMinimumRating(4.0);
    }

    /**
     * Saves or updates a game and clears related caches.
     *
     * @param game the game to save
     * @return the saved game
     */
    @Override
    @CacheEvict(value = {"gamesByPlatform", "gamesByGenre"}, allEntries = true)
    public Game save(Game game) {
        return gameRepository.save(game);
    }

    /**
     * Deletes a game by ID and clears related caches.
     *
     * @param id the ID of the game to delete
     */
    @Override
    @CacheEvict(value = {"gamesByPlatform", "gamesByGenre"}, allEntries = true)
    public void deleteById(Long id) {
        gameRepository.deleteById(id);
    }

    /**
     * Updates the stock quantity of a non-digital game.
     *
     * @param gameId   the game ID
     * @param quantity the amount to add or subtract from stock
     */
    @Override
    public void updateStock(Long gameId, int quantity) {
        Game game = getGameOrThrow(gameId);

        if (!game.isDigital()) {
            int newQuantity = game.getStockQuantity() + quantity;
            game.setStockQuantity(Math.max(0, newQuantity));
            gameRepository.save(game);
        }
    }

    /**
     * Calculates and updates the average rating of a game based on its reviews.
     *
     * @param gameId the ID of the game
     * @return the calculated average rating, or null if no reviews exist
     */
    @Override
    public Double calculateAverageRating(Long gameId) {
        Game game = getGameOrThrow(gameId);

        OptionalDouble average = game.getReviews().stream()
                .mapToInt(Review::getRating)
                .average();

        Double avgRating = average.isPresent() ? average.getAsDouble() : null;
        game.setAverageRating(avgRating);
        gameRepository.save(game);

        return avgRating;
    }
}
