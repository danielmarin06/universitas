package co.edu.poli.ces3.universitas.database.repositories;

import co.edu.poli.ces3.universitas.database.CRUD;
import co.edu.poli.ces3.universitas.database.ConexionMySql;
import co.edu.poli.ces3.universitas.database.dao.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements CRUD {

    private ConexionMySql cnnMysql;

    public UserRepository(){
        cnnMysql = new ConexionMySql("localhost");
    }


    @Override
    public List<User> get() throws SQLException {
        Connection con = cnnMysql.conexion();
        Statement sts = con.createStatement();
        ResultSet rs = sts.executeQuery("SELECT * FROM users");
        List<User> list = new ArrayList<>();

        while (rs.next()){
            list.add(new User(rs.getString("name"), rs.getString("lastName")));
        }

        return list;
    }

    @Override
    public User insert() {
        return null;
    }
}
