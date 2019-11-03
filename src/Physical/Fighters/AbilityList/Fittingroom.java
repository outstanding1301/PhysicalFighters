/*    */ package Physical.Fighters.AbilityList;
/*    */ 
/*    */ import Physical.Fighters.MainModule.AbilityBase;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Rank;
/*    */ import Physical.Fighters.MainModule.EventManager;
/*    */ import java.util.Collection;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.GameMode;
/*    */ import org.bukkit.World;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.player.PlayerInteractEvent;
/*    */ 
/*    */ public class Fittingroom extends AbilityBase
/*    */ {
/*    */   public Fittingroom()
/*    */   {
/* 18 */     if (!Physical.Fighters.PhysicalFighters.Toner) {
/* 19 */       InitAbility(
/* 20 */         "탈의실", 
/* 21 */         Physical.Fighters.MainModule.AbilityBase.Type.Active_Immediately, 
/* 22 */         AbilityBase.Rank.SSS, 
/* 23 */         new String[] { "능력 사용시 자기 자신을 제외한 모든 플레이어가 손에 쥐고있는 아이템을 떨어뜨립니다." });
/* 24 */       InitAbility(160, 0, true);
/* 25 */       RegisterLeftClickEvent();
/*    */     }
/*    */   }
/*    */   
/*    */   public int A_Condition(Event event, int CustomData) {
/* 30 */     if (!EventManager.DamageGuard) {
/* 31 */       PlayerInteractEvent Event = (PlayerInteractEvent)event;
/* 32 */       if ((PlayerCheck(Event.getPlayer())) && 
/* 33 */         (ItemCheck(Physical.Fighters.MinerModule.ACC.DefaultItem)))
/* 34 */         return 0;
/*    */     }
/* 36 */     return -1;
/*    */   }
/*    */   
/*    */   public void A_Effect(Event event, int CustomData) {
/* 40 */     PlayerInteractEvent Event = (PlayerInteractEvent)event;
/* 41 */     Player[] t = Bukkit.getOnlinePlayers();
/* 42 */     Player p = Event.getPlayer();
/* 43 */     World w = p.getWorld();
/* 44 */     for (int i = 0; i < (Bukkit.getOnlinePlayers()).length; i++) {
/* 45 */       if ((t[i].getGameMode() != GameMode.CREATIVE) && (t[i] != p) && (t[i].getItemInHand().getTypeId() != 0)) {
/* 46 */         w.dropItem(t[i].getLocation(), t[i].getItemInHand());
/* 47 */         t[i].getInventory().remove(t[i].getItemInHand());
/*    */       }
/*    */     }
/* 50 */     Bukkit.broadcastMessage(org.bukkit.ChatColor.AQUA + p.getName() + 
/* 51 */       "님이 능력을 사용해 모든 플레이어의 무장을 해체시켰습니다.");
/*    */   }
/*    */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Fittingroom.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */