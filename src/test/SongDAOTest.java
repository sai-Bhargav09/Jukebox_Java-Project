import dao.SongDAO;
import model.Song;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.AssertJUnit.assertEquals;

public class SongDAOTest
{
    SongDAO songDAO=new SongDAO();
    List<Song> songList=songDAO.readSongs();

    @Test
    public void givenSongGenreGetSongDetails()
    {
        assertEquals("Rahaman",songDAO.searchByGenre(songList,"Patriotic").get(0).getArtist());
        assertEquals("RRR",songDAO.searchByGenre(songList,"History").get(0).getAlbum());
        assertEquals("Closer",songDAO.searchByGenre(songList,"EDM").get(0).getSongName());
    }

    @Test
    public void givenSongArtistGetSongDetails()
    {
        assertEquals("History",songDAO.searchByArtist(songList,"Hemachandra").get(0).getGenre());
        assertEquals("Vande Mataram",songDAO.searchByArtist(songList,"Rahaman").get(0).getSongName());
        assertEquals("YJHD",songDAO.searchByArtist(songList,"Arijit").get(0).getAlbum());
    }

}
