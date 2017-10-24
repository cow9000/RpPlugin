package me.DerekV.lotrRP;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import com.darkblade12.particleeffect.ParticleEffect;
import com.darkblade12.particleeffect.ParticleEffect.ParticleData;

public class CustomMobs implements Listener{

	public static lotrRP plugin;
	 public CustomMobs(lotrRP instance) {
	     plugin = instance; 
	 }
	
	
	 @EventHandler
	 public void onPigAttack(EntityDamageByEntityEvent event) {

	 if(event.getEntity().getType().toString()=="CHICKEN") {
		 if(event.getDamager().getType().toString()=="PLAYER") {
				 Entity pig = event.getEntity();
				 Player player = (Player) event.getDamager();
				 pig.teleport(player);
				 for(Player allPlayers : player.getWorld().getPlayers()) {
				 allPlayers.playEffect(pig.getLocation(), Effect.POTION_BREAK, 9);
				 }
				 Integer height = 1;
				 player.damage(3);
				 Vector victor = player.getVelocity();
				 Vector newVictor = victor.add(new Vector(0,height,0));
				 player.setVelocity(newVictor);
			 }
		 }
	 }
	 
		 /*public void createHelix(Player p){
			 for(Double y = p.getEyeLocation().getY();
					 y < p.getEyeLocation().getY() + .5;
					 y+= 0.1) {
	                Location location1 = p.getEyeLocation();
	                Location location2 = p.getEyeLocation();
	                Location location3 = p.getEyeLocation();
	                int particles = 15;
	                float radius = 0.7f;
	                for (int i = 0; i < particles; i++) {
	                    double angle, x, z;
	                    angle = 2 * Math.PI * i / particles;
	                    x = Math.cos(angle) * radius;
	                    z = Math.sin(angle) * radius;
	                    location1.add(x, 0, z);
	                    location2.add(x, -0.66, z);
	                    location3.add(x, -1.33, z);
	                    ParticleEffect.VILLAGER_ANGRY.display(0, 0, 0, 0, i, location1, 1);
	                    ParticleEffect.FIREWORKS_SPARK.display(0, 0, 0, 0, i, location2, 1);
	                    ParticleEffect.DRIP_WATER.display(0, 0, 0, 0, i, location3, 1);
	                    location1.subtract(x, 0, z);
	                    location2.subtract(x, -0.66, z);
	                    location3.subtract(x, -1.33, z);
			    }
			 
			 
			 
			 }
			 
			 
	 }
		 @EventHandler
		 public void onPlayerMoveEvent(PlayerMoveEvent e) {
			 
		createHelix(e.getPlayer());
		 }*/
}
 