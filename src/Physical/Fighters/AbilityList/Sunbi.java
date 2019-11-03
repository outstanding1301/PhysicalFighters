/*    */ package Physical.Fighters.AbilityList;
/*    */ 
/*    */ import Physical.Fighters.MainModule.AbilityBase;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Rank;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Type;
/*    */ import Physical.Fighters.MainModule.EventManager;
/*    */ import Physical.Fighters.MinerModule.EventData;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
/*    */ import java.util.TimerTask;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.GameMode;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.World;
import org.bukkit.entity.Damageable;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.player.PlayerToggleSneakEvent;
/*    */ import org.bukkit.potion.PotionEffect;
/*    */ import org.bukkit.potion.PotionEffectType;
/*    */ 
/*    */ 
/*    */ public class Sunbi
/*    */   extends AbilityBase
/*    */ {
/*    */   public Sunbi()
/*    */   {
/* 28 */     InitAbility("나그네", AbilityBase.Type.Active_Immediately, AbilityBase.Rank.S, new String[] {
/* 29 */       "쉬프트를 누르고 앉으면 지나가던 나그네가 엣헴! 하며 주위의 사람들이 나그네에게 체력을 바칩니다. ", 
/* 30 */       "이 떄, 체력을 빼앗긴 플레이어들은 어지러움증과 고통을 느끼게됩니다." });
/* 31 */     InitAbility(30, 0, true);
/* 32 */     EventManager.onPlayerToggleSneakEvent.add(new EventData(this));
/*    */   }
/*    */   
/*    */   public int A_Condition(Event event, int CustomData)
/*    */   {
/* 37 */     PlayerToggleSneakEvent Event = (PlayerToggleSneakEvent)event;
/* 38 */     if ((!EventManager.DamageGuard) && 
/* 39 */       (PlayerCheck(Event.getPlayer())) && (Event.isSneaking())) {
/* 40 */       return 0;
/*    */     }
/* 42 */     return -1;
/*    */   }
/*    */   
/*    */   public void A_Effect(Event event, int CustomData)
/*    */   {
/* 47 */     PlayerToggleSneakEvent e = (PlayerToggleSneakEvent)event;
/* 48 */     Player[] arrayOfPlayer; int j = (arrayOfPlayer = Bukkit.getOnlinePlayers()).length; for (int i = 0; i < j; i++) { Player p = arrayOfPlayer[i];
/* 49 */       if ((p != e.getPlayer()) && 
/* 50 */         (e.getPlayer().getLocation().distance(p.getLocation()) < 10.0D)) {
/* 51 */         if (((Damageable)p).getHealth() > 2.0D) {
/* 52 */           p.setHealth(((Damageable)p).getHealth() - 2);
/*    */         } else
/* 54 */           p.damage(1000, e.getPlayer());
/* 55 */         if (((Damageable)e.getPlayer()).getHealth() <= 18) {
/* 56 */           e.getPlayer().setHealth(((Damageable)e.getPlayer()).getHealth() + 2);
/*    */         } else
/* 58 */           e.getPlayer().setHealth(20);
/* 59 */         p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 200, 3), true);
/* 60 */         p.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 200, 3), true);
/* 61 */         p.sendMessage(ChatColor.WHITE + "<" + e.getPlayer().getDisplayName() + ">엣헴!");
/*    */       }
/*    */     }
/*    */     
/* 65 */     e.getPlayer().sendMessage(ChatColor.WHITE + "<" + e.getPlayer().getDisplayName() + ">엣헴!");
/*    */   }
/*    */   
/*    */   class Pauck extends TimerTask
/*    */   {
/* 70 */     private int num = 0;
/* 71 */     private String name = null;
/*    */     
/* 73 */     public Pauck(String name1) { this.name = name1; }
/*    */     
/*    */ 
/*    */     public void run()
/*    */     {
/* 78 */       Player[] p1 = Bukkit.getOnlinePlayers();
/* 79 */       Player p = Bukkit.getPlayer(this.name);
/* 80 */       if (p != null) {
/* 81 */         for (int i = 0; i < (Bukkit.getOnlinePlayers()).length; i++)
/* 82 */           if ((p1[i] != p) && (p1[i].getGameMode() != GameMode.CREATIVE)) {
/* 83 */             Location lo = p1[i].getLocation();
/* 84 */             if ((p.getLocation().distance(p1[i].getLocation()) <= 10.0D) && (lo.getY() != 0.0D)) {
/* 85 */               Location loc2 = p1[i].getLocation();
/* 86 */               p1[i].getWorld().createExplosion(loc2, 0.3F);
/*    */             }
/*    */           }
/*    */       }
/* 90 */       if (this.num > 16) cancel();
/* 91 */       this.num += 1;
/*    */     }
/*    */   }
/*    */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Sunbi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */