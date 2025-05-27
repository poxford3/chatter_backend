package com.poxford3dev.chatter_backend.Service;

import com.poxford3dev.chatter_backend.Entity.ERole;
import com.poxford3dev.chatter_backend.Entity.Role;
import com.poxford3dev.chatter_backend.Repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class StringToERole {

    @Autowired
    private RoleRepo roleRepo;

    private Set<String> role;

    public Set<Role> stringToERole(Set<String> strRoles) {
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepo.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                if (role.equals("admin")) {
                    Role adminRole = roleRepo.findByName(ERole.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Error: Role (admin) is not found."));
                    roles.add(adminRole);
                } else {
                    Role userRole = roleRepo.findByName(ERole.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Error: Role (user) is not found."));
                    roles.add(userRole);
                }
            });
        }

        return roles;
    }
}
