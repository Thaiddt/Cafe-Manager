/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cafeteria.dao;

import java.util.List;

public interface DAO<T, V> {
    void insert(T entity);
    void delete(V id);
    void update(T entity);
    T selectByID(V id);
    List<T> selectAll();
    List<T> selectBySQL(String sql, Object...x);
}
