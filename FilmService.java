import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FilmService {

    public final List<Film> films = new ArrayList<>();

    // Valida índice da lista
    public void validateIndex(int index) {
        if (index < 0 || index >= films.size()) {
            throw new IllegalArgumentException("Índice inválido.");
        }
    }

    public void addFilm(Film film) {
        films.add(film);
    }

    public List<Film> getFilms() {
        return Collections.unmodifiableList(films);
    }

    public void removeFilm(int index) {
        validateIndex(index);
        films.remove(index);
    }

    public void editFilm(int index, String title, String director, int year, String genre, int duration) {
        validateIndex(index);
        Film film = films.get(index);
        film.setTitle(title);
        film.setDirector(director);
        film.setReleaseYear(year);
        film.setGenre(genre);
        film.setDuration(duration);
    }

    // Métodos específicos para editar apenas um campo
    public void editTitle(int index, String title) { validateIndex(index); films.get(index).setTitle(title); }
    public void editDirector(int index, String director) { validateIndex(index); films.get(index).setDirector(director); }
    public void editYear(int index, int year) { validateIndex(index); films.get(index).setReleaseYear(year); }
    public void editGenre(int index, String genre) { validateIndex(index); films.get(index).setGenre(genre); }
    public void editDuration(int index, int duration) { validateIndex(index); films.get(index).setDuration(duration); }

    public void printFilms() {
        if (films.isEmpty()) {
            System.out.println("Nenhum filme cadastrado.");
            return;
        }
        for (int i = 0; i < films.size(); i++) {
            System.out.println(i + " - " + films.get(i));
        }
    }

    public boolean isEmpty() {
        return films.isEmpty();
    }
}