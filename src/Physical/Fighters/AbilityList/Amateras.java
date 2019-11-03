/*    */ package Physical.Fighters.AbilityList;
/*    */ 
/*    */ import Physical.Fighters.MainModule.AbilityBase;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Rank;
/*    */ import Physical.Fighters.MainModule.AbilityBase.ShowText;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Type;
/*    */ import Physical.Fighters.MainModule.EventManager;
/*    */ import Physical.Fighters.MinerModule.ACC;
/*    */ import java.util.Timer;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.World;
/*    */ import org.bukkit.block.Block;
import org.bukkit.entity.Damageable;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.player.PlayerInteractEvent;
/*    */ 
/*    */ 
/*    */ public class Amateras
/*    */   extends AbilityBase
/*    */ {
/* 22 */   Timer timer = new Timer();
/*    */   
/*    */   public Amateras() {
/* 25 */     InitAbility("아마테라스", AbilityBase.Type.Active_Immediately, AbilityBase.Rank.S, new String[] {
/* 26 */       "능력 사용시 체력을 소비해서 보고있는 사물을 태워버립니다.", 
/* 27 */       "*아카이누와 블레이즈 등 불에 내성이 있는 적에게는 통하지 않습니다." });
/* 28 */     InitAbility(0, 0, true, AbilityBase.ShowText.Custom_Text);
/* 29 */     RegisterRightClickEvent();
/*    */   }
/*    */   
/*    */   public int A_Condition(Event event, int CustomData)
/*    */   {
/* 34 */     PlayerInteractEvent Event = (PlayerInteractEvent)event;
/* 35 */     if ((PlayerCheck(Event.getPlayer())) && (ItemCheck(ACC.DefaultItem)) && 
/* 36 */       (!EventManager.DamageGuard)) {
/* 37 */       return 0;
/*    */     }
/* 39 */     return -1;
/*    */   }
/*    */   
/*    */   public void A_Effect(Event event, int CustomData)
/*    */   {
/* 44 */     Block block = GetPlayer().getTargetBlock(null, 0);
/* 45 */     Location ll = block.getLocation();
/* 46 */     Location l2 = block.getLocation();
/* 47 */     l2.setY(ll.getY() + 1.0D);
/* 48 */     if (block.getWorld().getBlockAt(l2).getType() == Material.AIR) {
/* 49 */       block.getWorld().getBlockAt(l2).setType(Material.FIRE);
/* 50 */       GetPlayer().setHealth((int) (((Damageable)GetPlayer()).getHealth() - 0.0D));
/*    */     } else {
/* 52 */       block.getWorld().getHighestBlockAt(ll).setType(Material.FIRE);
/* 53 */       GetPlayer().setHealth((int) (((Damageable)GetPlayer()).getHealth() - 0.0D));
/*    */     }
/*    */   }
/*    */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Amateras.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */