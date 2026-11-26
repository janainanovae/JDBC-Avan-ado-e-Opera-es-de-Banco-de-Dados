import java.sql.SQLException;
import java.util.List;

public class AppUsuarios {

    public static void main(String[] args) {
        UsuarioDAO dao = new UsuarioDAO();

        try {
            // CREATE
            System.out.println("===== Inserindo usuários =====");
            dao.inserir(new Usuario("João Silva", "joao@email.com"));
            dao.inserir(new Usuario("Maria Santos", "maria@email.com"));
            dao.inserir(new Usuario("Pedro Oliveira", "pedro@email.com"));
            System.out.println("3 usuários inseridos.");
            listar(dao);

            // UPDATE
            System.out.println();
            System.out.println("===== Atualizando o e-mail da Maria Santos =====");
            int atualizados = dao.atualizarEmail("Maria Santos", "maria.santos@novoemail.com");
            System.out.println(atualizados + " registro(s) atualizado(s).");
            listar(dao);

            // DELETE
            System.out.println();
            System.out.println("===== Excluindo o Pedro Oliveira =====");
            int excluidos = dao.excluirPorNome("Pedro Oliveira");
            System.out.println(excluidos + " registro(s) excluído(s).");
            listar(dao);

        } catch (SQLException e) {
            System.out.println("Erro de banco de dados: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState() + " | Código: " + e.getErrorCode());
        }
    }

    // READ: consulta todos os usuários e exibe no console
    private static void listar(UsuarioDAO dao) throws SQLException {
        List<Usuario> usuarios = dao.listarTodos();

        System.out.println("--- Usuários cadastrados ---");
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
            return;
        }
        for (Usuario usuario : usuarios) {
            System.out.println(usuario);
        }
    }
}
