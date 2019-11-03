/*     */ package Physical.Fighters.AbilityList;
/*     */ 
/*     */ import Physical.Fighters.MainModule.AbilityBase;
/*     */ import Physical.Fighters.MainModule.AbilityBase.Rank;
/*     */ import Physical.Fighters.MainModule.AbilityBase.ShowText;
/*     */ import Physical.Fighters.MainModule.AbilityBase.Type;
/*     */ import Physical.Fighters.MainModule.EventManager;
/*     */ import Physical.Fighters.MinerModule.EventData;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.entity.Arrow;
/*     */ import org.bukkit.entity.EntityType;
/*     */ import org.bukkit.entity.Item;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.entity.Projectile;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*     */ import org.bukkit.event.entity.EntityDeathEvent;
/*     */ import org.bukkit.event.entity.ProjectileLaunchEvent;
/*     */ import org.bukkit.event.player.PlayerDropItemEvent;
/*     */ import org.bukkit.event.player.PlayerRespawnEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.PlayerInventory;
/*     */ import org.bukkit.potion.PotionEffect;
/*     */ import org.bukkit.potion.PotionEffectType;
/*     */ import org.bukkit.util.Vector;
/*     */ 
/*     */ public class Multishot extends AbilityBase
/*     */ {
/*     */   public Multishot()
/*     */   {
/*  36 */     InitAbility("멀티샷", AbilityBase.Type.Active_Immediately, AbilityBase.Rank.A, new String[] {
/*  37 */       "죽거나 게임 시작시 화살 한묶음이 고정적으로 주어집니다.", 
/*  38 */       "화살 발사시에 여러발이 퍼지면서 날라갑니다. [움직이면서 사격시 화살이 나가지 않을 수 있습니다.]" });
/*  39 */     InitAbility(3, 0, true, AbilityBase.ShowText.All_Text);
/*  40 */     EventManager.onProjectileLaunchEvent.add(new EventData(this, 0));
/*  41 */     EventManager.onPlayerDropItem.add(new EventData(this, 1));
/*  42 */     EventManager.onPlayerRespawn.add(new EventData(this, 2));
/*  43 */     EventManager.onEntityDeath.add(new EventData(this, 3));
/*  44 */     EventManager.onEntityDamageByEntity.add(new EventData(this, 4));
/*     */   }
/*     */   
/*     */   public int A_Condition(Event event, int CustomData) {
/*  48 */     switch (CustomData) {
/*     */     case 0: 
/*  50 */       ProjectileLaunchEvent Event0 = (ProjectileLaunchEvent)event;
/*  51 */       if (((Event0.getEntity().getShooter() instanceof Player)) && 
/*  52 */         ((Event0.getEntity() instanceof Arrow)) && (((Player)Event0.getEntity().getShooter()).getItemInHand().getType() == Material.BOW)) {
/*  53 */         Arrow a = (Arrow)Event0.getEntity();
/*  54 */         if (PlayerCheck((Player)a.getShooter())) {
/*  55 */           return 0;
/*     */         }
/*     */       }
/*     */       
/*     */       break;
/*     */     case 1: 
/*  61 */       PlayerDropItemEvent Event1 = (PlayerDropItemEvent)event;
/*  62 */       if ((PlayerCheck(Event1.getPlayer())) && 
/*  63 */         (Event1.getItemDrop().getItemStack().getType() == Material.ARROW)) {
/*  64 */         PlayerInventory inv = Event1.getPlayer().getInventory();
/*  65 */         if (!inv.contains(Material.ARROW, 64)) {
/*  66 */           return 1;
/*     */         }
/*     */       }
/*     */       break;
/*     */     case 2: 
/*  71 */       PlayerRespawnEvent Event2 = (PlayerRespawnEvent)event;
/*  72 */       if (PlayerCheck(Event2.getPlayer()))
/*  73 */         return 2;
/*     */       break;
/*     */     case 3: 
/*  76 */       EntityDeathEvent Event3 = (EntityDeathEvent)event;
/*  77 */       if (PlayerCheck(Event3.getEntity()))
/*  78 */         return 3;
/*     */       break;
/*     */     case 4: 
/*  81 */       EntityDamageByEntityEvent Event4 = (EntityDamageByEntityEvent)event;
/*  82 */       if ((Event4.getDamager() instanceof Arrow)) {
/*  83 */         Arrow a = (Arrow)Event4.getDamager();
/*  84 */         if (PlayerCheck((Player)a.getShooter())) {
/*  85 */           if (((Event4.getEntity() instanceof Player)) && 
/*  86 */             ((Player)a.getShooter() == 
/*  87 */             (Player)Event4
/*  88 */             .getEntity()))
/*  89 */             return -1;
/*  90 */           if (Event4.getEntityType() != EntityType.PLAYER) {
/*  91 */             return 1;
/*     */           }
/*  93 */           ((Player)Event4.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20, 2), true);
/*  94 */           ((Player)Event4.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20, 2), false);
/*  95 */           ((Player)Event4.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20, 2), false);
/*     */         }
/*     */       }
/*     */       break;
/*     */     }
/* 100 */     return -1;
/*     */   }
/*     */   
/*     */   public void A_Effect(Event event, int CustomData)
/*     */   {
/* 105 */     switch (CustomData) {
/*     */     case 0: 
/* 107 */       ProjectileLaunchEvent Event0 = (ProjectileLaunchEvent)event;
/*     */       
/* 109 */       Arrow a2 = (Arrow)Event0.getEntity();
/* 110 */       Player p = (Player)a2.getShooter();
/* 111 */       a2.remove();
/* 112 */       Location ll = p.getLocation();
/* 113 */       Location l1 = p.getLocation();
/* 114 */       Location l2 = p.getLocation();
/* 115 */       Location l3 = p.getLocation();
/* 116 */       l1.setY(l2.getY() + 1.5D);
/* 117 */       AbilityBase.LookAngle(l1, l2, 0);
/* 118 */       AbilityBase.LookAngle(l3, ll, 100);
/* 119 */       Vector v = ll.toVector().subtract(l2.toVector()).normalize();
/* 120 */       for (int i = 0; i <= 10; i++)
/*     */       {
/* 122 */         Arrow a = p.getWorld().spawnArrow(l2, v, 1.5F, 10.0F);
/* 123 */         a.setVelocity(a.getVelocity().multiply(2.2D));
/* 124 */         a.setShooter(p);
/*     */       }
/* 126 */       break;
/*     */     case 1: 
/* 128 */       PlayerDropItemEvent Event1 = (PlayerDropItemEvent)event;
/* 129 */       Event1.getPlayer().sendMessage(
/* 130 */         ChatColor.RED + "소유한 화살이 64개 이하일시 화살을 버릴수 없습니다.");
/* 131 */       Event1.setCancelled(true);
/* 132 */       break;
/*     */     case 2: 
/* 134 */       PlayerRespawnEvent Event2 = (PlayerRespawnEvent)event;
/* 135 */       Event2.getPlayer().sendMessage(
/* 136 */         ChatColor.GREEN + "이전에 소유했던 화살은 모두 소멸하며 다시 지급됩니다.");
/* 137 */       PlayerInventory inv = Event2.getPlayer().getInventory();
/* 138 */       inv.remove(new ItemStack(262, 64));
/* 139 */       inv.setItem(8, new ItemStack(262, 64));
/* 140 */       inv.setItem(7, new ItemStack(261, 1));
/* 141 */       break;
/*     */     case 3: 
/* 143 */       EntityDeathEvent Event3 = (EntityDeathEvent)event;
/* 144 */       List<ItemStack> itemlist = Event3.getDrops();
/* 145 */       for (int l = 0; l < itemlist.size(); l++) {
/* 146 */         if (((ItemStack)itemlist.get(l)).getType() == Material.ARROW)
/* 147 */           itemlist.remove(l);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void A_SetEvent(Player p) {
/* 153 */     p.getInventory().setItem(8, new ItemStack(262, 64));
/* 154 */     p.getInventory().setItem(7, new ItemStack(261, 1));
/*     */   }
/*     */   
/*     */   public void A_ResetEvent(Player p)
/*     */   {
/* 159 */     p.getInventory().removeItem(new ItemStack[] { new ItemStack(262, 64) });
/*     */   }
/*     */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Multishot.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */