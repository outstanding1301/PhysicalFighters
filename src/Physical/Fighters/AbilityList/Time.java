/*    */ package Physical.Fighters.AbilityList;
import Physical.Fighters.MainModule.AbilityBase;
/*    */ 
/*    */ import Physical.Fighters.MainModule.AbilityBase.Rank;
/*    */ import Physical.Fighters.MainModule.EventManager;
/*    */ import Physical.Fighters.MinerModule.EventData;
/*    */ import java.util.ArrayList;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.player.PlayerInteractEvent;
/*    */ import org.bukkit.event.player.PlayerMoveEvent;
/*    */ 
/*    */ public class Time extends AbilityBase
/*    */ {
/*    */   public Time()
/*    */   {
/* 18 */     if (!Physical.Fighters.PhysicalFighters.Specialability) {
/* 19 */       InitAbility("타임", Physical.Fighters.MainModule.AbilityBase.Type.Active_Continue, AbilityBase.Rank.A, new String[] {
/* 20 */         "자신을 제외한 모든 능력자의 이동을 5초동안", "차단합니다. 단, 직접적인 이동만 불가능합니다.", 
/* 21 */         "능력이 없는 사람도 다 멈춥니다." });
/* 22 */       InitAbility(40, 5, true);
/* 23 */       RegisterLeftClickEvent();
/* 24 */       EventManager.onPlayerMoveEvent.add(new EventData(this));
/*    */     }
/*    */   }
/*    */   
/*    */   public int A_Condition(Event event, int CustomData)
/*    */   {
/* 30 */     PlayerInteractEvent Event = (PlayerInteractEvent)event;
/* 31 */     if ((PlayerCheck(Event.getPlayer())) && (ItemCheck(Physical.Fighters.MinerModule.ACC.DefaultItem)) && !EventManager.DamageGuard) {
/* 32 */       return 0;
/*    */     }
/* 34 */     return -1;
/*    */   }
/*    */   
/*    */   public void A_Effect(Event event, int CustomData)
/*    */   {
/* 39 */     PlayerMoveEvent Event = (PlayerMoveEvent)event;
/* 40 */     if (!PlayerCheck(Event.getPlayer())) {
/* 41 */       Event.setCancelled(true);
/*    */     }
/*    */   }
/*    */   
/*    */   public void A_DurationStart() {
/* 46 */     Bukkit.broadcastMessage(String.format("%s" + ChatColor.RED + 
/* 47 */       "님이 Time 능력을 사용했습니다.", new Object[] {
/* 48 */       GetPlayer().getName() }));
/*    */   }
/*    */   
/*    */   public void A_DurationEnd()
/*    */   {
/* 53 */     Bukkit.broadcastMessage(String.format(ChatColor.GREEN + 
/* 54 */       "Time 능력이 해제되었습니다.", new Object[0]));
/*    */   }
/*    */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Time.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */