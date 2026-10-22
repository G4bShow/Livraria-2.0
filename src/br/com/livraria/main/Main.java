package br.com.livraria.main;

import java.util.Scanner;
import br.com.livraria.service.Livraria;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Livraria livraria = new Livraria();

    public static void main(String[] args) {
        int opcao;

        do {
            exibirMenu();
            opcao = lerInt("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    cadastrarAutor();
                    break;

                case 2:
                    cadastrarLivro();
                    break;

                case 3:
                    cadastrarCliente();
                    break;

                case 4:
                    realizarVenda();
                    break;

                case 5:
                    livraria.listarAutores();
                    break;

                case 6:
                    livraria.listarLivros();
                    break;

                case 7:
                    livraria.listarClientes();
                    break;

                case 8:
                    livraria.listarVendas();
                    break;

                case 0:
                    System.out.println("Sistema encerrado.");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\n===== SISTEMA DE LIVRARIA =====");
        System.out.println("1 - Cadastrar autor");
        System.out.println("2 - Cadastrar livro");
        System.out.println("3 - Cadastrar cliente");
        System.out.println("4 - Vender livro");
        System.out.println("5 - Listar autores");
        System.out.println("6 - Listar livros");
        System.out.println("7 - Listar clientes");
        System.out.println("8 - Listar vendas");
        System.out.println("0 - Sair");
    }

    private static void cadastrarAutor() {
        String nome = lerTexto("Nome do autor: ");
        String nacionalidade = lerTexto("Nacionalidade: ");

        livraria.cadastrarAutor(nome, nacionalidade);
    }

    private static void cadastrarLivro() {
        String titulo = lerTexto("Título: ");
        String isbn = lerTexto("ISBN: ");
        double preco = lerDouble("Preço: R$ ");
        int estoque = lerInt("Quantidade em estoque: ");
        String autor = lerTexto("Nome do autor cadastrado: ");

        livraria.cadastrarLivro(
                titulo, isbn, preco, estoque, autor);
    }

    private static void cadastrarCliente() {
        String nome = lerTexto("Nome do cliente: ");
        String cpf = lerTexto("CPF: ");
        String email = lerTexto("E-mail: ");

        livraria.cadastrarCliente(nome, cpf, email);
    }

    private static void realizarVenda() {
        String isbn = lerTexto("ISBN do livro: ");
        String cpf = lerTexto("CPF do cliente: ");
        int quantidade = lerInt("Quantidade: ");
        double desconto = lerDouble("Desconto (%): ");

        livraria.venderLivro(isbn, cpf, quantidade, desconto);
    }

    private static String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine().trim();
    }

    private static int lerInt(String mensagem) {
        while (true) {
            try {
                return Integer.parseInt(lerTexto(mensagem));
            } catch (NumberFormatException e) {
                System.out.println("Digite um número inteiro válido.");
            }
        }
    }

    private static double lerDouble(String mensagem) {
        while (true) {
            try {
                String valor = lerTexto(mensagem).replace(",", ".");
                return Double.parseDouble(valor);
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido.");
            }
        }
    }
}