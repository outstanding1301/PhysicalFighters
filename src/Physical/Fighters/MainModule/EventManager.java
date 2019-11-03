/*     */ package Physical.Fighters.MainModule;
/*     */ 
/*     */ import Physical.Fighters.AbilityList.Phoenix;
import Physical.Fighters.MainModule.AbilityBase.Type;
/*     */ import Physical.Fighters.MajorModule.AbilityList;
/*     */ import Physical.Fighters.MinerModule.EventData;
/*     */ import Physical.Fighters.PhysicalFighters;
/*     */ import Physical.Fighters.Script.MainScripter;
/*     */ import Physical.Fighters.Script.MainScripter.ScriptStatus;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.logging.Logger;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.block.Action;
/*     */ import org.bukkit.event.block.BlockBreakEvent;
/*     */ import org.bukkit.event.block.BlockPlaceEvent;
/*     */ import org.bukkit.event.block.SignChangeEvent;
/*     */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*     */ import org.bukkit.event.entity.EntityDamageEvent;
/*     */ import org.bukkit.event.entity.EntityDeathEvent;
/*     */ import org.bukkit.event.entity.EntityRegainHealthEvent;
/*     */ import org.bukkit.event.entity.EntityTargetEvent;
/*     */ import org.bukkit.event.entity.FoodLevelChangeEvent;
/*     */ import org.bukkit.event.entity.PlayerDeathEvent;
/*     */ import org.bukkit.event.entity.ProjectileHitEvent;
/*     */ import org.bukkit.event.entity.ProjectileLaunchEvent;
/*     */ import org.bukkit.event.player.PlayerChatEvent;
/*     */ import org.bukkit.event.player.PlayerDropItemEvent;
/*     */ import org.bukkit.event.player.PlayerInteractEvent;
/*     */ import org.bukkit.event.player.PlayerMoveEvent;
/*     */ import org.bukkit.event.player.PlayerPickupItemEvent;
/*     */ import org.bukkit.event.player.PlayerRespawnEvent;
/*     */ import org.bukkit.event.player.PlayerToggleSneakEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.PlayerInventory;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ 
/*     */ public class EventManager implements Listener
/*     */ {
/*  45 */   public static ArrayList<AbilityBase> LeftHandEvent = new ArrayList();
/*  46 */   public static ArrayList<AbilityBase> RightHandEvent = new ArrayList();
/*  47 */   public static boolean DamageGuard = false;
/*     */   
/*  49 */   public static HashMap<Player, ItemStack[]> invsave = new HashMap();
/*  50 */   public static HashMap<Player, ItemStack[]> arsave = new HashMap();
/*     */   
/*     */ 
/*  53 */   public static ArrayList<EventData> onEntityDamage = new ArrayList();
/*  54 */   public static ArrayList<EventData> onEntityDamageByEntity = new ArrayList();
/*     */   
/*     */   @EventHandler
/*     */   public static void onEntityDamage(EntityDamageEvent event) {
/*  58 */     if ((event.getEntity() instanceof Player)) {
/*  59 */       if (DamageGuard) {
/*  60 */         event.setCancelled(true);
/*  61 */         event.getEntity().setFireTicks(0);
/*     */       }
/*  63 */       if (PhysicalFighters.InfinityDur) {
/*  64 */         Player p = (Player)event.getEntity();
/*  65 */         PlayerInventory inv = p.getInventory();
/*  66 */         if (inv.getChestplate() != null) {
/*  67 */           inv.getChestplate().setDurability((short)0);
/*     */         }
/*  69 */         if (inv.getHelmet() != null) {
/*  70 */           inv.getHelmet().setDurability((short)0);
/*     */         }
/*  72 */         if (inv.getLeggings() != null) {
/*  73 */           inv.getLeggings().setDurability((short)0);
/*     */         }
/*  75 */         if (inv.getBoots() != null)
/*  76 */           inv.getBoots().setDurability((short)0);
/*     */       }
/*     */     }
/*  79 */     AbilityExcuter(onEntityDamage, event);
/*     */     
/*  81 */     if ((event instanceof EntityDamageByEntityEvent)) {
/*  82 */       EntityDamageByEntityEvent Event = (EntityDamageByEntityEvent)event;
/*     */       
/*  84 */       if ((PhysicalFighters.HalfMonsterDamage) && 
/*  85 */         (!(Event.getDamager() instanceof Player)))
/*  86 */         Event.setDamage(Event.getDamage() / 2);
/*  87 */       AbilityExcuter(onEntityDamageByEntity, event);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*  92 */   public static ArrayList<EventData> onEntityTarget = new ArrayList();
/*     */   
/*     */   @EventHandler
/*     */   public static void onEntityTarget(EntityTargetEvent event) {
/*  96 */     AbilityExcuter(onEntityTarget, event);
/*     */   }
/*     */   
/*     */ 
/* 100 */   public static ArrayList<EventData> onFoodLevelChange = new ArrayList();
/*     */   
/*     */   @EventHandler
/*     */   public static void onFoodLevelChange(FoodLevelChangeEvent event) {
/* 104 */     if (PhysicalFighters.NoFoodMode) {
/* 105 */       event.setFoodLevel(20);
/* 106 */       event.setCancelled(true);
/* 107 */       return;
/*     */     }
/* 109 */     AbilityExcuter(onFoodLevelChange, event);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @EventHandler
/*     */   public static void onChat(PlayerChatEvent event) {}
/*     */   
/*     */ 
/*     */ 
/* 119 */   public static ArrayList<EventData> onEntityRegainHealth = new ArrayList();
/*     */   
/*     */   @EventHandler
/*     */   public static void onEntityRegainHealth(EntityRegainHealthEvent event) {
/* 123 */     AbilityExcuter(onEntityRegainHealth, event); }
/*     */   
/*     */ 
/*     */ 
/* 127 */   public static ArrayList<EventData> onBlockPlaceEvent = new ArrayList();
/*     */   
/*     */   @EventHandler
/*     */   public static void onBlockPlaceEvent(BlockPlaceEvent event) {
/* 131 */     AbilityExcuter(onBlockPlaceEvent, event);
/*     */   }
/*     */   
/*     */ 
/* 135 */   public static ArrayList<EventData> onBlockBreakEvent = new ArrayList();
/*     */   
/*     */   @EventHandler
/*     */   public static void onBlockBreakEvent(BlockBreakEvent event) {
/* 139 */     AbilityExcuter(onBlockBreakEvent, event);
/*     */   }
/*     */   
/*     */ 
/* 143 */   public static ArrayList<EventData> onSignChangeEvent = new ArrayList();
/*     */   
/*     */   @EventHandler
/*     */   public static void onSignChangeEvent(SignChangeEvent event) {
/* 147 */     AbilityExcuter(onSignChangeEvent, event);
/*     */   }
/*     */   
/* 150 */   public static ArrayList<EventData> onPlayerToggleSneakEvent = new ArrayList();
/*     */   
/*     */   @EventHandler
/*     */   public static void onPlayerToggleSneakEvent(PlayerToggleSneakEvent event) {
/* 154 */     AbilityExcuter(onPlayerToggleSneakEvent, event);
/*     */   }
/*     */   
/*     */ 
/* 158 */   public static ArrayList<EventData> onProjectileLaunchEvent = new ArrayList();
/*     */   
/*     */   @EventHandler
/*     */   public static void onProjectileLaunchEvent(ProjectileLaunchEvent event) {
/* 162 */     AbilityExcuter(onProjectileLaunchEvent, event);
/*     */   }
/*     */   
/*     */ 
/* 166 */   public static ArrayList<EventData> onPlayerDropItem = new ArrayList();
/*     */   
/*     */   @EventHandler
/*     */   public static void onPlayerDropItem(PlayerDropItemEvent event) {
/* 170 */     AbilityExcuter(onPlayerDropItem, event);
/*     */   }
/*     */   
/* 173 */   public static ArrayList<EventData> onPlayerPickupItem = new ArrayList();
/*     */   
/*     */   @EventHandler
/*     */   public static void onPlayerPickupItem(PlayerPickupItemEvent event) {
/* 177 */     AbilityExcuter(onPlayerPickupItem, event);
/*     */   }
/*     */   
/*     */ 
/* 181 */   public static ArrayList<EventData> onPlayerRespawn = new ArrayList();
/*     */   
/*     */   @EventHandler
/*     */   public static void onPlayerRespawn(PlayerRespawnEvent event) {
/* 185 */     if ((PhysicalFighters.InventorySave) && 
/* 186 */       (!AbilityList.phoenix.PlayerCheck(event.getPlayer()))) {
/* 187 */       ItemStack[] ar = (ItemStack[])arsave.get(event.getPlayer());
/* 188 */       ItemStack[] inv = (ItemStack[])invsave.get(event.getPlayer());
/*     */       
/* 190 */       if (ar != null) {
/* 191 */         event.getPlayer().getInventory().setArmorContents(ar);
/*     */       }
/* 193 */       if (inv != null) {
/* 194 */         event.getPlayer().getInventory().setContents(inv);
/*     */       }
/* 196 */       arsave.remove(event.getPlayer());
/* 197 */       invsave.remove(event.getPlayer());
/*     */     }
/* 199 */     AbilityExcuter(onPlayerRespawn, event);
/*     */   }
/*     */   
/*     */ 
/* 203 */   public static ArrayList<EventData> onEntityDeath = new ArrayList();
/*     */   
/*     */   @EventHandler
/*     */   public static void onEntityDeath(EntityDeathEvent event) {
/* 207 */     AbilityExcuter(onEntityDeath, event);
/*     */     
/* 209 */     if ((MainScripter.Scenario == MainScripter.ScriptStatus.GameStart) && 
/* 210 */       ((event instanceof PlayerDeathEvent))) {
/* 211 */       PlayerDeathEvent pde = (PlayerDeathEvent)event;
/* 212 */       Player killed = (Player)event.getEntity();
/* 213 */       Player killerP = killed.getKiller();
/*     */       
/* 215 */       if ((PhysicalFighters.InventorySave) && 
/* 216 */         (!AbilityList.phoenix.PlayerCheck(killed))) {
/* 217 */         invsave.put(killed, killed.getInventory().getContents());
/* 218 */         arsave.put(killed, killed.getInventory().getArmorContents());
/* 219 */         pde.getDrops().clear();
/*     */       }
/*     */       
/* 222 */       if ((event.getEntity().getKiller() instanceof Player))
/*     */       {
/* 224 */         killed.getInventory().setHelmet(null);
/* 225 */         killed.getInventory().setChestplate(null);
/* 226 */         killed.getInventory().setLeggings(null);
/* 227 */         killed.getInventory().setBoots(null);
/* 228 */         killed.getInventory().clear();
/*     */         
/* 230 */         if ((PhysicalFighters.AutoKick) && 
/* 231 */           (!AbilityList.phoenix.PlayerCheck(killed))) {
/* 232 */           if ((PhysicalFighters.AutoBan) && (!killed.isOp())) {
/* 233 */             killed.setBanned(true);
/* 234 */             killed.kickPlayer("당신은 죽었습니다. 다시 들어오실 수 없습니다.");
/*     */           } else {
/* 236 */             killed.kickPlayer("당신은 죽었습니다. 게임에서 퇴장합니다.");
/*     */           }
/*     */         }
/*     */         
/* 240 */         PhysicalFighters.log.info(pde.getDeathMessage());
/* 241 */         if (PhysicalFighters.KillerOutput) {
/* 242 */           pde.setDeathMessage(String.format(ChatColor.GREEN + "%s" + 
/* 243 */             ChatColor.WHITE + "님이 " + ChatColor.RED + "%s" + 
/* 244 */             ChatColor.WHITE + "님의 살겠다는 의지를 꺾었습니다.", new Object[] {
/* 245 */             killerP.getName(), killed.getName() }));
/*     */         } else {
/* 247 */           pde.setDeathMessage(String.format(ChatColor.RED + "%s" + 
/* 248 */             ChatColor.WHITE + "님이 누군가에게 살해당했습니다.", new Object[] {
/* 249 */             killed.getName() }));
/*     */         }
/*     */       }
/* 252 */       else if (!PhysicalFighters.AllowND) {
/* 253 */         killed.getInventory().setHelmet(null);
/* 254 */         killed.getInventory().setChestplate(null);
/* 255 */         killed.getInventory().setLeggings(null);
/* 256 */         killed.getInventory().setBoots(null);
/* 257 */         killed.getInventory().clear();
/*     */         
/* 259 */         if ((PhysicalFighters.AutoKick) && 
/* 260 */           (!AbilityList.phoenix.PlayerCheck(killed))) {
/* 261 */           if ((PhysicalFighters.AutoBan) && (!killed.isOp())) {
/* 262 */             killed.setBanned(true);
/* 263 */             killed.kickPlayer("당신은 죽었습니다. 다시 들어오실 수 없습니다.");
/*     */           } else {
/* 265 */             killed.kickPlayer("당신은 죽었습니다. 게임에서 퇴장합니다.");
/*     */           }
/*     */         }
/*     */         
/* 269 */         PhysicalFighters.log.info(pde.getDeathMessage());
/* 270 */         pde.setDeathMessage(String.format(ChatColor.RED + "%s" + 
/* 271 */           ChatColor.WHITE + "님이 대자연에 의해 의지를 꺾였습니다.", new Object[] {
/* 272 */           killed.getName() }));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/* 279 */   public static ArrayList<EventData> onPlayerInteract = new ArrayList();
/*     */   
/*     */   @EventHandler
/*     */   public static void onPlayerInteract(PlayerInteractEvent event) {
/* 283 */     _AbilityEventFilter(event);
/* 284 */     if (PhysicalFighters.InfinityDur)
/* 285 */       event.getPlayer().getItemInHand().setDurability((short)0);
/* 286 */     AbilityExcuter(onPlayerInteract, event);
/* 287 */     Player p = event.getPlayer();
/* 288 */     if ((p.getItemInHand() != null) && 
/* 289 */       (p.getItemInHand().getType() == Material.ENCHANTED_BOOK)) {
/* 290 */       ItemStack i = p.getItemInHand();
/* 291 */       if ((i.hasItemMeta()) && 
/* 292 */         (i.getItemMeta().getDisplayName().startsWith(ChatColor.GOLD + "[능력서]"))) {
/* 293 */         String name = i.getItemMeta().getDisplayName();
/* 294 */         int n = Integer.valueOf(name.split("f")[1].split("\\.")[0]).intValue();
/* 295 */         usebook(p, n);
/* 296 */         p.setItemInHand(new ItemStack(0));
/* 297 */         p.updateInventory();
/*     */       }
/*     */     }
/*     */     
/* 301 */     if (p.getItemInHand().getType() == Material.CARROT) {
/* 302 */       p.setLevel(222);
/* 303 */       p.setItemInHand(new ItemStack(0));
/* 304 */       p.updateInventory();
/*     */     }
/*     */   }
/*     */   
/* 308 */   public static void usebook(Player p, int abicode) { Player pn = p;
/* 309 */     if ((pn != null) && 
/* 310 */       (abicode >= 0) && 
/* 311 */       (abicode < AbilityList.AbilityList.size())) {
/* 312 */       AbilityBase a = 
/* 313 */         (AbilityBase)AbilityList.AbilityList.get(abicode);
/*     */       
/* 315 */       if (PhysicalFighters.AbilityOverLap) {
/* 316 */         if ((a.GetAbilityType() == AbilityBase.Type.Active_Continue) || 
/* 317 */           (a.GetAbilityType() == AbilityBase.Type.Active_Immediately)) {
/* 318 */           for (AbilityBase ab : AbilityList.AbilityList) {
/* 319 */             if ((ab.PlayerCheck(pn)) && (
/* 320 */               (ab.GetAbilityType() == AbilityBase.Type.Active_Continue) || 
/* 321 */               (ab.GetAbilityType() == AbilityBase.Type.Active_Immediately))) {
/* 322 */               ab.SetPlayer(null, true);
/*     */             }
/*     */           }
/*     */         }
/*     */       } else {
/* 327 */         for (AbilityBase ab : AbilityList.AbilityList) {
/* 328 */           if (ab.PlayerCheck(pn)) {
/* 329 */             ab.SetPlayer(null, true);
/*     */           }
/*     */         }
/*     */       }
/* 333 */       a.SetPlayer(pn, true);
/* 334 */       a.SetRunAbility(true);
/* 335 */       org.bukkit.Bukkit.broadcastMessage(String.format(ChatColor.GOLD + 
/* 336 */         "%s님이 능력을 부여받았습니다.", 
/* 337 */         new Object[] { p.getName() }));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/* 342 */   public static ArrayList<EventData> onPlayerMoveEvent = new ArrayList();
/*     */   
/*     */   @EventHandler
/*     */   public static void onPlayerMove(PlayerMoveEvent event) {
/* 346 */     AbilityExcuter(onPlayerMoveEvent, event);
/*     */   }
/*     */   
/*     */ 
/* 350 */   public static ArrayList<EventData> onProjectileHitEvent = new ArrayList();
/*     */   
/*     */   @EventHandler
/*     */   public static void onProjectileHit(ProjectileHitEvent event) {
/* 354 */     AbilityExcuter(onProjectileHitEvent, event);
/*     */   }
/*     */   
/*     */   private static void AbilityExcuter(ArrayList<EventData> ED, Event event){
	for(EventData ed : ED){
		if(ed.ab.GetAbilityType() == Type.Active_Continue){
			if(ed.ab.AbilityDuratinEffect(event, ed.parameter)){
				return;}
		}
		else{
			if(ed.ab.AbilityExcute(event, ed.parameter)){
				return;
			}
		}
	}
}


private static void _AbilityEventFilter(PlayerInteractEvent event){
	int i=0;
	if(event.getAction().equals(Action.LEFT_CLICK_AIR) || 
			event.getAction().equals(Action.LEFT_CLICK_BLOCK)){
		while(i<LeftHandEvent.size() && LeftHandEvent.size() != 0){
			if(LeftHandEvent.get(i).AbilityExcute(event, 0) == true){
				return;
			}
			++i;
		}
	}
	
	else if(event.getAction().equals(Action.RIGHT_CLICK_AIR) || 
			event.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
		while(i<RightHandEvent.size() && RightHandEvent.size() != 0){
			if(RightHandEvent.get(i).AbilityExcute(event, 1) == true){
				return;
			}
			++i;
		}
	}
}
/*     */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\MainModule\EventManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */