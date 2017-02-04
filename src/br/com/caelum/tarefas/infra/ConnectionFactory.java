package br.com.caelum.tarefas.infra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionFactory {

   static {
      try {
         Class.forName("com.mysql.jdbc.Driver");
      } 
      catch (ClassNotFoundException e) {
    	  System.out.println("Não conectou");
         throw new RuntimeException(e);
         
      }
   }

   public Connection conectar() throws SQLException {
      String servidor = "localhost";
      String porta = "3307";
      String database = "cursospringmvc";
      String usuario = "maykell";
      String senha = "mall@2126";
      return DriverManager
         	.getConnection("jdbc:mysql://"+servidor+":"+porta+
            "/"+database+"?user="+usuario+"&password="+senha);
   }

   public static void desconectar(Connection conn) throws SQLException {
      conn.close();
   }
}