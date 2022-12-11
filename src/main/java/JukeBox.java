import dao.*;
import model.PlayListData;
import model.Podcast;
import model.Song;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.Scanner;

public class JukeBox {
    public static void main(String[] args) {
        SongDAO songDAO = new SongDAO();
        PodcastDAO podcastDAO = new PodcastDAO();
        PlayListDAO playListDAO = new PlayListDAO();
        UserDAO userDAO = new UserDAO();
        Scanner sc = new Scanner(System.in);
        int option,option1,option2,option3,option4;

        System.out.println("Hi. Welcome to Jukebox");
        int userId = userDAO.login();

        System.out.println("---------------------------Songs--------------------------------------");
        List<Song> songList = songDAO.readSongs();
        songDAO.displayAllSongs(songList);

        System.out.println("---------------------------Podcasts----------------------------------");
        List<Podcast> podcasts = podcastDAO.readPodcasts();
        podcastDAO.displayAllPodcasts(podcasts);

        do
        {
            System.out.println("Main Menu");
            System.out.println("Choose an Option below \n1.Search a Song \n2.Search a Podcast \n3.Create a Playlist \n4.Play Music \n5.Exit");
            option = sc.nextInt();
            switch (option)
            {
                case 1:
                {
                    do
                    {
                        System.out.println("Do you want to search songs by \n1.Artist \n2.Album \n3.Genre \n4.Display All Songs \n5.Back Menu");
                        option1 = sc.nextInt();
                        switch (option1)
                        {
                            case 1:
                            {
                                System.out.println("Enter Artist Name:");
                                String artist = sc.next();
                                List<Song> artistsongs = songDAO.searchByArtist(songList, artist);
                                if (artistsongs.size() > 0)
                                    songDAO.displayAllSongs(artistsongs);
                                else
                                    System.out.println("Sorry, The song you're looking for isn't available in our jukebox.");
                                break;
                            }
                            case 2:
                            {
                                System.out.println("Enter Album Name:");
                                String album = sc.next();
                                List<Song> albumSongs = songDAO.searchByAlbum(songList, album);
                                if (albumSongs.size() > 0)
                                    songDAO.displayAllSongs(albumSongs);
                                else
                                    System.out.println("Sorry, The song you're looking for isn't available in our jukebox.");
                                break;
                            }
                            case 3:
                            {
                                System.out.println("Enter Genre:");
                                String genre = sc.next();
                                System.out.println("Enter the word or Initial letter of song");
                                List<Song> genreSongs = songDAO.searchByGenre(songList, genre);
                                if (genreSongs.size() > 0)
                                    songDAO.displayAllSongs(genreSongs);
                                else
                                    System.out.println("Sorry, The song you're looking for isn't available in our jukebox.");
                                break;
                            }
                            case 4:
                            {
                                songDAO.displayAllSongs(songList);
                                break;
                            }
                            case 5: break;
                            default:
                            {
                                System.out.println("Wrong Option");
                                break;
                            }

                        }

                    }while (option1 !=5);
                    break;
                }


                case 2:
                {
                    do {
                        System.out.println("Do you want to search Podcasts by \n1.Name \n2.Celebrity  \n3.Published Date \n4.Display all Podcasts \n5.Back Menu");
                        option2 = sc.nextInt();
                        switch (option2)
                        {
                            case 1:
                            {
                                System.out.println("Enter the name of podcast");
                                String podcastName = sc.next();
                                List<Podcast> podcastList = podcastDAO.searchByPodcastName(podcasts, podcastName);
                                if (podcastList.size() > 0)
                                    podcastDAO.displayAllPodcasts(podcastList);
                                else
                                    System.out.println("Sorry, The Podcast you're looking for isn't available in our jukebox.");
                                break;
                            }
                            case 2:
                            {
                                System.out.println("Enter the Celebrity name:");
                                String celebrityName = sc.next();
                                List<Podcast> podcastList = podcastDAO.searchByCelebrity(podcasts, celebrityName);
                                if (podcastList.size() > 0)
                                    podcastDAO.displayAllPodcasts(podcastList);
                                else
                                    System.out.println("Sorry, The Podcast you're looking for isn't available in our jukebox.");
                                break;
                            }
                            case 3:
                            {
                                System.out.println("Enter date(YYYY-MM-DD):");
                                String str = sc.next();
                                Date date=Date.valueOf(str);
                                List<Podcast> podcastList = podcastDAO.searchByDate(podcasts, date);
                                if (podcastList.size() > 0)
                                    podcastDAO.displayAllPodcasts(podcastList);
                                else
                                    System.out.println("Sorry, The Podcast you're looking for isn't available in our jukebox.");
                                break;
                            }
                            case 4:
                            {
                                podcastDAO.displayAllPodcasts(podcasts);
                                break;
                            }
                            case 5:
                                break;
                            default:
                                System.out.println("Wrong Option");
                                break;
                        }
                    }while (option2!=5);
                    break;
                }

                case 3:
                {
                    do
                    {
                        System.out.println("What do you want to do? \n1.Create Playlist \n2.Insert songs into Playlist" +
                                " \n3.Insert podcast into Playlist \n4.Display Playlists \n5.Display content of Playlist " +
                                "\n6.Delete a Playlist \n7.back menu");
                        option3= sc.nextInt();
                        switch (option3)
                        {
                            case 1:
                            {
                                System.out.println("Enter the Name of Playlist");
                                String name=sc.next();
                                int count=playListDAO.createPlaylist(name,userId);
                                if(count>0)
                                    System.out.println("Playlist Created");
                                else
                                    System.out.println("Playlist not created");
                                break;
                            }
                            case 2:
                            {
                                playListDAO.displayPlaylist(userId);
                                System.out.println("Enter the PlaylistId in which you want to enter songs");
                                int playlistId=sc.nextInt();
                                songDAO.displayAllSongs(songList);
                                System.out.println("Enter the songId to insert into Playlist");
                                int songId=sc.nextInt();
                                int count=playListDAO.insertSong(playlistId,songId);
                                if(count>0)
                                    System.out.println("Song inserted Successfully");
                                else
                                    System.out.println("Failed to insert the song");
                                break;
                            }
                            case 3:
                            {
                                playListDAO.displayPlaylist(userId);
                                System.out.println("Enter the PlaylistId in which you want to enter Podcast");
                                int playlistId=sc.nextInt();
                                podcastDAO.displayAllPodcasts(podcasts);
                                System.out.println("Enter the podId to insert into Playlist");
                                int podId=sc.nextInt();
                                int count=playListDAO.insertPodcast(playlistId,podId);
                                if(count>0)
                                    System.out.println("Podcast inserted Successfully");
                                else
                                    System.out.println("failed to insert The Podcast");
                                break;
                            }
                            case 4:
                            {
                                  playListDAO.displayPlaylist(userId);
                                break;
                            }
                            case 5:
                            {
                                playListDAO.displayPlaylist(userId);
                                System.out.println("Enter the name of Playlist");
                                String name= sc.next();
                                playListDAO.playlistcontent(name);
                                break;
                            }
                            case 6:
                            {
                                playListDAO.displayPlaylist(userId);
                                System.out.println("Enter the PlaylistId to delete");
                                int playlistId = sc.nextInt();
                                playListDAO.deletePlaylist(playlistId);
                                break;

                            }
                            case 7:break;

                            default:
                                System.out.println("Wrong Option");
                        }

                    }while (option3!=7);
                    break;
                }

                case 4:
                {
                    do {
                        System.out.println("Choose an option to Play Music:-\n1.Playlists\n2.Songs\n3.Podcasts\n4.Back Menu");
                        option4 = sc.nextInt();
                        switch (option4) {
                            case 1: {
                                playListDAO.playdata(userId);
                                break;
                            }
                            case 2: {
                                songDAO.playSongs();
                                break;
                            }
                            case 3: {
                                podcastDAO.playPodcasts();
                                break;
                            }
                            case 4:
                                break;

                            default:
                                System.out.println("Wrong Option!!");

                        }

                    } while (option4 != 4);
                    break;
                }
                default:
                    System.out.println("Wrong Option");
            }
        }
        while (option != 5) ;

    }
}
