import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static List<Film> films = new ArrayList<>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1 - Adicionar filme");
            System.out.println("2 - Listar filmes");
            System.out.println("3 - Editar filme");
            System.out.println("4 - Excluir filme");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            option = scanner.nextInt();
            scanner.nextLine(); // limpar buffer

            switch (option) {

                case 1:
                    adicionarFilme(scanner);
                    break;

                case 2:
                    listarFilmes();
                    break;

                case 3:
                    editarFilme(scanner);
                    break;

                case 4:
                    excluirFilme(scanner);
                    break;

                case 0:
                    System.out.println("Encerrando o programa...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (option != 0);

        scanner.close();
    }

    // CREATE
    public static void adicionarFilme(Scanner scanner) {
        System.out.println("Digite o título:");
        String title = scanner.nextLine();

        System.out.println("Digite o diretor:");
        String director = scanner.nextLine();

        System.out.println("Digite o ano:");
        int year = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Digite o gênero:");
        String genre = scanner.nextLine();

        System.out.println("Digite a duração (em minutos):");
        int duration = scanner.nextInt();
        scanner.nextLine();

        films.add(new Film(title, director, year, genre, duration));

        System.out.println("Filme adicionado com sucesso!");
    }

    // READ
    public static void listarFilmes() {

        if (films.isEmpty()) {
            System.out.println("Nenhum filme cadastrado.");
            return;
        }

        System.out.println("\nLista de filmes:");
        for (int i = 0; i < films.size(); i++) {
            System.out.println(i + " - " + films.get(i));
        }
    }

    // UPDATE
    public static void editarFilme(Scanner scanner) {

        listarFilmes();

        if (films.isEmpty()) return;

        System.out.println("Digite o índice do filme que deseja editar:");
        int index = scanner.nextInt();
        scanner.nextLine();

        if (index < 0 || index >= films.size()) {
            System.out.println("Índice inválido!");
            return;
        }

        Film film = films.get(index);

        System.out.println("Novo título:");
        film.setTitle(scanner.nextLine());

        System.out.println("Novo diretor:");
        film.setDirector(scanner.nextLine());

        System.out.println("Novo ano:");
        film.setReleaseYear(scanner.nextInt());
        scanner.nextLine();

        System.out.println("Novo gênero:");
        film.setGenre(scanner.nextLine());

        System.out.println("Nova duração (em minutos):");
        film.setDuration(scanner.nextInt());
        scanner.nextLine();

        System.out.println("Filme atualizado com sucesso!");
    }

    // DELETE
    public static void excluirFilme(Scanner scanner) {

        listarFilmes();

        if (films.isEmpty()) return;

        System.out.println("Digite o índice do filme que deseja excluir:");
        int index = scanner.nextInt();
        scanner.nextLine();

        if (index < 0 || index >= films.size()) {
            System.out.println("Índice inválido!");
            return;
        }

        films.remove(index);
        System.out.println("Filme removido com sucesso!");
    }
}