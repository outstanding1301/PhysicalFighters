/*    */ package Physical.Fighters.AbilityList;
/*    */ 
/*    */ import Physical.Fighters.MainModule.AbilityBase;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Rank;
/*    */ import Physical.Fighters.MainModule.EventManager;
/*    */ import java.util.Collection;
/*    */ import java.util.Timer;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.GameMode;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.player.PlayerInteractEvent;
/*    */ 
/*    */ public class Temari extends AbilityBase
/*    */ {
/*    */   public Temari()
/*    */   {
/* 19 */     InitAbility("테마리", Physical.Fighters.MainModule.AbilityBase.Type.Active_Immediately, AbilityBase.Rank.S, new String[] {
/* 20 */       "철괴 왼쪽클릭시 20초간 자신의 주변에 있는 적들을 공중으로 날려버립니다." });
/* 21 */     InitAbility(60, 0, true);
/* 22 */     RegisterLeftClickEvent();
/*    */   }
/*    */   
/*    */   public int A_Condition(Event event, int CustomData) {
/* 26 */     PlayerInteractEvent Event = (PlayerInteractEvent)event;
/* 27 */     if ((!EventManager.DamageGuard) && 
/* 28 */       (PlayerCheck(Event.getPlayer())) && (ItemCheck(Physical.Fighters.MinerModule.ACC.DefaultItem))) {
/* 29 */       return 0;
/*    */     }
/* 31 */     return -1;
/*    */   }
/*    */   
/*    */   public void A_Effect(Event event, int CustomData)
/*    */   {
/* 36 */     PlayerInteractEvent Event = (PlayerInteractEvent)event;
/* 37 */     Timer timer = new Timer();
/* 38 */     timer.schedule(new Pauck(Event.getPlayer().getName()), 500L, 1500L);
/*    */   }
/*    */   
/*    */   class Pauck extends java.util.TimerTask
/*    */   {
/* 43 */     private int num = 0;
/* 44 */     private String name = null;
/*    */     
/* 46 */     public Pauck(String name1) { this.name = name1; }
/*    */     
/*    */ 
/*    */     public void run()
/*    */     {
/* 51 */       Player[] p1 = Bukkit.getOnlinePlayers();
/* 52 */       Player p = Bukkit.getPlayer(this.name);
/* 53 */       if (p != null) {
/* 54 */         for (int i = 0; i < (Bukkit.getOnlinePlayers()).length; i++)
/* 55 */           if ((p1[i] != p) && (p1[i].getGameMode() != GameMode.CREATIVE)) {
/* 56 */             Location lo = p1[i].getLocation();
/* 57 */             if ((p.getLocation().distance(p1[i].getLocation()) <= 10.0D) && (lo.getY() != 0.0D)) {
/* 58 */               Location loc2 = p1[i].getLocation();
/* 59 */               loc2.setY(lo.getY() + 4.0D);
/* 60 */               Temari.goVelocity(p1[i], loc2, 1);
/*    */             }
/*    */           }
/*    */       }
/* 64 */       if (this.num > 16) cancel();
/* 65 */       this.num += 1;
/*    */     }
/*    */   }
/*    */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Temari.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */