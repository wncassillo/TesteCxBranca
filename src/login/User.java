package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/** Classe User é responsável por conectar ao BD,
 * validar Login do Usuário 
 * e guardar o nome do mesmo. **/
public class User {
    /**Método conectarBD: 
     * Responsável por criar uma coenxão com o bd
     * @return o método retorna a conexão, para ser usada em outros métodos. **/
    public Connection conectarBD(){ 
        Connection conn = null;
        try{
            Class.forName("com.mysql.Driver.Manager").newInstance();
            String url = "jdbc:mysql://127.0.0.1/test?user=lopes&password=123";
            conn =  DriverManager.getConnection(url);
        }catch (Exception e) { }
        return conn;} 
    
    /** As variaveis da classe são definidas e inicializadas. **/
    public String nome="";
    /** A variavel nome guardará o nome do usuario,
     * caso este consiga fazer login.**/
    public boolean result = false;
    /** A variavel booleana result demarcar se o usuário fez login ou não.
     * O valor da mesma se altera para True caso o login seja bem-sucedido.**/
    
    /**Método verificarUsuario: 
     * Esse método é o responsável por fazer a consulta MYSQL 
     * com o login e senha informados pelo usuario, para então determinar
     * se o login foi bem-sucedido ou não.
     * @return valor booleano que indica se o login foi bem-sucedido ou não.**/
    public boolean verificarUsuario(String login, String senha){
        String sql = "";
        Connection conn = conectarBD();
        //INSTRUÇÃO SQL
        /**Concatenação de String predefinida + login e senha
         * inseridos pelo usuário, gerando a linha de sql que
         realizará a consulta.**/
        sql += "select nome from usuarios ";
        sql +="where login = " + "'" + login + "'";
        sql += " and senha = " + "'" + senha + "';";
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);  
            /** IF verifica se houve um retorno da consulta realizada
             * Em caso negativo, é lançado um erro.
             * Em caso positivo o código continua, onde as variaveis
             * result e nome recebem novos valores.**/
            if(rs.next()){    
                result = true;
                nome = rs.getString("nome");}
            }catch (Exception e) { } 
            return result;} 
        }//fim da class
