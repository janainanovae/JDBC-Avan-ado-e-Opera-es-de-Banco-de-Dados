public class Usuario {

    private int id;
    private String nome;
    private String email;

    // Usado ao ler do banco (o id já existe)
    public Usuario(int id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    // Usado ao inserir (o id é gerado pelo banco)
    public Usuario(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "ID: " + id + " - Nome: " + nome + " - Email: " + email;
    }
}
