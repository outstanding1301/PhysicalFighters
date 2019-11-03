/*    */ package Physical.Fighters.AbilityList;
/*    */ 
/*    */ import Physical.Fighters.MainModule.AbilityBase.Rank;
import Physical.Fighters.MainModule.AbilityBase;
/*    */ import Physical.Fighters.MainModule.EventManager;
/*    */ import java.util.Collection;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.player.PlayerInteractEvent;
/*    */ import org.bukkit.potion.PotionEffect;
/*    */ import org.bukkit.potion.PotionEffectType;
/*    */ import org.bukkit.util.Vector;
/*    */ 
/*    */ public class SuperFan extends AbilityBase
/*    */ {
/*    */   public SuperFan()
/*    */   {
/* 19 */     InitAbility("선풍기", Physical.Fighters.MainModule.AbilityBase.Type.Active_Immediately, AbilityBase.Rank.B, new String[] {
/* 20 */       "철괴를 들고 왼쪽클릭하면 바라보는 방향의 플레이어들을  날려버립니다.", 
/* 21 */       "이때 날라간 플레이어들은 무더위에 시원함을 느껴 체력이 회복됩니다.", 
/* 22 */       "하지만 강한 바람에 의해 눈을 뜨기가 힘들고, 허약해집니다." });
/* 23 */     InitAbility(20, 0, true);
/* 24 */     RegisterLeftClickEvent();
/*    */   }
/*    */   
/*    */   public int A_Condition(Event event, int CustomData)
/*    */   {
/* 29 */     PlayerInteractEvent Event = (PlayerInteractEvent)event;
/* 30 */     if ((PlayerCheck(Event.getPlayer())) && (ItemCheck(Physical.Fighters.MinerModule.ACC.DefaultItem)) && !EventManager.DamageGuard)
/*    */     {
/* 32 */       return 0;
/*    */     }
/* 34 */     return -1;
/*    */   }
/*    */   
/*    */   public void A_Effect(Event event, int CustomData) {
/* 38 */     PlayerInteractEvent Event = (PlayerInteractEvent)event;
/* 39 */     Player p = Event.getPlayer();
/* 40 */     Location l = Event.getPlayer().getLocation();
/* 41 */     Location l2 = Event.getPlayer().getLocation();
/* 42 */     Location l3 = Event.getPlayer().getLocation();
/* 43 */     l3.setY(l.getY() - 1.0D);
/* 44 */     double degrees = Math.toRadians(-(l.getYaw() % 360.0F));
/* 45 */     double ydeg = Math.toRadians(-(l.getPitch() % 360.0F));
/* 46 */     for (int i = 1; i < 10; i++) {
/* 47 */       l2.setX(l.getX() + 1 * i + 2.0D * (Math.sin(degrees) * Math.cos(ydeg)));
/* 48 */       l2.setY(l.getY() + 1 * i + 2.0D * Math.sin(ydeg));
/* 49 */       l2.setZ(l.getZ() + 1 * i + 2.0D * (Math.cos(degrees) * Math.cos(ydeg)));
/* 50 */       Player[] pp = Bukkit.getOnlinePlayers();
/* 51 */       for (int ii = 0; ii < (Bukkit.getOnlinePlayers()).length; ii++) {
/* 52 */         if (pp[ii] != GetPlayer())
/*    */         {
/* 54 */           Location loc = pp[ii].getLocation();
/* 55 */           if (l2.distance(loc) <= 3.0D)
/*    */           {
/* 57 */             if (!EventManager.DamageGuard)
/*    */             {
/* 59 */               pp[ii].setVelocity(pp[ii].getVelocity().add(
/* 60 */                 l3.toVector()
/* 61 */                 .subtract(pp[ii].getLocation().toVector()).normalize()
/* 62 */                 .multiply(-2.2D)));
/* 63 */               pp[ii].addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 0), true);
/* 64 */               pp[ii].addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 2), true);
/* 65 */               pp[ii].addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 100, 2), false);
/* 66 */               pp[ii].addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 100, 2), false);
/* 67 */               pp[ii].sendMessage(org.bukkit.ChatColor.LIGHT_PURPLE + "선풍기의 강력한 바람에 힘을 잃었습니다!");
/*    */             }
/*    */           }
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\SuperFan.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */