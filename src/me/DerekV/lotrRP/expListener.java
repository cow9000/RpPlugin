package me.DerekV.lotrRP;

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.CaveSpider;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Ocelot;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Slime;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Squid;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Witch;
import org.bukkit.entity.WitherSkull;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.material.Mushroom;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class expListener implements Listener{

	
	 public static lotrRP plugin;
	   
	 public expListener(lotrRP instance) {
	     plugin = instance; 
	 }
	
	@EventHandler
	public void onMonsterDeath(EntityDeathEvent e){
		
		int exp = 0;
		
		if(e.getEntity() instanceof Cow){
			exp = 0;
		}else if(e.getEntity() instanceof Chicken){
			exp = 0;
		}else if(e.getEntity() instanceof Pig){
			exp = 0;
		}else if(e.getEntity() instanceof Horse){
			exp = 0;
		}else if(e.getEntity() instanceof Ocelot){
			exp = 0;
		}else if(e.getEntity() instanceof Sheep){
			exp = 0;
		}else if(e.getEntity() instanceof Bat){
			exp = 0;
		}else if(e.getEntity() instanceof Mushroom){
			exp = 0;
		}else if(e.getEntity() instanceof Squid){
			exp = 0;
		}else if(e.getEntity() instanceof Villager){
			exp = 0;
		}else if(e.getEntity() instanceof CaveSpider){
			exp = 3;
		}else if(e.getEntity() instanceof Spider){
			exp = 2;
		}else if(e.getEntity() instanceof Blaze){
			exp = 4;
		}else if(e.getEntity() instanceof Creeper){
			exp = 3;
		}else if(e.getEntity() instanceof Witch){
			exp = 3;
		}else if(e.getEntity() instanceof Zombie){
			exp = 2;
		}else if(e.getEntity() instanceof Slime){
			exp = 1;
		}else if(e.getEntity() instanceof Skeleton){
			exp = 2;
		}
		
		
		
		
		LivingEntity entity = (LivingEntity) e.getEntity();
			if(entity.getKiller() instanceof Player && !(entity.getHealth() > 1)){
				Player p = (Player) e.getEntity().getKiller();
				Location loc = new Location(p.getWorld(), entity.getLocation().getX(), entity.getLocation().getY() + 1, entity.getLocation().getZ());
				
				final WitherSkull skull = (WitherSkull) p.getWorld().spawn(loc, WitherSkull.class);
				skull.setDirection(new Vector(0, 0, 0));
				skull.setVelocity(new Vector(0, 0, 0));
				final Bat bat = (Bat) e.getEntity().getLocation().getWorld().spawnEntity(e.getEntity().getLocation(), EntityType.BAT);
				bat.setCustomName(ChatColor.GREEN + Integer.toString(exp) + " Experience");
				bat.setCustomNameVisible(true);
				bat.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 10000, 100));
				skull.setPassenger(bat);
				
				  FileConfiguration userconfig = null;
			      File userfile = new File(plugin.getDataFolder()+File.separator+"userdata"+File.separator+p.getName()+".yml");
			      userconfig = YamlConfiguration.loadConfiguration(userfile);
			      int expA = userconfig.getInt(p.getUniqueId() + ".exp");
			      int level = userconfig.getInt(p.getUniqueId() + ".level");
			      userconfig.set(p.getUniqueId() + ".exp", expA + exp);
			      
			      try {
					userconfig.save(userfile);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			      
			      
			      
			      if(level == 0){
			    	  if(expA + exp > 150){
					      userconfig.set(p.getUniqueId() + ".exp", 0);
					      userconfig.set(p.getUniqueId() + ".level", level + 1);
					      try {
								userconfig.save(userfile);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
					      level = userconfig.getInt(p.getUniqueId() + ".level");
					      p.sendMessage(ChatColor.GOLD + "+1 level! you are now level " + level);
			    	  }
			      }else if(level == 1){
			    	  if(expA + exp > 450){
					      userconfig.set(p.getUniqueId() + ".exp", 0);
					      userconfig.set(p.getUniqueId() + ".exp", level + 1);
					      try {
								userconfig.save(userfile);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
					      level = userconfig.getInt(p.getUniqueId() + ".level");
					      p.sendMessage(ChatColor.GOLD + "+1 level! you are now level " + level);
			    	  }
			      }
			      
			      
			      
				plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
					 
					  public void run() {
					      bat.remove();
					      skull.remove();
					  }
					}, 60L);// 60 L == 3 sec, 20 ticks == 1 sec
				
				
				
				
				
			}
		}
	
	
	
	
}
