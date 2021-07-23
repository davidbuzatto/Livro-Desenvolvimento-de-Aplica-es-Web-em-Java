package vendaprodutos.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import vendaprodutos.entidades.ItemVenda;
import vendaprodutos.entidades.Produto;
import vendaprodutos.entidades.Venda;

/**
 * DAO para a entidade ItemVenda.
 *
 * @author Prof. Dr. David Buzatto
 */
public class ItemVendaDAO extends DAO<ItemVenda> {

    public ItemVendaDAO() throws SQLException {
    }

    @Override
    public void salvar( ItemVenda obj ) throws SQLException {

        PreparedStatement stmt = getConnection().prepareStatement(
                "INSERT INTO " + 
                "item_venda( venda_id, produto_id, valor, quantidade ) " + 
                "VALUES( ?, ?, ?, ? );" );

        stmt.setInt( 1, obj.getVenda().getId() );
        stmt.setInt( 2, obj.getProduto().getId() );
        stmt.setBigDecimal( 3, obj.getValor() );
        stmt.setBigDecimal( 4, obj.getQuantidade() );

        stmt.executeUpdate();
        stmt.close();

    }

    @Override
    public void atualizar( ItemVenda obj ) throws SQLException {
        // não faz sentido na nossa implementação,
        // pois não é possível atualizar um item
        // da venda armazenado
    }

    @Override
    public void excluir( ItemVenda obj ) throws SQLException {
        // não faz sentido na nossa implementação,
        // pois não é possível excluir um item
        // da venda armazenado
    }

    @Override
    public List<ItemVenda> listarTodos() throws SQLException {
        
        // nesse caso, não há sentido haver uma listagem por todos
        // os itens de venda, visto que essa entidade é uma entidade
        // de ligação.
        List<ItemVenda> lista = new ArrayList<>();
        return lista;

    }

    @Override
    public ItemVenda obterPorId( int id ) throws SQLException {

        // o identificador dessa entidade é composto!
        // precisamos ter um método especializado...
        return null;

    }
    
    /**
     * Obtenção de todos os itens de venda por um identificador da venda.
     * Esse método será utilizado para o ajuste do estoque das vendas
     * que forem canceladas. Apenas os valores necessários serão obtidos.
     */
    public List<ItemVenda> obterPorIdVenda( int idVenda ) throws SQLException {

        List<ItemVenda> itensVenda = new ArrayList<>();

        PreparedStatement stmt = getConnection().prepareStatement(
                "SELECT" + 
                "    iv.quantidade quantidadeItemVenda, " +
                "    p.id idProduto, " + 
                "    p.estoque estoqueProduto " +
                "FROM" +
                "    item_venda iv, " +
                "    produto p " + 
                "WHERE iv.produto_id = p.id AND " + 
                "      iv.venda_id = ?;" );

        stmt.setInt( 1, idVenda );

        ResultSet rs = stmt.executeQuery();

        while ( rs.next() ) {

            ItemVenda iv = new ItemVenda();
            Produto p = new Produto();
            
            iv.setQuantidade( rs.getBigDecimal( "quantidadeItemVenda" ) );
            iv.setProduto( p );
            
            p.setId( rs.getInt( "idProduto" ) );
            p.setEstoque( rs.getBigDecimal( "estoqueProduto" ) );
            
            itensVenda.add( iv );

        }

        rs.close();
        stmt.close();

        return itensVenda;

    }

}
