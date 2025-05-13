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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

/**
 * Service implementation for managing game-related business logic.
 * <p>
 * Provides methods to retrieve, search, create, update, and delete games,
 * as well as platform/genre filtering, new release detection, and rating aggregation.
 * </p>
 *
 * @author Daniel Hinbest
 * @version 1.1
 * @since 2025-05-13
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
     * Retrieves all games.
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
     * @return an Optional containing the game if found, or empty otherwise
     */
    @Override
    public Optional<Game> findById(Long id) {
        return gameRepository.findById(id);
    }

    /**
     * Finds a game by ID or throws an exception if not found.
     *
     * @param id the ID of the game
     * @return the game with the specified ID
     * @throws ResourceNotFoundException if the game does not exist
     */
    @Override
    public Game getGameOrThrow(Long id) {
        return gameRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Game not found with id: " + id));
    }

    /**
     * Searches for games by title (case-insensitive, partial match).
     *
     * @param title the title to search
     * @return a list of matching games
     */
    @Override
    public List<Game> findByTitle(String title) {
        return gameRepository.findByTitleContainingIgnoreCase(title);
    }

    /**
     * Retrieves games associated with a specific platform.
     *
     * @param platformName the platform name (e.g., "PC", "PlayStation 5")
     * @return a list of games available on the platform
     * @throws ResourceNotFoundException if the platform is not found
     */
    @Override
    @Cacheable(value = "gamesByPlatform", key = "#platformName.toLowerCase()")
    public List<Game> findByPlatform(String platformName) {
        Optional<Platform> platform = platformRepository.findByNameContainingIgnoreCase(platformName);
        if (platform.isPresent()) {
            return gameRepository.findByPlatformsContaining(platform.get());
        } else {
            throw new ResourceNotFoundException("Platform not found: " + platformName);
        }
    }

    /**
     * Retrieves games by genre.
     *
     * @param genreName the genre name (e.g., "Action", "RPG")
     * @return a list of games in the specified genre
     * @throws ResourceNotFoundException if the genre is not found
     */
    @Override
    @Cacheable(value = "gamesByGenre", key = "#genreName.toLowerCase()")
    public List<Game> findByGenre(String genreName) {
        Optional<Genre> genre = genreRepository.findByNameContainingIgnoreCase(genreName);
        if (genre.isPresent()) {
            return gameRepository.findByGenresContaining(genre.get());
        } else {
            throw new ResourceNotFoundException("Genre not found: " + genreName);
        }
    }

    /**
     * Retrieves games released within the past month, sorted by newest first.
     *
     * @return a list of new releases
     */
    @Override
    public List<Game> findNewReleases() {
        LocalDate oneMonthAgo = LocalDate.now().minusMonths(1);
        return gameRepository.findByReleaseDateAfterOrderByReleaseDateDesc(oneMonthAgo);
    }

    /**
     * Retrieves games with average rating >= 4.0.
     *
     * @return a list of top-rated games
     */
    @Override
    public List<Game> findTopRated() {
        return gameRepository.findByMinimumRating(4.0);
    }

    /**
     * Saves or updates a game entity.
     * Clears platform/genre caches to ensure data consistency.
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
     * Deletes a game by its ID.
     * Clears platform/genre caches to ensure data consistency.
     *
     * @param id the ID of the game to delete
     */
    @Override
    @CacheEvict(value = {"gamesByPlatform", "gamesByGenre"}, allEntries = true)
    public void deleteById(Long id) {
        gameRepository.deleteById(id);
    }

    /**
     * Updates stock quantity for physical games.
     * Ignores digital games since they are not stock-limited.
     *
     * @param gameId the ID of the game
     * @param quantity the amount to adjust (positive or negative)
     * @throws ResourceNotFoundException if the game does not exist
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
     * Calculates and updates the average rating for a game.
     *
     * @param gameId the ID of the game
     * @return the new average rating, or null if no reviews exist
     * @throws ResourceNotFoundException if the game does not exist
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
