package co.edu.poli.ces3.universitas.database;

import co.edu.poli.ces3.universitas.database.dao.User;
import com.google.gson.JsonObject;

import java.sql.SQLException;
import java.util.List;

public interface CRUD<E> {

    public List<E> get() throws SQLException;

    public E getOne(int id) throws SQLException;

    public E insert();

    public E update(JsonObject userUpdate, int id) throws SQLException;
}
