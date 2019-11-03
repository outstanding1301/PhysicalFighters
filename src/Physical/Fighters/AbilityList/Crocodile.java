/*     */ package Physical.Fighters.AbilityList;
/*     */ 
/*     */ import Physical.Fighters.MainModule.AbilityBase;
import Physical.Fighters.MainModule.EventManager;
/*     */ import Physical.Fighters.MainModule.AbilityBase.Rank;
/*     */ import Physical.Fighters.MainModule.AbilityBase.Type;
/*     */ import Physical.Fighters.MinerModule.ACC;
/*     */ import Physical.Fighters.PhysicalFighters;
/*     */ import java.util.Collection;
/*     */ import java.util.Timer;
/*     */ import java.util.TimerTask;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.GameMode;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.player.PlayerInteractEvent;
/*     */ import org.bukkit.potion.PotionEffect;
/*     */ import org.bukkit.potion.PotionEffectType;
/*     */ import org.bukkit.util.Vector;
/*     */ 
/*     */ public class Crocodile extends AbilityBase
/*     */ {
/*     */   public Crocodile()
/*     */   {
/*  29 */     if (PhysicalFighters.SRankUsed) {
/*  30 */       InitAbility("크로커다일", AbilityBase.Type.Active_Immediately, AbilityBase.Rank.S, new String[] {
/*  31 */         "철괴 왼쪽클릭시 자신의 주변의 있는 블력을 모래로 바꿔버립니다.", 
/*  32 */         "철괴 오른쪽클릭시 모래 주변에 있는 적에게 일정시간동안 모래바람을 일으킵니다." });
/*  33 */       InitAbility(30, 0, true);
/*  34 */       RegisterLeftClickEvent();
/*  35 */       RegisterRightClickEvent();
/*     */     }
/*     */   }
/*     */   
/*     */   public int A_Condition(Event event, int CustomData)
/*     */   {
/*  41 */     switch (CustomData) {
/*     */     case 0: 
/*  43 */       PlayerInteractEvent Event = (PlayerInteractEvent)event;
/*  44 */       if ((PlayerCheck(Event.getPlayer())) && (ItemCheck(ACC.DefaultItem)) && !EventManager.DamageGuard) {
/*  45 */         return 0;
/*     */       }
/*     */     case 1: 
/*  48 */       PlayerInteractEvent Event1 = (PlayerInteractEvent)event;
/*  49 */       if ((PlayerCheck(Event1.getPlayer())) && (ItemCheck(ACC.DefaultItem)) && !EventManager.DamageGuard)
/*  50 */         return 1;
/*     */       break;
/*     */     }
/*  53 */     return -1;
/*     */   }
/*     */   
/*     */   public void A_Effect(Event event, int CustomData)
/*     */   {
/*  58 */     switch (CustomData) {
/*     */     case 0: 
/*  60 */       PlayerInteractEvent Event = (PlayerInteractEvent)event;
/*  61 */       Player p = Event.getPlayer();
/*  62 */       World w = Event.getPlayer().getWorld();
/*  63 */       Location loc = p.getLocation();
/*  64 */       for (int i = -5; i < 6; i++) {
/*  65 */         for (int j = -5; j < 6; j++) {
/*  66 */           for (int k = -1; k < 6; k++) {
/*  67 */             if (w.getBlockAt((int)loc.getX() + i, (int)loc.getY() + k, (int)loc.getZ() + j).getType() != Material.AIR)
/*  68 */               w.getBlockAt((int)loc.getX() + i, (int)loc.getY() + k, (int)loc.getZ() + j).setType(Material.SAND);
/*     */           }
/*     */         }
/*     */       }
/*  72 */       break;
/*     */     case 1: 
/*  74 */       PlayerInteractEvent Event1 = (PlayerInteractEvent)event;
/*  75 */       Timer timer = new Timer();
/*  76 */       timer.schedule(new Pauck(Event1.getPlayer().getName()), 500L, 1500L);
/*     */     }
/*     */   }
/*     */   
/*     */   class Pauck extends TimerTask
/*     */   {
/*  82 */     private int num = 0;
/*  83 */     private String name = null;
/*     */     
/*  85 */     public Pauck(String name1) { this.name = name1; }
/*     */     
/*     */ 
/*     */     public void run()
/*     */     {
/*  90 */       Player[] p1 = Bukkit.getOnlinePlayers();
/*  91 */       Player p = Bukkit.getPlayer(this.name);
/*  92 */       if (p != null) {
/*  93 */         for (int i = 0; i < (Bukkit.getOnlinePlayers()).length; i++) {
/*  94 */           if ((p1[i] != p) && (p1[i].getGameMode() != GameMode.CREATIVE)) {
/*  95 */             Location loc = p1[i].getLocation();
/*  96 */             World w = p.getWorld();
/*  97 */             for (int i1 = 0; i1 < 2; i1++) {
/*  98 */               for (int j = -1; j < 2; j++)
/*  99 */                 for (int k = -1; k < 2; k++)
/* 100 */                   if (w.getBlockAt((int)loc.getX() + i1, (int)loc.getY() + k, (int)loc.getZ() + j).getType() == Material.SAND) {
/* 101 */                     p1[i].damage(2, p);
/* 102 */                     p1[i].setVelocity(p1[i].getVelocity().add(
/* 103 */                       p.getLocation().toVector()
/* 104 */                       .subtract(p1[i].getLocation().toVector()).normalize()
/* 105 */                       .multiply(-1)));
/* 106 */                     p1[i].addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 30, 0), true);
/*     */                   }
/*     */             }
/*     */           }
/*     */         }
/* 111 */         if (this.num > 10) { cancel();p.sendMessage(ChatColor.GREEN + "지속시간이 끝났습니다."); }
/* 112 */         this.num += 1;
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Crocodile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */