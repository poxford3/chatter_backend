package com.poxford3dev.chatter_backend.Service;

import com.poxford3dev.chatter_backend.Entity.Friend;
import com.poxford3dev.chatter_backend.Entity.FriendId;
import com.poxford3dev.chatter_backend.Repository.FriendRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public boolean addFriend(Integer userOneId, Integer userTwoId) {
        List<Integer> friendsOrdered = orderedFriendId(userOneId, userTwoId);
        if (isAlreadyFriends(userOneId, userTwoId)) {
            throw new EntityNotFoundException("Error: already friends");
        } else {
            Friend newFriend = new Friend(friendsOrdered.get(0), friendsOrdered.get(1));
            friendRepo.save(newFriend);
            return true;
        }
    }
}
