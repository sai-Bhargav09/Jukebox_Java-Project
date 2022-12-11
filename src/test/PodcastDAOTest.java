import dao.PodcastDAO;
import model.Podcast;
import org.testng.annotations.Test;


import java.sql.Date;
import java.util.List;

import static org.testng.AssertJUnit.assertEquals;


public class PodcastDAOTest
{
    PodcastDAO podcastDAO=new PodcastDAO();
    List<Podcast> podcastList=podcastDAO.readPodcasts();

    @Test
     public void givenPodcastNameGetPodcastDetails()
    {
        assertEquals(102, podcastDAO.searchByPodcastName(podcastList,"beautiful").get(0).getPodcastId());
        assertEquals("Andrew", podcastDAO.searchByPodcastName(podcastList,"softbeat").get(0).getCelebrity());
        assertEquals(Date.valueOf("2012-05-10"), podcastDAO.searchByPodcastName(podcastList,"The Podcast").get(0).getPodDate());
    }

    @Test
    public void givenPodcastCelebrityGetPodcastDetails()
    {
        assertEquals(102,podcastDAO.searchByCelebrity(podcastList,"Charles").get(0).getPodcastId());
        assertEquals("ordering",podcastDAO.searchByCelebrity(podcastList,"James").get(0).getPodcastName());
        assertEquals(Date.valueOf("2019-12-25"),podcastDAO.searchByCelebrity(podcastList,"Andrew").get(0).getPodDate());
    }

    @Test
    public void givenPodcastDateGetPodcastDetaisl()
    {
        assertEquals(103,podcastDAO.searchByDate(podcastList,Date.valueOf("2017-05-09")).get(0).getPodcastId());
        assertEquals("softbeat",podcastDAO.searchByDate(podcastList,Date.valueOf("2019-12-25")).get(0).getPodcastName());
        assertEquals("Carla",podcastDAO.searchByDate(podcastList,Date.valueOf("2014-07-05")).get(0).getCelebrity());

    }





}
