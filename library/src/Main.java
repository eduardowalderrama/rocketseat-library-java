import domain.BibliotecaService;
import model.Autor;
import model.Livro;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        BibliotecaService bibliotecaService = getBibliotecaService();

        // Loop para a interacao com o usuario
        while (true) {
            System.out.println("Deseja ver os livros disponiveis? (sim/nao)");
            String resposta = scanner.nextLine().toLowerCase();

            if (resposta.equals("sim")) {
                List<Livro> livrosDisponiveis = bibliotecaService.listarLivrosDisponiveis();

                if (livrosDisponiveis.isEmpty()) {
                    System.out.println("Nao ha livros disponiveis no momento.");
                } else {
                    System.out.println("Livros disponiveis:");
                    for (Livro livro : livrosDisponiveis) {
                        System.out.println(livro.getId() + ": " + livro.getTitulo());
                    }

                    System.out.println("Digite o ID do livro que voce deseja emprestar:");
                    int idLivro = scanner.nextInt();
                    scanner.nextLine();

                    Livro livroSelecionado = bibliotecaService.buscarLivroPorId(idLivro);

                    if (livroSelecionado != null && livroSelecionado.isDisponivel()) {
                        System.out.println("Digite seu nome:");
                        String nomeUsuario = scanner.nextLine();

                        bibliotecaService.emprestarLivro(livroSelecionado, nomeUsuario);
                        System.out.println("O livro " + livroSelecionado.getTitulo() + " de " +
                                livroSelecionado.getAutor().getNome() + " foi emprestado para " + nomeUsuario);
                    } else {
                        System.out.println("Livro nao encontrado ou nao disponivel para emprestimo.");
                    }
                }
            } else if (resposta.equals("nao")) {
                System.out.println("Obrigado por utilizar o sistema da biblioteca.");
                break;
            } else {
                System.out.println("Resposta invalida. Por favor, responda com 'sim' ou 'nao'.");
            }
        }

        scanner.close();
    }

    private static BibliotecaService getBibliotecaService() {
        BibliotecaService bibliotecaService = new BibliotecaService();

        // Adicionando autores
        Autor autor1 = new Autor(1, "Benjamin Graham", new Date());
        Autor autor2 = new Autor(2, "George Samuel Clason", new Date());

        bibliotecaService.adicionarAutor(autor1);
        bibliotecaService.adicionarAutor(autor2);

        // Adicionando livros
        Livro livro1 = new Livro(1, "O Investidor Inteligente", autor1);
        Livro livro2 = new Livro(2, "O Homem Mais Rico da Babilonia", autor2);
        Livro livro3 = new Livro(3, "A Interpretacao das Demonstracoes Financeiras", autor1);

        bibliotecaService.adicionarLivro(livro1);
        bibliotecaService.adicionarLivro(livro2);
        bibliotecaService.adicionarLivro(livro3);

        return bibliotecaService;
    }

}