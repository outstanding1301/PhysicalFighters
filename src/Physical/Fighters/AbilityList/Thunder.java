/*    */ package Physical.Fighters.AbilityList;
/*    */ 
/*    */ import Physical.Fighters.MainModule.AbilityBase;
import Physical.Fighters.MainModule.EventManager;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Rank;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Type;
/*    */ import Physical.Fighters.MinerModule.ACC;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.World;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.player.PlayerInteractEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Thunder
/*    */   extends AbilityBase
/*    */ {
/* 20 */   public static boolean ppon = false; 
/*    */   
/*    */   public Thunder() {
/* 23 */     InitAbility("썬더볼트", AbilityBase.Type.Active_Immediately, AbilityBase.Rank.S, new String[] {
/* 24 */       "철괴 좌클릭으로 능력을 사용합니다.", 
/* 25 */       "주변 5칸의 플레이어에게 데미지를 줍니다." });
/* 26 */     InitAbility(5, 0, true);
/* 27 */     RegisterLeftClickEvent();
/*    */   }
/*    */   
/*    */   public int A_Condition(Event event, int CustomData)
/*    */   {
/* 32 */     if (CustomData == 0) {
/* 33 */       PlayerInteractEvent e = (PlayerInteractEvent)event;
/* 34 */       if ((PlayerCheck(e.getPlayer())) && (ItemCheck(ACC.DefaultItem)) && !EventManager.DamageGuard) {
/* 35 */         return 0;
/*    */       }
/*    */     }
/* 38 */     return -1;
/*    */   }
/*    */   
/*    */   public void A_Effect(Event event, int CustomData) {
/* 42 */     PlayerInteractEvent e = (PlayerInteractEvent)event;
/* 43 */     for (Player p : Bukkit.getOnlinePlayers()) {
/* 44 */       if (p != e.getPlayer() && p.getLocation().distance(e.getPlayer().getLocation()) <= 5) {
/* 45 */         p.getWorld().strikeLightningEffect(p.getLocation());
/* 46 */         p.damage(10, e.getPlayer());
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Thunder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */