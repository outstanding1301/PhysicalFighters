/*    */ package Physical.Fighters.AbilityList;
import Physical.Fighters.MainModule.EventManager;
/*    */ 
/*    */ import Physical.Fighters.MinerModule.ACC;
/*    */ import java.util.Collection;
/*    */ import java.util.LinkedList;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.player.PlayerInteractEvent;
/*    */ import org.bukkit.util.Vector;
/*    */ 
/*    */ public class Magnet extends Physical.Fighters.MainModule.AbilityBase
/*    */ {
/*    */   public Magnet()
/*    */   {
/* 17 */     InitAbility("자석", Physical.Fighters.MainModule.AbilityBase.Type.Active_Immediately, Physical.Fighters.MainModule.AbilityBase.Rank.S, new String[] {
/* 18 */       "철괴를 들고 왼쪽클릭시, 20칸안에 있는 모든 적을 자신의 방향으로,", 
/* 19 */       "철괴를 들고 오른쪽클릭시, 20칸안에 있는 모든 적을 자신의 반대방향으로 날려버립니다." });
/* 20 */     InitAbility(40, 0, true);
/* 21 */     RegisterLeftClickEvent();
/* 22 */     RegisterRightClickEvent();
/*    */   }
/*    */   
/*    */   public int A_Condition(Event event, int CustomData)
/*    */   {
/* 27 */     PlayerInteractEvent Event = (PlayerInteractEvent)event;
/* 28 */     switch (CustomData) {
/*    */     case 0: 
/* 30 */       if ((PlayerCheck(Event.getPlayer())) && (ItemCheck(ACC.DefaultItem)) && !EventManager.DamageGuard) {
/* 31 */         return 0;
/*    */       }
/*    */       break;
/*    */     case 1: 
/* 35 */       if ((PlayerCheck(Event.getPlayer())) && (ItemCheck(ACC.DefaultItem)) && !EventManager.DamageGuard) {
/* 36 */         return 1;
/*    */       }
/*    */       break;
/*    */     }
/* 40 */     return -1;
/*    */   }
/*    */   
/*    */   public void A_Effect(Event event, int CustomData)
/*    */   {
/* 45 */     PlayerInteractEvent Event = (PlayerInteractEvent)event;
/* 46 */     Player p = Event.getPlayer();
/* 47 */     LinkedList<Player> ts = new LinkedList();
/* 48 */     for (int i = 0; i < (Bukkit.getOnlinePlayers()).length; i++)
/*    */     {
/* 50 */       if ((p.getLocation().distance((Bukkit.getOnlinePlayers())[i].getLocation()) < 20.0D) && ((Bukkit.getOnlinePlayers())[i] != p) && ((Bukkit.getOnlinePlayers())[i].getGameMode() != org.bukkit.GameMode.CREATIVE)) {
/* 51 */         ts.add((Bukkit.getOnlinePlayers())[i]);
/*    */       }
/*    */     }
/* 54 */     if (!ts.isEmpty()) {
/* 55 */       Location l = p.getLocation();
/* 56 */       l.setY(l.getY() + 2.0D);
/* 57 */       Location l2 = p.getLocation();
/* 58 */       l2.setY(l.getY() - 3.0D);
/* 59 */       switch (CustomData) {
/*    */       case 0: 
/* 61 */         for (int i = 0; i < ts.size(); i++) {
/* 62 */           Player t = (Player)ts.get(i);
/* 63 */           t.setVelocity(t.getVelocity().add(
/* 64 */             t.getLocation().toVector()
/* 65 */             .subtract(
/* 66 */             l.toVector())
/* 67 */             .normalize().multiply(-3)));
/*    */         }
/* 69 */         ts.clear();
/* 70 */         break;
/*    */       case 1: 
/* 72 */         for (int i = 0; i < ts.size(); i++) {
/* 73 */           Player t = (Player)ts.get(i);
/* 74 */           t.setVelocity(t.getVelocity().add(
/* 75 */             t.getLocation().toVector()
/* 76 */             .subtract(
/* 77 */             l2.toVector())
/* 78 */             .normalize().multiply(3)));
/*    */         }
/* 80 */         ts.clear();
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Magnet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */