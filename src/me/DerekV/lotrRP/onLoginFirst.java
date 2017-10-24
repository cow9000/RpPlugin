package me.DerekV.lotrRP;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class onLoginFirst implements Listener{
	
	 public static lotrRP plugin;
	   
	 public onLoginFirst(lotrRP instance) {
	         plugin = instance; 
	        }
	 FileConfiguration userconfig = null;
	 
	@EventHandler
	public void PlayerJoin(PlayerJoinEvent e) throws IOException{
        File userfile = new File(plugin.getDataFolder()+File.separator+"userdata"+File.separator+e.getPlayer().getName()+".yml");
        if (!userfile.exists()) {
            userfile.createNewFile();
            userconfig = YamlConfiguration.loadConfiguration(userfile);
			userconfig.set(e.getPlayer().getUniqueId() + ".class", "");
			userconfig.set(e.getPlayer().getUniqueId() + ".race", "");
			userconfig.set(e.getPlayer().getUniqueId() + ".exp", 0);
			userconfig.set(e.getPlayer().getUniqueId() + ".level", 0);
			userconfig.set(e.getPlayer().getUniqueId() + ".name", e.getPlayer().getName());
			userconfig.set(e.getPlayer().getUniqueId() + ".custom", "");
			userconfig.set(e.getPlayer().getUniqueId() + ".quest", "");
			userconfig.set(e.getPlayer().getUniqueId() + ".quest.name", "");
			userconfig.set(e.getPlayer().getUniqueId() + "quest..assigner", "");
			userconfig.save(userfile);
        }
		
		
	}
	
	
}
