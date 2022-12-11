package model;

import java.util.Date;

public class Podcast
{
    private int podcastId;
    private String podcastName;
    private String celebrity;
    private Date podDate;
    private String podDuration;
    private String podLocation;

    public Podcast() {}

    public Podcast(int podcastId, String podcastName, String celebrity, Date podDate, String podDuration, String podLocation) {
        this.podcastId = podcastId;
        this.podcastName = podcastName;
        this.celebrity = celebrity;
        this.podDate = podDate;
        this.podDuration = podDuration;
        this.podLocation = podLocation;
    }

    public int getPodcastId() {
        return podcastId;
    }

    public void setPodcastId(int podcastId) {
        this.podcastId = podcastId;
    }

    public String getPodcastName() {
        return podcastName;
    }

    public void setPodcastName(String podcastName) {
        this.podcastName = podcastName;
    }

    public String getCelebrity() {
        return celebrity;
    }

    public void setCelebrity(String celebrity) {
        this.celebrity = celebrity;
    }

    public Date getPodDate() {
        return podDate;
    }

    public void setPodDate(Date podDate) {
        this.podDate = podDate;
    }

    public String getPodDuration() {
        return podDuration;
    }

    public void setPodDuration(String podDuration) {
        this.podDuration = podDuration;
    }

    public String getPodLocation() {
        return podLocation;
    }

    public void setPodLocation(String podLocation) {
        this.podLocation = podLocation;
    }

    @Override
    public String toString() {
        System.out.printf("%-10s %20s %20s %20s %20s",this.podcastId,this.podcastName,this.celebrity,this.podDuration,this.podDate);
        return "";

    }
}
