/*    */ package Physical.Fighters.AbilityList;
/*    */ 
/*    */ import Physical.Fighters.MainModule.AbilityBase;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Rank;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Type;
/*    */ import Physical.Fighters.MainModule.EventManager;
/*    */ import Physical.Fighters.PhysicalFighters;
/*    */ import java.util.Collection;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.World;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.player.PlayerInteractEvent;
/*    */ 
/*    */ public class Enel extends AbilityBase
/*    */ {
/*    */   public Enel()
/*    */   {
/* 21 */     if (PhysicalFighters.SRankUsed) {
/* 22 */       InitAbility("갓 에넬", AbilityBase.Type.Active_Immediately, AbilityBase.Rank.S, new String[] {
/* 23 */         "바라보는 방향으로 번개를 연속발사합니다." });
/* 24 */       InitAbility(30, 0, true);
/* 25 */       RegisterLeftClickEvent();
/*    */     }
/*    */   }
/*    */   
/*    */   public int A_Condition(Event event, int CustomData)
/*    */   {
/* 31 */     PlayerInteractEvent Event = (PlayerInteractEvent)event;
/* 32 */     if ((!EventManager.DamageGuard) && 
/* 33 */       (PlayerCheck(Event.getPlayer())) && (ItemCheck(Physical.Fighters.MinerModule.ACC.DefaultItem))) {
/* 34 */       return 0;
/*    */     }
/* 36 */     return -1;
/*    */   }
/*    */   
/*    */   public void A_Effect(Event event, int CustomData)
/*    */   {
/* 41 */     PlayerInteractEvent Event = (PlayerInteractEvent)event;
/* 42 */     Location l = Event.getPlayer().getLocation();
/* 43 */     Location l2 = Event.getPlayer().getLocation();
/* 44 */     double degrees = Math.toRadians(-(l.getYaw() % 360.0F));
/*    */     
/* 46 */     for (int i = 1; i < 5; i++) {
/* 47 */       l2.setX(l.getX() + 1 * i + 4.0D * Math.sin(degrees));
/* 48 */       l2.setZ(l.getZ() + 1 * i + 4.0D * Math.cos(degrees));
/* 49 */       l2.getWorld().strikeLightning(l2);
/* 50 */       Player[] List = Bukkit.getOnlinePlayers();
/* 51 */       Player[] arrayOfPlayer1; int j = (arrayOfPlayer1 = List).length; for (int k = 0; k < j; k++) { Player pp = arrayOfPlayer1[k];
/* 52 */         if (pp != GetPlayer()) {
/* 53 */           Location loc = pp.getLocation();
/*    */           
/*    */ 
/* 56 */           if ((l2.getWorld().getBlockAt(l2).getLocation().distance(loc) <= 3.0D) && 
/* 57 */             (!EventManager.DamageGuard)) {
/* 58 */             pp.damage(15);
/*    */           }
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Enel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */