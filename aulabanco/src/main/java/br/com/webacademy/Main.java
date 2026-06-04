package br.com.webacademy;

import java.util.List;
import java.util.Scanner;
import br.com.webacademy.Produto;
import br.com.webacademy.ProdutoDAO;


public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final ProdutoDAO produtoDAO = new ProdutoDAO();

    public static void main(String[] args) {
        int opcao;
        do {
            exibirMenu();
            opcao = Integer.parseInt(scanner.nextLine());
            switch (opcao) {
                case 0 -> salvarProduto();
                case 1 -> buscarTodosProdutos();
                case 2 -> buscarProdutoPorId();
                case 3 -> atualizarProduto();
                case 4 -> excluirProduto();
                case 5 -> System.exit(0);
                default -> System.out.println("Opção Inválida");
            }

        } while (opcao != 5);
    }

    private static void exibirMenu() {
        System.out.println("\n### Menu de Operações ###");
        System.out.println("0. Salvar novo produto");
        System.out.println("1. Buscar todos produtos");
        System.out.println("2. Buscar produto por ID");
        System.out.println("3. Atualizar produto");
        System.out.println("4. Excluir produto");
        System.out.println("5. Sair do programa");
        System.out.print("Escolha uma opção: ");
    }

    private static void salvarProduto() {
        System.out.println("\n### Criar Novo Produto ###");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Quantidade: ");
        int quantidade = Integer.parseInt(scanner.nextLine());

        System.out.print("Valor: ");
        Double valor = Double.parseDouble(scanner.nextLine());

        Produto produto = new Produto(nome, quantidade, valor);

        try {
            produtoDAO.salvar(produto);
            System.out.println("Produto criado com sucesso!");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private static void buscarTodosProdutos() {
        System.out.println("\n### Buscar Todos os Produtos ###");

        try {
            List<Produto> produtos = produtoDAO.buscarTodos();

            if (produtos != null && !produtos.isEmpty()) {
                System.out.println("Lista de Produtos:");
                for (Produto produto : produtos) {
                    System.out.println("ID: " + produto.id() + ", Nome: " + produto.nome() + ", Quantidade: "
                            + produto.quantidade() + ", Valor: " + produto.valor());
                }
            } else {
                System.out.println("Nenhum produto encontrado.");
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private static void buscarProdutoPorId() {
        System.out.println("\n### Buscar Produto por ID ###");
        System.out.print("Digite o ID do produto: ");
        Long id = Long.parseLong(scanner.nextLine());

        try {
            Produto produto = produtoDAO.buscarPorId(id);
            if (produto != null) {
                System.out.println("Produto encontrado:");
                System.out.println("ID: " + produto.id() + ", Nome: " + produto.nome() + ", Quantidade: "
                        + produto.quantidade() + ", Valor: " + produto.valor());
            } else {
                System.out.println("Produto não encontrado.");
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private static void atualizarProduto() {
        System.out.println("\n### Atualizar Produto ###");
        System.out.print("Digite o ID do produto que deseja atualizar: ");
        Long id = Long.parseLong(scanner.nextLine());

        try {
            Produto produtoExistente = produtoDAO.buscarPorId(id);
            if (produtoExistente != null) {
                System.out.print("Novo nome (atual: " + produtoExistente.nome() + "): ");
                String nome = scanner.nextLine();
                System.out.print("Nova quantidade (atual: " + produtoExistente.quantidade() + "): ");
                int quantidade = Integer.parseInt(scanner.nextLine());
                System.out.print("Novo valor (atual: " + produtoExistente.valor() + "): ");
                Double valor = Double.parseDouble(scanner.nextLine());
                Produto produtoAtualizado = new Produto(id, nome, quantidade, valor);
                try {
                    produtoDAO.atualizar(produtoAtualizado);
                    System.out.println("Produto atualizado com sucesso!");
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            } else {
                System.out.println("Produto não encontrado.");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private static void excluirProduto() {
        System.out.println("\n### Excluir Produto ###");
        System.out.print("Digite o ID do produto que deseja excluir: ");
        Long id = Long.parseLong(scanner.nextLine());

        try {
            Produto produtoExistente = produtoDAO.buscarPorId(id);
            if (produtoExistente != null) {
                produtoDAO.excluir(produtoExistente.id());
                System.out.println("Produto excluído com sucesso!");
            } else {
                System.out.println("Produto não encontrado.");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}