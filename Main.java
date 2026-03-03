import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static List<Film> films = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Quantos filmes você quer cadastrar?");
        int quantidade = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha

        // CREATE - pedir ao usuário para digitar
        for (int i = 0; i < quantidade; i++) {
            System.out.println("Digite o título do filme:");
            String title = scanner.nextLine();

            System.out.println("Digite o diretor:");
            String director = scanner.nextLine();

            System.out.println("Digite o ano de lançamento:");
            int releaseYear = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            System.out.println("Digite o gênero:");
            String genre = scanner.nextLine();

            films.add(new Film(title, director, releaseYear, genre));
        }

        // READ - mostrar filmes cadastrados
        System.out.println("\nLista de filmes cadastrados:");
        for (Film film : films) {
            System.out.println(film);
        }

        // Exemplo de UPDATE
        if (!films.isEmpty()) {
            System.out.println("\nAtualizando o título do primeiro filme...");
            System.out.println("Digite o novo título:");
            String novoTitulo = scanner.nextLine();
            films.get(0).setTitle(novoTitulo);
        }

        // READ novamente
        System.out.println("\nLista de filmes atualizada:");
        for (Film film : films) {
            System.out.println(film);
        }

        // Exemplo de DELETE
        if (!films.isEmpty()) {
            System.out.println("\nRemovendo o último filme da lista...");
            films.remove(films.size() - 1);
        }

        // READ final
        System.out.println("\nLista de filmes final:");
        for (Film film : films) {
            System.out.println(film);
        }

        scanner.close();
    }
}