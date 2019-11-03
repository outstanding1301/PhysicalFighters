/*     */ package Physical.Fighters.AbilityList;
/*     */ 
/*     */ import Physical.Fighters.MainModule.AbilityBase;
/*     */ import Physical.Fighters.MainModule.AbilityBase.Rank;
/*     */ import Physical.Fighters.MainModule.AbilityBase.Type;
/*     */ import Physical.Fighters.MainModule.EventManager;
/*     */ import Physical.Fighters.MinerModule.EventData;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.entity.Arrow;
/*     */ import org.bukkit.entity.Item;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*     */ import org.bukkit.event.entity.EntityDeathEvent;
/*     */ import org.bukkit.event.player.PlayerDropItemEvent;
/*     */ import org.bukkit.event.player.PlayerRespawnEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.PlayerInventory;
/*     */ import org.bukkit.potion.PotionEffect;
/*     */ import org.bukkit.potion.PotionEffectType;
/*     */ 
/*     */ public class PoisonArrow extends AbilityBase
/*     */ {
/*     */   public PoisonArrow()
/*     */   {
/*  29 */     InitAbility("독화살", AbilityBase.Type.Passive_Manual, AbilityBase.Rank.B, new String[] {
/*  30 */       "죽거나 게임 시작시 화살 한묶음이 고정적으로 주어집니다.", "화살에 맞은 적은 6초간 독에걸립니다." });
/*  31 */     InitAbility(0, 0, true);
/*  32 */     EventManager.onEntityDamageByEntity.add(new EventData(this, 0));
/*  33 */     EventManager.onPlayerDropItem.add(new EventData(this, 1));
/*  34 */     EventManager.onPlayerRespawn.add(new EventData(this, 2));
/*  35 */     EventManager.onEntityDeath.add(new EventData(this, 3));
/*     */   }
/*     */   
/*     */   public int A_Condition(Event event, int CustomData) {
/*  39 */     switch (CustomData) {
/*     */     case 0: 
/*  41 */       EntityDamageByEntityEvent Event0 = (EntityDamageByEntityEvent)event;
/*  42 */       if ((Event0.getDamager() instanceof Arrow)) {
/*  43 */         Arrow a = (Arrow)Event0.getDamager();
/*  44 */         if (PlayerCheck((Player)a.getShooter())) {
/*  45 */           if (((Event0.getEntity() instanceof Player)) && 
/*  46 */             ((Player)a.getShooter() == 
/*  47 */             (Player)Event0
/*  48 */             .getEntity()))
/*  49 */             return 9999;
/*  50 */           return 0;
/*     */         }
/*     */       }
/*     */       break;
/*     */     case 1: 
/*  55 */       PlayerDropItemEvent Event1 = (PlayerDropItemEvent)event;
/*  56 */       if ((PlayerCheck(Event1.getPlayer())) && 
/*  57 */         (Event1.getItemDrop().getItemStack().getType() == Material.ARROW)) {
/*  58 */         PlayerInventory inv = Event1.getPlayer().getInventory();
/*  59 */         if (!inv.contains(Material.ARROW, 64)) {
/*  60 */           return 1;
/*     */         }
/*     */       }
/*     */       break;
/*     */     case 2: 
/*  65 */       PlayerRespawnEvent Event2 = (PlayerRespawnEvent)event;
/*  66 */       if (PlayerCheck(Event2.getPlayer()))
/*  67 */         return 2;
/*     */       break;
/*     */     case 3: 
/*  70 */       EntityDeathEvent Event3 = (EntityDeathEvent)event;
/*  71 */       if (PlayerCheck(Event3.getEntity()))
/*  72 */         return 3;
/*     */       break;
/*     */     }
/*  75 */     return -1;
/*     */   }
/*     */   
/*     */   public void A_Effect(Event event, int CustomData)
/*     */   {
/*  80 */     switch (CustomData) {
/*     */     case 0: 
/*  82 */       EntityDamageByEntityEvent Event0 = (EntityDamageByEntityEvent)event;
/*  83 */       Player p = (Player)Event0.getEntity();
/*  84 */       p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 
/*  85 */         60, 0), true);
/*  86 */       p.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 120, 
/*  87 */         0), true);
/*  88 */       break;
/*     */     case 1: 
/*  90 */       PlayerDropItemEvent Event1 = (PlayerDropItemEvent)event;
/*  91 */       Event1.getPlayer().sendMessage(
/*  92 */         ChatColor.RED + "소유한 화살이 64개 이하일시 화살을 버릴수 없습니다.");
/*  93 */       Event1.setCancelled(true);
/*  94 */       break;
/*     */     case 2: 
/*  96 */       PlayerRespawnEvent Event2 = (PlayerRespawnEvent)event;
/*  97 */       Event2.getPlayer().sendMessage(
/*  98 */         ChatColor.GREEN + "이전에 소유했던 화살은 모두 소멸하며 다시 지급됩니다.");
/*  99 */       PlayerInventory inv = Event2.getPlayer().getInventory();
/* 100 */       inv.remove(new ItemStack(262, 64));
/* 101 */       inv.setItem(8, new ItemStack(262, 64));
/* 102 */       inv.setItem(7, new ItemStack(261, 1));
/* 103 */       break;
/*     */     case 3: 
/* 105 */       EntityDeathEvent Event3 = (EntityDeathEvent)event;
/* 106 */       List<ItemStack> itemlist = Event3.getDrops();
/* 107 */       for (int l = 0; l < itemlist.size(); l++) {
/* 108 */         if (((ItemStack)itemlist.get(l)).getType() == Material.ARROW)
/* 109 */           itemlist.remove(l);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void A_SetEvent(Player p) {
/* 115 */     p.getInventory().setItem(8, new ItemStack(262, 64));
/* 116 */     p.getInventory().setItem(7, new ItemStack(261, 1));
/*     */   }
/*     */   
/*     */   public void A_ResetEvent(Player p)
/*     */   {
/* 121 */     p.getInventory().removeItem(new ItemStack[] { new ItemStack(262, 64) });
/*     */   }
/*     */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\PoisonArrow.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */