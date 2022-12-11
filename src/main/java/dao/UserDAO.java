package dao;

import connection.Connections;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class UserDAO
{
    public static Connection con= Connections.connectToSql();
    Scanner sc=new Scanner(System.in);

    public List<User> getAllUsers()
    {
        List<User> userList=new ArrayList<>();
        try
        {
            Statement s=con.createStatement();
            ResultSet rs=s.executeQuery("select * from users");
            while (rs.next())
            {
                userList.add(new User(rs.getInt(1),rs.getString(2),rs.getString(3)));
            }
        }
        catch(SQLException se)
        {
            se.printStackTrace();
        }
        return userList;

    }

    public int login()
    {
        int userid=0;
        System.out.println("Enter a choice \n1.Existing User \n2.New User");
        int choose=sc.nextInt();
        switch (choose)
        {
            case 1:
            {
                System.out.println("Enter Username:");
                String username=sc.next();
                System.out.println("Enter Password");
                String password=sc.next();
                List<User> userList=getAllUsers();
                List<User> verify= userList.stream().filter(i -> i.getUserName().equalsIgnoreCase(username)).filter(i -> i.getPassword().equalsIgnoreCase(password)).toList();
                try
                {
                    int userId=verify.get(0).getUserId();
                    System.out.println("Login Success");
                    return userId;

                }
                catch (ArrayIndexOutOfBoundsException e)
                {
                    System.out.println("Username or Password incorrect.Try Again");
                    login();
                }
                break;
            }
            case 2:
            {
                userid=newUser();
                break;
            }
        }
        return userid;

    }

    public int newUser()
    {
        int userId=0;
        System.out.println("Enter User name:");
        String name=sc.next();
        System.out.println("Enter Password");
        String password=sc.next();
        try
        {
            PreparedStatement preparedStatement= con.prepareStatement("insert into users(username,pasword) values(?,?)");
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,password);
            preparedStatement.executeUpdate();
            System.out.println("Account Created Successfully :)\n");

            PreparedStatement ps=con.prepareStatement("select userid from users where username=?");
            ps.setString(1,name);
            ResultSet rs= ps.executeQuery();
            while(rs.next())
            {
                userId= rs.getInt(1);
                return userId;
            }
        }
        catch (SQLException se)
        {
            se.printStackTrace();
            newUser();
        }
        return userId;
    }
}
