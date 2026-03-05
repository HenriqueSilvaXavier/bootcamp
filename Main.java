import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        FilmService service = new FilmService();

        int option;

        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1 - Adicionar filme");
            System.out.println("2 - Listar filmes");
            System.out.println("3 - Editar filme (todos campos)");
            System.out.println("4 - Editar apenas um campo");
            System.out.println("5 - Deletar filme");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {

                case 1:

                    System.out.print("Título: ");
                    String title = scanner.nextLine();

                    System.out.print("Diretor: ");
                    String director = scanner.nextLine();

                    System.out.print("Ano de lançamento: ");
                    int year = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Gênero: ");
                    String genre = scanner.nextLine();

                    System.out.print("Duração (minutos): ");
                    int duration = scanner.nextInt();
                    scanner.nextLine();

                    Film film = new Film(title, director, year, genre, duration);
                    service.addFilm(film);

                    System.out.println("Filme adicionado!");

                    break;

                case 2:

                    List<Film> films = service.getFilms();

                    if (films.isEmpty()) {
                        System.out.println("Nenhum filme cadastrado.");
                    } else {
                        for (int i = 0; i < films.size(); i++) {
                            System.out.println(i + " - " + films.get(i));
                        }
                    }

                    break;

                case 3:
                    List<Film> filmsEdit = service.getFilms();

                    for (int i = 0; i < filmsEdit.size(); i++) {
                        System.out.println(i + " - " + filmsEdit.get(i));
                    }
                    System.out.print("Índice do filme: ");
                    int editIndex = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Novo título: ");
                    String newTitle = scanner.nextLine();

                    System.out.print("Novo diretor: ");
                    String newDirector = scanner.nextLine();

                    System.out.print("Novo ano: ");
                    int newYear = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Novo gênero: ");
                    String newGenre = scanner.nextLine();

                    System.out.print("Nova duração: ");
                    int newDuration = scanner.nextInt();
                    scanner.nextLine();

                    service.editFilm(editIndex, newTitle, newDirector, newYear, newGenre, newDuration);

                    System.out.println("Filme atualizado!");

                    break;

                case 4:
                    filmsEdit = service.getFilms();

                    for (int i = 0; i < filmsEdit.size(); i++) {
                        System.out.println(i + " - " + filmsEdit.get(i));
                    }

                    System.out.print("Índice do filme: ");
                    int index = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Campo para editar:");
                    System.out.println("1 - Título");
                    System.out.println("2 - Diretor");
                    System.out.println("3 - Ano");
                    System.out.println("4 - Gênero");
                    System.out.println("5 - Duração");

                    int field = scanner.nextInt();
                    scanner.nextLine();

                    Object value;

                    switch (field) {

                        case 1:
                            System.out.print("Novo título: ");
                            value = scanner.nextLine();
                            break;

                        case 2:
                            System.out.print("Novo diretor: ");
                            value = scanner.nextLine();
                            break;

                        case 3:
                            System.out.print("Novo ano: ");
                            value = scanner.nextInt();
                            scanner.nextLine();
                            break;

                        case 4:
                            System.out.print("Novo gênero: ");
                            value = scanner.nextLine();
                            break;

                        case 5:
                            System.out.print("Nova duração: ");
                            value = scanner.nextInt();
                            scanner.nextLine();
                            break;

                        default:
                            System.out.println("Campo inválido.");
                            continue;
                    }

                    service.editOneField(index, field, value);

                    System.out.println("Campo atualizado!");

                    break;

                case 5:
                    List<Film> filmsDelete = service.getFilms();

                    for (int i = 0; i < filmsDelete.size(); i++) {
                        System.out.println(i + " - " + filmsDelete.get(i));
                    }

                    System.out.print("Índice do filme para deletar: ");
                    int deleteIndex = scanner.nextInt();
                    scanner.nextLine();

                    service.removeFilm(deleteIndex);

                    System.out.println("Filme removido!");

                    break;

                case 0:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (option != 0);

        scanner.close();
    }
}