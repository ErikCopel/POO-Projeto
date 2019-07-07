/*
Classe: Perfil
Atributos:
    Nome – String: Nome do perfil.
    Atividades – Lista_Atividades: Objeto contendo a lista de atividades atrelada ao perfil.

    Métodos:

        setNome():
            
            Entrada:
                
                Nome – String: nome do perfil.
            
            Processamento:
            
                Altera o nome do objeto.

        Remover():
            
            Processamento:
                
                Há a garantia de que sempre existirá dados a serem removidos (os arquivos são criados,
                se necessário, na instanciação da classe). Deleta o arquivo das atividades atreladas ao perfil, e
                remove os dados do perfil do arquivo de perfis. Libera o objeto.

        criaLista():

            Processamento:

                Gera um arquivo para a lista de atividades do perfil, e instancia um objeto Lista.
*/
package app;

public class Perfil {

    public String nome = "";

/* ======================================================================================================
    Metodo          - Perfil
    Entrada         - Nome – String: Nome do perfil.
    Descricao       - "Faz uma busca na lista de perfis criados, verificando se o nome passado já foi criado.
    Caso sim, cria uma lista de atividades, que carregará os dados atrelados ao perfil. Caso não, cria
    uma lista de atividades vazia, gera um arquivo para as atividades do perfil, e adiciona o perfil no
    arquivo com os perfis criados."
    ====================================================================================================== */
    public Perfil(String nome) {
        this.nome = nome;
    }

    /* ======================================================================================================
    Metodo          - getNome()
    Saida           - nome – String: nome do perfil.
    ====================================================================================================== */

    public String getNome() {
        return this.nome;
    }
    /* ======================================================================================================
    Metodo          - setNome()
    Descricao       - altera nome do objeto
    Entrada         - Nome – String: nome do perfil.
    ====================================================================================================== */

    public void setNome(String nome) {
        this.nome = nome;
    }
    /* ======================================================================================================
    Metodo          - remover()
    Descricao       -
    Entrada         - 
    Saida           -
    ====================================================================================================== */
    public void remover() {
        
    }
    /* ======================================================================================================
    Metodo          - criaLista()
    Descricao       -
    Entrada         - 
    Saida           -
    ====================================================================================================== */
    public void criaLista() {
        
    }
    
}

