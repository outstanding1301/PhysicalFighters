/*    */ package Physical.Fighters.AbilityList;
/*    */ 
/*    */ import Physical.Fighters.MainModule.AbilityBase;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Rank;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Type;
/*    */ import Physical.Fighters.MainModule.EventManager;
/*    */ import Physical.Fighters.MinerModule.EventData;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Timer;
/*    */ import java.util.TimerTask;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*    */ import org.bukkit.event.player.PlayerInteractEvent;
/*    */ 
/*    */ public class gongban extends AbilityBase
/*    */ {
/* 20 */   public static boolean ppon = false;
/*    */   
/*    */   public gongban() {
/* 23 */     InitAbility("공격반사", AbilityBase.Type.Active_Immediately, AbilityBase.Rank.S, new String[] {
/* 24 */       "철괴 좌클릭으로 능력을 사용합니다.", 
/* 25 */       "능력 사용 후 5초간 받는 모든 데미지를 돌려줍니다." });
/* 26 */     InitAbility(60, 0, true);
/* 27 */     RegisterLeftClickEvent();
/* 28 */     EventManager.onEntityDamageByEntity.add(new EventData(this, 1));
/*    */   }
/*    */   
/*    */   public int A_Condition(Event event, int CustomData)
/*    */   {
/* 33 */     if (CustomData == 0) {
/* 34 */       PlayerInteractEvent e = (PlayerInteractEvent)event;
/* 35 */       if ((PlayerCheck(e.getPlayer())) && (ItemCheck(Physical.Fighters.MinerModule.ACC.DefaultItem)) && !EventManager.DamageGuard) {
/* 36 */         return 0;
/*    */       }
/*    */     }
/*    */     
/* 40 */     if (CustomData == 1) {
/* 41 */       EntityDamageByEntityEvent Event = (EntityDamageByEntityEvent)event;
/* 42 */       if ((PlayerCheck(Event.getEntity())) && (ppon) && 
/* 43 */         ((Event.getDamager() instanceof Player)) && !EventManager.DamageGuard) {
/* 44 */         Player p = (Player)Event.getEntity();
/* 45 */         Player t = (Player)Event.getDamager();
/* 46 */         t.damage(Event.getDamage(), p);
/* 47 */         Event.setCancelled(true);
/*    */       }
/*    */     }
/*    */     
/* 51 */     return -1;
/*    */   }
/*    */   
/*    */   public void A_Effect(Event event, int CustomData) {
/* 55 */     PlayerInteractEvent e = (PlayerInteractEvent)event;
/* 56 */     Bukkit.broadcastMessage(ChatColor.GRAY + "지금부터 5초간 " + e.getPlayer().getName() + "님을 공격시 가한 데미지를 돌려받습니다.");
/* 57 */     ppon = true;
/* 58 */     Timer timer = new Timer();
/* 59 */     timer.schedule(new offTimer(), 5000L); }
/*    */   
/*    */   class offTimer extends TimerTask { offTimer() {}
/*    */     
/* 63 */     public void run() { gongban.ppon = false;
/* 64 */       gongban.this.GetPlayer().sendMessage(ChatColor.DARK_PURPLE + "지속시간이 끝났습니다.");
/*    */     }
/*    */   }
/*    */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\gongban.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */