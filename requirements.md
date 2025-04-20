# EshopExpress Implementation Guide

This comprehensive guide will help you implement a Java-based gaming e-commerce platform with Spring Boot, focusing on video games, gaming accessories, and digital content delivery with a Nintendo-inspired theme.

## Project Requirements

### Functional Requirements

1. **Product Management**
    - Manage game catalog (add, update, delete games)
    - Manage gaming accessories (controllers, headsets, etc.)
    - Support for both physical and digital products
    - Product categorization by platform, genre, and type

2. **User Management**
    - User registration and authentication
    - Role-based access (Customer, Admin)
    - User profiles and purchase history
    - Wishlist functionality

3. **Shopping Experience**
    - Browse products by category, platform, and genre
    - Search functionality with filters
    - Product recommendations
    - Product reviews and ratings

4. **Order Processing**
    - Shopping cart functionality
    - Checkout process
    - Order confirmation and history
    - Digital product key delivery
    - Inventory management for physical products

5. **Admin Functionality**
    - Dashboard with sales analytics
    - Inventory management
    - User management
    - Order processing

### Technical Requirements

1. **Backend**
    - Java 21 (LTS)
    - Spring Boot 3.4.4 or 3.5.0 (M3)
    - RESTful API architecture
    - Spring Security for authentication

2. **Database**
    - PostgreSQL
    - JPA/Hibernate for ORM
    - Database transaction management

3. **Frontend**
    - Thymeleaf for server-side templating
    - Responsive design with Bootstrap or custom CSS
    - JavaScript for enhanced interactivity

4. **Development Tools**
    - IntelliJ IDEA (Ultimate recommended for database tools)
    - Git for version control
    - Maven or Gradle for dependency management

## Database Setup

### PostgreSQL Configuration

1. **Create Database and User**
   ```sql
   CREATE DATABASE eshopexpress OWNER daniel;
   ```

2. **Spring Boot Configuration** (application.properties)
   ```properties
   # Database Connection
   spring.datasource.url=jdbc:postgresql://localhost:5432/eshopexpress
   spring.datasource.username=daniel
   spring.datasource.password=your_password_here
   
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

### Database Schema

![Database Schema Diagram]

The database will consist of the following core tables:

1. **Users** - Store user information
2. **Roles** - Define user roles
3. **Games** - Store game information
4. **Platforms** - Gaming platforms (Switch, PlayStation, etc.)
5. **Genres** - Game genres
6. **Accessories** - Gaming peripherals and merchandise
7. **Orders** - Customer orders
8. **OrderItems** - Individual items in orders
9. **DigitalKeys** - Digital product activation keys
10. **Reviews** - User reviews for products
11. **WishlistItems** - User wishlist entries

## Class Structure Breakdown

### 1. Model Layer

#### Core Entities

**User.java**
```java
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String username;
    
    @Column(nullable = false)
    private String password;
    
    @Column(nullable = false)
    private String email;
    
    private String firstName;
    private String lastName;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();
    
    @OneToMany(mappedBy = "user")
    private List<Order> orders = new ArrayList<>();
    
    @OneToMany(mappedBy = "user")
    private List<Review> reviews = new ArrayList<>();
    
    @OneToMany(mappedBy = "user")
    private List<WishlistItem> wishlist = new ArrayList<>();
    
    // Getters, setters, constructors
}
```

**Game.java**
```java
@Entity
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String title;
    
    @Column(length = 2000)
    private String description;
    
    @Column(nullable = false)
    private BigDecimal price;
    
    private String coverImageUrl;
    
    @Column(name = "release_date")
    private LocalDate releaseDate;
    
    private String publisher;
    private String developer;
    
    @Enumerated(EnumType.STRING)
    private AgeRating ageRating;
    
    @ManyToMany
    @JoinTable(
        name = "game_genres",
        joinColumns = @JoinColumn(name = "game_id"),
        inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genre> genres = new HashSet<>();
    
    @ManyToMany
    @JoinTable(
        name = "game_platforms",
        joinColumns = @JoinColumn(name = "game_id"),
        inverseJoinColumns = @JoinColumn(name = "platform_id")
    )
    private Set<Platform> platforms = new HashSet<>();
    
    @OneToMany(mappedBy = "game")
    private Set<Review> reviews = new HashSet<>();
    
    @Column(name = "is_digital")
    private boolean digital;
    
    @Column(name = "stock_quantity")
    private Integer stockQuantity;
    
    @Column(name = "average_rating")
    private Double averageRating;
    
    // Getters, setters, constructors
}
```

**Complete all other entity classes:**
- Platform.java
- Genre.java
- Accessory.java
- Order.java
- OrderItem.java
- DigitalKey.java
- Review.java
- WishlistItem.java
- Role.java

#### Data Transfer Objects (DTOs)

Create DTOs for transferring data between layers:

**GameDto.java**
```java
public class GameDto {
    private Long id;
    private String title;
    private String description;
    private BigDecimal price;
    private String coverImageUrl;
    private LocalDate releaseDate;
    private String publisher;
    private String developer;
    private AgeRating ageRating;
    private Set<String> genreNames = new HashSet<>();
    private Set<String> platformNames = new HashSet<>();
    private boolean digital;
    private Integer stockQuantity;
    private Double averageRating;
    
    // Getters, setters, constructors
}
```

Create similar DTOs for other entities that require them.

#### Enums

**AgeRating.java**
```java
public enum AgeRating {
    EVERYONE("E", "Everyone"),
    EVERYONE_10_PLUS("E10+", "Everyone 10+"),
    TEEN("T", "Teen"),
    MATURE("M", "Mature 17+"),
    ADULTS_ONLY("AO", "Adults Only 18+"),
    RATING_PENDING("RP", "Rating Pending");
    
    private final String code;
    private final String description;
    
    AgeRating(String code, String description) {
        this.code = code;
        this.description = description;
    }
    
    // Getters
}
```

**Create other enums:**
- OrderStatus.java
- KeyStatus.java
- AccessoryType.java

### 2. Repository Layer

Create Spring Data JPA repositories for each entity:

**GameRepository.java**
```java
@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    List<Game> findByTitleContainingIgnoreCase(String title);
    
    List<Game> findByPlatformsContaining(Platform platform);
    
    List<Game> findByGenresContaining(Genre genre);
    
    List<Game> findByReleaseDateAfterOrderByReleaseDateDesc(LocalDate date);
    
    List<Game> findTop8ByOrderByReleaseDateDesc();
    
    @Query("SELECT g FROM Game g WHERE g.averageRating >= :minRating")
    List<Game> findByMinimumRating(@Param("minRating") Double minRating);
    
    @Query("SELECT g FROM Game g JOIN g.genres gen JOIN g.platforms plat " +
           "WHERE gen IN :genres AND plat IN :platforms AND g NOT IN :excludedGames")
    List<Game> findRecommendedGames(
            @Param("genres") Set<Genre> genres,
            @Param("platforms") Set<Platform> platforms,
            @Param("excludedGames") List<Game> excludedGames);
}
```

**Create repositories for all other entities:**
- UserRepository.java
- PlatformRepository.java
- GenreRepository.java
- AccessoryRepository.java
- OrderRepository.java
- DigitalKeyRepository.java
- ReviewRepository.java
- WishlistItemRepository.java
- RoleRepository.java

### 3. Service Layer

**GameService.java (Interface)**
```java
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
```

**GameServiceImpl.java**
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
    public List<Game> findAllGames() {
        return gameRepository.findAll();
    }
    
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
    public List<Game> findByTitle(String title) {
        return gameRepository.findByTitleContainingIgnoreCase(title);
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
    public List<Game> findTopRated() {
        return gameRepository.findByMinimumRating(4.0);
    }
    
    @Override
    @CacheEvict(value = {"gamesByPlatform", "gamesByGenre"}, allEntries = true)
    public Game save(Game game) {
        return gameRepository.save(game);
    }
    
    @Override
    @CacheEvict(value = {"gamesByPlatform", "gamesByGenre"}, allEntries = true)
    public void deleteById(Long id) {
        gameRepository.deleteById(id);
    }
    
    @Override
    public void updateStock(Long gameId, int quantity) {
        Game game = getGameOrThrow(gameId);
        
        if (!game.isDigital()) {
            int newQuantity = game.getStockQuantity() + quantity;
            game.setStockQuantity(Math.max(0, newQuantity));
            gameRepository.save(game);
        }
    }
    
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
```

**Create service interfaces and implementations for all other entities:**
- UserService.java & UserServiceImpl.java
- OrderService.java & OrderServiceImpl.java
- DigitalDeliveryService.java
- ReviewService.java & ReviewServiceImpl.java
- CartService.java
- WishlistService.java & WishlistServiceImpl.java

### 4. Controller Layer

**GameController.java**
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
    
    @GetMapping("/search")
    public String searchGames(@RequestParam String query, Model model) {
        model.addAttribute("games", gameService.findByTitle(query));
        model.addAttribute("searchQuery", query);
        return "games/search-results";
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
    
    // Admin endpoints
    @GetMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public String createGameForm(Model model) {
        model.addAttribute("game", new GameDto());
        model.addAttribute("platforms", platformService.findAllPlatforms());
        model.addAttribute("genres", genreService.findAllGenres());
        return "admin/games/create";
    }
    
    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public String createGame(@Valid GameDto gameDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("platforms", platformService.findAllPlatforms());
            model.addAttribute("genres", genreService.findAllGenres());
            return "admin/games/create";
        }
        
        // Convert DTO to entity and save
        Game game = convertToEntity(gameDto);
        gameService.save(game);
        
        return "redirect:/admin/games?success=created";
    }
    
    // Helper method to convert DTO to entity
    private Game convertToEntity(GameDto gameDto) {
        Game game = new Game();
        game.setTitle(gameDto.getTitle());
        game.setDescription(gameDto.getDescription());
        game.setPrice(gameDto.getPrice());
        game.setCoverImageUrl(gameDto.getCoverImageUrl());
        game.setReleaseDate(gameDto.getReleaseDate());
        game.setPublisher(gameDto.getPublisher());
        game.setDeveloper(gameDto.getDeveloper());
        game.setAgeRating(gameDto.getAgeRating());
        game.setDigital(gameDto.isDigital());
        game.setStockQuantity(gameDto.getStockQuantity());
        
        // Convert genre names to entities
        Set<Genre> genres = gameDto.getGenreNames().stream()
            .map(genreService::findByNameOrCreate)
            .collect(Collectors.toSet());
        game.setGenres(genres);
        
        // Convert platform names to entities
        Set<Platform> platforms = gameDto.getPlatformNames().stream()
            .map(platformService::findByNameOrCreate)
            .collect(Collectors.toSet());
        game.setPlatforms(platforms);
        
        return game;
    }
}
```

**Create controllers for other main features:**
- HomeController.java
- UserController.java
- CartController.java
- CheckoutController.java
- OrderController.java
- AdminController.java
- AccessoryController.java
- WishlistController.java

### 5. Security Configuration

**SecurityConfig.java**
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

**UserDetailsServiceImpl.java**
```java
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
            
        return new org.springframework.security.core.userdetails.User(
            user.getUsername(),
            user.getPassword(),
            user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
                .collect(Collectors.toList())
        );
    }
}
```

### 6. Exception Handling

**ResourceNotFoundException.java**
```java
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
```

**GlobalExceptionHandler.java**
```java
@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public String handleResourceNotFoundException(ResourceNotFoundException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error/404";
    }
    
    @ExceptionHandler(Exception.class)
    public String handleGenericException(Exception ex, Model model) {
        model.addAttribute("errorMessage", "An unexpected error occurred");
        return "error/500";
    }
}
```

### 7. Utility Classes

**FileUploadUtil.java**
```java
@Component
public class FileUploadUtil {
    
    @Value("${eshopexpress.images.upload-dir}")
    private String uploadDir;
    
    public String saveFile(String fileName, MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(uploadDir);
        
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        
        String fileCode = RandomString.make(8);
        String uniqueFileName = fileCode + "-" + fileName;
        
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(uniqueFileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        }
        
        return uniqueFileName;
    }
}
```

## View Templates (Thymeleaf)

### Core Templates

**Layout Template (templates/layout/main.html)**
```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org" 
      xmlns:sec="http://www.thymeleaf.org/extras/spring-security">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>EshopExpress - <th:block th:include=":: #pageTitle">Page Title</th:block></title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" th:href="@{/css/main.css}">
</head>
<body>
    <!-- Navigation -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-danger">
        <div class="container">
            <a class="navbar-brand" th:href="@{/}">EshopExpress</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <a class="nav-link" th:href="@{/games}">Games</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" th:href="@{/accessories}">Accessories</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="platformsDropdown" role="button" 
                           data-bs-toggle="dropdown">
                            Platforms
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="platformsDropdown">
                            <li th:each="platform : ${@platformService.findAllPlatforms()}">
                                <a class="dropdown-item" th:href="@{/games/platform/{name}(name=${platform.name})}" 
                                   th:text="${platform.name}">Platform</a>
                            </li>
                        </ul>
                    </li>
                </ul>
                
                <!-- Search Form -->
                <form class="d-flex mx-3" th:action="@{/search}" method="get">
                    <input class="form-control me-2" type="search" name="query" placeholder="Search games...">
                    <button class="btn btn-outline-light" type="submit">Search</button>
                </form>
                
                <!-- User Menu -->
                <ul class="navbar-nav">
                    <li class="nav-item" sec:authorize="!isAuthenticated()">
                        <a class="nav-link" th:href="@{/login}">Login</a>
                    </li>
                    <li class="nav-item" sec:authorize="!isAuthenticated()">
                        <a class="nav-link" th:href="@{/register}">Register</a>
                    </li>
                    
                    <!-- Cart -->
                    <li class="nav-item" sec:authorize="isAuthenticated()">
                        <a class="nav-link position-relative" th:href="@{/cart}">
                            Cart
                            <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-primary" 
                                  th:if="${session.cart != null && !session.cart.items.empty}" 
                                  th:text="${session.cart.items.size()}">0</span>
                        </a>
                    </li>
                    
                    <!-- User Dropdown -->
                    <li class="nav-item dropdown" sec:authorize="isAuthenticated()">
                        <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" 
                           data-bs-toggle="dropdown" th:text="${#authentication.name}">
                            Username
                        </a>
                        <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="userDropdown">
                            <li><a class="dropdown-item" th:href="@{/account}">My Account</a></li>
                            <li><a class="dropdown-item" th:href="@{/account/orders}">My Orders</a></li>
                            <li><a class="dropdown-item" th:href="@{/wishlist}">My Wishlist</a></li>
                            <li sec:authorize="hasRole('ADMIN')">
                                <a class="dropdown-item" th:href="@{/admin}">Admin Dashboard</a>
                            </li>
                            <li><hr class="dropdown-divider"></li>
                            <li>
                                <form th:action="@{/logout}" method="post">
                                    <button class="dropdown-item" type="submit">Logout</button>
                                </form>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    
    <!-- Content -->
    <div class="container mt-4">
        <!-- Alert Messages -->
        <div th:if="${successMessage}" class="alert alert-success alert-dismissible fade show">
            <span th:text="${successMessage}">Success message</span>
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </div>
        
        <div th:if="${errorMessage}" class="alert alert-danger alert-dismissible fade show">
            <span th:text="${errorMessage}">Error message</span>
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </div>
        
        <!-- Main Content -->
        <div th:replace=":: #content">
            Content goes here
        </div>
    </div>
    
    <!-- Footer -->
    <footer class="bg-dark text-white mt-5 py-4">
        <div class="container">
            <div class="row">
                <div class="col-md-4">
                    <h5>EshopExpress</h5>
                    <p>Your Nintendo-inspired gaming marketplace</p>
                </div>
                <div class="col-md-4">
                    <h5>Quick Links</h5>
                    <ul class="list-unstyled">
                        <li><a class="text-white" th:href="@{/games}">Games</a></li>
                        <li><a class="text-white" th:href="@{/accessories}">Accessories</a></li>
                        <li><a class="text-white" th:href="@{/about}">About Us</a></li>
                        <li><a class="text-white" th:href="@{/contact}">Contact</a></li>
                    </ul>
                </div>
                <div class="col-md-4">
                    <h5>Contact Us</h5>
                    <address>
                        <p>Email: info@eshopexpress.com</p>
                        <p>Phone: (123) 456-7890</p>
                    </address>
                </div>
            </div>
            <div class="row mt-3">
                <div class="col text-center">
                    <p>&copy; 2025 EshopExpress. All rights reserved.</p>
                    <p class="small">This is a portfolio project and not a real e-commerce store.</p>
                </div>
            </div>
        </div>