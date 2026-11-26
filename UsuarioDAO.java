import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// DAO (Data Access Object): concentra todo o acesso ao banco para a tabela usuarios.
// Todos os comandos usam PreparedStatement com parâmetros (?), nunca concatenação de texto,
// o que evita SQL Injection.
public class UsuarioDAO {

    private static final String SQL_INSERIR =
            "INSERT INTO usuarios (nome, email) VALUES (?, ?)";
    private static final String SQL_ATUALIZAR_EMAIL =
            "UPDATE usuarios SET email = ? WHERE nome = ?";
    private static final String SQL_EXCLUIR =
            "DELETE FROM usuarios WHERE nome = ?";
    private static final String SQL_LISTAR =
            "SELECT id, nome, email FROM usuarios ORDER BY id";

    // CREATE: devolve quantos registros foram inseridos
    public int inserir(Usuario usuario) throws SQLException {
        // try-with-resources: a conexão e o statement são fechados automaticamente
        try (Connection conexao = ConexaoBanco.obterConexao();
             PreparedStatement comando = conexao.prepareStatement(SQL_INSERIR)) {

            comando.setString(1, usuario.getNome());
            comando.setString(2, usuario.getEmail());
            return comando.executeUpdate();
        }
    }

    // UPDATE: atualiza o e-mail do usuário localizado pelo nome; devolve quantos registros mudaram
    public int atualizarEmail(String nome, String novoEmail) throws SQLException {
        try (Connection conexao = ConexaoBanco.obterConexao();
             PreparedStatement comando = conexao.prepareStatement(SQL_ATUALIZAR_EMAIL)) {

            comando.setString(1, novoEmail);
            comando.setString(2, nome);
            return comando.executeUpdate();
        }
    }

    // DELETE: exclui o usuário pelo nome; devolve quantos registros foram removidos
    public int excluirPorNome(String nome) throws SQLException {
        try (Connection conexao = ConexaoBanco.obterConexao();
             PreparedStatement comando = conexao.prepareStatement(SQL_EXCLUIR)) {

            comando.setString(1, nome);
            return comando.executeUpdate();
        }
    }

    // READ: percorre o ResultSet linha a linha e monta a lista de usuários
    public List<Usuario> listarTodos() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();

        try (Connection conexao = ConexaoBanco.obterConexao();
             PreparedStatement comando = conexao.prepareStatement(SQL_LISTAR);
             ResultSet resultado = comando.executeQuery()) {

            while (resultado.next()) {
                int id = resultado.getInt("id");
                String nome = resultado.getString("nome");
                String email = resultado.getString("email");
                usuarios.add(new Usuario(id, nome, email));
            }
        }

        return usuarios;
    }
}
