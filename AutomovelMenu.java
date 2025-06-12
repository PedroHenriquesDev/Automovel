package Automovel;
import Automovel.Automovel;
import java.io.*;
import java.util.*;

public class AutomovelManager {
    private ArrayList<Automovel> lista = new ArrayList<>();
    private final String arquivo = "automoveis.txt";

    public AutomovelManager() {
        carregarDados();
    }

    public void inserirAutomovel(Automovel a) {
        if (buscarPorPlaca(a.getPlaca()) != null) {
            System.out.println("Erro: Placa já cadastrada.");
            return;
        }
        lista.add(a);
        System.out.println("Automóvel incluído com sucesso.");
    }

    public void removerAutomovel(String placa) {
        Automovel a = buscarPorPlaca(placa);
        if (a != null) {
            lista.remove(a);
            System.out.println("Automóvel removido.");
        } else {
            System.out.println("Automóvel não encontrado.");
        }
    }

    public void alterarAutomovel(String placa, String modelo, String marca, int ano, double valor) {
        Automovel a = buscarPorPlaca(placa);
        if (a != null) {
            a.setModelo(modelo);
            a.setMarca(marca);
            a.setAno(ano);
            a.setValor(valor);
            System.out.println("Dados alterados com sucesso.");
        } else {
            System.out.println("Automóvel não encontrado.");
        }
    }

    public Automovel buscarPorPlaca(String placa) {
        for (Automovel a : lista) {
            if (a.getPlaca().equalsIgnoreCase(placa)) {
                return a;
            }
        }
        return null;
    }

    public void listarAutomoveis(String criterio) {
        Comparator<Automovel> comparator = switch (criterio.toLowerCase()) {
            case "placa" -> Comparator.comparing(Automovel::getPlaca);
            case "modelo" -> Comparator.comparing(Automovel::getModelo);
            case "marca" -> Comparator.comparing(Automovel::getMarca);
            default -> null;
        };

        if (comparator == null) {
            System.out.println("Critério inválido.");
            return;
        }

        lista.stream().sorted(comparator).forEach(System.out::println);
    }

    public void salvarDados() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo))) {
            for (Automovel a : lista) {
                writer.write(a.toCSV());
                writer.newLine();
            }
            System.out.println("Dados salvos com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados: " + e.getMessage());
        }
    }

    private void carregarDados() {
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length == 5) {
                    Automovel a = new Automovel(
                            dados[0], dados[1], dados[2],
                            Integer.parseInt(dados[3]),
                            Double.parseDouble(dados[4])
                    );
                    lista.add(a);
                }
            }
        } catch (IOException e) {
            System.out.println("Arquivo não encontrado. Um novo será criado ao salvar.");
        }
    }
}
