package ca.eshopexpress.repository;

import ca.eshopexpress.model.entity.Game;
import ca.eshopexpress.model.entity.Genre;
import ca.eshopexpress.model.entity.Platform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * Repository interface for accessing and managing {@link Game} entities in the database.
 * <p>
 * Provides CRUD operations and various query methods for retrieving games by title,
 * platform, genre, release date, rating, and for generating recommendations.
 * </p>
 *
 * @author Daniel Hinbest
 * @version 1.0
 * @since 2025-05-10
 */
@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    /**
     * Retrieves a list of games whose titles contain the specified string,
     * ignoring case.
     *
     * @param title the title substring to search for
     * @return a list of games with matching titles
     */
    List<Game> findByTitleContainingIgnoreCase(String title);

    /**
     * Retrieves a list of games that are available on the specified platform name.
     *
     * @param platform the name of the platform
     * @return a list of games available on the specified platform
     */
    List<Game> findByPlatformsContaining(String platform);

    /**
     * Retrieves a list of games that belong to the specified genre name.
     *
     * @param genre the name of the genre
     * @return a list of games in the specified genre
     */
    List<Game> findByGenresContaining(String genre);

    /**
     * Retrieves a list of games released after the specified date,
     * sorted by release date in descending order.
     *
     * @param date the cutoff release date
     * @return a list of recently released games
     */
    List<Game> findByReleaseDateAfterOrderByReleaseDateDesc(LocalDate date);

    /**
     * Retrieves the top 8 games released after the specified date,
     * sorted by release date in descending order.
     *
     * @param date the cutoff release date
     * @return a list of up to 8 most recent games
     */
    List<Game> findTop8ByReleaseDateAfterOrderByReleaseDateDesc(LocalDate date);

    /**
     * Retrieves a list of games that have an average rating greater than or equal
     * to the specified minimum rating.
     *
     * @param minRating the minimum rating value
     * @return a list of games that meet the rating threshold
     */
    @Query("SELECT g FROM Game g WHERE g.averageRating >= :minRating")
    List<Game> findByMinimumRating(@Param("minRating") Double minRating);

    /**
     * Retrieves a list of recommended games that match the specified genres and platforms,
     * excluding the provided list of games (e.g., already played or owned games).
     *
     * @param genres        the preferred genres
     * @param platforms     the preferred platforms
     * @param excludedGames the games to exclude from recommendations
     * @return a list of recommended games
     */
    @Query("SELECT g FROM Game g JOIN g.genres gen JOIN g.platforms plat " +
            "WHERE gen IN :genres AND plat IN :platforms AND g NOT IN :excludedGames")
    List<Game> findRecommendedGames(
            @Param("genres") Set<Genre> genres,
            @Param("platforms") Set<Platform> platforms,
            @Param("excludedGames") List<Game> excludedGames);
}
