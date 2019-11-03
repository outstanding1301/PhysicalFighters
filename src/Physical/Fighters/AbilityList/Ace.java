/*    */ package Physical.Fighters.AbilityList;
/*    */ 
/*    */ import Physical.Fighters.MainModule.AbilityBase;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Rank;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Type;
/*    */ import Physical.Fighters.MainModule.EventManager;
/*    */ import java.util.Collection;
/*    */ import java.util.Timer;
/*    */ import java.util.TimerTask;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.GameMode;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.player.PlayerInteractEvent;
/*    */ 
/*    */ public class Ace extends AbilityBase
/*    */ {
/*    */   public Ace()
/*    */   {
/* 21 */     InitAbility("에이스", AbilityBase.Type.Active_Immediately, AbilityBase.Rank.S, new String[] {
/* 22 */       "철괴 왼쪽클릭시 20초간 자신의 주변에 있는 적들을 불태웁니다." });
/* 23 */     InitAbility(40, 0, true);
/* 24 */     RegisterLeftClickEvent();
/*    */   }
/*    */   
/*    */   public int A_Condition(Event event, int CustomData) {
/* 28 */     PlayerInteractEvent Event = (PlayerInteractEvent)event;
/* 29 */     if ((!EventManager.DamageGuard) && 
/* 30 */       (PlayerCheck(Event.getPlayer())) && (ItemCheck(Physical.Fighters.MinerModule.ACC.DefaultItem))) {
/* 31 */       return 0;
/*    */     }
/* 33 */     return -1;
/*    */   }
/*    */   
/*    */   public void A_Effect(Event event, int CustomData)
/*    */   {
/* 38 */     PlayerInteractEvent Event = (PlayerInteractEvent)event;
/* 39 */     Timer timer = new Timer();
/* 40 */     timer.schedule(new Pauck(Event.getPlayer().getName()), 500L, 1500L);
/*    */   }
/*    */   
/*    */   class Pauck extends TimerTask
/*    */   {
/* 45 */     private int num = 0;
/* 46 */     private String name = null;
/*    */     
/* 48 */     public Pauck(String name1) { this.name = name1; }
/*    */     
/*    */ 
/*    */     public void run()
/*    */     {
/* 53 */       Player[] p1 = Bukkit.getOnlinePlayers();
/* 54 */       Player p = Bukkit.getPlayer(this.name);
/* 55 */       if (p != null) {
/* 56 */         for (int i = 0; i < p1.length; i++) {
/* 57 */           if ((p1[i] != p) && (p1[i].getGameMode() != GameMode.CREATIVE)) {
/* 58 */             Location lo = p1[i].getLocation();
/* 59 */             if ((p.getLocation().distance(p1[i].getLocation()) <= 10.0D) && (lo.getY() != 0.0D))
/* 60 */               p1[i].setFireTicks(80);
/*    */           }
/*    */         }
/*    */       }
/* 64 */       if (this.num > 16) cancel();
/* 65 */       this.num += 1;
/*    */     }
/*    */   }
/*    */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Ace.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */