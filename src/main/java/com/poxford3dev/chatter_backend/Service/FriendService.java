package com.poxford3dev.chatter_backend.Service;

import com.poxford3dev.chatter_backend.Entity.Friend;
import com.poxford3dev.chatter_backend.Entity.FriendId;
import com.poxford3dev.chatter_backend.Repository.FriendRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FriendService {

    @Autowired
    private FriendRepo friendRepo;

    public List<Friend> getFriendsById(Integer id) {
        return friendRepo.findByFriendOneOrFriendTwo(id, id);
    }

    private List<Integer> orderedFriendId(Integer id1, Integer id2) {
        List<Integer> l = new ArrayList<Integer>();

        if (id1 < id2) {
            l.add(id1);
            l.add(id2);
        } else {
            l.add(id2);
            l.add(id1);
        }
        return l;
    }

    public boolean isAlreadyFriends(Integer id1, Integer id2) {
        return friendRepo.findByFriendOneAndFriendTwo(id1, id2);
    }

    public Friend saveFriendship(Integer friendOne, Integer friendTwo) {
        List<Integer> ordered = orderedFriendId(friendOne, friendTwo);
        Friend friend = new Friend(ordered.get(0), ordered.get(1));
        return friendRepo.save(friend);
    }

    // Retrieve all Friend relationships
    public List<Friend> getAllFriendships() {
        return friendRepo.findAll();
    }

    // Retrieve a specific Friend relationship
    public Optional<Friend> getFriendship(Integer friendOne, Integer friendTwo) {
        List<Integer> ordered = orderedFriendId(friendOne, friendTwo);
        FriendId id = new FriendId(ordered.get(0), ordered.get(1));
        return friendRepo.findById(id);
    }
    
    // Delete a Friend relationship
    public void deleteFriendship(Integer friendOne, Integer friendTwo) {
        List<Integer> ordered = orderedFriendId(friendOne, friendTwo);
        FriendId id = new FriendId(ordered.get(0), ordered.get(1));
        friendRepo.deleteById(id);
    }
}
