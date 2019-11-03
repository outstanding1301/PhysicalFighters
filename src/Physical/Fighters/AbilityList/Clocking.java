/*    */ package Physical.Fighters.AbilityList;
/*    */ 
/*    */ import Physical.Fighters.MainModule.AbilityBase;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Rank;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Type;
/*    */ import java.util.Collection;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.player.PlayerInteractEvent;
/*    */ 
/*    */ public class Clocking extends AbilityBase
/*    */ {
/*    */   public Clocking()
/*    */   {
/* 16 */     InitAbility("클로킹", AbilityBase.Type.Active_Continue, AbilityBase.Rank.A, new String[] {
/* 17 */       "능력 사용시 일정시간동안 다른 사람에게 보이지 않습니다.", 
/* 18 */       "클로킹 상태에서는 타인에게 공격 받지 않습니다." });
/* 19 */     InitAbility(35, 5, true);
/* 20 */     RegisterLeftClickEvent();
/*    */   }
/*    */   
/*    */   public int A_Condition(Event event, int CustomData)
/*    */   {
/* 25 */     PlayerInteractEvent Event = (PlayerInteractEvent)event;
/* 26 */     if ((PlayerCheck(Event.getPlayer())) && (ItemCheck(Physical.Fighters.MinerModule.ACC.DefaultItem)))
/* 27 */       return 0;
/* 28 */     return -1;
/*    */   }
/*    */   
/*    */   public void A_DurationStart()
/*    */   {
/* 33 */     Player[] List = Bukkit.getOnlinePlayers();
/* 34 */     Player[] arrayOfPlayer1; int j = (arrayOfPlayer1 = List).length; for (int i = 0; i < j; i++) { Player p = arrayOfPlayer1[i];
/* 35 */       p.hidePlayer(GetPlayer());
/*    */     }
/*    */   }
/*    */   
/*    */   public void A_FinalDurationEnd() {
/* 40 */     if (GetPlayer() != null) {
/* 41 */       Player[] List = Bukkit.getOnlinePlayers();
/* 42 */       if ((List != null) && (List.length != 0)) { Player[] arrayOfPlayer1;
/* 43 */         int j = (arrayOfPlayer1 = List).length; for (int i = 0; i < j; i++) { Player p = arrayOfPlayer1[i];
/* 44 */           p.showPlayer(GetPlayer());
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   public void A_Effect(Event event, int CustomData) {}
/*    */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Clocking.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */