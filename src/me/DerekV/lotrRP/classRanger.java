package me.DerekV.lotrRP;


import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;

public class classRanger implements Listener{
	
	public static lotrRP plugin;
	
	public classRanger(lotrRP instance){
		plugin = instance;
	}
	
	
	@EventHandler
	public void EntityShootBowEvent(EntityDamageByEntityEvent e){
		
		//Bow mroe damage!
		
		if(e.getDamager() instanceof Player){
			
			Player p = (Player) e.getDamager();
			
			FileConfiguration userconfig = null;
		    File userfile = new File(plugin.getDataFolder()+File.separator+"userdata"+File.separator+ p.getName() +".yml");
		    userconfig = YamlConfiguration.loadConfiguration(userfile);
		    if(userconfig.getString(p.getUniqueId() + ".class").equals("ranger")){
				if(userconfig.getInt(p.getUniqueId() + ".level") >= 20){
					if(userconfig.getStringList(p.getUniqueId() + ".custom").contains("fire4")){
						if(p.getItemInHand().equals(Material.BOW)){
							LivingEntity le = (LivingEntity) e.getEntity();
							le.damage(5);
						}
					}
				}
		    }
		}
		
		//Fire arrows!
		if(e.getDamager() instanceof Arrow){
			Arrow arrow = (Arrow) e.getDamager();
			if(arrow.getShooter() instanceof Player){
				
				
				//Get shooter
				Player p = (Player) arrow.getShooter();
				
				FileConfiguration userconfig = null;
			    File userfile = new File(plugin.getDataFolder()+File.separator+"userdata"+File.separator+ p.getName() +".yml");
			    userconfig = YamlConfiguration.loadConfiguration(userfile);
			    
			    
			    //Check if ...
			    if(userconfig.getString(p.getUniqueId() + ".class").equals("ranger")){
			    	if(userconfig.getInt(p.getUniqueId() + ".level") >= 5){
			    		if(userconfig.getStringList(p.getUniqueId() + ".custom").contains("fire")){
							arrow.getWorld().playEffect(arrow.getLocation(), Effect.MOBSPAWNER_FLAMES, 5);
							int Level = (int) (userconfig.getInt(p.getUniqueId() + ".level") * 1.25 + 20);
							
							if(userconfig.getStringList(p.getUniqueId() + ".custom").contains("fire2")){
								Level = (int) (userconfig.getInt(p.getUniqueId() + ".level") * 1.25 + 30);
							}
							
							//Sets max level of fire
							if(Level > 75){
								Level = 60;
							}
							e.getEntity().setFireTicks(Level);
			    		}
			    	}
				}
			}			
			
		}
		if(e.getEntity() instanceof Player){
			
			Player p = (Player) e.getEntity();
			FileConfiguration userconfig = null;
		    File userfile = new File(plugin.getDataFolder()+File.separator+"userdata"+File.separator+ p.getName() +".yml");
		    userconfig = YamlConfiguration.loadConfiguration(userfile);
		    
			if(userconfig.getInt(p.getUniqueId() + ".level") >= 15){
	    		if(userconfig.getStringList(p.getUniqueId() + ".custom").contains("fire3")){
	    			if(e.getDamager() instanceof Arrow){
	    				if(e.getEntity() instanceof Player){
	    					
	    					int i = (int) (Math.random()*10+1);
	    					if(i == 1){
	    						p.getInventory().addItem(new ItemStack(Material.ARROW, 1));
	    						p.sendMessage(ChatColor.GREEN + "Arrow dodged!");
	    						e.setCancelled(true);
	    					}
	    				}
	    			}
	    		}
	    	}
			
		}
		
		
		
		
//Testing
	
		
		
		
		
	}
	
	
}
