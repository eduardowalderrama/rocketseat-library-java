package domain;

import model.Autor;
import model.Biblioteca;
import model.Emprestimo;
import model.Livro;

import java.util.ArrayList;
import java.util.List;

public class BibliotecaService {

    Biblioteca biblioteca = new Biblioteca();

    public void emprestarLivro(Livro livroSelecionado, String nomeUsuario) {
        List<Emprestimo> emprestimos = biblioteca.getEmprestimos();
        if (livroSelecionado.isDisponivel()) {
            Emprestimo emprestimo = new Emprestimo(livroSelecionado, nomeUsuario);
            emprestimos.add(emprestimo);
            livroSelecionado.setDisponivel(false);
        } else {
            System.out.println("Livro nao disponivel para emprestimo.");
        }
    }

    public void adicionarAutor(Autor autor) {
        List<Autor> autores = biblioteca.getAutores();
        autores.add(autor);

        biblioteca.setAutores(autores);
    }

    public void adicionarLivro(Livro livro) {
        List<Livro> livros = biblioteca.getLivros();
        livros.add(livro);

        biblioteca.setLivros(livros);
    }

    public List<Livro> listarLivrosDisponiveis() {
        List<Livro> livrosDisponiveis = new ArrayList<>();
        List<Livro> livros = biblioteca.getLivros();
        for (Livro livro : livros) {
            if (livro.isDisponivel()) {
                livrosDisponiveis.add(livro);
            }
        }
        return livrosDisponiveis;
    }

    public Livro buscarLivroPorId(int idLivro) {
        List<Livro> livros = biblioteca.getLivros();
        for (Livro livro : livros) {
            if (livro.getId() == idLivro) {
                return livro;
            }
        }
        return null;
    }
}
