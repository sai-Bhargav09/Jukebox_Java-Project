package model;

public class PlayListData {
    private int playlistId;
    private int songId;
    private int podId;

    public PlayListData() {
    }

    public PlayListData(int playlistId, int songId, int podId) {
        this.playlistId = playlistId;
        this.songId = songId;
        this.podId = podId;
    }

    public int getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(int playlistId) {
        this.playlistId = playlistId;
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public int getPodId() {
        return podId;
    }

    public void setPodId(int podId) {
        this.podId = podId;
    }

    @Override
    public String toString() {
        return "PlayListData{" +
                "playlistId=" + playlistId +
                ", songId=" + songId +
                ", podId=" + podId +
                '}';
    }
}
