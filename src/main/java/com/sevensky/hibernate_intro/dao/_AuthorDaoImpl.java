package com.sevensky.hibernate_intro.dao;

import com.sevensky.hibernate_intro.domain.Author;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;

//@Component
public class _AuthorDaoImpl implements AuthorDao {

    private final DataSource dataSource;

    public _AuthorDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Author getById(long id) {
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;

        try {
            connection=dataSource.getConnection();
            statement=connection.createStatement();
            resultSet=statement.executeQuery("select * from author where id="+id);
            if(resultSet.next()){
                return getAuthor(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                closeAll(resultSet, statement, connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public Author getByName(String firstName, String lastName) {
        Connection connection=null;
        PreparedStatement ps=null;
        ResultSet resultSet=null;

        try {
            connection=dataSource.getConnection();
            ps=connection.prepareStatement("select * from author where first_name =? and  last_name = ?");
            ps.setString(1,firstName);
            ps.setString(2,lastName);
            resultSet=ps.executeQuery();

            if(resultSet.next()){
                return getAuthor(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                closeAll(resultSet, ps, connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public Author save(Author author) {
        Connection connection=null;
        PreparedStatement ps=null;
        ResultSet resultSet=null;

        try {
            connection=dataSource.getConnection();
            ps=connection.prepareStatement("Insert Into author(first_name,last_name) values(?,?)");
            ps.setString(1,author.getFirstName());
            ps.setString(2,author.getLastName());
            ps.execute();

            Statement statement=connection.createStatement();

            resultSet=statement.executeQuery("select LAST_INSERT_ID()");

            if(resultSet.next()){
                long id=resultSet.getLong(1);
                return this.getById(id);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                closeAll(resultSet, ps, connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public Author updateAuthor(Author author) {
        Connection connection=null;
        PreparedStatement ps=null;
        ResultSet resultSet=null;

        try {
            connection=dataSource.getConnection();
            ps=connection.prepareStatement("UPDATE author set first_name=?,last_name=? where id=?");
            ps.setString(1,author.getFirstName());
            ps.setString(2,author.getLastName());
            ps.setLong(3,author.getId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                closeAll(resultSet, ps, connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return this.getById(author.getId());
    }

    @Override
    public void deleteAuthor(Author author) {
        Connection connection=null;
        PreparedStatement ps=null;

        try {
            connection=dataSource.getConnection();
            ps=connection.prepareStatement("DELETE FROm author  where id=?");
            ps.setLong(1,author.getId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                closeAll(null, ps, connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static Author getAuthor(ResultSet resultSet) throws SQLException {
        Author author=new Author();
        author.setId(resultSet.getLong("id"));
        author.setFirstName(resultSet.getString("first_name"));
        author.setLastName(resultSet.getString("last_name"));
        return author;
    }


    private void closeAll(ResultSet resultSet, Statement statement, Connection connection) throws SQLException {
        if(resultSet!=null){
            resultSet.close();
        }
        if(statement!=null){
            statement.close();
        }
        if(connection!=null){
            connection.close();
        }
    }

}
