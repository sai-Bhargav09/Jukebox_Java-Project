package model;

public class Song
{
    private int songId;
    private String songName;
    private String artist;
    private String genre;
    private String album;
    private String location;

    public Song() {}

    public Song(int songId, String songName, String artist, String genre, String album, String location)
    {
        this.songId = songId;
        this.songName = songName;
        this.artist = artist;
        this.genre = genre;
        this.album = album;
        this.location = location;
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString()
    {
        System.out.printf("%-10s %20s %20s %20s %20s",this.songId,this.songName,this.artist,this.album,this.genre);
    return "";
    }
}
