/*    */ package Physical.Fighters.AbilityList;
/*    */ 
/*    */ import Physical.Fighters.MainModule.AbilityBase;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Rank;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Type;
/*    */ import Physical.Fighters.MainModule.EventManager;
/*    */ import Physical.Fighters.MinerModule.ACC;
/*    */ import java.util.Collection;
/*    */ import java.util.Timer;
/*    */ import java.util.TimerTask;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.GameMode;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.World;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.player.PlayerInteractEvent;
/*    */ 
/*    */ public class Boom extends AbilityBase
/*    */ {
/*    */   public Boom()
/*    */   {
/* 23 */     InitAbility("붐포인트", AbilityBase.Type.Active_Immediately, AbilityBase.Rank.S, new String[] {
/* 24 */       "철괴 왼쪽클릭시 20초간 자신의 주변에 있는적을을 폭발시킵니다." });
/* 25 */     InitAbility(60, 0, true);
/* 26 */     RegisterLeftClickEvent();
/*    */   }
/*    */   
/*    */   public int A_Condition(Event event, int CustomData)
/*    */   {
/* 31 */     PlayerInteractEvent Event = (PlayerInteractEvent)event;
/* 32 */     if ((!EventManager.DamageGuard) && 
/* 33 */       (PlayerCheck(Event.getPlayer())) && (ItemCheck(ACC.DefaultItem))) {
/* 34 */       return 0;
/*    */     }
/* 36 */     return -1;
/*    */   }
/*    */   
/*    */   public void A_Effect(Event event, int CustomData)
/*    */   {
/* 41 */     PlayerInteractEvent Event = (PlayerInteractEvent)event;
/* 42 */     Timer timer = new Timer();
/* 43 */     timer.schedule(new Pauck(Event.getPlayer().getName()), 500L, 1500L);
/*    */   }
/*    */   
/*    */   class Pauck extends TimerTask
/*    */   {
/* 48 */     private int num = 0;
/* 49 */     private String name = null;
/*    */     
/* 51 */     public Pauck(String name1) { this.name = name1; }
/*    */     
/*    */ 
/*    */     public void run()
/*    */     {
/* 56 */       Player[] p1 = Bukkit.getOnlinePlayers();
/* 57 */       Player p = Bukkit.getPlayer(this.name);
/* 58 */       if (p != null) {
/* 59 */         for (int i = 0; i < (Bukkit.getOnlinePlayers()).length; i++)
/* 60 */           if ((p1[i] != p) && (p1[i].getGameMode() != GameMode.CREATIVE)) {
/* 61 */             Location lo = p1[i].getLocation();
/* 62 */             if ((p.getLocation().distance(p1[i].getLocation()) <= 10.0D) && (lo.getY() != 0.0D)) {
/* 63 */               Location loc2 = p1[i].getLocation();
/* 64 */               p1[i].getWorld().createExplosion(loc2, 0.3F);
/*    */             }
/*    */           }
/*    */       }
/* 68 */       if (this.num > 16) cancel();
/* 69 */       this.num += 1;
/*    */     }
/*    */   }
/*    */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Boom.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */