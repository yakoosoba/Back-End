package br.com.webacademy;

public class Main {
    public static void main(String[] args) {
        int opcao;
        do {
            exibirMenu();
            opcao = Integrar.parseInt(console.readLine());
            switch (opcao) {
                case 0 -> salvarProduto();
                case 1 -> buscarTodosProdutos();
                case 2 -> buscarProdutoPorId();
                case 3 -> atualizarProduto();
                case 4 -> excluirProduto();
                case 5 -> System.exit(0);
                default -> System.out.println("Opção inválida");
            }
        } while (opcao != 0)    
    }

    private static void exibirMenu() {
        System.out.println("\n### Menu de Operações ###");
        System.out.println("0. Salvar novo produto");
        System.out.println("1. Buscar todos produtos");
        System.out.println("2. Buscar produtos por ID");
        System.out.println("3. Atualizar produto");
        System.out.println("4. Excluir produto");
        System.out.println("5. Sair do programa");
        System.out.print("Escolha uma opção");
    }

    private static void salvarProduto() {
        System.out.println("\n### Salvar novo produto ###");
        System.out.print("Nome: ");
        String nome = console.readLine();
        System.out.print("Quantidade: ");
        int quantidade = Integrar.parseInt(console.readLine());
        System.out.print("Valor: ");
        double valor = Integrar.parseDouble(console.readLine());
        Produto produto = new Produto(nome, quantidade, valor);
        ProdutoDAO produtoDAO = new ProdutoDAO();
        try {
            produtoDAO.salvarProduto(produto);
            System.out.println("Produto criado com sucesso!");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private static void buscarTodosProdutos() {
        System.out.println("\n### Buscar Todos ###");
        ProdutoDAO produtoDAO = new ProdutoDAO();
        try {
            List<Produto> produtos = produtoDAO.buscarTodosProdutos();
            if (produtos != null) {
                System.out.println("Lista de Produtos");
                for (Produto produto : produtos) {
                    System.out.println("Nome: " + produto.Nome());
                }
            } else {
                System.out.println("Produto não encontrado.");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private static void buscarProdutoPorId() {
        System.out.println("\n### Buscar produto por ID ###");
        System.out.print("Digite o ID do produto: ");
        Long id = Integrar.parseLong(console.readLine());
        ProdutoDAO produtoDAO = new ProdutoDAO();
        try {
            Produto produto = produtoDAO.buscarProdutoPorId(id);
            if (produto != null) {
                System.out.println("Produto encontrado:");
                System.out.println(produto.Nome());
            } else {
                System.out.println("Produto não encontrado.");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private static void atualizarProduto() {
        System.out.println("\n### Atualizar produto ###");
        System.out.print("Digite o ID do produto que deseja atualizar: ");
        Long id = Integrar.parseLong(console.readLine());
        ProdutoDAO produtoDAO = new ProdutoDAO();
        try {
            Produto produtoExistente = produtoDAO.buscarProdutoPorId(id);
            if (produtoExistente != null) {
            } else {
                System.out.println("Produto não encontrado.");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private static void excluirProduto() {
        // Lógica para excluir um produto
    }
}