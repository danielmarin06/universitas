package co.edu.poli.ces3.universitas.database;

import co.edu.poli.ces3.universitas.database.dao.User;

import java.sql.SQLException;
import java.util.List;

public interface CRUD {

    public List<User> get() throws SQLException;

    public User insert();

}
