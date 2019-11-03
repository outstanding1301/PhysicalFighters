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
/*     */ public class Blitzcrank extends AbilityBase
/*     */ {
/*     */   public Blitzcrank()
/*     */   {
/*  29 */     if (PhysicalFighters.SRankUsed) {
/*  30 */       InitAbility("블리츠크랭크", AbilityBase.Type.Active_Immediately, AbilityBase.Rank.SS, new String[] {
/*  31 */         "눈덩이를 던져, 맞은 적을 자신에게 끌어당깁니다." });
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
/*  45 */       if ((!EventManager.DamageGuard) && 
/*  46 */         ((Event0.getDamager() instanceof Snowball))) {
/*  47 */         Snowball a = (Snowball)Event0.getDamager();
/*  48 */         if (PlayerCheck((Player)a.getShooter())) {
/*  49 */           if (((Event0.getEntity() instanceof Player)) && 
/*  50 */             ((Player)a.getShooter() == 
/*  51 */             (Player)Event0
/*  52 */             .getEntity()))
/*  53 */             return 9999;
/*  54 */           return 0;
/*     */         }
/*     */       }
/*     */       break;
/*     */     case 1: 
/*  59 */       PlayerDropItemEvent Event1 = (PlayerDropItemEvent)event;
/*  60 */       if ((PlayerCheck(Event1.getPlayer())) && 
/*  61 */         (Event1.getItemDrop().getItemStack().getType() == Material.SNOW_BALL)) {
/*  62 */         PlayerInventory inv = Event1.getPlayer().getInventory();
/*  63 */         if (!inv.contains(Material.SNOW_BALL, 16)) {
/*  64 */           return 1;
/*     */         }
/*     */       }
/*     */       break;
/*     */     case 2: 
/*  69 */       PlayerRespawnEvent Event2 = (PlayerRespawnEvent)event;
/*  70 */       if (PlayerCheck(Event2.getPlayer()))
/*  71 */         return 2;
/*     */       break;
/*     */     case 3: 
/*  74 */       EntityDeathEvent Event3 = (EntityDeathEvent)event;
/*  75 */       if (PlayerCheck(Event3.getEntity()))
/*  76 */         return 3;
/*     */       break;
/*     */     }
/*  79 */     return -1;
/*     */   }
/*     */   
/*     */   public void A_Effect(Event event, int CustomData)
/*     */   {
/*  84 */     switch (CustomData) {
/*     */     case 0: 
/*  86 */       EntityDamageByEntityEvent Event0 = (EntityDamageByEntityEvent)event;
/*  87 */       Player pe = (Player)Event0.getEntity();
/*  88 */       Location l2 = GetPlayer().getLocation();
/*  89 */       pe.teleport(l2);
/*  90 */       break;
/*     */     case 1: 
/*  92 */       PlayerDropItemEvent Event1 = (PlayerDropItemEvent)event;
/*  93 */       Event1.getPlayer().sendMessage(
/*  94 */         ChatColor.RED + "소유한 눈덩이가 16개 이하일시 못버립니다.");
/*  95 */       Event1.setCancelled(true);
/*  96 */       break;
/*     */     case 2: 
/*  98 */       PlayerRespawnEvent Event2 = (PlayerRespawnEvent)event;
/*  99 */       Event2.getPlayer().sendMessage(ChatColor.GREEN + "눈덩이가 지급됩니다.");
/* 100 */       PlayerInventory inv = Event2.getPlayer().getInventory();
/* 101 */       inv.setItem(8, new ItemStack(332, 64));
/* 102 */       inv.setItem(7, new ItemStack(332, 64));
/* 103 */       break;
/*     */     case 3: 
/* 105 */       EntityDeathEvent Event3 = (EntityDeathEvent)event;
/* 106 */       List<ItemStack> itemlist = Event3.getDrops();
/* 107 */       for (int l = 0; l < itemlist.size(); l++) {
/* 108 */         if (((ItemStack)itemlist.get(l)).getType() == Material.SNOW_BALL)
/* 109 */           itemlist.remove(l);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void A_SetEvent(Player p) {
/* 115 */     p.getInventory().setItem(8, new ItemStack(332, 64));
/* 116 */     p.getInventory().setItem(7, new ItemStack(332, 64));
/*     */   }
/*     */   
/*     */   public void A_ResetEvent(Player p)
/*     */   {
/* 121 */     p.getInventory().setItem(8, new ItemStack(332, 64));
/* 122 */     p.getInventory().setItem(7, new ItemStack(332, 64));
/*     */   }
/*     */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Blitzcrank.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */