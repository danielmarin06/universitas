package co.edu.poli.ces3.universitas.database;

import co.edu.poli.ces3.universitas.database.dao.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConexionMySql {

    private int port;
    private String host;
    private String user;
    private String password;
    private String dataBaseName;
    public static final long SERIAL = 1L;

    ConexionMySql(String host){
        user = "root";
        password = "";
        this.port = 3306;
        this.host= host != null ? host : "127.0.0.1";
        this.dataBaseName = "ces3-universitas";
    }


    public void disconect(){
    }
    public Connection conexion(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection cnn = DriverManager.getConnection( "jdbc:mysql://" +host+":"+port+"/"+ dataBaseName, user, password);
            return cnn;
        }catch (ClassNotFoundException | SQLException exception){
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        }
        return null;
    }

    public List<User> getUsers() throws SQLException {
        Connection con = this.conexion();
        Statement sts = con.createStatement();
        ResultSet rs = sts.executeQuery("SELECT * FROM users");
        List<User> list = new ArrayList<>();

        while (rs.next()){
            list.add(new User(rs.getString("name"), rs.getString("lastName")));
        }

        return list;
    }

    public static int sum(int a, int b){
        return a + b;
    }
    public static void main(String[] args) {

        ConexionMySql con = new ConexionMySql("localhost");
        Connection conection = con.conexion();

        try {
            for (User x:
                 con.getUsers()) {
                System.out.println("nombre: " + x.getName());
                System.out.println("apellido: " + x.getLastname());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("la suma es " + ConexionMySql.sum(3,4));
        System.out.println(ConexionMySql.SERIAL);
        System.out.println("Hello Conection MySQL");
    }


}
