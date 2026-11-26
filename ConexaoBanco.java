import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBanco {

    private static final String URL = "jdbc:mysql://localhost:3306/jdbc_usuarios?serverTimezone=UTC";

    // Usuário e senha vêm de variáveis de ambiente (DB_USER e DB_PASSWORD), para a senha
    // não ficar escrita no código que vai para o GitHub. Se não existirem, usa os valores padrão abaixo.
    private static final String USUARIO = valorOuPadrao("DB_USER", "root");
    private static final String SENHA = valorOuPadrao("DB_PASSWORD", "");

    private static String valorOuPadrao(String variavel, String padrao) {
        String valor = System.getenv(variavel);
        return (valor == null || valor.isEmpty()) ? padrao : valor;
    }

    // Abre uma nova conexão com o MySQL. Quem chamar é responsável por fechá-la (try-with-resources).
    public static Connection obterConexao() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}
