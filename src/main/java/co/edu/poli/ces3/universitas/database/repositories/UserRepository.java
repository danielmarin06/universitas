package co.edu.poli.ces3.universitas.database.repositories;

import co.edu.poli.ces3.universitas.database.CRUD;
import co.edu.poli.ces3.universitas.database.ConexionMySql;
import co.edu.poli.ces3.universitas.database.dao.User;
import com.google.gson.JsonObject;

import java.sql.*;
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
            list.add(new User(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("lastName"),
                    rs.getString("mail"),
                    rs.getString("password"),
                    rs.getDate("createdAt"),
                    rs.getDate("updatedAt"),
                    rs.getDate("deletedAt")
            ));
        }

        rs.close();
        sts.close();
        con.close();

        return list;
    }

    @Override
    public User getOne(int id) throws SQLException {
        Connection con = cnnMysql.conexion();
        PreparedStatement sts = con.prepareStatement("SELECT * FROM users WHERE id = ?");
        sts.setInt(1,id);
        ResultSet rs = sts.executeQuery();
        if(rs.next())
            return new User(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("lastName"),
                    rs.getString("mail"),
                    rs.getString("password"),
                    rs.getDate("createdAt"),
                    rs.getDate("updatedAt"),
                    rs.getDate("deletedAt")
            );
        return null;
    }

    @Override
    public User insert() {
        return null;
    }

    @Override
    public User update(JsonObject userUpdate, int id) throws SQLException {
        String sql = "UPDATE users SET name = ?, lastName = ?, mail = ?, password = ?, updatedAt = now() WHERE id = ?";
        Connection cnn = this.cnnMysql.conexion();
        PreparedStatement sts = cnn.prepareStatement(sql);
        sts.setString(1, userUpdate.get("name").getAsString());
        sts.setString(2, userUpdate.get("lastName").getAsString());
        sts.setString(3, userUpdate.get("mail").getAsString());
        sts.setString(4, userUpdate.get("password").getAsString());
        sts.setInt(5, id);
        sts.execute();
        return this.getOne(id);
    }

    public void disconect() throws SQLException {
        cnnMysql.disconect();
    }
}
