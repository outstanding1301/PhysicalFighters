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
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.entity.Arrow;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.Item;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*     */ import org.bukkit.event.entity.EntityDeathEvent;
/*     */ import org.bukkit.event.player.PlayerDropItemEvent;
/*     */ import org.bukkit.event.player.PlayerRespawnEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.PlayerInventory;
/*     */ 
/*     */ public class Archer extends AbilityBase
/*     */ {
/*     */   public Archer()
/*     */   {
/*  29 */     InitAbility("아쳐", AbilityBase.Type.Passive_Manual, AbilityBase.Rank.A, new String[] {
/*  30 */       "죽거나 게임 시작시 화살 한묶음이 고정적으로 주어집니다.", 
/*  31 */       "60% 확률로 불화살을 쏘며  40% 확률로 작은 폭발을 일으킵니다.", "불화살의 불은 6초간 지속됩니다.", 
/*  32 */       "화살 데미지가 3 상승합니다." });
/*  33 */     InitAbility(0, 0, true);
/*  34 */     EventManager.onEntityDamageByEntity.add(new EventData(this, 0));
/*  35 */     EventManager.onPlayerDropItem.add(new EventData(this, 1));
/*  36 */     EventManager.onPlayerRespawn.add(new EventData(this, 2));
/*  37 */     EventManager.onEntityDeath.add(new EventData(this, 3));
/*     */   }
/*     */   
/*     */   public int A_Condition(Event event, int CustomData)
/*     */   {
/*  42 */     switch (CustomData) {
/*     */     case 0: 
/*  44 */       EntityDamageByEntityEvent Event0 = (EntityDamageByEntityEvent)event;
/*  45 */       if ((Event0.getDamager() instanceof Arrow)) {
/*  46 */         Arrow a = (Arrow)Event0.getDamager();
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
/*  60 */         (Event1.getItemDrop().getItemStack().getType() == Material.ARROW)) {
/*  61 */         PlayerInventory inv = Event1.getPlayer().getInventory();
/*  62 */         if (!inv.contains(Material.ARROW, 64)) {
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
/*  86 */       Event0.setDamage((int) (Event0.getDamage() + 3.0D));
/*  87 */       if (Math.random() <= 0.6D) {
/*  88 */         Event0.getEntity().setFireTicks(120);
/*  89 */       } else if (Math.random() <= 0.4D) {
/*  90 */         World w = Event0.getEntity().getWorld();
/*  91 */         w.createExplosion(Event0.getEntity().getLocation(), 1.7F);
/*     */       }
/*  93 */       break;
/*     */     case 1: 
/*  95 */       PlayerDropItemEvent Event1 = (PlayerDropItemEvent)event;
/*  96 */       Event1.getPlayer().sendMessage(
/*  97 */         ChatColor.RED + "소유한 화살이 64개 이하일시 화살을 버릴수 없습니다.");
/*  98 */       Event1.setCancelled(true);
/*  99 */       break;
/*     */     case 2: 
/* 101 */       PlayerRespawnEvent Event2 = (PlayerRespawnEvent)event;
/* 102 */       Event2.getPlayer().sendMessage(
/* 103 */         ChatColor.GREEN + "이전에 소유했던 화살은 모두 소멸하며 다시 지급됩니다.");
/* 104 */       PlayerInventory inv = Event2.getPlayer().getInventory();
/* 105 */       inv.remove(new ItemStack(262, 64));
/* 106 */       inv.setItem(8, new ItemStack(262, 64));
/* 107 */       inv.setItem(7, new ItemStack(261, 1));
/* 108 */       break;
/*     */     case 3: 
/* 110 */       EntityDeathEvent Event3 = (EntityDeathEvent)event;
/* 111 */       List<ItemStack> itemlist = Event3.getDrops();
/* 112 */       for (int l = 0; l < itemlist.size(); l++) {
/* 113 */         if (((ItemStack)itemlist.get(l)).getType() == Material.ARROW)
/* 114 */           itemlist.remove(l);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void A_SetEvent(Player p) {
/* 120 */     p.getInventory().setItem(8, new ItemStack(262, 64));
/* 121 */     p.getInventory().setItem(7, new ItemStack(261, 1));
/*     */   }
/*     */   
/*     */   public void A_ResetEvent(Player p)
/*     */   {
/* 126 */     p.getInventory().removeItem(new ItemStack[] { new ItemStack(262, 64) });
/*     */   }
/*     */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Archer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */