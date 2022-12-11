package dao;

import connection.Connections;
import model.AudioData;
import model.PlayList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlayListDAO {
    public static Connection connect= Connections.connectToSql();
    Scanner sc=new Scanner(System.in);



    public int createPlaylist(String playlistName,int userId)
    {
//        UserDAO userDAO=new UserDAO();
//        int userId1=userDAO.login();

        int count=0;
        try
        {
            //insert into playlists(playlistname,userid) values('Mine',409);
            PreparedStatement ps= connect.prepareStatement("insert into playlists(playlistname,userid) values(?,?)");
            ps.setString(1,playlistName);
            ps.setInt(2,userId);
            count=ps.executeUpdate();

        }
        catch (SQLException se)
        {
            se.printStackTrace();
        }
        return count;
    }


    public void displayPlaylist(int userId)
    {
        List<PlayList> playLists=new ArrayList<>();
        try
        {
//            UserDAO userDAO=new UserDAO();
//            int userId=userDAO.login();
            //List<PlayList> userPlayList=playLists.stream().filter(i->i.getUserId()==userId).toList();
            PreparedStatement ps= connect.prepareStatement("select * from playlists where userid=?");
            ps.setInt(1,userId);
            ResultSet rs=ps.executeQuery();
            while (rs.next())
            {
                int id=rs.getInt(1);
                String name=rs.getString(2);
                int user=rs.getInt(3);
                playLists.add(new PlayList(id,name,user));
            }
        }
        catch (SQLException se)
        {
            se.printStackTrace();
        }
        for(PlayList p:playLists)
            System.out.println(p);
    }


    public int insertSong(int playListId,int songId)
    {
        int count=0;
        try
        {
            PreparedStatement ps= connect.prepareStatement("insert into playlistdata values(?,?,null)");
            ps.setInt(1,playListId);
            ps.setInt(2,songId);
            count=ps.executeUpdate();
        }
        catch (SQLException se)
        {
            se.printStackTrace();
        }
        return count;
    }


    public int insertPodcast(int playListId,int podId)
    {
        int count=0;
        try
        {
            PreparedStatement ps= connect.prepareStatement("insert into playlistdata values(?,null,?)");
            ps.setInt(1,playListId);
            ps.setInt(2,podId);
            count=ps.executeUpdate();
        }
        catch (SQLException se)
        {
            se.printStackTrace();
        }
        return count;
    }


    public void insertAlbum(int playlistId, String albumName)
    {
        try
        {
            PreparedStatement ps= connect.prepareStatement("select songid from songs where album=?");
            ps.setString(1,albumName);
            ResultSet rs=ps.executeQuery();
            while (rs.next())
            {
                int c=rs.getInt(1);
                insertSong(playlistId,c);
            }
        }
        catch (SQLException se)
        {
            se.printStackTrace();
        }


    }


    public int insertSongAndPodcast(int playListId,int songId,int podId)
    {
        int count=0;
        try
        {
            PreparedStatement ps= connect.prepareStatement("insert into playlistdata values(?,?,?)");
            ps.setInt(1,playListId);
            ps.setInt(2,songId);
            ps.setInt(3,podId);
            count=ps.executeUpdate();
            ps.close();
            connect.close();
        }
        catch (SQLException se)
        {
            se.printStackTrace();
        }
        return count;
    }

    public void deletePlaylist(int playListId)
    {
        try
        {
            PreparedStatement ps= connect.prepareStatement("delete from playList where playlistid=?");
            ps.setInt(1,playListId);
            ps.executeUpdate();
            ps.close();
        }
        catch (SQLException se)
        {
            se.printStackTrace();
        }
    }

    public void playlistcontent(String playListName)
    {
        try
        {
            PreparedStatement ps= connect.prepareStatement("select songid,songname from songs where songid in" +
                    "(select songid from playlistdata where playlistid in(select playlistid from playlists where playlistname=?)) ");
            PreparedStatement ps1= connect.prepareStatement("select podid,podname from podcasts where podid in" +
                    "(select podid from playlistdata where playlistid in(select playlistid from playlists where playlistname=?)) ");
            ps.setString(1,playListName);
            ps1.setString(1,playListName);
            ResultSet rs= ps.executeQuery();
            ResultSet rs1= ps1.executeQuery();

            while (rs.next())
            {
                System.out.println(rs.getInt(1)+"  "+rs.getString(2));
            }
            while (rs1.next())
            {
                System.out.println(rs1.getInt(1)+"  "+rs1.getString(2));
            }
        }
        catch(SQLException se)
        {
            se.printStackTrace();
        }
    }
    public void playdata(int userId)
    {
        displayPlaylist(userId);
        System.out.println("Enter the name of playlist to play");
        String playlist=sc.next();
        int songId,podId;
        List<AudioData> audioList=new ArrayList<>();
        AudioPlayerDAO audioPlayerDAO=new AudioPlayerDAO();
        try
        {
            PreparedStatement ps= connect.prepareStatement("select songid,podid from playlistdata where playlistid in" +
                    "(select playlistid from playlists where playlistname=?)");
            ps.setString(1,playlist);
            ResultSet rs=ps.executeQuery();
            while (rs.next())
            {
                songId=rs.getInt(1);
                podId=rs.getInt(2);

                    PreparedStatement ps1= connect.prepareStatement("select songname,location from songs where songid=?");
                    ps1.setInt(1,songId);
                    ResultSet rs1 = ps1.executeQuery();
                    while (rs1.next())
                    {
                        audioList.add(new AudioData(rs1.getString(1),rs1.getString(2)));
                    }

                {
                    PreparedStatement ps2= connect.prepareStatement("select podname,podlocation from podcasts where podid=?");
                    ps2.setInt(1,podId);
                    ResultSet rs2 = ps2.executeQuery();
                    while (rs2.next())
                    {
                        audioList.add(new AudioData(rs2.getString(1),rs2.getString(2)));

                    }
                }
            }
            audioPlayerDAO.jukeBoxPlayer(audioList);
        }
        catch(SQLException se)
        {
            se.printStackTrace();
        }

    }

}
