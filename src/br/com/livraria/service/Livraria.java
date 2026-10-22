package br.com.livraria.service;


import java.util.ArrayList;
import java.util.List;

import br.com.livraria.model.Autor;
import br.com.livraria.model.Livro;
import br.com.livraria.model.Cliente;
import br.com.livraria.model.Venda;

public class Livraria {
    private List<Autor> autores = new ArrayList<>();
    private List<Livro> livros = new ArrayList<>();
    private List<Cliente> clientes = new ArrayList<>();
    private List<Venda> vendas = new ArrayList<>();

    // AUTOR

    public Autor buscarAutor(String nome) {
        if (nome == null) {
            return null;
        }

        for (Autor autor : autores) {
            if (autor.getNome().equalsIgnoreCase(nome.trim())) {
                return autor;
            }
        }

        return null;
    }

    public boolean cadastrarAutor(String nome, String nacionalidade) {
        if (nome == null || nome.isBlank()
                || nacionalidade == null
                || nacionalidade.isBlank()) {
            System.out.println("Preencha os dados do autor.");
            return false;
        }

        if (buscarAutor(nome) != null) {
            System.out.println("Autor já cadastrado.");
            return false;
        }

        autores.add(new Autor(nome.trim(), nacionalidade.trim()));
        System.out.println("Autor cadastrado com sucesso!");
        return true;
    }

    // LIVRO

    public Livro buscarLivro(String isbn) {
        if (isbn == null) {
            return null;
        }

        for (Livro livro : livros) {
            if (livro.getIsbn().equalsIgnoreCase(isbn.trim())) {
                return livro;
            }
        }

        return null;
    }

    public boolean cadastrarLivro(String titulo, String isbn,
                                  double preco, int estoque,
                                  String nomeAutor) {
        if (titulo == null || titulo.isBlank()
                || isbn == null || isbn.isBlank()
                || !Double.isFinite(preco) || preco <= 0
                || estoque < 0) {
            System.out.println("Dados do livro inválidos.");
            return false;
        }

        if (buscarLivro(isbn) != null) {
            System.out.println("ISBN já cadastrado.");
            return false;
        }

        Autor autor = buscarAutor(nomeAutor);

        if (autor == null) {
            System.out.println(
                    "Autor não encontrado. Cadastre-o primeiro.");
            return false;
        }

        Livro livro = new Livro(
                titulo.trim(), isbn.trim(), preco, estoque, autor);

        livros.add(livro);
        System.out.println("Livro cadastrado com sucesso!");
        return true;
    }

    // CLIENTE

    public Cliente buscarCliente(String cpf) {
        if (cpf == null) {
            return null;
        }

        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(cpf.trim())) {
                return cliente;
            }
        }

        return null;
    }

    public boolean cadastrarCliente(String nome, String cpf,
                                    String email) {
        if (nome == null || nome.isBlank()
                || cpf == null || cpf.isBlank()
                || email == null || email.isBlank()) {
            System.out.println("Preencha os dados do cliente.");
            return false;
        }

        if (buscarCliente(cpf) != null) {
            System.out.println("CPF já cadastrado.");
            return false;
        }

        clientes.add(new Cliente(
                nome.trim(), cpf.trim(), email.trim()));

        System.out.println("Cliente cadastrado com sucesso!");
        return true;
    }

    // VENDA

    public boolean venderLivro(String isbn, String cpf,
                               int quantidade, double desconto) {
        Livro livro = buscarLivro(isbn);
        Cliente cliente = buscarCliente(cpf);

        if (livro == null) {
            System.out.println("Livro não encontrado.");
            return false;
        }

        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return false;
        }

        if (quantidade <= 0 || quantidade > livro.getEstoque()) {
            System.out.println(
                    "Quantidade inválida ou estoque insuficiente.");
            return false;
        }

        if (!Double.isFinite(desconto) || desconto < 0 || desconto > 100) {
            System.out.println("Desconto inválido.");
            return false;
        }

        Venda venda = new Venda(livro, cliente, quantidade, desconto);
        vendas.add(venda);

        System.out.println(venda);
        return true;
    }

    // LISTAGENS

    public void listarAutores() {
        if (autores.isEmpty()) {
            System.out.println("Nenhum autor cadastrado.");
            return;
        }

        for (Autor autor : autores) {
            System.out.println(autor);
        }
    }

    public void listarLivros() {
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado.");
            return;
        }

        for (Livro livro : livros) {
            System.out.println(livro);
        }
    }

    public void listarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }

        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }
    }

    public void listarVendas() {
        if (vendas.isEmpty()) {
            System.out.println("Nenhuma venda realizada.");
            return;
        }

        for (Venda venda : vendas) {
            System.out.println(venda);
        }
    }
}