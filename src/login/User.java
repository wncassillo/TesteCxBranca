package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class User {
    //ponto de Início
    public Connection conectarBD(){ //Ponto 02 tentando conexão com bd
        Connection conn = null;
        try{
            Class.forName("com.mysql.Driver.Manager").newInstance();
            String url = "jdbc:mysql://127.0.0.1/test?user=lopes&password=123";
            conn =  DriverManager.getConnection(url);
        }catch (Exception e) { }
        return conn;}
    //Ponto 03 retorno da conexão
    //Ponto Final 01: Falha na conexão ao BD

    //Ponto 1 Variaveis de controle de login
    //1 pq não está dentro de um método, roda quando uma isntancia da classe é criada.
    public String nome="";
    public boolean result = false;
    
    public boolean verificarUsuario(String login, String senha){
        //Ponto 04 entrando com informações pra pegar dados no BD
        String sql = "";
        Connection conn = conectarBD();
        //INSTRUÇÃO SQL
        sql += "select nome from usuarios ";
        sql +="where login = " + "'" + login + "'";
        sql += " and senha = " + "'" + senha + "';";
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            //Ponto 05 Retorno dos dados do BD
            
            //Ponto 06 IF Verificando resultados
            if(rs.next()){ //Ponto final 02: Falha na consulta
                
                //Ponto 07 "Update" variaves de controle
                result = true;
                nome = rs.getString("nome");}
            }catch (Exception e) { } 
            //Ponto 08 return da variavel result.
            return result;} 
            //Ponto Final 03: Sucesso!
        }//fim da class
