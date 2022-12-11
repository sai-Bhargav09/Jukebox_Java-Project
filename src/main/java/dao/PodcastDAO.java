package dao;

import connection.Connections;
import model.AudioData;
import model.Podcast;
import model.Song;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class PodcastDAO {
    public static Connection connect= Connections.connectToSql();

    public PodcastDAO() {}


    public List<Podcast> readPodcasts() {
        List<Podcast> podList=new ArrayList<>();
        try
        {
            Statement statObj=connect.createStatement();
            ResultSet rSet=statObj.executeQuery("select * from podcasts");
            while (rSet.next())
            {
                podList.add(new Podcast(rSet.getInt(1),rSet.getString(2),rSet.getString(3),
                        rSet.getDate(4),rSet.getString(5),rSet.getString(6)));
            }
        }
        catch (SQLException obj)
        {
            obj.printStackTrace();
        }
        return podList;
    }


    public void displayAllPodcasts(List<Podcast> podList)
    {
        podList=podList.stream().sorted((o1,o2)->o1.getPodcastName().compareToIgnoreCase(o2.getPodcastName())).collect(Collectors.toList());
        System.out.printf("%-10s %20s %20s %20s %20s", "PodcastId", "PodcastName", "Celebrity", "PodDuration", "PodDate");
        System.out.println();
        podList.forEach(i-> System.out.println(i));
    }


    public List<Podcast> searchByPodcastName(List<Podcast> podList, String podName)
    {
        return podList.stream().filter(i->i.getPodcastName().equalsIgnoreCase(podName)).collect(Collectors.toList());
    }


    public List<Podcast> searchByCelebrity(List<Podcast> podList, String celebrity)
    {
        return podList.stream().filter(i->i.getCelebrity().equalsIgnoreCase(celebrity)).collect(Collectors.toList());
    }


    public List<Podcast> searchByDate(List<Podcast> podList, Date publishedDate)
    {
        return podList.stream().filter(i->i.getPodDate().equals(publishedDate)).collect(Collectors.toList());
    }


    public List<Podcast> searchByPodId(List<Podcast> podList, int podId)
    {
        return podList.stream().filter(i->i.getPodcastId()==podId).collect(Collectors.toList());
    }

    public void playPodcasts()
    {
        List<Podcast> podcastList = new ArrayList<>();
        displayAllPodcasts(podcastList);
        List<AudioData> songAudioList = new ArrayList<>();
        AudioPlayerDAO audioPlayerDAO = new AudioPlayerDAO();
        try
        {
            Statement s = connect.createStatement();
            ResultSet rs = s.executeQuery("select podname,podlocation from podcasts");
            while (rs.next())
            {
                songAudioList.add(new AudioData(rs.getString(1), rs.getString(2)));
            }
            audioPlayerDAO.jukeBoxPlayer(songAudioList);

        }
        catch (SQLException se)
        {
            se.printStackTrace();
        }
    }

}
