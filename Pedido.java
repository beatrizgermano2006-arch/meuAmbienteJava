import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Pedido {
    private static int ContadorId = 1;
    private static  DateTimeFormatter FormatoHr =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    
    private int Id;
    private Cliente cliente;
    private List<ItemDePedido> itens;
    private LocalDateTime dataCriacao;

    //Construtor para criar pedido, inclui o cliente e a data de criação do pedido
    public Pedido(Cliente cliente) {
        this.Id = ContadorId++;
        this.cliente = cliente;
        this.itens = new ArrayList<>();
        this.dataCriacao = LocalDateTime.now();
    }

    //Adicionar um item ao pedido
    public void adicionarItem(ItemDePedido item) {
        itens.add(item);
    }

    //Remover um item de pedido por índice
     public boolean removerItem(int indice) {
        if (indice >= 0 && indice < itens.size()) {
            itens.remove(indice);
            return true;
        }
        return false;
    }

    //Limpar todos os itens do pedido (para caso o pedido seja cancelado ou refeito)
    public int getId() {return Id;}
    public Cliente getCliente() {return cliente;}
    public List<ItemDePedido> getItens() {return Collections.unmodifiableList(itens);}
    public LocalDateTime getDataCriacao() {return dataCriacao;}
    public String getDataFormatada() {return dataCriacao.format(FormatoHr);}

    @Override
    public String toString() {
        return String.format("Pedido #%03d", Id);
    }

     // Chama o método de cada item
    public double calcularTotal() {
    double total = 0;
    for (ItemDePedido item : itens) {
        total += item.calcularTotal();
    }
    return total;
}

    // Novo método para representação completa
    public String getRepresentacaoTexto() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Pedido #%03d | Cliente: %s | Data: %s\n", Id, cliente.getNome(), getDataFormatada()));
        sb.append(String.format("  %s TOTAL: R$ %f", "", calcularTotal()));
        return sb.toString();
    }
}