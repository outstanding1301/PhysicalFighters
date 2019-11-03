/*     */ package Physical.Fighters.AbilityList;
/*     */ 
/*     */ import Physical.Fighters.MainModule.AbilityBase;
/*     */ import Physical.Fighters.MainModule.AbilityBase.Rank;
/*     */ import Physical.Fighters.MainModule.AbilityBase.Type;
/*     */ import Physical.Fighters.MainModule.EventManager;
/*     */ import Physical.Fighters.MinerModule.EventData;
/*     */ import Physical.Fighters.PhysicalFighters;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.entity.Item;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.entity.Snowball;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*     */ import org.bukkit.event.entity.EntityDeathEvent;
/*     */ import org.bukkit.event.player.PlayerDropItemEvent;
/*     */ import org.bukkit.event.player.PlayerRespawnEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.PlayerInventory;
/*     */ 
/*     */ public class Minato extends AbilityBase
/*     */ {
/*     */   public Minato()
/*     */   {
/*  29 */     if (PhysicalFighters.SRankUsed) {
/*  30 */       InitAbility("미나토", AbilityBase.Type.Passive_Manual, AbilityBase.Rank.S, new String[] {
/*  31 */         "눈덩이를 던져, 맞은 적에게 텔레포트합니다." });
/*  32 */       InitAbility(1, 0, true);
/*  33 */       EventManager.onEntityDamageByEntity.add(new EventData(this, 0));
/*  34 */       EventManager.onPlayerDropItem.add(new EventData(this, 1));
/*  35 */       EventManager.onPlayerRespawn.add(new EventData(this, 2));
/*  36 */       EventManager.onEntityDeath.add(new EventData(this, 3));
/*     */     }
/*     */   }
/*     */   
/*     */   public int A_Condition(Event event, int CustomData)
/*     */   {
/*  42 */     switch (CustomData) {
/*     */     case 0: 
/*  44 */       EntityDamageByEntityEvent Event0 = (EntityDamageByEntityEvent)event;
/*  45 */       if ((Event0.getDamager() instanceof Snowball)) {
/*  46 */         Snowball a = (Snowball)Event0.getDamager();
/*  47 */         if (PlayerCheck((Player)a.getShooter())) {
/*  48 */           if (((Event0.getEntity() instanceof Player)) && 
/*  49 */             ((Player)a.getShooter() == 
/*  50 */             (Player)Event0
/*  51 */             .getEntity()))
/*  52 */             return 9999;
/*  53 */           return 0;
/*     */         }
/*     */       }
/*     */       break;
/*     */     case 1: 
/*  58 */       PlayerDropItemEvent Event1 = (PlayerDropItemEvent)event;
/*  59 */       if ((PlayerCheck(Event1.getPlayer())) && 
/*  60 */         (Event1.getItemDrop().getItemStack().getType() == Material.SNOW_BALL)) {
/*  61 */         PlayerInventory inv = Event1.getPlayer().getInventory();
/*  62 */         if (!inv.contains(Material.SNOW_BALL, 16)) {
/*  63 */           return 1;
/*     */         }
/*     */       }
/*     */       break;
/*     */     case 2: 
/*  68 */       PlayerRespawnEvent Event2 = (PlayerRespawnEvent)event;
/*  69 */       if (PlayerCheck(Event2.getPlayer()))
/*  70 */         return 2;
/*     */       break;
/*     */     case 3: 
/*  73 */       EntityDeathEvent Event3 = (EntityDeathEvent)event;
/*  74 */       if (PlayerCheck(Event3.getEntity()))
/*  75 */         return 3;
/*     */       break;
/*     */     }
/*  78 */     return -1;
/*     */   }
/*     */   
/*     */   public void A_Effect(Event event, int CustomData)
/*     */   {
/*  83 */     switch (CustomData) {
/*     */     case 0: 
/*  85 */       EntityDamageByEntityEvent Event0 = (EntityDamageByEntityEvent)event;
/*  86 */       Player pe = (Player)Event0.getEntity();
/*  87 */       Location l1 = pe.getLocation();
/*  88 */       GetPlayer().teleport(l1);
/*  89 */       break;
/*     */     case 1: 
/*  91 */       PlayerDropItemEvent Event1 = (PlayerDropItemEvent)event;
/*  92 */       Event1.getPlayer().sendMessage(
/*  93 */         ChatColor.RED + "소유한 눈덩이가 16개 이하일시 못버립니다.");
/*  94 */       Event1.setCancelled(true);
/*  95 */       break;
/*     */     case 2: 
/*  97 */       PlayerRespawnEvent Event2 = (PlayerRespawnEvent)event;
/*  98 */       Event2.getPlayer().sendMessage(ChatColor.GREEN + "눈덩이가 지급됩니다.");
/*  99 */       PlayerInventory inv = Event2.getPlayer().getInventory();
/* 100 */       inv.setItem(8, new ItemStack(332, 64));
/* 101 */       inv.setItem(7, new ItemStack(332, 64));
/* 102 */       break;
/*     */     case 3: 
/* 104 */       EntityDeathEvent Event3 = (EntityDeathEvent)event;
/* 105 */       List<ItemStack> itemlist = Event3.getDrops();
/* 106 */       for (int l = 0; l < itemlist.size(); l++) {
/* 107 */         if (((ItemStack)itemlist.get(l)).getType() == Material.SNOW_BALL)
/* 108 */           itemlist.remove(l);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void A_SetEvent(Player p) {
/* 114 */     p.getInventory().setItem(8, new ItemStack(332, 64));
/* 115 */     p.getInventory().setItem(7, new ItemStack(332, 64));
/*     */   }
/*     */   
/*     */   public void A_ResetEvent(Player p)
/*     */   {
/* 120 */     p.getInventory().setItem(8, new ItemStack(332, 64));
/* 121 */     p.getInventory().setItem(7, new ItemStack(332, 64));
/*     */   }
/*     */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Minato.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */