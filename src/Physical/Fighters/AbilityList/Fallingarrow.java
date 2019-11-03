/*    */ package Physical.Fighters.AbilityList;
/*    */ 
/*    */ import Physical.Fighters.MainModule.AbilityBase;
import Physical.Fighters.MainModule.EventManager;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Rank;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Type;
/*    */ import Physical.Fighters.MinerModule.ACC;
/*    */ import Physical.Fighters.PhysicalFighters;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.World;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.entity.Arrow;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.player.PlayerInteractEvent;
/*    */ import org.bukkit.util.Vector;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Fallingarrow
/*    */   extends AbilityBase
/*    */ {
/*    */   public Fallingarrow()
/*    */   {
/* 24 */     if (!PhysicalFighters.Specialability) {
/* 25 */       InitAbility("낙하화살", AbilityBase.Type.Active_Immediately, AbilityBase.Rank.A, new String[] {
/* 26 */         "바라보는곳에 빠른속도로 화살 16발을 내리꽂습니다. [실내에서 사용이 안될 수 있습니다.]" });
/* 27 */       InitAbility(3, 0, true);
/* 28 */       RegisterLeftClickEvent();
/*    */     }
/*    */   }
/*    */   
/*    */   public int A_Condition(Event event, int CustomData)
/*    */   {
/* 34 */     PlayerInteractEvent Event = (PlayerInteractEvent)event;
/* 35 */     if ((PlayerCheck(Event.getPlayer())) && 
/* 36 */       (ItemCheck(ACC.DefaultItem)) && !EventManager.DamageGuard) {
/* 37 */       return 0;
/*    */     }
/* 39 */     return -1;
/*    */   }
/*    */   
/*    */   public void A_Effect(Event event, int CustomData)
/*    */   {
/* 44 */     PlayerInteractEvent Event = (PlayerInteractEvent)event;
/* 45 */     Player p = Event.getPlayer();
/* 46 */     Location l2 = p.getTargetBlock(null, 0).getLocation();
/* 47 */     Vector v = new Vector(0, -10, 0);
/* 48 */     l2.setY(256.0D);
/* 49 */     for (int i = -1; i <= 2; i++) {
/* 50 */       for (int j = -1; j <= 2; j++) {
/* 51 */         Location l = p.getTargetBlock(null, 0).getLocation();
/* 52 */         l.setX(l.getX() + 0.5D * i);
/* 53 */         if (l.getY() + 10.0D > 250.0D) {
/* 54 */           l.setY(250.0D);
/*    */         }
/*    */         else {
/* 57 */           l.setY(l.getY() + 10.0D);
/*    */         }
/* 59 */         l.setZ(l.getZ() + 0.5D * j);
/* 60 */         Arrow a = (Arrow)p.getWorld().spawn(l, Arrow.class);
/* 61 */         a.setVelocity(v);
/* 62 */         a.setShooter(p);
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Fallingarrow.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */