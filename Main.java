import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        FilmService service = new FilmService();
        int option;

        do {
            printMenu();
            try {
                option = scanner.nextInt();
                scanner.nextLine(); // limpar buffer

                switch (option) {
                    case 1 -> addFilm(scanner, service);
                    case 2 -> service.printFilms();
                    case 3 -> editAllFields(scanner, service);
                    case 4 -> editSingleField(scanner, service);
                    case 5 -> deleteFilm(scanner, service);
                    case 0 -> System.out.println("Saindo...");
                    default -> System.out.println("Opção inválida!");
                }

            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
                option = -1; // para continuar o loop

            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Tente novamente.");
                scanner.nextLine(); // limpar buffer
                option = -1;
            }

        } while (option != 0);

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n===== MENU =====");
        System.out.println("1 - Adicionar filme");
        System.out.println("2 - Listar filmes");
        System.out.println("3 - Editar filme (todos campos)");
        System.out.println("4 - Editar apenas um campo");
        System.out.println("5 - Deletar filme");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void addFilm(Scanner scanner, FilmService service) {
        System.out.print("Título: "); String title = scanner.nextLine();
        System.out.print("Diretor: "); String director = scanner.nextLine();
        System.out.print("Ano de lançamento: "); int year = scanner.nextInt(); scanner.nextLine();
        System.out.print("Gênero: "); String genre = scanner.nextLine();
        System.out.print("Duração (minutos): "); int duration = scanner.nextInt(); scanner.nextLine();

        service.addFilm(new Film(title, director, year, genre, duration));
        System.out.println("Filme adicionado!");
    }

    private static void editAllFields(Scanner scanner, FilmService service) {
        if (service.isEmpty()) { System.out.println("Nenhum filme cadastrado."); return; }
        service.printFilms();
        System.out.print("Índice do filme: "); int index = scanner.nextInt(); scanner.nextLine();

        System.out.print("Novo título: "); String title = scanner.nextLine();
        System.out.print("Novo diretor: "); String director = scanner.nextLine();
        System.out.print("Novo ano: "); int year = scanner.nextInt(); scanner.nextLine();
        System.out.print("Novo gênero: "); String genre = scanner.nextLine();
        System.out.print("Nova duração: "); int duration = scanner.nextInt(); scanner.nextLine();

        service.editFilm(index, title, director, year, genre, duration);
        System.out.println("Filme atualizado!");
    }

    private static void editSingleField(Scanner scanner, FilmService service) {
        if (service.isEmpty()) { System.out.println("Nenhum filme cadastrado."); return; }
        service.printFilms();
        System.out.print("Índice do filme: "); int index = scanner.nextInt(); scanner.nextLine();

        System.out.println("Campo para editar:");
        System.out.println("1 - Título\n2 - Diretor\n3 - Ano\n4 - Gênero\n5 - Duração");
        int field = scanner.nextInt(); scanner.nextLine();

        switch (field) {
            case 1 -> { System.out.print("Novo título: "); service.editTitle(index, scanner.nextLine()); }
            case 2 -> { System.out.print("Novo diretor: "); service.editDirector(index, scanner.nextLine()); }
            case 3 -> { System.out.print("Novo ano: "); service.editYear(index, scanner.nextInt()); scanner.nextLine(); }
            case 4 -> { System.out.print("Novo gênero: "); service.editGenre(index, scanner.nextLine()); }
            case 5 -> { System.out.print("Nova duração: "); service.editDuration(index, scanner.nextInt()); scanner.nextLine(); }
            default -> System.out.println("Campo inválido.");
        }
        System.out.println("Campo atualizado!");
    }

    private static void deleteFilm(Scanner scanner, FilmService service) {
        if (service.isEmpty()) { System.out.println("Nenhum filme cadastrado."); return; }
        service.printFilms();
        System.out.print("Índice do filme para deletar: "); int index = scanner.nextInt(); scanner.nextLine();
        service.removeFilm(index);
        System.out.println("Filme removido!");
    }
}