package com.poxford3dev.chatter_backend.Entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Setter
@Getter
// TODO need to finish setting up the friend stuff,
// having issues with the FriendId
public class FriendId implements Serializable {
    private Integer friendOne;
    private Integer friendTwo;

    public FriendId() {}

    public FriendId(Integer friendOne, Integer friendTwo) {
        this.friendOne = friendOne;
        this.friendTwo = friendTwo;
    }

    // equals() and hashCode() are required for composite keys

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FriendId that)) return false;
        return Objects.equals(friendOne, that.friendOne) &&
                Objects.equals(friendTwo, that.friendTwo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(friendOne, friendTwo);
    }

}
