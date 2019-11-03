/*    */ package Physical.Fighters.AbilityList;
/*    */ 
/*    */ import Physical.Fighters.MainModule.AbilityBase;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Rank;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Type;
/*    */ import Physical.Fighters.MainModule.EventManager;
/*    */ import Physical.Fighters.MinerModule.EventData;
/*    */ import Physical.Fighters.PhysicalFighters;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Random;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.World;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.potion.PotionEffect;
/*    */ import org.bukkit.potion.PotionEffectType;
/*    */ 
/*    */ 
/*    */ public class Infighter
/*    */   extends AbilityBase
/*    */ {
/*    */   public Infighter()
/*    */   {
/* 26 */     if (PhysicalFighters.SRankUsed)
/*    */     {
/* 28 */       InitAbility("인파이터", AbilityBase.Type.Passive_AutoMatic, AbilityBase.Rank.A, new String[] {
/* 29 */         "주먹으로 모든것을 해결하는 능력입니다.", 
/* 30 */         "주먹으로 공격하면 대상에게 큰 충격을 받습니다.", 
/* 31 */         "10%확률로 폭발이 일어나며 대상이 넉백됩니다.", 
/* 32 */         "20%의 확률로 대상을 5초간 그로기상태로 만듭니다." });
/* 33 */       InitAbility(0, 0, true);
/* 34 */       EventManager.onEntityDamageByEntity.add(new EventData(this));
/*    */     }
/*    */   }
/*    */   
/*    */   public int A_Condition(Event event, int CustomData)
/*    */   {
/* 40 */     EntityDamageByEntityEvent Event = (EntityDamageByEntityEvent)event;
/* 41 */     if ((!EventManager.DamageGuard) && 
/* 42 */       (PlayerCheck(Event.getDamager()))) {
/* 43 */       return 0;
/*    */     }
/* 45 */     return -1;
/*    */   }
/*    */   
/*    */   public void A_Effect(Event event, int CustomData)
/*    */   {
/* 50 */     EntityDamageByEntityEvent e = (EntityDamageByEntityEvent)event;
/* 51 */     Player p = (Player)e.getDamager();
/* 52 */     Player t = (Player)e.getEntity();
/* 53 */     if (p.getItemInHand().getTypeId() == 0) {
/* 54 */       Random r = new Random();
/* 55 */       int dmg = 5 + r.nextInt(12);
/* 56 */       e.setDamage(dmg);
/* 57 */       if (Math.random() <= 0.1D) {
/* 58 */         t.getWorld().createExplosion(t.getLocation(), 0.0F);
/* 59 */         Location l1 = p.getLocation();
/* 60 */         AbilityBase.goVelocity(t, l1, -3);
/*    */       }
/* 62 */       if (Math.random() <= 0.2D) {
/* 63 */         t.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 100, 0), true);
/* 64 */         t.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 0), true);
/* 65 */         t.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 100, 0), true);
/* 66 */         t.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 0), true);
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Infighter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */