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
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*    */ import org.bukkit.event.entity.EntityDamageEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Yeomryo
/*    */   extends AbilityBase
/*    */ {
/* 26 */   public static int guard = 30;
/* 27 */   private Timer t = new Timer();
/*    */   
/* 29 */   public Yeomryo() { InitAbility("야스오", AbilityBase.Type.Passive_Manual, AbilityBase.Rank.S, new String[] {
/* 30 */       "데미지를 흡수하는 30의 보호막을 얻으며", "공격시 피격 대상에게 3의 추가데미지를 입힙니다.", "보호막은 5초당 10씩 회복됩니다." });
/* 31 */     InitAbility(0, 0, true);
/* 32 */     EventManager.onEntityDamageByEntity.add(new EventData(this, 2));
/* 33 */     EventManager.onEntityDamage.add(new EventData(this, 3));
/*    */   }
/*    */   
/*    */   public int A_Condition(Event event, int CustomData)
/*    */   {
/* 38 */     if (CustomData == 2) {
/* 39 */       EntityDamageByEntityEvent e2 = (EntityDamageByEntityEvent)event;
/* 40 */       if (PlayerCheck(e2.getDamager())) {
/* 41 */         if ((e2.getEntity() instanceof Player)) {
/* 42 */           e2.setDamage((int) (e2.getDamage() + 3.0D));
/*    */         }
/*    */         else {
/* 45 */           e2.setDamage(50);
/*    */         }
/*    */       }
/* 48 */     } else if (CustomData == 3) {
/* 49 */       EntityDamageEvent e3 = (EntityDamageEvent)event;
/* 50 */       if (PlayerCheck(e3.getEntity())) {
/* 51 */         Player p = (Player)e3.getEntity();
/* 52 */         if (guard > 0) {
/* 53 */           guard = (int)(guard - e3.getDamage());
/* 54 */           if (guard <= 0) {
/* 55 */             p.sendMessage(ChatColor.YELLOW + "보호막으로 " + (e3.getDamage() - guard) + "의 데미지를 흡수했습니다. (남은 보호막:" + guard + ")");
/* 56 */             p.sendMessage(ChatColor.RED + "보호막이 깨졌습니다!");
/* 57 */             e3.setDamage(e3.getDamage() - guard);
/* 58 */             guard = 0;
/*    */           } else {
/* 60 */             p.sendMessage(ChatColor.YELLOW + "보호막으로 " + e3.getDamage() + "의 데미지를 흡수했습니다. (남은 보호막:" + guard + ")");
/* 61 */             e3.setDamage(0);
/*    */           }
/*    */         }
/*    */       }
/*    */     }
/*    */     
/* 67 */     return -1;
/*    */   }
/*    */   
/*    */   public void A_Effect(Event event, int CustomData) {}
/*    */   
/*    */   class Heal
/*    */     extends TimerTask
/*    */   {
/*    */     Player p;
/*    */     
/*    */     public Heal(Player pp)
/*    */     {
/* 79 */       this.p = pp;
/*    */     }
/*    */     
/*    */     public void run() {
/* 83 */       if (Yeomryo.guard != 30) {
/* 84 */         if (Yeomryo.guard + 10 >= 30) {
/* 85 */           Yeomryo.guard = 30;
/* 86 */           this.p.sendMessage(ChatColor.GREEN + "보호막이 완전히 회복되었습니다.");
/*    */         } else {
/* 88 */           Yeomryo.guard += 10;
/* 89 */           this.p.sendMessage(ChatColor.GREEN + "보호막이 10 회복되었습니다 (보호막 체력:" + Yeomryo.guard + ")");
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Yeomryo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */