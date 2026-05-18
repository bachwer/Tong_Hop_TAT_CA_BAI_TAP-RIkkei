import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    static class Movie {

        private String movieId;
        private String title;
        private String genre;
        private double rating;

        public Movie(String movieId, String title,
                     String genre, double rating) {

            this.movieId = movieId;
            this.title = title;
            this.genre = genre;
            this.rating = rating;
        }

        // Getters
        public String getMovieId() {
            return movieId;
        }

        public String getTitle() {
            return title;
        }

        public String getGenre() {
            return genre;
        }

        public double getRating() {
            return rating;
        }

        // Setters
        public void setMovieId(String movieId) {
            this.movieId = movieId;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setGenre(String genre) {
            this.genre = genre;
        }

        public void setRating(double rating) {
            this.rating = rating;
        }
    }


    private List<Movie> movies = new ArrayList<>();

    public MovieController() {
        movies.add(new Movie("M001", "Inception", "Sci-Fi", 8.8));
        movies.add(new Movie("M002", "Parasite", "Drama", 8.6));
        movies.add(new Movie("M003", "Interstellar", "Sci-Fi", 8.7));
    }


    @GetMapping("/{movieId}")
    public Movie getMovieById(@PathVariable String movieId) {

        for (Movie movie : movies) {
            if (movie.getMovieId().equals(movieId)) {
                return movie;
            }
        }

        return null;
    }


    @GetMapping
    public List<Movie> getMoviesByGenre(
            @RequestParam String genre) {

        return movies.stream()
                .filter(movie ->
                        movie.getGenre().equalsIgnoreCase(genre))
                .collect(Collectors.toList());
    }
}