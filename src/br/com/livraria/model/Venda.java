package br.com.livraria.model;


public class Venda {
    private Livro livro;
    private Cliente cliente;
    private int quantidade;
    private double desconto;
    private double total;

    public Venda(Livro livro, Cliente cliente,
                 int quantidade, double desconto) {
        if (quantidade <= 0 || quantidade > livro.getEstoque()) {
            throw new IllegalArgumentException(
                    "Quantidade inválida ou estoque insuficiente.");
        }

        if (desconto < 0 || desconto > 100) {
            throw new IllegalArgumentException(
                    "Desconto deve estar entre 0 e 100%.");
        }

        this.livro = livro;
        this.cliente = cliente;
        this.quantidade = quantidade;
        this.desconto = desconto;

        double precoComDesconto =
                livro.calcularPrecoComDesconto(desconto);

        this.total = precoComDesconto * quantidade;

        livro.retirarEstoque(quantidade);
    }

    public double getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "\n===== COMPROVANTE DE VENDA ====="
                + "\nCliente: " + cliente.getNome()
                + "\nLivro: " + livro.getTitulo()
                + "\nQuantidade: " + quantidade
                + "\nDesconto: " + desconto + "%"
                + "\nTotal: R$ " + String.format("%.2f", total)
                + "\nEstoque restante: " + livro.getEstoque();
    }
}