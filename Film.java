import java.time.Year;

public class Film {
    private String title;
    private String director;
    private int releaseYear;
    private String genre;
    private int duration; // duração em minutos

    public Film(String title, String director, int releaseYear, String genre, int duration) {
        setTitle(title);
        setDirector(director);
        setReleaseYear(releaseYear);
        setGenre(genre);
        setDuration(duration);
    }

    // GETTERS
    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public String getGenre() {
        return genre;
    }

    public int getDuration() {
        return duration;
    }

    // SETTERS COM VALIDAÇÃO

    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("O título não pode ser vazio.");
        }
        this.title = title;
    }

    public void setDirector(String director) {
        if (director == null || director.trim().isEmpty()) {
            throw new IllegalArgumentException("O diretor não pode ser vazio.");
        }
        this.director = director;
    }

    public void setReleaseYear(int releaseYear) {
        int currentYear = Year.now().getValue();

        if (releaseYear < 1888) {
            throw new IllegalArgumentException("Não existiam filmes antes de 1888.");
        }

        if (releaseYear > currentYear) {
            throw new IllegalArgumentException("O ano não pode ser no futuro.");
        }

        this.releaseYear = releaseYear;
    }

    public void setGenre(String genre) {
        if (genre == null || genre.trim().isEmpty()) {
            throw new IllegalArgumentException("O gênero não pode ser vazio.");
        }
        this.genre = genre;
    }

    public void setDuration(int duration) {
        if (duration <= 0) {
            throw new IllegalArgumentException("A duração deve ser maior que zero.");
        }

        if (duration > 600) {
            throw new IllegalArgumentException("A duração máxima permitida é 600 minutos.");
        }

        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Film{" +
                "title='" + title + '\'' +
                ", director='" + director + '\'' +
                ", releaseYear=" + releaseYear +
                ", genre='" + genre + '\'' +
                ", duration=" + duration + " min" +
                '}';
    }
}