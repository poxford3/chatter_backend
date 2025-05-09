package com.poxford3dev.chatter_backend.Repository;

import com.poxford3dev.chatter_backend.Entity.Friend;
import com.poxford3dev.chatter_backend.Entity.FriendId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendRepo extends JpaRepository<Friend, FriendId> {

    /**
     *
     * @param friendOne id of friend one
     * @param friendTwo id of friend two
     * @return a list of friends based on given id
     */
    List<Friend> findByFriendOneOrFriendTwo(Integer friendOne, Integer friendTwo);

    /**
     *
     * @param friendOne id of friend one
     * @param friendTwo id of friend two
     * @return boolean stating whether 2 users are already friends
     */
    boolean findByFriendOneAndFriendTwo(Integer friendOne, Integer friendTwo);
}
