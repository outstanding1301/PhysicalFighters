/*    */ package Physical.Fighters.AbilityList;
/*    */ 
/*    */ import Physical.Fighters.MainModule.AbilityBase;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Rank;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Type;
/*    */ import Physical.Fighters.MainModule.EventManager;
/*    */ import Physical.Fighters.MinerModule.ACC;
/*    */ import Physical.Fighters.MinerModule.EventData;
/*    */ import java.util.ArrayList;
/*    */ import org.bukkit.ChatColor;
import org.bukkit.entity.Damageable;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*    */ import org.bukkit.event.player.PlayerInteractEvent;
/*    */ 
/*    */ public class Medic extends AbilityBase
/*    */ {
/*    */   public Medic()
/*    */   {
/* 20 */     InitAbility("메딕", AbilityBase.Type.Active_Immediately, AbilityBase.Rank.B, new String[] {
/* 21 */       "철괴 왼클릭시 맞은 사람의 체력이 6 회복됩니다.", "철괴 오른클릭시 자신의 체력을 6 회복합니다.", 
/* 22 */       "두 기능은 쿨타임을 공유합니다." });
/* 23 */     InitAbility(5, 0, true);
/* 24 */     EventManager.onEntityDamageByEntity.add(new EventData(this));
/* 25 */     RegisterRightClickEvent();
/*    */   }
/*    */   
/*    */   public int A_Condition(Event event, int CustomData)
/*    */   {
/* 30 */     switch (CustomData) {
/*    */     case 0: 
/* 32 */       EntityDamageByEntityEvent Event1 = (EntityDamageByEntityEvent)event;
/* 33 */       if (((Event1.getEntity() instanceof Player)) && 
/* 34 */         (PlayerCheck(Event1.getDamager())) && 
/* 35 */         (ItemCheck(ACC.DefaultItem))) {
/* 36 */         return 0;
/*    */       }
/*    */       
/*    */       break;
/*    */     case 1: 
/* 41 */       PlayerInteractEvent Event2 = (PlayerInteractEvent)event;
/* 42 */       if ((PlayerCheck(Event2.getPlayer())) && 
/* 43 */         (ItemCheck(ACC.DefaultItem))) {
/* 44 */         return 1;
/*    */       }
/*    */       break;
/*    */     }
/*    */     
/* 49 */     return -1;
/*    */   }
/*    */   
/*    */   public void A_Effect(Event event, int CustomData)
/*    */   {
/* 54 */     switch (CustomData) {
/*    */     case 0: 
/* 56 */       EntityDamageByEntityEvent Event1 = (EntityDamageByEntityEvent)event;
/* 57 */       Player p1 = (Player)Event1.getEntity();
/* 58 */       if (((Damageable)p1).getHealth() <= 14.0D) {
/* 59 */         p1.setHealth(((Damageable)p1).getHealth() + 6);
/*    */       } else {
/* 61 */         p1.setHealth(20);
/*    */       }
/* 63 */       p1.sendMessage(String.format(ChatColor.GREEN + 
/* 64 */         "%s님의 메딕 능력으로 체력을 6 회복했습니다.", new Object[] { GetPlayer().getName() }));
/* 65 */       GetPlayer().sendMessage(
/* 66 */         String.format(ChatColor.GREEN + "%s님의 체력을 6 회복시켰습니다.", new Object[] {
/* 67 */         p1.getName() }));
/* 68 */       Event1.setCancelled(true);
/* 69 */       break;
/*    */     case 1: 
/* 71 */       PlayerInteractEvent Event2 = (PlayerInteractEvent)event;
/* 72 */       Player p2 = Event2.getPlayer();
/* 73 */       if (((Damageable)p2).getHealth() <= 14.0D) {
/* 74 */         p2.setHealth(((Damageable)p2).getHealth() + 6);
/*    */       } else
/* 76 */         p2.setHealth(20);
/* 77 */       p2.sendMessage(ChatColor.GREEN + "자신의 체력을 6 회복했습니다.");
/*    */     }
/*    */   }
/*    */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Medic.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */