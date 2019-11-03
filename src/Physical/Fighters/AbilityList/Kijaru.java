/*    */ package Physical.Fighters.AbilityList;
/*    */ 
/*    */ import Physical.Fighters.MainModule.AbilityBase;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Rank;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Type;
/*    */ import Physical.Fighters.MainModule.EventManager;
/*    */ import Physical.Fighters.MinerModule.ACC;
/*    */ import Physical.Fighters.MinerModule.EventData;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Timer;
/*    */ import java.util.TimerTask;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.World;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*    */ import org.bukkit.event.entity.EntityDamageEvent;
/*    */ import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
/*    */ 
/*    */ public class Kijaru extends AbilityBase
/*    */ {
/*    */   public Kijaru()
/*    */   {
/* 26 */     InitAbility("키자루", AbilityBase.Type.Active_Immediately, AbilityBase.Rank.SS, new String[] {
/* 27 */       "철괴로 타격을 당한 상대를 빛의 속도로 타격합니다.", 
/* 28 */       "상대는 엄청난 속도로 멀리 날라갑니다. 당신도 상대를 따라 근접하게 날라갑니다.", 
/* 29 */       "낙하데미지를 받지 않습니다." });
/* 30 */     InitAbility(45, 0, true);
/* 31 */     EventManager.onEntityDamageByEntity.add(new EventData(this));
/* 32 */     EventManager.onEntityDamage.add(new EventData(this, 3));
/*    */   }
/*    */   
/*    */   public int A_Condition(Event event, int CustomData)
/*    */   {
/* 37 */     if (CustomData == 3)
/*    */     {
/* 39 */       EntityDamageEvent Event2 = (EntityDamageEvent)event;
/* 40 */       if ((PlayerCheck(Event2.getEntity())) && 
/* 41 */         (Event2.getCause() == EntityDamageEvent.DamageCause.FALL)) {
/* 42 */         Event2.setCancelled(true);
/* 43 */         GetPlayer().sendMessage(ChatColor.GREEN + "사뿐하게 떨어져 데미지를 받지 않았습니다.");
/*    */       }
/*    */     }
/*    */     else
/*    */     {
/* 48 */       EntityDamageByEntityEvent Event = (EntityDamageByEntityEvent)event;
/* 49 */       if ((PlayerCheck(Event.getDamager())) && (ItemCheck(ACC.DefaultItem)) && !EventManager.DamageGuard)
/* 50 */         return 0;
/*    */     }
/* 52 */     return -1;
/*    */   }
/*    */   
/*    */   public void A_Effect(Event event, int CustomData)
/*    */   {
/* 57 */     EntityDamageByEntityEvent Event = (EntityDamageByEntityEvent)event;
/* 58 */     Event.setDamage(8);
/* 59 */     Location l2 = Event.getEntity().getLocation();
/* 60 */     l2.setY(Event.getEntity().getLocation().getY() + 1.0D);
/* 61 */     Event.getEntity().teleport(l2);
/* 62 */     goPlayerVelocity((Player)Event.getEntity(), (Player)Event.getDamager(), -10);
/* 63 */     Event.getEntity().getWorld().createExplosion(Event.getEntity().getLocation(), 0.0F);
/* 64 */     Timer timer = new Timer();
/* 65 */     timer.schedule(new Kizaru(Event.getDamager(), Event.getEntity()), 1000L);
/*    */   }
/*    */   
/*    */   class Kizaru extends TimerTask {
/*    */     Player player22;
/*    */     Player player;
/*    */     
/*    */     Kizaru(Entity entity, Entity entity2) {
/* 73 */       this.player22 = ((Player)entity);
/* 74 */       this.player = ((Player)entity2);
/*    */     }
/*    */     
/*    */     public void run()
/*    */     {
/* 79 */       Location loc2 = this.player.getLocation();
/* 80 */       loc2.setY(this.player.getLocation().getY() + 2.0D);
/* 81 */       this.player22.teleport(loc2);
/*    */     }
/*    */   }
/*    */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Kijaru.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */