package be.pxl.ja.streamingservice.model;


import be.pxl.ja.streamingservice.exception.InvalidDateException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Profile {
    private static final int MAX_RECENTLY_WATCHED_SIZE = 5;
    private static final int MAX_CURRENTLY_WATCHING_SIZE = 3;
    List<Content> recentlyWatched;
    List<Content> currentlyWatching;
    private String name;
    private LocalDate dateOfBirth;

    public Profile(String name) {
        this.name = name;
        recentlyWatched = new ArrayList<>(MAX_RECENTLY_WATCHED_SIZE);
        currentlyWatching = new ArrayList<>(MAX_CURRENTLY_WATCHING_SIZE);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        if (dateOfBirth.isAfter(LocalDate.now())) {
            throw new InvalidDateException(dateOfBirth, "date of birth", "No date of birth in future allowed.");
        }
        this.dateOfBirth = dateOfBirth;
    }

    public int getAge() {
        if (dateOfBirth == null) {
            return 0;
        }
        return (int) ChronoUnit.YEARS.between(dateOfBirth, LocalDateTime.now());
    }

    public boolean allowedToWatch(Content content) {
        return content.getMaturityRating().getMinimumAge() <= getAge();
    }

    public void startWatching(Content content) {
        if (!currentlyWatching.contains(content)) {
            currentlyWatching.add(content);
        }
    }

    public void finishWatching(Content content) {
        currentlyWatching.remove(content);
        recentlyWatched.add(content);
    }
}
