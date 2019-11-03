/*    */ package Physical.Fighters.AbilityList;
/*    */ 
/*    */ import Physical.Fighters.MainModule.AbilityBase;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Rank;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Type;
/*    */ import Physical.Fighters.MainModule.EventManager;
/*    */ import Physical.Fighters.MajorModule.AbilityList;
/*    */ import Physical.Fighters.MinerModule.EventData;
/*    */ import Physical.Fighters.PhysicalFighters;
/*    */ import java.util.ArrayList;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.entity.EntityDeathEvent;
/*    */ 
/*    */ public class Mirroring extends AbilityBase
/*    */ {
/*    */   public Mirroring()
/*    */   {
/* 22 */     if ((!PhysicalFighters.Toner) && 
/* 23 */       (!PhysicalFighters.Specialability)) {
/* 24 */       InitAbility("미러링", AbilityBase.Type.Passive_Manual, AbilityBase.Rank.SSS, new String[] {
/* 25 */         "당신을 죽인 사람을 함께 저승으로 끌고갑니다.", "자신이 죽을경우 죽인 사람 역시 죽게됩니다.", 
/* 26 */         "데스노트는 이 능력에 죽지 않습니다." });
/* 27 */       InitAbility(0, 0, true);
/* 28 */       EventManager.onEntityDeath.add(new EventData(this));
/*    */     }
/*    */   }
/*    */   
/*    */   public int A_Condition(Event event, int CustomData)
/*    */   {
/* 34 */     EntityDeathEvent Event = (EntityDeathEvent)event;
/* 35 */     if (((Event.getEntity().getKiller() instanceof Player)) && 
/* 36 */       (PlayerCheck(Event.getEntity())))
/* 37 */       return 0;
/* 38 */     return -1;
/*    */   }
/*    */   
/*    */   public void A_Effect(Event event, int CustomData)
/*    */   {
/* 43 */     EntityDeathEvent Event = (EntityDeathEvent)event;
/* 44 */     Player p = (Player)Event.getEntity();
/* 45 */     Bukkit.broadcastMessage(String.format(ChatColor.RED + 
/* 46 */       "%s님의 미러링 능력이 발동되었습니다.", new Object[] {
/* 47 */       p.getName() }));
/* 48 */     if (AbilityList.assimilation.GetPlayer() == p.getKiller()) {
/* 49 */       AbilityList.assimilation.A_Effect(Event, 1);
/* 50 */       Bukkit.broadcastMessage(ChatColor.GREEN + "미러링 능력이 무력화 되었습니다.");
/* 51 */       return;
/*    */     }
/*    */     
/* 54 */     if ((AbilityList.aegis.GetPlayer() == p.getKiller()) && 
/* 55 */       (AbilityList.aegis.GetDurationState())) {
/* 56 */       Bukkit.broadcastMessage(ChatColor.GREEN + "미러링 능력이 무력화 되었습니다.");
/* 57 */       return;
/*    */     }
/*    */     
/* 60 */     Bukkit.broadcastMessage(String.format(ChatColor.RED + 
/* 61 */       "%s님의 능력에 의해 %s님이 죽었습니다.", new Object[] {
/* 62 */       p.getName(), p.getKiller()
/* 63 */       .getName() }));
/* 64 */     p.getKiller().damage(5000);
/*    */   }
/*    */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Mirroring.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */