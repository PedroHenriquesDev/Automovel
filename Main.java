package Automovel;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AutomovelManager manager = new AutomovelManager();
        Scanner sc = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n1 - Incluir automóvel");
            System.out.println("2 - Remover automóvel");
            System.out.println("3 - Alterar dados de automóvel");
            System.out.println("4 - Consultar automóvel por placa");
            System.out.println("5 - Listar automóveis (ordenado)");
            System.out.println("6 - Salvar e sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine(); // limpar buffer

            switch (opcao) {
                case 1 -> {
                    System.out.print("Placa: ");
                    String placa = sc.nextLine();
                    System.out.print("Modelo: ");
                    String modelo = sc.nextLine();
                    System.out.print("Marca: ");
                    String marca = sc.nextLine();
                    System.out.print("Ano: ");
                    int ano = sc.nextInt();
                    System.out.print("Valor: ");
                    double valor = sc.nextDouble();
                    sc.nextLine();
                    manager.inserirAutomovel(new Automovel(placa, modelo, marca, ano, valor));
                }
                case 2 -> {
                    System.out.print("Placa: ");
                    String placa = sc.nextLine();
                    manager.removerAutomovel(placa);
                }
                case 3 -> {
                    System.out.print("Placa: ");
                    String placa = sc.nextLine();
                    System.out.print("Novo modelo: ");
                    String modelo = sc.nextLine();
                    System.out.print("Nova marca: ");
                    String marca = sc.nextLine();
                    System.out.print("Novo ano: ");
                    int ano = sc.nextInt();
                    System.out.print("Novo valor: ");
                    double valor = sc.nextDouble();
                    sc.nextLine();
                    manager.alterarAutomovel(placa, modelo, marca, ano, valor);
                }
                case 4 -> {
                    System.out.print("Placa: ");
                    String placa = sc.nextLine();
                    Automovel a = manager.buscarPorPlaca(placa);
                    System.out.println(a != null ? a : "Automóvel não encontrado.");
                }
                case 5 -> {
                    System.out.print("Ordenar por (placa/modelo/marca): ");
                    String criterio = sc.nextLine();
                    manager.listarAutomoveis(criterio);
                }
                case 6 -> manager.salvarDados();
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 6);

        sc.close();
    }
}
