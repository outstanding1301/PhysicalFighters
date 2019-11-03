/*    */ package Physical.Fighters.AbilityList;
/*    */ 
/*    */ import Physical.Fighters.MainModule.AbilityBase;
import Physical.Fighters.MainModule.EventManager;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Rank;
/*    */ import java.util.Collection;

import org.bukkit.Bukkit;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.World;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.player.PlayerInteractEvent;
/*    */ 
/*    */ public class ExplosionGlove extends AbilityBase
/*    */ {
/*    */   public ExplosionGlove()
/*    */   {
/* 16 */     InitAbility("폭파장갑", Physical.Fighters.MainModule.AbilityBase.Type.Active_Immediately, AbilityBase.Rank.A, new String[] { "능력 사용시 주변의 플레이어에게 폭발을 일으키며", "공중으로 띄웁니다." });
/* 17 */     InitAbility(50, 0, true);
/* 18 */     RegisterLeftClickEvent();
/*    */   }
/*    */   
/*    */   public int A_Condition(Event event, int CustomData) {
/* 22 */     if (CustomData == 0) {
/* 23 */       PlayerInteractEvent e = (PlayerInteractEvent)event;
/* 24 */       if ((PlayerCheck(e.getPlayer())) && (ItemCheck(Material.IRON_INGOT.getId())) && !EventManager.DamageGuard) {
/* 25 */         return 0;
/*    */       }
/*    */     }
/* 28 */     return -1;
/*    */   }
/*    */   
/*    */   public void A_Effect(Event event, int CustomData) {
/* 32 */     if (CustomData == 0) {
/* 33 */       PlayerInteractEvent e = (PlayerInteractEvent)event;
/* 34 */       Player[] pl = Bukkit.getOnlinePlayers();
/* 35 */       e.getPlayer().getWorld().createExplosion(e.getPlayer().getLocation(), 5.0F);
/* 36 */       for (Player p : pl) {
/* 37 */         if ((p != GetPlayer()) && 
/* 38 */           (p.getLocation().distance(GetPlayer().getLocation()) <= 15.0D)) {
/* 39 */           p.getWorld().createExplosion(p.getLocation(), 3.0F);
/* 40 */           p.damage(14, e.getPlayer());
/* 41 */           p.setVelocity(new org.bukkit.util.Vector(0.0D, 1.5D, 0.0D));
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\ExplosionGlove.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */