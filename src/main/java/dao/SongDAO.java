package dao;

import connection.Connections;
import model.AudioData;
import model.Song;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SongDAO
{
    public static Connection conn= Connections.connectToSql();

    public SongDAO() {}

    public List<Song> readSongs() {
        List<Song> songList=new ArrayList<>();
        try
        {
            Statement ps=conn.createStatement();
            ResultSet rSet=ps.executeQuery("select * from songs");
            while(rSet.next())
            {
                songList.add(new Song(rSet.getInt(1),rSet.getString(2),
                        rSet.getString(3),rSet.getString(4),
                        rSet.getString(5),rSet.getString(6)));
            }
        }
        catch (SQLException se )
        {
            se.printStackTrace();
        }
        return songList;
    }

    public void displayAllSongs(List<Song> songList)
    {
        System.out.printf("%-10s %20s %20s %20s %20s", "SongID", "SongName", "Artist", "Album", "Genre");
        System.out.println();
        songList.forEach(i-> System.out.println(i));
        //songList.forEach(System.out::println);
    }

    public List<Song> searchByGenre(List<Song> songList,String genre)
    {
        List<Song> genreList=new ArrayList<>();
        genreList= songList.stream().filter(i->i.getGenre().equalsIgnoreCase(genre)).sorted((o1,o2)->o1.getSongName().compareToIgnoreCase(o2.getSongName())).collect(Collectors.toList());
        return genreList;
    }


    public List<Song> searchByArtist(List<Song> songList,String artist)
    {
        List<Song> artistList=new ArrayList<>();
        artistList= songList.stream().filter(i->i.getArtist().equalsIgnoreCase(artist)).sorted((o1,o2)->o1.getSongName().compareToIgnoreCase(o2.getSongName())).collect(Collectors.toList());
        return artistList;
    }

    public List<Song> searchByAlbum(List<Song> songList,String album)
    {
        List<Song> albumList=new ArrayList<>();
        albumList= songList.stream().filter(i->i.getAlbum().equalsIgnoreCase(album)).sorted((o1,o2)->o1.getSongName().compareToIgnoreCase(o2.getSongName())).collect(Collectors.toList());
        return albumList;
    }

    public void playSongs()
    {
        List<Song> songList=new ArrayList<>();
        displayAllSongs(songList);
        List<AudioData> songAudioList=new ArrayList<>();
        AudioPlayerDAO audioPlayerDAO=new AudioPlayerDAO();
        try
        {
            Statement s=conn.createStatement();
            ResultSet rs=s.executeQuery("select songname,location from songs");
            while(rs.next())
            {
                songAudioList.add(new AudioData(rs.getString(1), rs.getString(2)));
            }
            audioPlayerDAO.jukeBoxPlayer(songAudioList);

        }catch (SQLException se)
        {
            se.printStackTrace();
        }


    }



}
