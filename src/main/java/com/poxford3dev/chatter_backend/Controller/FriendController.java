package com.poxford3dev.chatter_backend.Controller;

import com.poxford3dev.chatter_backend.Entity.Friend;
import com.poxford3dev.chatter_backend.Service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/friends")
public class FriendController {

    @Autowired
    private FriendService friendService;

    @GetMapping("/{id}")
    public List<Friend> getFriendById(@PathVariable("id") Integer id) {
        // have to pass twice bc in the FriendRepo, it uses a `%s, %s` syntax to search both columns with an OR
        return friendService.getFriendsById(id);
    }

    @PostMapping("/{id1}/{id2}")
    public boolean addFriend(@PathVariable("id1") Integer id1, @PathVariable("id2") Integer id2) {
        return friendService.addFriend(id1,id2);
    }
}
