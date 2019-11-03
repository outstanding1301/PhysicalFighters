/*     */ package Physical.Fighters.AbilityList;
/*     */ 
/*     */ import Physical.Fighters.MainModule.AbilityBase;
/*     */ import Physical.Fighters.MainModule.AbilityBase.Rank;
/*     */ import Physical.Fighters.MainModule.AbilityBase.Type;
/*     */ import Physical.Fighters.MainModule.EventManager;
/*     */ import Physical.Fighters.MinerModule.ACC;
/*     */ import Physical.Fighters.MinerModule.EventData;
/*     */ import Physical.Fighters.PhysicalFighters;
/*     */ import java.util.ArrayList;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.entity.EntityDamageEvent;
/*     */ import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
/*     */ import org.bukkit.event.player.PlayerInteractEvent;
/*     */ import org.bukkit.event.player.PlayerMoveEvent;
/*     */ import org.bukkit.potion.PotionEffect;
/*     */ import org.bukkit.potion.PotionEffectType;
/*     */ 
/*     */ 
/*     */ public class Poseidon
/*     */   extends AbilityBase
/*     */ {
/*     */   public Poseidon()
/*     */   {
/*  31 */     if ((!PhysicalFighters.Toner) && 
/*  32 */       (PhysicalFighters.SRankUsed)) {
/*  33 */       InitAbility("포세이돈", AbilityBase.Type.Active_Immediately, AbilityBase.Rank.SS, new String[] {
/*  34 */         "바라보는곳에 거대한 어항을 만들어 가둡니다.", "물에서 숨을 쉴 수 있습니다." });
/*  35 */       InitAbility(60, 0, true);
/*  36 */       RegisterLeftClickEvent();
/*  37 */       EventManager.onEntityDamage.add(new EventData(this, 3));
/*  38 */       EventManager.onPlayerMoveEvent.add(new EventData(this, 4));
/*     */     }
/*     */   }
/*     */   
/*     */   public int A_Condition(Event event, int CustomData)
/*     */   {
/*  44 */     if (CustomData == 0) {
/*  45 */       PlayerInteractEvent Event = (PlayerInteractEvent)event;
/*  46 */       if ((!EventManager.DamageGuard) && 
/*  47 */         (PlayerCheck(Event.getPlayer())) && 
/*  48 */         (ItemCheck(ACC.DefaultItem)) && 
/*  49 */         (Event.getPlayer().getTargetBlock(null, 0).getType() != Material.BEDROCK)) {
/*  50 */         return 0;
/*     */       }
/*     */     }
/*  53 */     if (CustomData == 3) {
/*  54 */       EntityDamageEvent Event2 = (EntityDamageEvent)event;
/*  55 */       if ((PlayerCheck(Event2.getEntity())) && 
/*  56 */         (Event2.getCause() == EntityDamageEvent.DamageCause.DROWNING)) {
/*  57 */         Event2.setCancelled(true);
/*     */       }
/*     */     }
/*  60 */     if (CustomData == 4) {
/*  61 */       PlayerMoveEvent e = (PlayerMoveEvent)event;
/*  62 */       if (PlayerCheck(e.getPlayer())) {
/*  63 */         if (e.getPlayer().getLocation().getBlock().isLiquid())
/*     */         {
/*  65 */           e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 999999999, 1));
/*  66 */           e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 999999999, 1));
/*     */         } else {
/*  68 */           e.getPlayer().removePotionEffect(PotionEffectType.SPEED);
/*  69 */           e.getPlayer().removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
/*     */         }
/*  71 */         for (Player p : Bukkit.getOnlinePlayers()) {
/*  72 */           if ((p.getLocation().distance(e.getPlayer().getLocation()) <= 10.0D) && 
/*  73 */             (p.getLocation().getBlock().isLiquid()) && 
/*  74 */             (p != e.getPlayer())) {
/*  75 */             p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 1));
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*  83 */     return -1;
/*     */   }
/*     */   
/*     */   public void A_Effect(Event event, int CustomData)
/*     */   {
/*  88 */     PlayerInteractEvent Event = (PlayerInteractEvent)event;
/*  89 */     Player p = Event.getPlayer();
/*  90 */     Location l1 = p.getTargetBlock(null, 0).getLocation();
/*  91 */     Location l2 = p.getTargetBlock(null, 0).getLocation();
/*  92 */     for (int j = 0; j <= 8; j++) {
/*  93 */       l2.setY(l1.getY() + j);
/*  94 */       for (int i = 0; i <= 5; i++) {
/*  95 */         l2.setX(l1.getX() + i);
/*  96 */         p.getWorld().getBlockAt(l2).setType(Material.GLASS);
/*  97 */         for (int k = 0; k <= 5; k++) {
/*  98 */           l2.setZ(l1.getZ() + k);
/*  99 */           p.getWorld().getBlockAt(l2).setType(Material.GLASS);
/*     */         }
/*     */       }
/* 102 */       for (int i = 0; i <= 5; i++) {
/* 103 */         l2.setX(l1.getX() - i);
/* 104 */         p.getWorld().getBlockAt(l2).setType(Material.GLASS);
/* 105 */         for (int k = 0; k <= 5; k++) {
/* 106 */           l2.setZ(l1.getZ() - k);
/* 107 */           p.getWorld().getBlockAt(l2).setType(Material.GLASS);
/*     */         }
/*     */       }
/* 110 */       for (int i = 0; i <= 5; i++) {
/* 111 */         l2.setX(l1.getX() - i);
/* 112 */         p.getWorld().getBlockAt(l2).setType(Material.GLASS);
/* 113 */         for (int k = 0; k <= 5; k++) {
/* 114 */           l2.setZ(l1.getZ() + k);
/* 115 */           p.getWorld().getBlockAt(l2).setType(Material.GLASS);
/*     */         }
/*     */       }
/* 118 */       for (int i = 0; i <= 5; i++) {
/* 119 */         l2.setX(l1.getX() + i);
/*     */         
/* 121 */         p.getWorld().getBlockAt(l2).setType(Material.GLASS);
/* 122 */         for (int k = 0; k <= 5; k++) {
/* 123 */           l2.setZ(l1.getZ() - k);
/*     */           
/* 125 */           p.getWorld().getBlockAt(l2).setType(Material.GLASS);
/*     */         }
/*     */       }
/*     */     }
/* 129 */     for (int j = 1; j <= 6; j++) {
/* 130 */       l2.setY(l1.getY() + j);
/* 131 */       for (int i = 0; i <= 2; i++) {
/* 132 */         l2.setX(l1.getX() + i);
/*     */         
/* 134 */         p.getWorld().getBlockAt(l2).setType(Material.WATER);
/* 135 */         for (int k = 0; k <= 2; k++) {
/* 136 */           l2.setZ(l1.getZ() + k);
/*     */           
/* 138 */           p.getWorld().getBlockAt(l2).setType(Material.WATER);
/*     */         }
/*     */       }
/* 141 */       for (int i = 0; i <= 2; i++) {
/* 142 */         l2.setX(l1.getX() - i);
/*     */         
/* 144 */         p.getWorld().getBlockAt(l2).setType(Material.WATER);
/* 145 */         for (int k = 0; k <= 2; k++) {
/* 146 */           l2.setZ(l1.getZ() - k);
/*     */           
/* 148 */           p.getWorld().getBlockAt(l2).setType(Material.WATER);
/*     */         }
/*     */       }
/* 151 */       for (int i = 0; i <= 2; i++) {
/* 152 */         l2.setX(l1.getX() - i);
/*     */         
/* 154 */         p.getWorld().getBlockAt(l2).setType(Material.WATER);
/* 155 */         for (int k = 0; k <= 2; k++) {
/* 156 */           l2.setZ(l1.getZ() + k);
/*     */           
/* 158 */           p.getWorld().getBlockAt(l2).setType(Material.WATER);
/*     */         }
/*     */       }
/* 161 */       for (int i = 0; i <= 2; i++) {
/* 162 */         l2.setX(l1.getX() + i);
/*     */         
/* 164 */         p.getWorld().getBlockAt(l2).setType(Material.WATER);
/* 165 */         for (int k = 0; k <= 2; k++) {
/* 166 */           l2.setZ(l1.getZ() - k);
/*     */           
/* 168 */           p.getWorld().getBlockAt(l2).setType(Material.WATER);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Poseidon.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */