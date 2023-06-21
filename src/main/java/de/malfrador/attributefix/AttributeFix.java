package de.malfrador.attributefix;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class AttributeFix extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onLogin(PlayerLoginEvent event) {
        Player player = event.getPlayer();
        Attributable defaultAttributes = EntityType.PLAYER.getDefaultAttributes();
        for (Attribute attribute : Attribute.values()) {
            if (defaultAttributes.getAttribute(attribute) == null) continue;
            if (player.getAttribute(attribute) == null) continue;
            double baseValue = defaultAttributes.getAttribute(attribute).getBaseValue();
            double value = player.getAttribute(attribute).getValue();
            if (value != baseValue) {
                player.getAttribute(attribute).setBaseValue(baseValue);
            }
            player.getAttribute(attribute).getModifiers().clear();
        }
    }
}
