/*     */ package Physical.Fighters.AbilityList;
/*     */ 
/*     */ import Physical.Fighters.MainModule.AbilityBase;
/*     */ import Physical.Fighters.MainModule.AbilityBase.Rank;
/*     */ import Physical.Fighters.MainModule.AbilityBase.Type;
/*     */ import Physical.Fighters.MainModule.EventManager;
/*     */ import Physical.Fighters.MinerModule.ACC;
/*     */ import Physical.Fighters.MinerModule.EventData;
/*     */ import java.util.ArrayList;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Location;
import org.bukkit.entity.Damageable;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*     */ import org.bukkit.event.entity.EntityDamageEvent;
/*     */ import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
/*     */ import org.bukkit.event.player.PlayerInteractEvent;
/*     */ import org.bukkit.util.Vector;
/*     */ 
/*     */ public class CP9 extends AbilityBase
/*     */ {
/*     */   public CP9()
/*     */   {
/*  24 */     if (Physical.Fighters.PhysicalFighters.SRankUsed) {
/*  25 */       InitAbility(
/*  26 */         "CP9", 
/*  27 */         AbilityBase.Type.Active_Immediately, 
/*  28 */         AbilityBase.Rank.S, 
/*  29 */         new String[] {
/*  30 */         "철괴로 상대 타격시에 강한데미지를 줍니다.", 
/*  31 */         "철괴 오른클릭시 폭발과함께 바라보는 방향으로 빠르게 전진합니다. [점프와 사용하면 효율적]", 
/*  32 */         "*낙하데미지를 받지않습니다." });
/*  33 */       InitAbility(15, 0, true);
/*  34 */       EventManager.onEntityDamageByEntity.add(new EventData(this));
/*  35 */       RegisterRightClickEvent();
/*  36 */       EventManager.onEntityDamage.add(new EventData(this, 3));
/*     */     }
/*     */   }
/*     */   
/*     */   public int A_Condition(Event event, int CustomData) {
/*  41 */     switch (CustomData) {
/*     */     case 0: 
/*  43 */       EntityDamageByEntityEvent Event1 = (EntityDamageByEntityEvent)event;
/*  44 */       if (((Event1.getEntity() instanceof Player)) && 
/*  45 */         (PlayerCheck(Event1.getDamager())) && 
/*  46 */         (ItemCheck(ACC.DefaultItem)) && 
/*  47 */         (!EventManager.DamageGuard)) {
/*  48 */         return 1;
/*     */       }
/*     */       
/*     */       break;
/*     */     case 1: 
/*  53 */       PlayerInteractEvent Event2 = (PlayerInteractEvent)event;
/*  54 */       if ((PlayerCheck(Event2.getPlayer())) && 
/*  55 */         (ItemCheck(ACC.DefaultItem))) {
/*  56 */         return 2;
/*     */       }
/*     */       
/*     */       break;
/*     */     }
/*     */     
/*  62 */     if (CustomData == 3) {
/*  63 */       EntityDamageEvent Event2 = (EntityDamageEvent)event;
/*  64 */       if ((PlayerCheck(Event2.getEntity())) && 
/*  65 */         (Event2.getCause() == EntityDamageEvent.DamageCause.FALL)) {
/*  66 */         Event2.setCancelled(true);
/*  67 */         GetPlayer().sendMessage(
/*  68 */           ChatColor.GREEN + "사뿐하게 떨어져 데미지를 받지 않았습니다.");
/*     */       }
/*     */     }
/*     */     
/*  72 */     return -1;
/*     */   }
/*     */   
/*     */   public void A_Effect(Event event, int CustomData) {
/*  76 */     switch (CustomData) {
/*     */     case 1: 
/*  78 */       EntityDamageByEntityEvent Event1 = (EntityDamageByEntityEvent)event;
/*  79 */       Player p1 = (Player)Event1.getEntity();
/*  80 */       p1.setHealth(((Damageable)p1).getHealth() - 6);
/*  81 */       GetPlayer().sendMessage(
/*  82 */         String.format(ChatColor.RED + "%s님에게 지건을 사용했습니다.", 
/*  83 */         new Object[] { p1.getName() }));
/*  84 */       break;
/*     */     case 2: 
/*  86 */       PlayerInteractEvent Event = (PlayerInteractEvent)event;
/*  87 */       Location loca = Event.getPlayer().getLocation();
/*  88 */       Location loca2 = Event.getPlayer().getLocation();
/*  89 */       Player p = Event.getPlayer();
/*  90 */       double degrees = Math.toRadians(-(Event.getPlayer().getLocation()
/*  91 */         .getYaw() % 360.0F));
/*  92 */       double ydeg = Math.toRadians(-(Event.getPlayer().getLocation()
/*  93 */         .getPitch() % 360.0F));
/*  94 */       loca.setX(Event.getPlayer().getLocation().getX() + -1.5D * (
/*  95 */         Math.sin(degrees) * Math.cos(ydeg)));
/*  96 */       loca.setY(Event.getPlayer().getLocation().getY() + -1.5D * 
/*  97 */         Math.sin(ydeg));
/*  98 */       loca.setZ(Event.getPlayer().getLocation().getZ() + -1.5D * (
/*  99 */         Math.cos(degrees) * Math.cos(ydeg)));
/* 100 */       Event.getPlayer().getWorld().createExplosion(loca, 0.0F);
/* 101 */       loca2.setX(Event.getPlayer().getLocation().getX() + 5.0D * (
/* 102 */         Math.sin(degrees) * Math.cos(ydeg)));
/* 103 */       loca2.setY(Event.getPlayer().getLocation().getY() + 5.0D * 
/* 104 */         Math.sin(ydeg));
/* 105 */       loca2.setZ(Event.getPlayer().getLocation().getZ() + 5.0D * (
/* 106 */         Math.cos(degrees) * Math.cos(ydeg)));
/* 107 */       p.setVelocity(((Damageable)p).getVelocity().add(
/* 108 */         loca2.toVector()
/* 109 */         .subtract(
/* 110 */         Event.getPlayer().getLocation().toVector())
/* 111 */         .normalize().multiply(5)));
/*     */     }
/*     */   }
/*     */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\CP9.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */