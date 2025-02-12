package kz.lattuse.oop.dbconnection;


import kz.lattuse.oop.entities.User;

import kz.lattuse.oop.entities.Post;

import java.sql.*;

import java.util.ArrayList;

public class DbConnection {
    // credentials here
    private static final String url = "jdbc:postgresql://localhost:5432/oop_db";

    private static final String username = "postgres";

    private static final String password = "admin";

    public void getConnectionToDb() {
        try {
            // connection itself
            Connection con = DriverManager.getConnection(url, username, password);

            System.out.println("Connection established successfully");

            String query = "select * from public.users";

            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(query);

            rs.next();

            String name = rs.getString("name");

            System.out.println(name);

            stmt.close();

            con.close();

            System.out.println("Connection closed successfully");

        }catch (Exception e) {
            System.out.println(e);
        }
    }


    public Connection connection() {
        try {
            // connection itself

            Connection con = DriverManager.getConnection(url, username, password);
            System.out.println("Connection established successfully");
            return con;
        } catch (SQLException e) {
            System.err.println("SQLException occurred:");
            System.err.println("Error code: " + e.getErrorCode()); // SQL error code
            System.err.println("SQL state: " + e.getSQLState()); // SQL state
            System.err.println("Message: " + e.getMessage());
            return null;
        }
    }

    public int closeConnection(Connection con) {
        try {
            if (con != null) {
                con.close();
                System.out.println("Connection closed successfully");
                return 0;
            }
            else{
                return 2;
            }

        }catch (SQLException e) {
            System.err.println("SQLException occurred:");
            System.err.println("Error code: " + e.getErrorCode()); // SQL error code
            System.err.println("SQL state: " + e.getSQLState()); // SQL state
            System.err.println("Message: " + e.getMessage());
            return 1;
        }
    }


    public ArrayList<User> getAllUsersRS(Connection con) throws SQLException {
        String query = "SELECT * FROM public.users"; // Query to be run
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query); // Execute query
        ArrayList<User> users = new ArrayList<>();
        System.out.println("lol");

        while (rs.next()) {
            System.out.println("it works");
            User user = new User("","", 0);
            user.setName(rs.getString("name"));
            user.setGender(rs.getString("gender"));
            user.setId(rs.getInt("id"));
            System.out.println("it works");
            users.add(user);
        }

        stmt.close();
        closeConnection(con);
        return users;
    }


    public ArrayList<User> findUserByName(Connection con, String name) throws SQLException {
        String query = "SELECT * FROM public.users WHERE name=?"; // Query to be run
        PreparedStatement st = con.prepareStatement(query);
        st.setString(1, name);

        ResultSet rs = st.executeQuery(); // Execute query

        ArrayList<User> users = new ArrayList<>();

        while (rs.next()) {
            User user = new User("","",0);
            user.setName(rs.getString("name"));
            user.setGender(rs.getString("gender"));
            user.setId(rs.getInt("id"));
            users.add(user);
        }

        st.close();
        closeConnection(con);
        return users;
    }

    public User findUserById(Connection con, int id) throws SQLException {
        String query = "SELECT * FROM public.users WHERE id=?"; // Query to be run
        PreparedStatement st = con.prepareStatement(query);
        st.setInt(1, id);

        ResultSet rs = st.executeQuery(); // Execute query

        ArrayList<User> users = new ArrayList<>();
        User user = new User("", "", 0);

        while (rs.next()) {
            user.setName(rs.getString("name"));
            user.setGender(rs.getString("gender"));
            user.setId(rs.getInt("id"));
            users.add(user);
        }

        st.close();
        closeConnection(con);
        return user;
    }

    public User createUser(Connection con, User u) throws SQLException {
        String query = "INSERT INTO public.users (id, name, gender) VALUES (?, ?, ?)"; // query to be run
        PreparedStatement st = con.prepareStatement(query);
        st.setInt(1, u.getId());
        st.setString(2, u.getName());
        st.setString(3, u.getGender());
        int success = st.executeUpdate(); // Execute query
        st.close();
        closeConnection(con);

        if (success > 0) {
            System.out.println("User is added");
            return u;
        }

        return null;
    }


    public User updateUser(Connection con, User u, int oldId) throws SQLException {
        String query = "UPDATE public.users SET name=?, gender=?, id=? WHERE id=?"; // Query to be run
        PreparedStatement st = con.prepareStatement(query);
        st.setString(1, u.getName());
        st.setString(2, u.getGender());
        st.setInt(3, u.getId());
        st.setInt(4, oldId);
        int success = st.executeUpdate(); // Execute query
        st.close();
        closeConnection(con);

        if (success > 0) {
            System.out.println("User is updated");
            return u;
        }

        return null;
    }



    public User deleteUser(Connection con, User u) throws SQLException {
        String query = "DELETE FROM public.users WHERE id=?"; // query to be run
        PreparedStatement st = con.prepareStatement(query);
        st.setInt(1, u.getId());

        //ResultSet rs = st.executeQuery(); // Execute query
        int success = st.executeUpdate();
        st.close();
        closeConnection(con);

        if (success > 0) {
            System.out.println("User is deleted");
            return u;
        }

        return null;
    }




    public ArrayList<Post> getAllPostsRS(Connection con) throws SQLException {
        String query = "SELECT * FROM public.posts"; // Query to be run
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query); // Execute query
        ArrayList<Post> posts = new ArrayList<>();
        System.out.println("lol");

        while (rs.next()) {
            System.out.println("it works");
            Post post = new Post(0,0,"","");
            post.setId(rs.getInt("id"));
            post.setAuthorId(rs.getInt("authorid"));
            post.setTitle(rs.getString("title"));
            post.setContent(rs.getString("content"));
            System.out.println("it works");
            posts.add(post);
        }

        stmt.close();
        closeConnection(con);
        return posts;
    }


    public ArrayList<Post> findPostsByTitle(Connection con, String title) throws SQLException {
        String query = "SELECT * FROM public.posts WHERE title=?"; // Query to be run
        PreparedStatement st = con.prepareStatement(query);
        st.setString(1, title);

        ResultSet rs = st.executeQuery(); // Execute query

        ArrayList<Post> posts = new ArrayList<>();
        Post post = new Post(0, 0,"","");
        while (rs.next()) {
            post.setId(rs.getInt("id"));
            post.setAuthorId(rs.getInt("authorid"));
            post.setTitle(rs.getString("title"));
            post.setContent(rs.getString("content"));
            posts.add(post);
        }

        st.close();
        closeConnection(con);
        return posts;
    }

    public ArrayList<Post> findPostsByAuthorId(Connection con, int authorId) throws SQLException {
        String query = "SELECT * FROM public.posts WHERE authorId=?"; // Query to be run
        PreparedStatement st = con.prepareStatement(query);
        st.setInt(1, authorId);

        ResultSet rs = st.executeQuery(); // Execute query

        ArrayList<Post> posts = new ArrayList<>();
        Post post = new Post(0, 0,"", "");
        while (rs.next()) {
            post.setId(rs.getInt("id"));
            post.setAuthorId(rs.getInt("authorid"));
            post.setTitle(rs.getString("title"));
            post.setContent(rs.getString("content"));
            posts.add(post);
        }

        st.close();
        closeConnection(con);
        return posts;
    }

    public Post findPostById(Connection con, int id) throws SQLException {
        String query = "SELECT * FROM public.posts WHERE id=?"; // Query to be run
        PreparedStatement st = con.prepareStatement(query);
        st.setInt(1, id);

        ResultSet rs = st.executeQuery(); // Execute query

        ArrayList<Post> posts = new ArrayList<>();
        Post post = new Post(0,0, "", "");
        while (rs.next()) {
            post.setId(rs.getInt("id"));
            post.setAuthorId(rs.getInt("authorid"));
            post.setTitle(rs.getString("title"));
            post.setContent(rs.getString("content"));
            posts.add(post);
        }

        st.close();
        closeConnection(con);
        return post;
    }

    public Post createPost(Connection con, Post p) throws SQLException {
        String query = "INSERT INTO public.posts (id, authorId, title, content) VALUES (?, ?, ?, ?)"; // query to be run
        PreparedStatement st = con.prepareStatement(query);
        st.setInt(1, p.getId());
        st.setInt(2, p.getAuthorId());
        st.setString(3, p.getTitle());
        st.setString(4, p.getContent());
        int success = st.executeUpdate(); // Execute query
        st.close();
        closeConnection(con);

        if (success > 0) {
            System.out.println("Post is added");
            return p;
        }

        return null;
    }


    public Post updatePost(Connection con, Post p, int oldId) throws SQLException {
        String query = "UPDATE public.posts SET id=?, authorId=?, title=?, content=? WHERE id=?"; // Query to be run
        PreparedStatement st = con.prepareStatement(query);
        st.setInt(1, p.getId());
        st.setInt(2, p.getAuthorId());
        st.setString(3, p.getTitle());
        st.setString(4, p.getContent());
        st.setInt(5, oldId);
        int success = st.executeUpdate(); // Execute query
        st.close();
        closeConnection(con);

        if (success > 0) {
            System.out.println("Post is updated");
            return p;
        }

        return null;
    }



    public Post deletePost(Connection con, Post p) throws SQLException {
        String query = "DELETE FROM public.posts WHERE id=?"; // query to be run
        PreparedStatement st = con.prepareStatement(query);
        st.setInt(1, p.getId());

        //ResultSet rs = st.executeQuery(); // Execute query
        int success = st.executeUpdate();
        st.close();
        closeConnection(con);

        if (success > 0) {
            System.out.println("Post is deleted");
            return p;
        }

        return null;
    }



}
