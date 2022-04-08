package net.blessedfoxx.ticketbot.utils;

import net.dv8tion.jda.api.entities.Role;

import java.awt.Color;
import java.util.List;

public class UserManager {

    public static Color getRoleColor(List<Role> roles) {
        for (Role role : roles) {

            if (role.getColor() != null){
                return role.getColor();
            }

        }
        return Color.decode("#E91E63");
    }
}

