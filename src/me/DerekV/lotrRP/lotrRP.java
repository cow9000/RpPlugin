package me.DerekV.lotrRP;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Chest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.WitherSkull;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class lotrRP extends JavaPlugin{
	
    
	public static lotrRP plugin;
	public final Logger logger = Logger.getLogger("Minecraft");
	

	
	
	onLoginFirst olf = new onLoginFirst(this);
	linkWeapon lw = new linkWeapon(this);
	rightClickChicken rcc = new rightClickChicken();
	InventoryListener il = new InventoryListener(this);
	QuestingSystem qs = new QuestingSystem(this);
	expListener el = new expListener(this);
	
	//Class listeners
	classRanger cr = new classRanger(this);
	CustomMobs cm = new CustomMobs(null);
	

	@Override
	public void onDisable(){
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName() + " Version" + pdfFile.getVersion() + " Has been Disabled!");
		//CustomEntityType.unregisterEntities();
	}
	
	
	@Override
	public void onEnable(){
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName() + " Version" + pdfFile.getVersion() + " Has been Enabled!");
		getConfig().options().copyDefaults(true);
		saveConfig();
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(this.olf, this);
		pm.registerEvents(this.lw, this);
		pm.registerEvents(this.cr, this);
		pm.registerEvents(this.cm, this);
		pm.registerEvents(this.rcc, this);
		pm.registerEvents(this.il, this);
		pm.registerEvents(this.qs, this);
		pm.registerEvents(this.el, this);
		//CustomEntityType.registerEntities();
		
	    
        File userfiles;
        try {
            userfiles = new File(getDataFolder() + File.separator + "userdata");
            if(!userfiles.exists()){
                userfiles.mkdirs();
            }
        } catch(SecurityException e) {
            userfiles = null;
        }
		
		
		//Custom recipes
		ItemStack chicken = new ItemStack(Material.CLAY_BALL);
		ItemMeta im = chicken.getItemMeta();
		im.setDisplayName(ChatColor.RED + "????");
		chicken.setItemMeta(im);
		ShapedRecipe chickenSummoner = new ShapedRecipe(chicken);
		chickenSummoner.shape("^%^","%*%","^%^");
		chickenSummoner.setIngredient('%', Material.SEEDS);
		chickenSummoner.setIngredient('*', Material.MILK_BUCKET);
		chickenSummoner.setIngredient('^', Material.EGG);
		getServer().addRecipe(chickenSummoner);
	}
	
	public void saveFile(FileConfiguration userconfig, File userfile){
		saveFile(userconfig, userfile);
	}
	
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
        if (!(sender instanceof Player)) {
            return true;
    }
        
        
        
    Player p = (Player) sender;
    
    FileConfiguration userconfig = null;
    File userfile = new File(this.getDataFolder()+File.separator+"userdata"+File.separator+p.getName()+".yml");
    userconfig = YamlConfiguration.loadConfiguration(userfile);
    
    if(cmd.getName().equalsIgnoreCase("rp")){
    	if(args.length == 0){
    		p.sendMessage(ChatColor.GOLD + "=======" + ChatColor.GREEN + "Roluikcraft RP" + ChatColor.GOLD +"=======");
    		p.sendMessage(ChatColor.RED + "Commands - ");
    		p.sendMessage(ChatColor.GREEN + "/rp about - Tells about server");
    		p.sendMessage(ChatColor.GREEN + "/rp class - Allows you to get a class");
    		p.sendMessage(ChatColor.GREEN + "/rp race - Allows you to get a race");
    		p.sendMessage(ChatColor.GREEN + "/rp skills - Shows you all the skills open too you.");
    		p.sendMessage(ChatColor.GREEN + "/rp fastloc [locname]- sets fast-travel markers");
    		
    		p.sendMessage(ChatColor.GREEN + "/rp npc converse [npcname] [npc chat] - Sets NPC. ONLY FOR OP");
    		p.sendMessage(ChatColor.GREEN + "/rp npc quest add [npcname] [questname] - Sets NPC. ONLY FOR OP");
    		p.sendMessage(ChatColor.GREEN + "/rp npc quest desc [npcname] [desc of quest] - Sets NPC. ONLY FOR OP");
    		p.sendMessage(ChatColor.GREEN + "/rp npc quest obj [npcname] [obj] [other] - Sets NPC. ONLY FOR OP");
    		p.sendMessage(ChatColor.GREEN + "/rp npc quest sound [npcname] [sound] - Sets NPC. ONLY FOR OP");
    		p.sendMessage(ChatColor.GREEN + "/rp quest leave - leave quest");
    		
    		p.sendMessage(ChatColor.GOLD + "==========================");
			
    		
    		
    		/*CraftWorld cw = (CraftWorld)p.getWorld();
			CustomEntityChicken bc = new CustomEntityChicken(cw.getHandle());
			bc.setPosition(p.getLocation().getX(), (p.getLocation().getY()), (p.getLocation().getZ()));
			cw.getHandle().addEntity(bc);*/
    		
    		WitherSkull skull = (WitherSkull) p.getWorld().spawn(p.getLocation(), WitherSkull.class);
    		skull.setDirection(new Vector(0, 0, 0));
    		skull.setVelocity(new Vector(0, 0, 0));
    		
    		
    	}else{
    		if(args[0].equalsIgnoreCase("about")){
    			p.sendMessage(ChatColor.GOLD + "This plugin was made to give you a unique feel to this rp server, it was coded by cow9000 who helped create the server. You can choose classes, races, and other things to make your game adventourous!");
    		}else if(args[0].equals("race")){
    			if (args.length == 2){
    				//Check if not already set
    				Object choice = userconfig.get(p.getUniqueId() + ".race");
    				if(choice.equals("")){
    					
    					if(args[1].equalsIgnoreCase("Human")){
	    					p.sendMessage(ChatColor.GREEN + "You are now a human!");
	    					userconfig.set(p.getUniqueId() + ".race", "human");
	    					try {
								userconfig.save(userfile);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
    					}
    					if(args[1].equalsIgnoreCase("Elf")){
	    					p.sendMessage(ChatColor.GREEN + "You are now a elf!");
	    					userconfig.set(p.getUniqueId() + ".race", "elf");
	    					try {
								userconfig.save(userfile);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
    					}
    					if(args[1].equalsIgnoreCase("Dwarf")){
	    					p.sendMessage(ChatColor.GREEN + "You are now a dwarf!");
	    					userconfig.set(p.getUniqueId() + ".race", "dwarf");
	    					try {
								userconfig.save(userfile);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
    					}
    					if(args[1].equalsIgnoreCase("Hobbit")){
	    					p.sendMessage(ChatColor.GREEN + "You are now a hobbit!");
	    					userconfig.set(p.getUniqueId() + ".race", "hobbit");
	    					try {
								userconfig.save(userfile);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
    					}
    					if(args[1].equalsIgnoreCase("Undead")){
	    					p.sendMessage(ChatColor.GREEN + "You are now a undead!");
	    					userconfig.set(p.getUniqueId() + ".race", "undead");
	    					try {
								userconfig.save(userfile);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
    					}
    					if(args[1].equalsIgnoreCase("Orc")){
	    					p.sendMessage(ChatColor.GREEN + "You are now a orc!");
	    					userconfig.set(p.getUniqueId() + ".race", "orc");
	    					try {
								userconfig.save(userfile);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
    					}
    					if(args[1].equalsIgnoreCase("demon")){
	    					p.sendMessage(ChatColor.GREEN + "You are now a demon!");
	    					userconfig.set(p.getUniqueId() + ".race", "demon");
	    					saveFile(userconfig, userfile);
    					}
    					if(args[1].equalsIgnoreCase("dragon")){
	    					p.sendMessage(ChatColor.GREEN + "You are now a dragon!");
	    					userconfig.set(p.getUniqueId() + ".race", "dragon");
	    					try {
								userconfig.save(userfile);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
    					}
    					if(args[1].equalsIgnoreCase("ent")){
	    					p.sendMessage(ChatColor.GREEN + "You are now a ent!");
	    					userconfig.set(p.getUniqueId() + ".race", "ent");
	    					try {
								userconfig.save(userfile);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
    					}
    				}else{
    					p.sendMessage(ChatColor.RED + "You are already a - " + userconfig.get(p.getUniqueId() + ".race"));
    				}
    			}else{
    				p.sendMessage(ChatColor.RED + "Correct useage - /rp race [race]");
    				p.sendMessage(ChatColor.GOLD + "Races - Human, Elf, Dwarf, Hobbit, Orc, Undead, Demon, Dragon, Ent");
    			}
    		}else if(args[0].equalsIgnoreCase("class")){
    			if(args.length == 2){
    				Object choice2 = userconfig.getString(p.getUniqueId() + ".class");
    				if(choice2.equals("")){
	    				if(args[1].equalsIgnoreCase("Ranger")){
	    					p.sendMessage(ChatColor.GREEN + "You are now a ranger!");
	    					userconfig.set(p.getUniqueId() + ".class", "ranger");
	    					try {
								userconfig.save(userfile);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
	    					
	    					//Bow itemstack
	    					ItemStack bow = new ItemStack(Material.BOW, 1);
	    					ItemMeta im = bow.getItemMeta();
	    					
	    					ArrayList<String> lore = new ArrayList<String>();
	    					lore.add(ChatColor.GOLD + "Level:1");
	    					lore.add(ChatColor.GREEN + "Beginners bow");
	    					
	    					int i = 0;
	    					for(String s: lore){
	    					    s.replace("&", "§");
	    					    lore.set(i, s);
	    					    i++;
	    					}
	    				
	    					im.setDisplayName("Branch and string");
	    					
	    					im.setLore(lore);
	    					
	    					
	    					bow.setItemMeta(im);
	    					
	    					bow.getItemMeta().setLore(lore);
	    					
	    					p.getInventory().addItem(bow);
	    				}
	    				if(args[1].equalsIgnoreCase("Wizard")){
	    					p.sendMessage(ChatColor.GREEN + "You are now a wizard!");
	    					userconfig.set(p.getUniqueId() + ".class", "wizard");
	    					try {
								userconfig.save(userfile);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
	    				}
	    				if(args[1].equalsIgnoreCase("sorc")){
	    					p.sendMessage(ChatColor.GREEN + "You are now a sorc!");
	    					userconfig.set(p.getUniqueId() + ".class", "sorc");
	    					try {
								userconfig.save(userfile);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
	    				}
	    				if(args[1].equalsIgnoreCase("Warrior")){
	    					p.sendMessage(ChatColor.GREEN + "You are now a warrior!");
	    					userconfig.set(p.getUniqueId() + ".class", "warrior");
	    					try {
								userconfig.save(userfile);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
	    				}
	    				if(args[1].equalsIgnoreCase("Rogue")){
	    					p.sendMessage(ChatColor.GREEN + "You are now a rogue!");
	    					userconfig.set(p.getUniqueId() + ".class", "rogue");
	    					try {
								userconfig.save(userfile);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
	    				}
	    				if(args[1].equalsIgnoreCase("Healer")){
	    					p.sendMessage(ChatColor.GREEN + "You are now a healer!");
	    					userconfig.set(p.getUniqueId() + ".class", "healer");
	    					try {
								userconfig.save(userfile);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
	    				}
    				}else{
    					p.sendMessage(ChatColor.RED + "You already have a class - " + userconfig.get(p.getUniqueId() + ".class"));
    				}
    			}else{
    				p.sendMessage(ChatColor.RED + "Correct useage - /rp class [class]");
    				p.sendMessage(ChatColor.GOLD + "Classes - Ranger, Wizard, Sorc, Warrior, Rogue, Healer");
    			}
    		}else if(args[0].equalsIgnoreCase("skills")){
    			
    			/*RANGER INV
    			 * 
    			 * 
    			 * 
    			 * */
    			Inventory myInventory = Bukkit.createInventory(null, 27, "Inventory");
    			if(userconfig.get(p.getUniqueId() + ".class").equals("ranger")){
    				
    				int Level = (int) (userconfig.getInt(p.getUniqueId() + ".level"));
    				Object choice = userconfig.getStringList(p.getUniqueId() + ".custom");
    				List<String> toggle = new ArrayList<String>();
    				
    				
    				if(Level >= 5){
    					if(!choice.toString().contains("fire") && !choice.toString().contains("acid") && !choice.toString().contains("ice")){
    						
    						List<String> fire = new ArrayList<String>();
    						fire.add("If choosen, Allows you to shoot fire arrows at enemys.");
    						
    						ItemStack i1 = new ItemStack(Material.STAINED_CLAY, 1, (short) 13); 
    						ItemMeta im = i1.getItemMeta();
    						im.setLore(fire);
    						im.setDisplayName(ChatColor.RED + "Fire Arrows");
    						i1.setItemMeta(im);
    						
    						myInventory.setItem(0, i1);
    						
    					}else{
    						List<String> fire = new ArrayList<String>();
    						fire.add("You already have it!");
    						
    						ItemStack i1 = new ItemStack(Material.STAINED_CLAY, 1, (short) 14); 
    						ItemMeta im = i1.getItemMeta();
    						im.setLore(fire);
    						im.setDisplayName(ChatColor.RED + "Fire Arrows !");
    						i1.setItemMeta(im);
    						
    						myInventory.setItem(0, i1);
    				}
    					
    				}else{
    					List<String> fire = new ArrayList<String>();
    						fire.add("You are not high enough level");
    						
    						ItemStack i1 = new ItemStack(Material.STAINED_CLAY, 1, (short) 14); 
    						ItemMeta im = i1.getItemMeta();
    						im.setLore(fire);
    						im.setDisplayName(ChatColor.RED + "Fire Arrows !");
    						i1.setItemMeta(im);
    						
    						myInventory.setItem(0, i1);
    				}
    				
    				//Level 2 fire arrows
    				if(Level >= 10){
    					if(!choice.toString().contains("fire2") && !choice.toString().contains("acid") && !choice.toString().contains("ice")){
    						if(choice.toString().contains("fire")){
    							List<String> fire = new ArrayList<String>();
        						fire.add("If choosen, your enemys will be burnt longer.");
        						
        						ItemStack i2 = new ItemStack(Material.STAINED_CLAY, 1, (short) 13); 
        						ItemMeta im = i2.getItemMeta();
        						im.setLore(fire);
        						im.setDisplayName(ChatColor.RED + "Fire Arrows 2");
        						i2.setItemMeta(im);
        						
        						myInventory.setItem(1, i2);
    						}else{
    	    					List<String> fire = new ArrayList<String>();
    							fire.add("You must have Fire Arrows 1!");
    							
    							ItemStack i2 = new ItemStack(Material.STAINED_CLAY, 1, (short) 14); 
    							ItemMeta im = i2.getItemMeta();
    							im.setLore(fire);
    							im.setDisplayName(ChatColor.RED + "Fire Arrows 2!");
    							i2.setItemMeta(im);
    							
    							myInventory.setItem(1, i2);
    						}
    					}else{
    						List<String> fire = new ArrayList<String>();
    						fire.add("You already have it!");
    						
    						ItemStack i2 = new ItemStack(Material.STAINED_CLAY, 1, (short) 14); 
    						ItemMeta im = i2.getItemMeta();
    						im.setLore(fire);
    						im.setDisplayName(ChatColor.RED + "Fire Arrows 2!");
    						i2.setItemMeta(im);
    						
    						myInventory.setItem(1, i2);
    					}
    				}else{
    					List<String> fire = new ArrayList<String>();
						fire.add("You are not high enough level");
						
						ItemStack i2 = new ItemStack(Material.STAINED_CLAY, 1, (short) 14); 
						ItemMeta im = i2.getItemMeta();
						im.setLore(fire);
						im.setDisplayName(ChatColor.RED + "Fire Arrows 2!");
						i2.setItemMeta(im);
						
						myInventory.setItem(1, i2);
    				}
    				if(Level >= 15){
    					if(!choice.toString().contains("fire3") && !choice.toString().contains("acid") && !choice.toString().contains("ice")){
    						if(choice.toString().contains("fire2")){
    	    					List<String> fire = new ArrayList<String>();
    							fire.add("10% Ability to dodge arrows");
    							
    							ItemStack i2 = new ItemStack(Material.STAINED_CLAY, 1, (short) 13); 
    							ItemMeta im = i2.getItemMeta();
    							im.setLore(fire);
    							im.setDisplayName(ChatColor.RED + "Dodge Arrow 1");
    							i2.setItemMeta(im);
    							
    							myInventory.setItem(2, i2);
    						}else{
    	    					List<String> fire = new ArrayList<String>();
    							fire.add("You must have Fire Arrows 2!");
    							
    							ItemStack i2 = new ItemStack(Material.STAINED_CLAY, 1, (short) 14); 
    							ItemMeta im = i2.getItemMeta();
    							im.setLore(fire);
    							im.setDisplayName(ChatColor.RED + "Dodge Arrow 1!");
    							i2.setItemMeta(im);
    							
    							myInventory.setItem(2, i2);
    						}
    					}else{
        					List<String> fire = new ArrayList<String>();
    						fire.add("You already have it!");
    						
    						ItemStack i2 = new ItemStack(Material.STAINED_CLAY, 1, (short) 14); 
    						ItemMeta im = i2.getItemMeta();
    						im.setLore(fire);
    						im.setDisplayName(ChatColor.RED + "Dodge Arrow 1!");
    						i2.setItemMeta(im);
    						
    						myInventory.setItem(2, i2);
    					}
    				}else{
    					List<String> fire = new ArrayList<String>();
						fire.add("Not high enough level!");
						
						ItemStack i2 = new ItemStack(Material.STAINED_CLAY, 1, (short) 14); 
						ItemMeta im = i2.getItemMeta();
						im.setLore(fire);
						im.setDisplayName(ChatColor.RED + "Dodge Arrow 1!");
						i2.setItemMeta(im);
						
						myInventory.setItem(2, i2);
    				}
    				if(Level >= 20){
    					if(!choice.toString().contains("fire4") && !choice.toString().contains("acid") && !choice.toString().contains("ice")){
    						if(choice.toString().contains("fire3")){
    	    					List<String> fire = new ArrayList<String>();
    							fire.add("Your bow will deal more melee damage!");
    							
    							ItemStack i2 = new ItemStack(Material.STAINED_CLAY, 1, (short) 13); 
    							ItemMeta im = i2.getItemMeta();
    							im.setLore(fire);
    							im.setDisplayName(ChatColor.RED + "Damaging Bow");
    							i2.setItemMeta(im);
    							
    							myInventory.setItem(3, i2);
    						}else{
    	    					List<String> fire = new ArrayList<String>();
    							fire.add("You must have Dodge Arrow 1!");
    							
    							ItemStack i2 = new ItemStack(Material.STAINED_CLAY, 1, (short) 14); 
    							ItemMeta im = i2.getItemMeta();
    							im.setLore(fire);
    							im.setDisplayName(ChatColor.RED + "Damaging Bow!");
    							i2.setItemMeta(im);
    							
    							myInventory.setItem(3, i2);
    						}
    					}else{
        					List<String> fire = new ArrayList<String>();
    						fire.add("You already have it!");
    						
    						ItemStack i2 = new ItemStack(Material.STAINED_CLAY, 1, (short) 14); 
    						ItemMeta im = i2.getItemMeta();
    						im.setLore(fire);
    						im.setDisplayName(ChatColor.RED + "Damaging Bow!");
    						i2.setItemMeta(im);
    						
    						myInventory.setItem(3, i2);
    					}
    				}else{
    					List<String> fire = new ArrayList<String>();
						fire.add("Not high enough level!");
						
						ItemStack i2 = new ItemStack(Material.STAINED_CLAY, 1, (short) 14); 
						ItemMeta im = i2.getItemMeta();
						im.setLore(fire);
						im.setDisplayName(ChatColor.RED + "Damaging Bow!");
						i2.setItemMeta(im);
						
						myInventory.setItem(3, i2);
    				}
    				p.openInventory(myInventory);
    			}
  
    			/*
    			 * 
    			 * 
    			 * 
    			 * 
    			 * 
    			 * */
    			
    		}else if(args[0].equalsIgnoreCase("npc")){
    			//
    			//
    			//
    			//
		    	if(args[1].equalsIgnoreCase("converse")){
		    		if(args.length > 3){
		        		if(p.isOp()){
		    					
		    					String npcName = args[2];
		    					
		        				String message = "";
		    					for(int i = 3; i < args.length; i++){
		    						message = message + " " + args[i];
		    						message = message.replace("&", "§");
		    					}
		    					
		    					this.getConfig().set(npcName + ".converse", message);
		    					this.saveConfig();
		        				
		    					p.sendMessage(ChatColor.GOLD + npcName + " chats now is -" + message);
		    					
		    				}else{
				    			p.sendMessage(ChatColor.RED + "You are not op!");
				    		}
		    			}else{
		    				p.sendMessage(ChatColor.RED + "/rp npc converse [Npc name] [string], /rp npc quest [] [] []");
			    		}
		    		//
		    		//
		    		//
		    		//
		    		}else if(args[1].equalsIgnoreCase("quest")){
		    			if(args[2].equalsIgnoreCase("desc")){
		    				
		    				if(args.length > 5){
		    					if(p.isOp()){
			    					String npcName = args[3];
			    					String questDesc = "";
			    					for(int i = 4; i < args.length; i++){
			    						questDesc = questDesc + " " + args[i];
			    					}
			    					
			    					
			    					
			    					this.getConfig().set(npcName + ".quest.desc", questDesc);
			    					
			    					this.saveConfig();
			    					
			    					p.sendMessage(ChatColor.GREEN + "You have set the desc to " + questDesc);
			    					
		    					}else{
			    					p.sendMessage(ChatColor.RED + "You are not op!");
			    				}
		    				}
		    				//
		    				//
		    				//
		    				//
		    			}else if(args[2].equalsIgnoreCase("sound")){
		    				
		    				if(args.length > 4){
		    					String NPCname = args[3];
		    					String soundfile = args[4];
		    					
		    					p.sendMessage(ChatColor.GREEN + "Sound set!");
		    					
		    					this.getConfig().set(NPCname + ".sound", soundfile);
		    					this.saveConfig();
		    					
		    				}
		    				
		    			}
		    				else if(args[2].equalsIgnoreCase("add")){
			    			
			    			if(args.length > 4){
			    				if(p.isOp()){
			    					
			    					String npcName = args[3];
			    					String npcQuest = args[4];
			    					
			    					p.sendMessage(ChatColor.GREEN + "Added " + npcQuest + " to " + npcName);
			    					
			    					this.getConfig().set(npcName + ".quest.name", npcQuest);
			    					
			    					this.saveConfig();
			    					
			    				}else{
			    					p.sendMessage(ChatColor.RED + "You are not op!");
			    				}
			    			}
		    			}else if(args[2].equalsIgnoreCase("objective")){
		    				if(args.length < 6){
		    					p.sendMessage(ChatColor.GREEN + "Objectives - kill, obtain, location, talk, help");
		    				}else{
		    					String npcName = args[4];
		    					String npcObj = args[5];
		    					String other = args[6];
		    					this.getConfig().set(npcName + ".quest.objective", npcObj);
		    					
		    					this.saveConfig();
		    					
		    				}
		    				
		    			}
		    			//
		    			//
		    			//
		    			//
		    		}else{
		    			p.sendMessage(ChatColor.GREEN + "/rp npc converse [npc name] [conversation]\n/rp npc quest add [npcname] [questname]\n"
		    					+ "/rp npc quest desc [npc name] [quest desc]\n");
		    		}
    			}else if(args[0].equalsIgnoreCase("fastloc") && p.isOp()){
    				if(args.length > 1){
    					String name = args[1];
    					Location loc = p.getLocation();
    					
    					this.getConfig().set("fastT.loc." + name, name);
    					this.getConfig().set("fastT.loc."  + name + ".locx", loc.getX());
    					this.getConfig().set("fastT.loc."  + name + ".locy", loc.getY());
    					this.getConfig().set("fastT.loc."  + name + ".locz", loc.getZ());
    					this.getConfig().set("fastT.loc."  + name + ".locyaw", loc.getYaw());
    					this.getConfig().set("fastT.loc."  + name + ".locpitch", loc.getPitch());
    					
    					
    					List<String> location = this.getConfig().getStringList("fastT.loc.names");
    					location.add(name);
    					
    					
    					
    					this.getConfig().set("fastT.loc.names", location);
    					
    					this.saveConfig();
    					
    					p.sendMessage(ChatColor.GREEN + "Location saved!");
    					
    				}else{
    					p.sendMessage(ChatColor.RED + "/rp fastloc [locName]");
    				}
    				
    				
    				
    			}else if(args[0].equalsIgnoreCase("quest")){
    	    		if(args[1].equalsIgnoreCase("leave")){
    	    			userconfig.set(p.getUniqueId() + ".quest.name", "");
    	    			userconfig.set(p.getUniqueId() + ".quest.assigner", "");
    	    			p.sendMessage(ChatColor.RED + "You have left the quest.");
    	    			try {
							userconfig.save(userfile);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
    	    		}
    	    	}
    		
    		
    		
    		
    		
    		
    	}
    	
    	
    	
    	
    }
		
		
		return true;
	}
	
	
	
}
