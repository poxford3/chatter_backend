package com.poxford3dev.chatter_backend.Controller;

import com.poxford3dev.chatter_backend.Entity.Friend;
import com.poxford3dev.chatter_backend.Service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/friends")
public class FriendController {

    @Autowired
    private FriendService friendService;

    // Create a new Friend relationship
    @PostMapping("/{friendOne}/{friendTwo}")
    public ResponseEntity<Friend> createFriendship(@PathVariable Integer friendOne, @PathVariable Integer friendTwo) {
        Friend friend = friendService.saveFriendship(friendOne, friendTwo);
        return ResponseEntity.ok(friend);
    }

    // Get all Friend relationships
    @GetMapping
    public ResponseEntity<List<Friend>> getAllFriendships() {
        return ResponseEntity.ok(friendService.getAllFriendships());
    }

    // Get specific Friend relationship
    @GetMapping("/{friendOne}/{friendTwo}")
    public ResponseEntity<Friend> getFriendship(@PathVariable Integer friendOne, @PathVariable Integer friendTwo) {
        return friendService.getFriendship(friendOne, friendTwo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Friend>> getFriendById(@PathVariable("id") Integer id) {
        // have to pass twice bc in the FriendRepo, it uses a `%s, %s` syntax to search both columns with an OR
        return ResponseEntity.ok(friendService.getFriendsById(id));
    }

    @DeleteMapping("/{id1}/{id2}")
    public void deleteFriendship(@PathVariable Integer friendOne, @PathVariable Integer friendTwo) {
//        return ResponseEntity.ok(friendService.deleteFriendship(friendOne, friendTwo));
        friendService.deleteFriendship(friendOne, friendTwo);
    }
}
