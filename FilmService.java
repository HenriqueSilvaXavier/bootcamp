import java.util.ArrayList;
import java.util.List;

public class FilmService {

    private List<Film> films = new ArrayList<>();

    public void addFilm(Film film) {
        films.add(film);
    }

    public List<Film> getFilms() {
        return films;
    }

    public void removeFilm(int index) {
        films.remove(index);
    }

    public void editFilm(int index, String title, String director, int year, String genre, int duration) {

        Film film = films.get(index);

        film.setTitle(title);
        film.setDirector(director);
        film.setReleaseYear(year);
        film.setGenre(genre);
        film.setDuration(duration);
    }

    public void editOneField(int index, int field, Object value) {

        Film film = films.get(index);

        switch (field) {

            case 1:
                film.setTitle((String) value);
                break;

            case 2:
                film.setDirector((String) value);
                break;

            case 3:
                film.setReleaseYear((Integer) value);
                break;

            case 4:
                film.setGenre((String) value);
                break;

            case 5:
                film.setDuration((Integer) value);
                break;

            default:
                throw new IllegalArgumentException("Campo inválido.");
        }
    }

    public boolean isEmpty() {
        return films.isEmpty();
    }
}