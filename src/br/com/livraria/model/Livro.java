package br.com.livraria.model;

public class Livro {

    private String titulo;
    private String isbn;
    private double preco;
    private int estoque;
    private Autor autor;


    public Livro(String titulo, String isbn, double preco,
                 int estoque, Autor autor) {
        this.titulo = titulo;
        this.isbn = isbn;
        this.preco = preco;
        this.estoque = estoque;
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getIsbn() {
        return isbn;
    }

    public double getPreco() {
        return preco;
    }

    public int getEstoque() {
        return estoque;
    }

    public Autor getAutor() {
        return autor;
    }

    public double calcularPrecoComDesconto(double percentual) {
        if (percentual < 0 || percentual > 100) {
            throw new IllegalArgumentException(
                    "Desconto deve estar entre 0 e 100%.");
        }

        return preco * (1 - percentual / 100);
    }

    public void retirarEstoque(int quantidade) {
        if (quantidade <= 0 || quantidade > estoque) {
            throw new IllegalArgumentException(
                    "Quantidade inválida para o estoque.");
        }

        estoque -= quantidade;
    }

    @Override
    public String toString() {
        return "Título: " + titulo
                + " | ISBN: " + isbn
                + " | Autor: " + autor.getNome()
                + " | Preço: R$ " + String.format("%.2f", preco)

                + " | Estoque: " + estoque;
    }
}