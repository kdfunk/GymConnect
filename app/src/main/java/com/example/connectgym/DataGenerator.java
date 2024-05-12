package com.example.connectgym;

import java.util.HashMap;

public class DataGenerator {

    public static HashMap<String, User> generateDummyUsers() {
        HashMap<String, User> users = new HashMap<>();

        // Dummy data for user preferences
        HashMap<String, Integer> ratingsUser1 = new HashMap<>();
        ratingsUser1.put("Yoga", 5);
        ratingsUser1.put("Crossfit", 1);

        HashMap<String, Integer> ratingsUser2 = new HashMap<>();
        ratingsUser2.put("Yoga", 1);
        ratingsUser2.put("Crossfit", 5);

        HashMap<String, Integer> ratingsUser3 = new HashMap<>();
        ratingsUser3.put("Yoga", 4);
        ratingsUser3.put("Cardio", 4);

        users.put("user1", new User("user1", "user1@example.com", ratingsUser1));
        users.put("user2", new User("user2", "user2@example.com", ratingsUser2));
        users.put("user3", new User("user3", "user3@example.com", ratingsUser3));

        return users;
    }
}
