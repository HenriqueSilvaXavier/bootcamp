public class Film {
    private String title;
    private String director;
    private int releaseYear;
    private String genre;
    private int duration;

    public Film(String title, String director, int releaseYear, String genre, int duration) {
        this.title = title;
        this.director = director;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.duration = duration;
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
    // SETTERS
    public void setTitle(String title) {
        this.title = title;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Film{" +
                "title='" + title + '\'' +
                ", director='" + director + '\'' +
                ", releaseYear=" + releaseYear +
                ", genre='" + genre + '\'' +
                ", duration=" + duration +
                '}';
    }
}