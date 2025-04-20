# EshopExpress Implementation Guide

This guide will help you implement a Java-based gaming e-commerce platform with Spring Boot, focusing on video games, gaming accessories, and digital content delivery with a Nintendo-inspired theme.

## Getting Started with Spring Boot and Java

### Development Environment

#### IDE Setup
- **IntelliJ IDEA Community Edition** is sufficient for Java and Spring Boot development
  - The Community Edition supports Spring Boot projects but lacks some specialized Spring tooling
  - Ultimate Edition offers additional features like enhanced Spring support, database tools, and HTTP client

#### Project Initialization
1. Use [Spring Initializr](https://start.spring.io/) to create your project with these dependencies:
   - Spring Web
   - Spring Data JPA
   - Spring Security
   - Thymeleaf (for templating)
   - PostgreSQL Driver (or MySQL)
   - Lombok (optional, but reduces boilerplate)
   - Validation
   - DevTools
   - Spring Cache (for game catalog performance)
   - Spring Mail (for order notifications)

2. Import the generated project into IntelliJ IDEA

### Project Structure
Create a standard layered architecture:

```
src/main/java/com/yourname/eshopexpress/
├── config/                # Configuration classes
├── controller/            # MVC controllers
├── model/                 # Entity classes
│   ├── dto/               # Data Transfer Objects
│   ├── entity/            # JPA entities
│   ├── enums/             # Enumerations (GamePlatform, GameGenre, etc.)
│   └── security/          # Security-related models
├── repository/            # Spring Data repositories
├── service/               # Business logic
│   ├── impl/              # Service implementations
│   └── digital/           # Digital content delivery services
├── exception/             # Custom exceptions
├── util/                  # Utility classes
└── event/                 # Event listeners (order events, etc.)
```

## Key Domain Entities

#### Game Entity
```java
@Entity
@Table(name = "games")
@Data  // If using Lombok
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    private String title;
    
    @Column(length = 2000)
    private String description;
    
    @Positive
    private BigDecimal price;
    
    private String coverImageUrl;
    
    @Column(name = "release_date")
    private LocalDate releaseDate;
    
    @Column(name = "publisher")
    private String publisher;
    
    @Column(name = "developer")
    private String developer;
    
    @Enumerated(EnumType.STRING)
    private AgeRating ageRating;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "game_genres",
        joinColumns = @JoinColumn(name = "game_id"),
        inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genre> genres = new HashSet<>();
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "game_platforms",
        joinColumns = @JoinColumn(name = "game_id"),
        inverseJoinColumns = @JoinColumn(name = "platform_id")
    )
    private Set<Platform> platforms = new HashSet<>();
    
    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Review> reviews = new HashSet<>();
    
    @Column(name = "is_digital")
    private boolean digital;
    
    @Column(name = "stock_quantity")
    private Integer stockQuantity;
    
    @Column(name = "average_rating")
    private Double averageRating;
}
```

#### Platform Entity
```java
@Entity
@Table(name = "platforms")
@Data
public class Platform {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    private String name;
    
    private String manufacturer;
    
    @ManyToMany(mappedBy = "platforms")
    private Set<Game> games = new HashSet<>();
}
```

#### Gaming Accessory Entity
```java
@Entity
@Table(name = "accessories")
@Data
public class Accessory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    private String name;
    
    @Column(length = 1000)
    private String description;
    
    @Positive
    private BigDecimal price;
    
    private String imageUrl;
    
    @Enumerated(EnumType.STRING)
    private AccessoryType type;
    
    @ManyToMany
    @JoinTable(
        name = "accessory_platforms",
        joinColumns = @JoinColumn(name = "accessory_id"),
        inverseJoinColumns = @JoinColumn(name = "platform_id")
    )
    private Set<Platform> compatiblePlatforms = new HashSet<>();
    
    private String brand;
    
    private String model;
    
    @Column(name = "stock_quantity")
    private Integer stockQuantity;
}
```

#### Digital Product Key
```java
@Entity
@Table(name = "digital_keys")
@Data
public class DigitalKey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;
    
    @Column(name = "activation_key", unique = true)
    private String activationKey;
    
    @Enumerated(EnumType.STRING)
    private KeyStatus status; // AVAILABLE, SOLD, RESERVED
    
    @OneToOne(mappedBy = "digitalKey")
    private OrderItem orderItem;
    
    @ManyToOne
    @JoinColumn(name = "platform_id")
    private Platform platform;
}
```

```java
@Service
public class GameServiceImpl implements GameService {
    @Autowired
    private GameRepository gameRepository;
    
    @Autowired
    private PlatformRepository platformRepository;
    
    @Autowired
    private GenreRepository genreRepository;
    
    @Override
    public Optional<Game> findById(Long id) {
        return gameRepository.findById(id);
    }
    
    @Override
    public Game getGameOrThrow(Long id) {
        return gameRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Game not found with id: " + id));
    }
    
    @Override
    @Cacheable("gamesByPlatform")
    public List<Game> findByPlatform(String platformName) {
        Platform platform = platformRepository.findByNameIgnoreCase(platformName)
            .orElseThrow(() -> new ResourceNotFoundException("Platform not found: " + platformName));
        return gameRepository.findByPlatformsContaining(platform);
    }
    
    @Override
    @Cacheable("gamesByGenre")
    public List<Game> findByGenre(String genreName) {
        Genre genre = genreRepository.findByNameIgnoreCase(genreName)
            .orElseThrow(() -> new ResourceNotFoundException("Genre not found: " + genreName));
        return gameRepository.findByGenresContaining(genre);
    }
    
    @Override
    public List<Game> findNewReleases() {
        LocalDate oneMonthAgo = LocalDate.now().minusMonths(1);
        return gameRepository.findByReleaseDateAfterOrderByReleaseDateDesc(oneMonthAgo);
    }
    
    @Override
    @CacheEvict(value = {"gamesByPlatform", "gamesByGenre"}, allEntries = true)
    public Game save(Game game) {
        return gameRepository.save(game);
    }
}
```

```java
@Controller
@RequestMapping("/games")
public class GameController {
    @Autowired
    private GameService gameService;
    
    @Autowired
    private PlatformService platformService;
    
    @Autowired
    private GenreService genreService;
    
    @Autowired
    private ReviewService reviewService;
    
    @GetMapping
    public String listGames(Model model) {
        model.addAttribute("games", gameService.findAllGames());
        model.addAttribute("platforms", platformService.findAllPlatforms());
        model.addAttribute("genres", genreService.findAllGenres());
        model.addAttribute("newReleases", gameService.findNewReleases());
        return "games/list";
    }
    
    @GetMapping("/{id}")
    public String viewGame(@PathVariable Long id, Model model, Authentication authentication) {
        Game game = gameService.getGameOrThrow(id);
        model.addAttribute("game", game);
        
        // Add review form if user is authenticated
        if (authentication != null) {
            model.addAttribute("reviewForm", new ReviewDto());
            // Check if user already reviewed this game
            boolean hasReviewed = reviewService.hasUserReviewedGame(
                authentication.getName(), id);
            model.addAttribute("hasReviewed", hasReviewed);
        }
        
        return "games/view";
    }
    
    @GetMapping("/platform/{platformName}")
    public String gamesByPlatform(@PathVariable String platformName, Model model) {
        model.addAttribute("games", gameService.findByPlatform(platformName));
        model.addAttribute("platformName", platformName);
        return "games/by-platform";
    }
    
    @GetMapping("/genre/{genreName}")
    public String gamesByGenre(@PathVariable String genreName, Model model) {
        model.addAttribute("games", gameService.findByGenre(genreName));
        model.addAttribute("genreName", genreName);
        return "games/by-genre";
    }
    
    @PostMapping("/{id}/review")
    @PreAuthorize("isAuthenticated()")
    public String addReview(@PathVariable Long id, @Valid ReviewDto reviewDto, 
                           BindingResult result, Authentication authentication) {
        if (result.hasErrors()) {
            return "redirect:/games/" + id + "?error=validation";
        }
        
        reviewService.addReview(id, reviewDto, authentication.getName());
        return "redirect:/games/" + id + "?success=review-added";
    }
}
```

### 4. Template Implementation with Thymeleaf

```html
<!-- templates/games/view.html -->
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org" 
      xmlns:sec="http://www.thymeleaf.org/extras/spring-security">
<head>
    <title th:text="${game.title}">Game Title</title>
    <link rel="stylesheet" th:href="@{/css/main.css}">
</head>
<body>
    <div class="container">
        <div class="game-detail">
            <div class="game-header">
                <h1 th:text="${game.title}">Game Title</h1>
                <div class="game-meta">
                    <span class="publisher" th:text="${game.publisher}">Publisher</span>
                    <span class="developer" th:text="${game.developer}">Developer</span>
                    <span class="release-date" th:text="${#temporals.format(game.releaseDate, 'MMMM d, yyyy')}">Release Date</span>
                </div>
            </div>
            
            <div class="game-content">
                <div class="game-image">
                    <img th:src="${game.coverImageUrl}" alt="Game Cover">
                </div>
                
                <div class="game-info">
                    <div class="rating" th:if="${game.averageRating != null}">
                        <span th:text="${#numbers.formatDecimal(game.averageRating, 1, 1)}">4.5</span>/5.0
                    </div>
                    
                    <div class="price-section">
                        <span class="price" th:text="${'$' + #numbers.formatDecimal(game.price, 1, 2)}">$49.99</span>
                        
                        <div class="availability">
                            <span th:if="${game.digital}" class="digital">Digital Download</span>
                            <span th:if="${!game.digital && game.stockQuantity > 0}" class="in-stock">In Stock</span>
                            <span th:if="${!game.digital && game.stockQuantity == 0}" class="out-of-stock">Out of Stock</span>
                        </div>
                        
                        <form th:action="@{/cart/add}" method="post" sec:authorize="isAuthenticated()">
                            <input type="hidden" name="gameId" th:value="${game.id}">
                            <button type="submit" class="btn add-to-cart" 
                                    th:disabled="${!game.digital && game.stockQuantity == 0}">
                                Add to Cart
                            </button>
                        </form>
                        
                        <a th:href="@{/login}" class="btn login-to-buy" sec:authorize="!isAuthenticated()">
                            Log in to Purchase
                        </a>
                    </div>
                    
                    <div class="game-details">
                        <div class="platforms">
                            <h3>Available on:</h3>
                            <ul>
                                <li th:each="platform : ${game.platforms}" th:text="${platform.name}">Platform</li>
                            </ul>
                        </div>
                        
                        <div class="genres">
                            <h3>Genres:</h3>
                            <ul>
                                <li th:each="genre : ${game.genres}" th:text="${genre.name}">Genre</li>
                            </ul>
                        </div>
                        
                        <div class="age-rating">
                            <h3>Age Rating:</h3>
                            <span th:text="${game.ageRating}">TEEN</span>
                        </div>
                    </div>
                </div>
            </div>
            
            <div class="game-description">
                <h2>About this game</h2>
                <p th:text="${game.description}">Game description...</p>
            </div>
            
            <div class="reviews-section">
                <h2>Customer Reviews</h2>
                
                <div th:if="${game.reviews.empty}">
                    <p>No reviews yet. Be the first to review this game!</p>
                </div>
                
                <div class="review-form" sec:authorize="isAuthenticated()" th:if="${!hasReviewed}">
                    <h3>Write a Review</h3>
                    <form th:action="@{/games/{id}/review(id=${game.id})}" method="post" th:object="${reviewForm}">
                        <div class="form-group">
                            <label for="rating">Rating:</label>
                            <select id="rating" th:field="*{rating}" required>
                                <option value="5">5 - Excellent</option>
                                <option value="4">4 - Good</option>
                                <option value="3">3 - Average</option>
                                <option value="2">2 - Poor</option>
                                <option value="1">1 - Terrible</option>
                            </select>
                        </div>
                        
                        <div class="form-group">
                            <label for="comment">Your Review:</label>
                            <textarea id="comment" th:field="*{comment}" rows="4" required></textarea>
                        </div>
                        
                        <button type="submit" class="btn">Submit Review</button>
                    </form>
                </div>
                
                <div class="reviews-list">
                    <div class="review" th:each="review : ${game.reviews}">
                        <div class="review-header">
                            <span class="reviewer" th:text="${review.user.username}">Username</span>
                            <span class="review-date" th:text="${#temporals.format(review.createdAt, 'MMM d, yyyy')}">Jan 1, 2025</span>
                            <div class="stars">
                                <span th:text="${review.rating + '/5'}">5/5</span>
                            </div>
                        </div>
                        <div class="review-content" th:text="${review.comment}">Review text...</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
```

## Database Configuration

In `application.properties`:

```properties
# PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/eshopexpress
spring.datasource.username=postgres
spring.datasource.password=password

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

# Thymeleaf
spring.thymeleaf.cache=false

# File upload limits
spring.servlet.multipart.max-file-size=10MB
spring.servlet.multipart.max-request-size=10MB

# Custom application properties
eshopexpress.images.upload-dir=uploads/images
eshopexpress.featured-games.count=6
```

## Security Implementation

Configure security for your gaming e-commerce platform:

```java
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                // Public URLs
                .requestMatchers("/", "/games/**", "/accessories/**", "/search/**", 
                                 "/register", "/css/**", "/js/**", "/images/**").permitAll()
                // Admin URLs
                .requestMatchers("/admin/**").hasRole("ADMIN")
                // Cart and Checkout require authentication
                .requestMatchers("/cart/**", "/checkout/**", "/account/**").authenticated()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/")
                .permitAll()
            )
            .rememberMe(remember -> remember
                .key("eshopExpressRememberMeKey")
                .tokenValiditySeconds(86400) // 1 day
            );
        
        return http.build();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
}
```

## Digital Content Delivery System

For handling digital game keys and downloads:

```java
@Service
public class DigitalDeliveryService {
    
    @Autowired
    private DigitalKeyRepository digitalKeyRepository;
    
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private EmailService emailService;
    
    @Transactional
    public void processDigitalPurchase(Order order) {
        for (OrderItem item : order.getOrderItems()) {
            if (item.getGame() != null && item.getGame().isDigital()) {
                // Allocate a digital key for this game
                DigitalKey key = findAvailableKey(item.getGame(), item.getPlatform());
                if (key != null) {
                    // Mark the key as sold and associate with order item
                    key.setStatus(KeyStatus.SOLD);
                    key.setOrderItem(item);
                    digitalKeyRepository.save(key);
                    
                    // Associate digital key with order item
                    item.setDigitalKey(key);
                    
                    // Send email with digital key
                    emailService.sendDigitalKeyEmail(
                        order.getUser().getEmail(),
                        "Your Digital Game Key for " + item.getGame().getTitle(),
                        createDigitalKeyEmailContent(item.getGame(), key, order.getUser())
                    );
                }
            }
        }
    }
    
    private DigitalKey findAvailableKey(Game game, Platform platform) {
        return digitalKeyRepository.findFirstByGameAndPlatformAndStatus(
            game, platform, KeyStatus.AVAILABLE);
    }
    
    private String createDigitalKeyEmailContent(Game game, DigitalKey key, User user) {
        StringBuilder builder = new StringBuilder();
        builder.append("Hello ").append(user.getFirstName()).append(",\n\n");
        builder.append("Thank you for your purchase from EshopExpress!\n\n");
        builder.append("Here is your digital key for ").append(game.getTitle()).append(":\n\n");
        builder.append("ACTIVATION KEY: ").append(key.getActivationKey()).append("\n\n");
        builder.append("To activate your game:\n");
        builder.append("1. Log in to your gaming platform\n");
        builder.append("2. Navigate to the 'Redeem Code' section\n");
        builder.append("3. Enter the activation key above\n\n");
        builder.append("If you encounter any issues, please contact our customer support team.\n\n");
        builder.append("Happy gaming!\n");
        builder.append("The EshopExpress Team");
        
        return builder.toString();
    }
}
```

## Shopping Cart Implementation

```java
@Service
public class CartService {
    
    @Autowired
    private GameRepository gameRepository;
    
    @Autowired
    private AccessoryRepository accessoryRepository;
    
    public Cart addGameToCart(Cart cart, Long gameId) {
        if (cart == null) {
            cart = new Cart();
        }
        
        Game game = gameRepository.findById(gameId)
            .orElseThrow(() -> new ResourceNotFoundException("Game not found"));
            
        // Check if this game is already in cart
        boolean exists = cart.getItems().stream()
            .anyMatch(item -> item.getItemType() == CartItemType.GAME 
                     && item.getItemId().equals(gameId));
                     
        if (!exists) {
            CartItem item = new CartItem();
            item.setItemId(game.getId());
            item.setItemType(CartItemType.GAME);
            item.setName(game.getTitle());
            item.setPrice(game.getPrice());
            item.setQuantity(1);
            item.setImageUrl(game.getCoverImageUrl());
            item.setDigital(game.isDigital());
            
            cart.getItems().add(item);
            cart.updateTotals();
        }
        
        return cart;
    }
    
    public Cart addAccessoryToCart(Cart cart, Long accessoryId) {
        if (cart == null) {
            cart = new Cart();
        }
        
        Accessory accessory = accessoryRepository.findById(accessoryId)
            .orElseThrow(() -> new ResourceNotFoundException("Accessory not found"));
            
        // Similar implementation to addGameToCart
        // ...
        
        return cart;
    }
    
    public Cart updateItemQuantity(Cart cart, String itemKey, int quantity) {
        // Implementation to update quantity
        // ...
        return cart;
    }
    
    public Cart removeItem(Cart cart, String itemKey) {
        // Implementation to remove item
        // ...
        return cart;
    }
}
```

## Nintendo-Inspired Theme Elements

To embrace the Nintendo-inspired theme for your "EshopExpress" application:

### 1. Color Scheme

Implement a color scheme reminiscent of Nintendo's brand:
- Primary: Bright Red (#E60012) - Similar to Nintendo's logo
- Secondary: White (#FFFFFF)
- Accents: Black (#000000)
- Highlight: Nintendo Blue (#00489C)

### 2. UI Components

Add Nintendo-inspired design elements:
- Rounded corners on buttons and cards
- Clean, family-friendly interface
- Simple, bold typography
- Game card layouts similar to Nintendo eShop

### 3. Custom Categories

Create Nintendo-inspired game categories:
- Family Favorites
- Retro Classics
- Adventure Titles
- Multiplayer Fun
- Indie Discoveries

### 4. User Experience Features

- "Wishlist" functionality (similar to Nintendo eShop)
- Game recommendation system
- Deal of the day section
- Coming soon section for upcoming releases
- Virtual currency system for purchases and rewards

## Additional Advanced Features

### 1. Recommendation Engine

```java
@Service
public class RecommendationService {
    
    @Autowired
    private GameRepository gameRepository;
    
    @Autowired
    private OrderRepository orderRepository;
    
    public List<Game> getPersonalizedRecommendations(User user) {
        // Get user's purchase history
        List<Game> purchasedGames = orderRepository.findGamesPurchasedByUser(user.getId());
        
        // Get genres and platforms user prefers
        Set<Genre> preferredGenres = extractGenres(purchasedGames);
        Set<Platform> preferredPlatforms = extractPlatforms(purchasedGames);
        
        // Find games with similar genres or platforms that user hasn't purchased
        return gameRepository.findRecommendedGames(
            preferredGenres, preferredPlatforms, purchasedGames);
    }
    
    private Set<Genre> extractGenres(List<Game> games) {
        return games.stream()
            .flatMap(game -> game.getGenres().stream())
            .collect(Collectors.toSet());
    }
    
    private Set<Platform> extractPlatforms(List<Game> games) {
        return games.stream()
            .flatMap(game -> game.getPlatforms().stream())
            .collect(Collectors.toSet());
    }
}
```

### 2. Analytics Dashboard (Admin)

Create an admin dashboard with:
- Sales statistics
- Bestselling games and accessories
- Customer activity
- Inventory status
- Revenue charts

### 3. Bundle Deals

Allow creating game bundles with discounted pricing:

```java
@Entity
@Table(name = "bundles")
public class Bundle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    private String description;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "bundle_games",
        joinColumns = @JoinColumn(name = "bundle_id"),
        inverseJoinColumns = @JoinColumn(name = "game_id")
    )
    private Set<Game> games = new HashSet<>();
    
    private BigDecimal originalPrice;
    
    private BigDecimal discountedPrice;
    
    private LocalDate startDate;
    
    private LocalDate endDate;
    
    private boolean active;
    
    // Getters, setters, etc.
}
```

## Resources

- [Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/)
- [Baeldung Spring Tutorials](https://www.baeldung.com/spring-tutorial)
- [Spring Data JPA](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)
- [Thymeleaf Documentation](https://www.thymeleaf.org/documentation.html)
- [Spring Security Reference](https://docs.spring.io/spring-security/reference/index.html)